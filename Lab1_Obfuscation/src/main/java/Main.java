import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        CatalogService service = new CatalogService();
        try {
            service.deObfuscateCatalog();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
