package Model.components.LeftSidebar.LeftSidebarCategory;

import Model.IMat;
import Model.Main;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public enum MainCategory {
    FAVORIT ("Favoriter",
            IMat.getInstance().getFavorites(), true),
    ALLA_VAROR ("Alla varor",
            ProductCategory.BREAD,
            ProductCategory.HOT_DRINKS,
            ProductCategory.COLD_DRINKS,
            ProductCategory.FISH,
            ProductCategory.CABBAGE, ProductCategory.FRUIT,
            ProductCategory.MELONS, ProductCategory.VEGETABLE_FRUIT,
            ProductCategory.BERRY, ProductCategory.CITRUS_FRUIT,
            ProductCategory.ROOT_VEGETABLE, ProductCategory.EXOTIC_FRUIT,
            ProductCategory.MEAT,
            ProductCategory.DAIRIES,
            ProductCategory.FLOUR_SUGAR_SALT,
            ProductCategory.NUTS_AND_SEEDS,
            ProductCategory.PASTA,
            ProductCategory.POTATO_RICE),
    BRÖD_OCH_KAKOR("Bröd & Kakor",
            ProductCategory.BREAD),
    DRYCK("Dryck",
            ProductCategory.HOT_DRINKS,
            ProductCategory.COLD_DRINKS),
    FISK("Fisk",
            ProductCategory.FISH),
    FRUKT_OCH_GRÖNT("Frukt & Grönt",
            ProductCategory.CABBAGE, ProductCategory.FRUIT,
            ProductCategory.MELONS, ProductCategory.VEGETABLE_FRUIT,
            ProductCategory.BERRY, ProductCategory.CITRUS_FRUIT,
            ProductCategory.ROOT_VEGETABLE, ProductCategory.EXOTIC_FRUIT),
    KÖTT("Kött",
            ProductCategory.MEAT),
    MEJERI("Mejeri",
            ProductCategory.DAIRIES),
    SKAFFERI("Skafferi",
            ProductCategory.FLOUR_SUGAR_SALT,
            ProductCategory.NUTS_AND_SEEDS,
            ProductCategory.PASTA,
            ProductCategory.POTATO_RICE);



    String name;
    List<ProductCategory> subCategories;
    List<Product> products;
    private boolean usePicture = false;

    MainCategory(String name, List<Product> products, boolean usePicture){
        this.name = name;
        this.products = products;
        this.usePicture = usePicture;

        //getProducts();
    }
    MainCategory(String name, ProductCategory... productCategories){
//   bara lite nyfiken vad betyder punkterna  ^  ? /Mattias
        this.name = name;
        subCategories = new ArrayList<ProductCategory>();
        for(ProductCategory category: productCategories){
            subCategories.add(category);
        }
        //getProducts();
    }


    /**
     * Method used to get products related to a main category
     * Products are cached and will only be loaded once.
     * @return products that belongs to a specific main category
     */
    public List<Product> getProducts(){
        if(products == null){
            products = new ArrayList<Product>();
            for(ProductCategory category: subCategories){
                products.addAll(IMat.getInstance().getProducts(category));
            }
        }
        return products;
    }

    public boolean shouldUsePicture(){
        return usePicture;
    }

    public String toString(){
        return name;
    }
}
