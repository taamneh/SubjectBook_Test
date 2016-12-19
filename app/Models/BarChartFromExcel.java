package Models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by staamneh on 5/20/2015.
 */
public class BarChartFromExcel extends JsonFromExcel {

    private boolean isFirst = true;
    private JSONArray arrTemp= new JSONArray();
    ArrayList<Double> templst = new ArrayList<Double>();
    ArrayList<String> colNames = new ArrayList<String>();
    private int counter =0;
    TreeMap<String, String> namesMapping = new TreeMap<>();

    public void addToHeader(String str, boolean newRow){
        if(isFirst)
        {
            if(namesMapping.containsKey(str.replaceAll("\\s+", "")))
            {
                str = namesMapping.get(str.replaceAll("\\s+", ""));
            }
            else if(namesMapping.containsKey(str)){
                str = namesMapping.get(str);
            }
            JSONObject obj = new JSONObject();
            obj.put("id","");
            obj.put("label",str);
            obj.put("type", "string");
            header.add(obj);

            isFirst = false;
        }
        else {
            if(namesMapping.containsKey(str.replaceAll("\\s+", "")))
            {
                str = namesMapping.get(str.replaceAll("\\s+", ""));
            }
            else if(namesMapping.containsKey(str)){
                str = namesMapping.get(str);
            }
            JSONObject obj = new JSONObject();
            obj.put("id", "");
            obj.put("label", str);
            obj.put("type", "number");
            header.add(obj);

            colNames.add(str);

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
    public TreeMap<String, Double> getCloNamesWithVal()
    {

        TreeMap<String, Double> tree = new TreeMap<String,Double>();

        for(int i =0; i <colNames.size(); i++ )
        {
            if(templst.size() > i)
                tree.put(colNames.get(i), templst.get(i));
            else
                tree.put(colNames.get(i), 0.0);
        }

        return tree;
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

        /*namesMapping.put("Normal Drive", "ND");
        namesMapping.put("Loaded Drive 1", "LD1");
        namesMapping.put("Loaded Drive 2", "LD2");
        namesMapping.put("Loaded Drive 3", "LD3");
        namesMapping.put("Loaded Drive 4", "LD4");
        namesMapping.put("Failure Drive", "FD");*/



    }


    public BarChartFromExcel(String fileName, int signalType, TreeMap<String, String> mp){
        super (signalType, fileName);

        if(mp != null){



            Iterator it = mp.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                namesMapping.put(pair.getKey().toString(), pair.getValue().toString());
                System.out.println(pair.getKey());
               // it.remove(); // avoids a ConcurrentModificationException
            }
        }




    }}
