package diary.utils.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret:your256bitsecretkeymustbeatleast32characterslong}")
    private String secret;

    @Value("${jwt.expiration:7200000}")
    private long expirationMs; // 2小时

    /**
     * 生成 JWT token
     *
     * @param username 用户名
     * @return JWT token
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /**
     * 从 token 中提取 username
     *
     * @param token token
     * @return username
     */
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}