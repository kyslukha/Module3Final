subscriptionsCREATE SCHEMA `moduleHibernate`;

use moduleHibernate;

CREATE TABLE `moduleHibernate`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fullName` VARCHAR(50) NOT NULL,
  `login` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `age` INT NOT NULL,
  `is_author` TINYINT NOT NULL DEFAULT 0,
  `is_moderator` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);






 CREATE TABLE `moduleHibernate`.`subscriptions` (
  `writer_id` INT NOT NULL,
  `follower_id` INT NOT NULL,
  PRIMARY KEY (`writer_id`, `follower_id`),
  INDEX `pk_follower_idx` (`follower_id` ASC) VISIBLE,
  CONSTRAINT `pk_writer_id`
    FOREIGN KEY (`writer_id`)
    REFERENCES `module3_db`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `pk_follower_id`
    FOREIGN KEY (`follower_id`)
    REFERENCES `moduleHibernate`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `moduleHibernate`.`post` (
  `post_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `content` VARCHAR(1000) NULL,
  `author_id` INT NOT NULL,
  `moderator_id` INT NULL,
  `rating` DECIMAL NULL,
  `post_status` ENUM('DRAFT', 'IN_PROGRESS', 'WORKSHEET', 'PUBLISHED') NULL DEFAULT 'DRAFT',
  PRIMARY KEY (`post_id`));

  ALTER TABLE `moduleHibernate`.`post`
ADD INDEX `pk_author_id_idx` (`author_id` ASC) VISIBLE,
ADD INDEX `pk_moderator_id_idx` (`moderator_id` ASC) VISIBLE;

ALTER TABLE `moduleHibernate`.`post` 
ADD CONSTRAINT `pk_author_id`
  FOREIGN KEY (`author_id`)
  REFERENCES `moduleHibernate`.`users` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `pk_moderator_id`
  FOREIGN KEY (`moderator_id`)
  REFERENCES `moduleHibernate`.`users` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

