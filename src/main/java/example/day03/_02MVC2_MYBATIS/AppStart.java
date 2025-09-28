// ======================================================================
// ✅ AppStart (스프링 부트 실행 진입점)
// - 내장 톰캣을 띄우고, 컴포넌트 스캔(@Controller/@Service/@Mapper 등) 시작
// ======================================================================
package example.day03._02MVC2_MYBATIS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class, args);
    }
}
