import java.util.ArrayList;
import java.util.Scanner;

/**
 * Generates a specified amount of random rectangles and saves them to a data file.
 * @author nwnorris
 */
public class P4Generate {

    private int rectToGenerate = 100;
    String prefix = "R";
    private int paneWidth = 900;
    private int paneHeight = 600;
    private int minSize = 5;
    private int maxSize = 200;
    private NateRectFactory factory;

    /**
     * Constructor, immediately queries the user and generates the rectangles.
     */
    public P4Generate(){
        query();
        generate();
    }

    /**
     * Queries the user for non-standard parameters, giving the user the option to use default values.
     */
    public void query(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to use default values?");
        String dfault = scanner.nextLine();

        //Get non-standard parameters from user
        //Thanks Prof Bill for P4Helper!
        if(!(dfault.equalsIgnoreCase("y") || dfault.equalsIgnoreCase("yes") || dfault.equalsIgnoreCase("true"))){
            rectToGenerate = P4Helper.queryUserInt(scanner, "Rects to generate: ", rectToGenerate);
            prefix = P4Helper.queryUserString(scanner, "Prefix: ", prefix);
            paneWidth = P4Helper.queryUserInt(scanner, "Pane width: ", paneWidth);
            paneHeight = P4Helper.queryUserInt(scanner, "Pane height: ", paneHeight);
            minSize = P4Helper.queryUserInt(scanner, "Min size: ", minSize);
            maxSize = P4Helper.queryUserInt(scanner, "Max size: ", maxSize);
        }
    }

    /**
     * Generates the specified rectangles using a NateRectFactory.
     */
    private void generate(){
        factory = new NateRectFactory(minSize, maxSize);
        ArrayList<Rect210> rects = factory.getRandomRects(prefix, rectToGenerate, paneWidth, paneHeight);
        RectIO.writeFile(prefix, rects);
        System.out.println(rectToGenerate + " rects generated.");
    }

    /**
     * Gets the Rect210Factory used by this to generate rects.
     * @return The Rect210Factory in use.
     */
    public Rect210Factory getFactory(){
        return factory;
    }


    /**
     * Optional main so that P4Generate can be run from the command line.
     * @param args JVM arguments
     */
    public static void main(String...args){
        new P4Generate();
    }

}
