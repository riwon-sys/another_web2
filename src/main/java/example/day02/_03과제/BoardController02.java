package example.day02._03과제;

// =======================================================================================
// ✅ BoardController02 — Day02 _03과제: 게시판 RestController (DAO 주입형)
// 요구 경로 (과제 명세):
//   - POST   /day02/task2/board
//   - GET    /day02/task2/board
//   - GET    /day02/task2/board/view?bno=1
//   - PUT    /day02/task2/board
//   - DELETE /day02/task2/board?bno=1
//
// 주요 포인트:
//  - 싱글톤 금지, IoC/DI로 DAO 주입 (✅ 생성자 주입 권장)
//  - 컨트롤러는 "입력 받기/검증/DAO 호출/응답" 역할만 담당
//  - 상태코드는 단순화(불린) — 과제 요구에 맞춤 (실무는 ResponseEntity 권장)
// rw 25-09-18
// =======================================================================================

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // CS : REST 컨트롤러(자동 JSON 변환)
@RequestMapping("/day02/task2/board") // ✅ 과제 요구 경로로 통일 (공통 접두사)
public class BoardController02 {

    // ------------------------------------------------------------------------------
    // [DI] 생성자 주입(권장) — final 로 불변 보장
    // ------------------------------------------------------------------------------
    private final BoardDao boardDao;

    public BoardController02(BoardDao boardDao) { // @Autowired 생략 가능(생성자 1개)
        this.boardDao = boardDao;
    }

    // ===================================================================================
    // [1] 글쓰기 : POST /day02/task2/board
    // - 요청(JSON): { "title": "..." , "content": "..." }
    // - 처리: bno는 서버(DAO)가 auto_increment로 할당
    // - 응답: true/false
    // ===================================================================================
    @PostMapping("") // CS
    public boolean create(@RequestBody BoardDto boardDto) {
        System.out.println("BoardController02.create"); // soutm
        return boardDao.method1(boardDto);
    } // fe

    // ===================================================================================
    // [2] 전체 글 조회 : GET /day02/task2/board
    // - 응답: [ {bno, title, content}, ... ]
    // ===================================================================================
    @GetMapping("") // CS
    public List<BoardDto> findAll() {
        System.out.println("BoardController02.findAll"); // soutm
        return boardDao.method2();
    } // fe

    // ===================================================================================
    // [3] 개별 글 조회 : GET /day02/task2/board/view?bno=#
    // - 응답: {bno, title, content} 또는 null(학습용)
    // ===================================================================================
    @GetMapping("/view") // CS
    public BoardDto findOne(@RequestParam(name = "bno") int bno) {
        System.out.println("BoardController02.findOne bno=" + bno); // soutm+soutv
        return boardDao.method3(bno);
    } // fe

    // ===================================================================================
    // [4] 개별 글 수정 : PUT /day02/task2/board
    // - 요청(JSON): { "bno": # , "title": "수정제목" , "content": "수정내용" }
    // - 응답: true/false
    // ===================================================================================
    @PutMapping("") // CS
    public boolean update(@RequestBody BoardDto boardDto) {
        System.out.println("BoardController02.update req=" + boardDto); // soutp
        return boardDao.method4(boardDto);
    } // fe

    // ===================================================================================
    // [5] 개별 글 삭제 : DELETE /day02/task2/board?bno=#
    // - 응답: true/false
    // ===================================================================================
    @DeleteMapping("") // ✅ 클래스 레벨 접두사와 결합됨
    public boolean delete(@RequestParam(name = "bno") int bno) {
        System.out.println("BoardController02.delete bno=" + bno); // soutm+soutv
        return boardDao.method5(bno);
    } // fe

} // CE