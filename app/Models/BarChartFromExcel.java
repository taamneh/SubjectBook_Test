package Models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by staamneh on 5/20/2015.
 */
public class BarChartFromExcel extends JsonFromExcel {

    private boolean isFirst = true;
    private JSONArray arrTemp= new JSONArray();
    ArrayList<Double> templst = new ArrayList<Double>();
    private int counter =0;

    public void addToHeader(String str, boolean newRow){
        if(isFirst)
        {
            JSONObject obj = new JSONObject();
            obj.put("id","");
            obj.put("label",str);
            obj.put("type", "string");
            header.add(obj);

            isFirst = false;
        }
        else {
            JSONObject obj = new JSONObject();
            obj.put("id", "");
            obj.put("label", str);
            obj.put("type", "number");
            header.add(obj);

        }
    }
    public void addToContent(Double num, boolean newRow){

        if(!newRow) {     // if this is not a new record
           JSONObject firstVal = new JSONObject();
            firstVal.put("v",num);
            arrTemp.add(firstVal);


            if(templst.size() >counter)
                templst.set(counter, num + templst.get(counter));
            else
                templst.add(num);

            counter++;

        }
        else { // if this is a new record

            JSONObject obj = new JSONObject();
            obj.put("c", arrTemp);
            content.add(obj);
            arrTemp = new JSONArray();
            JSONObject firstVal = new JSONObject();
            firstVal.put("v", num); // this will be the time
            arrTemp.add(firstVal);

            counter =0;


        }

    }

    public ArrayList<Double> getArrayOfDouble()
    {

        return templst;
    }

    public void addToContent(String num, boolean newRow){


       // System.out.println(num + "  ------------" + newRow);
        if(!newRow) {     // if this is not a new record
            JSONObject firstVal = new JSONObject();
            firstVal.put("v",num);
            arrTemp.add(firstVal);

        }
        else { // if this is a new record

            JSONObject obj = new JSONObject();
            obj.put("c", arrTemp);
            content.add(obj);
            arrTemp = new JSONArray();
            JSONObject firstVal = new JSONObject();
            firstVal.put("v", num); // this will be the time
            arrTemp.add(firstVal);

            counter =0;
        }

    }
    public void finalize (){

        if(arrTemp.size() >0) {
            JSONObject obj = new JSONObject();
            obj.put("c", arrTemp);
            content.add(obj);
        }

    }




    public BarChartFromExcel(String fileName, int signalType){
        super (signalType, fileName);
    }
}
