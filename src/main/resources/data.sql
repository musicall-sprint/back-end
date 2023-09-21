INSERT INTO instrument (name) VALUES
('Guitarra'),
('Baixo'),
('Bateria'),
('Teclado'),
('Piano'),
('Violino'),
('Trompete'),
('Saxofone'),
('Clarinete'),
('Flauta'),
('Violão'),
('Cavaquinho'),
('Pandeiro'),
('Surdo'),
('Tamborim');

INSERT INTO user (birth_date, confirmation_token, cpf, email, is_confirmed, is_password_reset_requested, last_name, name, number_of_events, password, password_reset_token, telephone, type)
VALUES ('2000-01-01', 'c111cf08-a6e8-4611-b624-e1d4c4a45978', '12345678901', 'john.doe@example.com', true, false, 'Doe', 'John', 0, '$2a$10$H9BHHHpnGo2lrMCeGGcRWuZC5Pt9pL9K6dGz3yNCorqw0LoM1l.fS', '', '99999999999', 'ORG'),
       ('2000-01-01', 'c111cf08-a6e8-4611-b624-e1d4c4a45978', '12345678903', 'john2.doe@example.com', true, false, 'Doe', 'John', 0, '$2a$10$IgJaWyQfnW1Vu7huiZ5Ht.hrWb0xKhGWIrTPciOzriBdxslN.4qXK', '', '99919999999', 'MSC');

INSERT INTO musician (user_id, cep, description)
VALUES (2, '03366-010', 'Tá ligado né pai');

INSERT INTO event (user_id, name, cep, complement, event_date, duration_hours, finalized)
VALUES
  (1, 'My Event - AL', '01414-001', 'My Complement', '2024-01-02', 2, false),
  (1, 'My Event - AL', '57000-000', 'My Complement', '2024-01-02', 2, false),
  (1, 'My Event - AM', '69000-000', 'My Complement', '2024-01-04', 2, false),
  (1, 'My Event - CE', '60000-000', 'My Complement', '2024-01-06', 2, false),
  (1, 'My Event - GO', '74000-000', 'My Complement', '2024-01-09', 2, false),
  (1, 'My Event - MA', '65000-000', 'My Complement', '2024-01-10', 2, false),
  (1, 'My Event - MT', '78000-000', 'My Complement', '2024-01-11', 2, false),
  (1, 'My Event - PB', '58000-000', 'My Complement', '2024-01-15', 2, false),
  (1, 'My Event - PE', '50000-000', 'My Complement', '2024-01-17', 2, false),
  (1, 'My Event - PI', '64000-000', 'My Complement', '2024-01-18', 2, false),
  (1, 'My Event - RJ', '20000-000', 'My Complement', '2024-01-19', 2, false),
  (1, 'My Event - RN', '59000-000', 'My Complement', '2024-01-20', 2, false),
  (1, 'My Event - RS', '90000-000', 'My Complement', '2024-01-21', 2, false);

INSERT INTO event_job (event_id, instrument_id, musician_id)
VALUES
  (1, 2, NULL),
  (1, 3, NULL),
  (1, 4, NULL),
  (1, 5, NULL),
  (1, 6, NULL),

  (2, 2, NULL),
  (2, 3, NULL),
  (2, 4, NULL),
  (2, 5, NULL),
  (2, 6, NULL),
  (3, 2, NULL),
  (3, 3, NULL),
  (3, 4, NULL),
  (3, 5, NULL),
  (3, 6, NULL),

  (11, 2, NULL),
  (11, 3, NULL),
  (11, 3, NULL);

INSERT INTO musician_instrument (musician_id, instrument_id) VALUES
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6);

INSERT INTO job_request (event_job_id, musician_id, organizer_confirmed, musician_confirmed) VALUES
(1, 1, 0, 1),
(2, 1, 0, 1);

INSERT INTO notification (job_request_id, notification_type, user_id) VALUES
(1, 1, 1),
(2, 1,1);
