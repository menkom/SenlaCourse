CREATE SCHEMA IF NOT EXISTS `hotel` DEFAULT CHARACTER SET cp1251 ;
USE `hotel` ;

-- -----------------------------------------------------
-- Table `hotel`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`client` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotel`.`room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`room` (
  `id` INT NOT NULL,
  `number` INT NOT NULL,
  `capacity` SMALLINT NOT NULL,
  `roomstar` VARCHAR(5) NOT NULL,
  `roomstatus` VARCHAR(9) NOT NULL,
  `price` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotel`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`order` (
  `id` INT NOT NULL,
  `num` INT NOT NULL,
  `client_id` INT NOT NULL,
  `room_id` INT NOT NULL,
  `start_date` DATE NOT NULL,
  `finish_date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_client_idx` (`client_id` ASC),
  INDEX `fk_order_room1_idx` (`room_id` ASC),
  CONSTRAINT `fk_order_client`
    FOREIGN KEY (`client_id`)
    REFERENCES `hotel`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_room1`
    FOREIGN KEY (`room_id`)
    REFERENCES `hotel`.`room` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotel`.`service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`service` (
  `id` INT NOT NULL,
  `code` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `price` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotel`.`service_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`service_order` (
  `id` INT NOT NULL,
  `service_id` INT NOT NULL,
  `order_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_service_order_order1_idx` (`order_id` ASC),
  INDEX `fk_service_order_service1_idx` (`service_id` ASC),
  CONSTRAINT `fk_service_order_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `hotel`.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_order_service1`
    FOREIGN KEY (`service_id`)
    REFERENCES `hotel`.`service` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
