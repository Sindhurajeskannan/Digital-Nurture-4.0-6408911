public class FinancialForecasting {

    public static double calculateFutureValue(double amount, double rate, int yearsLeft) {
        if (yearsLeft == 0) {
            return amount;
        }
        return calculateFutureValue(amount * (1 + rate), rate, yearsLeft - 1);
    }

    public static double calculateFutureValueOptimized(double amount, double rate, int totalYears) {
        double[] values = new double[totalYears + 1];
        values[0] = amount;
        for (int year = 1; year <= totalYears; year++) {
            values[year] = values[year - 1] * (1 + rate);
        }
        return values[totalYears];
    }

    public static void main(String[] args) {
        double startingAmount = 10000;
        double annualGrowthRate = 0.08;
        int numberOfYears = 5;

        double futureValueRecursive = calculateFutureValue(startingAmount, annualGrowthRate, numberOfYears);
        System.out.println("Future Value (Recursive): " + futureValueRecursive);

        double futureValueOptimized = calculateFutureValueOptimized(startingAmount, annualGrowthRate, numberOfYears);
        System.out.println("Future Value (Optimized): " + futureValueOptimized);
    }
}
