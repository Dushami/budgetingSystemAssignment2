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
                String[] values = line.split(","); // Split by comma
                for (int j = 0; j < values.length && j < expenditure[i].length; j++) {
                    expenditure[i][j] = Double.parseDouble(values[j].trim());
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
     * @param saveName
     */
    public void saveFile(String saveName) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(saveName))) {
            for (int i = 0; i < expenditure.length; i++) {
                for (int j = 0; j < expenditure[i].length; j++) {
                    pw.print(expenditure[i][j]);
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

