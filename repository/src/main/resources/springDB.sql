-- -----------------------------------------------------
-- Schema springDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema springDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `springDB`;
USE `springDB` ;

-- -----------------------------------------------------
-- Table `springDB`.`gift_certificate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `springDB`.`gift_certificate` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(150) NOT NULL,
  `price` INT NOT NULL,
  `duration` SMALLINT NOT NULL,
  `create_date` DATETIME NOT NULL,
  `last_update_date` DATETIME NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `springDB`.`tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `springDB`.`tag` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `springDB`.`gift_certificate_has_tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `springDB`.`gift_certificate_has_tag` (
  `gift_certificate_id` INT NOT NULL,
  `tag_id` INT NOT NULL,
  PRIMARY KEY (`gift_certificate_id`, `tag_id`),
    FOREIGN KEY (`gift_certificate_id`)
    REFERENCES `springDB`.`gift_certificate` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`tag_id`)
    REFERENCES `springDB`.`tag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)

