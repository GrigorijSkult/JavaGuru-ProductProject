package shoppingList.shoppingCart.service.businessLogic.exception;

public class ShoppingCartNotFoundException extends RuntimeException {

    private final Long shoppingCartNotFoundNumber;

    public ShoppingCartNotFoundException(Long shoppingCartNotFoundNumber) {
        this.shoppingCartNotFoundNumber = shoppingCartNotFoundNumber;
    }

    public String getItemNotFoundMessage() {
        return "Shopping cart with ID '" + shoppingCartNotFoundNumber + "' is not found;";
    }

}
