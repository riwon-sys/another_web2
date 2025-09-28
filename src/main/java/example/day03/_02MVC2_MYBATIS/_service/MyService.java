// ======================================================================
// ✅ Service (비즈니스 로직 계층)
// - 검증/계산/트랜잭션 경계 등 "업무 규칙"을 담당합니다.
// - DB 접근은 Mapper에 위임하고, 컨트롤러에 반환할 형태로 결과를 정리합니다.
// ======================================================================
package example.day03._02MVC2_MYBATIS._service;

import example.day03._02MVC2_MYBATIS._model.dto.MyDto;
import example.day03._02MVC2_MYBATIS._model.mapper.MyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {

    @Autowired
    private MyMapper myMapper;

    // [C] 등록
    public int save(MyDto myDto) {
        System.out.println("MyService.save");
        System.out.println("myDto = " + myDto);
        return myMapper.save(myDto);
    }

    // [R] 전체조회
    public List<MyDto> findAll() {
        System.out.println("MyService.findAll");
        // 매개변수가 없으므로 soutp(파라미터 출력) 로그가 없습니다.
        return myMapper.findAll();
    }

    // [R] 개별조회
    public MyDto findById(int id) {
        System.out.println("MyService.findById");
        System.out.println("id = " + id);
        return myMapper.findById(id);
    }

    // [U] 개별수정
    public int update(MyDto myDto) {
        System.out.println("MyService.update");
        System.out.println("myDto = " + myDto);
        return myMapper.update(myDto);
    }

    // [D] 개별삭제
    public int deleteById(int id) {
        System.out.println("MyService.deleteById");
        System.out.println("id = " + id);
        return myMapper.deleteById(id);
    }
}
