-- =======================================================================================
-- ✅ schema.sql — DB 스키마 초기화 (서버 기동 시 실행)
-- rw 25-09-23 생성
-- 설명 : spring.sql.init.mode=always 인 경우, 서버 실행 시 DROP → CREATE 반복 수행
-- 개발 중엔 자동 실행(테이블 생성)
-- =======================================================================================


-- =======================================================================================
-- ✅ day03_02MVC2_MYBATIS 사용하는 테이블
-- rw 25-09-23 생성
-- =======================================================================================

DROP DATABASE IF EXISTS springweb2;
CREATE DATABASE springweb2;
USE springweb2;

CREATE TABLE `user` (
                        id   INT AUTO_INCREMENT,
                        name VARCHAR(100),
                        age  INT,
                        CONSTRAINT PRIMARY KEY (id)
);


-- =======================================================================================
-- ✅ day03_03과제04 사용하는 테이블
-- rw 25-09-23 생성
-- =======================================================================================

-- 같은 DB(springweb2)를 사용하지만, 별도 테이블(day03board) 생성
-- ※ 필요 없으면 주석 처리 가능
CREATE TABLE day03board (
                            bno INT AUTO_INCREMENT,
                            title VARCHAR(200),
                            content TEXT,
                            CONSTRAINT PRIMARY KEY (bno)
);

-- =======================================================================================
-- ✅ Day04 사용하는 테이블
-- rw 25-09-23 생성
-- fs) 테이블명은 실습 코드에 맞춰 student(단수)로 통일
-- =======================================================================================
DROP TABLE IF EXISTS student;

CREATE TABLE student (
                         sno  INT AUTO_INCREMENT,
                         name VARCHAR(100) NOT NULL,
                         kor  INT NOT NULL,
                         math INT NOT NULL,
                         CONSTRAINT pk_student PRIMARY KEY (sno)
);

-- =======================================================================================
-- ✅ DDL: taskboard (게시판)
-- rw 25-09-24 생성
-- 설명 : 과제05 전용 테이블, 가이드 요구 컬럼만 사용(bno/title/content)
-- =======================================================================================
DROP TABLE IF EXISTS taskboard;

CREATE TABLE taskboard (
                           bno     INT AUTO_INCREMENT,
                           title   VARCHAR(200) NOT NULL,
                           content TEXT         NOT NULL,
                           CONSTRAINT pk_taskboard PRIMARY KEY (bno)
);

-- =======================================================================================
-- ✅ Day05 schema.sql | rw 25-09-26 생성
-- - 테이블: student, student_score
-- - 애플리케이션 기동 시 spring.sql.init.mode=always 이면 자동 실행
-- =======================================================================================

-- ---------------------- day05 ---------------------------------- --
-- 학생 테이블
DROP TABLE if EXISTS student_score;
DROP TABLE if EXISTS student;

CREATE TABLE student (
                         sno INT AUTO_INCREMENT,              -- 학생 번호 (자동 증가)
                         name VARCHAR(50) NOT NULL,           -- 이름
                         kor INT NOT NULL,                    -- 국어 점수
                         math INT NOT NULL,                   -- 수학 점수
                         CONSTRAINT  PRIMARY KEY (sno)        -- 기본키 제약 조건 추가
);
-- 성적 기록 테이블 (트랜잭션 실습용)
CREATE TABLE student_score (
                               score_id INT AUTO_INCREMENT,         -- 성적 ID (자동 증가)
                               sno INT NOT NULL,                    -- 학생 번호 (외래키)
                               subject VARCHAR(20) NOT NULL,        -- 과목명 ('국어' 또는 '수학')
                               score INT NOT NULL,                  -- 점수
                               CONSTRAINT  PRIMARY KEY (score_id),  -- 기본키 제약 조건 추가
                               CONSTRAINT  FOREIGN KEY (sno) REFERENCES student(sno)  -- 외래키 제약 조건 추가
);
-- ---------------------- ------ ---------------------------------- --