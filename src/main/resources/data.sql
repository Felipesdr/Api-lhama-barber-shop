--Insert User data
INSERT INTO users(name, email, password, phone, role, active)
VALUES('Bruno Motossera', 'bruno@gmail.com', '$2y$10$ABNIPtqFQUckSF6tHo6Xg.Sn0hsr6uM4es7J/FQn7MTkW2svamMle', '16992334555', 'BARBER', true);

INSERT INTO users(name, email, password, phone, role, active)
VALUES('Aruã Navalha', 'aruã@gmail.com', '$2y$10$ABNIPtqFQUckSF6tHo6Xg.Sn0hsr6uM4es7J/FQn7MTkW2svamMle', '16992334555', 'BARBER', true);

INSERT INTO users(name, email, password, phone, role, active)
VALUES('Edson Blumenau', 'edson@gmail.com', '$2y$10$ABNIPtqFQUckSF6tHo6Xg.Sn0hsr6uM4es7J/FQn7MTkW2svamMle', '16992334555', 'BARBER', true);

INSERT INTO users(name, email, password, phone, role, active)
VALUES('Kleberson mota', 'kleberson@gmail.com', '$2y$10$ABNIPtqFQUckSF6tHo6Xg.Sn0hsr6uM4es7J/FQn7MTkW2svamMle', '16992334555', 'CLIENT', true);

INSERT INTO users(name, email, password, phone, role, active)
VALUES('Jonas Jeferson', 'jonas@gmail.com', '$2y$10$ABNIPtqFQUckSF6tHo6Xg.Sn0hsr6uM4es7J/FQn7MTkW2svamMle', '16992334555', 'CLIENT', true);

INSERT INTO users(name, email, password, phone, role, active)
VALUES('Aribã Juajara', 'aribã@gmail.com', '$2y$10$ABNIPtqFQUckSF6tHo6Xg.Sn0hsr6uM4es7J/FQn7MTkW2svamMle', '16992334555', 'CLIENT', true);

INSERT INTO users(name, email, password, phone, role, active)
VALUES('DONO', 'dono@gmail.com', '$2y$10$ABNIPtqFQUckSF6tHo6Xg.Sn0hsr6uM4es7J/FQn7MTkW2svamMle', '16992334555', 'ADMIN', true);

--Insert Barber unavailable time
INSERT INTO barber_unavailable_time(description, start, finish, id_barber, active)
VALUES ('Carnaval', '2024-02-10 09:00:00', '2024-02-13 18:00:00', 1, TRUE);

INSERT INTO barber_unavailable_time(description, start, finish, id_barber, active)
VALUES ('Carnaval', '2024-02-10 09:00:00', '2024-02-13 18:00:00', 3, TRUE);

INSERT INTO barber_unavailable_time(description, start, finish, id_barber, active)
VALUES ('Carnaval', '2024-03-29 14:30:00', '2024-03-29 15:00:00', 3, TRUE);

INSERT INTO barber_unavailable_time(description, start, finish, id_barber, active)
VALUES ('Carnaval', '2024-03-29 14:30:00', '2024-03-29 15:00:00', 2, TRUE);

INSERT INTO barber_unavailable_time(description, start, finish, id_barber, active)
VALUES ('Carnaval', '2024-03-29 14:30:00', '2024-03-29 15:00:00', 1, TRUE);

INSERT INTO barber_unavailable_time(description, start, finish, id_barber, active)
VALUES ('Carnaval', '2024-03-29 16:00:00', '2024-03-30 16:30:00', 1, TRUE);

--Insert Service data
INSERT INTO service (name, description, duration, price, active)
VALUES ('Cabelo', 'Corte de cabelo', 45, 45.00, true);

INSERT INTO service (name, description, duration, price, active)
VALUES ('Barba', 'Corte de barba', 45, 45.00, true);

INSERT INTO service (name, description, duration, price, active)
VALUES ('Sobrancelha', 'Alinhamento de sobrancelha', 10, 15.00, true);

--Insert Unavailable time data
INSERT INTO unavailable_time (description, start, finish, active)
VALUES ('Sexta feira santa', '2025-03-29 09:00:00', '2025-03-29 12:00:00', true);

--Insert Schedule data
INSERT INTO schedule(id_client, id_service, id_barber, start, finish, status)
VALUES (1, 1, 1, '2025-03-29 13:00:00', '2025-03-29 13:30:00', 'CONFIRMED');

INSERT INTO schedule(id_client, id_service, id_barber, start, finish, status)
VALUES (1, 1, 2, '2025-03-29 13:00:00', '2025-03-29 13:30:00', 'CONFIRMED');

INSERT INTO schedule(id_client, id_service, id_barber, start, finish, status)
VALUES (1, 1, 3, '2025-03-29 13:00:00', '2025-03-29 13:30:00', 'CONFIRMED');

INSERT INTO schedule(id_client, id_service, id_barber, start, finish, status)
VALUES (1, 1, 3, '2025-03-29 13:30:00', '2025-03-29 14:00:00', 'CONFIRMED');

INSERT INTO schedule(id_client, id_service, id_barber, start, finish, status)
VALUES (1, 1, 2, '2025-03-29 13:30:00', '2025-03-29 14:00:00', 'CONFIRMED');

INSERT INTO schedule(id_client, id_service, id_barber, start, finish, status)
VALUES (1, 1, 3, '2025-07-07 09:00:00', '2025-07-07 09:30:00', 'CONFIRMED');