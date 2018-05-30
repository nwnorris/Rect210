
import java.util.Scanner;

/**
 * Some helper code for P4.
 * @author williamkrieger
 */
public class P4Helper {

    /**
     * Query user for an int value and return it.
     * @param keyboard The input Scanner, probably System.in
     * @param prompt User prompt
     * @param defaultValue Default value is returned on error or 
     * user carriage return
     * @return Returns user's int value
     */
    public static int queryUserInt( Scanner keyboard, String prompt, 
            int defaultValue) {
        int theValue = defaultValue;    // return value, assume default for now

        System.out.print( prompt + " [default=" + defaultValue + "]: ");
        String line = keyboard.nextLine();
        try {
            theValue = Integer.parseInt(line);
        } catch( NumberFormatException exc) {
            // no action, default will be used
        }

        return theValue;
    }

    /**
     * Query user for a String value and return it. 
     * @param keyboard The input Scanner, probably System.in
     * @param prompt User prompt
     * @param defaultValue Default value is returned on error or 
     * user carriage return
     * @return Returns user's String value
     */
    public static String queryUserString( Scanner keyboard, String prompt,
            String defaultValue) {
        String theValue = defaultValue;

        System.out.print( prompt + "[default=" + defaultValue + "]: ");
        String line = keyboard.nextLine();
        if( line.length() > 0) {
            theValue = line;
        }
        return theValue;
    }
}
