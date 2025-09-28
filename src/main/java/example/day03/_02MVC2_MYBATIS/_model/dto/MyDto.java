// ======================================================================
// ✅ DTO (Data Transfer Object)
// - 계층 간(Controller↔Service↔Mapper) 데이터를 전달하는 그릇
// - 화면/요청/응답 형태에 맞춰 필드를 설계합니다.
// ======================================================================
package example.day03._02MVC2_MYBATIS._model.dto;

import lombok.*;

@Setter @Getter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
public class MyDto {
    private int id;       // PK
    private String name;  // 이름
    private int age;      // 나이
}
