import java.util.ArrayList;
import java.util.List;

public class ObserverPatternExample {

    interface Observer {
        void update(String stockName, double newPrice);
    }

    interface Stock {
        void registerObserver(Observer observer);
        void deregisterObserver(Observer observer);
        void notifyObservers(String stockName, double newPrice);
    }

    static class StockMarket implements Stock {
        private List<Observer> observers = new ArrayList<>();

        public void registerObserver(Observer observer) {
            observers.add(observer);
        }

        public void deregisterObserver(Observer observer) {
            observers.remove(observer);
        }

        public void notifyObservers(String stockName, double newPrice) {
            for (Observer observer : observers) {
                observer.update(stockName, newPrice);
            }
        }

        public void updateStockPrice(String stockName, double newPrice) {
            System.out.println("\nStock price updated: " + stockName + " = Rs." + newPrice);
            notifyObservers(stockName, newPrice);
        }
    }

    static class MobileApp implements Observer {
        public void update(String stockName, double newPrice) {
            System.out.println("MobileApp: " + stockName + " updated to Rs." + newPrice);
        }
    }

    static class WebApp implements Observer {
        public void update(String stockName, double newPrice) {
            System.out.println("WebApp: " + stockName + " updated to Rs." + newPrice);
        }
    }

    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        Observer mobileApp = new MobileApp();
        Observer webApp = new WebApp();

        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(webApp);

        stockMarket.updateStockPrice("TRENDS", 3520.75);
        stockMarket.updateStockPrice("ZARA", 1420.00);

        stockMarket.deregisterObserver(webApp);
        stockMarket.updateStockPrice("H&M", 2755.30);
    }
}
