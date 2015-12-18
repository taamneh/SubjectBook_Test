package Models;

import java.util.ArrayList;

/**
 * Created by staamneh on 5/18/2015.
 */
public class MeanAndSizeOfSignal {
    public double mean;
    public int size;
    public ArrayList<Double> allNum;
    public MeanAndSizeOfSignal(double m, int s, ArrayList<Double> all){
        mean= m;
        size= s;
        allNum = all;
    }

}

