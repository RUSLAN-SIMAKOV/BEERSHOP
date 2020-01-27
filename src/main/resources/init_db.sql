CREATE SCHEMA `beershop` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `beershop`.`items` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `price` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);

  INSERT INTO `beershop`.`items` (`id`, `name`, `price`) VALUES ('1', 'firstZ', '123');
  INSERT INTO items (name, price) VALUES ("second", 100);

  CREATE TABLE `beershop`.`orders` (
  `id_order` INT NOT NULL AUTO_INCREMENT,
  `id_user` INT NOT NULL,
  PRIMARY KEY (`id_order`));

  CREATE TABLE `beershop`.`orders_items` (
  `id_orders_items` INT NOT NULL AUTO_INCREMENT,
  `id_order` INT NOT NULL,
  `id_item` INT NOT NULL,
  PRIMARY KEY (`id_orders_items`),
  INDEX `orders_items_order_fk_idx` (`id_order` ASC) VISIBLE,
  INDEX `orders_items_items_fk_idx` (`id_item` ASC) VISIBLE,
  CONSTRAINT `orders_items_orders_fk`
    FOREIGN KEY (`id_order`)
    REFERENCES `beershop`.`orders` (`id_order`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `orders_items_items_fk`
    FOREIGN KEY (`id_item`)
    REFERENCES `beershop`.`items` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `beershop`.`users` (
  `id_user` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `token` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_user`));

CREATE TABLE `beershop`.`roles` (
  `id_role` INT NOT NULL,
  `role-name` VARCHAR(45) NULL,
  PRIMARY KEY (`id_role`));
CREATE TABLE `beershop`.`users_roles` (
  `id_users_roles` INT NOT NULL,
  `id_user` INT NULL,
  `id_role` INT NULL,
  PRIMARY KEY (`id_users_roles`),
  INDEX `user_roles_fk_idx` (`id_role` ASC) VISIBLE,
  CONSTRAINT `user_roles_fk`
    FOREIGN KEY (`id_role`)
    REFERENCES `beershop`.`roles` (`id_role`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `beershop`.`buckets` (
  `id_buckets` INT NOT NULL,
  `id_user` INT NULL,
  PRIMARY KEY (`id_buckets`));

CREATE TABLE `beershop`.`item_bucket` (
  `id_item_bucket` INT NOT NULL,
  `id_bucket` INT NULL,
  `id_item` INT NULL,
  PRIMARY KEY (`id_item_bucket`));

