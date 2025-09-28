// =======================================================================================
// [Day05] AppStart | rw 25-09-26
// - 역할 : Spring Boot 시작점
// - 규칙 : @MapperScan 사용 안 함( task 스타일 유지 ), 기본 패키지 자동 스캔 사용
// - 패키지: example.day05
// =======================================================================================
package example.day05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication   // basePackages 지정 X (example.day05.* 자동 스캔)
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class, args);
    }
}