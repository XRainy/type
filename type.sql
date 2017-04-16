-- MySQL Script generated by MySQL Workbench
-- Mon Apr  3 21:08:59 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema type
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema type
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `type` DEFAULT CHARACTER SET utf8 ;
USE `type` ;

-- -----------------------------------------------------
-- Table `type`.`tbuser`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tbusertype`.`tbuser` (
  `userId` VARCHAR(255) NOT NULL,
  `userName` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `record` VARCHAR(10000) NOT NULL,
  `createTime` DATETIME NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `state` INT NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE INDEX `userName_UNIQUE` (`userName` ASC))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;