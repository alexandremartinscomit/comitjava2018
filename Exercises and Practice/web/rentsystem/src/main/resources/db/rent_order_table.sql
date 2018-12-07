CREATE TABLE `rentsystemclass`.`rent_orders` (
  `rent_order_id` VARCHAR(50) NOT NULL,
  `car_id` VARCHAR(45) NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `expected_delivery_date` BIGINT NULL,
  `rent_date` BIGINT NULL,
  `price` FLOAT NULL,
  `observations` TEXT NULL,
  `free_upgrade` TINYINT NULL,
  PRIMARY KEY (`rent_order_id`));
