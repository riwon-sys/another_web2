// ======================================================================
// ✅ AppStart (스프링 부트 실행 진입점)
// - 내장 톰캣을 띄우고, 동일/하위 패키지의 컴포넌트(@Controller/@Service/@Mapper)를 스캔
// ======================================================================
package example.day03._03과제04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class, args);
    }
}