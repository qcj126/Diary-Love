package diary.file.service.downloadservice;

import java.util.List;
import java.util.Map;

public interface DownloadService {
    /**
     * 批量下载图片
     * @param ossUrls OSS图片URL列表
     * @return 下载结果
     */
    Map<String, Object> batchDownloadPhotos(List<String> ossUrls);
}
