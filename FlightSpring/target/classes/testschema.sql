DROP TABLE IF EXISTS `flight`;

CREATE TABLE `flight`(
	`id` BIGINT AUTO_INCREMENT,
	`start_location` VARCHAR(255),
	`end_location` VARCHAR(255),
	`airlines` VARCHAR(255),
	`flight_date` DATE,
	`price` DECIMAL(10,2),
	PRIMARY KEY(`id`)
);