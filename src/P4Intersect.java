import java.util.ArrayList;

/**
 * Calculates the intersections in a list of Rect210s, writes those intersections to a file.
 * Must be loaded from a rectangle data file.
 * @author nwnorris
 */
public class P4Intersect {

    ArrayList<Rect210> rects;
    private ArrayList<RectIntersect> intersects;
    private LineSweeper sweeper;
    private boolean sweeping = false;
    private String fileName;
    private long runTime;

    /**
     * Constructor, requires a file name and a Rect210Factory to be used when loading the rect file.
     * @param fileName The rect data file to load.
     * @param factory The factory to use when loading the rects.
     */
    public P4Intersect(String fileName, Rect210Factory factory){
        this.fileName = fileName;
        rects = RectIO.readFile(fileName, factory );
        intersects = new ArrayList<>();
    }

    /**
     * Creates a LineSweeper and enables sweeping.
     */
    public void initSweepLine(){
        sweeper = new LineSweeper(this);
        sweeping = true;
    }

    /**
     * Steps through the sweep algorithm one item at a time. If done, saves intersections to file.
     * @return The latest SweepEvent processed.
     */
    public SweepEvent sweep(){
        if(sweeping){
            SweepEvent result = sweeper.sweep();
            if(result == null){
                sweeping = false;
                saveOutput();
            }
            return result;
        } else {
            return null;
        }
    }

    /**
     * Creates a brute force sledgehammer and uses the hammer to calculate all intersections. Writes the result to a file.
     */
    public void bruteForce(){

        RectSledgehammer hammer = new RectSledgehammer(this);
        long start = System.currentTimeMillis();
        intersects = hammer.bruteForce();
        runTime = System.currentTimeMillis() - start;
        saveOutput();
    }

    /**
     * Saves the current intersection list to a file.
     */
    public void saveOutput(){
        RectIO.writeIntersectFile(fileName, intersects);
    }

    /**
     * Gets the number of intersections found.
     * @return The number of intersections.
     */
    public int getNumIntersects(){
        return intersects.size();
    }

    /**
     * Gets the current list of intersections.
     * @return The list containing all currently calculated intersections.
     */
    public ArrayList<RectIntersect> getIntersects() {
        return intersects;
    }

    /**
     * Gets the data list of rectangles used for calculating intersections.
     * @return The list of Rect210s used by this.
     */
    public ArrayList<Rect210> getRects() {
        return rects;
    }

    /**
     * Gets if sweeping is currently enabled.
     * @return True if currently sweeping, false otherwise.
     */
    boolean isSweeping(){
        return sweeping;
    }

    /**
     * Gets the LineSweeper used to calculate intersections.
     * @return The in-use LineSweeper.
     */
    public LineSweeper getSweeper() {
        return sweeper;
    }

    public long getRunTime(){
        return runTime;
    }
}
