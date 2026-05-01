package diary.file.service;

public interface RedisService {
    Long getPhotoCount();

    void updatePhotoCount(long photoCount);
}
