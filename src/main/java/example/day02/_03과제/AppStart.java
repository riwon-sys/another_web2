package example.day02._03과제;

// =======================================================================================
// ✅ AppStart.java — Day02 _03과제 실행 진입점
// - 스프링 부트를 구동하여 내장 톰캣 서버를 실행합니다.
// - @ComponentScan 범위 안에 있는 @RestController, @Service, @Component 들이 자동 등록됩니다.
// - 본 과제의 BoardController01 이 자동으로 로딩되어 API 테스트가 가능합니다.
// rw 25-09-17
// =======================================================================================

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // CS
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class, args); // soutm
    } // fe
} // CE