package Models;

/**
 * Created by staamneh on 4/27/2015.
 */
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Iterator;

import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class NewExcelFormat {

    private int first_row;
    private int first_col;

    public JsonFromExcel dataFromExcel;  // polymorphism

    public NewExcelFormat(JsonFromExcel data){
        dataFromExcel = data;
        first_row =9;
        first_col = 2;
    }



    public NewExcelFormat(JsonFromExcel data, int fr, int fc){
        dataFromExcel = data;
        first_row =fr;
        first_col = fc;
    }

    private int ColMatching(String colName) {
        int num =0;
        for(int i =0; i < colName.length() ; i++)
            num += (int)colName.charAt(i) - 64;

        return num;
    }


    public void processOneSheet(String filename) throws Exception {
        OPCPackage pkg = OPCPackage.open(dataFromExcel.getFileName());
        XSSFReader r = new XSSFReader( pkg );
        SharedStringsTable sst = r.getSharedStringsTable();

        XMLReader parser = fetchSheetParser(sst);

        // rId2 found by processing the Workbook
        // Seems to either be rId# or rSheet#
        InputStream sheet2 = r.getSheet("rId2");
        InputSource sheetSource = new InputSource(sheet2);
        parser.parse(sheetSource);

        sheet2.close();
    }

    public void processAllSheets() throws Exception {


        try {
            OPCPackage pkg = OPCPackage.open(dataFromExcel.getFileName());
            XSSFReader r = new XSSFReader(pkg);
            SharedStringsTable sst = r.getSharedStringsTable();

            System.out.println("$$$$$$$$$$$$$$$$$$$$" + dataFromExcel.getFileName());

            XMLReader parser = fetchSheetParser(sst);

            Iterator<InputStream> sheets = r.getSheetsData();
            Boolean isFirst = true;
            while (sheets.hasNext() && isFirst) {
                //System.out.println("Processing new sheet:\n");
                InputStream sheet = sheets.next();
                InputSource sheetSource = new InputSource(sheet);
                parser.parse(sheetSource);
                sheet.close();
                isFirst = false; // read only the first sheet
                // System.out.println(content);
            }

            pkg.close();
        }
        catch(org.apache.poi.openxml4j.exceptions.OpenXML4JException e){
            System.out.println("NULLLLLLLLLLLLLLLLLLLLLLLLLLLLL############################");
        }


    }

    public XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {
        XMLReader parser =
                XMLReaderFactory.createXMLReader(
                        "org.apache.xerces.parsers.SAXParser"
                );
        ContentHandler handler = new SheetHandler(sst);
        parser.setContentHandler(handler);
        return parser;
    }


    /**
     * See org.xml.sax.helpers.DefaultHandler javadocs
     */
    private  class SheetHandler extends DefaultHandler {
        private SharedStringsTable sst;
        private String lastContents;
        private boolean nextIsString;
        private int rowNumber=0;
        private int colNumber;
        private int tempRowNum =-1;
        private boolean isFirstCol;
        // salah ..


        private SheetHandler(SharedStringsTable sst) {
            this.sst = sst;
        }

        public void startElement(String uri, String localName, String name,
                                 Attributes attributes) throws SAXException {

            // c => cell

            if(name.equals("c")) {
                // Print the cell reference

                rowNumber = Integer.parseInt(attributes.getValue("r").replaceAll("[^\\d.]", ""));
                colNumber = ColMatching(attributes.getValue("r").replaceAll("[^a-zA-Z]", ""));

                if(attributes.getValue("r").startsWith("A"))
                    isFirstCol = false;
                else
                    isFirstCol = true;


                // Figure out if the value is an index in the SST
                String cellType = attributes.getValue("t");
                if(cellType != null && cellType.equals("s")) {
                    nextIsString = true;
                } else {
                    nextIsString = false;
                }
            }
            // Clear contents cache
            lastContents = "";
        }


        public void endElement(String uri, String localName, String name)
                throws SAXException {
            // Process the last contents as required.
            // Do now, as characters() may be called more than once




            if(nextIsString) {
                int idx = Integer.parseInt(lastContents);
                lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
                nextIsString = false;
                // if(rowNumber ==9 && idx >0 )
                if(rowNumber == first_row && colNumber >= first_col )  // to avoid the title of the sheet at row 8 and frame at first col
                    addToHeader(lastContents);
            }
            else {

                if (rowNumber >first_row) { // this to avoid the first 8 rows
                    //Double idx = Double.parseDouble(lastContents);

                    if (tempRowNum == -1) { // this only executed once
                        tempRowNum = rowNumber;
                    }
                    // v => contents of a cell
                    // Output after we've seen the string contents
                    if (name.equals("v") && isFirstCol) {
                        //System.out.println(name + " - ");
                        addToContent();
                    }
                }
            }
        }

        private void addToHeader (String str){

            dataFromExcel.addToHeader(str, true);
        }
        private void addToContent (){

            if(tempRowNum == rowNumber) {
                dataFromExcel.addToContent(Double.parseDouble(lastContents), false); // we still in the same line
            }
            else {
                dataFromExcel.addToContent(Double.parseDouble(lastContents), true);
                tempRowNum = rowNumber;  // new line
            }
        }

        public void characters(char[] ch, int start, int length)
                throws SAXException {
            lastContents += new String(ch, start, length);
        }

        public void characters2(char[] ch, int start, int length)
                throws SAXException {
            lastContents += new String(ch, start, length);
        }
    }

    public static void main(String[] args) throws Exception {



    }
}