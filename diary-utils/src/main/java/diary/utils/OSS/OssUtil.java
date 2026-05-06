package diary.utils.OSS;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Date;

@Component
@Slf4j
public class OssUtil {
    @Resource
    private OSS ossClient;

    @Value("${aliyun.oss.bucket-name}")
    private String bucketName;
    /**
     * 生成OSS签名URL
     * @param ossUrl OSS URL或object key
     * @return 签名URL
     */
    public String generateSignedUrlByKey(String ossUrl) {
        try {
            // 如果传入的是完整的OSS URL，提取object key
            String objectKey = extractObjectKeyFromUrl(ossUrl);

            // 生成签名URL，有效期5分钟
            String signedUrl = generateSignedUrl(objectKey);
            log.debug("生成签名URL成功，objectKey: {}", objectKey);
            return signedUrl;
        } catch (Exception e) {
            log.error("生成签名URL失败, ossUrl: {}", ossUrl, e);
            throw new RuntimeException("生成签名URL失败: " + e.getMessage(), e);
        }
    }
    /**
     * 从OSS URL中提取object key
     */
    public String extractObjectKeyFromUrl(String ossUrl) {
        // 如果已经是object key（不包含http），直接返回
        if (!ossUrl.startsWith("http://") && !ossUrl.startsWith("https://")) {
            return ossUrl;
        }

        try {
            // 从URL中提取object key
            // 格式: https://bucket-name.endpoint/object-key
            URI url = new URI(ossUrl);
            String path = url.getPath();
            // 移除开头的 /
            if (path.startsWith("/")) {
                path = path.substring(1);
            }
            return path;
        } catch (Exception e) {
            log.error("从URL提取object key失败: {}", ossUrl, e);
            // 如果提取失败，尝试直接返回
            return ossUrl;
        }
    }

    public String getSignedUrlByFileName(String fileName) {
        try {
            // 生成签名URL，有效期5分钟
            String signedUrl = generateSignedUrl(fileName);
            log.debug("生成签名URL成功，fileName: {}", fileName);
            return signedUrl;
        } catch (Exception e) {
            log.error("生成签名URL失败, fileName: {}", fileName, e);
            throw new RuntimeException("生成签名URL失败: " + e.getMessage(), e);
        }
    }

    private String generateSignedUrl(String key) {
        Date expiration = new Date(System.currentTimeMillis() + 5 * 60 * 1000);
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, key);
        request.setExpiration(expiration);
        request.setMethod(HttpMethod.GET);

        return ossClient.generatePresignedUrl(request).toString();
    }

}
