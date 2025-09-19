package example.day02._03과제;

// =======================================================================================
// ✅ BoardDto — 게시글 전달용 DTO (Data Transfer Object)
// - 클라이언트 ↔ 서버 간 데이터 운반 전용 클래스
// - @RequestBody(JSON) ↔ BoardDto 자동 매핑: JSON 키명 == 필드명 일치 필요
// rw 25-09-17
// =======================================================================================

import lombok.*;

@Setter @Getter @ToString
@AllArgsConstructor @NoArgsConstructor
public class BoardDto { // CS
    // PK 격 역할 — 서버에서 자동 증가값 할당 (메모리 리스트 기준)
    private int bno;

    // 제목 / 내용 — 클라이언트가 JSON Body로 전달
    private String title;
    private String content;
} // CE