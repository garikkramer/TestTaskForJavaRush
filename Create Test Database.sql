CREATE SCHEMA `test` DEFAULT CHARACTER SET utf8;

CREATE TABLE `test`.`deal` (
`ID` INT(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
`NAME` VARCHAR(25) NOT NULL,
`CREATED_DATE` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
`DESCRIPTION` VARCHAR(250) NOT NULL,
`IS_DONE` BIT(1) DEFAULT false);

INSERT INTO `test`.`deal` (`NAME`, `DESCRIPTION`) VALUES ('deal1', 'deal description');
INSERT INTO `test`.`deal` (`NAME`, `DESCRIPTION`) VALUES ('deal2', 'deal description');
INSERT INTO `test`.`deal` (`NAME`, `DESCRIPTION`) VALUES ('deal3', 'deal description');
INSERT INTO `test`.`deal` (`NAME`, `DESCRIPTION`) VALUES ('deal4', 'deal description');
INSERT INTO `test`.`deal` (`NAME`, `DESCRIPTION`) VALUES ('deal5', 'deal description');
INSERT INTO `test`.`deal` (`NAME`, `DESCRIPTION`) VALUES ('deal6', 'deal description');
INSERT INTO `test`.`deal` (`NAME`, `DESCRIPTION`) VALUES ('deal7', 'deal description');
INSERT INTO `test`.`deal` (`NAME`, `DESCRIPTION`) VALUES ('deal8', 'deal description');
INSERT INTO `test`.`deal` (`NAME`, `DESCRIPTION`) VALUES ('deal9', 'deal description');
INSERT INTO `test`.`deal` (`NAME`, `DESCRIPTION`) VALUES ('deal10', 'deal description');