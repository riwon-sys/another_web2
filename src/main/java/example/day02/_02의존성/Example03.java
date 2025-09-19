package example.day02._02의존성;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
    [스프링 방법3 : IoC(제어의 역전) + DI(의존성 주입)]
    - IoC : 객체 생성/생명주기를 개발자(new) 대신 스프링 컨테이너가 관리.
    - DI  : 필요한 의존 객체를 컨테이너가 주입해줌.
    - 여기서는 "필드 주입" 사용 → 간단하지만 권장 X (테스트/불변성/설계 명확성 약함).
*/

// [1] 서비스 클래스 : 스프링이 빈으로 등록/관리 (IoC)
@Component // 스프링 컨테이너가 'SampleService3' 인스턴스를 빈으로 등록/관리
class SampleService3 { // CS
    void method() {
        System.out.println("SampleService3.method");
    } // fe
} // CE

// [2] 컨트롤러 클래스 : DI로 빈 주입 (필드 주입 방식)
class SampleController3 { // CS
    // 필드 주입: 간단하지만, 테스트/명시성/불변성/순환참조 감지에 불리 → 지양 권고
    @Autowired
    private SampleService3 sampleService3;

    public void method() {
        sampleService3.method();
    } // fe
} // CE

public class Example03 { } // 파일 진입점(예시)