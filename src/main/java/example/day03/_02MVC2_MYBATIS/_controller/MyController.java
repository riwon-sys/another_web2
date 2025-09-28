// ======================================================================
// ✅ Controller (요청의 입구)
// - HTTP 요청(URL/메서드) → Service 호출 → 응답 반환
// - 컨트롤러는 "흐름 제어"만 하고, 로직은 Service에 위임합니다.
// ======================================================================
package example.day03._02MVC2_MYBATIS._controller;

import example.day03._02MVC2_MYBATIS._model.dto.MyDto;
import example.day03._02MVC2_MYBATIS._service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {

    // 스프링 컨테이너가 생성/관리하는 MyService 빈을 주입(DI)
    @Autowired
    private MyService myService;

    // [CREATE] POST http://localhost:8080/day03/save
    // RequestBody JSON 예시: {"name":"유재석","age":40}
    @PostMapping("/day03/save")
    public int save(@RequestBody MyDto myDto) {
        System.out.println("MyController.save");   // 현재 메서드명 로그
        System.out.println("myDto = " + myDto);    // 요청 바디로 받은 DTO 내용 로그
        return myService.save(myDto);              // 로직은 서비스에 위임
    }

    // [READ-ALL] GET http://localhost:8080/day03/findall
    @GetMapping("/day03/findall")
    public List<MyDto> findAll() {
        System.out.println("MyController.findAll");
        return myService.findAll();
    }

    // [READ-ONE] GET http://localhost:8080/day03/findbyid?id=1
    @GetMapping("/day03/findbyid")
    public MyDto findById(@RequestParam int id) {
        System.out.println("MyController.findById");
        System.out.println("id = " + id);
        return myService.findById(id);
    }

    // [DELETE] DELETE http://localhost:8080/day03/deletebyid?id=1
    @DeleteMapping("/day03/deletebyid")
    public int deleteById(@RequestParam int id) {
        System.out.println("MyController.deleteById");
        System.out.println("id = " + id);
        return myService.deleteById(id);
    }

    // [UPDATE] PUT http://localhost:8080/day03/update
    // RequestBody JSON 예시: {"id":1,"name":"유재석2","age":402}
    @PutMapping("/day03/update")
    public int update(@RequestBody MyDto myDto) {
        System.out.println("MyController.update");
        System.out.println("myDto = " + myDto);
        return myService.update(myDto);
    }
}
