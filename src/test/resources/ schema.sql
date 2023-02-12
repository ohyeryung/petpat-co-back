-- ------------------------------------------------------------
-- TB_USER
-- ------------------------------------------------------------
create table TB_USER
(
    USER_ID          bigint auto_increment primary key,
    LOCATION         varchar(255) not null,
    NICKNAME         varchar(255) not null,
    PASSWORD         varchar(255) not null,
    PROFILE_IMG_PATH varchar(255) null,
    USER_EMAIL       varchar(255) not null
);
-- ------------------------------------------------------------
-- CATEGORY
-- ------------------------------------------------------------
create table tb_category_group
(
    CATEGORY_GROUP_ID   bigint       not null  primary key,
    CATEGORY_GROUP_NAME varchar(255) not null,
    POST_TYPE           varchar(255) not null
);

create table petpat.tb_pet_category
(
    PET_CATEGORY_ID   bigint       not null primary key,
    PET_CATEGORY_NAME varchar(255) not null,
    CATEGORY_GROUP_ID bigint       not null,
    constraint FK319s7poc288s9uvswes14brnc
        foreign key (CATEGORY_GROUP_ID) references petpat.tb_category_group (CATEGORY_GROUP_ID)
);

create table petpat.tb_trade_category
(
    TRADE_CATEGORY_ID   bigint auto_increment primary key,
    TRADE_CATEGORY_NAME varchar(255) not null,
    CATEGORY_GROUP_ID   bigint       not null,
    constraint FKipc8i1x4cn9ks8gcueedaimfj
        foreign key (CATEGORY_GROUP_ID) references petpat.tb_category_group (CATEGORY_GROUP_ID)
);


create table tb_trade_category_detail
(
    TRADE_CATEGORY_DETAIL_ID   bigint auto_increment primary key,
    TRADE_CATEGORY_DETAIL_NAME varchar(255) not null,
    TRADE_CATEGORY_ID          bigint       not null,
    constraint FKpxkkm611jgm543oqc83hipipa
        foreign key (TRADE_CATEGORY_ID) references petpat.tb_trade_category (TRADE_CATEGORY_ID)
);


-- ------------------------------------------------------------
-- IMAGE
-- ------------------------------------------------------------

create table petpat.tb_image
(
    IMAGE_ID           bigint auto_increment primary key,
    FAKE_FILE_NAME     varchar(255) not null,
    FILE_PATH          varchar(255) not null,
    ORIGINAL_FILE_NAME varchar(255) not null,
    POST_ID            bigint       not null,
    POST_TYPE          varchar(255) not null
);

-- ------------------------------------------------------------
-- POST
-- ------------------------------------------------------------
create table petpat.tb_rehoming
(
    REHOMING_ID       bigint auto_increment primary key,
    CREATED_AT        datetime(6)  null,
    UPDATED_AT        datetime(6)  null,
    CITY_COUNTRY_NAME varchar(255) not null,
    CITY_NAME         varchar(255) not null,
    DESCRIPTION       text         not null,
    DETAIL_AD_NAME    varchar(255) not null,
    FULL_AD_NAME      varchar(255) not null,
    GENDER            varchar(255) not null,
    PET_AGE           varchar(255) not null,
    PET_NAME          varchar(255) not null,
    POST_TYPE         varchar(255) null,
    PRICE             bigint       null,
    STATUS            varchar(255) null,
    TITLE             varchar(20)  not null,
    TOWNSHIP_NAME     varchar(255) not null,
    VIEW_CNT          int          null,
    CATEGORY_GROUP_ID bigint       not null,
    PET_CATEGORY_ID   bigint       not null,
    USER_ID           bigint       null,
    constraint FK2svcx12lc0815eo9yc7wo96of
        foreign key (CATEGORY_GROUP_ID) references petpat.tb_category_group (CATEGORY_GROUP_ID),
    constraint FKdijewdsyyqprsgch7968f3i5t
        foreign key (PET_CATEGORY_ID) references petpat.tb_pet_category (PET_CATEGORY_ID),
    constraint FKfi64mt1y5psuvbnmmp8x4qg12
        foreign key (USER_ID) references petpat.tb_user (USER_ID)
);

create table petpat.tb_trade
(
    TRADE_ID              bigint auto_increment primary key,
    CREATED_AT            datetime(6)   null,
    UPDATED_AT            datetime(6)   null,
    CONTENT               varchar(2000) not null,
    LOCATION              varchar(255)  not null,
    POST_TYPE             varchar(255)  not null,
    PRICE                 bigint        not null,
    STATUS                varchar(255)  null,
    TITLE                 varchar(20)   not null,
    VIEW_CNT              int           not null,
    TRADE_CATEGORY_DETAIL bigint        not null,
    USER_ID               bigint        null,
    constraint FK2odyv9pvrlfdtsgbk7y25r3i0
        foreign key (TRADE_CATEGORY_DETAIL) references petpat.tb_trade_category_detail (TRADE_CATEGORY_DETAIL_ID),
    constraint FKf2uppbcb846mnkig1gnk0xbwn
        foreign key (USER_ID) references petpat.tb_user (USER_ID)
);

-- ------------------------------------------------------------
-- LIKES
-- ------------------------------------------------------------

create table petpat.tb_likes
(
    LIKE_ID   bigint auto_increment primary key,
    POST_ID   bigint       null,
    POST_TYPE varchar(255) null,
    USER_ID   bigint       null,
    constraint FKp3q7axesgcgcu135n66ljun0n
        foreign key (USER_ID) references petpat.tb_user (USER_ID)
);
-- ------------------------------------------------------------
-- BOOKMARK
-- ------------------------------------------------------------

create table petpat.tb_bookmark
(
    BOOKMARK_ID bigint auto_increment primary key,
    POST_ID     bigint       null,
    POST_TYPE   varchar(255) null,
    USER_ID     bigint       null,
    constraint FKs4t09i44l6nxbs8t95ahvxxxa
        foreign key (USER_ID) references petpat.tb_user (USER_ID)
);

-- ------------------------------------------------------------
-- [index]
-- ------------------------------------------------------------
-- [CATEGORY]
create index trade_category_name_index   on petpat.tb_trade_category (TRADE_CATEGORY_NAME);
create index trade_category_detail_index on petpat.tb_trade_category_detail (TRADE_CATEGORY_DETAIL_NAME);
