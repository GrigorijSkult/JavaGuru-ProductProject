package shoppingList.businessLogic.services.exception;

public class ItemIDNofFoundException extends Error{

    private final Long itemNotFoundNumber;

    public ItemIDNofFoundException(Long itemNotFoundNumber) {
        this.itemNotFoundNumber = itemNotFoundNumber;
    }

    public String getItemNotFoundMessage() {
        return "Product with ID '" + itemNotFoundNumber + "' is not found;";
    }
}
