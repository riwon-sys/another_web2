// =======================================================================================
// [Day05] StudentService | rw 25-09-26
// - 설명 : Controller <-> Mapper 중간 계층 (task 스타일 주석)
// - 패키지: example.day05._service
// =======================================================================================
package example.day05._service;

import example.day05._mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentMapper studentMapper;

    // ===================================================================================
    // ✅ [1]. 등록
    /*
        - 흐름: 파라미터 검증 → 매퍼 save 호출 → 처리 건수 반환
        - 응답: int (1=성공)
    */
    // ===================================================================================
    public int save(Map<String, Object> map) {
        if (map == null || map.get("name") == null) return 0;
        return studentMapper.save(map);
    }

    // ===================================================================================
    // ✅ [2]. 전체조회
    /*
        - 흐름: 매퍼 findAll 위임
        - 응답: List<Map>
    */
    // ===================================================================================
    public List<Map<String, Object>> findAll() {
        return studentMapper.findAll();
    }

    // ===================================================================================
    // ✅ [3]. 수정
    /*
        - 흐름: sno 유효성 체크 → 매퍼 update 호출
        - 응답: int
    */
    // ===================================================================================
    public int update(Map<String, Object> map) {
        if (map == null || map.get("sno") == null) return 0;
        return studentMapper.update(map);
    }

    // ===================================================================================
    // ✅ [4]. 삭제
    /*
        - 흐름: 매퍼 delete 호출
        - 응답: boolean
    */
    // ===================================================================================
    public boolean delete(int sno) {
        return studentMapper.delete(sno);
    }

    // ===================================================================================
    // ✅ [5]. 특정 점수 이상 조회
    /*
        - 흐름: 매퍼 findStudentScores 호출
        - 응답: List<Map>
    */
    // ===================================================================================
    public List<Map<String, Object>> findStudentScores(int minKor, int minMath) {
        return studentMapper.findStudentScores(minKor, minMath);
    }

    // ===================================================================================
    // ✅ [6]. 여러 명 등록 (배치)
    /*
        - 흐름: 비어있는지 검사 → 매퍼 saveAll 호출
        - 응답: boolean
    */
    // ===================================================================================
    public boolean saveAll(List<Map<String, Object>> list) {
        if (list == null || list.isEmpty()) return false;
        return studentMapper.saveAll(list);
    }
}