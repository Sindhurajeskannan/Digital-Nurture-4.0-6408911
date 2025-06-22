public class DependencyInjectionExample {

    interface CustomerRepository {
        String findCustomerById(String customerId);
    }

    static class CustomerRepositoryImpl implements CustomerRepository {
        public String findCustomerById(String customerId) {
            return "Customer{id='" + customerId + "', name='Customer1'}";
        }
    }

    static class CustomerService {
        private CustomerRepository repository;

        public CustomerService(CustomerRepository repository) {
            this.repository = repository;
        }

        public void getCustomerDetails(String customerId) {
            String customer = repository.findCustomerById(customerId);
            System.out.println("Customer details: " + customer);
        }
    }

    public static void main(String[] args) {
        CustomerRepository repository = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repository);

        service.getCustomerDetails("CUST1001");
    }
}
