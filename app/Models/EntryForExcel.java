package Models;

/**
 * Created by staamneh on 8/29/2016.
 */
public class EntryForExcel {
    private double time;
    private String sessionName;
    private String stimulus;
    private String failure;
    private double persperation;
    private double peda;
    private double hr;
    private double br;
    private double hrv;
    private double speed;
    private double accelerator;
    private double braking;
    private double steering;
    private double laneOffset;
    private double lanePosition;


    public EntryForExcel(double time, String name, String stm, String fail, double pers){
        this.time = time;
        sessionName = name;
        stimulus = stm;
        failure = fail;
        persperation = pers;
        peda = 0;
        hr = 0;
        br = 0 ;
        hrv = 0;


    }

    public EntryForExcel()
    {

    }

    public EntryForExcel(double time, String name, String stm, String fail, double pers, double peda, double br, double hr, double hrv){
        this.time = time;
        sessionName = name;
        stimulus = stm;
        failure = fail;
        persperation = pers;
        this.hr = hr;
        this.br = br;
        this.hrv = hrv;
        this.peda = peda;

    }

    public EntryForExcel(double time, String name, String stm, String fail, double pers, double peda, double br, double hr, double hrv, double sp , double acc , double bk, double ster, double lane, double position){
        this.time = time;
        sessionName = name;
        stimulus = stm;
        failure = fail;
        persperation = pers;
        this.hr = hr;
        this.br = br;
        this.hrv = hrv;
        this.peda = peda;
        this.speed = sp;
        this.accelerator = acc;
        this.braking = bk;
        this.steering = ster;
        this.laneOffset =lane;
        this.lanePosition = position;



    }


    public double getTime(){
        return time;
    }
    public String getName()
    {
        return sessionName;
    }
    public String getStimulus(){
        return stimulus;
    }
    public String getFailure(){
     return failure;
    }

    public double getPersperation(){
        return persperation;
    }
    public double getPeda(){
        return peda;
    }
    public double getHr(){
        return hr;
    }
    public double getHrv(){
        return hrv;
    }
    public double getbr(){
        return br;
    }


    public double getSpeed(){
        return speed;
    }
    public double getAcc(){
        return accelerator;
    }
    public double getBrake(){
        return braking;
    }
    public double getSteering(){
        return steering;
    }
    public double getLaneOffset(){
        return laneOffset;
    }
    public double getLanePosition() {return this.lanePosition;}
}
