package shoppingList.services.validations.exception;

public class ProductNotFoundException extends RuntimeException{

    private final Long productNotFoundNumber;

    public ProductNotFoundException(Long productNotFoundNumber) {
        this.productNotFoundNumber = productNotFoundNumber;
    }

    public String getItemNotFoundMessage() {
        return "Product with ID '" + productNotFoundNumber + "' is not found;";
    }
}
