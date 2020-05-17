package shoppingList.UI;

import shoppingList.businessLogic.services.ProductService;
import shoppingList.businessLogic.services.exception.ItemIDNofFoundException;

import java.util.Scanner;

public class FindProductByIdUI {

    ProductService productService;

    public FindProductByIdUI(ProductService productService) {
        this.productService = productService;
    }

    public void findProductByID(){
        System.out.print("Please enter product ID number:");
        Scanner sc = new Scanner(System.in);
        Long id = sc.nextLong();
        try {
            System.out.println("Your product is:" + productService.findProductByID(id));
        }catch (ItemIDNofFoundException e){
            System.out.println(e.getItemNotFoundMessage());
        }
    }
}
