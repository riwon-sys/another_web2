/*  BoardController 클래스 | rw 25-09-24 생성
    - 가이드 지침서대로 게시판 REST 컨트롤러를 구성합니다.
    - 엔드포인트
        1) 글쓰기        POST    /spring/task/board
        2) 전체 조회     GET     /spring/task/board
        3) 개별 조회     GET     /spring/task/board/view  (?bno=1)
        4) 개별 수정     PUT     /spring/task/board
        5) 개별 삭제     DELETE  /spring/task/board       (?bno=1)
*/

package example.task._controller;

// [A] 스프링 Web 관련 어노테이션
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

// [D] 내부 모듈 import
import example.task._model._dto.BoardDto;
import example.task._service.BoardService;

@RestController                                // [A-1] REST 전용 컨트롤러
@RequestMapping("/spring/task/board")          // [A-2] 공통 URL prefix
public class BoardController { // CS

    @Autowired
    private BoardService boardService; // [*] 서비스 주입

    // =======================================================================================
    // ✅ 1. 글쓰기
    /*
        - 매핑 방식: POST
        - 요청 URL: /spring/task/board
        - 요청 데이터: { "title":"", "content":"" } (RequestBody, JSON)
        - 응답 데이터: true | false
        - rw) POST /spring/task/board
    */
    @PostMapping("")
    public boolean save(@RequestBody BoardDto dto) {
        return boardService.save(dto);
    }

    // =======================================================================================
    // ✅ 2. 전체 글 조회
    /*
        - 매핑 방식: GET
        - 요청 URL: /spring/task/board
        - 요청 데이터: 없음
        - 응답 데이터: [ { "bno":0, "title":"", "content":"" }, ... ]
        - rw) GET /spring/task/board
    */
    @GetMapping("")
    public List<BoardDto> findAll() {
        return boardService.findAll();
    }

    // =======================================================================================
    // ✅ 3. 개별 글 조회
    /*
        - 매핑 방식: GET
        - 요청 URL: /spring/task/board/view
        - 요청 데이터: ?bno=1 (QueryString)
        - 응답 데이터: { "bno":0, "title":"", "content":"" } | null
        - rw) GET /spring/task/board/view
    */
    @GetMapping("/view")
    public BoardDto findById(@RequestParam int bno) {
        return boardService.findById(bno);
    }

    // =======================================================================================
    // ✅ 4. 개별 글 수정
    /*
        - 매핑 방식: PUT
        - 요청 URL: /spring/task/board
        - 요청 데이터: { "bno":0, "title":"", "content":"" } (RequestBody, JSON)
        - 응답 데이터: true | false
        - rw) PUT /spring/task/board
    */
    @PutMapping("")
    public boolean update(@RequestBody BoardDto dto) {
        return boardService.update(dto);
    }

    // =======================================================================================
    // ✅ 5. 개별 글 삭제
    /*
        - 매핑 방식: DELETE
        - 요청 URL: /spring/task/board
        - 요청 데이터: ?bno=1 (QueryString)
        - 응답 데이터: true | false
        - rw) DELETE /spring/task/board
    */
    @DeleteMapping("")
    public boolean delete(@RequestParam int bno) {
        return boardService.delete(bno);
    }

} // CE