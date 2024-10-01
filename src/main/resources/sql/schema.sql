CREATE TABLE LotteryEvents
(
    ID          BIGINT PRIMARY KEY AUTO_INCREMENT,
    Title       VARCHAR(255) UNIQUE NOT NULL,
    Description VARCHAR(255),
    CreatedAt   TIMESTAMP           NOT NULL
);

-- Table: LotteryPrizes
CREATE TABLE LotteryPrizes
(
    ID          BIGINT PRIMARY KEY AUTO_INCREMENT,
    Title       VARCHAR(255) NOT NULL,
    Number      INT          NOT NULL,
    Priority    INT          NOT NULL,
    Description VARCHAR(255),
    EventID     BIGINT       NOT NULL,
    CreatedAt   TIMESTAMP    NOT NULL,
    UNIQUE (Title, EventID),
    UNIQUE (Priority, EventID),
    FOREIGN KEY (EventID) REFERENCES LotteryEvents (ID)
);

-- Table: LotteryTickets
CREATE TABLE LotteryTickets
(
    ID           BIGINT PRIMARY KEY AUTO_INCREMENT,
    SerialNumber BIGINT    NOT NULL UNIQUE,
    EventID      BIGINT    NOT NULL,
    CreatedAt    TIMESTAMP NOT NULL,
    RandomIndex  UUID UNIQUE,
    FOREIGN KEY (EventID) REFERENCES LotteryEvents (ID)
);

-- Table: LotteryPicks
CREATE TABLE LotteryPicks
(
    ID        BIGINT PRIMARY KEY AUTO_INCREMENT,
    TicketID  BIGINT    NOT NULL UNIQUE,
    PrizeID   BIGINT    NOT NULL,
    CreatedAt TIMESTAMP NOT NULL,
    FOREIGN KEY (TicketID) REFERENCES LotteryTickets (ID),
    FOREIGN KEY (PrizeID) REFERENCES LotteryPrizes (ID)
);
