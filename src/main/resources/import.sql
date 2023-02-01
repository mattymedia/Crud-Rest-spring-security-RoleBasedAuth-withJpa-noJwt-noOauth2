INSERT INTO roles(role_name) VALUES('ROLE_ADMIN');
INSERT INTO roles(role_name) VALUES('ROLE_USER');

INSERT INTO books(title, author, published_on, create_on) VALUES('La Maria', 'Jorge Isaacs', '1867-06-08', '2023-01-19');
INSERT INTO books(title, author, published_on, create_on) VALUES('Cien años de soledad', 'Gabriel García Márquez', '1967-03-05', '2023-01-19');

INSERT INTO users(username, password) VALUES('admin', '$2a$10$2Mz9.Ag6yMS.gK5NEUENV.mPNcnSe1ia6rhaxEXhQT5oX2O5vtgGu');
INSERT INTO users(username, password) VALUES('maikol', '$2a$10$G7ut3Appm10Akik1DuIL.ek3a8qHrxEoLxiUg9o3bdbU8uZ12mNje');

INSERT INTO role_user(user_id, role_id) VALUES(1,1);
INSERT INTO role_user(user_id, role_id) VALUES(1,2);
INSERT INTO role_user(user_id, role_id) VALUES(2,2);