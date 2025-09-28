/*  AppStart 클래스 | rw 25-09-24 생성
    - 스프링 부트 실행 진입점입니다.
    - 프로젝트에 기존 AppStart가 있으면 그 파일로 실행하세요. (중복 생성 금지)
*/

package example.task;

// [A] 스프링 부트 어노테이션/런처
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppStart { // CS
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class, args);
    }
} // CE