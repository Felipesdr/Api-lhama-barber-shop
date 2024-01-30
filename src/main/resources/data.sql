
INSERT INTO barber (name, phone, description, active)
VALUES ('Robson motoserra', '123456789', 'Cortes de cabelo tão radicais que parecem feitos com uma motosserra!',  true);

INSERT INTO barber (name, phone, description, active)
VALUES ('Pedro Cabeludo', '123456789', 'Barbeiro experiente, pronto para transformar seu visual com estilo e precisão.',  true);

INSERT INTO barber (name, phone, description, active)
VALUES ('Leôncio Lenhador', '123456789', 'Barbeiro experiente, pronto para transformar seu visual com estilo e precisão.',  true);

--Insert Barber unavailable time
INSERT INTO barber_unavailable_time(description, start, finish, id_barber, active)
VALUES ('Carnaval', '2024-02-10 09:00:00', '2024-02-13 18:00:00', 1, TRUE);

INSERT INTO barber_unavailable_time(description, start, finish, id_barber, active)
VALUES ('Carnaval', '2024-02-10 09:00:00', '2024-02-13 18:00:00', 3, TRUE);

--Insert Client Data
INSERT INTO CLIENT (name, email, phone, active)
VALUES ('Vera Fischer', 'verafischer@gmail.com', '123-456-7890', true);

INSERT INTO CLIENT (name, email, phone, active)
VALUES ('Agnaldo Timoteo', 'agnaldo@gmail.com', '123-456-7890', true);

INSERT INTO CLIENT (name, email, phone, active)
VALUES ('Bruna Marquezine', 'bruna@gmail.com', '123-456-7890', true);

--Insert Service data
INSERT INTO service (name, description, duration, price, active)
VALUES ('Cabelo', 'Corte de cabelo', 45, 45.00, true);

INSERT INTO service (name, description, duration, price, active)
VALUES ('Barba', 'Corte de barba', 45, 45.00, true);

INSERT INTO service (name, description, duration, price, active)
VALUES ('Sobrancelha', 'Alinhamento de sobrancelha', 10, 15.00, true);

--Insert Unavailable time data
INSERT INTO unavailable_time (description, start, finish, active)
VALUES ('Sexta feira santa', '2024-03-29 09:00:00', '2024-03-29 18:00:00', true);

--Insert Schedule status data
INSERT INTO schedule_status (status)
VALUES ('pending');

INSERT INTO schedule_status (status)
VALUES ('confirmed');

INSERT INTO schedule_status (status)
VALUES ('executing');

INSERT INTO schedule_status (status)
VALUES ('finished');

INSERT INTO schedule_status (status)
VALUES ('canceled');

--Insert Schedule data
INSERT INTO schedule(id_client, id_service, id_barber, start, finish, id_schedule_status)
VALUES (1, 1, 1, '2024-03-03 09:00:00', '2024-03-03 09:45:00', 2);

INSERT INTO schedule(id_client, id_service, id_barber, start, finish, id_schedule_status)
VALUES (2, 1, 2, '2024-03-03 09:00:00', '2024-03-03 09:45:00', 2);

INSERT INTO schedule(id_client, id_service, id_barber, start, finish, id_schedule_status)
VALUES (1, 1, 3, '2024-05-05 09:00:00', '2023-05-05 09:45:00', 2);

INSERT INTO schedule(id_client, id_service, id_barber, start, finish, id_schedule_status)
VALUES (1, 1, 3, '2024-06-06 09:00:00', '2023-06-06 09:45:00', 2);

INSERT INTO schedule(id_client, id_service, id_barber, start, finish, id_schedule_status)
VALUES (1, 1, 3, '2024-07-07 09:00:00', '2023-07-07 09:45:00', 5);

INSERT INTO users(name, email, password, phone, role)
VALUES('Jali Enra Bei', 'jalienrabei@gmail.com', '$2a$12$F26Oa/jGC.Gbc53eCGi6b.xmZEIINiPEsZ/1F4L1QHOtNX9cDgXyG', '16992455588', 'ADMIN');








