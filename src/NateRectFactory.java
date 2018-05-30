import java.util.ArrayList;
import java.util.Random;

/**
 * A Rect210Factory implementation; handles creation of random and non-random NateRects.
 * @author nwnorris
 */
public class NateRectFactory implements Rect210Factory {

    int maxSize;
    int minSize;
    Random random;

    /**
     * Constructor, needs a min and max rect size.
     * @param min The minimum rect x/y width.
     * @param max The maximum rect x/y width.
     */
    public NateRectFactory(int min, int max){
        random = new Random();
        minSize = min;
        maxSize = max;
    }

    /**
     * Creates a certain amount of random NateRects.
     * @param namePrefix Name prefix for all rects
     * @param numRects Number of rects to create
     * @param paneWidth Width of the pane where all rects reside
     * @param paneHeight Height of the pane where all rects reside
     * @return A list containing the randomly generated rects.
     */
    public ArrayList<Rect210> getRandomRects(String namePrefix, int numRects, int paneWidth, int paneHeight) {
        ArrayList<Rect210> rects = new ArrayList<>();
        for(int i = 0; i < numRects; i++){
            rects.add(getRandomRect(namePrefix + i, paneWidth, paneHeight));
        }
        return rects;
    }

    /**
     * Creates one random NateRect.
     * @param name The rect name
     * @param paneWidth Width of the pane where rect resides
     * @param paneHeight Height of the pane where rect resides
     * @return A random Rect210
     */
    public Rect210 getRandomRect(String name, int paneWidth, int paneHeight) {
        int xBound = maxSize;
        int yBound = maxSize;
        int x = random.nextInt(paneWidth);
        int y = random.nextInt(paneHeight);

        //Don't go offscreen when generating rects
        if(x + maxSize > paneWidth){
            xBound = (paneWidth - x);
        }
        if(y + maxSize > paneHeight){
            yBound = paneHeight - y;
        }

        int w = random.nextInt(xBound) + minSize;
        int h = random.nextInt(yBound) + minSize;
        return getRect(name, x, y, w, h);
    }

    /**
     * Creates a NateRect with the specified parameters.
     * @param name The rect name
     * @param x The x coordinate of the upper right point
     * @param y The y coordinate of the upper right point
     * @param width The width (x axis)
     * @param length The length (y axis)
     * @return A new NateRect with the specified parameters.
     */
    public Rect210 getRect(String name, int x, int y, int width, int length) {
        return new NateRect(x, y, width, length, name);
    }

    /**
     * Gets the max x/y width of a rect from this factory.
     * @return The max size of a factory rect.
     */
    public int getMaxSize(){
        return maxSize;
    }

    /**
     * Gets the min x/y width of a rect from this factory.
     * @return The min size of a factory rect.
     */
    public int getMinSize(){
        return minSize;
    }

    /**
     * Sets the max size to a specified value.
     * @param n The new max size.
     */
    public void setMaxSize(int n){
        maxSize = n;
    }

    /**
     * Sets the min size to a specified value.
     * @param n The new min size;
     */
    public void setMinSize(int n){
        minSize = n;
    }
}
