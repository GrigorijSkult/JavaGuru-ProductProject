package shoppingList.businessLogic.validations;

public interface ValidationForm<T> {

    public boolean isValid(T product);

    public String errorMessage();
}
