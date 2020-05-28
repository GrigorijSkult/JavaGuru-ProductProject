package shoppingList.services.validations;

public interface ValidationRule<T> {

    void validate(T product);

}
