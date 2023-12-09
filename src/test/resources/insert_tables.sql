INSERT INTO managers(manager_id, first_name, last_name, manager_status)
VALUES
    ('82d07ab4-7319-4ac0-af54-167663454b48', 'John', 'Doe', 'ACTIVE'),
    ('410aa8af-c670-4d4b-8c5c-704f595ea9b9', 'Jane', 'Smith', 'ACTIVE');

INSERT INTO products(product_id, product_name, product_status, product_currency_code, interest_rate, product_limit, manager_id)
VALUES
    ('7cb780c5-7b00-4e89-b8cf-0af4fc4a1a13', 'Product 1', 'AVAILABLE', 'USD', '0.05', '100000', '82d07ab4-7319-4ac0-af54-167663454b48'),
    ('b60039b7-6d46-4a40-91ad-4b3d6a2112d6', 'Product 2', 'AVAILABLE', 'EUR', '0.03', '50000', '410aa8af-c670-4d4b-8c5c-704f595ea9b9');

INSERT INTO clients(client_id, client_status, tax_code, first_name, last_name, email, address, phone, password, manager_id)
VALUES
    ('a4283a17-e794-4f7f-bf74-0ce24f02bf92', 'ACTIVE', '123456789', 'Alice', 'Johnson', 'alice@example.com', '123 Main St', '1234567890', 'password123', '82d07ab4-7319-4ac0-af54-167663454b48'),
    ('65064d1a-f100-45e4-992a-64a56f715e48', 'ACTIVE', '987654321', 'Bob', 'Smith', 'bob@example.com', '456 Oak St', '9876543210', 'password456', '410aa8af-c670-4d4b-8c5c-704f595ea9b9');

INSERT INTO accounts(account_id, account_name, account_type, account_status, account_balance, account_currency_code, client_id)
VALUES
    ('50991763-ce2c-4862-827f-8cfacbd261e7', 'Porsche for my Wife', 'SAVINGS', 'ACTIVE', '98826.78', 'USD', 'a4283a17-e794-4f7f-bf74-0ce24f02bf92'),
    ('2115a101-bae8-49b1-87b5-1de5265ea492', 'Alfa Romeo for my Husband', 'CURRENT', 'ACTIVE', '142982.33', 'EUR', '65064d1a-f100-45e4-992a-64a56f715e48');

INSERT INTO agreements(agreement_id, interest_rate, agreement_status, agreement_sum, product_id, account_id)
VALUES
    ('95e4be3a-6551-4c0f-a632-662a049a40b8', '0.05', 'ACTIVE', '50000', '7cb780c5-7b00-4e89-b8cf-0af4fc4a1a13', '50991763-ce2c-4862-827f-8cfacbd261e7'),
    ('0746b1c1-e846-4070-aa47-2cd728f9fddd', '0.03', 'ACTIVE', '30000', 'b60039b7-6d46-4a40-91ad-4b3d6a2112d6', '2115a101-bae8-49b1-87b5-1de5265ea492');

INSERT INTO cards(card_id, card_type, card_number, card_holder, cvv, account_id, client_id)
VALUES
    ('e854afdd-6db7-4475-8350-f98ed3ab4f7f', 'MASTERCARD', '1234567890123456', 'Alice Johnson', '123', '50991763-ce2c-4862-827f-8cfacbd261e7', 'a4283a17-e794-4f7f-bf74-0ce24f02bf92'),
    ('625de6a7-f586-4128-b55e-f9c443bde1e4', 'VISA', '9876543210987654', 'Bob Smith', '456', '2115a101-bae8-49b1-87b5-1de5265ea492', '65064d1a-f100-45e4-992a-64a56f715e48');

INSERT INTO transactions(transaction_id, transaction_type, transaction_amount, currency_code, transaction_description, debit_account_id, credit_account_id)
VALUES
    ('fef3af1f-7dc6-423d-a475-87d46d798e0e', 'TRANSFER', '5000.00', 'USD', 'Purchase', '50991763-ce2c-4862-827f-8cfacbd261e7', '2115a101-bae8-49b1-87b5-1de5265ea492'),
    ('d73ca86b-402f-4af5-a7c4-861e830f1253', 'REFUND', '3000.00', 'EUR', 'Refund', '2115a101-bae8-49b1-87b5-1de5265ea492', '50991763-ce2c-4862-827f-8cfacbd261e7');
