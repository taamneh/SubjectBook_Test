package Models;

import org.apache.poi.hssf.eventusermodel.HSSFEventFactory;
import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.hssf.record.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by staamneh on 4/27/2015.
 */


public class OldExcelFormat
{
    private SSTRecord sstrec;
    public JsonFromExcel dataFromExcel;  // polymorphism

    public OldExcelFormat(JsonFromExcel data)
    {
        dataFromExcel = data;
    }


    private  class SheetHandler implements HSSFListener {

        private int rowNumber=0;
        private int tempRowNum =-1;
        private int numberOfSheet=0;


        /**
         * This method listens for incoming records and handles them as required.
         *
         * @param record The record that was found while reading.
         */
        public void processRecord(Record record) {
            switch (record.getSid()) {
                // the BOFRecord can represent either the beginning of a sheet or the workbook
                case BOFRecord.sid:
                    BOFRecord bof = (BOFRecord) record;
                    if (bof.getType() == bof.TYPE_WORKBOOK) {
                       // System.out.println("Encountered workbook");
                        // assigned to the class level member
                    } else if (bof.getType() == bof.TYPE_WORKSHEET) {
                       // System.out.println("Encountered sheet reference");
                        numberOfSheet++;
                    }
                    break;
                case BoundSheetRecord.sid:
                   // System.out.println(SSTRecord.sid);
                        BoundSheetRecord bsr = (BoundSheetRecord) record;
                       // System.out.println("New sheet named: " + bsr.getSheetname());

                    break;
                case RowRecord.sid:
                    RowRecord rowrec = (RowRecord) record;
               /* System.out.println("Row found, first column at "
                        + rowrec.getFirstCol() + " last column at " + rowrec.getLastCol());*/
                   // System.out.println(rowNumber);
                    break;
                case NumberRecord.sid:
                    NumberRecord numrec = (NumberRecord) record;
                    rowNumber = numrec.getRow();
                    if(numrec.getColumn() >0 && numberOfSheet <=1)
                        addToContent(numrec.getValue());
               /* System.out.println("Cell found with value " + numrec.getValue()
                        + " at row " + numrec.getRow() + " and column " + numrec.getColumn());*/
                    break;
                // SSTRecords store a array of unique strings used in Excel.
                case SSTRecord.sid:
                    sstrec = (SSTRecord) record;
                    /*for (int k = 0; k < sstrec.getNumUniqueStrings(); k++) {

                        System.out.println("String table value " + k + " = " + sstrec.getString(k));
                    }*/
                    break;
                case LabelSSTRecord.sid:

                    LabelSSTRecord lrec = (LabelSSTRecord) record;
                    /*System.out.println("String cell found with value "
                            + sstrec.getString(lrec.getSSTIndex()) + "   " + lrec.getRow());*/
                    if (lrec.getRow() == 8  && lrec.getColumn()>0 && numberOfSheet <=1)
                        addToHeader(sstrec.getString(lrec.getSSTIndex()).toString());
                    tempRowNum = rowNumber;
                    break;
            }
        }


        private void addToHeader(String str) {
            dataFromExcel.addToHeader(str, true);
        }

        private void addToContent(double val) {

            if (tempRowNum == rowNumber) {
                dataFromExcel.addToContent(val, false); // we still in the same line
            } else {
                dataFromExcel.addToContent(val, true);
                tempRowNum = rowNumber;  // new line
            }
        }
    }


    public void readSheet() throws IOException
    {
        // create a new file input stream with the input file specified
        // at the command line
        FileInputStream fin = new FileInputStream(dataFromExcel.getFileName());
        // create a new org.apache.poi.poifs.filesystem.Filesystem
        POIFSFileSystem poifs = new POIFSFileSystem(fin);
        // get the Workbook (excel part) stream in a InputStream
        InputStream din = poifs.createDocumentInputStream("Workbook");
        // construct out HSSFRequest object
        HSSFRequest req = new HSSFRequest();
        // lazy listen for ALL records with the listener shown above
        req.addListenerForAllRecords(new SheetHandler());

        // create our event factory
        HSSFEventFactory factory = new HSSFEventFactory();
        // process our events based on the document input stream
        factory.processEvents(req, din);
        // once all the events are processed close our file input stream
        fin.close();
        // and our document input stream (don't want to leak these!)
        din.close();
        //System.out.println("done.");
    }
}
