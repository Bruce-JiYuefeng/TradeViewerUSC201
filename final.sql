CREATE DATABASE trade_journal;
USE trade_journal;


CREATE TABLE users (
    user_id BIGINT NOT NULL AUTO_INCREMENT, 
    name VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY(user_id)
);




