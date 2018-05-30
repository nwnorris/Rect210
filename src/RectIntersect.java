
/**
 * This class records two intersecting rectangles.
 * @author williamkrieger
 */
public class RectIntersect implements Comparable<RectIntersect> {
    /** first intersecting rect */
    private final Rect210 rect1;
    /** second intersecting rect */
    private final Rect210 rect2;

    /**
     * ctor
     * @param r1 First rect
     * @param r2 Second rect
     */
    public RectIntersect( Rect210 r1, Rect210 r2) {
        if( r1.getName().compareTo(r2.getName()) <= 0) {
            this.rect1 = r1;
            this.rect2 = r2;
        }
        else {
            this.rect1 = r2;
            this.rect2 = r1;
        }
    }

    /**
     * Return the first rect.
     * @return Return the first rect
     */
    public Rect210 getRect1() { return this.rect1; }

    /**
     * Return the second rect.
     * @return Return the second rect
     */
    public Rect210 getRect2() { return this.rect2; }

    /**
     * Returns a nicely formatted string of the intersection.
     * It's rect1-name " " rect2-name.
     * @return Returns a nicely formatted string of the intersection
     */
    @Override
    public String toString() {
        String s = rect1.getName() + " " + rect2.getName();
        return s;
    }

    /**
     * RectIntersect objects are sorted by R1 name and then R2 name.
     * @param other The other rect intersect object
     * @return Returns (this name - other name)
     */
    @Override
    public int compareTo(RectIntersect other) {
        int comp = this.rect1.getName().compareTo( other.rect1.getName());
        if( comp == 0) { 
            comp = this.rect2.getName().compareTo( other.rect2.getName());
        }
        return comp;
    }
}
