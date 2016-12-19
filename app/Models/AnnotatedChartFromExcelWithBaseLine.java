package Models;

        import org.json.simple.JSONArray;
        import org.json.simple.JSONObject;

        import java.text.DecimalFormat;
        import java.util.ArrayList;
        import java.util.Iterator;
        import java.util.Map;
        import java.util.TreeMap;


public class AnnotatedChartFromExcelWithBaseLine extends JsonFromExcel {

    private JSONArray arrTemp= new JSONArray();
    TreeMap<Double,Double> meanPerInterval = new TreeMap<>();
    private TreeMap<Double, Double> blSignal ;
    private int counterForHeader = 0;
    private ArrayList<Activity> activities;
    private ArrayList<Integer> distinctActionType;
    private ArrayList<String> distinctAnno;
    private ArrayList<Integer> consumedActivity = new ArrayList<>();
    private double max =-1000000;
    private double min = 1000000;
    private boolean addToHeader = false;
    private int frameCtr =1;
    private ArrayList<Double> avergaSignal = new ArrayList<>(20);
    private int ctrForArray =0;
    private String baselineName = "Baseline Mean";
    private boolean logarithmic = false;
    private double minY = 0;

    public double findMeanOfInterval( TreeMap<Double, Double> all, double start, double end, boolean abs )
    {
        double tempMean = 0.0;
        int count = 0;
        if(!abs) {
            if (end == -1) {

                for(Map.Entry<Double,Double> entry : all.entrySet()) {
                    Double key = entry.getKey();
                    Double value = entry.getValue();
                    if (!Double.isNaN(value)) {
                        if (key > start) {

                            tempMean = tempMean + value;
                            count++;
                        }
                    }
                }
            } else {
                for(Map.Entry<Double,Double> entry : all.entrySet()) {
                    Double key = entry.getKey();
                    Double value = entry.getValue();
                    if (!Double.isNaN(value)) {
                        if (key >= start && key < end) {
                            tempMean = tempMean + value;
                            count++;
                        }
                    }
                }
            }
        }
        else{
            if (end == -1) {
                for(Map.Entry<Double,Double> entry : all.entrySet()) {
                    Double key = entry.getKey();
                    Double value = entry.getValue();
                    if (!Double.isNaN(value)) {
                        if (key > start) {
                            tempMean = tempMean + Math.abs(value);
                            count++;
                        }
                    }
                }
            } else {
                for(Map.Entry<Double,Double> entry : all.entrySet()) {
                    Double key = entry.getKey();
                    Double value = entry.getValue();
                    if (!Double.isNaN(value)) {
                        if (key >= start && key < end) {
                            tempMean = tempMean + Math.abs(value);
                            count++;
                        }
                    }
                }
            }
        }
        tempMean = tempMean/ count;
        return tempMean;
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


    public void buildDistinctAnnotation()
    {
        double previous =0.0;
        distinctAnno = new ArrayList<>();
        distinctActionType = new ArrayList<>();
        for(Activity temp: activities) {
            //if(!temp.annotation.toLowerCase().contains("light")) {
                if (!distinctActionType.contains(temp.actionType)) {
                    distinctActionType.add(temp.actionType);
                    distinctAnno.add(temp.label);
                }
            if(!temp.annotation.toLowerCase().contains("light")) {
                meanPerInterval.put(temp.startTime, findMeanOfInterval(blSignal, previous, temp.startTime, false));
                meanPerInterval.put(temp.endTime, findMeanOfInterval(blSignal, temp.startTime, temp.endTime, false));
                previous = temp.endTime;

           }
        }



           meanPerInterval.put(blSignal.lastKey(), findMeanOfInterval(blSignal,previous, -1, false));
            //meanPerInterval.put(822.0, findMeanOfInterval(blSignal,previous, -1, false));
            //TODO hanlde the last annoation
            //System.out.println(blSignal);
            //System.out.println("MEan PEr Interval:   " + meanPerInterval);


    }

    public void insertColorBackground()
    {
        //ArrayList<String> distinctAnno = buildDistinctAnnotation();
        JSONObject obj;
        String columnName = "bgCol";
        int util = 1;
        for(int ct=0; ct< distinctAnno.size(); ct++) {
            String str = distinctAnno.get(ct);
            if(str.toLowerCase().contains("multitasking"))
                str = "No Stressor";
            else if(str.toLowerCase().contains("motoric"))
                str = "Sensorimotor Stressor";
            else if(str.toLowerCase().contains("cognitive"))
                str = "Cognitive Stressor";
            else if(str.toLowerCase().contains("motoric"))
                str = "Sensorimotor Stressor";

            if(str.toLowerCase().contains("light"))
            {
                columnName = "bgCol" + "line" +util;
            }
            else
                columnName = "bgCol" + util;

            util++;
            obj = new JSONObject();
            obj.put("id",columnName);
            obj.put("label",str);
            obj.put("type","number");
            obj.put("color","grey");
            header.add(obj);
        }

    }
    public  String getAnnotation(  Double val,  ArrayList<Integer> consumedActivity)
    {
        int ctr =0;
        for(Activity act: activities)
        {
            if((Math.floor(val)>= Math.floor(act.startTime) /*|| Math.floor(val)== Math.floor(act.endTime)*/) && !consumedActivity.contains(ctr)) {
                consumedActivity.add(ctr);
                return act.annotationText;
            }
            ctr++;
        }
        return null;
    }
    public  boolean inActitivity(  Double val, int actionType)
    {
        for(Activity act: activities)
        {
            if(Math.floor(val)>= Math.floor(act.startTime) && Math.floor(val)<= Math.floor(act.endTime) && actionType == act.actionType) {
                return true;
            }
        }
        return false;
    }
    public void addToHeader(String str,  boolean newRow)
    {

        if(str.toLowerCase().contains("breaking"))
            str = "Braking";

        JSONObject obj = new JSONObject();
        //obj.put("id","");
        obj.put("label",str);
        obj.put("type", "number");
        header.add(obj);

        if(counterForHeader==0) {
            obj = new JSONObject();
            // obj.put("id","");
            obj.put("type","string");
            obj.put("role", "annotation");
            header.add(obj);

            obj = new JSONObject();
            //obj.put("id","");
            obj.put("type","string");
            obj.put("role", "annotationText");
            header.add(obj);
        }

        counterForHeader++;
    }
    public void addToContent(Double num,  boolean newRow)
    {
        if(! addToHeader){
            JSONObject obj = new JSONObject();
            //obj.put("id","");
            obj.put("label",baselineName);
            obj.put("type", "number");
            header.add(obj);
            addToHeader = true ;
        }

        String annotation;
        if(!newRow) {
            if(avergaSignal.get(ctrForArray) !=-1.0) // check if the array is empty
                avergaSignal.set(ctrForArray,num + avergaSignal.get(ctrForArray));
            else
                avergaSignal.set(ctrForArray,num);
            ctrForArray++;
        }
        else {
            if(frameCtr % frameRate ==0 ) {
                double time = 0.0;
                JSONObject obj = new JSONObject();
                for(int i= 0; i< ctrForArray; i++){
                    if(i ==0) {  // this is for the time
                        time = Double.parseDouble(new DecimalFormat("#.#").format(avergaSignal.get(i)));
                        addObjectToArrayJson( new DecimalFormat("#.#").format(avergaSignal.get(i)));
                        annotation = getAnnotation( avergaSignal.get(i) ,consumedActivity);
                        if(annotation != null) {
                            addObjectToArrayJson( ".");
                            addObjectToArrayJson(annotation);
                        }
                        else {
                            addObjectToArrayJson( null);
                            addObjectToArrayJson( null);
                        }
                    }
                    else {
                        double temp = (avergaSignal.get(i) / frameRate);
                        addObjectToArrayJson( new DecimalFormat("#.####").format(temp));
                        if(max < temp)
                            max = temp;

                        if(min > temp)
                            min= temp;
                    }
                }
                //addObjectToArrayJson(Double.toString(getMeanForPoint(time)));
                addObjectToArrayJson(new DecimalFormat("#.####").format(getMeanForPoint(time)));
                for(int itr=0; itr<distinctActionType.size(); itr++) // this is to add the annotation background
                {
                    if(inActitivity(avergaSignal.get(0), distinctActionType.get(itr))){
                        addObjectToArrayJson( Double.toString(.03));  //.03 is a dummy to distinguish between null and values
                    }
                    else{
                        addObjectToArrayJson(null);
                    }
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
        }
    }

    private double getMeanForPoint(double point){

        for(Map.Entry<Double,Double> entry : meanPerInterval.entrySet()) {
            Double key = entry.getKey();
            Double value = entry.getValue();
            if (!Double.isNaN(value)) {
                if (point < key) {
                    return value;
                }
            }
           // System.out.println(key + " => " + value);
        }

        // if the baseline is shorter than the current signal
        return   meanPerInterval.get(meanPerInterval.lastKey());

    }
    private  void addObjectToArrayJson( String val){
        JSONObject style1 = new JSONObject();
        style1.put("v", val);
        arrTemp.add(style1);
    }

    public void handleMaxForAnnotations()
    {
        if(max ==0)
            max = 0.05;

        System.out.println("max  " + max);
        System.out.println("min  " + minY);

        double newMax = max + 0.1 * (max - minY);
        if(logarithmic) {
            newMax = max + 0.1 * (max - min);
        }


        System.out.println("new Max  " + newMax);

        JSONObject line,head, cel;
        JSONArray arr;
        int aux =0;
        boolean isFirst = true;
        Iterator each = content.iterator(), it;

        if(!logarithmic || max > 1) {
            while (each.hasNext()) {
                line = (JSONObject) each.next();
                arr = (JSONArray) line.get("c");
                it = arr.iterator();
                aux = 0;
                while (it.hasNext()) {
                    head = (JSONObject) header.get(aux);
                    cel = (JSONObject) it.next();
                    if (head.get("color") == "grey") {
                        if (cel.get("v") != null) {
                            cel.put("v", newMax);  // if this is stimuls but the vlues are more than 1
                        }

                    }
                    aux++;
                }
            }
        }
        else{
            while (each.hasNext()) {
                line = (JSONObject) each.next();
                arr = (JSONArray) line.get("c");
                it = arr.iterator();
                aux = 0;
                while (it.hasNext()) {
                    head = (JSONObject) header.get(aux);
                    cel = (JSONObject) it.next();
                    if (head.get("color") == "grey") {
                        if (cel.get("v") != null) {

                            if(head.get("id").toString().toLowerCase().contains("line")) {
                                cel.put("v", newMax);
                            }
                            else
                                cel.put("v", min);  // if this is stimuls but the vlues are more than 1
                        }

                    }
                    aux++;
                }
            }
        }
    }

    public void finalSteps()
    {
        insertColorBackground();
        handleMaxForAnnotations();

    }

    public AnnotatedChartFromExcelWithBaseLine(String fileName,TreeMap<Double, Double> bl,  ArrayList<Activity>  act, int signal, String baselineSessionName, int isLog, double minY)
    {
        super(signal, fileName);
        activities = act;
        blSignal = bl;
        intializeArray();
        buildDistinctAnnotation();
        if(baselineSessionName != null) {
            baselineName = "Mean " +baselineSessionName;
        }

        if(isLog ==1)
            logarithmic = true;
        this.minY = minY;
    }


}
