-- 테스트/개발용 더미 사용자
INSERT INTO user (email, password, nickname) VALUES
('user1@fridge.com', 'pw', 'user1'),
('user2@fridge.com', 'pw', 'user2'),
('user3@fridge.com', 'pw', 'user3');

-- 테스트/개발용 더미 냉장고
INSERT INTO fridge (name) VALUES
('테스트냉장고1'),
('테스트냉장고2'),
('테스트냉장고3');

-- 테스트/개발용 더미 카테고리(기본 외)
INSERT INTO category (name, color) VALUES
('카테고리1', '#111111'),
('카테고리2', '#222222'),
('카테고리3', '#333333');

-- 테스트/개발용 더미 아이템
INSERT INTO item (fridge_id, category_id, name, barcode) VALUES
(1, 1, '테스트아이템1', 'B-1'),
(2, 2, '테스트아이템2', 'B-2'),
(3, 3, '테스트아이템3', 'B-3');

-- 테스트/개발용 더미 냉장고-사용자-롤
INSERT INTO fridge_user_role (fridge_id, user_id, role) VALUES
(1, 1, 'OWNER'),
(2, 2, 'MEMBER'),
(3, 3, 'MEMBER');

-- 테스트/개발용 더미 사용자-카테고리 연결
INSERT INTO user_category (user_id, category_id, color, name) VALUES
(1, 1, '#123456', '테스트유저카테고리'),
(2, 2, '#654321', '업데이트유저카테고리'),
(3, 3, '#000000', '삭제유저카테고리');
