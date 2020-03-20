CREATE TABLE user
(
    id                 BIGINT AUTO_INCREMENT COMMENT 'id'
        PRIMARY KEY,
    name               VARCHAR(20)                               NULL COMMENT '姓名',
    age                INT                                       NULL COMMENT '年龄，多行注释
第一行注释，
第二行注释，
第三行注释，
第四行注释',
    mobile             VARCHAR(11)                               NULL COMMENT '联系方式',
    sex                ENUM ('男', '女')                           NULL COMMENT '性别',
    create_date        TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) NOT NULL COMMENT '创建时间',
    last_modified_date TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) NOT NULL COMMENT '最后修改时间',
    version            INT          DEFAULT 1                    NOT NULL COMMENT '版本号'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci COMMENT ='用户，多行注释
第一行注释，
第二行注释，
第三行注释，
第四行注释';

INSERT INTO user (name, age, mobile, sex)
VALUES ('小1', 24, '13092235311', '男'),
       ('小2', 38, '13075607401', '男'),
       ('小3', 21, '13006422851', '女'),
       ('小4', 22, '13041567582', '女'),
       ('小5', 34, '13031978439', '男'),
       ('小6', 29, '13033482141', '女'),
       ('小7', 25, '13048354758', '男'),
       ('小8', 30, '13017401179', '女'),
       ('小9', 25, '13095429605', '女');