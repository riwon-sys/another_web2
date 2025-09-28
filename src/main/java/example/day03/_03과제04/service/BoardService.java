// ======================================================================
// ✅ Service (비즈니스 로직 계층)
// - 검증/계산/흐름 제어 담당. DB 접근은 Mapper에 위임
// ======================================================================
package example.day03._03과제04.service;

import example.day03._03과제04.model.dto.BoardDto;
import example.day03._03과제04.model.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;

    // [C] 등록
    public int save(BoardDto boardDto) {
        System.out.println("BoardService.save");
        System.out.println("boardDto = " + boardDto);
        return boardMapper.save(boardDto);
    }

    // [R] 전체조회
    public List<BoardDto> findAll() {
        return boardMapper.findAll();
    }

    // [R] 개별조회
    public BoardDto findById(int bno) {      // ✅ 변수명 오류 수정(id→bno 통일)
        return boardMapper.findById(bno);
    }

    // [U] 수정
    public int update(BoardDto boardDto) {
        return boardMapper.update(boardDto);
    }

    // [D] 삭제
    public int deleteById(int bno) {         // ✅ 변수명 오류 수정(id→bno 통일)
        return boardMapper.deleteById(bno);
    }
}