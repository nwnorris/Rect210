import java.util.ArrayList;

/**
 * Brute forces the number of intersections between a list of rectangles.
 * @author nwnorris
 */
public class RectSledgehammer {

    private P4Intersect intersect;

    /**
     * Constructor, requires a P4Intersect to hook into.
     * @param p4 The P4Intersect parent.
     */
    public RectSledgehammer(P4Intersect p4){
        intersect = p4;
    }

    /**
     * Brute forces the intersections. Order n^2, please don't use this. Please. It makes me vomit.
     * @return A list of all the intersections.
     */
    public ArrayList<RectIntersect> bruteForce(){
        ArrayList<Rect210> rects = intersect.getRects();
        ArrayList<RectIntersect> out = new ArrayList<>();

        for(int i = 0; i < rects.size(); i++){
            for(int j = i+1; j < rects.size(); j++){
                if(rects.get(i).intersects(rects.get(j))){
                    out.add(new RectIntersect(rects.get(i), rects.get(j)));
                }
            }
        }
        return out;
    }
}
