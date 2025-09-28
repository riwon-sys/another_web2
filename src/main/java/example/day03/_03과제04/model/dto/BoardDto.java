// ======================================================================
// ✅ DTO (Data Transfer Object)
// - 계층 간(Controller ↔ Service ↔ Mapper) 데이터 전달용 그릇
// ======================================================================
package example.day03._03과제04.model.dto;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class BoardDto {
    private int bno;        // 글번호(PK, AUTO_INCREMENT)
    private String title;   // 제목
    private String content; // 내용
}