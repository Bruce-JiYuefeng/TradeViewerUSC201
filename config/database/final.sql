DROP DATABASE IF EXISTS trade_journal;
CREATE DATABASE IF NOT EXISTS trade_journal;
USE trade_journal;

# instruments are another word for financial market securities. stocks are instruments. 
CREATE TABLE IF NOT EXISTS instruments (
    instrument_id INT NOT NULL AUTO_INCREMENT, 
    instrument_name VARCHAR(50) NOT NULL,
    PRIMARY KEY(instrument_id)
);

INSERT INTO instruments (instrument_id, instrument_name) VALUES
(1, 'spy'),
(2, 'qqq'),
(3, 'nvda'),
(4, 'amzn'),
(5, 'goog'),
(6, 'aapl'),
(7, 'msft'),
(8, 'tsla'),
(9, 'meta'),
(10, 'brk.b'),
(11, 'v'),
(12, 'jnj');

CREATE TABLE IF NOT EXISTS users (
    user_id BIGINT NOT NULL AUTO_INCREMENT, 
    name VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY(user_id)
);

INSERT INTO users (user_id, name, password) VALUES
(1, 'test', '123abc'),
(2, 'mrNoTrades', '123abc');

CREATE TABLE IF NOT EXISTS trades (
    trade_id BIGINT NOT NULL AUTO_INCREMENT, 
    instrument_id INT NOT NULL,
    user_id BIGINT NOT NULL,
    date_time DATETIME NOT NULL,	
    buy_sell VARCHAR(4) NOT NULL,
    quantity DECIMAL(8,2) NOT NULL,
    price DECIMAL(12, 2) NOT NULL,
    price_out DECIMAL(8, 2) NOT NULL,
    pnl DECIMAL(15, 2),
    PRIMARY KEY(trade_id),
    FOREIGN KEY (instrument_id) REFERENCES instruments(instrument_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

INSERT INTO trades (instrument_id, user_id, date_time, buy_sell, quantity, price, price_out, pnl) VALUES
(1, 1,'2024-12-11 10:15:00', 'sell', 100.00, 300.00, 299.00, 100.00),
(2, 1,'2024-12-11 10:30:00', 'buy', 50.00, 200.00, 198.00, -100.00),
(1, 1,'2024-12-11 11:00:00', 'buy', 120.00, 295.00, 296.00, 120.00),
(3, 1,'2024-12-11 11:15:00', 'sell', 200.00, 400.00, 405.00, 500.00),
(1, 1,'2024-12-11 12:00:00', 'sell', 50.00, 305.00, 303.00, -100.00);
