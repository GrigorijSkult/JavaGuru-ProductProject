package shoppingList.businessLogic.services;

import shoppingList.businessLogic.services.exception.DbContainsSimilarProductException;
import shoppingList.businessLogic.services.exception.ItemIDNofFoundException;
import shoppingList.database.ProductRepositoryImp;
import shoppingList.domain.Product;

import java.util.Collections;
import java.util.List;

public class ProductService implements TemplateService<Product>{

    ProductRepositoryImp productRepositoryImp;

    public ProductService(ProductRepositoryImp productRepositoryImp) {
        this.productRepositoryImp = productRepositoryImp;
    }

    @Override
    public boolean addProductService(Product newProduct) throws DbContainsSimilarProductException {
        //validation of a new product
        if (productRepositoryImp.doesDbContainsSimilarProduct(newProduct)){
            throw new DbContainsSimilarProductException(newProduct);
        }else {
            productRepositoryImp.addProduct(newProduct);
            return true;
        }
    }

    @Override
    public boolean removeProductByIDService(Long id) throws ItemIDNofFoundException {
        if(productRepositoryImp.doesDbContainsId(id)){
            productRepositoryImp.removeProductByID(id);
            return true;
        }else {
            throw new ItemIDNofFoundException(id);
        }
    }

    @Override
    public List<Product> listOfAllProductsService() {
        if (productRepositoryImp.listOfAllProducts().isEmpty()) {
            System.out.println("DataBase is empty");
            return Collections.emptyList();
        } else {
            System.out.println("Product DataBase:");
            return productRepositoryImp.listOfAllProducts();
        }
    }

    @Override
    public Product findProductByID(Long id) throws ItemIDNofFoundException {
        if(doesDbContainsIdService(id)){
            return productRepositoryImp.findProductByID(id);
        }else{
            throw new ItemIDNofFoundException(id);
        }
    }


    //Additional methods
    public boolean doesDbContainsIdService(Long id){
        return productRepositoryImp.doesDbContainsId(id);
    }
}
