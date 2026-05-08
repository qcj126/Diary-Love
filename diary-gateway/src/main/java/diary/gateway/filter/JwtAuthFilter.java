package diary.gateway.filter;

import diary.utils.jwt.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * JWT 认证过滤器
 * 在每个请求到达时校验 token 是否有效
 */
@Component
public class JwtAuthFilter implements GlobalFilter, Ordered {

    @Resource
    private JwtUtil jwtUtil;

    // 需要排除认证的路径列表
    private static final List<String> EXCLUDE_PATHS = Arrays.asList(
            "/user/login",
            "/user/register",
            "/user/verifyCode",
            "/user/resetPassword",
            "/actuator/**",
            "/favicon.ico"
    );
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        // 检查是否需要跳过认证
        if (shouldSkipAuth(path)) {
            return chain.filter(exchange);
        }

        // 从请求头中获取 Authorization token
        String authHeader = request.getHeaders().getFirst("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        String token = authHeader.substring(7); // 移除 "Bearer " 前缀

        try {
            // 校验 token
            String username = jwtUtil.extractUsername(token);
            if (username == null || username.trim().isEmpty()) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            // 将用户名添加到请求属性中，供后续服务使用
            exchange.getAttributes().put("username", username);

        } catch (Exception e) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        return chain.filter(exchange);
    }
    
    private boolean shouldSkipAuth(String path) {
        for (String excludePath : EXCLUDE_PATHS) {
            if (excludePath.endsWith("**")) {
                String prefix = excludePath.substring(0, excludePath.length() - 2);
                if (path.startsWith(prefix)) {
                    return true;
                }
            } else if (path.equals(excludePath)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1; // 在路由之前执行
    }

}
