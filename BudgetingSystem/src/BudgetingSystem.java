public class BudgetingSystem {
    public static void main(String[] args) {
        Budget budget = new Budget();

        budget.loadFile("table-content.txt");
        budget.displayInformation();
        //System.out.println("Total expenditure: £" + budget.getTotalExpenditure());
        //budget.displayMonthlyExpenditure();
        budget.displayAverageMonthlyItemCost();

//        budget.randomPopulateTable();
//        budget.displayInformation();
//        budget.saveFile("randomTable.txt");

//        budget.loadFile("randomTable.txt");
//        budget.displayInformation();
    }
}