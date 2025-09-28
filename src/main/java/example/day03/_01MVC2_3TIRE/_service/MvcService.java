// ========================================================================
// ✅ Service
// - 비즈니스 로직을 담당하는 계층
// - Controller에서 넘어온 요청을 처리하고 Repository를 통해 DB 접근
// - 중간에서 "로직"을 구현하는 핵심 계층
// ========================================================================
package example.day03._01MVC2_3TIRE._service;

import example.day03._01MVC2_3TIRE._model._respository.MvcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Service 계층임을 명시하고 스프링 컨테이너에 등록
// 스프링이 자동으로 제어(IoC)하고 필요한 곳에 주입(DI)해줌
public class MvcService {

    // ✅ 순환참조 조심 (Controller를 다시 주입하지 않음)
    // @Autowired MvcController mvcController;

    // Controller → Service → Repository 구조

    // ✅ Repository 주입 (생성자 방식 권장)
    final MvcRepository mvcRepository;

    @Autowired
    public MvcService(MvcRepository mvcRepository) {
        this.mvcRepository = mvcRepository;
    }
}