DROP TABLE IF EXISTS client;
CREATE TABLE client (
  id identity NOT NULL PRIMARY KEY,
  name varchar(50) DEFAULT NULL
);

DROP TABLE IF EXISTS wallet;
CREATE TABLE wallet (
  id identity NOT NULL PRIMARY KEY,
  money decimal(20,2) NOT NULL DEFAULT 0,
  id_client int,
  FOREIGN KEY (id_client) REFERENCES client(id)
);
