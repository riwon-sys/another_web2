// ======================================================================
// ✅ Controller (요청의 입구)
// - URL/HTTP 메서드 매핑 → Service 호출 → 결과 반환
// - 주소는 모두 소문자로 통일
// ======================================================================
package example.day03._03과제04.controller;

import example.day03._03과제04.model.dto.BoardDto;
import example.day03._03과제04.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/day03/task/board")  // ✅ 과제 명세의 공통 prefix
public class BoardController {

    @Autowired
    private BoardService boardService;

    // [1] 글쓰기  POST /day03/task/board
    // Body: { "title": "...", "content": "..." }
    // 응답: 1(성공) / 0(실패)
    @PostMapping("")
    public int save(@RequestBody BoardDto boardDto) {
        System.out.println("BoardController.save");
        System.out.println("boardDto = " + boardDto);
        return boardService.save(boardDto);
    }

    // [2] 전체 글 조회  GET /day03/task/board
    @GetMapping("")
    public List<BoardDto> findAll() {
        return boardService.findAll();
    }

    // [3] 개별 글 조회  GET /day03/task/board/view?bno=1
    // ✅ 과제 명세에 맞춰 엔드포인트를 /view 로 제공
    @GetMapping("/view")
    public BoardDto find(@RequestParam int bno) {
        return boardService.findById(bno);
    }

    // [4] 글 수정  PUT /day03/task/board
    // Body: { "bno":1, "title":"...", "content":"..." }
    @PutMapping("")
    public int update(@RequestBody BoardDto boardDto) {
        return boardService.update(boardDto);
    }

    // [5] 글 삭제  DELETE /day03/task/board?bno=1
    @DeleteMapping("")
    public int delete(@RequestParam int bno) {
        return boardService.deleteById(bno);
    }
}