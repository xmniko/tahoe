CREATE DATABASE `tahoedb` /*!40100 COLLATE 'utf8_general_ci' */

CREATE TABLE `demo_task` (
	`ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`CREATED_AT` DATETIME NULL DEFAULT NULL,
	`CREATED_BY` VARCHAR(100) NULL DEFAULT NULL,
	`UPDATED_AT` DATETIME NULL DEFAULT NULL,
	`UPDATED_BY` VARCHAR(100) NULL DEFAULT NULL,
	`TITLE` VARCHAR(100) NULL DEFAULT NULL,
	`TASK_ORDER` INT(11) UNSIGNED NULL DEFAULT NULL,
	`COMPLETED` BIT(1) NULL DEFAULT NULL,
	INDEX `ID` (`ID`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `sys_role` (
	`ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`CREATED_AT` DATETIME NULL DEFAULT NULL,
	`CREATED_BY` VARCHAR(100) NULL DEFAULT NULL,
	`UPDATED_AT` DATETIME NULL DEFAULT NULL,
	`UPDATED_BY` VARCHAR(100) NULL DEFAULT NULL,
	`NAME` VARCHAR(100) NULL DEFAULT NULL,
	`PERMISSIONS` VARCHAR(100) NULL DEFAULT NULL,
	INDEX `ID` (`ID`)
)
ENGINE=InnoDB
;

CREATE TABLE `sys_user` (
	`ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`CREATED_AT` DATETIME NULL DEFAULT NULL,
	`CREATED_BY` VARCHAR(100) NULL DEFAULT NULL,
	`UPDATED_AT` DATETIME NULL DEFAULT NULL,
	`UPDATED_BY` VARCHAR(100) NULL DEFAULT NULL,
	`EMAIL` VARCHAR(100) NULL DEFAULT NULL,
	`PASSWORD` VARCHAR(100) NULL DEFAULT NULL,
	`SALT` VARCHAR(100) NULL DEFAULT NULL,
	`NICK_NAME` VARCHAR(100) NULL DEFAULT NULL,
	`STATUS` VARCHAR(30) NULL DEFAULT NULL,
	INDEX `ID` (`ID`)
)
ENGINE=InnoDB
;

CREATE TABLE `sys_user_role` (
	`ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`CREATED_AT` DATETIME NULL DEFAULT NULL,
	`CREATED_BY` VARCHAR(100) NULL DEFAULT NULL,
	`UPDATED_AT` DATETIME NULL DEFAULT NULL,
	`UPDATED_BY` VARCHAR(100) NULL DEFAULT NULL,
	`USER_ID` BIGINT(20) NOT NULL ,
	`ROLE_ID` BIGINT(20) NOT NULL ,
	INDEX `ID` (`ID`)
)
ENGINE=InnoDB
;