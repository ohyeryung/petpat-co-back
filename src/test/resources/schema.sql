-- TB_ADDRESS 테이블 생성문 추가
CREATE TABLE IF NOT EXISTS TB_ADDRESS (
    ADDRESS_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    PROVINCE VARCHAR(255),
    CITY VARCHAR(255),
    DISTRICT VARCHAR(255),
    TOWN VARCHAR(255)
    );

-- TB_CATEGORY_GROUP 생성문 추가
CREATE TABLE IF NOT EXISTS TB_CATEGORY_GROUP (
    CATEGORY_GROUP_ID INT PRIMARY KEY,
    CATEGORY_GROUP_NAME VARCHAR(255),
    POST_TYPE VARCHAR(255)
    );

-- TB_PET_CATEGORY 생성문 추가
CREATE TABLE IF NOT EXISTS TB_PET_CATEGORY (
    PET_CATEGORY_ID INT PRIMARY KEY,
    PET_CATEGORY_NAME VARCHAR(255),
    CATEGORY_GROUP_ID BIGINT
    );

-- TB_TRADE_CATEGORY 생성문 추가
CREATE TABLE IF NOT EXISTS TB_TRADE_CATEGORY (
    TRADE_CATEGORY_ID INT PRIMARY KEY,
    TRADE_CATEGORY_NAME VARCHAR(255),
    CATEGORY_GROUP_ID BIGINT
    );

-- TB_TRADE_CATEGORY_DETAIL 생성문 추가
CREATE TABLE IF NOT EXISTS TB_TRADE_CATEGORY_DETAIL (
    TRADE_CATEGORY_DETAIL_ID INT PRIMARY KEY,
    TRADE_CATEGORY_DETAIL_NAME VARCHAR(255),
    TRADE_CATEGORY_ID BIGINT
    );