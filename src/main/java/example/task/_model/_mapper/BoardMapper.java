/*  BoardMapper 인터페이스 | rw 25-09-24 생성
    - XML 없이 @SQL 어노테이션으로만 SQL을 매핑합니다.
    - 테이블명은 가이드 지시대로 'taskboard' 입니다.
*/

package example.task._model._mapper;

// [A] MyBatis 어노테이션
import org.apache.ibatis.annotations.*;

// [D] 내부 모듈 import
import example.task._model._dto.BoardDto;

import java.util.List;

@Mapper
public interface BoardMapper { // CS

    // =======================================================================================
    // ✅ 1. 글쓰기
    /*
        - 매핑 방식: @Insert
        - 대상 테이블: taskboard
        - SQL: INSERT INTO taskboard(title, content) VALUES(#{title}, #{content})
        - rw) POST /spring/task/board
          R: { "title":"", "content":"" }
          W: int (영향 행 수) → Service 에서 boolean 으로 변환
    */
    @Insert("INSERT INTO taskboard(title, content) VALUES(#{title}, #{content})")
    int save(BoardDto dto);

    // =======================================================================================
    // ✅ 2. 전체 글 조회
    /*
        - 매핑 방식: @Select
        - SQL: SELECT bno, title, content FROM taskboard ORDER BY bno DESC
        - rw) GET /spring/task/board
          R: -
          W: List<BoardDto>
    */
    @Select("SELECT bno, title, content FROM taskboard ORDER BY bno DESC")
    List<BoardDto> findAll();

    // =======================================================================================
    // ✅ 3. 개별 글 조회
    /*
        - 매핑 방식: @Select
        - SQL: SELECT bno, title, content FROM taskboard WHERE bno = #{bno}
        - rw) GET /spring/task/board/view
          R: ?bno=1
          W: BoardDto | null
    */
    @Select("SELECT bno, title, content FROM taskboard WHERE bno = #{bno}")
    BoardDto findById(int bno);

    // =======================================================================================
    // ✅ 4. 개별 글 수정
    /*
        - 매핑 방식: @Update
        - SQL: UPDATE taskboard SET title = #{title}, content = #{content} WHERE bno = #{bno}
        - rw) PUT /spring/task/board
          R: { "bno":0, "title":"", "content":"" }
          W: int → Service 에서 boolean 변환
    */
    @Update("""
            UPDATE taskboard
               SET title = #{title},
                   content = #{content}
             WHERE bno = #{bno}
            """)
    int update(BoardDto dto);

    // =======================================================================================
    // ✅ 5. 개별 글 삭제
    /*
        - 매핑 방식: @Delete
        - SQL: DELETE FROM taskboard WHERE bno = #{bno}
        - rw) DELETE /spring/task/board
          R: ?bno=1
          W: int → Service 에서 boolean 변환
    */
    @Delete("DELETE FROM taskboard WHERE bno = #{bno}")
    int delete(int bno);

} // CE