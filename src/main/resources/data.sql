-- =======================================================================================
-- ✅ data.sql — DB 초기 데이터 삽입 (서버 기동 시 실행)
-- rw 25-09-23 생성
-- 설명 : spring.sql.init.mode=always 인 경우, schema.sql 실행 후 자동 실행됨
-- 개발 중엔 자동 실행(샘플 데이터)
-- =======================================================================================


-- =======================================================================================
-- ✅ day03_02MVC2_MYBATIS 사용하는 초기 데이터
-- rw 25-09-23 생성
-- =======================================================================================

INSERT INTO `user` (name, age) VALUES ('유재석', 40);
INSERT INTO `user` (name, age) VALUES ('이수근', 44);
INSERT INTO `user` (name, age) VALUES ('강호동', 50);


-- =======================================================================================
-- ✅ day03_03과제04 사용하는 초기 데이터
-- rw 25-09-23 생성
-- =======================================================================================

INSERT INTO day03board (title, content) VALUES ('첫 번째 글', '게시판 연습 첫 번째 내용입니다.');
INSERT INTO day03board (title, content) VALUES ('두 번째 글', '게시판 연습 두 번째 내용입니다.');
INSERT INTO day03board (title, content) VALUES ('세 번째 글', '게시판 연습 세 번째 내용입니다.');

-- =======================================================================================
-- ✅ day04 사용하는 초기 데이터
-- rw 25-09-24 생성
-- fe) 프론트 day04.html 열고 바로 목록이 보이게끔
-- =======================================================================================
INSERT INTO student(name, kor, math) VALUES ('유재석', 95, 100);
INSERT INTO student(name, kor, math) VALUES ('김종국', 88, 90);
INSERT INTO student(name, kor, math) VALUES ('송지효', 77, 85);

-- =======================================================================================
-- ✅ 과제05 (게시판) 사용하는 초기 데이터 — 테이블: taskboard
-- rw 25-09-24 생성
-- fe) created_at/updated_at 은 DEFAULT 로 자동 입력/갱신
-- =======================================================================================
INSERT INTO taskboard (title, content) VALUES ('공지: 실습 안내', '오늘은 게시판 CRUD를 완성합니다.');
INSERT INTO taskboard (title, content) VALUES ('첫 글', 'Hello Board!');
INSERT INTO taskboard (title, content) VALUES ('둘째 글', 'axios로 연동 성공!');

-- =======================================================================================
-- ✅ Day05 data.sql | rw 25-09-26 생성
-- - 내용: student / student_score 샘플 데이터 삽입
-- - 비고: 이름/점수는 임의 데이터
-- =======================================================================================

-- ---------------------- day05 ---------------------------------- --
-- 학생 테이블 샘플 데이터 삽입
-- INSERT INTO student (name, kor, math) VALUES ('홍길동', 90, 85);
-- INSERT INTO student (name, kor, math) VALUES ('임꺽정', 78, 92);
-- INSERT INTO student (name, kor, math) VALUES ('장보고', 88, 76);
INSERT
INTO student (name, kor, math)
VALUES ('김하늘', 92, 81) ,
       ('박서준', 78, 95) ,
       ('이지은', 88, 76) ;

-- 성적 테이블 샘플 데이터 삽입 (트랜잭션 실습을 위해 일부 데이터만 추가)
INSERT INTO student_score (sno, subject, score) VALUES (1, '국어', 92);
INSERT INTO student_score (sno, subject, score) VALUES (1, '수학', 81);
INSERT INTO student_score (sno, subject, score) VALUES (2, '국어', 78);
INSERT INTO student_score (sno, subject, score) VALUES (2, '수학', 95);
-- ---------------------- ------ ---------------------------------- --