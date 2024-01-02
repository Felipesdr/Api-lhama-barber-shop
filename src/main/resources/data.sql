--Insert barber data
MERGE INTO barber KEY (id_barber)
VALUES (1, 'Robson motoserra', '123456789', 'Cortes de cabelo tão radicais que parecem feitos com uma motosserra!',  true);

MERGE INTO barber KEY (id_barber)
VALUES (2, 'Pedro Cabeludo', '123456789', 'Barbeiro experiente, pronto para transformar seu visual com estilo e precisão.',  true);

MERGE INTO barber KEY (id_barber)
VALUES (3, 'Leôncio Lenhador', '123456789', 'Barbeiro experiente, pronto para transformar seu visual com estilo e precisão.',  true);

--Insert Barber unavailable time
MERGE INTO barber_unavailable_time KEY (id_barber_unavailable_time)
VALUES (1, 'Carnaval', '2024-02-10 09:00:00', '2024-02-13 18:00:00', 1, TRUE);

MERGE INTO barber_unavailable_time KEY (id_barber_unavailable_time)
VALUES (2, 'Carnaval', '2024-02-10 09:00:00', '2024-02-13 18:00:00', 3, TRUE);

--Insert Client Data
MERGE INTO CLIENT KEY (id_client)
VALUES (1, 'Vera Fischer', 'verafischer@gmail.com', '123-456-7890', true);

MERGE INTO CLIENT KEY (id_client)
VALUES (2, 'Agnaldo Timoteo', 'agnaldo@gmail.com', '123-456-7890', true);

MERGE INTO CLIENT KEY (id_client)
VALUES (3, 'Bruna Marquezine', 'bruna@gmail.com', '123-456-7890', true);

--Insert Service data
MERGE INTO service KEY (id_service)
VALUES (1, 'Cabelo', 'Corte de cabelo', 45, 45.00, true);

MERGE INTO service KEY (id_service)
VALUES (2, 'Barba', 'Corte de barba', 45, 45.00, true);

MERGE INTO service KEY (id_service)
VALUES (3, 'Sobrancelha', 'Alinhamento de sobrancelha', 10, 15.00, true);

--Insert Unavailable time data
MERGE INTO unavailable_time KEY (id_unavailable_time)
VALUES (1, 'Sexta feira santa', '2024-03-29 09:00:00', '2024-03-29 18:00:00', true);

--Insert Schedule status data
MERGE INTO schedule_status KEY (id_schedule_status)
VALUES (1, 'pending');

MERGE INTO schedule_status KEY (id_schedule_status)
VALUES (2, 'confirmed');

MERGE INTO schedule_status KEY (id_schedule_status)
VALUES (3, 'executing');

MERGE INTO schedule_status KEY (id_schedule_status)
VALUES (4, 'finished');

MERGE INTO schedule_status KEY (id_schedule_status)
VALUES (5, 'canceled');






