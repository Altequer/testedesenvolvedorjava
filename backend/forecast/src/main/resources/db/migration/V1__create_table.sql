CREATE TABLE `hbsis`.`city` (
  `id` INT(10) UNSIGNED NOT NULL COMMENT 'Código da cidade recebido da API open weather',
  `name` VARCHAR(250) NOT NULL COMMENT 'Nome da cidade',
  `longitude` float COMMENT 'Longitude obtida pela API',
  `latitude` float COMMENT 'Latitude obtida pela API',
  `country` VARCHAR(250) COMMENT 'País',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;