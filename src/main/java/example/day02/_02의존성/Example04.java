package example.day02._02의존성;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
    [스프링 방법4 : IoC + DI (권장 패턴: 생성자 주입)]
    - 장점
      1) 불변성: 의존 필드를 final로 고정 → 런타임 중 교체 방지
      2) 명시성: 필요한 의존성이 생성자 시그니처에 드러남 → 설계/리뷰 용이
      3) 테스트 용이: 생성자 파라미터에 대역(Stub/Mock) 주입이 쉽다
      4) 순환참조 조기 감지: 컨테이너가 빈 생성 단계에서 오류를 빨리 발견
*/

// [1] 서비스 클래스 : @Service (@Component 포함)
@Service // 비즈니스 로직인 경우 @Service 사용 권장 (역할 명확화 + 컴포넌트 스캔 대상)
class SampleService4 { // CS
    void method() {
        System.out.println("SampleService4.method");
    } // fe
} // CE

// [2] 컨트롤러 클래스 : 생성자 주입 (권장)
class SampleController4 { // CS
    // final : 불변 보장 (생성자에서 1회 주입 후 변경 불가)
    private final SampleService4 sampleService4;

    // @Autowired는 생성자가 1개면 생략 가능 (명시해도 무방)
    @Autowired
    public SampleController4(SampleService4 sampleService4) {
        this.sampleService4 = sampleService4;
    }

    public void method() {
        sampleService4.method();
    } // fe
} // CE

public class Example04 { } // 파일 진입점(예시)