package shoppingList.services.businessLogic;

import shoppingList.domain.Product;
import shoppingList.services.validations.exception.DbContainsSimilarProductException;
import shoppingList.services.validations.exception.ItemIDNofFoundException;

import java.util.List;

public interface TemplateService<T> {

    Product addProductService(T newProduct) throws DbContainsSimilarProductException;

    boolean removeProductByIDService(Long id) throws ItemIDNofFoundException;

    List<T> listOfAllProductsService();

    T findProductByID(Long id) throws ItemIDNofFoundException;
}
