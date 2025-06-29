CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest
IS
BEGIN
    FOR acc IN (
        SELECT AccountID, Balance
        FROM Accounts
        WHERE AccountType = 'Savings'
    ) LOOP
        UPDATE Accounts
        SET Balance = Balance + (Balance * 0.01),
            LastModified = SYSDATE
        WHERE AccountID = acc.AccountID;

        DBMS_OUTPUT.PUT_LINE('Interest applied to Account ID ' || acc.AccountID);
    END LOOP;

    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department IN VARCHAR2,
    p_bonus_percent IN NUMBER
)
IS
    v_old_salary NUMBER;
BEGIN
    FOR emp IN (
        SELECT EmployeeID, Salary
        FROM Employees
        WHERE Department = p_department
    ) LOOP
        v_old_salary := emp.Salary;

        UPDATE Employees
        SET Salary = Salary + (Salary * p_bonus_percent / 100)
        WHERE EmployeeID = emp.EmployeeID;

        DBMS_OUTPUT.PUT_LINE('Bonus applied to Employee ID ' || emp.EmployeeID || 
                             ', New Salary: ' || (v_old_salary + (v_old_salary * p_bonus_percent / 100)));
    END LOOP;

    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE TransferFunds (
    p_from_acc IN NUMBER,
    p_to_acc IN NUMBER,
    p_amount IN NUMBER
)
IS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance
    FROM Accounts
    WHERE AccountID = p_from_acc
    FOR UPDATE;

    IF v_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient balance in source account');
    END IF;

    UPDATE Accounts
    SET Balance = Balance - p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_from_acc;

    UPDATE Accounts
    SET Balance = Balance + p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_to_acc;

    DBMS_OUTPUT.PUT_LINE('Transfer of ' || p_amount || ' from Account ' || p_from_acc || ' to ' || p_to_acc || ' completed.');

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error in TransferFunds: ' || SQLERRM);
END;
/

BEGIN
    DBMS_OUTPUT.PUT_LINE('--- ProcessMonthlyInterest ---');
    ProcessMonthlyInterest;

    DBMS_OUTPUT.PUT_LINE(CHR(10) || '--- UpdateEmployeeBonus for IT Dept (15%) ---');
    UpdateEmployeeBonus('IT', 15);

    DBMS_OUTPUT.PUT_LINE(CHR(10) || '--- TransferFunds from Account 1 to 2 (Amount: 50) ---');
    TransferFunds(1, 2, 50);
END;
/