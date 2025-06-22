public class AdapterPatternExample {

    interface PaymentProcessor {
        void processPayment(double amount);
    }

    static class PayPalGateway {
        public void makePayment(double amount) {
            System.out.println("Paid :" + amount + " using PayPal.");
        }
    }

    static class RazorpayGateway {
        public void payAmount(double amountInRupees) {
            System.out.println("Paid :" + amountInRupees + " using Razorpay.");
        }
    }

    static class PayPalAdapter implements PaymentProcessor {
        private PayPalGateway paypal = new PayPalGateway();

        public void processPayment(double amount) {
            paypal.makePayment(amount);
        }
    }

    static class RazorpayAdapter implements PaymentProcessor {
        private RazorpayGateway razorpay = new RazorpayGateway();

        public void processPayment(double amount) {
            razorpay.payAmount(amount);
        }
    }

    public static void main(String[] args) {
        PaymentProcessor paypalProcessor = new PayPalAdapter();
        PaymentProcessor razorpayProcessor = new RazorpayAdapter();
        System.out.println("=== Payment Processing System ===");
        paypalProcessor.processPayment(1500.00);
        razorpayProcessor.processPayment(2200.50);
    }
}

