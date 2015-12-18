package Models;

import java.io.InputStream;

/**
 * Created by staamneh on 2/19/2015.
 */

public class ZipFileEntry {
    public String fileName;
    public String sessionName;
    public String subjectName;
    public InputStream data;

    public ZipFileEntry()
    {
        fileName="";
        sessionName ="";
        subjectName="";
        data= null;
    }
    public ZipFileEntry(InputStream is, String fileName, String sessionName, String subjectName)
    {
        this.fileName= fileName;
        this.sessionName =sessionName;
        this.subjectName=subjectName;
        data= is;
    }
}