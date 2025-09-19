package example.day02._01RESTparam;

// =======================================================================================
// ✅ [패키지 설명]
// - example.day02._01RESTparam : Day02, REST 파라미터 학습용 패키지
// - RestController03 : @RequestParam, @ModelAttribute 등 파라미터 매핑 방법 실습 컨트롤러
// =======================================================================================

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController                         // ✅ 해당 클래스가 REST 컨트롤러임을 명시 (JSON 반환)
@RequestMapping("/day02")              // ✅ 클래스 내 모든 메소드의 기본 URL 앞에 "/day02" 자동 접두사
public class RestController03 { // CS = Class Start

    // ===================================================================================
    // [1] HttpServletRequest / HttpServletResponse 직접 사용
    // - 과거 Servlet 방식
    // - req.getParameter("매개변수명") 으로 값 꺼내오기
    // - resp.getWriter().println("값") 으로 응답 작성하기
    // ===================================================================================
    @GetMapping("/doget01") // URL : http://localhost:8080/day02/doget01?name=홍길동
    public void method1(HttpServletRequest req ,  HttpServletResponse resp) throws Exception {
        System.out.println("RestController03.method1"); // soutm → 현재 함수명 출력

        // (1) 요청객체(req)에서 쿼리스트링 매개변수(name) 직접 추출
        String name = req.getParameter("name");
        System.out.println("name = " + name); // soutv → 가장 가까운 변수값 출력

        // (2) 응답객체(resp)를 사용해 응답 작성
        resp.setContentType("application/json"); // 응답 타입 : JSON
        resp.getWriter().println("true");        // 응답 body : true
    } // fe = function end

    // ===================================================================================
    // [2] Spring 방식 - @RequestParam 사용
    // - 더 간단하고 직관적인 방법
    // - 옵션:
    //   1. name="매개변수명" → 쿼리스트링 변수명과 매핑
    //   2. defaultValue="값" → 값이 없을 경우 기본값 지정
    //   3. required=true     → 필수 여부 지정 (false 가능)
    // ===================================================================================
    @GetMapping("/doget02") // URL : http://localhost:8080/day02/doget02?name=김철수
    public boolean method2(
            @RequestParam( name = "name" , defaultValue = "김리원" , required = true) String name){
        System.out.println("RestController03.method2"); // 함수명 출력
        System.out.println("name = " + name);           // 매개변수 출력
        return true; // 반환 시 자동으로 @ResponseBody 적용 → JSON 타입 응답
    } // fe

    // ===================================================================================
    // [2-2] 다중 파라미터 매핑
    // - 여러 개의 쿼리스트링 값을 동시에 받을 수 있음
    // - URL 예시: http://localhost:8080/day02/doget03?name=홍길동&age=20
    // ===================================================================================
    @GetMapping("/doget03")
    public int method3(
            @RequestParam(name="name") String name,
            @RequestParam(name="age") int age){
        System.out.println("RestController03.method3");
        System.out.println("name = " + name + ", age = " + age); // 두 변수 출력
        return 3; // 단순 숫자 응답
    } // fe

    // ===================================================================================
    // [3] Map<String,String>으로 한번에 받기
    // - 모든 쿼리스트링을 Map 컬렉션에 담아줌
    // - URL 예시: http://localhost:8080/day02/doget04?name=홍길동&age=20&addr=인천
    //   → map = {name=홍길동, age=20, addr=인천}
    // ===================================================================================
    @GetMapping("/doget04")
    public String method4(@RequestParam Map<String,String> map){
        System.out.println("RestController03.method4");
        System.out.println("map = " + map); // soutp → 매개변수 전체 출력
        return "success"; // 단순 문자열 응답
    } // fe

    // ===================================================================================
    // [4] DTO(@ModelAttribute) 매핑
    // - 쿼리스트링과 DTO 멤버변수명이 동일하면 자동 매핑
    // - @ModelAttribute는 생략 가능 (기본값)
    // - @RequestParam은 기본타입/Map/List/String만 지원,
    //   @ModelAttribute는 개발자가 만든 DTO까지 매핑 지원
    // - URL 예시:
    //   http://localhost:8080/day02/doget05?mno=1&id=abc&pw=123&name=홍길동
    //   → MemberDto 객체에 자동 매핑
    // ===================================================================================
    @GetMapping("/doget05")
    public boolean method5(@ModelAttribute MemberDto memberDto){
        System.out.println("RestController03.method5");
        System.out.println("memberDto = " + memberDto); // Dto toString() 결과 출력
        return true; // 단순 true 반환
    } // fe

} // CE = Class End