/*  BoardService 클래스 | rw 25-09-24 생성
    - 가이드 명세 그대로 Mapper 호출, 반환값을 boolean/객체로 표준화합니다.
    - 추가 검증/기능 없이 최소한만 구현합니다.
*/

package example.task._service;

// [D] 내부 모듈 import
import example.task._model._dto.BoardDto;
import example.task._model._mapper.BoardMapper;

// [A] 스프링 DI/컴포넌트
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class BoardService { // CS

    @Autowired
    private BoardMapper boardMapper; // [*] 매퍼 주입

    // =======================================================================================
    // ✅ 1. 글쓰기
    /*
        - rw) POST /spring/task/board
          R: {title, content}
          W: boolean
    */
    public boolean save(BoardDto dto) {
        return boardMapper.save(dto) > 0;
    }

    // =======================================================================================
    // ✅ 2. 전체 글 조회
    /*
        - rw) GET /spring/task/board
          R: -
          W: List<BoardDto>
    */
    public List<BoardDto> findAll() {
        return boardMapper.findAll();
    }

    // =======================================================================================
    // ✅ 3. 개별 글 조회
    /*
        - rw) GET /spring/task/board/view
          R: bno
          W: BoardDto | null
    */
    public BoardDto findById(int bno) {
        return boardMapper.findById(bno);
    }

    // =======================================================================================
    // ✅ 4. 개별 글 수정
    /*
        - rw) PUT /spring/task/board
          R: {bno, title, content}
          W: boolean
    */
    public boolean update(BoardDto dto) {
        return boardMapper.update(dto) > 0;
    }

    // =======================================================================================
    // ✅ 5. 개별 글 삭제
    /*
        - rw) DELETE /spring/task/board
          R: bno
          W: boolean
    */
    public boolean delete(int bno) {
        return boardMapper.delete(bno) > 0;
    }

} // CE