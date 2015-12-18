package Models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sun.reflect.generics.tree.Tree;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by staamneh on 5/7/2015.
 */

/**
 * Created by staamneh on 4/28/2015.
 */

public class ForBarFromExcel extends JsonFromExcel {

    ArrayList<Double> templst = new ArrayList<Double>();
    ArrayList<Double> timeList = new ArrayList<Double>();
    public TreeMap<Double, Double> timeAndData = new TreeMap<Double,Double>();
    private JSONArray arrTemp= new JSONArray();
    private ArrayList<Double> avergaSignal = new ArrayList<>(20);
    private double lastTimeVal =0;
    private int ctrForArray =0;
    private int frameCtr=1;
    private int readFromColum = 1;

    public void addToHeader(String str,  boolean newRow)
    {

        JSONObject obj = new JSONObject();
        obj.put("id","");
        obj.put("label",str);
        obj.put("type", "number");
        header.add(obj);

    }
    public void intializeArray()
    {
        for(int i=0; i<20; i++)
            avergaSignal.add(-1.0);
    }

    public void rest()
    {
        for(int i=0; i<avergaSignal.size() ; i++)
            avergaSignal.set(i, -1.0);
    }
    public void addToContent(Double num,  boolean newRow)
    {

        if(!newRow) {     // if this is not a new record

            if(ctrForArray ==readFromColum)
            {

                templst.add(num);
                timeAndData.put(lastTimeVal, num);
            }
            ctrForArray++;
        }
        else { // if this is a new record
            //if(frameCtr % frameRate ==0 ) {
              // templst.add(tempVal/frameRate);
                rest();
            //}
            frameCtr++; // increase the counter for frame
            ctrForArray =0;
            if(ctrForArray == 0) {
                lastTimeVal = num;
                timeList.add(num);
            }

            //avergaSignal.set(ctrForArray,num);
            ctrForArray++;


        }

    }
    public ForBarFromExcel(String fileName, int signal )
    {
        super(signal, fileName);
        intializeArray();

    }
    public ForBarFromExcel(String fileName, int signal, int readFrom )
    {
        super(signal, fileName);
        intializeArray();
        readFromColum = readFrom;

    }

    public ArrayList<Double> getArrayOfDouble()
    {
        return templst;
    }
    public ArrayList<Double> getTimeArray()
    {
        return timeList;
    }
}
