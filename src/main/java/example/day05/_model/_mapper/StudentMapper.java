// =======================================================================================
// [Day05] StudentMapper | rw 25-09-26
// - 설명 : MyBatis @어노테이션 기반 SQL (XML 불필요)
// - 패키지: example.day05._mapper
// =======================================================================================
package example.day05._mapper;

import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    // ===================================================================================
    // ✅ [1]. 등록
    /*
        - 매핑 방식: @Insert (POST)
        - 요청 파라미터: Map{name, kor, math}
        - 응답 데이터: int (처리 건수)
    */
    // ===================================================================================
    @Insert("INSERT INTO student(name, kor, math) VALUES(#{name}, #{kor}, #{math})")
    @Options(useGeneratedKeys = true, keyProperty = "sno")
    int save(Map<String, Object> map);

    // ===================================================================================
    // ✅ [2]. 전체조회
    /*
        - 매핑 방식: @Select (GET)
        - 요청 파라미터: X
        - 응답 데이터: List<Map>
    */
    // ===================================================================================
    @Select("<script>SELECT * FROM student ORDER BY sno DESC</script>")
    List<Map<String, Object>> findAll();

    // ===================================================================================
    // ✅ [3]. 수정
    /*
        - 매핑 방식: @Update (PUT)
        - 요청 파라미터: Map{sno, kor, math}
        - 응답 데이터: int
    */
    // ===================================================================================
    @Update("UPDATE student SET kor = #{kor}, math = #{math} WHERE sno = #{sno}")
    int update(Map<String, Object> map);

    // ===================================================================================
    // ✅ [4]. 삭제
    /*
        - 매핑 방식: @Delete (DELETE)
        - 요청 파라미터: sno
        - 응답 데이터: boolean
    */
    // ===================================================================================
    @Delete("DELETE FROM student WHERE sno = #{sno}")
    boolean delete(int sno);

    // ===================================================================================
    // ✅ [5]. 특정 점수 이상 조회 (동적 쿼리)
    /*
        - 매핑 방식: @Select + <if> (GET)
        - 요청 파라미터: minKor, minMath
        - 응답 데이터: List<Map>
    */
    // ===================================================================================
    @Select("""
        <script>
            SELECT * FROM student
            WHERE 1=1
              <if test="minKor  != null">AND kor  >= #{minKor}</if>
              <if test="minMath != null">AND math >= #{minMath}</if>
            ORDER BY sno DESC
        </script>
    """)
    List<Map<String, Object>> findStudentScores(Integer minKor, Integer minMath);

    // ===================================================================================
    // ✅ [6]. 여러 명 등록 (배치)
    /*
        - 매핑 방식: @Insert + <foreach> (POST)
        - 요청 파라미터: List<Map>{name, kor, math}
        - 응답 데이터: boolean
    */
    // ===================================================================================
    @Insert("""
        <script>
            INSERT INTO student(name, kor, math) VALUES
            <foreach collection="list" item="s" separator=",">
                (#{s.name}, #{s.kor}, #{s.math})
            </foreach>
        </script>
    """)
    boolean saveAll(List<Map<String, Object>> list);
}