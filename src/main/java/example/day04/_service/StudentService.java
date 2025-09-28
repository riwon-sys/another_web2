// =======================================================================================
// ✅ [Service]. StudentService
// fs) 트랜잭션/검증/비즈니스 규칙 담당. 이번 실습은 단순 위임 + 최소 검증.
// =======================================================================================
package example.day04._service;

import example.day04._model._dto.StudentDto;
import example.day04._model._mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    // [1] 등록
    public int save(StudentDto dto) {
        // fe) 간단 검증 예시: 음수 점수 방지
        if (dto.getKor() < 0 || dto.getMath() < 0) { return 0; }
        return studentMapper.save(dto);
    }

    // [2] 전체조회
    public List<StudentDto> findAll() {
        return studentMapper.findAll();
    }

    // [3] 수정
    public int update(StudentDto dto) {
        if (dto.getSno() <= 0) { return 0; }
        return studentMapper.update(dto);
    }

    // [4] 삭제
    public int delete(int sno) {
        if (sno <= 0) { return 0; }
        return studentMapper.delete(sno);
    }
}