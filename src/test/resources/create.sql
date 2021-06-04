DROP TABLE IF exists CLIENTS_TEST;
DROP TABLE IF exists BANK_CARDS_TEST;
CREATE TABLE CLIENTS_TEST
(
    id int PRIMARY KEY,
    full_name varchar,
    phone varchar,
    bank_account_number varchar
);

CREATE TABLE BANK_CARDS_TEST
(

    bank_account_number varchar,
    number_card varchar primary key,
    balance double
);
