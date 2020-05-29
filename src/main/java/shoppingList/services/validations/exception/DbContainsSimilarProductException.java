package shoppingList.services.validations.exception;

import shoppingList.domain.ProductEntity;

public class DbContainsSimilarProductException extends RuntimeException{

    ProductEntity similarProductEntity;

    public DbContainsSimilarProductException(ProductEntity similarProductEntity){
        this.similarProductEntity = similarProductEntity;
    }

    public String DbContainsSimilarProductExceptionMessage(){
        return "DataBase already contains this product;";
    }
}
