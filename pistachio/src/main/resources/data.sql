-- 소속 테이블에 기본값 추가 - 1 : 일반
INSERT INTO membership(name) VALUES ("일반");

-- 기부 프로젝트 테이블에 기본값 추가 - 1 : 일반
INSERT INTO donate_project(agency_id, name, description, current_donation_amount, target_donation_amount, start_time, end_time) VALUES (1, "일반 게시글", "일반 게시글", 0, 0, date('1970-1-1'), date('1970-1-1'));

-- role 테이블
INSERT INTO role(name) VALUES ('USER');
INSERT INTO role(name) VALUES ('PISTACHIO');
INSERT INTO role(name) VALUES ('AGENCY');
INSERT INTO role(name) VALUES ('ADMIN');