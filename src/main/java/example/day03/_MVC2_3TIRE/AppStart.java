// ========================================================================
// ✅ Day03 - MVC2 3계층 구조 (AppStart.java)
// - 스프링 부트 실행을 위한 시작 클래스
// - 프로젝트 전체의 진입점(Entry Point) 역할
// ========================================================================

package example.day03._MVC2_3TIRE;
// 현재 클래스가 위치한 패키지 경로
// - example: 루트 패키지
// - day03: 학습일 기준 day03
// - _MVC2_3TIRE: MVC 3계층 구조 연습용

import org.springframework.boot.SpringApplication;       // 스프링 애플리케이션 실행 클래스
import org.springframework.boot.autoconfigure.SpringBootApplication;
// @SpringBootApplication: 스프링 부트의 자동 설정 + 컴포넌트 스캔 + 설정을 묶어주는 핵심 어노테이션

// ========================================================================
// ✅ @SpringBootApplication
// - @Configuration : 스프링 설정 클래스임을 명시
// - @EnableAutoConfiguration : 스프링이 자동으로 환경 설정 수행
// - @ComponentScan : 같은 패키지 및 하위 패키지를 자동으로 스캔해서 Bean 등록
// ========================================================================
@SpringBootApplication
public class AppStart {

    // main 메소드 : 자바 프로그램의 시작점
    public static void main(String[] args) {
        // ✅ SpringApplication.run()
        // - 스프링 부트를 실행시키는 명령어
        // - 내장 톰캣 서버를 자동으로 띄움
        // - 현재 프로젝트 전체를 스프링 컨테이너로 관리 시작
        SpringApplication.run(AppStart.class, args);
    }
}
