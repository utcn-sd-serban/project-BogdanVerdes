package ro.utcn.sd.vba.videoplace;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class VideoPlaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoPlaceApplication.class, args);
    }

}
