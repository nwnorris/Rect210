import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Sweeps through a list of Rect210s and counts their intersections.
 * @author nwnorris
 */
public class LineSweeper {

    private PriorityQueue<SweepEvent> events;
    ArrayList<Rect210> sweepList;
    private P4Intersect intersect;
    private boolean sweeping = false;
    private boolean hasLastInt = false;
    private RectIntersect lastInt;

    /**
     * Constructor, requires a P4Intersect parent to store information in.
     * @param in The P4Intersect object to hook the LineSweeper to.
     */
    public LineSweeper(P4Intersect in){
        intersect = in;
        initSweepLine();
    }

    /**
     * Initializes the LineSweeper, clearing parent intersect values and creating the priority queue.
     */
    public void initSweepLine(){
        //Create our priority queue with inline comparator definition.
        events = new PriorityQueue<>(new Comparator<SweepEvent>() {
            @Override
            public int compare(SweepEvent o1, SweepEvent o2) {
                if(o1.getValue() < o2.getValue()){
                    return -1;
                } else if(o1.getValue() > o2.getValue()){
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        //Add enter and exit events for each rect
        for(Rect210 r : intersect.rects){
            events.add(new SweepEvent(r, SweepEvent.SWEEPEVENT_ENTER));
            events.add(new SweepEvent(r, SweepEvent.SWEEPEVENT_EXIT));
        }

        //Final steps
        intersect.getIntersects().clear();
        sweepList = new ArrayList<>();
        sweeping = true;

    }

    /**
     * Each call of sweep() steps through the sweepLine algorithm.
     * @return
     */
    public SweepEvent sweep(){

        //We're done if empty
        if(!events.isEmpty()) {

            SweepEvent min = events.poll();

            //Entrance event
            if (min.getEventType() == SweepEvent.SWEEPEVENT_ENTER) {
                sweepList.add(min.getRect());
            //Exit event
            } else if (min.getEventType() == SweepEvent.SWEEPEVENT_EXIT) {
                sweepList.remove(min.getRect());
                //Check for intersections on exit
                for (Rect210 r : sweepList) {
                    if (min.getRect().intersects(r)) {
                        RectIntersect newInt = new RectIntersect(min.getRect(), r);
                        lastInt = newInt;
                        hasLastInt = true;
                        intersect.getIntersects().add(newInt);
                    }
                }
            }
            return min;
        } else {
            return null;
        }

    }

    /**
     * Get the rects currently intersecting the sweep line.
     * @return All rectangles in the sweep list.
     */
    public ArrayList<Rect210> getSweepList() {
        return sweepList;
    }

}
