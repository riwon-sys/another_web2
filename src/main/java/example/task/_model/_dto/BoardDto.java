/*  BoardDto 클래스 | rw 25-09-24 생성
    - Controller ↔ Service ↔ Mapper 간 데이터 전달용 DTO입니다.
    - 가이드 요구 컬럼만 사용: bno, title, content
*/

package example.task._model._dto;

// [B] Lombok 어노테이션
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class BoardDto { // CS

    // =======================================================================================
    // [*] 필드
    private int bno;        // [1] 게시글 번호(PK, AUTO_INCREMENT)
    private String title;   // [2] 제목
    private String content; // [3] 내용
    // =======================================================================================

} // CE