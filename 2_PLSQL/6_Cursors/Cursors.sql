BEGIN
    DBMS_OUTPUT.PUT_LINE('--- GenerateMonthlyStatements ---');

    FOR cust IN (
        SELECT DISTINCT c.CustomerID, c.Name
        FROM Customers c
        JOIN Accounts a ON c.CustomerID = a.CustomerID
        JOIN Transactions t ON a.AccountID = t.AccountID
        WHERE TRUNC(t.TransactionDate, 'MM') = TRUNC(SYSDATE, 'MM')
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Statement for Customer: ' || cust.Name || ' (ID: ' || cust.CustomerID || ')');
        
        FOR txn IN (
            SELECT t.TransactionDate, t.Amount, t.TransactionType
            FROM Accounts a
            JOIN Transactions t ON a.AccountID = t.AccountID
            WHERE a.CustomerID = cust.CustomerID
              AND TRUNC(t.TransactionDate, 'MM') = TRUNC(SYSDATE, 'MM')
        ) LOOP
            DBMS_OUTPUT.PUT_LINE('- ' || txn.TransactionDate || ' | ' || txn.TransactionType || ' | $' || txn.Amount);
        END LOOP;

        DBMS_OUTPUT.PUT_LINE('-----------------------------------');
    END LOOP;

    DBMS_OUTPUT.PUT_LINE(CHR(10) || '--- ApplyAnnualFee ---');

    FOR acc IN (
        SELECT AccountID, Balance
        FROM Accounts
    ) LOOP
        UPDATE Accounts
        SET Balance = Balance - 100,
            LastModified = SYSDATE
        WHERE AccountID = acc.AccountID;

        DBMS_OUTPUT.PUT_LINE('Annual fee deducted from Account ID ' || acc.AccountID);
    END LOOP;

    DBMS_OUTPUT.PUT_LINE(CHR(10) || '--- UpdateLoanInterestRates ---');

    FOR ln IN (
        SELECT LoanID, InterestRate
        FROM Loans
    ) LOOP
        UPDATE Loans
        SET InterestRate = InterestRate + 0.5
        WHERE LoanID = ln.LoanID;

        DBMS_OUTPUT.PUT_LINE('Loan ID ' || ln.LoanID || ' interest updated to ' || (ln.InterestRate + 0.5));
    END LOOP;

    COMMIT;
END;
/