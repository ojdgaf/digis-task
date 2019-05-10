DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `login` VARCHAR(16) NOT NULL UNIQUE,
  `full_name` VARCHAR(100) NOT NULL,
  `date_of_birth` DATE NOT NULL,
  `gender` VARCHAR(100) NOT NULL
) ENGINE=InnoDB;

INSERT INTO `users` VALUES (1, 'ojdgaf', 'Eugene Burdeinyi', '1996-12-30', 'male');