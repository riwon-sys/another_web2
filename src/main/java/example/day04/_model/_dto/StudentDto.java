// =======================================================================================
// ✅ [DTO]. StudentDto
// fs) Controller <-> Service <-> Mapper 사이 데이터 운반용
// fe) 필드명은 SQL의 컬럼과 1:1 매핑 (카멜/스네이크 자동변환은 MyBatis 설정에 따름)
// =======================================================================================
package example.day04._model._dto;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@Builder // 선택: 필요 시 빌더 사용
public class StudentDto {
    private int sno;     // 식별번호 (PK, auto_increment)
    private String name; // 학생명
    private int kor;     // 국어점수
    private int math;    // 수학점수
}