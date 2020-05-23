package shoppingList.services.businessLogic;

import shoppingList.services.validations.exception.DbContainsSimilarProductException;
import shoppingList.services.validations.exception.ItemIDNofFoundException;
import shoppingList.services.validations.ProductValidationService;
import shoppingList.repository.ProductImpRepository;
import shoppingList.domain.Product;

import java.util.Collections;
import java.util.List;

public class ProductService implements TemplateService<Product> {

    ProductImpRepository productImpRepository;
    ProductValidationService validationService = new ProductValidationService();

    public ProductService(ProductImpRepository productImpRepository) {
        this.productImpRepository = productImpRepository;
    }

    //Main methods
    @Override
    public Product addProductService(Product newProduct) throws DbContainsSimilarProductException{
         validationService.validate(newProduct);
        if (productImpRepository.doesDbContainsSimilarProduct(newProduct)) {
            throw new DbContainsSimilarProductException(newProduct);
        } else {
            return productImpRepository.addProduct(newProduct);
        }
    }

    @Override
    public boolean removeProductByIDService(Long id) throws ItemIDNofFoundException {
        if (productImpRepository.doesDbContainsId(id)) {
            productImpRepository.removeProductByID(id);
            return true;
        } else {
            throw new ItemIDNofFoundException(id);
        }
    }

    @Override
    public List<Product> listOfAllProductsService() {
        if (productImpRepository.listOfAllProducts().isEmpty()) {
            System.out.println("DataBase is empty");
            return Collections.emptyList();
        } else {
            System.out.println("Product DataBase:");
            return productImpRepository.listOfAllProducts();
        }
    }

    @Override
    public Product findProductByID(Long id) throws ItemIDNofFoundException {
        Product product = productImpRepository.findProductByID(id);
        if (product != null) {
            return product;
        } else {
            throw new ItemIDNofFoundException(id);
        }
    }


    //Additional methods
    public boolean doesDbContainsIdService(Long id) {
        return productImpRepository.doesDbContainsId(id);
    }
}
