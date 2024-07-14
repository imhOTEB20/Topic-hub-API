-- -----------------------------------------------------
-- Table `topic_hub_api`.`servers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `topic_hub_api`.`servers` (
  `server_id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  `creation_datetime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `last_update` TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`server_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `topic_hub_api`.`topics`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `topic_hub_api`.`topics` (
  `topic_id` BIGINT NOT NULL AUTO_INCREMENT,
  `server_id` BIGINT NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  `creation_datetime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`topic_id`),
  INDEX `fk_channel_server` (`server_id` ASC) VISIBLE,
  CONSTRAINT `fk_channel_server`
    FOREIGN KEY (`server_id`)
    REFERENCES `topic_hub_api`.`servers` (`server_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 63
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `topic_hub_api`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `topic_hub_api`.`users` (
  `user_id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `creation_datetime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `last_update` TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `username` (`username` ASC) VISIBLE,
  UNIQUE INDEX `email` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 101
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `topic_hub_api`.`messages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `topic_hub_api`.`messages` (
  `message_id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `topic_id` BIGINT NOT NULL,
  `content` VARCHAR(5000) NOT NULL,
  `status` VARCHAR(45) NULL DEFAULT 'ACTIVO',
  `creation_datetime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `last_update` TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`message_id`),
  INDEX `fk_messages_user` (`user_id` ASC) VISIBLE,
  INDEX `fk_messages_topic` (`topic_id` ASC) VISIBLE,
  CONSTRAINT `fk_messages_topic`
    FOREIGN KEY (`topic_id`)
    REFERENCES `topic_hub_api`.`topics` (`topic_id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_messages_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `topic_hub_api`.`users` (`user_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1029
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `topic_hub_api`.`old_messages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `topic_hub_api`.`old_messages` (
  `old_messages_id` BIGINT NOT NULL AUTO_INCREMENT,
  `message_id` BIGINT NOT NULL,
  `content` VARCHAR(5000) NULL,
  PRIMARY KEY (`old_messages_id`),
  INDEX `fk_old_messages_messages_idx` (`message_id` ASC) VISIBLE,
  CONSTRAINT `fk_old_messages_messages`
    FOREIGN KEY (`message_id`)
    REFERENCES `topic_hub_api`.`messages` (`message_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `topic_hub_api`.`server_onboarding`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `topic_hub_api`.`server_onboarding` (
  `server_onboarding_id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `server_id` BIGINT NOT NULL,
  `joined_datetime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`server_onboarding_id`),
  UNIQUE INDEX `user_id` (`user_id` ASC, `server_id` ASC) VISIBLE,
  INDEX `fk_user_server_associations_server` (`server_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_server_associations_server`
    FOREIGN KEY (`server_id`)
    REFERENCES `topic_hub_api`.`servers` (`server_id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_user_server_associations_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `topic_hub_api`.`users` (`user_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1748
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;