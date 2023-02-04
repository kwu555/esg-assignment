drop table if exists customer;
DROP SEQUENCE IF EXISTS id_seq;
CREATE SEQUENCE id_seq;

CREATE TABLE customer (
  id INT default id_seq.nextval PRIMARY KEY,
  customer_ref VARCHAR(10) NOT NULL,
  customer_name VARCHAR(30) NOT NULL,
  address_line1 VARCHAR(30) NOT NULL,
  address_line2 VARCHAR(30) NOT NULL,
  town VARCHAR(30) NOT NULL,
  county VARCHAR(30) NOT NULL,
  country VARCHAR(30) NOT NULL,
  postcode VARCHAR(10) NOT NULL,

  UNIQUE(customer_ref)
);