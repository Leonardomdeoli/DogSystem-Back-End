USE db_dogsystem ;

INSERT  INTO 
   tb_uf (cod_uf, sigla) 
VALUES (1, 'MG');

INSERT INTO 
   tb_city (cod_city, name, cod_uf) 
VALUES (1,'Uberlândia',1);
    
INSERT INTO
   tb_neighborhood ( cod_neigh, name, cod_city) 
VALUES (1,'Laranjeiras',1),
	   (2,'Shopping Park',1);
    
INSERT INTO 
   tb_address (cod_address,name ,zipcode, cod_neigh) 
VALUES (1, 'Rua China','38410250', 1),
       (2, 'Rua Agnelo Fagundes da Costa','38411470', 2);

INSERT INTO 
   tb_user (cod_user, complement, cpf, email, name, number, password, phone, cod_address)
VALUES (1,null,'33344470736', 'leonardomdeoli@gmail.com', 'Leonardo Mendes de Oliveira',120, 
'0ccf5342a28ed1889254c7cf6ae8e20ac4014477d75d22136ba4254ab1781e4be5fe00058e1b4e81','3432221276',1),
(2,null,'24672434001', 'dogsystemudi@gmail.com', 'DogSystem',20, 
'0ccf5342a28ed1889254c7cf6ae8e20ac4014477d75d22136ba4254ab1781e4be5fe00058e1b4e81','3432226276',2);

INSERT INTO 
    tb_permission (cod_per, role) 
VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_EMPLOYEE'), (3, 'ROLE_USER');

INSERT INTO 
    tb_user_permission (cod_per, cod_user) 
VALUES (1 ,1);