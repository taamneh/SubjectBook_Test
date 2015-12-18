package Models;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by staamneh on 1/29/2015.
 */
public class Activity {
    public double startTime;
    public double endTime;
    public int actionType;
    public String annotation;
    public String annotationText;
    public String label;

    Activity(double startTime,double endTime,int actionType, String annotation, String annotationText, String label)
    {
        this.startTime = startTime;
        this.endTime = endTime;
        this.actionType = actionType;
        this.annotation = annotation;
        this.annotationText = annotationText;
        this.label = label;
    }
}
