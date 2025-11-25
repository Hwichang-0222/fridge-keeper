-- 사용자 테이블
CREATE TABLE user (
    user_id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    email        VARCHAR(100) NOT NULL UNIQUE,
    password     VARCHAR(255) NOT NULL,
    nickname     VARCHAR(50),
    created_at   DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 냉장고 테이블
CREATE TABLE fridge (
    fridge_id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(100) NOT NULL,
    created_at   DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 냉장고-사용자-롤(M:N 관계) 테이블
CREATE TABLE fridge_user_role (
    fridge_id    BIGINT NOT NULL,
    user_id      BIGINT NOT NULL,
    role         ENUM('OWNER', 'MEMBER') NOT NULL,
    joined_at    DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (fridge_id, user_id),
    FOREIGN KEY (fridge_id) REFERENCES fridge(fridge_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
);

-- 카테고리 테이블 (기본/커스텀)
CREATE TABLE category (
    category_id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(50) NOT NULL,
    color        VARCHAR(20),
    created_by_user_id BIGINT,
    UNIQUE KEY uk_category_name_user (name, created_by_user_id),
    FOREIGN KEY (created_by_user_id) REFERENCES user(user_id) ON DELETE SET NULL
);

-- 아이템 테이블
CREATE TABLE item (
    item_id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    fridge_id    BIGINT NOT NULL,
    category_id  BIGINT NULL,
    name         VARCHAR(100) NOT NULL,
    expiration_date DATE,
    memo         VARCHAR(255),
    created_at   DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fridge_id) REFERENCES fridge(fridge_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category(category_id) ON DELETE SET NULL
);

-- 사용자-카테고리 연결 테이블 (대규모 사용자 환경)
CREATE TABLE user_category (
    user_id      BIGINT NOT NULL,
    category_id  BIGINT NOT NULL,
    color        VARCHAR(20), -- 사용자별 커스텀 색상
    name         VARCHAR(50), -- 사용자별 커스텀 이름(필요시)
    PRIMARY KEY (user_id, category_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category(category_id) ON DELETE CASCADE
);
