DROP TRIGGER IF EXISTS TGR_IN_TB_USER;

DELIMITER $$
CREATE TRIGGER TGR_IN_TB_USER BEFORE 
  INSERT ON TB_USER FOR EACH ROW
BEGIN
  DECLARE msg VARCHAR(255);
  
    IF (SELECT COUNT(*) FROM TB_USER WHERE CPF = NEW.CPF) > 0 THEN
        SET msg = 'O campo CPF já está cadastrado';
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
         
        ELSE IF (SELECT COUNT(*) FROM TB_USER WHERE EMAIL = NEW.EMAIL) > 0 THEN
            SET msg = 'O campo Email já está cadastrado';
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;   
            
            ELSE IF (SELECT COUNT(*) FROM TB_USER WHERE PHONE = NEW.PHONE) > 0 THEN
                 SET msg = 'O campo telefone já está cadastrado';
                 SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
                 
            END IF;
            
        END IF;
    END IF;
END$$

DROP TRIGGER IF EXISTS TGR_UP_TB_USER;

DELIMITER $$
CREATE TRIGGER TGR_UP_TB_USER BEFORE 
  UPDATE ON TB_USER FOR EACH ROW
BEGIN
  DECLARE msg VARCHAR(255);
  
    IF (SELECT COUNT(*) FROM TB_USER WHERE CPF = NEW.CPF AND cod_user <> OLD.COD_USER) > 0 THEN
        SET msg = 'O campo CPF já está cadastrado';
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
         
        ELSE IF (SELECT COUNT(*) FROM TB_USER WHERE EMAIL = NEW.EMAIL AND cod_user <> OLD.COD_USER) > 0 THEN
            SET msg = 'O campo Email já está cadastrado';
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;   
            
            ELSE IF (SELECT COUNT(*) FROM TB_USER WHERE PHONE = NEW.PHONE AND cod_user <> OLD.COD_USER) > 0 THEN
                 SET msg = 'O campo telefone já está cadastrado';
                 SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
                 
            END IF;
            
        END IF;
    END IF;
END$$
