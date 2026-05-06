package diary.config.consts;

import java.util.Arrays;
import java.util.List;

public class FileTypeConst {
    // 内容类型前缀
    public static final String CONTENT_TYPE_IMAGE_PREFIX = "image/";
    public static final String CONTENT_TYPE_VIDEO_PREFIX = "video/";
    public static final String CONTENT_TYPE_AUDIO_PREFIX = "audio/";
    public static final String CONTENT_TYPE_APPLICATION_PREFIX = "application/";
    
    // 图片扩展名
    public static final List<String> IMAGE_EXTENSIONS = Arrays.asList(
            "jpg", "jpeg", "png", "gif", "bmp", "webp", "tiff", "svg"
    );
    
    // 视频扩展名
    public static final List<String> VIDEO_EXTENSIONS = Arrays.asList(
            "mp4", "avi", "mov", "wmv", "flv", "mkv", "webm", "mpeg", "3gp", "m4v"
    );
    
    // 音频扩展名
    public static final List<String> AUDIO_EXTENSIONS = Arrays.asList(
            "mp3", "wav", "aac", "flac", "ogg", "m4a", "wma", "opus"
    );
    
    // 文档扩展名
    public static final List<String> DOCUMENT_EXTENSIONS = Arrays.asList(
            "pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt", "csv"
    );
}
