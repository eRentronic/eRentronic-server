INSERT INTO keyboard_vendor(product_type, vendor_type)
VALUES ('KEYBOARD', 'LOGITECH'),
       ('KEYBOARD', 'ABKO'),
       ('KEYBOARD', 'COX'),
       ('KEYBOARD', 'LEOPLOD'),
       ('KEYBOARD', 'HAPPYHACKING'),
       ('KEYBOARD', 'REALFORCE'),
       ('KEYBOARD', 'MSI'),
       ('KEYBOARD', 'XENICS');

INSERT INTO keyboard_layout(layout_type)
VALUES ('FULL'),
       ('TEN_KEY_LESS'),
       ('SEVENTY_FIVE_PERCENT'),
       ('SIXTY_FIVE_PERCENT');

INSERT INTO keyboard_connection(product_type, connection_type)
VALUES ('KEYBOARD', 'CABLE'),
       ('KEYBOARD', 'WIRELESS'),
       ('KEYBOARD', 'BLUETOOTH');

INSERT INTO switch(switch_type)
VALUES ('BLUE'),
       ('BROWN'),
       ('RED'),
       ('BLACK'),
       ('SILENCE_RED');

INSERT INTO member(name, email, full_address, address1, address2, zip_code)
VALUES ('name', 'mock@mock.com', '서울 송파구 송파대로 567 101동 101호', '서울 송파구 송파대로 567', '101동 101호',
        '05503');

INSERT INTO discount_policy(title, rate)
VALUES ('이벤트 할인', 0.0225),
       ('신제품 할인', 0.10);

INSERT INTO product(DTYPE, CONTENT, PRICE, QUANTITY, RENTABLE, RENTAL_PRICE,
                    RENTAL_PRODUCT_QUANTITY, TITLE, VIEW_COUNT, connection_id, vendor_id)
VALUES ('Keyboard', '축교환 완전방수 게이밍 카일 광축 블랙', 53500, 10, true, 5000, 5, 'HACKER K660', 1, 1, 2),
       ('Keyboard',
        '(정품) 컨텐츠 상세 긴 목 데이터 A occaecati tempora beatae optio nihil. Est sapiente doloribus assumenda eaque ipsa autem. Est facilis quibusdam consectetur occaecati occaecati. Nam in adipisci fuga eum debitis.',
        146900, 10, true, 5000, 5, 'MX_KEYS', 1, 1, 1),
       ('Keyboard', '무선 키보드 (정품) (적축)', 99000, 10, true, 5000, 5, 'SIGNATURE K855', 1, 1, 1),
       ('Keyboard', '(정품)', 97900, 10, false, 0, 0, 'MK850', 1, 1, 1),
       ('Keyboard',
        '(정품) 목 데이터 긴 거 A occaecati tempora beatae optio nihil. Est sapiente doloribus assumenda eaque ipsa autem. Est facilis quibusdam consectetur occaecati occaecati. Nam in adipisci fuga eum debitis.',
        62950, 10, true, 5000, 5, 'MK545', 1, 1, 1),
       ('Keyboard', 'OfficeMaster 한글', 159000, 10, true, 5000, 5, 'GK898B', 1, 1, 1),
       ('Keyboard', '그라파이트 화이트 한글', 166400, 10, true, 5000, 5, 'FC900RBT PD', 1, 1, 4),
       ('Keyboard', '화이트투톤 한글 ', 166970, 10, true, 5000, 5, 'NP900RBT PD', 1, 1, 1),
       ('Keyboard', '이색사출 PBT 체리 키보드 미드나잇그린', 75000, 10, true, 5000, 5, 'HACKER K660M', 1, 1, 1),
       ('Keyboard', '(정품)', 249000, 10, true, 5000, 5, 'G913 TKL', 1, 1, 1),
       ('Keyboard', '화이트 블루스타 한글', 164320, 10, true, 5000, 5, 'FC750RBT PD', 1, 1, 4),
       ('Keyboard', '축교환 게이밍 기계식 블랙 (청축)', 33500, 10, true, 4000, 5, 'HACKER K640', 1, 1, 2),  --12
       ('Keyboard', '축교환 완전방수 게이밍 카일 광축 블랙 (클릭)', 53500, 10, true, 5000, 5, 'HACKER K660', 1, 1,
        2),                                                                                    --13
       ('Keyboard', '코랄블루 영문 (저소음 적축)', 189000, 10, true, 10000, 5, 'FC750RBT PD', 1, 1, 4),   --14
       ('Keyboard', '그라파이트 화이트 한글 (저소음 적축)', 169150, 10, true, 3000, 5, 'FC900RBT PD', 1, 1,
        4),                                                                                    --15
       ('Keyboard', '화이트민트 한글 (저소음적축)', 174990, 10, true, 6000, 5, 'FC900RBT PD', 1, 1, 4),    --16
       ('Keyboard', '이색사출 그레이 블루 한글 (저소음 적축)', 141040, 10, true, 7500, 5, 'FC900R PD', 1, 1,
        4),                                                                                    --17
       ('Keyboard', '(청축)', 56900, 10, true, 4000, 5, 'STORMX K5', 1, 1, 4),                   --18
       ('Keyboard', '차콜 블루 한글 (저소음 적축)', 169620, 10, true, 4000, 5, 'FC900RBT PD', 1, 1, 4),   --19
       ('Keyboard', '기계식 키보드 (청축)', 75800, 10, true, 6000, 5, 'STORMX TITAN MARK V', 1, 1, 4), --20
       ('Keyboard', '코랄블루 영문 (저소음 적축)', 187490, 10, true, 3500, 5, 'FC750RBT PD', 1, 1, 4),    --21
       ('Keyboard', '(화이트, 청축)', 105000, 10, true, 4500, 5, 'SCORPIUS M10TSFL', 1, 1, 4),      --22
       ('Keyboard', '스카이문 LED 완전방수 무접점 (45g)', 190000, 10, true, 4500, 5, 'HACKER K990 V3', 1, 1,
        2),                                                                                    --23
       ('Keyboard', '(청축)', 132000, 10, true, 6000, 5, 'TESORO M9 SPECTRUM', 1, 1, 4),         --24
       ('Keyboard', '무선 블루투스 기계식 미니 키보드 (블랙)', 165000, 10, true, 8000, 5, 'K660M-L68', 1, 1,
        2),                                                                                    --25
       ('Keyboard', '그레이퍼플 한글 (청축)', 164900, 10, true, 10000, 5, 'FC900RBT PD', 1, 1, 4),      --26
       ('Keyboard', '무접점 키보드 (그레이, 30g)', 199000, 10, true, 4500, 5, 'KN10C PBT', 1, 1, 2),    --27
       ('Keyboard', '(화이트)', 27850, 10, true, 3000, 5, 'STORMX TITAN SE', 1, 1, 8),            --28
       ('Keyboard', '게이밍 기계식 키보드 (갈축)', 38990, 10, true, 5000, 5, '스톰체이서LT', 1, 1, 8),         --29
       ('Keyboard', 'LOW PROFILE', 99000, 10, true, 2000, 5, 'GK50', 1, 1, 7);
--30
--        ('Keyboard', '', , 10, true, , 5, '', 1, 1, 1), --31
--        ('Keyboard', '', , 10, true, , 5, '', 1, 1, 1), --32
--        ('Keyboard', '', , 10, true, , 5, '', 1, 1, 1), --33
--        ('Keyboard', '', , 10, true, , 5, '', 1, 1, 1); --34

INSERT INTO keyboard(id, layout_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 2),
       (5, 1),
       (6, 1),
       (7, 1),
       (8, 3),
       (9, 1),
       (10, 1),
       (11, 1),
       (12, 1),
       (13, 1),
       (14, 1),
       (15, 1),
       (16, 1),
       (17, 1),
       (18, 1),
       (19, 1),
       (20, 1),
       (21, 1),
       (22, 1),
       (23, 1),
       (24, 1),
       (25, 1),
       (26, 1),
       (27, 1),
       (28, 1),
       (29, 1),
       (30, 1);


INSERT INTO product_discount_policy(product_id, discount_policy_id)
VALUES (1, 1),
       (1, 2),
       (3, 1),
       (5, 1),
       (6, 2),
       (7, 1),
       (8, 2),
       (9, 1),
       (10, 2);

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
       (25, 'spare'),

--        상세이미지
       (26, 'https://www.leopold.co.kr/Editor/2203/3547032030_1646900091.34.jpg'),
       (27, 'https://www.leopold.co.kr/Editor/2202/3547032030_1644298182.74.jpg'),
       (28, 'https://www.leopold.co.kr/Editor/2202/3547032030_1644303608.81.jpg'),
       (29,
        'http://www.imagelink.co.kr/files/attach/images/149/553/011/21db29b5dec0f20bb26ac6cae9aa8514.jpg'),
       (30,
        'http://www.imagelink.co.kr/files/attach/images/149/739/009/18b21f9af7c2487d4a4cd78fbb488c0a.jpg'),
       (31,
        'http://sweety009.cafe24.com/files/attach/images/120/958/008/f3f619146d53349dd2be6f3563c07ab8.jpg'),
       (32,
        'http://sweety009.cafe24.com/files/attach/images/116/421/014/26c4acf2a11cd4a36b9e0371a8fc5fe3.jpg'),
       (33,
        'http://ai.esmplus.com/crecrew/170928_%E1%84%89%E1%85%A1%E1%86%BC%E1%84%89%E1%85%A6%E1%84%91%E1%85%A6%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%B5_PETRA%E1%84%80%E1%85%A6%E1%84%8B%E1%85%B5%E1%84%86%E1%85%B5%E1%86%BC%E1%84%8F%E1%85%B5%E1%84%87%E1%85%A9%E1%84%83%E1%85%B3%E1%84%86%E1%85%A1%E1%84%8B%E1%85%AE%E1%84%89%E1%85%B3%E1%84%89%E1%85%A6%E1%84%90%E1%85%B3.jpg'),
       (34,
        'http://sweety009.cafe24.com/files/attach/images/116/968/008/db400a54f03c6486bd62bb53062e81c7.jpg'),
       (35,
        'https://cdn006.negagea.net/timco/%ec%a0%9c%eb%8b%89%ec%8a%a4/%ec%83%81%ec%84%b8%ed%8e%98%ec%9d%b4%ec%a7%80/STORMX_TITAN_SE_%ec%83%81%ec%84%b8%ec%9d%b4%eb%af%b8%ec%a7%80.jpg'),
       (36,
        'http://ai.esmplus.com/crecrew/190225_%E1%84%89%E1%85%A1%E1%86%BC%E1%84%89%E1%85%A6%E1%84%91%E1%85%A6%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%B5_COX%20CK30_01.jpg'),

--        여분
       (37, 'https://cdn.funshop.co.kr/products/0000102949/637765447577158486.jpg'),
       (38,
        'http://img.danawa.com/prod_img/500000/086/768/desc/prod_12768086/add_1/20210108141756332_72FTYPB0.jpg'),
       (39,
        'http://img.danawa.com/prod_img/500000/114/471/desc/prod_14471114/add_1/20210614171925125_IT4W3AKF.jpg'),

--        2022-08-25 추가
       (40,
        'https://img.danawa.com/prod_img/500000/084/709/img/4709084_1.jpg?shrink=500:500&_v=20211228101305'),
       (41,
        'https://img.danawa.com/prod_img/500000/084/709/img/4709084_2.jpg?shrink=500:500&_v=20211228101305'),
       (42,
        'https://img.danawa.com/prod_img/500000/084/709/img/4709084_3.jpg?shrink=500:500&_v=20211228101305'),

       (43,
        'https://iws.danawa.com/prod_img/500000/084/709/desc/prod_4709084/add_2/20210407133650303_2ABM4GIN.jpg'),

       (44,
        'https://img.danawa.com/prod_img/500000/479/221/img/5221479_1.jpg?shrink=500:500&_v=20220714150155'),
       (45,
        'https://img.danawa.com/prod_img/500000/479/221/img/5221479_2.jpg?shrink=500:500&_v=20220714150155'),
       (46,
        'https://img.danawa.com/prod_img/500000/479/221/img/5221479_3.jpg?shrink=500:500&_v=20220714150155'),
       (47,
        'https://img.danawa.com/prod_img/500000/479/221/img/5221479_4.jpg?shrink=500:500&_v=20220714150155'),

       (48,
        'https://iws.danawa.com/prod_img/500000/479/221/desc/prod_5221479/add_1/20211018140519394_J9Z97W8G.jpg'),

       (49,
        'https://img.danawa.com/prod_img/500000/857/565/img/17565857_1.jpg?shrink=330:330&_v=20220729135928'),

       (50,
        'https://iws.danawa.com/prod_img/500000/857/565/desc/prod_17565857/add_1/20220729135819435_FTV70WHO.jpg'),

       (51,
        'https://img.danawa.com/prod_img/500000/637/448/img/15448637_1.jpg?shrink=500:500&_v=20211015164726'),

       (52,
        'https://iws.danawa.com/prod_img/500000/637/448/desc/prod_15448637/add_1/20211015164239828_AVZ91C1V.jpg'),

       (53,
        'https://img.danawa.com/prod_img/500000/701/079/img/17079701_1.jpg?shrink=500:500&_v=20220519094414'),
       (54,
        'https://img.danawa.com/prod_img/500000/701/079/img/17079701_2.jpg?shrink=500:500&_v=20220519094414'),
       (55,
        'https://img.danawa.com/prod_img/500000/701/079/img/17079701_3.jpg?shrink=500:500&_v=20220519094414'),

       (56,
        'https://iws.danawa.com/prod_img/500000/701/079/desc/prod_17079701/add_1/20220519091117682_TD44HO5K.jpg'),

       (57,
        'https://img.danawa.com/prod_img/500000/449/497/img/5497449_1.jpg?shrink=500:500&_v=20190823155537'),
       (58,
        'https://img.danawa.com/prod_img/500000/449/497/img/5497449_2.jpg?shrink=500:500&_v=20190823155537'),
       (59,
        'https://img.danawa.com/prod_img/500000/449/497/img/5497449_3.jpg?shrink=500:500&_v=20190823155537'),

       (60,
        'https://iws.danawa.com/prod_img/500000/449/497/desc/prod_5497449/add_1/FC900R-PD_KR_final.jpg'),

       (61,
        'https://img.danawa.com/prod_img/500000/145/736/img/4736145_1.jpg?shrink=500:500&_v=20170405190254'),
       (62,
        'https://img.danawa.com/prod_img/500000/145/736/img/4736145_2.jpg?shrink=500:500&_v=20170405190254'),

       (63,
        'https://iws.danawa.com/prod_img/500000/145/736/desc/prod_4736145/add_1/STORM_K5_01.jpg'),

       (64,
        'https://img.danawa.com/prod_img/500000/088/351/img/16351088_1.jpg?shrink=500:500&_v=20220209094639'),

       (65,
        'https://iws.danawa.com/prod_img/500000/088/351/desc/prod_16351088/add_1/20220209094315406_MNMQJYMM.jpg'),

       (66,
        'https://img.danawa.com/prod_img/500000/254/285/img/4285254_1.jpg?shrink=500:500&_v=20220602181028'),

       (67, 'https://iws.danawa.com/prod_img/500000/254/285/desc/prod_4285254/add_1/mark5_01.jpg'),

       (68,
        'https://img.danawa.com/prod_img/500000/857/565/img/17565857_1.jpg?shrink=500:500&_v=20220729135928'),

       (69,
        'https://iws.danawa.com/prod_img/500000/076/656/desc/prod_14656076/add_1/20210712131814266_4VMBE7Q4.jpg'),

       (70,
        'https://img.danawa.com/prod_img/500000/254/756/img/3756254_1.jpg?shrink=500:500&_v=20160817152931'),
       (71,
        'https://img.danawa.com/prod_img/500000/254/756/img/3756254_2.jpg?shrink=500:500&_v=20160817152931'),

       (72,
        'https://iws.danawa.com/prod_img/500000/254/756/desc/prod_3756254/add_1/m10tsfl_01.jpg'),

       (73,
        'https://img.danawa.com/prod_img/500000/871/242/img/9242871_1.jpg?shrink=500:500&_v=20200107105606'),
       (74,
        'https://img.danawa.com/prod_img/500000/871/242/img/9242871_2.jpg?shrink=500:500&_v=20200107105606'),
       (75,
        'https://img.danawa.com/prod_img/500000/871/242/img/9242871_3.jpg?shrink=500:500&_v=20200107105606'),

       (76,
        'https://iws.danawa.com/prod_img/500000/871/242/desc/prod_9242871/add_1/20190809160636305_0WQYWL5J.jpg'),

       (77,
        'https://img.danawa.com/prod_img/500000/221/757/img/3757221_1.jpg?shrink=500:500&_v=20160817152730'),
       (78, 'https://iws.danawa.com/prod_img/500000/221/757/desc/prod_3757221/add_1/m9sfl_01.jpg'),

       (79,
        'https://img.danawa.com/prod_img/500000/816/345/img/17345816_1.jpg?shrink=500:500&_v=20220708091931'),
       (80,
        'https://img.danawa.com/prod_img/500000/816/345/img/17345816_2.jpg?shrink=500:500&_v=20220708091931'),
       (81,
        'https://img.danawa.com/prod_img/500000/816/345/img/17345816_3.jpg?shrink=500:500&_v=20220708091931'),
       (82,
        'https://img.danawa.com/prod_img/500000/816/345/img/17345816_4.jpg?shrink=500:500&_v=20220708091931'),

       (83,
        'https://iws.danawa.com/prod_img/500000/816/345/desc/prod_17345816/add_1/20220708091924398_XQROXYB9.jpg'),

       (84,
        'https://img.danawa.com/prod_img/500000/455/679/img/17679455_1.jpg?shrink=500:500&_v=20220818125634'),

       (85,
        'https://iws.danawa.com/prod_img/500000/455/679/desc/prod_17679455/add_1/20220818125644656_T0W59WYD.jpg'),

       (86,
        'https://img.danawa.com/prod_img/500000/799/229/img/15229799_1.jpg?shrink=500:500&_v=20210913112802'),
       (87,
        'https://img.danawa.com/prod_img/500000/799/229/img/15229799_2.jpg?shrink=500:500&_v=20210913112802'),
       (88,
        'https://img.danawa.com/prod_img/500000/799/229/img/15229799_3.jpg?shrink=500:500&_v=20210913112802'),
       (89,
        'https://img.danawa.com/prod_img/500000/799/229/img/15229799_4.jpg?shrink=500:500&_v=20210913112802'),

       (90,
        'https://iws.danawa.com/prod_img/500000/799/229/desc/prod_15229799/add_1/20210913095457840_IBOU9BR6.jpg'),

       (91,
        'https://img.danawa.com/prod_img/500000/484/698/img/3698484_1.jpg?shrink=500:500&_v=20180119091712'),

       (92,
        'https://iws.danawa.com/prod_img/500000/484/698/desc/prod_3698484/add_1/TITANse_01.jpg'),

       (93,
        'https://img.danawa.com/prod_img/500000/072/875/img/12875072_1.jpg?shrink=500:500&_v=20220602181027'),
       (94,
        'https://img.danawa.com/prod_img/500000/072/875/img/12875072_2.jpg?shrink=500:500&_v=20220602181027'),
       (95,
        'https://img.danawa.com/prod_img/500000/072/875/img/12875072_3.jpg?shrink=500:500&_v=20220602181027'),
       (96,
        'https://img.danawa.com/prod_img/500000/072/875/img/12875072_4.jpg?shrink=500:500&_v=20220602181027'),

       (97,
        'https://iws.danawa.com/prod_img/500000/072/875/desc/prod_12875072/add_1/20201209144926292_PSKDRSEV.jpg'),

       (98,
        'https://img.danawa.com/prod_img/500000/852/887/img/11887852_1.jpg?shrink=500:500&_v=20210413125632'),
       (99,
        'https://img.danawa.com/prod_img/500000/852/887/img/11887852_2.jpg?shrink=500:500&_v=20210413125632'),
       (100,
        'https://img.danawa.com/prod_img/500000/852/887/img/11887852_3.jpg?shrink=500:500&_v=20210413125632'),
       (101,
        'https://img.danawa.com/prod_img/500000/852/887/img/11887852_4.jpg?shrink=500:500&_v=20210413125632'),

       (102,
        'https://iws.danawa.com/prod_img/500000/852/887/desc/prod_11887852/add_1/20210318145828103_BTZN0AF7.jpg');

INSERT INTO product_image(product_id, image_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 4),
       (2, 5),
       (2, 6),
       (2, 7),
       (3, 8),
       (3, 9),
       (4, 10),
       (4, 11),
       (4, 12),
       (5, 13),
       (5, 14),
       (6, 15),
       (6, 16),
       (6, 17),
       (7, 18),
       (8, 19),
       (9, 20),
       (10, 21),
       (11, 22),
       (11, 23),
       (11, 24),
       (12, 40),
       (12, 41),
       (12, 42),
       (13, 44),
       (13, 45),
       (13, 46),
       (13, 47),
       (14, 49),
       (15, 51),
       (16, 53),
       (16, 54),
       (16, 55),
       (17, 57),
       (17, 58),
       (17, 59),
       (18, 61),
       (18, 62),
       (19, 64),
       (20, 66),
       (21, 68),
       (22, 70),
       (22, 71),
       (23, 73),
       (23, 74),
       (23, 75),
       (24, 77),
       (25, 79),
       (25, 80),
       (25, 81),
       (25, 82),
       (26, 84),
       (27, 86),
       (27, 87),
       (27, 88),
       (27, 89),
       (28, 91),
       (29, 93),
       (29, 94),
       (29, 95),
       (29, 96),
       (30, 98),
       (30, 99),
       (30, 100),
       (30, 101);

INSERT INTO product_info_image(product_id, image_id)
VALUES (1, 26),
       (2, 27),
       (3, 28),
       (4, 29),
       (5, 30),
       (6, 31),
       (7, 32),
       (8, 33),
       (9, 34),
       (10, 35),
       (11, 36),
       (12, 43),
       (13, 48),
       (14, 50),
       (15, 52),
       (16, 56),
       (17, 60),
       (18, 63),
       (19, 65),
       (20, 67),
       (21, 69),
       (22, 72),
       (23, 76),
       (24, 78),
       (25, 83),
       (26, 85),
       (27, 90),
       (28, 92),
       (29, 97),
       (30, 102);

INSERT INTO product_like(product_id, member_id)
VALUES (1, 1);

INSERT INTO keyboard_switch(keyboard_id, switch_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 2),
       (2, 4),
       (2, 5),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1),
       (7, 1),
       (8, 1),
       (9, 1),
       (10, 1),
       (11, 1),
       (12, 1),
       (12, 2),
       (13, 4),
       (14, 2),
       (14, 5),
       (15, 1),
       (16, 2),
       (17, 2),
       (18, 1),
       (19, 3),
       (20, 1),
       (21, 2),
       (21, 4),
       (22, 1),
       (22, 4),
       (23, 5),
       (24, 5),
       (25, 2),
       (26, 1),
       (26, 4),
       (27, 1),
       (28, 5),
       (29, 2),
       (30, 4);
