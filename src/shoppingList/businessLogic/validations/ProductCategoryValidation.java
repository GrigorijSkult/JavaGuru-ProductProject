package shoppingList.businessLogic.validations;

import shoppingList.domain.Product;
import shoppingList.domain.ProductCategory;

public class ProductCategoryValidation implements ValidationForm<Product> {

    @Override
    public boolean isValid(Product product) {
        for (ProductCategory value : ProductCategory.values()) {
            if (product.getProductCategory().equals(value)){
                return true;
            }
        }
        return false;
    }

    public boolean isValid(ProductCategory productCategory) {
        for (ProductCategory value : ProductCategory.values()) {
            if (productCategory.equals(value)){
                return true;
            }
        }
        return false;
    }

    public boolean isValid(String productCategory) throws NumberFormatException{
        try {
            int i = Integer.parseInt(productCategory);
            //System.out.println("Please enter correct product category number");
            return i >= 0 && i < ProductCategory.values().length;
        }catch (NumberFormatException e){
            //System.out.println("Please enter product category number");
            return false;
        }
    }

    @Override
    public String errorMessage() {
        return "Product category number is incorrect";
    }
}
