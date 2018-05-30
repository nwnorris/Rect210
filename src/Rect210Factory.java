
import java.util.ArrayList;

/**
 * This factory creates rectangles, regular and random.
 * 
 * @author williamkrieger
 */
public interface Rect210Factory {
    /**
     * Return the max size parameter for random rects.
     * @return Return the max size parameter
     */
    public int getMaxSize();

    /**
     * Returns the min size parameter for random rects.
     * @return Returns the min size parameter
     */
    public int getMinSize();

    /**
     * Set the max size parameter for random rects.
     * @param size The max size setting
     */
    public void setMaxSize( int size);

    /**
     * Set the min size parameter for random rects.
     * @param size The min size setting
     */
    public void setMinSize( int size);

    /**
     * Returns a new rect.
     * @param name The name
     * @param x The x coordinate of the upper right point
     * @param y The y coordinate of the upper right point
     * @param width The width (x axis)
     * @param length The length (y axis)
     * @return Returns a new rect
     */
    public Rect210 getRect(String name, int x, int y,
            int width, int length);

    /**
     * Returns a random rect.
     * The pane width and height define max x and y values for 
     * randomly-created rects.
     * @param name The name
     * @param paneWidth Width of the pane where rect resides
     * @param paneHeight Height of the pane where rect resides
     * @return 
     */
    public Rect210 getRandomRect(String name, int paneWidth, int paneHeight);

    /**
     * Returns N random rects.
     * The pane width and height define max x and y values for 
     * randomly-created rects.
     * @param namePrefix Name prefix for all rects
     * @param numRects Number of rects to create
     * @param paneWidth Width of the pane where all rects reside
     * @param paneHeight Height of the pane where all rects reside
     * @return Returns N random rects
     */
    public ArrayList<Rect210> getRandomRects(String namePrefix, int numRects, 
            int paneWidth, int paneHeight);

}
