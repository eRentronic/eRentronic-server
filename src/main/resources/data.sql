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

INSERT INTO product(DTYPE, CONTENT,	PRICE, QUANTITY, RENTABLE, RENTAL_PRICE, RENTAL_PRODUCT_QUANTITY, TITLE, VIEW_COUNT, connection_id, vendor_id)
VALUES ('Keyboard', '축교환 완전방수 게이밍 카일 광축 블랙', 53500, 10, true, 5000, 5, 'HACKER K660', 1, 1, 1),
       ('Keyboard', '(정품) 컨텐츠 상세 긴 목 데이터 A occaecati tempora beatae optio nihil. Est sapiente doloribus assumenda eaque ipsa autem. Est facilis quibusdam consectetur occaecati occaecati. Nam in adipisci fuga eum debitis.', 146900, 10, true, 5000, 5, 'MX_KEYS', 1, 1, 1),
       ('Keyboard', '무선 키보드 (정품) (적축)', 99000, 10, true, 5000, 5, 'SIGNATURE K855', 1, 1, 1),
       ('Keyboard', '(정품)', 97900, 10, false, 0, 0, 'MK850', 1, 1, 1),
       ('Keyboard', '(정품) 목 데이터 긴 거 A occaecati tempora beatae optio nihil. Est sapiente doloribus assumenda eaque ipsa autem. Est facilis quibusdam consectetur occaecati occaecati. Nam in adipisci fuga eum debitis.', 62950, 10, true, 5000, 5, 'MK545', 1, 1, 1),
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

INSERT INTO image(id, image_url)
VALUES (1, 'https://cdn.pixabay.com/photo/2017/03/24/03/20/keyboard-2170063_1280.png'),
       (2, 'https://cdn.pixabay.com/photo/2020/04/08/16/32/keyboard-5017973_960_720.jpg'),
       (3, 'https://cdn.pixabay.com/photo/2016/11/28/01/34/laptop-1864126_960_720.jpg'),
       (4, 'https://cdn.pixabay.com/photo/2013/07/13/14/06/keyboard-162134_960_720.png'),
       (5, 'https://cdn.pixabay.com/photo/2015/09/09/18/30/keyboard-932370_960_720.jpg'),
       (6, 'https://cdn.pixabay.com/photo/2017/03/30/05/17/letters-2187262_960_720.jpg'),
       (7, 'https://cdn.pixabay.com/photo/2015/10/01/00/06/keyboard-966269_960_720.png'),
       (8, 'https://cdn.pixabay.com/photo/2017/01/16/07/22/keyboard-1983410_960_720.jpg'),
       (9, 'https://cdn.pixabay.com/photo/2015/08/13/01/00/keyboard-886462_960_720.jpg'),
       (10, 'https://cdn.pixabay.com/photo/2016/08/29/16/08/keyboard-1628579_960_720.jpg'),
       (11, 'https://cdn.pixabay.com/photo/2022/02/01/04/46/keyboard-6985517_960_720.jpg'),
       (12, 'https://cdn.pixabay.com/photo/2015/09/09/22/03/keyboard-933680_960_720.jpg'),
       (13, 'https://cdn.pixabay.com/photo/2018/08/19/15/58/mac-3616959_960_720.jpg'),
       (14, 'https://cdn.pixabay.com/photo/2013/08/25/11/41/keyboard-175614_960_720.jpg'),
       (15, 'https://cdn.pixabay.com/photo/2015/10/17/17/38/keyboard-992915_960_720.png'),
       (16, 'https://cdn.pixabay.com/photo/2020/01/12/08/12/keyboard-4759502_960_720.jpg'),
       (17, 'https://cdn.pixabay.com/photo/2012/02/21/07/48/cress-15086_960_720.jpg'),
       (18, 'https://cdn.pixabay.com/photo/2019/07/28/14/22/love-4368785_960_720.jpg'),
       (19, 'https://cdn.pixabay.com/photo/2015/09/09/19/38/keyboard-932830_960_720.jpg'),
       (20, 'https://cdn.pixabay.com/photo/2020/08/13/13/37/computer-5485237_960_720.jpg'),
       (21, 'https://cdn.pixabay.com/photo/2019/01/04/11/51/keyboard-3913007_960_720.jpg'),
       (22, 'https://cdn.pixabay.com/photo/2017/08/03/08/43/mouse-2575068_960_720.jpg'),
       (23, 'https://cdn.pixabay.com/photo/2020/01/12/08/12/keyboard-4759501_960_720.jpg'),
       (24, 'https://cdn.pixabay.com/photo/2022/01/30/17/15/keyboard-6981763_960_720.jpg'),
--        여분
       (25, 'https://cdn.pixabay.com/photo/2022/06/11/14/22/keyboard-7256457_960_720.png'),
--        상세이미지
       (26, 'https://www.leopold.co.kr/Editor/2203/3547032030_1646900091.34.jpg'),
       (27, 'https://www.leopold.co.kr/Editor/2202/3547032030_1644298182.74.jpg'),
       (28, 'https://www.leopold.co.kr/Editor/2202/3547032030_1644303608.81.jpg'),
       (29, 'http://www.imagelink.co.kr/files/attach/images/149/553/011/21db29b5dec0f20bb26ac6cae9aa8514.jpg'),
       (30, 'http://www.imagelink.co.kr/files/attach/images/149/739/009/18b21f9af7c2487d4a4cd78fbb488c0a.jpg'),
       (31, 'http://sweety009.cafe24.com/files/attach/images/120/958/008/f3f619146d53349dd2be6f3563c07ab8.jpg'),
       (32, 'http://sweety009.cafe24.com/files/attach/images/116/421/014/26c4acf2a11cd4a36b9e0371a8fc5fe3.jpg'),
       (33, 'http://ai.esmplus.com/crecrew/170928_%E1%84%89%E1%85%A1%E1%86%BC%E1%84%89%E1%85%A6%E1%84%91%E1%85%A6%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%B5_PETRA%E1%84%80%E1%85%A6%E1%84%8B%E1%85%B5%E1%84%86%E1%85%B5%E1%86%BC%E1%84%8F%E1%85%B5%E1%84%87%E1%85%A9%E1%84%83%E1%85%B3%E1%84%86%E1%85%A1%E1%84%8B%E1%85%AE%E1%84%89%E1%85%B3%E1%84%89%E1%85%A6%E1%84%90%E1%85%B3.jpg'),
       (34, 'http://sweety009.cafe24.com/files/attach/images/116/968/008/db400a54f03c6486bd62bb53062e81c7.jpg'),
       (35, 'https://cdn006.negagea.net/timco/%ec%a0%9c%eb%8b%89%ec%8a%a4/%ec%83%81%ec%84%b8%ed%8e%98%ec%9d%b4%ec%a7%80/STORMX_TITAN_SE_%ec%83%81%ec%84%b8%ec%9d%b4%eb%af%b8%ec%a7%80.jpg'),
       (36, 'http://ai.esmplus.com/crecrew/190225_%E1%84%89%E1%85%A1%E1%86%BC%E1%84%89%E1%85%A6%E1%84%91%E1%85%A6%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%B5_COX%20CK30_01.jpg'),
--        여분
       (37, 'https://cdn.funshop.co.kr/products/0000102949/637765447577158486.jpg'),
       (38, 'http://img.danawa.com/prod_img/500000/086/768/desc/prod_12768086/add_1/20210108141756332_72FTYPB0.jpg'),
       (39, 'http://img.danawa.com/prod_img/500000/114/471/desc/prod_14471114/add_1/20210614171925125_IT4W3AKF.jpg');

INSERT INTO product_image(product_id, image_id)
VALUES (1, 1), (1, 2), (1, 3), (2, 4), (2, 5), (2, 6), (2, 7), (3, 8), (3, 9), (4, 10), (4, 11), (4, 12), (5, 13), (5, 14),
       (6, 15), (6, 16), (6, 17), (7, 18), (8, 19), (9, 20), (10, 21), (11, 22), (11, 23), (11, 24);

INSERT INTO product_info_image(product_id, image_id)
VALUES (1, 26), (2, 27), (3, 28), (4, 29), (5, 30), (6, 31), (7, 32), (8, 33), (9, 34), (10, 35), (11, 36);

INSERT INTO product_like(product_id, member_id)
VALUES (1, 1);

INSERT INTO keyboard_switch(keyboard_id, switch_id)
VALUES (1, 1), (1, 2), (1, 3), (2, 2), (2, 4), (2, 5), (3, 1), (4, 1), (5, 1), (6, 1), (7, 1), (8, 1), (9, 1), (10, 1), (11, 1);
