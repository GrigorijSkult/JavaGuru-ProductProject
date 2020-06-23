package shoppingList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import shoppingList.config.AppConfig;
import shoppingList.console.ProductMainUI;

class ShoppingListApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductMainUI productMainUI = context.getBean(ProductMainUI.class);
        productMainUI.runMainUI();
    }
}
