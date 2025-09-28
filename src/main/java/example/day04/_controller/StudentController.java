// =======================================================================================
// ✅ [Controller]. StudentController
// fs) REST 엔드포인트. 프론트 index.js와 URL/메서드 형식 맞춤.
// fe) 삭제는 쿼리스트링 ?sno= 로 받도록 유지 (프론트 코드와 동일)
// =======================================================================================
package example.day04._controller;

import example.day04._model._dto.StudentDto;
import example.day04._service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/day04/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // ===================================================================================
    // ✅ [1]. 등록 : POST /day04/student
    // req : { "name":"", "kor":0, "math":0 }
    // res : 1 | 0
    // ===================================================================================
    @PostMapping("")
    public int save(@RequestBody StudentDto dto) {
        System.out.println("StudentController.save " + dto);
        return studentService.save(dto);
    }

    // ===================================================================================
    // ✅ [2]. 전체조회 : GET /day04/student
    // res : [ {sno, name, kor, math}, ... ]
    // ===================================================================================
    @GetMapping("")
    public List<StudentDto> findAll() {
        System.out.println("StudentController.findAll");
        return studentService.findAll();
    }

    // ===================================================================================
    // ✅ [3]. 수정 : PUT /day04/student
    // req : { "sno":1, "name":"", "kor":0, "math":0 }
    // res : 1 | 0
    // ===================================================================================
    @PutMapping("")
    public int update(@RequestBody StudentDto dto) {
        System.out.println("StudentController.update " + dto);
        return studentService.update(dto);
    }

    // ===================================================================================
    // ✅ [4]. 삭제 : DELETE /day04/student?sno=1
    // res : 1 | 0
    // (대안) REST 엄격 버전 → DELETE /day04/student/{sno} + @PathVariable
    // ===================================================================================
    @DeleteMapping("")
    public int delete(@RequestParam int sno) {
        System.out.println("StudentController.delete sno=" + sno);
        return studentService.delete(sno);
    }
}