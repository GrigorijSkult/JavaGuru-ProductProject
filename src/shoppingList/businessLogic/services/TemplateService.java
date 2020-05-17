package shoppingList.businessLogic.services;

import shoppingList.businessLogic.services.exception.DbContainsSimilarProductException;
import shoppingList.businessLogic.services.exception.ItemIDNofFoundException;

import java.util.List;

public interface TemplateService<T> {

    boolean addProductService(T newProduct) throws DbContainsSimilarProductException;

    boolean removeProductByIDService(Long id) throws ItemIDNofFoundException;

    List<T> listOfAllProductsService();

    T findProductByID(Long id) throws ItemIDNofFoundException;
}
