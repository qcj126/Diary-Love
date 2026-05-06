package diary.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class DiaryConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiaryConfigApplication.class, args);
        log.info("Diary Config Service 应用已经启动成功");
    }
}
