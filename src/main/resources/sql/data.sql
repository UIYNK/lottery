INSERT INTO LotteryEvents (CreatedAt, Title, Description)
VALUES (CURRENT_TIMESTAMP, 'قرعه‌کشی سال نو', 'رویداد ویژه سال نو');

INSERT INTO LotteryPrizes (CreatedAt, Title, Description, EventID)
VALUES (CURRENT_TIMESTAMP, 'خودرو', 'پراید', 1);
INSERT INTO LotteryPrizes (CreatedAt, Title, Description, EventID)
VALUES (CURRENT_TIMESTAMP, 'لپ‌تاپ', 'مک بوک', 1);
INSERT INTO LotteryPrizes (CreatedAt, Title, Description, EventID)
VALUES (CURRENT_TIMESTAMP, 'گوشی هوشمند', 'سامسونگ', 1);


INSERT INTO LotteryTickets (CreatedAt, SerialNumber, EventID)
VALUES (CURRENT_TIMESTAMP, 'SN-001', 1);
INSERT INTO LotteryTickets (CreatedAt, SerialNumber, EventID)
VALUES (CURRENT_TIMESTAMP, 'SN-002', 1);
INSERT INTO LotteryTickets (CreatedAt, SerialNumber, EventID)
VALUES (CURRENT_TIMESTAMP, 'SN-003', 1);


INSERT INTO LotteryPicks (CreatedAt, TicketID, PrizeID)
VALUES (CURRENT_TIMESTAMP, 1, 1);
INSERT INTO LotteryPicks (CreatedAt, TicketID, PrizeID)
VALUES (CURRENT_TIMESTAMP, 2, 2);
INSERT INTO LotteryPicks (CreatedAt, TicketID, PrizeID)
VALUES (CURRENT_TIMESTAMP, 3, 3);