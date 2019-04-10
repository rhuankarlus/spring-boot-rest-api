CREATE TABLE audit
(
  id        INT          NOT NULL,
  url       VARCHAR(255),
  content   VARCHAR(255) NOT NULL,
  type      INT          NOT NULL,
  date_time TIMESTAMP,
  PRIMARY KEY (id)
);