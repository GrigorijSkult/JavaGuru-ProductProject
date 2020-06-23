package shoppingList.console;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ExitUI implements UserInterfaceUnit {

    @Override
    public void execute() {
        System.out.println("Do you want to close the program?");
        System.out.print("Tap 'Y' for 'Yes' or 'N' for 'No': ");
        Scanner scanner = new Scanner(System.in);
        String userChoice = scanner.nextLine();
            if (userChoice.startsWith("Y") || userChoice.startsWith("y")) {
                System.out.println("Program is finished;");
                System.exit(0);
            } else {
                System.out.println("Operation is canceled. Program wont be closed;");
            }
    }

    @Override
    public String toString() {
        return "End program.";
    }
}
