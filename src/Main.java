import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        IMatDataHandler.getInstance().addFavorite(new Product());
    }
}
