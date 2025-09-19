package example.day01._01어노테이션;

import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/* =========================================================
   [1] 메소드에 붙이는 커스텀 어노테이션 정의 (Annotation2)
   - RUNTIME: 실행 중에도 정보 유지 (리플렉션으로 읽기 가능)
   - METHOD : 메소드에만 붙일 수 있게 제한
   - value2에는 기본값 100 지정 (생략하면 100으로 읽힘)
   ========================================================= */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Annotation2 {
    String value1();        // 필수 속성 (항상 넣어야 함)
    int value2() default 100; // 선택 속성 (기본값 100)
}

/* =========================================================
   [2] 메소드에 Annotation2를 실제로 붙여 사용
   - @RestController는 스프링용 메타어노테이션(표시 용도)
   - 주의: 어노테이션의 값은 "메소드 내부 변수"처럼 쓰지 않고,
          리플렉션으로 꺼내서 읽습니다.
   ========================================================= */
@RestController
class TestClass2 {

    @Annotation2(value1 = "유재석", value2 = 40)
    public void method1() { } // 리플렉션으로 읽으면 (value1=유재석, value2=40)

    @Annotation2(value1 = "강호동") // value2 생략 → 기본값 100으로 읽힘
    public void method2() { } // 리플렉션으로 읽으면 (value1=강호동, value2=100)
}

/* =========================================================
   [3] 클래스/인터페이스/열거형에 붙이는 커스텀 어노테이션 (Annotation3)
   - TYPE: 클래스/인터페이스/열거형에 붙일 수 있음
   ========================================================= */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Annotation3 {
    String value1();
    int value2() default 100;
}

/* Annotation3를 클래스에 적용 */
@Annotation3(value1 = "유재석", value2 = 40)
class Member { }

/* =========================================================
   [4] 실행: 리플렉션으로 어노테이션 값 읽어보기
   - 클래스에 붙은 Annotation3 읽기
   - 메소드에 붙은 Annotation2 읽기(보너스)
   ========================================================= */
public class Example02 {
    public static void main(String[] args) {
        // (A) 클래스에 붙은 Annotation3 읽기
        Annotation3 a3 = Member.class.getAnnotation(Annotation3.class);
        if (a3 != null) {
            System.out.println("[Member 클래스의 Annotation3]");
            System.out.println("value1 = " + a3.value1()); // 유재석
            System.out.println("value2 = " + a3.value2()); // 40
        } else {
            System.out.println("Member에는 Annotation3가 없습니다.");
        }

        // (B) 메소드에 붙은 Annotation2 읽기 (보너스)
        System.out.println("\n[TestClass2 메소드의 Annotation2]");
        for (Method m : TestClass2.class.getDeclaredMethods()) {
            Annotation2 a2 = m.getAnnotation(Annotation2.class);
            if (a2 != null) {
                System.out.printf(
                        "%s() -> value1=\"%s\", value2=%d%n",
                        m.getName(), a2.value1(), a2.value2()
                );
            }
        }
    }
}
