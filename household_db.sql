-- phpMyAdmin SQL Dump
-- version 5.1.1deb5ubuntu1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- 생성 시간: 23-03-06 11:25
-- 서버 버전: 8.0.31-0ubuntu0.22.04.1
-- PHP 버전: 8.1.2-1ubuntu2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 데이터베이스: `household_db`
--

-- --------------------------------------------------------

--
-- 테이블 구조 `culture_category`
--

CREATE TABLE `culture_category` (
  `cc_seq` int NOT NULL,
  `cc_name` varchar(50) NOT NULL,
  `cc_mi_seq` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `culture_category`
--

INSERT INTO `culture_category` (`cc_seq`, `cc_name`, `cc_mi_seq`) VALUES
(1, '공연', 1),
(2, '스포츠', 1),
(3, '전시회', 1),
(21, 'test1', 3),
(49, '영화', 8),
(50, '전시', 8),
(51, '공연', 8),
(52, '여행', 8),
(53, '기타', 8),
(54, '영화', 11),
(55, '전시', 11),
(56, '공연', 11),
(57, '여행', 11);

-- --------------------------------------------------------

--
-- 테이블 구조 `culture_detail_category`
--

CREATE TABLE `culture_detail_category` (
  `cdc_seq` int NOT NULL,
  `cdc_name` varchar(50) NOT NULL,
  `cdc_cc_seq` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `culture_detail_category`
--

INSERT INTO `culture_detail_category` (`cdc_seq`, `cdc_name`, `cdc_cc_seq`) VALUES
(1, '오케스트라', 1),
(2, '콘서트', 1),
(3, '오페라', 1),
(4, '뮤지컬', 1),
(5, '서커스', 1),
(6, '마술', 1),
(7, '축구', 2),
(8, '농구', 2),
(9, '배구', 2),
(10, '핸드볼', 2),
(11, '야구', 2),
(12, '하키', 2),
(13, '럭비', 2),
(14, '미식축구', 2),
(15, '골프', 2),
(16, '미술관', 3),
(17, '박물관', 3),
(18, '갤러리', 3),
(21, '스포츠추가', 2),
(24, '오케스트라', 2),
(25, '세부카테고리1', 21),
(34, '굿즈', 49),
(35, '티켓', 49),
(36, '미술', 50),
(37, '사진', 50),
(38, '음식', 52),
(39, '관광명소', 52),
(40, '뮤지컬', 51),
(41, '연극', 51),
(42, '오페라', 51),
(43, '서커스', 51),
(44, '팬미팅', 49),
(45, '티켓', 54),
(46, '사진', 55),
(47, '오페라', 56),
(48, '연극', 56),
(49, '뮤지컬', 56),
(50, '비행기 티켓', 57),
(51, '음식', 57),
(53, '역사', 55),
(54, '미술', 55),
(55, '여행지', 57);

-- --------------------------------------------------------

--
-- 테이블 구조 `expense_history`
--

CREATE TABLE `expense_history` (
  `eh_seq` int NOT NULL,
  `eh_title` varchar(60) NOT NULL,
  `eh_content` text,
  `eh_date` datetime NOT NULL,
  `eh_mi_seq` int NOT NULL,
  `eh_pi_seq` int NOT NULL,
  `eh_price` int NOT NULL,
  `eh_store_name` varchar(100) NOT NULL,
  `eh_img_file` text,
  `eh_location` text NOT NULL,
  `eh_balance` int NOT NULL,
  `eh_cc_seq` int NOT NULL,
  `eh_cdc_seq` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `expense_history`
--

INSERT INTO `expense_history` (`eh_seq`, `eh_title`, `eh_content`, `eh_date`, `eh_mi_seq`, `eh_pi_seq`, `eh_price`, `eh_store_name`, `eh_img_file`, `eh_location`, `eh_balance`, `eh_cc_seq`, `eh_cdc_seq`) VALUES
(1, '스파이더맨 영화', '스파이더맨 노웨이홈 1명', '2022-02-09 12:00:00', 1, 1, 10000, 'CGV 대구연경', 'Expose_1676594709326.jpg', '대구광역시 북구 연경동 동화천로 290', 20000, 1, 1),
(2, 'Charlie Puth Presents The \"Charlie\" Live Experience', '찰리 푸스 공연', '2022-05-24 19:30:00', 1, 1, 10000, 'The Pavilion at Toyota Music Factory', 'Expose_1676596619839.png', '300 W Las Colinas Blvd., Irving, TX 75039 미국', 20000, 2, 1),
(3, '베토벤 뮤지컬', '뮤지컬 〈베토벤; Beethoven Secret〉', '2022-02-25 14:30:00', 1, 1, 140000, '예술의전당 오페라하우스', 'Expose_1676602254257.jpeg', '서울특별시 서초구 서초3동 남부순환로 2406', 1000000, 2, 1),
(4, 'LCK 관람', 'LCK 스프링 (2.17) - 1경기 LSB vs T1', '2022-02-17 14:30:00', 1, 1, 9000, '롤파크', 'Expose_1676603027287.jpeg', '서울특별시 종로구 종로 33 그랑서울', 1000000, 1, 1),
(5, '덕수궁 석조전', '엔터<br/>테스트<br/>합니다<br/>안<br/>녕<br/>하<br/>세요', '2023-02-27 19:30:29', 1, 1, 6000, '덕수궁', 'Expose_1677493862205.jpeg', '덕수궁', 10000, 2, 2),
(6, '스키장', '테스트\r\n엔터\r\n테스트\r\n엔터', '2023-02-27 19:32:50', 1, 1, 800000, '곤지암리조트', 'Expose_1677494013624.jpeg', '곤지암리조트', 10000, 2, 2),
(7, '덕수궁 석조전', '3.1절 덕수궁 대한제국 석조전 방문  \r\n못 들어가서 아쉬움', '2023-03-01 15:01:28', 3, 4, 6000, '한국문화재청', 'Expose_1677736957515.jpeg', '덕수궁', 10000, 3, 17),
(8, '구름 그림', '구름 그림 ', '2023-03-02 15:12:43', 3, 1, 0, '집', 'Expose_1677737597088.jpeg', '집', 10000, 3, 17),
(9, '초밥집', '음식의 미술관', '2023-03-03 15:20:26', 3, 31, 6600, '스시하치', 'Expose_1677738095140.jpeg', '스시하치', 10000, 3, 16),
(10, '크레이프', '겁나 맛있습니다', '2023-03-12 15:23:51', 3, 4, 4000, '유니모', 'Expose_1677738299486.jpeg', '유니모', 10000, 1, 4),
(11, '어서오세요 대구입니다', '이거 뭐임??????', '2023-03-24 15:25:03', 3, 4, 100000, '서울 강남 대구광역시', 'Expose_1677738358192.jpeg', '서울 강남', 10000, 1, 6),
(12, '아이스크림의 신세계', '맛 돌았다\r\n근데 너무 비쌈', '2023-03-28 15:26:17', 3, 4, 5000, '이치하라 파킹', 'Expose_1677738437750.jpeg', '이치하라 파킹', 10000, 3, 16),
(13, '버거왕', '아이러빙잇\r\n빅맥 좋아요', '2023-03-12 16:56:45', 3, 3, 200000, '맥도날드', 'Expose_1677743870806.jpeg', '맥도날드', 10000, 1, 1),
(14, '덕수궁', '덕수궁', '2023-03-03 11:38:33', 3, 4, 6000, '한국문화재청', 'Expose_1677811224590.jpeg', '덕수궁', 10000, 1, 1),
(15, '고양이', '귀엽습니다', '2023-03-06 11:42:06', 3, 1, 10000000, '집', 'Expose_1677811420602.jpeg', '집', 10000, 3, 18),
(16, '음료수', '축구시청', '2023-01-03 11:59:43', 3, 12, 50000, '슈퍼', 'Expose_1677812467820.jpeg', '집', 10000, 2, 7),
(17, '꽃게링', '감상', '2023-01-10 00:04:23', 3, 2, 60000, '매점', 'Expose_1677812616116.jpeg', '대구문화회관', 10000, 1, 1),
(18, '우유', '야구 후 바나나맛 우유', '2023-02-15 12:06:00', 3, 13, 100000, '매점', 'Expose_1677812701426.jpeg', '집', 10000, 2, 11),
(19, '아이스크림', '아이스크림', '2023-02-14 12:05:13', 3, 25, 80000, '매점', 'Expose_1677812775966.jpeg', '축구장', 10000, 3, 18),
(20, '요플레', '테스트 분류', '2023-01-10 12:06:25', 3, 12, 150000, '매점2', 'Expose_1677812906217.jpeg', '집', 10000, 2, 9),
(21, '사탕', '먹기', '2023-01-17 12:09:12', 3, 3, 100000, '매점3', 'Expose_1677813006842.jpeg', '집', 10000, 3, 16),
(22, '딸기우유', 'ㅇㅅㅇ', '2022-12-15 12:10:24', 3, 15, 180000, '매점3', 'Expose_1677813076014.jpeg', '집', 10000, 21, 25),
(23, '덕수궁 석조전', '덕수궁 석조전 관람\r\n시간이 늦으서 안에 못 들어가서 아쉽다.', '2023-03-06 20:23:53', 8, 4, 6000, '한국문화재청', 'Expose_1677813894128.jpeg', '덕수궁', 10000, 3, 17),
(24, '예쁜 나무', '일루미네이션 너무 예쁜 나무', '2023-02-09 20:25:44', 8, 4, 20000, '독일마을', 'Expose_1677814021631.jpeg', '일루미네이션', 10000, 3, 16),
(25, '일루미네이션 나무', '일루미네이션 나무 너무 예뻤음', '2023-03-16 12:28:08', 8, 4, 20000, '독일마을', 'Expose_1677814142158.jpeg', '독일마을', 10000, 3, 16),
(26, '간만에 숭례문', '장엄함', '2023-03-03 12:29:11', 8, 4, 1200, '숭례문', 'Expose_1677814180891.jpeg', '숭례문', 10000, 3, 17),
(27, '어 금지', '갬성', '2023-03-21 15:05:53', 3, 4, 100000, '그린컴퓨터아카데미', 'Expose_1677823600417.jpeg', '그린컴퓨터아카데미', 10000, 3, 18),
(28, '광화문 토끼', '광화문 토끼 보고왔다', '2023-03-29 15:54:37', 8, 4, 6000, '광화문광장', 'Expose_1677826521763.jpeg', '광화문 광장', 10000, 50, 36),
(29, '덕수궁 석조전', '덕수궁 석조전 보고왔다.', '2023-03-01 16:04:14', 11, 4, 6000, '한국문화재청', 'Expose_1677827098081.jpeg', '덕수궁', 10000, 55, 53),
(30, '일루미네이션', '일루미네이션 나무', '2023-03-12 16:05:21', 11, 4, 20000, '독일마을', 'Expose_1677827167181.jpeg', '독일마을', 10000, 55, 54),
(31, '대방어 초밥', '정말 맛있습니다.', '2023-03-12 20:06:09', 11, 4, 6600, '스시하치', 'Expose_1677827221785.jpeg', '스시하치', 10000, 57, 51),
(32, '광화문 등불축제', '광화문 등불축제 보고왔습니다', '2023-03-19 16:09:35', 11, 4, 10000, '광화문', 'Expose_1677827426986.jpeg', '광화문', 10000, 55, 54);

-- --------------------------------------------------------

--
-- Stand-in structure for view `expense_list_view`
-- (See below for the actual view)
--
CREATE TABLE `expense_list_view` (
`mi_seq` int
,`eh_seq` int
,`eh_date` datetime
,`eh_title` varchar(60)
,`eh_content` text
,`eh_price` int
,`eh_store_name` varchar(100)
,`eh_location` text
,`eh_img_file` text
,`cc_seq` int
,`cc_name` varchar(50)
,`cdc_seq` int
,`cdc_name` varchar(50)
,`pi_type` int
,`pi_name` varchar(50)
);

-- --------------------------------------------------------

--
-- 테이블 구조 `kakaomember_info`
--

CREATE TABLE `kakaomember_info` (
  `kmi_seq` bigint NOT NULL,
  `kmi_email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `kmi_nickname` varchar(100) NOT NULL,
  `kmi_role` varchar(100) NOT NULL DEFAULT 'USER'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `kakaomember_info`
--

INSERT INTO `kakaomember_info` (`kmi_seq`, `kmi_email`, `kmi_nickname`, `kmi_role`) VALUES
(10, 'addi0518@naver.com', '권영장', 'USER');

-- --------------------------------------------------------

--
-- 테이블 구조 `member_info`
--

CREATE TABLE `member_info` (
  `mi_seq` int NOT NULL,
  `mi_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `mi_birth` date DEFAULT NULL,
  `mi_nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `mi_gen` int DEFAULT NULL,
  `mi_job` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `mi_email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `mi_pwd` varchar(100) NOT NULL,
  `mi_role` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'USER'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `member_info`
--

INSERT INTO `member_info` (`mi_seq`, `mi_name`, `mi_birth`, `mi_nickname`, `mi_gen`, `mi_job`, `mi_email`, `mi_pwd`, `mi_role`) VALUES
(1, NULL, NULL, 'user02', NULL, NULL, 'user02@gmail.com', '!1asdfgh', 'USER'),
(2, NULL, NULL, 'user03', NULL, NULL, 'user03@gmail.com', '!1asdfgh', 'USER'),
(3, NULL, NULL, 'user04', NULL, NULL, 'user04@gmail.com', '!1asdfgh', 'USER'),
(4, NULL, NULL, 'test1234', NULL, NULL, 'test1234@gmail.com', 'test1234!@', 'USER'),
(6, NULL, NULL, 'user05', NULL, NULL, 'user05@email.com', '!1asdfgh', 'USER'),
(7, NULL, NULL, 'user06', NULL, NULL, 'user06@email.com', '!1asdfgh', 'USER'),
(8, NULL, NULL, 'user07', NULL, NULL, 'user07@gmail.com', '!1asdfgh', 'USER'),
(9, NULL, NULL, 'user08', NULL, NULL, 'user08@email.com', '!1asdfgh', 'USER'),
(10, NULL, NULL, 'user10', NULL, NULL, 'user10@email.com', '!1asdfgh', 'USER'),
(11, NULL, NULL, 'user15', NULL, NULL, 'user15@gmail.com', '!1asdfgh', 'USER'),
(12, NULL, NULL, 'user20', NULL, NULL, 'user20@gmail.com', '!1asdfgh', 'USER');

-- --------------------------------------------------------

--
-- 테이블 구조 `payment_info`
--

CREATE TABLE `payment_info` (
  `pi_seq` int NOT NULL,
  `pi_type` int NOT NULL,
  `pi_name` varchar(50) DEFAULT NULL,
  `pi_mi_seq` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 테이블의 덤프 데이터 `payment_info`
--

INSERT INTO `payment_info` (`pi_seq`, `pi_type`, `pi_name`, `pi_mi_seq`) VALUES
(1, 1, 'KB국민카드', 1),
(2, 1, '롯데카드', 1),
(3, 1, '신한카드', 1),
(4, 1, '하나카드', 1),
(6, 1, '삼성카드', 1),
(7, 1, '현대카드', 1),
(8, 1, '우리카드', 1),
(9, 1, 'NH농협카드', 1),
(10, 1, '씨티카드', 1),
(11, 1, '카카오뱅크카드', 1),
(12, 1, '토스뱅크카드', 1),
(13, 2, '카카오뱅크', 1),
(14, 2, '농협은행', 1),
(15, 2, '국민은행', 1),
(16, 2, '신한은행', 1),
(17, 2, '우리은행', 1),
(18, 2, '기업은행', 1),
(19, 2, '새마을금고', 1),
(20, 2, '우체국', 1),
(21, 2, 'SC제일은행', 1),
(22, 2, '대구은행', 1),
(23, 2, '부산은행', 1),
(25, 2, '광주은행', 1),
(26, 2, '수협은행', 1),
(27, 2, '제주은행', 1),
(31, 3, '현금', 1),
(37, 2, '씨티은행', 3),
(38, 3, '현금2', 3),
(40, 1, '하나은행카드', 3),
(41, 1, 'OK뱅크카드', 3),
(42, 1, '기업카드', 3);

-- --------------------------------------------------------

--
-- 뷰 구조 `expense_list_view`
--
DROP TABLE IF EXISTS `expense_list_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `expense_list_view`  AS SELECT `e`.`mi_seq` AS `mi_seq`, `a`.`eh_seq` AS `eh_seq`, `a`.`eh_date` AS `eh_date`, `a`.`eh_title` AS `eh_title`, `a`.`eh_content` AS `eh_content`, `a`.`eh_price` AS `eh_price`, `a`.`eh_store_name` AS `eh_store_name`, `a`.`eh_location` AS `eh_location`, `a`.`eh_img_file` AS `eh_img_file`, `b`.`cc_seq` AS `cc_seq`, `b`.`cc_name` AS `cc_name`, `c`.`cdc_seq` AS `cdc_seq`, `c`.`cdc_name` AS `cdc_name`, `d`.`pi_type` AS `pi_type`, `d`.`pi_name` AS `pi_name` FROM ((((`member_info` `e` left join `expense_history` `a` on((`a`.`eh_mi_seq` = `e`.`mi_seq`))) left join `culture_category` `b` on((`a`.`eh_cc_seq` = `b`.`cc_seq`))) left join `culture_detail_category` `c` on((`a`.`eh_cdc_seq` = `c`.`cdc_seq`))) left join `payment_info` `d` on((`a`.`eh_pi_seq` = `d`.`pi_seq`))) ;

--
-- 덤프된 테이블의 인덱스
--

--
-- 테이블의 인덱스 `culture_category`
--
ALTER TABLE `culture_category`
  ADD PRIMARY KEY (`cc_seq`),
  ADD KEY `cc_mi_seq` (`cc_mi_seq`);

--
-- 테이블의 인덱스 `culture_detail_category`
--
ALTER TABLE `culture_detail_category`
  ADD PRIMARY KEY (`cdc_seq`),
  ADD KEY `culture_detail_category_FK` (`cdc_cc_seq`);

--
-- 테이블의 인덱스 `expense_history`
--
ALTER TABLE `expense_history`
  ADD PRIMARY KEY (`eh_seq`),
  ADD KEY `pi_seq` (`eh_pi_seq`),
  ADD KEY `cc_seq` (`eh_cc_seq`),
  ADD KEY `cdc_seq` (`eh_cdc_seq`),
  ADD KEY `mi_seq` (`eh_mi_seq`);

--
-- 테이블의 인덱스 `kakaomember_info`
--
ALTER TABLE `kakaomember_info`
  ADD PRIMARY KEY (`kmi_seq`);

--
-- 테이블의 인덱스 `member_info`
--
ALTER TABLE `member_info`
  ADD PRIMARY KEY (`mi_seq`);

--
-- 테이블의 인덱스 `payment_info`
--
ALTER TABLE `payment_info`
  ADD PRIMARY KEY (`pi_seq`),
  ADD KEY `pi_mi_seq` (`pi_mi_seq`);

--
-- 덤프된 테이블의 AUTO_INCREMENT
--

--
-- 테이블의 AUTO_INCREMENT `culture_category`
--
ALTER TABLE `culture_category`
  MODIFY `cc_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- 테이블의 AUTO_INCREMENT `culture_detail_category`
--
ALTER TABLE `culture_detail_category`
  MODIFY `cdc_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- 테이블의 AUTO_INCREMENT `expense_history`
--
ALTER TABLE `expense_history`
  MODIFY `eh_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- 테이블의 AUTO_INCREMENT `kakaomember_info`
--
ALTER TABLE `kakaomember_info`
  MODIFY `kmi_seq` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- 테이블의 AUTO_INCREMENT `member_info`
--
ALTER TABLE `member_info`
  MODIFY `mi_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- 테이블의 AUTO_INCREMENT `payment_info`
--
ALTER TABLE `payment_info`
  MODIFY `pi_seq` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- 덤프된 테이블의 제약사항
--

--
-- 테이블의 제약사항 `culture_category`
--
ALTER TABLE `culture_category`
  ADD CONSTRAINT `cc_mi_seq` FOREIGN KEY (`cc_mi_seq`) REFERENCES `member_info` (`mi_seq`);

--
-- 테이블의 제약사항 `culture_detail_category`
--
ALTER TABLE `culture_detail_category`
  ADD CONSTRAINT `culture_detail_category_FK` FOREIGN KEY (`cdc_cc_seq`) REFERENCES `culture_category` (`cc_seq`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- 테이블의 제약사항 `expense_history`
--
ALTER TABLE `expense_history`
  ADD CONSTRAINT `cc_seq` FOREIGN KEY (`eh_cc_seq`) REFERENCES `culture_category` (`cc_seq`),
  ADD CONSTRAINT `cdc_seq` FOREIGN KEY (`eh_cdc_seq`) REFERENCES `culture_detail_category` (`cdc_seq`),
  ADD CONSTRAINT `mi_seq` FOREIGN KEY (`eh_mi_seq`) REFERENCES `member_info` (`mi_seq`),
  ADD CONSTRAINT `pi_seq` FOREIGN KEY (`eh_pi_seq`) REFERENCES `payment_info` (`pi_seq`);

--
-- 테이블의 제약사항 `payment_info`
--
ALTER TABLE `payment_info`
  ADD CONSTRAINT `pi_mi_seq` FOREIGN KEY (`pi_mi_seq`) REFERENCES `member_info` (`mi_seq`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
