DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `borrarLicenciaCadudcada` ()   BEGIN
    DECLARE fecha_actual DATE;

    SET fecha_actual = CURDATE();

    DELETE FROM licencia WHERE licencia.valida_hasta < fecha_actual;

END$$

DELIMITER ;


DELIMITER $$
--
-- Eventos
--
CREATE DEFINER=`root`@`localhost` EVENT `borraLicencia` ON SCHEDULE EVERY 1 DAY STARTS '2023-05-13 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO CALL borrarLicenciaCadudcada()$$

DELIMITER ;
COMMIT;

DELIMITER //
CREATE TRIGGER crearLicenciaDefecto
AFTER INSERT ON usuario
FOR EACH ROW
BEGIN
    DECLARE fecha_actual DATE;
    DECLARE valida_hasta_date DATE;
    SET fecha_actual = CURDATE();
    SET valida_hasta_date = DATE_ADD(fecha_actual, INTERVAL 100 YEAR);
    
    INSERT INTO licencia (id_usuario, id_tipo_licencia, valida_hasta, valida_desde, fecha_expedicion, restricciones, observaciones, licencia_validada, url_img_licencia_anverso, url_img_licencia_reverso)
    VALUES (NEW.id_usuario, 1, valida_hasta_date, fecha_actual, fecha_actual, '', '', 1, NULL, NULL);
END //
DELIMITER ;