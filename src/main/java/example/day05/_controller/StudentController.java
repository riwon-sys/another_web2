// =======================================================================================
// [Day05] StudentController | rw 25-09-26
// - 공통 prefix: /day05/students
// - 패키지: example.day05._controller
// =======================================================================================
package example.day05._controller;

import example.day05._service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/day05/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    // ===================================================================================
    // ✅ [1]. 등록 (POST /day05/students)
    // - Body: { "name":"", "kor":0, "math":0 } → int(1/0)
    // ===================================================================================
    @PostMapping("")
    public int save(@RequestBody Map<String, Object> map) {
        return studentService.save(map);
    }

    // ===================================================================================
    // ✅ [2]. 전체조회 (GET /day05/students)
    // - Resp: [ { "sno":1,"name":"","kor":0,"math":0 }, ... ]
    // ===================================================================================
    @GetMapping("")
    public List<Map<String, Object>> findAll() {
        return studentService.findAll();
    }

    // ===================================================================================
    // ✅ [3]. 수정 (PUT /day05/students)
    // - Body: { "sno":1, "kor":90, "math":80 } → int(1/0)
    // ===================================================================================
    @PutMapping("")
    public int update(@RequestBody Map<String, Object> map) {
        return studentService.update(map);
    }

    // ===================================================================================
    // ✅ [4]. 삭제 (DELETE /day05/students?sno=1)
    // - Resp: boolean
    // ===================================================================================
    @DeleteMapping("")
    public boolean delete(@RequestParam int sno) {
        return studentService.delete(sno);
    }

    // ===================================================================================
    // ✅ [5]. 특정 점수 이상 (GET /day05/students/find/scores?minKor=&minMath=)
    // - Resp: List<Map>
    // ===================================================================================
    @GetMapping("/find/scores")
    public List<Map<String, Object>> findStudentScores(
            @RequestParam int minKor, @RequestParam int minMath) {
        return studentService.findStudentScores(minKor, minMath);
    }

    // ===================================================================================
    // ✅ [6]. 여러 명 등록 (POST /day05/students/save/all)
    // - Body: [ {"name":"A","kor":100,"math":90}, ... ] → boolean
    // ===================================================================================
    @PostMapping("/save/all")
    public boolean saveAll(@RequestBody List<Map<String, Object>> list) {
        return studentService.saveAll(list);
    }
}