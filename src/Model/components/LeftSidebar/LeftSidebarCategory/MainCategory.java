package Model.components.LeftSidebar.LeftSidebarCategory;

import Model.IMat;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public enum MainCategory {
    FRUKT_OCH_GRÖNT("Frukt och Grönt",
            ProductCategory.CABBAGE, ProductCategory.FRUIT,
            ProductCategory.MELONS, ProductCategory.VEGETABLE_FRUIT,
            ProductCategory.BERRY, ProductCategory.CITRUS_FRUIT,
            ProductCategory.ROOT_VEGETABLE, ProductCategory.EXOTIC_FRUIT),
    SKAFFERI("Skafferi",
            ProductCategory.FLOUR_SUGAR_SALT,
            ProductCategory.NUTS_AND_SEEDS,
            ProductCategory.PASTA,
            ProductCategory.POTATO_RICE);

    String name;
    List<ProductCategory> subCategories;
    List<Product> products;
    MainCategory(String name, ProductCategory... productCategories){
        this.name = name;
        subCategories = new ArrayList<ProductCategory>();
        for(ProductCategory category: productCategories){
            subCategories.add(category);
        }
        getProducts();
    }


    /**
     * Method used to get products related to a main category
     * Products are cached and will only be loaded once.
     * @return products that belongs to a specific main category
     */
    public List<Product> getProducts(){
        if(products == null || products.size() == 0){
            products = new ArrayList<Product>();
            for(ProductCategory category: subCategories){
                products.addAll(IMat.getInstance().getProducts(category));
            }
        }
        return products;
    }

    public String toString(){
        return name;
    }
}
