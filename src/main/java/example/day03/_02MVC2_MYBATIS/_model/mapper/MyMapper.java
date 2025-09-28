// ======================================================================
// ✅ Mapper (MyBatis 전용 DAO 인터페이스)
// - SQL은 XML(또는 어노테이션)에 작성하고,
//   실행은 Mapper 메서드 호출로 수행합니다.
// - 구현체는 MyBatis가 런타임에 자동 생성합니다.
// ======================================================================
package example.day03._02MVC2_MYBATIS._model.mapper;

import example.day03._02MVC2_MYBATIS._model.dto.MyDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyMapper {

    // [C] 등록
    int save(MyDto myDto);

    // [R] 전체조회
    List<MyDto> findAll();

    // [R] 개별조회
    MyDto findById(@Param("id") int id);

    // [U] 개별수정
    int update(MyDto myDto);

    // [D] 개별삭제
    int deleteById(@Param("id") int id);
}
