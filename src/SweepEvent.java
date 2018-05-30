/**
 * Holds a rectangle and an event type; used for putting rectangles in a priority queue.
 * @author nwnorris
 */
public class SweepEvent {

    //The two static event types
    public static final int SWEEPEVENT_ENTER = 0;
    public static final int SWEEPEVENT_EXIT = 1;

    private int eventType;
    private Rect210 rect;

    /**
     * Constructor, requires a Rect210 and an event type.
     * @param r The rectangle
     * @param t The event type (always 0 or 1)
     */
    public SweepEvent(Rect210 r, int t){
        rect = r;
        eventType = t;
    }

    /**
     * Gets the X value for the event; event type determines which x value is relevant.
     * @return The X value for the event
     */
    public int getValue(){
        if(eventType == SWEEPEVENT_ENTER){
            return rect.getX();
        } else if(eventType == SWEEPEVENT_EXIT){
            return rect.getXMax();
        } else {
            return -1;
        }
    }

    /**
     * Gets the stored rectangle.
     * @return The Rect210 for this event.
     */
    public Rect210 getRect() {
        return rect;
    }

    /**
     * Gets what kind of event this is.
     * @return 0 for enter, 1 for exit.
     */
    public int getEventType(){
        return eventType;
    }
}
