import java.text.DecimalFormat;

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
    public void populateTable() {

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
     * Displays the expenditure table in a formatted manner.
     * The output includes each month and its corresponding expenditures.
     */
    public void displayInformation() {
        System.out.println("Expenditure Table:");
        System.out.printf("%-10s %-10s %-10s %-10s %-10s%n", "Month", "Food (£)", "Rent (£)", "Clothes (£)", "Socialising (£)");
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        for (int i = 0; i < expenditure.length; i++) {
            System.out.printf("%-10s", months[i]);
            for (int j = 0; j < expenditure[i].length; j++) {
                System.out.printf("%-10.2f", expenditure[i][j]);
            }
            System.out.println();
        }
    }
}

