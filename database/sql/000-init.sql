CREATE USER service WITH SUPERUSER PASSWORD 'service';
CREATE DATABASE service OWNER service;

-- Switch to using database service
\c service

CREATE SCHEMA system AUTHORIZATION service

  CREATE TABLE system.user (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email_address VARCHAR(255)
  );

CREATE TABLE system.transfer (
  id SERIAL PRIMARY KEY,
  user_id INTEGER,
  amount INTEGER,
  FOREIGN KEY (user_id) REFERENCES system.user (id)
);

-- Insert the default user
INSERT INTO system.user
(first_name, last_name, email_address)
VALUES
  ('Adam', 'Karpowich', 'akarpowich94@gmail.com');

GRANT ALL PRIVILEGES ON SCHEMA system TO service;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA system TO service;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA system TO service;