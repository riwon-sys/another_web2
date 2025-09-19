package example.day02._03과제;

// =======================================================================================
// ✅ BoardController01 — Day02 [ _03과제 ]: 게시판 RestController
// 과제 명세 (경로 통일):
//  1) 글쓰기(POST)            /day02/task1/board
//  2) 전체 글 조회(GET)       /day02/task1/board
//  3) 개별 글 조회(GET)       /day02/task1/board/view?bno=1
//  4) 개별 글 수정(PUT)       /day02/task1/board
//  5) 개별 글 삭제(DELETE)    /day02/task1/board?bno=1
//
// 메모리 저장소(간단 버전):
//  - DB 대신 List<BoardDto> 사용
//  - bno 는 서버 측 auto_increment 로 1씩 증가
//
// 주의사항:
//  - 스레드 안전성은 고려하지 않음(학습용). 실무에서는 동시성 제어 필요.
//  - 경로는 과제 명세에 맞춰 /day02/task1/board 로 통일.
// rw 25-09-17
// =======================================================================================

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/day02/task1/board") // ✅ 과제 명세 경로로 통일
public class BoardController01 { // CS

    // -----------------------------------------------------------------------------
    // [메모리 테이블] — 간단한 학습용 저장소 (DB 대체)
    // -----------------------------------------------------------------------------
    private final List<BoardDto> boardTable = new ArrayList<>();
    private int auto_increment = 1; // 글 등록 시 bno 자동 증가

    // ===================================================================================
    // [1] 글쓰기 : POST /day02/task1/board
    // - 요청(JSON): { "title": "...", "content": "..." }
    // - 처리 흐름:
    //   1) 저장할 데이터들을 DTO로 받는다. (@RequestBody)
    //   2) 서버에서 bno 자동 할당 후 리스트에 저장
    //   3) 결과 true/false 응답
    // ===================================================================================
    @PostMapping("") // CS
    public boolean create(@RequestBody BoardDto boardDto) {
        System.out.println("BoardController01.create"); // soutm

        // [1] 서버에서 PK(bno) 자동 할당
        boardDto.setBno(auto_increment);

        // [2] 리스트(DB 대체)에 저장
        boardTable.add(boardDto);

        // [3] auto_increment 증가
        auto_increment++;

        return true; // 저장 성공
    } // fe

    // ===================================================================================
    // [2] 전체 글 조회 : GET /day02/task1/board
    // - 응답(JSON): [ {bno, title, content}, ... ]
    // - 처리 흐름:
    //   1) (입력 없음)
    //   2) 메모리 리스트 전체 반환
    // ===================================================================================
    @GetMapping("") // CS
    public List<BoardDto> findAll() {
        System.out.println("BoardController01.findAll"); // soutm
        return boardTable; // 리스트 전체 반환
    } // fe

    // ===================================================================================
    // [3] 개별 글 조회 : GET /day02/task1/board/view?bno=1
    // - 요청: bno (QueryString)
    // - 처리 흐름:
    //   1) 조회할 번호를 받는다.
    //   2) 리스트에서 해당 bno 를 가진 항목을 찾는다. (for / index 기반)
    //   3) 찾으면 DTO 반환, 없으면 null 반환
    // ===================================================================================
    @GetMapping("/view") // CS
    public BoardDto findOne(@RequestParam(name = "bno") int bno) {
        System.out.println("BoardController01.findOne, bno=" + bno); // soutm+soutv

        for (int i = 0; i < boardTable.size(); i++) { // i <= size-1 대신 i < size 권장
            BoardDto row = boardTable.get(i);
            if (row.getBno() == bno) {
                return row;
            }
        }
        return null; // 단순 학습용: 실무에선 404 처리 권장
    } // fe

    // ===================================================================================
    // [4] 개별 글 수정 : PUT /day02/task1/board
    // - 요청(JSON): { "bno": 1, "title": "수정제목", "content": "수정내용" }
    // - 처리 흐름:
    //   1) 수정할 번호와 새로운 내용을 DTO로 받는다.
    //   2) 리스트에서 bno가 같은 항목을 찾는다.
    //   3) 찾으면 해당 객체의 필드를 set(...)으로 수정한다.
    //   4) 결과 true/false 응답
    // ===================================================================================
    @PutMapping("") // CS
    public boolean update(@RequestBody BoardDto boardDto) {
        System.out.println("BoardController01.update, req=" + boardDto); // soutp

        for (int i = 0; i < boardTable.size(); i++) {
            BoardDto target = boardTable.get(i);
            if (target.getBno() == boardDto.getBno()) {
                // ✅ 실제 수정 로직 (원본 코드에는 누락되어 있었음)
                target.setTitle(boardDto.getTitle());
                target.setContent(boardDto.getContent());
                return true;
            }
        }
        return false; // 해당 bno 없음
    } // fe

    // ===================================================================================
    // [5] 개별 글 삭제 : DELETE /day02/task1/board?bno=1
    // - 요청: bno (QueryString)
    // - 처리 흐름:
    //   1) 삭제할 번호를 받는다.
    //   2) 리스트에서 해당 번호를 찾는다.
    //   3) 찾으면 remove(i) 로 삭제한다.
    //   4) 결과 true/false 응답
    // ===================================================================================
    @DeleteMapping("") // ✅ 클래스 레벨 경로에 이어지므로 여기선 빈 문자열 사용
    public boolean delete(@RequestParam(name = "bno") int bno) {
        System.out.println("BoardController01.delete, bno=" + bno); // soutm+soutv

        for (int i = 0; i < boardTable.size(); i++) {
            if (boardTable.get(i).getBno() == bno) {
                boardTable.remove(i);
                return true;
            }
        }
        return false;
    } // fe

} // CE
