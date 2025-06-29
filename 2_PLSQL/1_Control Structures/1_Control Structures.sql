-- First-time schema adjustment: Add IsVIP column (runs only once)
BEGIN
    BEGIN
        EXECUTE IMMEDIATE 'ALTER TABLE Customers ADD IsVIP VARCHAR2(5)';
    EXCEPTION
        WHEN OTHERS THEN
            IF SQLCODE = -01430 THEN
                NULL; -- column already exists
            ELSE
                RAISE;
            END IF;
    END;
END;
/

-- Combined Scenario Execution
BEGIN
    -- ========== Scenario 1 ==========
    DBMS_OUTPUT.PUT_LINE('--- Scenario 1: Interest Discount for Age > 60 ---');
    FOR customer_rec IN (SELECT CustomerID, DOB FROM Customers) LOOP
        IF FLOOR(MONTHS_BETWEEN(SYSDATE, customer_rec.DOB) / 12) > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = customer_rec.CustomerID;

            DBMS_OUTPUT.PUT_LINE('Discount applied to Customer ID: ' || customer_rec.CustomerID);
        END IF;
    END LOOP;

    -- ========== Scenario 2 ==========
    DBMS_OUTPUT.PUT_LINE(CHR(10) || '--- Scenario 2: Promote VIP for Balance > 10000 ---');
    FOR cust_rec IN (SELECT CustomerID, Balance FROM Customers) LOOP
        IF cust_rec.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = cust_rec.CustomerID;

            DBMS_OUTPUT.PUT_LINE('Customer ID ' || cust_rec.CustomerID || ' promoted to VIP.');
        END IF;
    END LOOP;

    -- ========== Scenario 3 ==========
    DBMS_OUTPUT.PUT_LINE(CHR(10) || '--- Scenario 3: Loan Due Reminders (Next 30 Days) ---');
    FOR loan_rec IN (
        SELECT l.CustomerID, l.EndDate, c.Name
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Loan for ' || loan_rec.Name ||
                             ' is due on ' || TO_CHAR(loan_rec.EndDate, 'DD-MON-YYYY'));
    END LOOP;

    COMMIT;
END;
/