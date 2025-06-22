public class DecoratorPatternExample {

    interface Notifier {
        void send(String message);
    }

    static class EmailNotifier implements Notifier {
        public void send(String message) {
            System.out.println("Email Notification: " + message);
        }
    }

    static abstract class NotifierDecorator implements Notifier {
        protected Notifier wrappee;

        public NotifierDecorator(Notifier notifier) {
            this.wrappee = notifier;
        }

        public void send(String message) {
            wrappee.send(message);
        }
    }

    static class SMSNotifierDecorator extends NotifierDecorator {
        public SMSNotifierDecorator(Notifier notifier) {
            super(notifier);
        }

        public void send(String message) {
            super.send(message);
            System.out.println("SMS Notification: " + message);
        }
    }

    static class SlackNotifierDecorator extends NotifierDecorator {
        public SlackNotifierDecorator(Notifier notifier) {
            super(notifier);
        }

        public void send(String message) {
            super.send(message);
            System.out.println("Slack Notification: " + message);
        }
    }

    public static void main(String[] args) {
        Notifier notifier = new EmailNotifier();
        Notifier smsNotifier = new SMSNotifierDecorator(notifier);
        Notifier slackNotifier = new SlackNotifierDecorator(smsNotifier);

        System.out.println("Sending multi-channel notification...");
        slackNotifier.send("Server is down!");
    }
}
