/* * data.sql - 렌터카 프로젝트 초기 더미 데이터
 * 스크린샷의 차종(Segment) 및 연료(Fuel) 기준 반영
 * 학습용 주석 포함
 */




-- ==========================================
-- 1. 회원 데이터 (Users) - 5명
-- ==========================================
-- 실제 서비스처럼 관리자, 일반 유저, 블랙리스트 등 다양한 케이스를 가정
INSERT INTO users (username, email) VALUES ('admin_kim', 'admin@rentcar.com');  -- 관리자
INSERT INTO users (username, email) VALUES ('user_lee', 'lee@naver.com');       -- 일반 고객 1
INSERT INTO users (username, email) VALUES ('user_park', 'park@gmail.com');     -- 일반 고객 2
INSERT INTO users (username, email) VALUES ('user_choi', 'choi@daum.net');      -- 장기 렌트 고객
INSERT INTO users (username, email) VALUES ('bad_user', 'black@test.com');      -- 블랙리스트(예시)


-- ==========================================
-- 2. 지점 및 픽업존 (Branch & Zone) - 3개 거점
-- ==========================================
-- 서울(역), 부산(역), 제주(공항) 3대 거점으로 구성

-- (1) 서울역점
INSERT INTO branch (name, region_depth1, branch_type, address, latitude, longitude, phone, open_hours, is_active)
VALUES ('서울역점', '서울', 'Station', '서울 용산구 한강대로 405', 37.5547, 126.9707, '02-111-2222', '08:00~22:00', 1);

INSERT INTO pickup_zone (branch_id, name, latitude, longitude, guide_info, landmark, is_active)
VALUES (1, '서울역 제2주차장 3층', 37.5547, 126.9707, 'KTX 3번 출구로 나와서 주차장 엘리베이터 이용', '롯데마트 옆', 1);

-- (2) 부산역점
INSERT INTO branch (name, region_depth1, branch_type, address, latitude, longitude, phone, open_hours, is_active)
VALUES ('부산역점', '부산', 'Station', '부산 동구 중앙대로 206', 35.1152, 129.0422, '051-333-4444', '09:00~20:00', 1);

INSERT INTO pickup_zone (branch_id, name, latitude, longitude, guide_info, landmark, is_active)
VALUES (2, '부산역 광장 지하주차장', 35.1152, 129.0422, '지하 2층 A열 15번 기둥', '부산역 광장 분수대', 1);

-- (3) 제주공항점
INSERT INTO branch (name, region_depth1, branch_type, address, latitude, longitude, phone, open_hours, is_active)
VALUES ('제주공항점', '제주', 'Airport', '제주시 공항로 2', 33.5104, 126.4913, '064-555-6666', '06:00~23:00', 1);

INSERT INTO pickup_zone (branch_id, name, latitude, longitude, guide_info, landmark, is_active)
VALUES (3, '렌터카 하우스 5구역', 33.5104, 126.4913, '공항 5번 게이트 앞 셔틀버스 탑승', '렌터카 하우스', 1);


-- ==========================================
-- 3. 브랜드 및 모델 (Brand & Model)
-- ==========================================
-- 스크린샷 분류 기준: 경형, 준중형, 중형, 대형, SUV, 승합RV, 수입
-- 연료 기준: 휘발유, 경유, LPG, 전기, 하이브리드

INSERT INTO brand (brand_name, origin_country) VALUES ('Hyundai', 'Korea');
INSERT INTO brand (brand_name, origin_country) VALUES ('Kia', 'Korea');
INSERT INTO brand (brand_name, origin_country) VALUES ('Genesis', 'Korea');
INSERT INTO brand (brand_name, origin_country) VALUES ('BMW', 'Germany');

-- 모델 1: 캐스퍼 (경형/휘발유)
INSERT INTO model (brand_id, model_name, fuel_type, segment, passenger_limit, transmission)
VALUES (1, 'Casper', 'Gasoline', 'Mini', 4, 'Auto'); -- 스크린샷 '경형'

-- 모델 2: 아반떼 (준중형/휘발유)
INSERT INTO model (brand_id, model_name, fuel_type, segment, passenger_limit, transmission)
VALUES (1, 'Avante', 'Gasoline', 'Compact', 5, 'Auto'); -- 스크린샷 '준중형'

-- 모델 3: 쏘나타 (중형/LPG)
INSERT INTO model (brand_id, model_name, fuel_type, segment, passenger_limit, transmission)
VALUES (1, 'Sonata', 'LPG', 'Midsize', 5, 'Auto'); -- 스크린샷 '중형'

-- 모델 4: G80 (대형/휘발유)
INSERT INTO model (brand_id, model_name, fuel_type, segment, passenger_limit, transmission)
VALUES (3, 'G80', 'Gasoline', 'Large', 5, 'Auto'); -- 스크린샷 '대형'

-- 모델 5: 쏘렌토 (SUV/하이브리드)
INSERT INTO model (brand_id, model_name, fuel_type, segment, passenger_limit, transmission)
VALUES (2, 'Sorento', 'Hybrid', 'SUV', 5, 'Auto'); -- 스크린샷 'SUV', '하이브리드'

-- 모델 6: 카니발 (승합RV/경유)
INSERT INTO model (brand_id, model_name, fuel_type, segment, passenger_limit, transmission)
VALUES (2, 'Carnival', 'Diesel', 'RV', 9, 'Auto'); -- 스크린샷 '승합RV', '경유'

-- 모델 7: 아이오닉5 (SUV/전기)
INSERT INTO model (brand_id, model_name, fuel_type, segment, passenger_limit, transmission)
VALUES (1, 'Ionic5', 'Electric', 'SUV', 5, 'Auto'); -- 스크린샷 'SUV', '전기'

-- 모델 8: BMW 520i (수입/휘발유)
INSERT INTO model (brand_id, model_name, fuel_type, segment, passenger_limit, transmission)
VALUES (4, '520i', 'Gasoline', 'Import', 5, 'Auto'); -- 스크린샷 '수입'


-- ==========================================
-- 4. 실제 차량 (Cars) - 12대
-- ==========================================
-- 각 지점에 차량을 골고루 배치

-- 서울역점 차량 (4대)
INSERT INTO car (model_id, zone_id, car_number, year, color, mileage, status, description)
VALUES (2, 1, '12가 3456', 2024, 'White', 5000, 'AVAILABLE', '신차급, 금연차량'); -- 아반떼
INSERT INTO car (model_id, zone_id, car_number, year, color, mileage, status, description)
VALUES (4, 1, '33허 9999', 2023, 'Black', 15000, 'AVAILABLE', '프리미엄 세단'); -- G80
INSERT INTO car (model_id, zone_id, car_number, year, color, mileage, status, description)
VALUES (7, 1, '11호 1111', 2024, 'Silver', 3000, 'RENTED', '전기차 충전 80% 이상'); -- 아이오닉5
INSERT INTO car (model_id, zone_id, car_number, year, color, mileage, status, description)
VALUES (1, 1, '99하 1212', 2025, 'Khaki', 1000, 'AVAILABLE', '귀여운 캐스퍼'); -- 캐스퍼

-- 부산역점 차량 (4대)
INSERT INTO car (model_id, zone_id, car_number, year, color, mileage, status, description)
VALUES (5, 2, '55호 5555', 2023, 'White', 22000, 'AVAILABLE', '패밀리 SUV 강추'); -- 쏘렌토
INSERT INTO car (model_id, zone_id, car_number, year, color, mileage, status, description)
VALUES (3, 2, '28허 2828', 2022, 'Grey', 45000, 'AVAILABLE', 'LPG 가성비 최고'); -- 쏘나타
INSERT INTO car (model_id, zone_id, car_number, year, color, mileage, status, description)
VALUES (2, 2, '19하 1919', 2023, 'Blue', 18000, 'MAINTENANCE', '타이어 교체 중'); -- 아반떼 (정비중)
INSERT INTO car (model_id, zone_id, car_number, year, color, mileage, status, description)
VALUES (6, 2, '77호 7777', 2024, 'Black', 8000, 'AVAILABLE', '9인승 버스전용차로 가능'); -- 카니발

-- 제주공항점 차량 (4대)
INSERT INTO car (model_id, zone_id, car_number, year, color, mileage, status, description)
VALUES (8, 3, '52허 5252', 2023, 'White', 12000, 'AVAILABLE', '제주 해안도로 드라이브 추천'); -- BMW
INSERT INTO car (model_id, zone_id, car_number, year, color, mileage, status, description)
VALUES (7, 3, '10호 1004', 2024, 'Matte Gray', 4000, 'AVAILABLE', '전기차 조용함'); -- 아이오닉5
INSERT INTO car (model_id, zone_id, car_number, year, color, mileage, status, description)
VALUES (1, 3, '82하 8282', 2025, 'Yellow', 500, 'AVAILABLE', '혼자 여행 추천'); -- 캐스퍼
INSERT INTO car (model_id, zone_id, car_number, year, color, mileage, status, description)
VALUES (6, 3, '60호 6060', 2023, 'White', 25000, 'RENTED', '골프백 4개 수납 가능'); -- 카니발


-- ==========================================
-- 5. 보험 정책 (Insurance) - 단기/장기 분리 요청 반영
-- ==========================================

-- (1) 일반 자차 (단기 렌트 기본)
INSERT INTO insurance_policies (policy_key, name, daily_fee, rent_discount_rate, description, is_active)
VALUES ('basic_short', '일반 자차 (단기)', 15000, 0.00, '사고 시 면책금 30만원 발생, 단기 대여용 기본 보험', 1);

-- (2) 완전 자차 (단기 렌트 고급)
INSERT INTO insurance_policies (policy_key, name, daily_fee, rent_discount_rate, description, is_active)
VALUES ('full_short', '완전 자차 (단기)', 30000, 0.00, '사고 시 고객 부담금 0원, 초보 운전자 추천', 1);

-- (3) 장기 렌트 전용 보험 (할인 혜택 포함)
-- 특징: 보험료는 조금 낮추고, 렌트료 할인(5%)을 적용하여 장기 고객 유치
INSERT INTO insurance_policies (policy_key, name, daily_fee, rent_discount_rate, description, is_active)
VALUES ('long_term_care', '장기 렌트 케어', 8000, 0.05, '[월 렌트 전용] 저렴한 보험료 + 렌트료 5% 추가 할인 혜택', 1);


-- ==========================================
-- 6. 가격 설정 (Rental Prices) - monthly_price 포함
-- ==========================================
-- car_id 순서대로 1~12번까지 가격 입력
-- 공식: 월 렌트료(monthly)는 보통 일일요금 * 20일~25일 정도로 저렴하게 책정

-- 1. 아반떼 (서울)
INSERT INTO rental_prices (id, car_id, daily_price, hourly_price, monthly_price, weekend_price, start_date, end_date)
VALUES ('p-1', 1, 60000, 6000, 900000, 70000, '2025-01-01', '2025-12-31');

-- 2. G80 (서울)
INSERT INTO rental_prices (id, car_id, daily_price, hourly_price, monthly_price, weekend_price, start_date, end_date)
VALUES ('p-2', 2, 200000, 20000, 3500000, 230000, '2025-01-01', '2025-12-31');

-- 3. 아이오닉5 (서울)
INSERT INTO rental_prices (id, car_id, daily_price, hourly_price, monthly_price, weekend_price, start_date, end_date)
VALUES ('p-3', 3, 120000, 12000, 1800000, 140000, '2025-01-01', '2025-12-31');

-- 4. 캐스퍼 (서울)
INSERT INTO rental_prices (id, car_id, daily_price, hourly_price, monthly_price, weekend_price, start_date, end_date)
VALUES ('p-4', 4, 40000, 4000, 600000, 50000, '2025-01-01', '2025-12-31');

-- 5. 쏘렌토 (부산)
INSERT INTO rental_prices (id, car_id, daily_price, hourly_price, monthly_price, weekend_price, start_date, end_date)
VALUES ('p-5', 5, 110000, 11000, 1700000, 130000, '2025-01-01', '2025-12-31');

-- 6. 쏘나타 (부산)
INSERT INTO rental_prices (id, car_id, daily_price, hourly_price, monthly_price, weekend_price, start_date, end_date)
VALUES ('p-6', 6, 80000, 8000, 1300000, 95000, '2025-01-01', '2025-12-31');

-- 7. 아반떼 (부산)
INSERT INTO rental_prices (id, car_id, daily_price, hourly_price, monthly_price, weekend_price, start_date, end_date)
VALUES ('p-7', 7, 60000, 6000, 900000, 70000, '2025-01-01', '2025-12-31');

-- 8. 카니발 (부산)
INSERT INTO rental_prices (id, car_id, daily_price, hourly_price, monthly_price, weekend_price, start_date, end_date)
VALUES ('p-8', 8, 150000, 15000, 2200000, 180000, '2025-01-01', '2025-12-31');

-- 9. BMW (제주) - 수입차라 비쌈
INSERT INTO rental_prices (id, car_id, daily_price, hourly_price, monthly_price, weekend_price, start_date, end_date)
VALUES ('p-9', 9, 250000, 25000, 4500000, 300000, '2025-01-01', '2025-12-31');

-- 10. 아이오닉5 (제주)
INSERT INTO rental_prices (id, car_id, daily_price, hourly_price, monthly_price, weekend_price, start_date, end_date)
VALUES ('p-10', 10, 120000, 12000, 1800000, 140000, '2025-01-01', '2025-12-31');

-- 11. 캐스퍼 (제주)
INSERT INTO rental_prices (id, car_id, daily_price, hourly_price, monthly_price, weekend_price, start_date, end_date)
VALUES ('p-11', 11, 45000, 4500, 700000, 55000, '2025-01-01', '2025-12-31'); -- 제주는 조금 더 비싸게 책정

-- 12. 카니발 (제주)
INSERT INTO rental_prices (id, car_id, daily_price, hourly_price, monthly_price, weekend_price, start_date, end_date)
VALUES ('p-12', 12, 160000, 16000, 2400000, 190000, '2025-01-01', '2025-12-31');


-- ==========================================
-- 7. 예약 샘플 (Reservation)
-- ==========================================
-- 예약 데이터가 있어야 대시보드 화면이 심심하지 않음

-- 예약 1: 김관리(user_id=1)가 서울에서 아반떼(car_id=1) 예약 (완료됨)
INSERT INTO reservation
(user_id, car_id, pickup_zone_id, return_zone_id, start_date, end_date, driver_name, driver_phone, license_kind, insurance_policy_key, final_price, status)
VALUES
    (1, 1, 1, 1, '2025-11-01 10:00:00', '2025-11-03 10:00:00', '김관리', '010-1234-5678', '2종 보통', 'full_short', 150000, 'COMPLETED');

-- 예약 2: 이유저(user_id=2)가 부산에서 쏘렌토(car_id=5) 예약 (진행중)
INSERT INTO reservation
(user_id, car_id, pickup_zone_id, return_zone_id, start_date, end_date, driver_name, driver_phone, license_kind, insurance_policy_key, final_price, status)
VALUES
    (2, 5, 2, 2, '2025-12-05 09:00:00', '2025-12-07 18:00:00', '이유저', '010-9876-5432', '1종 보통', 'basic_short', 250000, 'CONFIRMED');

-- 예약 3: 최장기(user_id=4)가 제주에서 아이오닉(car_id=10) 한 달 예약 (장기 보험 적용)
INSERT INTO reservation
(user_id, car_id, pickup_zone_id, return_zone_id, start_date, end_date, driver_name, driver_phone, license_kind, insurance_policy_key, final_price, status)
VALUES
    (4, 10, 3, 3, '2025-12-01 10:00:00', '2025-12-31 10:00:00', '최장기', '010-5555-7777', '2종 보통', 'long_term_care', 1800000, 'CONFIRMED');