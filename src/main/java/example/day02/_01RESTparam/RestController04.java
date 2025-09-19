package example.day02._01RESTparam;

// =======================================================================================
// ✅ [패키지 설명]
// - example.day02._01RESTparam : Day 02, REST 파라미터(Body/JSON) 학습 패키지
// - RestController04 : POST(JSON Body) 매핑 실습 컨트롤러 (@RequestBody 중심)
// =======================================================================================

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController                         // ✅ JSON 반환용 REST 컨트롤러
@RequestMapping("/day02")               // ✅ 공통 URL 접두사: /day02
public class RestController04 { // CS = Class Start

    // ===================================================================================
    // [1] HTTP 요청 Body(JSON) → 자바 객체로 매핑
    // - 사용 상황 : 주로 POST/PUT 에서 Body에 JSON을 담아 전송할 때
    // - 핵심 애노테이션 : @RequestBody
    //   * 요청의 Content-Type 이 "application/json" 일 때, JSON → 자바 타입으로 변환/주입
    //   * @RequestBody 를 생략하면 @ModelAttribute 가 적용되어 'Body 매핑'이 깨집니다. (반드시 명시!)
    // - 바인딩 규칙 :
    //   * JSON 키 이름 ↔ DTO의 필드명이 '정확히' 같아야 자동 매핑됩니다.
    //   * DTO에 없는 키는 무시되고, DTO에 있는데 JSON에 없는 필드는 null/기본값이 됩니다.
    // - 예시 URL/Body :
    //   METHOD  : POST
    //   URL     : http://localhost:8080/day02/dopost01
    //   Headers : Content-Type: application/json
    //   Body    : { "name": "유재석", "age": 40 }
    // ===================================================================================
    @PostMapping("/dopost01")
    public boolean method1(@RequestBody MemberDto memberDto) {
        System.out.println("RestController04.method1");         // soutm : 현재 함수명 로그
        System.out.println("memberDto = " + memberDto);         // soutp : 파라미터/객체 전체 로그
        return true; // ✅ 단순 성공 응답 (JSON: true)
    } // fe = function end

    // ===================================================================================
    // [2] HTTP 요청 Body(JSON) → Map 으로 매핑
    // - DTO 대신, 키/값 컬렉션으로 유연하게 받으려는 경우 사용
    // - 장점 : 빠른 프로토타이핑, 필드가 자주 바뀔 때 유리
    // - 단점 : 타입 안정성/검증이 어려움 → 실무에서는 DTO 권장
    // - 예시 URL/Body :
    //   METHOD  : POST
    //   URL     : http://localhost:8080/day02/dopost02
    //   Headers : Content-Type: application/json
    //   Body    : { "name": "유재석", "age": "40", "email": "test@example.com" }
    // ===================================================================================
    @PostMapping("/dopost02")
    public boolean method2(@RequestBody Map<String, String> map) {
        System.out.println("RestController04.method2");
        System.out.println("map = " + map); // 예: {name=유재석, age=40, email=test@example.com}
        return true; // ✅ 단순 성공 응답 (JSON: true)
    } // fe

} // CE = Class End