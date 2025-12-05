/* * table.sql - 렌터카 프로젝트 DB 스키마 정의
 * (보험 할인 정책 insurance_policies 추가 및 학습용 주석 완비 버전)
 */

-- 0. 초기화: 기존 테이블 삭제 (순서: 자식 -> 부모)
-- 에러 방지를 위해 외래키(FK)를 참조하고 있는 자식 테이블부터 순서대로 지웁니다.
DROP TABLE IF EXISTS rental_price_applied_log;  -- 로그
DROP TABLE IF EXISTS pricing_policy_mappings;   -- 정책 매핑
DROP TABLE IF EXISTS pricing_policies;          -- 가격 정책
DROP TABLE IF EXISTS rental_prices;             -- 기본 가격
DROP TABLE IF EXISTS reservation_history;       -- 예약 백업
DROP TABLE IF EXISTS reservation;               -- 예약
DROP TABLE IF EXISTS insurance_policies;        -- [NEW] 보험 정책
DROP TABLE IF EXISTS car;                       -- 차량
DROP TABLE IF EXISTS model;                     -- 모델
DROP TABLE IF EXISTS brand;                     -- 브랜드
DROP TABLE IF EXISTS pickup_zone;               -- 픽업존
DROP TABLE IF EXISTS branch;                    -- 지점
DROP TABLE IF EXISTS users;                     -- 회원

-- 1. 회원 테이블 (Users)
CREATE TABLE users (
                       user_id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- 회원 고유 ID (PK)
                       username VARCHAR(50) NOT NULL,              -- 회원 이름 (필수 입력)
                       email VARCHAR(100),                         -- 이메일 (로그인 ID 겸용)
                       created_at DATETIME DEFAULT CURRENT_TIMESTAMP -- 가입 일시
);

-- 2. 지점 정보 (Branch)
CREATE TABLE branch (
                        branch_id BIGINT AUTO_INCREMENT PRIMARY KEY, -- 지점 고유 ID (PK)
                        name VARCHAR(50),                            -- 지점명 (예: 서울 강남점)
                        region_depth1 VARCHAR(20),                   -- 지역 대분류 (서울, 경기, 제주 등)
                        branch_type VARCHAR(20),                     -- 지점 타입 (공항, 기차역, 터미널 등)
                        address VARCHAR(255),                        -- 상세 주소
                        latitude DECIMAL(10, 7),                     -- 위도 (지도 표시용)
                        longitude DECIMAL(10, 7),                    -- 경도 (지도 표시용)
                        phone VARCHAR(20),                           -- 지점 전화번호
                        open_hours VARCHAR(100),                     -- 운영 시간 (예: 09:00~22:00)
                        is_active TINYINT(1) DEFAULT 1               -- 운영 여부 (1:운영, 0:종료)
);

-- 3. 픽업존 (Pickup Zone)
CREATE TABLE pickup_zone (
                             zone_id BIGINT AUTO_INCREMENT PRIMARY KEY,   -- 픽업존 고유 ID (PK)
                             branch_id BIGINT,                            -- 소속 지점 ID (FK)
                             name VARCHAR(50),                            -- 구역 이름 (예: 지하1층 A-3)
                             latitude DECIMAL(10, 7),                     -- 픽업존 상세 위도
                             longitude DECIMAL(10, 7),                    -- 픽업존 상세 경도
                             guide_info TEXT,                             -- 찾아오는 길 안내 멘트
                             landmark VARCHAR(100),                       -- 주변 랜드마크
                             is_active TINYINT(1) DEFAULT 1,              -- 사용 가능 여부
                             FOREIGN KEY (branch_id) REFERENCES branch(branch_id)
);

-- 4. 차량 브랜드 (Brand)
CREATE TABLE brand (
                       brand_id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- 브랜드 ID (PK)
                       brand_name VARCHAR(100),                     -- 브랜드명 (Hyundai, Kia 등)
                       origin_country VARCHAR(100)                  -- 제조국 (Korea, Germany 등)
);

-- 5. 차량 모델 (Model)
CREATE TABLE model (
                       model_id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- 모델 ID (PK)
                       brand_id BIGINT,                             -- 제조사 ID (FK)
                       model_name VARCHAR(100),                     -- 모델명 (아반떼, 쏘렌토 등)
                       fuel_type VARCHAR(50),                       -- 연료 (휘발유, 디젤, 전기)
                       segment VARCHAR(50),                         -- 차급 (경차, 세단, SUV)
                       passenger_limit INT,                         -- 승차 인원
                       transmission VARCHAR(20),                    -- 변속기 (자동, 수동)
                       FOREIGN KEY (brand_id) REFERENCES brand(brand_id)
);

-- 6. 개별 차량 (Car)
CREATE TABLE car (
                     car_id BIGINT AUTO_INCREMENT PRIMARY KEY,    -- 차량 고유 ID (PK)
                     model_id BIGINT,                             -- 모델 ID (FK)
                     zone_id BIGINT,                              -- 현재 위치한 픽업존 ID (FK)
                     car_number VARCHAR(20),                      -- 차량 번호판
                     year INT,                                    -- 연식
                     color VARCHAR(20),                           -- 색상
                     mileage INT,                                 -- 주행 거리 (km)
                     status ENUM('AVAILABLE', 'RENTED', 'MAINTENANCE') DEFAULT 'AVAILABLE', -- 상태
                     description TEXT,                            -- 차량 특이사항 설명
                     FOREIGN KEY (model_id) REFERENCES model(model_id),
                     FOREIGN KEY (zone_id) REFERENCES pickup_zone(zone_id)
);

-- 7. [NEW] 보험 정책 (Insurance Policies) - 요청하신 할인 기능 구현
CREATE TABLE insurance_policies (
                                    policy_key VARCHAR(50) PRIMARY KEY,          -- 보험 코드 (PK, 예: 'basic', 'full')
                                    name VARCHAR(100),                           -- 표시 이름 (예: 일반 자차, 완전 자차)
                                    daily_fee DECIMAL(15, 0),                    -- 1일당 보험료 (예: 10,000원)
                                    rent_discount_rate DECIMAL(5, 2),            -- [요청] 렌트료 할인율 (예: 0.1 = 10% 할인)
                                    description TEXT,                            -- 보장 내용 설명
                                    is_active TINYINT(1) DEFAULT 1               -- 활성화 여부
);

-- 8. 가격 정책 (Rental Prices)
CREATE TABLE rental_prices (
                               id VARCHAR(50) PRIMARY KEY,                  -- 가격표 ID (UUID 등 문자열)
                               car_id BIGINT,                               -- 대상 차량 ID (FK)
                               daily_price DECIMAL(15, 2),                  -- 1일(24시간) 표준 요금
                               hourly_price DECIMAL(15, 2),                 -- 시간당 요금 (단기 대여용)
                               monthly_price DECIMAL(15, 2),                -- [NEW] 월 장기 렌트 요금 (별도 책정값)
                               weekend_price DECIMAL(15, 2),                -- 주말 할증 요금
                               currency VARCHAR(10) DEFAULT 'KRW',          -- 통화 (기본값: 원)
                               start_date DATE,                             -- 가격 적용 시작일
                               end_date DATE,                               -- 가격 적용 종료일
                               created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (car_id) REFERENCES car(car_id)
);

-- 9. 할인/할증 정책 (Policies)
CREATE TABLE pricing_policies (
                                  id VARCHAR(50) PRIMARY KEY,                  -- 정책 ID
                                  name VARCHAR(100),                           -- 정책명 (예: 여름 성수기)
                                  type VARCHAR(50),                            -- 타입 (주말, 시즌, 이벤트 등)
                                  start_date DATE,                             -- 정책 시작일
                                  end_date DATE,                               -- 정책 종료일
                                  multiplier DECIMAL(5, 2),                    -- 할증/할인율 (1.2 = 20% 인상)
                                  description TEXT,                            -- 정책 설명
                                  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 10. 정책 매핑 (Mappings)
CREATE TABLE pricing_policy_mappings (
                                         id VARCHAR(50) PRIMARY KEY,                  -- 매핑 ID
                                         pricing_policy_id VARCHAR(50),               -- 정책 ID (FK)
                                         car_id BIGINT,                               -- 차량 ID (FK)
                                         priority INT,                                -- 우선순위 (높을수록 먼저 적용)
                                         created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                         FOREIGN KEY (pricing_policy_id) REFERENCES pricing_policies(id),
                                         FOREIGN KEY (car_id) REFERENCES car(car_id)
);

-- 11. 예약 (Reservation)
CREATE TABLE reservation (
                             reservation_id BIGINT AUTO_INCREMENT PRIMARY KEY, -- 예약 ID (PK)
                             user_id BIGINT,                              -- 회원 ID (FK)
                             car_id BIGINT,                               -- 차량 ID (FK)
                             pickup_zone_id BIGINT,                       -- 대여 장소 ID (FK)
                             return_zone_id BIGINT,                       -- 반납 장소 ID (FK)
                             start_date DATETIME,                         -- 대여 시작 시간
                             end_date DATETIME,                           -- 반납 예정 시간

    -- 운전자 및 옵션
                             driver_name VARCHAR(50),                     -- 운전자명
                             driver_phone VARCHAR(20),                    -- 운전자 연락처
                             driver_birth DATE,                           -- 생년월일
                             license_kind VARCHAR(50),                    -- 면허 종류
                             license_number VARCHAR(50),                  -- 면허 번호

    -- [수정] 보험 정보를 단순 글자가 아닌 정책 테이블 ID로 연결
                             insurance_policy_key VARCHAR(50),            -- 선택한 보험 정책 (FK)

                             has_navi TINYINT(1) DEFAULT 1,               -- 내비게이션 여부
                             has_baby_seat TINYINT(1) DEFAULT 0,          -- 카시트 여부
                             has_dashcam TINYINT(1) DEFAULT 1,            -- 블랙박스 여부

    -- 금액 및 상태
                             estimated_price DECIMAL(15, 0),              -- 예상 금액
                             final_price DECIMAL(15, 0),                  -- 최종 결제 금액
                             status ENUM('PENDING', 'CONFIRMED', 'CANCELED', 'COMPLETED') DEFAULT 'PENDING', -- 예약 상태
                             payment_status VARCHAR(50) DEFAULT 'unpaid', -- 결제 상태
                             payment_method VARCHAR(50),                  -- 결제 수단
                             created_at DATETIME DEFAULT CURRENT_TIMESTAMP,

                             FOREIGN KEY (user_id) REFERENCES users(user_id),
                             FOREIGN KEY (car_id) REFERENCES car(car_id),
                             FOREIGN KEY (pickup_zone_id) REFERENCES pickup_zone(zone_id),
                             FOREIGN KEY (return_zone_id) REFERENCES pickup_zone(zone_id),
                             FOREIGN KEY (insurance_policy_key) REFERENCES insurance_policies(policy_key) -- 보험 테이블과 연결
);

-- 12. 가격 계산 로그 (Log)
CREATE TABLE rental_price_applied_log (
                                          id VARCHAR(50) PRIMARY KEY,                  -- 로그 ID
                                          reservation_id BIGINT,                       -- 예약 ID (FK)
                                          car_id BIGINT,                               -- 차량 ID
                                          base_price DECIMAL(15, 0),                   -- 적용된 기본가
                                          final_price DECIMAL(15, 0),                  -- 계산된 최종가
                                          applied_policies JSON,                       -- 적용된 정책 내역 (JSON)
                                          created_at DATETIME DEFAULT CURRENT_TIMESTAMP,

    -- 예약 삭제 시 로그도 함께 삭제 (데이터 무결성 유지)
                                          FOREIGN KEY (reservation_id) REFERENCES reservation(reservation_id) ON DELETE CASCADE
);

-- 13. 예약 히스토리 (History) - 백업용
CREATE TABLE reservation_history (
                                     history_id BIGINT AUTO_INCREMENT PRIMARY KEY, -- 히스토리 ID (PK)
                                     reservation_id BIGINT,                       -- 원본 예약 ID
                                     user_id BIGINT,                              -- 회원 ID
                                     car_id BIGINT,                               -- 차량 ID
                                     start_date DATETIME,                         -- 대여 시간
                                     end_date DATETIME,                           -- 반납 시간
                                     status VARCHAR(50),                          -- 당시 상태
                                     final_price DECIMAL(15, 0),                  -- 최종 금액
                                     origin_created_at DATETIME,                  -- 원본 생성일
                                     backup_at DATETIME DEFAULT CURRENT_TIMESTAMP -- 백업 일시
);