CREATE USER qa WITH SUPERUSER PASSWORD 'qa';
CREATE DATABASE qa OWNER qa;

-- Switch to using database qa
\c qa

CREATE SCHEMA test_results AUTHORIZATION qa;

CREATE TABLE test_results.team (
  name VARCHAR(255) NOT NULL,
  short_name VARCHAR(20) NOT NULL,
  email_address VARCHAR(255),
  active BOOLEAN NOT NULL DEFAULT TRUE,
  CONSTRAINT pk_team PRIMARY KEY (name),
  CONSTRAINT unique_short_name UNIQUE (short_name)
);

-- Insert the default team so it can be used for default module assignments
INSERT INTO test_results.team
(name, short_name)
VALUES
  ('Unknown', 'unknown');

GRANT ALL PRIVILEGES ON SCHEMA test_results TO qa;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA test_results TO qa;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA test_results TO qa;