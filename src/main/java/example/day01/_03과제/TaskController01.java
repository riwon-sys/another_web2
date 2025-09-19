// =======================================================================================
// TaskController01.java | rw 25-09-16 복기 주석 확장본 (코드 원문 불변, 주석만 추가)
// [과제 요약] DAY01 → _3과제: "게시판 RestController 만들기"
//   - AppStart 생성 (동일/상위 패키지에서 컴포넌트 스캔 되도록 구성)
//   - BoardController(여기서는 TaskController01) 생성
//   - 요구 URL/HTTP/응답 규격
//     1) 글쓰기(POST)          /day01/task/board       → boolean(true/false)
//     2) 전체 글 조회(GET)     /day01/task/board       → List<Map<String,String>> 예: [{bno:'1',btitle:'제목1'}, ...]
//     3) 개별 글 조회(GET)     /day01/task/board/view  → Map<String,String>     예: {bno:'1', btitle:'제목1'}
//     4) 개별 글 수정(PUT)     /day01/task/board       → boolean(true/false)
//     5) 개별 글 삭제(DELETE)  /day01/task/board       → int(삭제한 번호) 예: 3
//
// [학습 포인트]
//   - @RestController : @Controller + @ResponseBody (반환값을 HTTP 본문으로 직렬화해서 돌려줌)
//   - @RequestMapping 클래스 레벨 : 공통 URL prefix 지정 (하위 메서드 경로와 합쳐짐)
//   - @PostMapping / @GetMapping / @PutMapping / @DeleteMapping : HTTP 메서드별 축약 애너테이션
//   - 반환 타입에 따른 Content-Type (기본 Jackson 메시지 컨버터):
//         String → text/plain, Map/List/DTO → application/json
//
// [테스트 팁] (Talend/Postman/브라우저)
//   - 기본 포트: http://localhost:8080
//   - BASE 경로: /day01/task/board
//   - 예시: GET http://localhost:8080/day01/task/board/view  → 200 OK + {"bno":"1","btitle":"제목1"}
// =======================================================================================

package example.day01._03과제;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
// ✅ [설명] @RestController = @Controller + @ResponseBody
//    - 컨트롤러가 반환하는 값(문자열/Map/List/DTO 등)을 "뷰 렌더링"이 아니라 "HTTP 응답 본문"으로 직렬화해서 내려줍니다.
//    - 이 과제는 HTML 템플릿 없이 값(JSON/텍스트)을 바로 돌려보내는 REST 스타일을 연습합니다.
@RequestMapping(value = "/day01/task/board")
// ✅ [설명] 클래스 레벨 공통 URL(prefix) 지정
//    - 아래의 각 메서드 경로와 합쳐져 최종 URL이 됩니다.
//    - 예) 클래스: "/day01/task/board"  +  메서드: ""(빈 문자열)  → "/day01/task/board"
//    - 예) 클래스: "/day01/task/board"  +  메서드: "/view"       → "/day01/task/board/view"
public class TaskController01 { //  CS
// ℹ️ 과제 문서의 클래스명 예시는 "BoardController" 지만, 이름이 달라도 매핑만 올바르면 동작에는 문제 없습니다.

    // ===================================================================================
    // [1] 글쓰기: POST /day01/task/board
    // - 요청 데이터: 없음
    // - 응답 데이터: boolean (true/false)
    // - 의미: 글 쓰기 요청을 서버가 처리했다고 가정하고 성공 여부만 돌려주는 형태
    // - Talend 테스트:
    //     METHOD: POST
    //     URL   : http://localhost:8080/day01/task/board
    //     BODY  : (없음)
    //     RESP  : true (application/json)
    // ===================================================================================
    @PostMapping("") // ✅ 클래스 공통 URL 뒤에 추가 경로가 없으므로 최종 경로는 "/day01/task/board"
    public boolean method1(){return true;}

    // ===================================================================================
    // [2] 전체 글 조회: GET /day01/task/board
    // - 요청 데이터: 없음
    // - 응답 데이터: List<Map<String,String>>
    //      예: [ { "bno":"1", "btitle":"제목1" }, { "bno":"2", "btitle":"제목2" } ]
    // - 학습 목적: "리스트(여러 건) + 맵(키-값)" 구조를 JSON으로 바로 내려보내는 연습
    // - Talend 테스트:
    //     METHOD: GET
    //     URL   : http://localhost:8080/day01/task/board
    //     RESP  : JSON 배열 (application/json)
    //
    // ⚠️ 주의(학습 포인트 표시): 아래 map2를 만들고 값을 넣을 때 map1에 put하고 있어
    //    실제 실행 시 map2가 빈 객체로 내려갈 수 있습니다. (코드 수정은 금지 요청이 있어 주석으로만 안내)
    //    - 의도라면 그대로 두고, 의도가 아니면 추후 리팩토링 시 map2에 값을 넣도록 점검하면 좋아요.
    // ===================================================================================
    @GetMapping("")
    public List<Map<String,String>> method2(){
        List<Map<String,String>> list = new ArrayList<>();

        Map<String,String> map1 = new HashMap<>();
        map1.put("bno","1");           // 작성된 번호
        map1.put("btitle" , "제목1");  // 작성된 제목
        list.add(map1);                // ✅ 첫 번째 게시글 추가

        Map<String,String> map2 = new HashMap<>();
        map2.put("bno","2");           //
        map2.put("btitle" , "제목2");  //
        list.add(map2);                // ✅ 두 번째 게시글 추가

        return list; // ✅ 반환 타입: List<Map<String,String>> → JSON 배열로 직렬화되어 응답됨
    }

    // ===================================================================================
    // [3] 개별 글 조회: GET /day01/task/board/view
    // - 요청 데이터: 없음(파라미터 없이 예시 데이터 반환)
    // - 응답 데이터: Map<String,String>  예: {"bno":"1", "btitle":"제목1"}
    // - 학습 목적: "단일 건(한 개)" 데이터를 JSON 객체로 반환하는 연습
    // - Talend 테스트:
    //     METHOD: GET
    //     URL   : http://localhost:8080/day01/task/board/view
    //     RESP  : {"bno":"1","btitle":"제목1"}
    // ===================================================================================
    @GetMapping("/view")
    public Map<String,String> method3(){
        Map<String,String> map1 = new HashMap<>();
        map1.put("bno","1");           // 예시: 글 번호
        map1.put("btitle" , "제목1");  // 예시: 글 제목
        return map1;                   // ✅ 반환: JSON 객체
    }

    // ===================================================================================
    // [4] 개별 글 수정: PUT /day01/task/board
    // - 요청 데이터: 없음(학습용 예시. 실제 수정 데이터는 추후 @RequestBody 등으로 받게 됨)
    // - 응답 데이터: boolean (true/false)
    // - Talend 테스트:
    //     METHOD: PUT
    //     URL   : http://localhost:8080/day01/task/board
    //     BODY  : (없음)
    //     RESP  : true
    // ===================================================================================
    @PutMapping("")
    public boolean method4(){ return true; }

    // ===================================================================================
    // [5] 개별 글 삭제: DELETE /day01/task/board
    // - 요청 데이터: 없음(학습용 예시. 실제로는 삭제할 번호를 PathVariable/QueryParam/Body로 받는 식으로 확장)
    // - 응답 데이터: int (예: 삭제된 글 번호)
    // - Talend 테스트:
    //     METHOD: DELETE
    //     URL   : http://localhost:8080/day01/task/board
    //     RESP  : 3
    // ===================================================================================
    @DeleteMapping("")
    public int method5(){ return 3; }

} // CE

// =======================================================================================
// [추가 학습 노트 — 초보자용 개념 정리]
// 1) @RestController 를 쓰면, return 값이 그대로 HTTP 응답 본문(Body)로 나가요.
//    - String → text/plain
//    - Map/List/DTO → application/json (Jackson이 자동으로 JSON으로 바꿔줘요)
// 2) 클래스 @RequestMapping("/day01/task/board") 는 "공통 주소 접두사"예요.
//    - 메서드의 ""(빈 문자열)은 접두사 그대로를 의미해요. 즉 최종 경로는 "/day01/task/board"가 돼요.
// 3) HTTP 메서드별 축약 애너테이션
//    - @PostMapping = @RequestMapping(method = RequestMethod.POST)
//    - @GetMapping  = @RequestMapping(method = RequestMethod.GET)
//    - @PutMapping  = @RequestMapping(method = RequestMethod.PUT)
//    - @DeleteMapping = @RequestMapping(method = RequestMethod.DELETE)
// 4) 이 과제는 "값을 바로 내려보내는" REST 연습이므로, HTML 템플릿 파일이 없어도 200 OK + JSON/텍스트가 보여요.
// =======================================================================================
