CREATE SCHEMA IF NOT EXISTS `hotel` DEFAULT CHARACTER SET cp1251 ;
USE `hotel` ;

-- -----------------------------------------------------
-- Table `hotel`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`client` (
  `client_id` INT NOT NULL AUTO_INCREMENT,
  `client_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE INDEX `id_UNIQUE` (`client_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotel`.`room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`room` (
  `room_id` INT NOT NULL AUTO_INCREMENT,
  `room_number` INT NOT NULL,
  `room_capacity` SMALLINT NOT NULL,
  `room_roomstar` VARCHAR(5) NOT NULL,
  `room_roomstatus` VARCHAR(9) NOT NULL,
  `room_price` INT NOT NULL,
  PRIMARY KEY (`room_id`),
  UNIQUE INDEX `id_UNIQUE` (`room_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotel`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`order` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `order_num` INT NOT NULL,
  `order_client_id` INT NOT NULL,
  `order_room_id` INT NOT NULL,
  `order_start_date` DATE NOT NULL,
  `order_finish_date` DATE NULL,
  PRIMARY KEY (`order_id`),
  INDEX `fk_order_client_idx` (`order_client_id` ASC),
  INDEX `fk_order_room1_idx` (`order_room_id` ASC),
  UNIQUE INDEX `id_UNIQUE` (`order_id` ASC),
  CONSTRAINT `fk_order_client`
    FOREIGN KEY (`order_client_id`)
    REFERENCES `hotel`.`client` (`client_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_room1`
    FOREIGN KEY (`order_room_id`)
    REFERENCES `hotel`.`room` (`room_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotel`.`service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`service` (
  `service_id` INT NOT NULL AUTO_INCREMENT,
  `service_code` INT NOT NULL,
  `service_name` VARCHAR(45) NOT NULL,
  `service_price` INT NOT NULL,
  PRIMARY KEY (`service_id`),
  UNIQUE INDEX `id_UNIQUE` (`service_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotel`.`service_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`service_order` (
  `so_id` INT NOT NULL AUTO_INCREMENT,
  `so_service_id` INT NOT NULL,
  `so_order_id` INT NOT NULL,
  PRIMARY KEY (`so_id`),
  INDEX `fk_service_order_order1_idx` (`so_order_id` ASC),
  INDEX `fk_service_order_service1_idx` (`so_service_id` ASC),
  UNIQUE INDEX `id_UNIQUE` (`so_id` ASC),
  CONSTRAINT `fk_service_order_order1`
    FOREIGN KEY (`so_order_id`)
    REFERENCES `hotel`.`order` (`order_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_service_order_service1`
    FOREIGN KEY (`so_service_id`)
    REFERENCES `hotel`.`service` (`service_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;