// ======================================================================
// ✅ Mapper (MyBatis 전용 DAO 인터페이스)
// - 구현체는 MyBatis가 런타임에 자동 생성
// - XML의 <mapper namespace="..."> 와 FQN이 100% 일치해야 함
// ======================================================================
package example.day03._03과제04.model.mapper;

import example.day03._03과제04.model.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    // + SQL 매핑 하는 방법 : 1. .XML 파일에 작성된 sql 매핑 지원  2.@SQL어노테이션 매핑 지원


    // [4] 어노테이션 SQL 매핑
    // @Insert(" insert into day03board( title , content ) values( #{ title } , #{ content } ) ")
    int save(BoardDto boardDto);             // [C] 등록

    // @Select(" select * from day03board ")
    List<BoardDto> findAll();                // [R] 전체조회

    // @Select(" select * from day03board where bno = #{ bno }")
    BoardDto findById(int bno);              // [R] 개별조회

    // @Update(" update day03board set title = #{ title } , content = #{ content } where bno = #{ bno } ")
    int update(BoardDto boardDto);           // [U] 수정

    // @Delete(" delete from day03board where bno = #{ bno }" )
    int deleteById(int bno);                 // [D] 삭제
}