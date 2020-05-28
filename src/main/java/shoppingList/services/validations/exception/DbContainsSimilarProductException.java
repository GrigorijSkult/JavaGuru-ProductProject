package shoppingList.services.validations.exception;

import shoppingList.domain.Product;

public class DbContainsSimilarProductException extends RuntimeException{

    Product similarProduct;

    public DbContainsSimilarProductException(Product similarProduct){
        this.similarProduct = similarProduct;
    }

    public String DbContainsSimilarProductExceptionMessage(){
        return "DataBase already contains this product;";
    }
}
