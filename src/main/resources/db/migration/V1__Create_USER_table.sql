-- auto-generated definition
create table USER
(
    ID           INTEGER auto_increment primary key not null,
    ACCOUNT_ID   VARCHAR(255),
    USERNAME     VARCHAR(50),
    TOKEN        CHAR(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
);

