package example.day02._02의존성;

/*
    [고전적인 방법2 : 싱글톤 패턴]
    - 인스턴스 1개만 생성/공유하고 싶을 때 사용.
    - 장점 : 객체 생성 비용 줄이고 어디서든 동일 인스턴스 사용.
    - 단점 : 전역 상태/숨은 의존, 테스트 어려움, 멀티스레드/초기화 순서 문제, DIP 낮음.
*/

// [1] 서비스 클래스 (싱글톤)
class SampleService2 { // CS
    // (1) 클래스 로딩 시점에 단 하나의 인스턴스 생성 (eager initialization)
    private static final SampleService2 instance = new SampleService2();

    // (2) 외부에서 new 금지 (생성자 private)
    private SampleService2() {}

    // (3) 전역 접근자(정적 메소드)로 동일 인스턴스 제공
    public static SampleService2 getInstance() { return instance; }

    void method() {
        System.out.println("SampleService2.method");
    } // fe
} // CE

// [2] 컨트롤러 클래스
class SampleController2 { // CS
    // 전역 싱글톤에 의존 → 전역 의존성/숨은 결합 증가
    SampleService2 sampleService2 = SampleService2.getInstance();

    public void method() {
        sampleService2.method();
    } // fe
} // CE

public class Example02 { } // 파일 진입점(예시)