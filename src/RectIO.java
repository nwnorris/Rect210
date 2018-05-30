
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * File IO for rectangles.
 * 
 * @author williamkrieger
 * Slight modifications by nwnorris
 */
public class RectIO {

    /**
     * Reads a rect file and returns a list of rectangles.
     * 
     * @param fileName The file name
     * @param factory Factory used to create rects when you read them
     * @return Returns an ArrayList of rectangle. On error, null is returned.
     */
    public static ArrayList<Rect210> 
        readFile( String fileName, Rect210Factory factory) {
                ArrayList<Rect210> rectList = new ArrayList();
        try {
            File f = new File( fileName);
            Scanner reader = new Scanner ( f);
            int numRects = reader.nextInt();
            int count = 0;
            while( reader.hasNext()  &&  count < numRects) {
                // get rect params
                String name = reader.next();
                int x = reader.nextInt();
                int y = reader.nextInt();
                int width = reader.nextInt();
                int height = reader.nextInt();

                // create rect according to params using factory
                Rect210 r = factory.getRect(name, x, y, width, height);
                rectList.add( r);
            }
        } catch( FileNotFoundException exc) {
            System.out.println( exc);
            rectList = null;
        }
        return rectList;
    }

    /**
     * Write a list of rectangles to a rect file.
     * @param fileName The output file name
     * @param rectList The list of rectangles to write
     * @return Returns true if successful. On error, false is returned.
     */
    public static boolean writeFile( String fileName, 
            ArrayList<Rect210> rectList) {
        try {
            File f = new File( fileName+".txt");
            PrintWriter writer = new PrintWriter( f);
            writer.println( rectList.size());
            for( Rect210 r: rectList) {
                StringBuilder sb = new StringBuilder();
                sb.append( r.getName());
                sb.append( " ");
                sb.append( r.getX());
                sb.append( " ");
                sb.append( r.getY());
                sb.append( " ");
                sb.append( r.getWidth());
                sb.append( " ");
                sb.append( r.getHeight());
                writer.println( sb.toString());
            }
            writer.close();
        } catch( FileNotFoundException exc) {
            System.out.println( exc);
            return false;
        }
        return true;
    }

    /**
     * Write a file of rect intersections
     * @param fileName The output file name
     * @param intersections The list of rect intersections
     * @return Returns true if the write was successful.
     * On error, false is returned.
     */
    public static boolean writeIntersectFile( String fileName, 
            ArrayList<RectIntersect> intersections) {
        // open the output file
        PrintWriter writer;
        try {
            // create output file
            writer = new PrintWriter( fileName+"_intersect.txt");
        } catch (FileNotFoundException exc) {
            System.out.println(exc);    // bad output filem return false
            return false;
        }

        // sort intersection by name, then write the list to output file
        Collections.sort( intersections);
        writer.println( intersections.size());
        for( RectIntersect ri: intersections) {
            writer.println( ri);
        }
        writer.close();
        return true;
    }
}
