package diary.utils.crypto;

import org.mindrot.jbcrypt.BCrypt;

/**
 * BCrypt密码工具类（不依赖Spring）
 * 使用jbcrypt库提供真正的BCrypt密码加密和匹配功能
 */
public class BCryptUtilNoSpring {
    
    // BCrypt默认强度（工作因子）
    private static final int BCRYPT_COST = 12;
    
    /**
     * 密码加密方法
     * @param password 原始密码
     * @return 加密后的密码字符串（包含盐值）
     */
    public static String encode(String password) {
        if (password == null || password.trim().isEmpty()) {
            return null;
        }
        
        try {
            // 使用BCrypt加密密码
            return BCrypt.hashpw(password.trim(), BCrypt.gensalt(BCRYPT_COST));
        } catch (Exception e) {
            throw new RuntimeException("密码加密失败", e);
        }
    }
    
    /**
     * 密码匹配方法
     * @param rawPassword 原始密码
     * @param encodedPassword 加密后的密码（由encode方法生成）
     * @return 是否匹配
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        if (rawPassword == null || encodedPassword == null) {
            return false;
        }
        
        try {
            // 使用BCrypt验证密码
            return BCrypt.checkpw(rawPassword.trim(), encodedPassword);
        } catch (Exception e) {
            return false;
        }
    }
}
