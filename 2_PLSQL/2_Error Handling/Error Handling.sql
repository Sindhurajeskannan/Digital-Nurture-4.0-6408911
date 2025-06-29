BEGIN
    EXECUTE IMMEDIATE 'CREATE SEQUENCE Transactions_seq START WITH 100 INCREMENT BY 1';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE = -955 THEN NULL;
        ELSE RAISE;
        END IF;
END;
/

CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_from_account_id IN NUMBER,
    p_to_account_id IN NUMBER,
    p_amount IN NUMBER
)
IS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance
    FROM Accounts
    WHERE AccountID = p_from_account_id
    FOR UPDATE;

    IF v_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds in account ID ' || p_from_account_id);
    END IF;

    UPDATE Accounts
    SET Balance = Balance - p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_from_account_id;

    UPDATE Accounts
    SET Balance = Balance + p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_to_account_id;

    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (Transactions_seq.NEXTVAL, p_from_account_id, SYSDATE, p_amount, 'Transfer');

    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (Transactions_seq.NEXTVAL, p_to_account_id, SYSDATE, p_amount, 'Transfer');

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transfer of ' || p_amount || ' successful from ' || p_from_account_id || ' to ' || p_to_account_id);
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error during fund transfer: ' || SQLERRM);
END;
/

CREATE OR REPLACE PROCEDURE UpdateSalary (
    p_employee_id IN NUMBER,
    p_percent IN NUMBER
)
IS
    v_old_salary NUMBER;
BEGIN
    SELECT Salary INTO v_old_salary
    FROM Employees
    WHERE EmployeeID = p_employee_id;

    UPDATE Employees
    SET Salary = Salary + (Salary * p_percent / 100)
    WHERE EmployeeID = p_employee_id;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Salary updated for Employee ID ' || p_employee_id ||
                         '. Old: ' || v_old_salary || 
                         ', New: ' || (v_old_salary + (v_old_salary * p_percent / 100)));
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: Employee ID ' || p_employee_id || ' not found.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Unexpected error: ' || SQLERRM);
END;
/

CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_customer_id IN NUMBER,
    p_name IN VARCHAR2,
    p_dob IN DATE,
    p_balance IN NUMBER
)
IS
BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE);

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Customer ' || p_name || ' added successfully.');
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        DBMS_OUTPUT.PUT_LINE('Error: Customer ID ' || p_customer_id || ' already exists.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Unexpected error: ' || SQLERRM);
END;
/

BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Testing Fund Transfer ---');
    SafeTransferFunds(1, 2, 100);

    DBMS_OUTPUT.PUT_LINE(CHR(10) || '--- Testing Salary Update ---');
    UpdateSalary(2, 10);
    UpdateSalary(99, 15);

    DBMS_OUTPUT.PUT_LINE(CHR(10) || '--- Testing New Customer Insertion ---');
    AddNewCustomer(5, 'Test User', TO_DATE('2000-01-01', 'YYYY-MM-DD'), 5000);
    AddNewCustomer(5, 'Duplicate User', TO_DATE('2000-01-01', 'YYYY-MM-DD'), 5000);
END;
/