import java.util.Arrays;
import java.util.Comparator;

public class EcommerceSearch {

    static class Product {
        int productId;
        String productName;
        String category;

        public Product(int productId, String productName, String category) {
            this.productId = productId;
            this.productName = productName;
            this.category = category;
        }

        @Override
        public String toString() {
            return productId + " - " + productName + " (" + category + ")";
        }
    }

    public static Product linearSearch(Product[] products, String searchName) {
        for (Product p : products) {
            if (p.productName.equalsIgnoreCase(searchName)) {
                return p;
            }
        }
        return null;
    }

    public static Product binarySearch(Product[] products, String searchName) {
        int left = 0, right = products.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int cmp = products[mid].productName.compareToIgnoreCase(searchName);
            if (cmp == 0) return products[mid];
            else if (cmp < 0) left = mid + 1;
            else right = mid - 1;
        }
        return null;
    }

    public static void main(String[] args) {
        Product[] productList = {
            new Product(101, "Laptop", "Electronics"),
            new Product(102, "Shoes", "Fashion"),
            new Product(103, "Mobile", "Electronics"),
            new Product(104, "Book", "Education"),
            new Product(105, "Watch", "Accessories")
        };

        System.out.println("Linear Search:");
        String query1 = "Mobile";
        Product found1 = linearSearch(productList, query1);
        System.out.println(found1 != null ? "Found: " + found1 : "Product not found");

        Arrays.sort(productList, Comparator.comparing(p -> p.productName.toLowerCase()));

        System.out.println("\nBinary Search:");
        String query2 = "Mobile";
        Product found2 = binarySearch(productList, query2);
        System.out.println(found2 != null ? "Found: " + found2 : "Product not found");
    }
}
