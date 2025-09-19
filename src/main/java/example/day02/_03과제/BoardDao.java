package example.day02._03과제;

// =======================================================================================
// ✅ BoardDao — 메모리 기반 게시판 DAO (IoC/DI 대상, 싱글톤 구현 금지)
// 역할:
//  - 리스트를 "임시 DB"처럼 사용 (insert/findAll/findByBno/update/delete)
//  - bno 는 여기서만 auto_increment 로 할당/증가
// 주의:
//  - 학습용: 동시성 고려하지 않음 (실무는 동기화/Lock/Atomic 필요)
// rw 25-09-18
// =======================================================================================

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component // CS : 스프링 컨테이너가 빈으로 등록/관리 (의미상 @Repository도 가능)
public class BoardDao {

    // ------------------------------------------------------------------------------
    // [메모리 테이블] — 간단 학습용 저장소 (DB 대체)
    // ------------------------------------------------------------------------------
    private final List<BoardDto> boardTable = new ArrayList<>();
    private int auto_increment = 1; // bno 자동 증가 시드

    // ===================================================================================
    // [C] 글 등록 — DTO의 bno는 서버에서 채움
    // ===================================================================================
    public boolean method1(BoardDto boardDto) { // CS
        boardDto.setBno(auto_increment);   // 1) PK 부여
        boardTable.add(boardDto);          // 2) 저장
        auto_increment++;                  // 3) 증가
        return true;
    } // fe

    // ===================================================================================
    // [R] 전체 조회
    // ===================================================================================
    public List<BoardDto> method2() { // CS
        return boardTable; // 간단 반환(실무는 불변 뷰/복사본 고려)
    } // fe

    // ===================================================================================
    // [R] 개별 조회 — bno로 탐색
    // ===================================================================================
    public BoardDto method3(int bno) { // CS
        for (int index = 0; index < boardTable.size(); index++) { // ✅ '<' 로 범위 수정
            BoardDto row = boardTable.get(index);
            if (row.getBno() == bno) {
                return row; // 찾으면 즉시 반환
            }
        }
        return null; // 학습용: 실무는 Optional/예외/404 매핑
    } // fe

    // ===================================================================================
    // [U] 수정 — bno로 찾아 title/content 변경
    // ===================================================================================
    public boolean method4(BoardDto req) { // CS
        for (int index = 0; index < boardTable.size(); index++) { // ✅ '<' 로 범위 수정
            BoardDto target = boardTable.get(index);
            if (target.getBno() == req.getBno()) {
                // ✅ 실제 변경 반영 (원본 보존 + 필드만 업데이트)
                target.setTitle(req.getTitle());
                target.setContent(req.getContent());
                return true;
            }
        }
        return false;
    } // fe

    // ===================================================================================
    // [D] 삭제 — bno로 찾아 remove(index)
    // ===================================================================================
    public boolean method5(int bno) { // CS
        for (int index = 0; index < boardTable.size(); index++) { // ✅ '<' 로 범위 수정
            if (boardTable.get(index).getBno() == bno) {
                boardTable.remove(index);
                return true;
            }
        }
        return false;
    } // fe

} // CE