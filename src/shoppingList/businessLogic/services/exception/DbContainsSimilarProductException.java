package shoppingList.businessLogic.services.exception;

import shoppingList.domain.Product;

public class DbContainsSimilarProductException extends Error{

    Product similarProduct;

    public DbContainsSimilarProductException(Product similarProduct){
        this.similarProduct = similarProduct;
    }

    public String DbContainsSimilarProductExceptionMessage(){
        return "DataBase already contains this product;";
    }
}
