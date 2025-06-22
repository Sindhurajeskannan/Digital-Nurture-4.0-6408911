public class BuilderPatternExample {

    static class Computer {
        private final String cpu;
        private final String ram;
        private final String storage;

        // Private constructor (called by Builder)
        private Computer(Builder builder) {
            this.cpu = builder.cpu;
            this.ram = builder.ram;
            this.storage = builder.storage;
        }

        // Getters or toString() for displaying configuration
        public String toString() {
            return "Computer Configuration:\n"
                    + "CPU: " + cpu + "\n"
                    + "RAM: " + ram + "\n"
                    + "Storage: " + storage + "\n";
        }

        // Static nested Builder class
        static class Builder {
            private final String cpu;
            private final String ram;
            private String storage = "256GB SSD"; 
            public Builder(String cpu, String ram) {
                this.cpu = cpu;
                this.ram = ram;
            }
            public Builder setStorage(String storage) {
                this.storage = storage;
                return this;
            }

            public Computer build() {
                return new Computer(this);
            }
        }
    }

    
    public static void main(String[] args) {
        Computer basicComputer = new Computer.Builder("Intel i5", "8GB").build();

        Computer gamingComputer = new Computer.Builder("AMD Ryzen 9", "32GB")
                .setStorage("1TB SSD")
                .build();

        // Display configurations
        System.out.println("BASIC COMPUTER:\n" + basicComputer);
        System.out.println("GAMING COMPUTER:\n" + gamingComputer);
    }
}
