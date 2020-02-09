drop table if exists demo;

# 1. Client Type
CREATE TABLE client_type (client_type_code varchar(4) NOT NULL, description varchar(255) NOT NULL, PRIMARY KEY (client_type_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO client_type (client_type_code, description)
VALUES ('MC','Middle class');



# CREATE TABLE `credit_card_limit` (`client_account_number` varchar(10) NOT NULL,`account_limit` decimal(18,3) NOT NULL,
#                                      PRIMARY KEY (`client_account_number`),
#                                      CONSTRAINT `client_account_number` FOREIGN KEY (`client_account_number`) REFERENCES `client_account` (`account_number`)
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
#
#
# CREATE TABLE account_type (
#   account_type_code varchar(10) NOT NULL,
#   description varchar(50) NOT NULL,
#   transactional tinyint NOT NULL,
#   PRIMARY KEY (account_type_code)
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
#
# INSERT INTO account_type (account_type_code, description, transactional)
# VALUES ('CA' , 'Cheque Account', '1'), ('SA' , 'Savings Account', '1'),
#        ('CA' , 'Credit Card', '1'), ('HL' , 'Home Loan', '0'),
#        ('PL' , 'Personal Loan', '0'), ('VAF' , 'Vehicle Finance', '0');
