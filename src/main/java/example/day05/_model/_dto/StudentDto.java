// =======================================================================================
// [Day05] StudentDto | rw 25-09-26 생성
// - 설명: student 테이블 대응 테스트/전달용 DTO (강사 Map기반과 병행 사용)
// - 패키지: example.day05._model._dto
// =======================================================================================
package example.day05._model._dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {
    private Integer sno;   // PK
    private String  name;  // 이름
    private Integer kor;   // 국어
    private Integer math;  // 수학
}