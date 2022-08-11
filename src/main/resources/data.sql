INSERT INTO keyboard_vendor(product_type, vendor_type)
VALUES ('KEYBOARD', 'LOGITECH'), ('KEYBOARD', 'ABKO'), ('KEYBOARD', 'COX'), ('KEYBOARD', 'LEOPLOD'), ('KEYBOARD', 'HAPPYHACKING'), ('KEYBOARD', 'REALFORCE');

INSERT INTO keyboard_layout(layout_type)
VALUES ('FULL'), ('TEN_KEY_LESS'), ('SEVENTY_FIVE_PERCENT'), ('SIXTY_FIVE_PERCENT');

INSERT INTO keyboard_connection(product_type, connection_type)
VALUES ('KEYBOARD', 'CABLE'), ('KEYBOARD', 'WIRELESS'), ('KEYBOARD', 'BLUETOOTH');

INSERT INTO switch(switch_type)
VALUES ('BLUE'), ('BROWN'), ('RED'), ('BLACK'), ('SILENCE_RED');

INSERT INTO address(full_address, city, street, zip_code)
VALUES ('서울특별시 서초구, 양재동 137-130', '서울특별시', '서초구, 양재동', '137-130');

INSERT INTO member(name, email, address_id)
VALUES ('name', 'mock@mock.com', 1);

INSERT INTO discount_policy(title, rate)
VALUES ('이벤트 할인', 0.0225), ('신제품 할인', 0.10);

INSERT INTO product(DTYPE, CONTENT,	PRICE, QUANTITY, RENTABLE, RENTAL_PRICE, RENTAL_PRODUCT_COUNT, TITLE, VIEW_COUNT, connection_id, vendor_id)
VALUES ('Keyboard', '축교환 완전방수 게이밍 카일 광축 블랙', 53500, 10, true, 5000, 5, 'HACKER K660', 1, 1, 1),
       ('Keyboard', '(정품)', 146900, 10, true, 5000, 5, 'MX_KEYS', 1, 1, 1),
       ('Keyboard', '무선 키보드 (정품) (적축)', 99000, 10, true, 5000, 5, 'SIGNATURE K855', 1, 1, 1),
       ('Keyboard', '(정품)', 97900, 10, false, 0, 0, 'MK850', 1, 1, 1),
       ('Keyboard', '(정품)', 62950, 10, true, 5000, 5, 'MK545', 1, 1, 1),
       ('Keyboard', 'OfficeMaster 한글', 159000, 10, true, 5000, 5, 'GK898B', 1, 1, 1),
       ('Keyboard', '그라파이트 화이트 한글', 166400, 10, true, 5000, 5, 'FC900RBT PD', 1, 1, 1),
       ('Keyboard', '화이트투톤 한글 ', 166970, 10, true, 5000, 5, 'NP900RBT PD', 1, 1, 1),
       ('Keyboard', '이색사출 PBT 체리 키보드 미드나잇그린', 75000, 10, true, 5000, 5, 'HACKER K660M', 1, 1, 1),
       ('Keyboard', '(정품)', 249000, 10, true, 5000, 5, 'G913 TKL', 1, 1, 1),
       ('Keyboard', '화이트 블루스타 한글', 164320, 10, true, 5000, 5, 'FC750RBT PD', 1, 1, 1);

INSERT INTO keyboard(id, layout_id)
VALUES (1, 1), (2, 2), (3, 3), (4, 2), (5, 1), (6, 1), (7, 1), (8, 3), (9, 1), (10, 1), (11, 1);

INSERT INTO product_discount_policy(product_id, discount_policy_id)
VALUES (1, 1), (1, 2), (3, 1), (5, 1), (6, 2), (7, 1), (8, 2), (9, 1), (10, 2);

INSERT INTO image(image_url)
VALUES ('https://cdn.pixabay.com/photo/2017/03/24/03/20/keyboard-2170063_1280.png'),
       ('https://cdn.pixabay.com/photo/2020/04/08/16/32/keyboard-5017973_960_720.jpg'),
       ('https://cdn.pixabay.com/photo/2016/11/28/01/34/laptop-1864126_960_720.jpg'),
       ('https://www.leopold.co.kr/Editor/2203/3547032030_1646900091.34.jpg'),
       ('https://www.leopold.co.kr/Editor/2202/3547032030_1644298182.74.jpg'),
       ('https://www.leopold.co.kr/Editor/2202/3547032030_1644303608.81.jpg');

INSERT INTO product_image(product_id, image_id)
VALUES (1, 1), (1, 2), (1, 3), (2, 2), (2, 1), (2, 3), (3, 1), (4, 1), (5, 1), (5, 2), (6, 1), (7, 1), (8, 1), (9, 1), (10, 1), (11, 1);

INSERT INTO product_info_image(product_id, image_id)
VALUES (1, 4), (2, 5), (3, 6);

INSERT INTO product_like(product_id, member_id)
VALUES (1, 1);

INSERT INTO keyboard_switch(keyboard_id, switch_id)
VALUES (1, 1), (1, 2), (1, 3), (2, 2), (2, 4), (2, 5), (3, 1), (4, 1), (5, 1), (6, 1), (7, 1), (8, 1), (9, 1), (10, 1);
