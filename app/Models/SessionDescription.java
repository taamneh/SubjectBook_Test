package Models;

/**
 * Created by staamneh on 3/19/2016.
 */
public class SessionDescription {
    private String name;
    private String desiredName;
    private boolean hide;
    private int order;
    private boolean mutualEx;

    public void setName(String n){
        name = n;
    }
    public String getName(){
        return name;
    }
    public void setDesiredName (String d){
        desiredName = d;
    }

    public String getDesiredName (){
        return desiredName;
    }
    public void setHide (boolean h){
        hide = h;
    }
    public boolean getShow (){
        return hide;
    }
    public void setOrder (int o){
        order = o;
    }
    public int getOrder (){
        return order;
    }
    public void setMutualEx(boolean m){
        mutualEx = m;
    }
    public boolean getMutualEx(){
        return mutualEx;
    }


}

