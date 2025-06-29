CREATE OR REPLACE FUNCTION CalculateAge (
    p_dob IN DATE
) RETURN NUMBER
IS
    v_age NUMBER;
BEGIN
    v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
    RETURN v_age;
END;
/

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    p_loan_amount IN NUMBER,
    p_annual_rate IN NUMBER,
    p_years IN NUMBER
) RETURN NUMBER
IS
    v_monthly_rate NUMBER;
    v_months NUMBER;
    v_installment NUMBER;
BEGIN
    v_monthly_rate := p_annual_rate / 12 / 100;
    v_months := p_years * 12;

    IF v_monthly_rate = 0 THEN
        v_installment := p_loan_amount / v_months;
    ELSE
        v_installment := p_loan_amount * v_monthly_rate /
                         (1 - POWER(1 + v_monthly_rate, -v_months));
    END IF;

    RETURN ROUND(v_installment, 2);
END;
/

CREATE OR REPLACE FUNCTION HasSufficientBalance (
    p_account_id IN NUMBER,
    p_amount IN NUMBER
) RETURN BOOLEAN
IS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance
    FROM Accounts
    WHERE AccountID = p_account_id;

    RETURN v_balance >= p_amount;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
    WHEN OTHERS THEN
        RETURN FALSE;
END;
/

BEGIN
    DBMS_OUTPUT.PUT_LINE('--- CalculateAge ---');
    DBMS_OUTPUT.PUT_LINE('Age: ' || CalculateAge(TO_DATE('1995-04-15', 'YYYY-MM-DD')));

    DBMS_OUTPUT.PUT_LINE(CHR(10) || '--- CalculateMonthlyInstallment ---');
    DBMS_OUTPUT.PUT_LINE('Monthly EMI: ' || CalculateMonthlyInstallment(100000, 8, 5));

    DBMS_OUTPUT.PUT_LINE(CHR(10) || '--- HasSufficientBalance ---');
    IF HasSufficientBalance(1, 100) THEN
        DBMS_OUTPUT.PUT_LINE('Sufficient balance available.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Insufficient balance.');
    END IF;
END;
/