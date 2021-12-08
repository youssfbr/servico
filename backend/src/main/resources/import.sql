INSERT INTO client (name, cpf, register_date) VALUES ('Deus', '30939054000', now());
INSERT INTO client (name, cpf, register_date) VALUES ('Jesus', '30939054000', now());
INSERT INTO client (name, cpf, register_date) VALUES ('Alisson Youssf', '30939054000', now());
INSERT INTO client (name, cpf, email, birth_date, note, register_date) VALUES ('Alisson Youssf', '30939054000', 'youssfbr@gmail.com', '1977-04-20', 'est6', now());
INSERT INTO client (name, cpf, email, birth_date, note, register_date) VALUES ('Alexandre Ester', '30939054000', 'alexandre@gmail.com', '1977-04-20', 'fgh', now());

INSERT INTO TELEPHONE (TELEPHONE, CLIENT_ID) VALUES ('9987-0548', 5);

INSERT INTO brand (name) VALUES ('Sony');
INSERT INTO brand (name) VALUES ('Semp Toshiba');

INSERT INTO equipament (equipament_type, client_id, brand_id, MODEL, SERIAL, NOTE, REGISTER_DATE) VALUES (0, 1, 1, 'vaio', 'fdsglkfg', 'teste', NOW());
INSERT INTO equipament (equipament_type, client_id, brand_id, MODEL, SERIAL, NOTE, REGISTER_DATE) VALUES (0, 5, 2, 'STI  IS1442', 'Não dá pra der', 'teste2', NOW());
