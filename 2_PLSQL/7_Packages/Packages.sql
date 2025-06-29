CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddCustomer(p_customer_id NUMBER, p_name VARCHAR2, p_dob DATE, p_balance NUMBER);
    PROCEDURE UpdateCustomer(p_customer_id NUMBER, p_name VARCHAR2, p_dob DATE);
    FUNCTION GetCustomerBalance(p_customer_id NUMBER) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS
    PROCEDURE AddCustomer(p_customer_id NUMBER, p_name VARCHAR2, p_dob DATE, p_balance NUMBER) IS
    BEGIN
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
        VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE);
    END;

    PROCEDURE UpdateCustomer(p_customer_id NUMBER, p_name VARCHAR2, p_dob DATE) IS
    BEGIN
        UPDATE Customers
        SET Name = p_name,
            DOB = p_dob,
            LastModified = SYSDATE
        WHERE CustomerID = p_customer_id;
    END;

    FUNCTION GetCustomerBalance(p_customer_id NUMBER) RETURN NUMBER IS
        v_balance NUMBER;
    BEGIN
        SELECT Balance INTO v_balance
        FROM Customers
        WHERE CustomerID = p_customer_id;
        RETURN v_balance;
    END;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee(p_emp_id NUMBER, p_name VARCHAR2, p_position VARCHAR2, p_salary NUMBER, p_dept VARCHAR2, p_hiredate DATE);
    PROCEDURE UpdateEmployee(p_emp_id NUMBER, p_position VARCHAR2, p_salary NUMBER);
    FUNCTION GetAnnualSalary(p_emp_id NUMBER) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS
    PROCEDURE HireEmployee(p_emp_id NUMBER, p_name VARCHAR2, p_position VARCHAR2, p_salary NUMBER, p_dept VARCHAR2, p_hiredate DATE) IS
    BEGIN
        INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
        VALUES (p_emp_id, p_name, p_position, p_salary, p_dept, p_hiredate);
    END;

    PROCEDURE UpdateEmployee(p_emp_id NUMBER, p_position VARCHAR2, p_salary NUMBER) IS
    BEGIN
        UPDATE Employees
        SET Position = p_position,
            Salary = p_salary
        WHERE EmployeeID = p_emp_id;
    END;

    FUNCTION GetAnnualSalary(p_emp_id NUMBER) RETURN NUMBER IS
        v_salary NUMBER;
    BEGIN
        SELECT Salary INTO v_salary
        FROM Employees
        WHERE EmployeeID = p_emp_id;
        RETURN v_salary * 12;
    END;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount(p_account_id NUMBER, p_customer_id NUMBER, p_type VARCHAR2, p_balance NUMBER);
    PROCEDURE CloseAccount(p_account_id NUMBER);
    FUNCTION GetTotalCustomerBalance(p_customer_id NUMBER) RETURN NUMBER;
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS
    PROCEDURE OpenAccount(p_account_id NUMBER, p_customer_id NUMBER, p_type VARCHAR2, p_balance NUMBER) IS
    BEGIN
        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
        VALUES (p_account_id, p_customer_id, p_type, p_balance, SYSDATE);
    END;

    PROCEDURE CloseAccount(p_account_id NUMBER) IS
    BEGIN
        DELETE FROM Accounts WHERE AccountID = p_account_id;
    END;

    FUNCTION GetTotalCustomerBalance(p_customer_id NUMBER) RETURN NUMBER IS
        v_total NUMBER;
    BEGIN
        SELECT NVL(SUM(Balance), 0) INTO v_total
        FROM Accounts
        WHERE CustomerID = p_customer_id;
        RETURN v_total;
    END;
END AccountOperations;
/

BEGIN
    DBMS_OUTPUT.PUT_LINE('--- All Package Tests ---');
    CustomerManagement.AddCustomer(20, 'Eli Test', TO_DATE('2001-06-15', 'YYYY-MM-DD'), 3000);
    CustomerManagement.UpdateCustomer(20, 'Eli Updated', TO_DATE('2001-06-15', 'YYYY-MM-DD'));
    DBMS_OUTPUT.PUT_LINE('Customer Balance: ' || CustomerManagement.GetCustomerBalance(20));
    EmployeeManagement.HireEmployee(20, 'Eli Emp', 'Developer', 50000, 'IT', SYSDATE);
    EmployeeManagement.UpdateEmployee(20, 'Lead Dev', 55000);
    DBMS_OUTPUT.PUT_LINE('Annual Salary: ' || EmployeeManagement.GetAnnualSalary(20));
    AccountOperations.OpenAccount(20, 20, 'Savings', 1200);
    DBMS_OUTPUT.PUT_LINE('Total Account Balance: ' || AccountOperations.GetTotalCustomerBalance(20));
    AccountOperations.CloseAccount(20);
END;
/