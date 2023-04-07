INSERT INTO TRAIN (serial, carriage_number, capacity)
VALUES ('ABC123', 10, 100),
       ('DEF456', 8, 80),
       ('GHI789', 12, 120);
INSERT INTO STATION (name, city)
VALUES ('Grand Central Station', 'New York'),
       ('Penn Station', 'New York'),
       ('Union Station', 'Washington DC'),
       ('King Cross Station', 'London');
INSERT INTO trip (departure_time, arrival_time, departure_station_id, arrival_station_id, train_serial)
VALUES ('2023-04-07 08:00:00', '2023-04-07 12:00:00', 1, 2, 'ABC123'),
       ('2023-04-08 14:00:00', '2023-04-08 19:00:00', 3, 4, 'DEF456'),
       ('2023-04-09 10:00:00', '2023-04-09 13:00:00', 2, 3, 'GHI789');
