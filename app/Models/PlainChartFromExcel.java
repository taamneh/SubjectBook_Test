package Models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by staamneh on 4/28/2015.
 */

public class PlainChartFromExcel extends JsonFromExcel {


    private JSONArray arrTemp= new JSONArray();
    private ArrayList<Double> avergaSignal = new ArrayList<>(40);
    private int ctrForArray =0;
    private int frameCtr=1;

    public void addToHeader(String str,  boolean newRow)
    {

        if(str.toLowerCase().contains("breaking"))
            str = "Braking";
        JSONObject obj = new JSONObject();
        obj.put("id","");
        obj.put("label",str);
        obj.put("type", "number");
        header.add(obj);

    }
    public void intializeArray()
    {
        for(int i=0; i<40; i++)
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
           /* JSONObject firstVal = new JSONObject();
            firstVal.put("v", new DecimalFormat("#.####").format(num));
            arrTemp.add(firstVal);*/

            if(avergaSignal.get(ctrForArray) !=-1.0) // check if the araay is epty
                avergaSignal.set(ctrForArray,num + avergaSignal.get(ctrForArray));
            else
                avergaSignal.set(ctrForArray,num);
            ctrForArray++;
        }
        else { // if this is a new record
            if(frameCtr % frameRate ==0 ) {
                JSONObject obj = new JSONObject();
                for(int i= 0; i< ctrForArray; i++){
                    JSONObject firstVal = new JSONObject();
                    if(i ==0) {  // this is for the time
                        firstVal.put("v", new DecimalFormat("#.#").format(avergaSignal.get(i)));
                    }
                    else {
                        firstVal.put("v", new DecimalFormat("#.####").format(avergaSignal.get(i) / frameRate));
                    }
                    arrTemp.add(firstVal);
                }
                obj.put("c", arrTemp);
                content.add(obj);
                rest();
                arrTemp = new JSONArray();
            }
            frameCtr++; // increase the counter for frame
            ctrForArray =0;
            avergaSignal.set(ctrForArray,num);
            ctrForArray++;
            /*arrTemp = new JSONArray();
            JSONObject firstVal = new JSONObject();
            firstVal.put("v", new DecimalFormat("#.##").format(num)); // this will be the time
            arrTemp.add(firstVal);*/

        }

    }
    public PlainChartFromExcel(String fileName, int signal )
    {
        super(signal, fileName);
        intializeArray();

    }

 }
