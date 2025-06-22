public class ProxyPatternExample {

    interface Image {
        void display();
    }

    static class RealImage implements Image {
        private String filename;

        public RealImage(String filename) {
            this.filename = filename;
            loadFromServer();
        }

        private void loadFromServer() {
            System.out.println("Loading " + filename + " from remote server...");
        }

        public void display() {
            System.out.println("Displaying " + filename);
        }
    }

    static class ProxyImage implements Image {
        private String filename;
        private RealImage realImage;

        public ProxyImage(String filename) {
            this.filename = filename;
        }

        public void display() {
            if (realImage == null) {
                realImage = new RealImage(filename);
            } else {
                System.out.println("Using cached image: " + filename);
            }
            realImage.display();
        }
    }

    public static void main(String[] args) {
        Image image1 = new ProxyImage("nature.jpg");
        Image image2 = new ProxyImage("mountains.jpeg");

        System.out.println("First display of image1:");
        image1.display();

        System.out.println("\nSecond display of image1 (should use cache):");
        image1.display();

        System.out.println("\nFirst display of image2:");
        image2.display();
    }
}

