INSERT INTO client (name) VALUES ('Husband');
INSERT INTO client (name) VALUES ('Wife');

INSERT INTO wallet (money, id_client) VALUES
(20.10, SELECT id
  FROM client
 WHERE name = 'Husband');

INSERT INTO wallet (money, id_client) VALUES
(10000.55, SELECT id
  FROM client
 WHERE name = 'Wife');
