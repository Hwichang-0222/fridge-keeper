-- 더미 사용자
INSERT INTO user (user_id, email, password, nickname) VALUES
(1, 'user1@fridge.com', 'pw', 'user1'),
(2, 'user2@fridge.com', 'pw', 'user2'),
(3, 'user3@fridge.com', 'pw', 'user3');

-- 더미 냉장고
INSERT INTO fridge (fridge_id, name) VALUES
(1, '테스트냉장고1'),
(2, '테스트냉장고2'),
(3, '테스트냉장고3');

-- 더미 카테고리(기본 외)
INSERT INTO category (category_id, name, color) VALUES
(7, '카테고리1', '#111111'),
(8, '카테고리2', '#222222'),
(9, '카테고리3', '#333333');

-- 더미 아이템
INSERT INTO item (item_id, fridge_id, category_id, name, barcode) VALUES
(1, 1, 7, '테스트아이템1', 'B-1'),
(2, 2, 8, '테스트아이템2', 'B-2'),
(3, 3, 9, '테스트아이템3', 'B-3');

-- 더미 냉장고-사용자-롤
INSERT INTO fridge_user_role (fridge_id, user_id, role) VALUES
(1, 1, 'OWNER'),
(2, 2, 'MEMBER'),
(3, 3, 'MEMBER');

-- 더미 사용자-카테고리 연결
INSERT INTO user_category (user_id, category_id, color, name) VALUES
(1, 7, '#123456', '테스트유저카테고리'),
(2, 8, '#654321', '업데이트유저카테고리'),
(3, 9, '#000000', '삭제유저카테고리');
