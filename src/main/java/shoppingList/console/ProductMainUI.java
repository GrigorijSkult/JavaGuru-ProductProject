package shoppingList.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import shoppingList.console.UserInterfaceUnit;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Component
public class ProductMainUI {

    private final List<UserInterfaceUnit> interfaceUnitList;

    public ProductMainUI(@Qualifier("productMainUI") List<UserInterfaceUnit> interfaceUnitList) {
        this.interfaceUnitList = interfaceUnitList;
    }

    public void runMainUI() {
        boolean continueProgram = true;
        do {
            System.out.print("Products DataBase" + "\n" +
                    "-----------------" + "\n" +
                    "Please choose your action:" + "\n" +
                    "");
            Scanner scanner = new Scanner(System.in);
            try {
                for (int i = 0; i < interfaceUnitList.size(); i++) {
                    System.out.println((i + 1) + ". " + interfaceUnitList.get(i));
                }
                System.out.print("Select option: ");
                int userChoice = scanner.nextInt();
                interfaceUnitList.get(userChoice - 1).execute();
            } catch (InputMismatchException e) {
                System.out.println("Please enter numerical value!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please enter value from given list (" + "chose from 1. to " + interfaceUnitList.size() + ".)!;");
            }
            System.out.println();
        } while (continueProgram);
    }
}
