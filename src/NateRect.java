import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * A Rect210 implementation by Nate Norris. Rects can test if they contain and intersect another Rect210.
 * @author nwnorris
 */
public class NateRect implements Rect210 {

    Rectangle rect;
    String name;

    /**
     * Constructor, requires an (x,y) coordinate, width/height, and a name;
     * @param x The cartesian x coordinate
     * @param y The cartesian y coordinate
     * @param w The x width of the rectangle
     * @param h The y height of the rectangle
     * @param n The rectangle's name
     */
    public NateRect(int x, int y, int w, int h, String n){
        rect = new Rectangle(x, y, w, h);
        rect.setFill(Color.TRANSPARENT);
        name = n;
    }

    /**
     * Tests if this rectangle intersects another Rect210.
     * @param r2 The other rect
     * @return True if the two rects intersect, false otherwise.
     */
    public boolean intersects(Rect210 r2) {
        //Four cases where the rects cannot intersect, if any one is true there can't be an intersection
        boolean topY = (getYMax() < r2.getY());
        boolean botY = (getY() > r2.getYMax());
        boolean leftX = (getXMax() < r2.getX());
        boolean rightX = (getX() > r2.getXMax());
        if(topY || botY || leftX || rightX) return false;
        return true;

    }

    /**
     * Tests if a rect is entirely inside this rect.
     * @param r2 The other rect
     * @return True if r2 is inside this. False otherwise.
     */
    public boolean contains(Rect210 r2) {
        return ( ( r2.getX() > getX() ) && ( r2.getY() > getY() ) && (r2.getXMax() < getXMax() && ( r2.getYMax() < getYMax() )));
    }

    /**
     * Compares two Rect210s based on X coordinates.
     * @param r2 The other rect to compare to.
     * @return -1 if this.x is < r2.x, 0 if they are equal, 1 if this.x is > than r2.x.
     */
    public int compareTo(Rect210 r2){
        if(getX() < r2.getX()){
            return -1;
        } else if(getX() > r2.getX()){
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Gets the JavaFX Rectangle stored by the object.
     * @return A JavaFX Rectangle.
     */
    public Rectangle getRectangle() {
        return rect;
    }

    /**
     * Gets the name of this rectangle.
     * @return The rectangle's name.
     */
    public String getName(){
        return name;
    }

    /**
     * Gets the maximum X value occupied by this rect.
     * @return The rightmost x boundary of this rect.
     */
    public int getXMax(){
        return getX() + getWidth();
    }

    /**
     * Gets the maximum Y value occupied by this rect.
     * @return The bottommost y boundary of this rect.
     */
    public int getYMax(){
        return getY() + getHeight();
    }

    /**
     * Gets the X coordinate of this rect.
     * @return The X coordinate
     */
    public int getX(){
        return (int) rect.getX();
    }

    /**
     * Gets the Y coordinate of this rect.
     * @return The Y coordinate
     */
    public int getY(){
        return (int) rect.getY();
    }

    /**
     * Gets the width of this rect.
     * @return The rect's width.
     */
    public int getWidth(){
        return (int) rect.getWidth();
    }

    /**
     * Gets the height of this rect.
     * @return The rects' height.
     */
    public int getHeight(){
        return (int) rect.getHeight();
    }
}
