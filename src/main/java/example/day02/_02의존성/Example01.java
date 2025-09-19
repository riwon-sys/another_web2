package example.day02._02의존성;

/*
    [고전적인 방법1 : 인스턴스 직접 생성]
    - 스프링 없이 의존 대상을 내부에서 new로 직접 만든다.
    - 단점 : 강한 결합(의존 대상 교체/테스트 어려움), 확장성/유연성 저하.
*/

// [1] 서비스 클래스
class SampleService1 { // CS
    void method() {
        System.out.println("SampleService1.method"); // soutm
    } // fe
} // CE

// [2] 컨트롤러 클래스
class SampleController1 { // CS
    // 다른 클래스 메소드를 호출하는 고전적 방법 : 내부에서 직접 생성하여 사용 (강결합)
    // 교체하고 싶으면 코드 수정이 불가피 → OCP(Open-Closed) 위배
    SampleService1 sampleService1 = new SampleService1();

    public void method() {
        // 서비스 클래스 메소드 호출
        sampleService1.method();
    } // fe
} // CE

public class Example01 { } // 파일 진입점(예시)