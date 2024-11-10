import java.text.DecimalFormat;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *  Class to record users monthly expenditure
 *
 * @author Archie Hamilton
 * @version 1.0
 */

public class Budget {
    //Array for monthly expenditure
    private double[][] expenditure = new double[12][4];
    private double randomKey = (Math.random()*101);

    /**
     * Default constructor setting all values to 0.0
     */
    public Budget() {
        for (int i = 0; i < expenditure.length; i++) {
            for (int j = 0; j < expenditure[i].length; j++) {
                expenditure[i][j] = 0.0;
            }
        }
    }

    /**
     * Populates the expenditure array with test data.
     * This method is used for initializing the expenditure data
     * for testing purposes.
     */
    public void randomPopulateTable() {

        //give values to array, Jan[0] - Dec [11]
        DecimalFormat df = new DecimalFormat("0.00");
        for (int i = 0; i < expenditure.length; i++) {
            for (int j = 0; j < expenditure[i].length; j++) {
                double testNum = (Math.random() * 101);
                expenditure[i][j] = Double.parseDouble(df.format(testNum));
            }
        }
    }

    /**
     * A method to calculate the expenditure of the entire year
     */
    public double getTotalExpenditure() {
        double total = 0.0;
        for (int i = 0; i < expenditure.length; i++) {
            for (int j = 0; j < expenditure[i].length; j++) {
                total += expenditure[i][j];
            }
        }
        return total;
    }

    /**
     * Method to calculate an expenditure from only a single month
     * and display Month : Spend
     *
     * @return
     */
    public void displayMonthlyExpenditure() {
        double[] monthlyTotal = new double[12];

        for (int i = 0; i < expenditure.length; i++) {
            double total = 0;
            for (int j = 0; j < expenditure[i].length; j++) {
                total += expenditure[i][j];
            }
            monthlyTotal[i] = total;
        }

        System.out.println("--------------------");
        System.out.println("Monthly Expenditure:");
        System.out.println("--------------------");
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        for (int i = 0; i < monthlyTotal.length; i++) {
            System.out.printf("%-12s: £%.2f%n", months[i], monthlyTotal[i]);
        }
    }

    /**
     * A method which will calculate and display the average cost of
     * each of the categories through the entire year.
     */
    public void displayAverageMonthlyItemCost() {
        double[] totalSpend = new double[4];
        int months = expenditure.length;

        for (int i = 0; i < months; i++) {
            for (int j = 0; j < expenditure[i].length; j++) {
                totalSpend[j] += expenditure[i][j];
            }
        }

        double[] averageSpend = new double[4];
        for (int j = 0; j < totalSpend.length; j++) {
            averageSpend[j] = totalSpend[j] / months;
        }

        System.out.println("---------------------------");
        System.out.println("Average Monthly Expenditure:");
        System.out.println("---------------------------");
        String[] categories = {"Food (£)", "Rent (£)", "Clothes (£)", "Socialising (£)"};

        for (int j = 0; j < averageSpend.length; j++) {
            System.out.printf("%-15s : £%-10.2f%n", categories[j], averageSpend[j]);
        }
        System.out.println("---------------------------");
    }

    /**
     * Method to figure out the highest spend in each category
     * and display alongside the month that the value correlates with
     */

    public void displayHighestSpends() {
        String[] items = {"Food", "Rent", "Clothes", "Socialising"};
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        System.out.println("----------------------------");
        System.out.println("Most spent on each category:");
        System.out.println("----------------------------");
        System.out.println("Item         Spend       Month");

        for (int column = 0; column < expenditure[0].length; column++) {
            double highestSpend = 0;
            int highestMonthIndex = 0;

            for (int row = 0; row < expenditure.length; row++) {
                if (expenditure[row][column] > highestSpend) {
                    highestSpend = expenditure[row][column];
                    highestMonthIndex = row;
                }
            }

            System.out.printf("%-12s £%-10.2f %-8s%n", items[column], highestSpend, months[highestMonthIndex]);
        }
    }

    /**
     * Special Method to load "Table-Content.txt"
     * this file goes through no encryption so loading normally will
     * change the data with respect to randomKey
     */
    public void tableContentLoadFile(){
        try (BufferedReader br = new BufferedReader(new FileReader("table-content.txt"))) {
            String line;
            int i = 0;

            while ((line = br.readLine()) != null && i < expenditure.length) {
                String[] values = line.split(",");
                for (int j = 0; j < values.length && j < expenditure[i].length; j++) {
                    expenditure[i][j] = Double.parseDouble(values[j].trim());
                }
                i++;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }


    /**
     * Method to read "table-content.txt" and populate
     * table with this information instead of randomPopulateTable()
     *
     * @param "table-content.txt" text file
     */
    public void loadFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int i = 0;

            while ((line = br.readLine()) != null && i < expenditure.length) {
                String[] values = line.split(",");
                for (int j = 0; j < values.length && j < expenditure[i].length; j++) {
                    double encryptedValue = Double.parseDouble(values[j].trim());
                    expenditure[i][j] = (encryptedValue/randomKey);
                }
                i++;
            }
        } catch (IOException e) {
            Scanner file = new Scanner(System.in);
            System.err.println("Error reading file: " + e.getMessage());
            System.out.println("Please re-enter the file you would like to load: ");
            fileName = file.nextLine();
            loadFile(fileName);
        }
    }

    /**
     * Method allowing user to save a file with a table of the expenditure
     *
     */
    public void saveFile() {
        String saveName;
        Scanner file = new Scanner(System.in);
        System.out.println("What would you like to name the file?");
        saveName = file.nextLine();

        try (PrintWriter pw = new PrintWriter(new FileWriter(saveName))) {
            for (int i = 0; i < expenditure.length; i++) {
                for (int j = 0; j < expenditure[i].length; j++) {
                    pw.print(expenditure[i][j]*randomKey);
                    if (j < expenditure[i].length - 1) {
                        pw.print(",");
                    }
                }
                pw.println();
            }
            System.out.println("Data successfully saved to " + saveName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Display all information about spending
     */
    public void displayInformation() {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf("| %-12s | %-12s | %-12s | %-12s | %-12s |%n", "Month", "Food (£)", "Rent (£)", "Clothes (£)", "Socialising (£)");
        System.out.println("-------------------------------------------------------------------------------");
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        for (int i = 0; i < expenditure.length; i++) {
            System.out.printf("| %-12s ", months[i]);
            for (int j = 0; j < expenditure[i].length; j++) {
                if (expenditure[i][j] == expenditure[i][3] ) {
                    System.out.printf("| %-15.2f ", expenditure[i][j]);
                }  else {
                    System.out.printf("| %-12.2f ", expenditure[i][j]);
                }
            }
            System.out.println("|");
        }
        System.out.println("-------------------------------------------------------------------------------");
    }

}

