package controllers;

/**
 * Created by staamneh on 9/17/2015.
 */
public class SharedData {

    public final static int GOOGLE_DRIVE = 1;
    public final static int LOCALSERVER = 2;
    public static class SessionsBar{
        public String name;
        public String location;

        public SessionsBar(String a, String b)
        {
            name = a;
            location = b;
        }
    }


    public static class  MinMax
    {
         public int min;
         public int max;

        public MinMax(int x, int y)
        {
            min=x;
            max =y;
        }

    }


}
