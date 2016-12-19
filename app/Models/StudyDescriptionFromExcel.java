package Models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by staamneh on 3/18/2016.
 */
public class StudyDescriptionFromExcel extends JsonFromExcel  {


    private int counter =0;
    TreeMap<String, String> namesMapping = new TreeMap<>();

    ArrayList <SessionDescription> allSessions = new ArrayList<SessionDescription>();
    SessionDescription currentSession = new SessionDescription();

    public void addToHeader(String str, boolean newRow){
        System.out.println("Header: " +str);
    }
    public void addToContent(Double num, boolean newRow){

        System.out.println("ContentDouble:" + num + " ctr = " + counter);

        if(!newRow) {     // if this is not a new record

            if(counter ==2){
                if(num == 1)
                    currentSession.setHide(true);
                else
                    currentSession.setHide(false);
            } else if(counter == 3){
                    currentSession.setOrder(num.intValue());
            } else if(counter ==4) {
                if(num == 1)
                    currentSession.setMutualEx(true);
                else
                    currentSession.setMutualEx(false);

            }
            counter++;
        }
        else { // if this is a new record

            //allSessions.add(currentSession);
            //currentSession = new SessionDescription();
            //counter =0;

        }

    }

    public void addToContent(String num, boolean newRow){



        System.out.println("ContentString:" + num + " ctr = " + counter);
        // System.out.println(num + "  ------------" + newRow);
        if(!newRow) {     // if this is not a new record

            if(counter ==0)
                currentSession.setName(num);
            else if( counter ==1)
                currentSession.setDesiredName(num);

            counter++;

        }
        else { // if this is a new record
            allSessions.add(currentSession);
            currentSession = new SessionDescription();
            counter =0;
            currentSession.setName(num);
            counter++;
        }

    }


    public ArrayList<SessionDescription> getDescriptor()
    {

        for(SessionDescription sess : allSessions){
            System.out.println();
            System.out.print(sess.getName() + "\t");
            System.out.print(sess.getDesiredName() + "\t");

            System.out.print(sess.getShow() + "\t");
            System.out.print(sess.getOrder() + "\t");
            System.out.print(sess.getMutualEx() + "\t");
        }

        return allSessions;

    }
    public void finalize (){

        allSessions.add(currentSession);

    }

    public StudyDescriptionFromExcel(String fileName, int signalType) {
        super(signalType, fileName);
    }

}
