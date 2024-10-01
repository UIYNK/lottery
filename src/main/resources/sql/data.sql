INSERT INTO LOTTERYEVENTS (CREATEDAT, DESCRIPTION, TITLE)
VALUES (CURRENT_TIMESTAMP, 'رویداد ویژه هفته انتظامی', 'قرعه کشی راهپیمایی');

INSERT INTO LotteryPrizes (CreatedAt, NUMBER, PRIORITY, Title, EventID)
VALUES (CURRENT_TIMESTAMP, 1, 10, 'خودرو', 1);

INSERT INTO LotteryPrizes (CreatedAt, NUMBER, PRIORITY, Title, EventID)
VALUES (CURRENT_TIMESTAMP, 130, 9, 'دوچرخه', 1);

INSERT INTO LotteryPrizes (CreatedAt, NUMBER, PRIORITY, Title, EventID)
VALUES (CURRENT_TIMESTAMP, 300, 8, 'بن خرید', 1);


INSERT INTO LotteryPrizes (CreatedAt, NUMBER, PRIORITY, Title, EventID)
VALUES (CURRENT_TIMESTAMP, 80, 7, 'اقلام مصرفی', 1);