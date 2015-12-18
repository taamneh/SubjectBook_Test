package Models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Created by staamneh on 4/28/2015.
 */
public abstract class JsonFromExcel {
    private String fileName;
    private int signalType;
    public int frameRate;
    public  JSONArray header = new JSONArray();
    public  JSONArray content = new JSONArray();


    public JsonFromExcel(int f_Rate, String fileName){
        this.fileName = fileName;
        this.signalType = signalType;
        // frameRate = getFrameRate();
        frameRate = f_Rate;
    }

    /*public int getFrameRate() {
        if(signalType == 3 || signalType == 6 ||  signalType ==11) {
            return 8;
        }
        else if(signalType==2)
            return 16;
        else if(signalType == 13)
            return 20;
        else if(signalType ==1  )
            return 4;
        else if(signalType ==5 )
            return 16;
        else
            return 1;
    }*/

    abstract void addToHeader(String str, boolean newRow);
    abstract void addToContent(Double num, boolean newRow);
    void addToContent(String num, boolean newRow){  // this for bar chart

    }


    public JSONObject getJosonForChart()
    {
        JSONObject all = new JSONObject();
        all.put("cols" , header);
        all.put("rows" , content);
        return all;
    }



    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }
}
