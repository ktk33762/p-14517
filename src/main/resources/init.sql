-- DROP DATABASE IF EXISTS dev_db;
-- CREATE DATABASE dev_db;
-- USE dev_db;

CREATE TABLE post(
                     id INT UNSIGNED NOT NULL AUTO_INCREMENT,
                     createDate DATETIME NOT NULL,
                     modifyDate DATETIME NOT NULL,
                     title CHAR(100) NOT NULL,
                     content TEXT NOT NULL,
                     PRIMARY KEY(id)
);

INSERT INTO post
SET createDate = now(),
modifyDate = now(),
title = '제목 1',
content = '내용 1';

INSERT INTO post
SET createDate = now(),
modifyDate = now(),
title = '제목 2',
content = '내용 2';