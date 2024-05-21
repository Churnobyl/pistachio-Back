CREATE TABLE role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- 소속 테이블 생성
CREATE TABLE membership
(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    agency_profile_url VARCHAR(255)
);

-- 기부 프로젝트 테이블 생성
CREATE TABLE donate_project
(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    agency_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    current_donation_amount BIGINT NOT NULL,
    target_donation_amount BIGINT NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    is_end BOOL NOT NULL DEFAULT FALSE,
    CONSTRAINT FK_agency_id FOREIGN KEY (agency_id)
    REFERENCES membership(id)
);

-- 유저 테이블 생성
CREATE TABLE user
(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    membership_id BIGINT,
    email VARCHAR(128) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(64) NOT NULL UNIQUE,
    pista BIGINT DEFAULT 0,
    user_profile VARCHAR(255),
    is_admin BOOL DEFAULT false,
    is_activate BOOL DEFAULT true,
    CONSTRAINT FK_user_membership_id FOREIGN KEY (membership_id)
    REFERENCES membership(id)
);

CREATE TABLE user_role (
    user_id BIGINT,
    role_id BIGINT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (role_id) REFERENCES role(id)
);

-- 팔로우 테이블 생성
CREATE TABLE follow
(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    following_id BIGINT NOT NULL,
    followed_id BIGINT NOT NULL,
    CONSTRAINT FK_following_id FOREIGN KEY(following_id)
    REFERENCES user(id) ON DELETE CASCADE,
    CONSTRAINT FK_followed_id FOREIGN KEY(followed_id)
    REFERENCES user(id) ON DELETE CASCADE
);

-- 기부 테이블 생성
CREATE TABLE donation
(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    project_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    amount BIGINT NOT NULL,
    is_boast BOOL NOT NULL DEFAULT false,
    CONSTRAINT FK_project_id FOREIGN KEY(project_id)
    REFERENCES donate_project(id),
    CONSTRAINT FK_user_id FOREIGN KEY(user_id)
    REFERENCES user(id)
);

-- 소속된 프로젝트 테이블 생성
CREATE TABLE affiliated
(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    donated_user_id BIGINT NOT NULL,
    project_id BIGINT NOT NULL,
    CONSTRAINT FK_donated_user_id FOREIGN KEY(donated_user_id)
    REFERENCES user(id),
    CONSTRAINT FK_affiliated_project_id FOREIGN KEY(project_id)
    REFERENCES donate_project(id)
);

/* Second Sprint

-- 위치 정보 테이블 생성
CREATE TABLE location
(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    latitude FLOAT NOT NULL,
    longitude FLOAT NOT NULL
);

-- 위치 정보 테이블 조회
DESC location;


*/

-- 피드 테이블 생성
CREATE TABLE feed
(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    -- location_id BIGINT NOT NULL,
    project_id BIGINT NOT NULL,
    content TEXT,
    like_cnt INTEGER DEFAULT 0,
    is_boast BOOLEAN NOT NULL DEFAULT false,
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT FK_feed_user_id FOREIGN KEY(user_id)
    REFERENCES user(id),
--     CONSTRAINT FK_location_id FOREIGN KEY(location_id)
--     REFERENCES location(id),
    CONSTRAINT FK_feed_project_id FOREIGN KEY(project_id)
    REFERENCES donate_project(id)
);

-- 피드 사진 테이블 생성
CREATE TABLE feed_picture
(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    feed_id BIGINT NOT NULL,
    url VARCHAR(255) NOT NULL,
    CONSTRAINT FK_feed_picture_feed_id FOREIGN KEY(feed_id)
    REFERENCES feed(id)
);

/* Second Sprint

-- 해시태그 테이블 생성
CREATE TABLE hashtag
(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- 해시태그 테이블 조회
DESC hashtag;


-- 피드 해시태그 테이블 생성
CREATE TABLE feed_hashtag
(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    feed_id BIGINT NOT NULL,
    hashtag_id BIGINT NOT NULL,
    CONSTRAINT FK_feed_hashtag_feed_id FOREIGN KEY(feed_id)
    REFERENCES feed(id),
    CONSTRAINT FK_feed_hashtag_hashtag_id FOREIGN KEY(hashtag_id)
    REFERENCES hashtag(id)
);

-- 피드 해시태그 테이블 조회
DESC feed_hashtag;

*/


-- 피드 댓글 테이블 생성
CREATE TABLE feed_comment
(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    feed_id BIGINT NOT NULL,
    comment_user_no BIGINT NOT NULL,
    content TEXT NOT NULL,
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT FK_feed_comment_feed_id FOREIGN KEY(feed_id)
    REFERENCES feed(id),
    CONSTRAINT FK_feed_comment_comment_user_no FOREIGN KEY(comment_user_no)
    REFERENCES user(id)
);

-- 피드 좋아요 테이블 생성
CREATE TABLE feed_like
(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    feed_id BIGINT NOT NULL,
    like_user_no BIGINT NOT NULL,
    CONSTRAINT FK_feed_like_feed_id FOREIGN KEY(feed_id)
    REFERENCES feed(id),
    CONSTRAINT FK_feed_like_like_user_no FOREIGN KEY(like_user_no)
    REFERENCES user(id)
);
