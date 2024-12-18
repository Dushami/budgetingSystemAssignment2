import java.util.Scanner;

/**
 * Class contain main method, to control the program
 *
 * @author Archie Hamilton
 * @version 1.0
 */

public class BudgetingSystem {

    /**
     * Method to display a menu and track which option they pick
     *
     * @return
     */
    public int getMenuChoice(){
        System.out.println("-----------------------------------");
        System.out.println("Please select your desired function");
        System.out.println("-----------------------------------");
        System.out.println(
                "1. Load Table of Expenditure\n" +
                "2. Display Table of Expenditure\n" +
                "3. Display Total Spend for the Year\n" +
                "4. Display Total Spend Per Month\n" +
                "5. Display Average Spend Per Item\n" +
                "6. Save Table of Expenditure\n" +
                "7. Exit"
        );
        Scanner choice = new Scanner(System.in);
        return choice.nextInt();
    }

    private static String fileName;

    public String getLoadChoice(){
        Scanner choice = new Scanner(System.in);


        System.out.println("-------------------------------------------------");
        System.out.println("Type the name of the file you would like to load?");
        System.out.println("-------------------------------------------------");
        fileName = choice.nextLine();
        return fileName;
    }

    public static void main(String[] args) {
        /**
         * Create instances of each class
         */
        BudgetingSystem budgetingSystem = new BudgetingSystem();
        Budget budget = new Budget();
        int menuChoice;

        /**
         * do...while to loop the menu and exit when user selects option 7
         */
        do {
            menuChoice = budgetingSystem.getMenuChoice();
            switch (menuChoice) {
                case 1:
                    if(budgetingSystem.getLoadChoice().equals("table-content.txt")){
                        budget.tableContentLoadFile();
                    } else {
                        budget.loadFile(fileName);

                    }
                    break;
                case 2:
                    budget.displayInformation();
                    break;
                case 3:
                    System.out.println("Total expenditure: £" + budget.getTotalExpenditure());
                    break;
                case 4:
                    budget.displayMonthlyExpenditure();
                    break;
                case 5:
                    budget.displayAverageMonthlyItemCost();
                    break;
                case 6:
                    budget.saveFile();
                    break;
                case 7:
                    System.out.println("Thank you for using our budgeting system, Now Exiting program");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (menuChoice != 7);

//        budget.randomPopulateTable();
//        budget.displayInformation();
//        budget.displayHighestSpends();

//        budget.randomPopulateTable();
//        budget.displayInformation();
//        budget.saveFile();
//        budget.loadFile("*****MAKE THIS VALUE THE NAME YOU'RE GOING TO CALL THE FILE YOU SAVE");
//        budget.displayInformation();

//        budget.populate2DArrayOfObjects();
//        budget.displayObjectTable();

    }
}