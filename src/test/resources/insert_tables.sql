-- Insert data into the 'managers' table
INSERT INTO managers(manager_id, first_name, last_name, manager_status)
VALUES
    ('82d07ab4-7319-4ac0-af54-167663454b48', 'John', 'Doe', 'ACTIVE'),
    ('410aa8af-c670-4d4b-8c5c-704f595ea9b9', 'Jane', 'Smith', 'ACTIVE');

-- Insert data into the 'products' table
INSERT INTO products(product_id, product_name, product_status, product_currency_code, interest_rate, product_limit, manager_id)
VALUES
    ('7cb780c5-7b00-4e89-b8cf-0af4fc4a1a13', 'Product 1', 'ACTIVE', 'USD', '0.05', '100000', '82d07ab4-7319-4ac0-af54-167663454b48'),
    ('b60039b7-6d46-4a40-91ad-4b3d6a2112d6', 'Product 2', 'ACTIVE', 'EUR', '0.03', '50000', '410aa8af-c670-4d4b-8c5c-704f595ea9b9');

-- Insert data into the 'clients' table
INSERT INTO clients(client_id, client_status, tax_code, first_name, last_name, email, address, phone, password, manager_id)
VALUES
    ('a4283a17-e794-4f7f-bf74-0ce24f02bf92', 'ACTIVE', '123456789', 'Alice', 'Johnson', 'alice@example.com', '123 Main St', '1234567890', 'password123', '82d07ab4-7319-4ac0-af54-167663454b48'),
    ('65064d1a-f100-45e4-992a-64a56f715e48', 'ACTIVE', '987654321', 'Bob', 'Smith', 'bob@example.com', '456 Oak St', '9876543210', 'password456', '410aa8af-c670-4d4b-8c5c-704f595ea9b9');

-- Insert data into the 'accounts' table
INSERT INTO accounts(account_id, account_name, account_type, account_status, account_balance, account_currency_code, client_id)
VALUES
    ('50991763-ce2c-4862-827f-8cfacbd261e7', 'Porsche for my Wife', 'SAVINGS', 'ACTIVE', '98826.78', 'USD', 'a4283a17-e794-4f7f-bf74-0ce24f02bf92'),
    ('2115a101-bae8-49b1-87b5-1de5265ea492', 'Alfa Romeo for my Husband', 'CURRENT', 'ACTIVE', '142982.33', 'EUR', '65064d1a-f100-45e4-992a-64a56f715e48');

-- Insert data into the 'agreements' table
INSERT INTO agreements(agreement_id, interest_rate, agreement_status, agreement_sum, product_id, account_id)
VALUES
    ('95e4be3a-6551-4c0f-a632-662a049a40b8', '0.05', 'ACTIVE', '50000', '7cb780c5-7b00-4e89-b8cf-0af4fc4a1a13', '50991763-ce2c-4862-827f-8cfacbd261e7'),
    ('0746b1c1-e846-4070-aa47-2cd728f9fddd', '0.03', 'ACTIVE', '30000', 'b60039b7-6d46-4a40-91ad-4b3d6a2112d6', '2115a101-bae8-49b1-87b5-1de5265ea492');

-- Insert data into the 'cards' table
INSERT INTO cards(card_id, card_type, card_number, card_holder, cvv, account_id, client_id)
VALUES
    ('e854afdd-6db7-4475-8350-f98ed3ab4f7f', 'MASTERCARD', '1234567890123456', 'Alice Johnson', '123', '50991763-ce2c-4862-827f-8cfacbd261e7', 'a4283a17-e794-4f7f-bf74-0ce24f02bf92'),
    ('625de6a7-f586-4128-b55e-f9c443bde1e4', 'VISA', '9876543210987654', 'Bob Smith', '456', '2115a101-bae8-49b1-87b5-1de5265ea492', '65064d1a-f100-45e4-992a-64a56f715e48');

-- Insert data into the 'transactions' table
INSERT INTO transactions(transaction_id, transaction_type, transaction_amount, currency_code, transaction_description, debit_account_id, credit_account_id)
VALUES
    ('fef3af1f-7dc6-423d-a475-87d46d798e0e', 'TRANSFER', '5000.00', 'USD', 'Purchase', '50991763-ce2c-4862-827f-8cfacbd261e7', '2115a101-bae8-49b1-87b5-1de5265ea492'),
    ('d73ca86b-402f-4af5-a7c4-861e830f1253', 'REFUND', '3000.00', 'EUR', 'Refund', '2115a101-bae8-49b1-87b5-1de5265ea492', '50991763-ce2c-4862-827f-8cfacbd261e7');

-- Insert data into the 'managers' table
INSERT INTO managers(manager_id, first_name, last_name, manager_status)
VALUES
    ('54b71978-4c41-4399-af14-68bc0654e7e4', 'Michael', 'Anderson', 'ACTIVE'),
    ('83cab840-8c4f-494c-8e77-40ad297c2617', 'Emily', 'Wilson', 'ACTIVE');

-- Insert data into the 'products' table
INSERT INTO products(product_id, product_name, product_status, product_currency_code, interest_rate, product_limit, manager_id)
VALUES
    ('ac5ce679-311d-4523-9581-3ce3e148bd77', 'Product 3', 'ACTIVE', 'GBP', '0.02', '75000', '54b71978-4c41-4399-af14-68bc0654e7e4'),
    ('066612fa-fb88-48be-b36a-0b9ec00252e1', 'Product 4', 'ACTIVE', 'USD', '0.04', '120000', '83cab840-8c4f-494c-8e77-40ad297c2617');

-- Insert data into the 'clients' table
INSERT INTO clients(client_id, client_status, tax_code, first_name, last_name, email, address, phone, password, manager_id)
VALUES
    ('0e121aad-6431-46ed-8f3d-5059f637f1a3', 'ACTIVE', '654321789', 'David', 'Miller', 'david@example.com', '789 Elm St', '9876543210', 'password789', '54b71978-4c41-4399-af14-68bc0654e7e4'),
    ('3f7e47d6-c0a1-4710-9b64-37e27dc5f411', 'ACTIVE', '987123654', 'Sophia', 'Davis', 'sophia@example.com', '321 Pine St', '1239876540', 'password123', '83cab840-8c4f-494c-8e77-40ad297c2617');

-- Insert data into the 'accounts' table
INSERT INTO accounts(account_id, account_name, account_type, account_status, account_balance, account_currency_code, client_id)
VALUES
    ('80a1b0ee-2533-4642-a9ce-4b5cc28713d7', 'BMW for David', 'CURRENT', 'ACTIVE', '55000.45', 'GBP', '0e121aad-6431-46ed-8f3d-5059f637f1a3'),
    ('c511554c-4196-44ce-98c2-7c5debe9f97a', 'Tesla for Sophia', 'SAVINGS', 'ACTIVE', '98000.67', 'USD', '3f7e47d6-c0a1-4710-9b64-37e27dc5f411');

-- Insert data into the 'agreements' table
INSERT INTO agreements(agreement_id, interest_rate, agreement_status, agreement_sum, product_id, account_id)
VALUES
    ('a6c81659-ee43-4216-a36e-29ebee500efb', '0.02', 'ACTIVE', '30000', 'ac5ce679-311d-4523-9581-3ce3e148bd77', '80a1b0ee-2533-4642-a9ce-4b5cc28713d7'),
    ('72b5cc72-76d8-4c5a-a273-9e532cbce487', '0.04', 'ACTIVE', '50000', '066612fa-fb88-48be-b36a-0b9ec00252e1', 'c511554c-4196-44ce-98c2-7c5debe9f97a');

-- Insert data into the 'cards' table
INSERT INTO cards(card_id, card_type, card_number, card_holder, cvv, account_id, client_id)
VALUES
    ('e1579f68-41cb-4be7-8188-3ee9bd895a94', 'VISA', '5432109876543210', 'David Miller', '789', '80a1b0ee-2533-4642-a9ce-4b5cc28713d7', '0e121aad-6431-46ed-8f3d-5059f637f1a3'),
    ('e2a02048-5e9c-44e5-baf0-e98d2ef6bf28', 'MASTERCARD', '1234987654325678', 'Sophia Davis', '456', 'c511554c-4196-44ce-98c2-7c5debe9f97a', '3f7e47d6-c0a1-4710-9b64-37e27dc5f411');

-- Insert data into the 'transactions' table
INSERT INTO transactions(transaction_id, transaction_type, transaction_amount, currency_code, transaction_description, debit_account_id, credit_account_id)
VALUES
    ('5cafc175-46dd-4144-9c11-b30e8daece03', 'REFUND', '3000.00', 'GBP', 'Purchase', '80a1b0ee-2533-4642-a9ce-4b5cc28713d7', 'c511554c-4196-44ce-98c2-7c5debe9f97a'),
    ('08c2a15b-4468-4423-a03f-428cdf8edae7', 'TRANSFER', '1500.00', 'USD', 'Refund', 'c511554c-4196-44ce-98c2-7c5debe9f97a', '80a1b0ee-2533-4642-a9ce-4b5cc28713d7');
