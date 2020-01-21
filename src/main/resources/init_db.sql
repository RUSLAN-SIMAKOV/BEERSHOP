CREATE SCHEMA `beershop` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `beershop`.`items` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `price` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);

  INSERT INTO `beershop`.`items` (`id`, `name`, `price`) VALUES ('1', 'firstZ', '123');

  INSERT INTO items (name, price) VALUES ("second", 100);
