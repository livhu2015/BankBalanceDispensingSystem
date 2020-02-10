create database demo_db;

use demo_db;

CREATE TABLE demo_db.client_type (
  `client_type_code` varchar(4) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`client_type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE demo_db.`client_sub_type` (
    `client_sub_type_code` varchar(4) NOT NULL,
                                   `client_type_code` varchar(2) NOT NULL COMMENT 'client_type_code - FK',
                                   `description` varchar(255) NOT NULL,
                                   PRIMARY KEY (`client_sub_type_code`),
                                   KEY `client_type_code_idx` (`client_type_code`),
                                   CONSTRAINT `client_type_code` FOREIGN KEY (`client_type_code`)
                                       REFERENCES demo_db.`client_type` (`client_type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE demo_db.`client` (
                          `client_id` int NOT NULL,
                          `title` varchar(10) DEFAULT NULL,
                          `name` varchar(255) NOT NULL,
                          `surname` varchar(100) DEFAULT NULL,
                          `dob` datetime(6) NOT NULL,
                          `client_sub_type_code` varchar(4) NOT NULL,
                          PRIMARY KEY (`client_id`),
                          KEY `client_sub_type_code_idx` (`client_sub_type_code`),
                          CONSTRAINT `client_sub_type_code` FOREIGN KEY (`client_sub_type_code`) REFERENCES demo_db.`client_sub_type` (`client_sub_type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE demo_db.`account_type` (
                                `account_type_code` varchar(10) NOT NULL,
                                `description` varchar(50) NOT NULL,
                                `transactional` tinyint NOT NULL,
                                PRIMARY KEY (`account_type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE demo_db.`client_account` (
                                  `account_number` varchar(10) NOT NULL,
                                  `client_id` int NOT NULL COMMENT 'Client id - FK	',
                                  `display_balance` decimal(18,3) NOT NULL,
                                  `account_type_code` varchar(10) NOT NULL COMMENT 'account_type_code - FK',
                                  `currency_code` varchar(3) NOT NULL COMMENT 'currency_code - FK',
                                  PRIMARY KEY (`account_number`),
                                  KEY `account_type_code_idx` (`account_type_code`),
                                  KEY `client_id_idx` (`client_id`),
                                  KEY `currency_code_idx` (`currency_code`),
                                  CONSTRAINT `account_type_code` FOREIGN KEY (`account_type_code`) REFERENCES demo_db.`account_type` (`account_type_code`),
                                  CONSTRAINT `client_id` FOREIGN KEY (`client_id`) REFERENCES demo_db.`client` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE demo_db.`credit_card_limit` (
                                     `client_account_number` varchar(10) NOT NULL,
                                     `account_limit` decimal(18,3) NOT NULL,
                                     PRIMARY KEY (`client_account_number`),
                                     CONSTRAINT `client_account_number` FOREIGN KEY (`client_account_number`) REFERENCES demo_db.`client_account` (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE demo_db.currency (
  `currency_code` varchar(3) NOT NULL,
  `decimal_places` decimal(10,0) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`currency_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE demo_db.currency_conversion_rate (
  `currency_code` varchar(3) NOT NULL,
  `currency_conversion` varchar(1) NOT NULL,
  `rate` decimal(18,8) NOT NULL,
  PRIMARY KEY (`currency_code`),
  CONSTRAINT `currency_code` FOREIGN KEY (`currency_code`) REFERENCES demo_db.`currency` (`currency_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE demo_db.`currency_conversion_rate` (
                                            `currency_code` varchar(3) NOT NULL,
                                            `currency_conversion` varchar(1) NOT NULL,
                                            `rate` decimal(18,8) NOT NULL,
                                            PRIMARY KEY (`currency_code`),
                                            CONSTRAINT `currency_code` FOREIGN KEY (`currency_code`) REFERENCES demo_db.`currency` (`currency_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE demo_db.`atm` (
                       `atm_id` int NOT NULL,
                       `name` varchar(10) NOT NULL,
                       `location` varchar(255) NOT NULL,
                       PRIMARY KEY (`atm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE demo_db.`denomination_type` (
                                     `denomination_type_code` varchar(1) NOT NULL,
                                     `description` varchar(255) NOT NULL,
                                     PRIMARY KEY (`denomination_type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE demo_db.`denomination` (
                                `denomination_id` int NOT NULL,
                                `value` decimal(10,0) NOT NULL,
                                `denomination_type_code` varchar(1) DEFAULT NULL,
                                PRIMARY KEY (`denomination_id`),
                                KEY `denomination_type_code_idx` (`denomination_type_code`),
                                CONSTRAINT `denomination_type_code` FOREIGN KEY (`denomination_type_code`) REFERENCES demo_db.`denomination_type` (`denomination_type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE demo_db.`atm_allocation` (
                                  `atm_allocation_id` int NOT NULL,
                                  `atm_id` int NOT NULL,
                                  `denomination_id` int NOT NULL,
                                  `count` int NOT NULL,
                                  PRIMARY KEY (`atm_allocation_id`),
                                  KEY `denomination_id_idx` (`denomination_id`),
                                  KEY `atm_id_idx` (`atm_id`),
                                  CONSTRAINT `atm_id` FOREIGN KEY (`atm_id`) REFERENCES demo_db.`atm` (`atm_id`),
                                  CONSTRAINT `denomination_id` FOREIGN KEY (`denomination_id`) REFERENCES demo_db.`denomination` (`denomination_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
