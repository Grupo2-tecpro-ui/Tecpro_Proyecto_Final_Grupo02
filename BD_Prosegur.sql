CREATE DATABASE prosegur;
USE prosegur;


CREATE TABLE sede (
    idSede INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

INSERT INTO sede VALUES
(1, 'Miraflores'),
(2, 'Surco'),
(3, 'Tambo Norte');


CREATE TABLE motorizado (
    id INT PRIMARY KEY AUTO_INCREMENT,
    dni VARCHAR(8) NOT NULL UNIQUE,
    nombres VARCHAR(80),
    apellidos VARCHAR(80),
    celular VARCHAR(20),
    placa VARCHAR(20),
    marca VARCHAR(40),
    modelo VARCHAR(40),
    brevete VARCHAR(10),
    vencBrevete DATE,
    soat BOOLEAN,
    estado VARCHAR(20),
    fechaIngreso DATE,
    contrato VARCHAR(40),
    tarjetasAsignadas INT DEFAULT 0,
    diaRuta BOOLEAN DEFAULT FALSE,
    fechaTarjetas DATE,
    idSede INT,
    CONSTRAINT fk_sede FOREIGN KEY (idSede) REFERENCES sede(idSede)
);


CREATE TABLE entrega (
    id INT PRIMARY KEY AUTO_INCREMENT,
    dniMotorizado VARCHAR(8) NOT NULL,
    cantidad INT NOT NULL,
    requestedCantidad INT NOT NULL,
    fecha DATE NOT NULL,
    CONSTRAINT fk_entrega_motorizado
        FOREIGN KEY (dniMotorizado) REFERENCES motorizado(dni)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);


CREATE TABLE detalle_entrega (
    id INT PRIMARY KEY AUTO_INCREMENT,
    idEntrega INT NOT NULL,
    cliente VARCHAR(150),
    banco VARCHAR(80),
    direccion VARCHAR(160),
    conforme BOOLEAN,
    CONSTRAINT fk_detalle_entrega
        FOREIGN KEY (idEntrega) REFERENCES entrega(id)
        ON DELETE CASCADE
);



DELIMITER //
CREATE PROCEDURE agregarMotorizado(
    IN p_dni VARCHAR(8),
    IN p_nombres VARCHAR(80),
    IN p_apellidos VARCHAR(80),
    IN p_celular VARCHAR(20),
    IN p_placa VARCHAR(20),
    IN p_marca VARCHAR(40),
    IN p_modelo VARCHAR(40),
    IN p_brevete VARCHAR(10),
    IN p_vencBrevete DATE,
    IN p_soat BOOLEAN,
    IN p_estado VARCHAR(20),
    IN p_fechaIngreso DATE,
    IN p_contrato VARCHAR(40),
    IN p_tarjetasAsignadas INT,
    IN p_diaRuta BOOLEAN,
    IN p_fechaTarjetas DATE,
    IN p_idSede INT
)
BEGIN
    INSERT INTO motorizado(
        dni, nombres, apellidos, celular, placa,
        marca, modelo, brevete, vencBrevete, soat,
        estado, fechaIngreso, contrato,
        tarjetasAsignadas, diaRuta, fechaTarjetas,
        idSede
    )
    VALUES (
        p_dni, p_nombres, p_apellidos, p_celular, p_placa,
        p_marca, p_modelo, p_brevete, p_vencBrevete, p_soat,
        p_estado, p_fechaIngreso, p_contrato,
        p_tarjetasAsignadas, p_diaRuta, p_fechaTarjetas,
        p_idSede
    );
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE editarMotorizadoPorDni(
    IN p_dni VARCHAR(8),
    IN p_nombres VARCHAR(80),
    IN p_apellidos VARCHAR(80),
    IN p_celular VARCHAR(20),
    IN p_tarjetasAsignadas INT,
    IN p_estado VARCHAR(20)
)
BEGIN
    UPDATE motorizado
    SET
        nombres = p_nombres,
        apellidos = p_apellidos,
        celular = p_celular,
        tarjetasAsignadas = p_tarjetasAsignadas,
        estado = p_estado
    WHERE dni = p_dni;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE eliminarMotorizadoPorDni(
    IN p_dni VARCHAR(8)
)
BEGIN
    DELETE FROM motorizado
    WHERE dni = p_dni;
END //
DELIMITER ;


CREATE OR REPLACE VIEW vw_historial_entregas AS
SELECT 
    e.fecha,
    d.cliente,
    d.direccion,
    d.banco,
    CONCAT(m.nombres, ' ', m.apellidos) AS mensajeroNombre,
    m.dni AS mensajeroDni,
    d.conforme
FROM detalle_entrega d
JOIN entrega e ON d.idEntrega = e.id
JOIN motorizado m ON e.dniMotorizado = m.dni;


DELIMITER //
CREATE PROCEDURE registrarEntrega(
    IN p_dni VARCHAR(8),
    IN p_cantidad INT,
    IN p_fecha DATE
)
BEGIN
    INSERT INTO entrega(dniMotorizado, cantidad, requestedCantidad, fecha)
    VALUES(p_dni, p_cantidad, p_cantidad, p_fecha);

    UPDATE motorizado
    SET tarjetasAsignadas = tarjetasAsignadas - p_cantidad
    WHERE dni = p_dni;
END //
DELIMITER ;

ALTER TABLE motorizado
ADD CONSTRAINT chk_tarjetas_no_negativas
CHECK (tarjetasAsignadas >= 0);

ALTER TABLE motorizado
ADD CONSTRAINT chk_dni_formato
CHECK (dni REGEXP '^[0-9]{8}$');

DELIMITER //
CREATE PROCEDURE agregarDetalleEntrega(
    IN p_idEntrega INT,
    IN p_cliente VARCHAR(150),
    IN p_banco VARCHAR(80),
    IN p_direccion VARCHAR(160),
    IN p_conforme BOOLEAN
)
BEGIN
    INSERT INTO detalle_entrega(idEntrega, cliente, banco, direccion, conforme)
    VALUES(p_idEntrega, p_cliente, p_banco, p_direccion, p_conforme);
END //
DELIMITER ;

SELECT * FROM motorizado
SELECT * FROM entrega
SELECT * FROM detalle_entrega
SELECT * FROM motorizado_sede

ALTER TABLE entrega
DROP FOREIGN KEY fk_entrega_motorizado;

ALTER TABLE entrega
ADD CONSTRAINT fk_entrega_motorizado
FOREIGN KEY (dniMotorizado) REFERENCES motorizado(dni)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE motorizado
DROP FOREIGN KEY fk_sede;

ALTER TABLE motorizado
DROP COLUMN idSede;

CREATE TABLE motorizado_sede (
    idMotorizado INT NOT NULL,
    idSede INT NOT NULL,
    fechaAsignacion DATE NOT NULL,
    
    PRIMARY KEY (idMotorizado, idSede),

    CONSTRAINT fk_ms_motorizado
        FOREIGN KEY (idMotorizado) REFERENCES motorizado(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_ms_sede
        FOREIGN KEY (idSede) REFERENCES sede(idSede)
        ON DELETE CASCADE
);
ALTER TABLE motorizado
ADD COLUMN idSede INT;

ALTER TABLE motorizado
ADD CONSTRAINT fk_sede
    FOREIGN KEY (idSede) REFERENCES sede(idSede);

UPDATE motorizado SET nombres = '' WHERE nombres IS NULL AND id > 0;
UPDATE motorizado SET apellidos = '' WHERE apellidos IS NULL AND id > 0;
UPDATE motorizado SET celular = '' WHERE celular IS NULL AND id > 0;
UPDATE motorizado SET placa = '' WHERE placa IS NULL AND id > 0;
UPDATE motorizado SET marca = '' WHERE marca IS NULL AND id > 0;
UPDATE motorizado SET modelo = '' WHERE modelo IS NULL AND id > 0;
UPDATE motorizado SET brevete = '' WHERE brevete IS NULL AND id > 0;
UPDATE motorizado SET vencBrevete = '2000-01-01' WHERE vencBrevete IS NULL AND id > 0;
UPDATE motorizado SET soat = 0 WHERE soat IS NULL AND id > 0;
UPDATE motorizado SET estado = 'Inactivo' WHERE estado IS NULL AND id > 0;
UPDATE motorizado SET fechaIngreso = '2000-01-01' WHERE fechaIngreso IS NULL AND id > 0;
UPDATE motorizado SET contrato = 'No asignado' WHERE contrato IS NULL AND id > 0;
UPDATE motorizado SET fechaTarjetas = '2000-01-01' WHERE fechaTarjetas IS NULL AND id > 0;
UPDATE motorizado SET idSede = 1 WHERE idSede IS NULL AND id > 0;

ALTER TABLE motorizado MODIFY nombres VARCHAR(80) NOT NULL;
ALTER TABLE motorizado MODIFY apellidos VARCHAR(80) NOT NULL;
ALTER TABLE motorizado MODIFY celular VARCHAR(20) NOT NULL;
ALTER TABLE motorizado MODIFY placa VARCHAR(20) NOT NULL;
ALTER TABLE motorizado MODIFY marca VARCHAR(40) NOT NULL;
ALTER TABLE motorizado MODIFY modelo VARCHAR(40) NOT NULL;
ALTER TABLE motorizado MODIFY brevete VARCHAR(10) NOT NULL;
ALTER TABLE motorizado MODIFY vencBrevete DATE NOT NULL;
ALTER TABLE motorizado MODIFY soat BOOLEAN NOT NULL;
ALTER TABLE motorizado MODIFY estado VARCHAR(20) NOT NULL;
ALTER TABLE motorizado MODIFY fechaIngreso DATE NOT NULL;
ALTER TABLE motorizado MODIFY contrato VARCHAR(40) NOT NULL;
ALTER TABLE motorizado MODIFY tarjetasAsignadas INT NOT NULL;
ALTER TABLE motorizado MODIFY diaRuta BOOLEAN NOT NULL;
ALTER TABLE motorizado MODIFY fechaTarjetas DATE NOT NULL;
ALTER TABLE motorizado MODIFY idSede INT NOT NULL;

ALTER TABLE motorizado 
ADD CONSTRAINT uq_placa UNIQUE (placa);

ALTER TABLE motorizado 
ADD CONSTRAINT uq_celular UNIQUE (celular);

ALTER TABLE motorizado
ADD CONSTRAINT chk_estado
CHECK (estado IN ('Activo','Inactivo'));

ALTER TABLE motorizado
ADD CONSTRAINT chk_contrato
CHECK (contrato IN ('Express','Tiempo completo','Medio tiempo'));

SELECT * FROM motorizado_sede;


ALTER TABLE motorizado_sede DROP FOREIGN KEY fk_ms_motorizado;
ALTER TABLE motorizado_sede DROP FOREIGN KEY fk_ms_sede;
ALTER TABLE motorizado_sede DROP PRIMARY KEY;
ALTER TABLE motorizado_sede
ADD PRIMARY KEY (idMotorizado, fechaAsignacion);
ALTER TABLE motorizado_sede
ADD CONSTRAINT fk_ms_motorizado
FOREIGN KEY (idMotorizado) REFERENCES motorizado(id)
ON DELETE CASCADE;
ALTER TABLE motorizado_sede
ADD CONSTRAINT fk_ms_sede
FOREIGN KEY (idSede) REFERENCES sede(idSede)
ON DELETE CASCADE;


ALTER TABLE motorizado_sede
ADD COLUMN tarjetasAsignadasDia INT NOT NULL DEFAULT 0;


