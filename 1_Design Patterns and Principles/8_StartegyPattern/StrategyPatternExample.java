public class StrategyPatternExample {

    interface PaymentStrategy {
        void pay(double amount);
    }

    static class CreditCardPayment implements PaymentStrategy {
        private String cardNumber;

        public CreditCardPayment(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public void pay(double amount) {
            System.out.println("Paid Rs." + amount + " using Credit Card (Card No: " + cardNumber + ")");
        }
    }

    static class PayPalPayment implements PaymentStrategy {
        private String email;

        public PayPalPayment(String email) {
            this.email = email;
        }

        public void pay(double amount) {
            System.out.println("Paid Rs." + amount + " using PayPal (Email: " + email + ")");
        }
    }

    static class PaymentContext {
        private PaymentStrategy strategy;

        public void setPaymentStrategy(PaymentStrategy strategy) {
            this.strategy = strategy;
        }

        public void processPayment(double amount) {
            if (strategy == null) {
                System.out.println("Payment strategy not set.");
            } else {
                strategy.pay(amount);
            }
        }
    }

    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        context.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456"));
        context.processPayment(2500.00);

        context.setPaymentStrategy(new PayPalPayment("user@example.com"));
        context.processPayment(1800.50);
    }
}

