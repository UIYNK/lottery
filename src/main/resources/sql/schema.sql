CREATE TABLE LotteryEvents
(
    ID          BIGINT AUTO_INCREMENT PRIMARY KEY,
    CreatedAt   TIMESTAMP           NOT NULL,
    Title       NVARCHAR(50) UNIQUE NOT NULL,
    Description NVARCHAR(255)
);

CREATE TABLE LotteryPrizes
(
    ID          BIGINT AUTO_INCREMENT PRIMARY KEY,
    CreatedAt   TIMESTAMP    NOT NULL,
    Title       NVARCHAR(50) NOT NULL,
    Description NVARCHAR(255),
    EventID     BIGINT,
    FOREIGN KEY (EventID) REFERENCES LotteryEvents (ID)
);

CREATE TABLE LotteryTickets
(
    ID           BIGINT AUTO_INCREMENT PRIMARY KEY,
    CreatedAt    TIMESTAMP     NOT NULL,
    SerialNumber BIGINT UNIQUE NOT NULL,
    EventID      BIGINT,
    FOREIGN KEY (EventID) REFERENCES LotteryEvents (ID)
);

CREATE TABLE LotteryPicks
(
    ID        BIGINT AUTO_INCREMENT PRIMARY KEY,
    CreatedAt TIMESTAMP NOT NULL,
    TicketID  BIGINT UNIQUE,
    PrizeID   BIGINT UNIQUE,
    FOREIGN KEY (TicketID) REFERENCES LotteryTickets (ID),
    FOREIGN KEY (PrizeID) REFERENCES LotteryPrizes (ID)
);
