// ========================================================================
// ✅ Controller
// - 사용자의 요청(Request)을 제일 먼저 받는 계층
// - URL과 메서드를 매핑해서 요청을 처리
// - 내부적으로는 Service를 호출해서 로직 실행 후 결과 반환
// ========================================================================
package example.day03._MVC2_3TIRE._controller;

import example.day03._MVC2_3TIRE._service.MvcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController // Controller 계층임을 명시하고 스프링 컨테이너에 등록
// 스프링이 자동으로 제어(IoC)하고 HTTP 요청에 응답할 수 있게 해줌
public class MvcController {

    // ✅ 필드 주입
    // 스프링 컨테이너가 관리하는 MvcService 객체를 자동으로 넣어줌
    @Autowired
    MvcService mvcService;
}