package shoppingList.services.validations.exception;

public class ItemIDNofFoundException extends RuntimeException{

    private final Long itemNotFoundNumber;

    public ItemIDNofFoundException(Long itemNotFoundNumber) {
        this.itemNotFoundNumber = itemNotFoundNumber;
    }

    public String getItemNotFoundMessage() {
        return "Product with ID '" + itemNotFoundNumber + "' is not found;";
    }
}
