
import javafx.scene.shape.Rectangle;

/**
 * A rectangle that has a name and can be drawn using JavaFX.
 * Rect210 has-a JavaFX Rectangle.
 * 
 * @author williamkrieger
 */
public interface Rect210 extends Comparable<Rect210> {
    /**
     * Returns the rect name.
     * @return Returns the rect name
     */
    public String getName();

    /**
     * Returns the JavaFX rectangle, so the rect can be drawn easily.
     * @return Returns the JavaFX rectangle
     */
    public Rectangle getRectangle();

    /**
     * Returns the x coordinate of the rect's upper left point.
     * @return Returns the x coordinate
     */
    public int getX();

    /**
     * Returns the y coordinate of the rect's upper left point.
     * @return Returns the y coordinate
     */
    public int getY();

    /**
     * Returns the width (x-axis) of the rect.
     * @return Returns the width
     */
    public int getWidth();

    /**
     * Returns the height (y-axis) of the rect.
     * @return Returns the height
     */
    public int getHeight();

    /**
     * Returns the max x coordinate of the rect.
     * The lower right point, xMax = x + width.
     * @return Returns the max x coordinate
     */
    public int getXMax();

    /**
     * Returns the max y coordinate of the rect.
     * The lower right point, yMax = y + height.
     * @return 
     */
    public int getYMax();

    /**
     * Returns true if the this rectangles intersect with r2.
     * @param r2 The other rect
     * @return Returns true if they intersect
     */
    public boolean intersects( Rect210 r2);

    /**
     * Returns true if this rectangle contains r2.
     * @param r2 The other rect
     * @return Returns true if ir contains r2
     */
    public boolean contains( Rect210 r2);

}
