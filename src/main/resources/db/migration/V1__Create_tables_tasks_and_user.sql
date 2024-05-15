CREATE TABLE `tasks` (
  `tsk_id` int NOT NULL AUTO_INCREMENT,
  `description` text,
  `usr_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tsk_id`),
  KEY `fk_usr_id` (`usr_id`)
);

CREATE TABLE `user` (
  `usr_id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `password` text NOT NULL,
  `role` text NOT NULL,
  PRIMARY KEY (`usr_id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
);