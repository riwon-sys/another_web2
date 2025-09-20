// ========================================================================
// ✅ Repository (DAO, Data Access Object)
// - DB에 직접 접근하는 계층
// - MyBatis 또는 JPA 같은 라이브러리와 연결될 수 있음
// - 지금은 껍데기 인터페이스지만, 나중에 SQL 실행 메서드를 넣음
// ========================================================================
package example.day03._MVC2_3TIRE._model._respository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

// @Repository : JPA 기반 Repository (실무에서 사용)
// @Mapper     : MyBatis 기반 Mapper (실무에서 사용)
@Component // 단순 JDBC DAO라면 이렇게 스프링 빈 등록 가능
public interface MvcRepository {
}