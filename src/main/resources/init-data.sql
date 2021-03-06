DROP TABLE IF EXISTS BOOST_PRODUCT;

CREATE TABLE BOOST_PRODUCT ( PROD_ID VARCHAR(10) NOT NULL DEFAULT '', PROD_NAME VARCHAR(20) NOT NULL DEFAULT '', PROD_QUANTITY INTEGER(6) NOT NULL DEFAULT 0, PROD_UNIT_PRICE NUMERIC(8,2) NOT NULL DEFAULT '0.00', PROD_DESCRIPTION VARCHAR(300) NULL, PRIMARY KEY (PROD_ID) );

INSERT INTO BOOST_PRODUCT (PROD_ID, PROD_NAME, PROD_QUANTITY, PROD_UNIT_PRICE, PROD_DESCRIPTION) VALUES ('1', 'LAPTOP', 10, '700.00', 'TEST LAPTOP');
INSERT INTO BOOST_PRODUCT (PROD_ID, PROD_NAME, PROD_QUANTITY, PROD_UNIT_PRICE, PROD_DESCRIPTION) VALUES ('2', 'PHONE', 20, '500.00', 'TEST PHONE');
INSERT INTO BOOST_PRODUCT (PROD_ID, PROD_NAME, PROD_QUANTITY, PROD_UNIT_PRICE, PROD_DESCRIPTION) VALUES ('3', 'TV', 30, '2000.00', 'TEST TV');
INSERT INTO BOOST_PRODUCT (PROD_ID, PROD_NAME, PROD_QUANTITY, PROD_UNIT_PRICE, PROD_DESCRIPTION) VALUES ('4', 'CAR', 40, '25000.00', 'TEST CAR');