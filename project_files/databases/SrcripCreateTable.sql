-- MySQL Script generated by MySQL Workbench
-- Fri Mar 30 18:42:12 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema db_dogsystem
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `db_dogsystem` ;

-- -----------------------------------------------------
-- Schema db_dogsystem
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_dogsystem` DEFAULT CHARACTER SET latin1 ;
USE `db_dogsystem` ;

-- -----------------------------------------------------
-- Table `db_dogsystem`.`tb_uf`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_dogsystem`.`tb_uf` (
  `cod_uf` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `sigla` CHAR(2) NOT NULL,
  PRIMARY KEY (`cod_uf`),
  UNIQUE INDEX `UK_l537ownkvnjeinqybyxulq7hy` (`sigla` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `db_dogsystem`.`tb_city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_dogsystem`.`tb_city` (
  `cod_city` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(25) NOT NULL,
  `cod_uf` BIGINT(20) NOT NULL,
  PRIMARY KEY (`cod_city`),
  INDEX `FKqc4al0oxwyd2na6gb5uebhtn8` (`cod_uf` ASC),
  CONSTRAINT `FKqc4al0oxwyd2na6gb5uebhtn8`
    FOREIGN KEY (`cod_uf`)
    REFERENCES `db_dogsystem`.`tb_uf` (`cod_uf`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `db_dogsystem`.`tb_neighborhood`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_dogsystem`.`tb_neighborhood` (
  `cod_neigh` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `cod_city` BIGINT(20) NOT NULL,
  PRIMARY KEY (`cod_neigh`),
  INDEX `FK3co757a7pdrsqxslse9jdn2` (`cod_city` ASC),
  CONSTRAINT `FK3co757a7pdrsqxslse9jdn2`
    FOREIGN KEY (`cod_city`)
    REFERENCES `db_dogsystem`.`tb_city` (`cod_city`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `db_dogsystem`.`tb_address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_dogsystem`.`tb_address` (
  `cod_address` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `zipcode` VARCHAR(9) NOT NULL,
  `cod_neigh` BIGINT(20) NOT NULL,
  PRIMARY KEY (`cod_address`),
  INDEX `FKqu9tf3d0eo4philqllb946ox2` (`cod_neigh` ASC),
  CONSTRAINT `FKqu9tf3d0eo4philqllb946ox2`
    FOREIGN KEY (`cod_neigh`)
    REFERENCES `db_dogsystem`.`tb_neighborhood` (`cod_neigh`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `db_dogsystem`.`tb_service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_dogsystem`.`tb_service` (
  `cod_service` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `price` DECIMAL(20,2) NULL DEFAULT NULL,
  `size` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`cod_service`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `db_dogsystem`.`tb_breed`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_dogsystem`.`tb_breed` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `height` VARCHAR(255) NULL DEFAULT NULL,
  `life` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(120) NOT NULL,
  `porte` INT(11) NULL DEFAULT NULL,
  `tipo_animal` INT(11) NULL DEFAULT NULL,
  `weight` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_ej79vjm71j79r04d5m7nb2lsl` (`name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `db_dogsystem`.`tb_image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_dogsystem`.`tb_image` (
  `cod_image` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `base64` LONGBLOB NULL DEFAULT NULL,
  `filename` VARCHAR(255) NULL DEFAULT NULL,
  `filesize` INT(11) NULL DEFAULT NULL,
  `filetype` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`cod_image`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `db_dogsystem`.`tb_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_dogsystem`.`tb_user` (
  `cod_user` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `complement` VARCHAR(200) NULL DEFAULT NULL,
  `cpf` VARCHAR(14) NOT NULL,
  `email` VARCHAR(120) NOT NULL,
  `name` VARCHAR(120) NOT NULL,
  `number` BIGINT(20) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(13) NOT NULL,
  `cod_address` BIGINT(20) NOT NULL,
  PRIMARY KEY (`cod_user`),
  UNIQUE INDEX `UK_869sa3rebuf3nm0d4jwxdtouk` (`cpf` ASC),
  UNIQUE INDEX `UK_4vih17mube9j7cqyjlfbcrk4m` (`email` ASC),
  UNIQUE INDEX `UK_8yfdv7pbvgjexnabpkxnd2v2w` (`phone` ASC),
  INDEX `FKn9w2r81gai1qgltwsmofvd0bg` (`cod_address` ASC),
  CONSTRAINT `FKn9w2r81gai1qgltwsmofvd0bg`
    FOREIGN KEY (`cod_address`)
    REFERENCES `db_dogsystem`.`tb_address` (`cod_address`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `db_dogsystem`.`tb_pet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_dogsystem`.`tb_pet` (
  `cod_pet` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `date_tbirth` DATETIME NULL DEFAULT NULL,
  `fertile_period` DATETIME NULL DEFAULT NULL,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  `note` VARCHAR(255) NULL DEFAULT NULL,
  `sex` INT(11) NULL DEFAULT NULL,
  `tipo_animal` INT(11) NULL DEFAULT NULL,
  `usa_dog_love` CHAR(1) NOT NULL,
  `cod_breed` BIGINT(20) NULL DEFAULT NULL,
  `cod_image` BIGINT(20) NOT NULL,
  `cod_owner` BIGINT(20) NOT NULL,
  PRIMARY KEY (`cod_pet`),
  INDEX `FKfm58d1qeg2kqvegrym6x2ekfy` (`cod_breed` ASC),
  INDEX `FKohovp35wcnshujqfch5ykutqe` (`cod_image` ASC),
  INDEX `FKojd2x52x33sih4w6h2l11w1b4` (`cod_owner` ASC),
  CONSTRAINT `FKfm58d1qeg2kqvegrym6x2ekfy`
    FOREIGN KEY (`cod_breed`)
    REFERENCES `db_dogsystem`.`tb_breed` (`id`),
  CONSTRAINT `FKohovp35wcnshujqfch5ykutqe`
    FOREIGN KEY (`cod_image`)
    REFERENCES `db_dogsystem`.`tb_image` (`cod_image`),
  CONSTRAINT `FKojd2x52x33sih4w6h2l11w1b4`
    FOREIGN KEY (`cod_owner`)
    REFERENCES `db_dogsystem`.`tb_user` (`cod_user`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `db_dogsystem`.`tb_agenda_service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_dogsystem`.`tb_agenda_service` (
  `cod_agen_serv` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `low_date` DATE NULL DEFAULT NULL,
  `note` VARCHAR(100) NULL DEFAULT NULL,
  `pet` TINYBLOB NULL DEFAULT NULL,
  `scheduling_date` DATE NOT NULL,
  `time` VARCHAR(5) NOT NULL,
  `cod_service` BIGINT(20) NOT NULL,
  `cod_pet` BIGINT(20) NOT NULL,
  PRIMARY KEY (`cod_agen_serv`),
  INDEX `FK9qtjjwyst8hty4usj38w5x37t` (`cod_service` ASC),
  INDEX `FKitpxud9tl30m2jkq7s5mk827j` (`cod_pet` ASC),
  CONSTRAINT `FK9qtjjwyst8hty4usj38w5x37t`
    FOREIGN KEY (`cod_service`)
    REFERENCES `db_dogsystem`.`tb_service` (`cod_service`),
  CONSTRAINT `FKitpxud9tl30m2jkq7s5mk827j`
    FOREIGN KEY (`cod_pet`)
    REFERENCES `db_dogsystem`.`tb_pet` (`cod_pet`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `db_dogsystem`.`tb_agenda_vancine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_dogsystem`.`tb_agenda_vancine` (
  `cod_agen_vacc` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `low_date` DATE NULL DEFAULT NULL,
  `note` VARCHAR(100) NULL DEFAULT NULL,
  `pet` TINYBLOB NULL DEFAULT NULL,
  `scheduling_date` DATE NOT NULL,
  `cod_pet` BIGINT(20) NOT NULL,
  PRIMARY KEY (`cod_agen_vacc`),
  INDEX `FK8u7u3pvga51qj9h5ttc3hb4sa` (`cod_pet` ASC),
  CONSTRAINT `FK8u7u3pvga51qj9h5ttc3hb4sa`
    FOREIGN KEY (`cod_pet`)
    REFERENCES `db_dogsystem`.`tb_pet` (`cod_pet`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `db_dogsystem`.`tb_permission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_dogsystem`.`tb_permission` (
  `cod_per` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cod_per`),
  UNIQUE INDEX `UK_rwvnf9dhenmknvljgery79idd` (`role` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `db_dogsystem`.`tb_user_permission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_dogsystem`.`tb_user_permission` (
  `cod_per` BIGINT(20) NOT NULL,
  `cod_user` BIGINT(20) NOT NULL,
  PRIMARY KEY (`cod_per`, `cod_user`),
  INDEX `FKqa2i7u3cq7egl1elwopsfr3lh` (`cod_user` ASC),
  CONSTRAINT `FK7c5bfxt91r5s34d1cxday58fm`
    FOREIGN KEY (`cod_per`)
    REFERENCES `db_dogsystem`.`tb_permission` (`cod_per`),
  CONSTRAINT `FKqa2i7u3cq7egl1elwopsfr3lh`
    FOREIGN KEY (`cod_user`)
    REFERENCES `db_dogsystem`.`tb_user` (`cod_user`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `db_dogsystem`.`tb_vacinne`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_dogsystem`.`tb_vacinne` (
  `cod_vaccine` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`cod_vaccine`),
  CONSTRAINT `FKl48rbro3fogrx11v9rqsjd19r`
    FOREIGN KEY (`cod_vaccine`)
    REFERENCES `db_dogsystem`.`tb_agenda_vancine` (`cod_agen_vacc`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;