create table if not exists managers
(
    manager_id     uuid primary key,
    first_name     varchar(255),
    last_name      varchar(255),
    manager_status varchar(50),
    created_at     timestamp,
    updated_at     timestamp
);

create table if not exists products
(
    product_id            uuid primary key,
    product_name          varchar(255),
    product_status        varchar(50),
    product_currency_code varchar(3),
    interest_rate         decimal(3, 2),
    product_limit         decimal(16, 0),
    created_at            timestamp,
    updated_at            timestamp,
    manager_id            uuid,
    foreign key (manager_id) references managers (manager_id)
);

create table if not exists clients
(
    client_id     uuid primary key,
    client_status varchar(50),
    tax_code      varchar(20),
    first_name    varchar(255),
    last_name     varchar(255),
    email         varchar(255),
    address       varchar(255),
    phone         varchar(20),
    password      varchar(255),
    created_at    timestamp,
    updated_at    timestamp,
    manager_id    uuid,
    foreign key (manager_id) references managers (manager_id)
);

create table if not exists accounts
(
    account_id            uuid primary key,
    account_name          varchar(255),
    account_type          varchar(50),
    account_status        varchar(50),
    account_balance       decimal(15, 2),
    account_currency_code varchar(3),
    created_at            timestamp,
    updated_at            timestamp,
    client_id             uuid,
    foreign key (client_id) references clients (client_id)
);

create table if not exists agreements
(
    agreement_id     uuid primary key,
    interest_rate    decimal(3, 2),
    agreement_status varchar(50),
    agreement_sum    decimal(16, 2),
    created_at       timestamp,
    updated_at       timestamp,
    product_id       uuid,
    account_id       uuid,
    foreign key (product_id) references products (product_id),
    foreign key (account_id) references accounts (account_id)
);

create table if not exists cards
(
    card_id         uuid primary key,
    card_type       varchar(50),
    card_number     varchar(20),
    card_holder     varchar(255),
    cvv             varchar(3),
    expiration_date timestamp,
    created_at      timestamp,
    account_id      uuid,
    client_id       uuid,
    foreign key (account_id) references accounts(account_id),
    foreign key (client_id) references clients(client_id)
);

create table if not exists transactions(
    transaction_id uuid primary key ,
    transaction_type varchar(50),
    transaction_amount decimal(16,2),
    currency_code varchar(3),
    transaction_description varchar(255),
    created_at timestamp,
    debit_account_id uuid,
    credit_account_id uuid,
    foreign key (debit_account_id) references accounts(account_id),
    foreign key (credit_account_id) references accounts(account_id)
);
