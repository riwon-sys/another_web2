package example.day01._01어노테이션;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/* [1] 기본 클래스와 상속 구조 (부모 → 자식) */
class SuperClass {                // 부모(상위) 클래스
    public void method1() { }     // 비어있는 메소드(형태만 정의)
}

class SubClass extends SuperClass {     // 자식(하위) 클래스, 부모를 상속받음

    @Override  // 부모의 method1를 "재정의(오버라이드)" 했다는 표시 (컴파일러가 확인해줌)
    public void method1() {
        // super.method1() : 부모가 원래 하던 행동을 그대로 먼저 실행
        // 이후 자식이 필요한 내용을 추가로 넣을 수도 있음(지금은 예시로 부모 것만 호출)
        super.method1();
    }

    @Deprecated // "이 메소드는 이제 권장하지 않아요" 라는 경고 스티커
    public void method2() { }
}

/* [2] 사용자 정의(커스텀) 어노테이션 만들기 */
// - @Retention(RUNTIME): 실행 중에도 어노테이션 정보가 살아있게 함(리플렉션으로 읽으려면 필수)
// - @Target(METHOD)   : 이 어노테이션은 "메소드에만" 붙일 수 있게 제한
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Annotation1 {
    // 어노테이션의 "필수 속성" (인터페이스의 추상 메소드처럼 보이지만, 값 규격을 정의하는 개념)
    String value1();
    // 사용 예: @Annotation1(value1="값")
}

/* [3] 커스텀 어노테이션 사용 예시 클래스 */
class TestClass {

    // 아래 어노테이션의 value1 속성에 실제 값을 "주입" (메소드에 이름표를 붙이는 느낌)
    @Annotation1(value1 = "어노테이션에 값 주입")
    public void method3() {
        // 어노테이션 값은 직접 변수처럼 쓰지 않고(= 스코프 밖)
        // "리플렉션"이라는 거울 도구로 꺼내서 읽습니다.
    }
}

/* [4] 실행 클래스: 리플렉션으로 어노테이션 읽기 */
public class Example01 {
    public static void main(String[] args) {

        // (1) 기본 어노테이션(@Override, @Deprecated) 동작 확인
        SubClass subClass = new SubClass();
        subClass.method1();  // 오버라이드된 메소드 호출 (부모의 method1도 함께 실행됨)
        subClass.method2();  // @Deprecated 경고가 뜨는 메소드 (학습용 호출)

        // (2) 리플렉션으로 어노테이션 값 읽기 (실행 중에 "이름표" 내용 확인하기)
        try {
            // 1) TestClass 안의 "public void method3()" 메소드 정보를 거울로 들여다봄
            Method method = TestClass.class.getMethod("method3");

            // 2) 그 메소드에 붙은 Annotation1 이름표(어노테이션) 객체를 꺼냄
            Annotation1 annotation = method.getAnnotation(Annotation1.class);

            // 3) 안전하게 널 체크 후 값 출력
            if (annotation != null) {
                // value1 속성에 넣었던 문자열을 출력
                System.out.println(annotation.value1());  // 출력: 어노테이션에 값 주입
            } else {
                System.out.println("Annotation1 어노테이션이 붙어 있지 않습니다.");
            }
        } catch (NoSuchMethodException e) {
            System.out.println("method3 메소드를 찾을 수 없습니다: " + e.getMessage());
        } catch (SecurityException e) {
            System.out.println("리플렉션 접근이 보안 정책에 의해 막혔습니다: " + e.getMessage());
        } catch (Exception e) {
            // 그 외 예외 방어
            System.out.println("예상치 못한 오류: " + e);
        }
    }
}
