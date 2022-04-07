CREATE DATABASE `bd_hibernate2` /*!40100 DEFAULT CHARACTER SET utf8mb4*/;
USE `bd_hibernate2`;

create table `persona`(
		`id_persona` int(11) not null,
        `nombre_persona` varchar(45) default null,
        `profesion_persona` varchar(45) default null,
        `telefono_persona` varchar(45) default null,
        `tipo_persona` int (2) not null,
        `nacimiento_id` int(11) not null,
        PRIMARY KEY (id_persona)
);

create table nacimiento(
		id_nacimiento int(11) not null auto_increment,
        ciudad_nacimiento varchar(45) default null,
        departamento_nacimiento varchar(45) default null,
        fecha_nacimiento date default null,
        pais_nacimiento varchar(45) default null,
        primary key (id_nacimiento)
);

create table `mascotas`(
 `id_mascota` int(11) not null auto_increment,
 `nombre` varchar(45) not null,
 `raza` varchar(45) default null,
 `color` varchar(45) default null,
 `sexo` varchar(45) default null,
 primary key(`id_mascota`)
)ENGINE=InnoDB auto_increment=6 DEFAULT CHARSET=utf8mb4;

ALTER TABLE persona
ADD INDEX fk_persona_nacimiento (nacimiento_id ASC);
;
ALTER TABLE persona
ADD CONSTRAINT fk_persona_nacimiento
		FOREIGN KEY (nacimiento_id)
		REFERENCES nacimiento(id_nacimiento)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION;

ALTER TABLE `bd_hibernate2`.`mascotas`
ADD COLUMN `persona_id` INT(11) NULL AFTER `sexo`;

ALTER TABLE `bd_hibernate2`.`mascotas`
ADD INDEX `fk_mascotas_persona_idx` (`persona_id` ASC);
;
ALTER TABLE `bd_hibernate2`.`mascotas`
ADD CONSTRAINT `fk_mascotas_persona`
	FOREIGN KEY (`persona_id`)
    REFERENCES `bd_hibernate2`.`persona` (`id_persona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
        
create table `bd_hibernate2`.`productos`(
  `id_producto` int(11) not null auto_increment,
  `nombre_producto` varchar(45) not null,
  `precio_producto` double default null,
  primary key(`id_producto`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table `bd_hibernate2`.`personas_productos`(
  `persona_id` int(11) not null,
  `producto_id` int(11) not null,
  key `FK_producto` (`producto_id`),
  key `FK_persona` (`persona_id`),
  constraint `FK_producto` foreign key (`producto_id`) references `productos` (`id_producto`),
  constraint `FK_persona` foreign key (`persona_id`) references `persona` (`id_persona`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;