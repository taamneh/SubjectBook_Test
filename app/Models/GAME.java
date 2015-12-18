package Models;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by staamneh on 11/21/2014.
 */
public class GAME {

    public static void main(String[] args) throws IOException
    {


        Random randomGenerator = new Random();
        System.out.println((1 << 3) * 1000 + randomGenerator.nextInt(1001)*3);

        /*try {

            List<List<String>> steps_dv = new ArrayList<>();
            List<List<Integer>> time_dv = new ArrayList<>();

            List<List<String>> steps_mv = new ArrayList<>();
            List<List<Integer>> time_mv = new ArrayList<>();


            FileInputStream fis = new FileInputStream(new File("C:\\Users\\staamneh\\Desktop\\CPL-Lab\\Children Study\\Game Part\\Dr. P\\NewDecomposition.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);

            Row row = null;
            Iterator cellIterator = null;
            Cell cell = null;
            // we noly allow four empty spaces

            //row = sheet.getRow(8);
            //cellIterator = row.cellIterator();

          int i = 0;
            while (i<20) {

                System.out.println();
                System.out.println();
                sheet = workbook.getSheetAt(i);
                Iterator rowIterator = sheet.iterator();
                int rowctr=0;
                while (rowIterator.hasNext()) {
                    row = (Row) rowIterator.next();
                    cellIterator = row.cellIterator();
                    System.out.println();
                    List<String> tempStep = new ArrayList<>();
                    List<Integer> tempTime = new ArrayList<>();
                    while (cellIterator.hasNext()) {
                        cell = (Cell) cellIterator.next();

                        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {

                            System.out.println(cell.getNumericCellValue());
                        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            Date dt = cell.getDateCellValue();
                            System.out.print(dt.getHours() * 60 + dt.getMinutes());
                            if(rowctr ==2 || rowctr ==5) {
                                tempTime.add(dt.getHours() * 60 + dt.getMinutes());
                            }

                        } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                            if(rowctr ==0 || rowctr ==3) {
                                tempStep.add(cell.getStringCellValue());
                            }
                            if(rowctr ==2 || rowctr ==5) {
                                tempTime.add(-1);
                            }
                            System.out.print(cell.getStringCellValue());
                        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                            //print(cell.getNumericCellValue + "\t\t")
                            Date dt = cell.getDateCellValue();
                            System.out.print(dt.getHours() * 60 + dt.getMinutes());
                            if(rowctr ==2 || rowctr ==5) {
                                tempTime.add(dt.getHours() * 60 + dt.getMinutes());
                            }

                        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                            //System.out.println(cell.getDateCellValue());
                            Date dt = cell.getDateCellValue();
                            System.out.print(dt.getHours() * 60 + dt.getMinutes());
                            if(rowctr ==2 || rowctr ==5) {
                                tempTime.add(dt.getHours() * 60 + dt.getMinutes());
                            }
                        } else {
                            //print(cell.getNumericCellValue + "\t\t")
                        }
                        System.out.print("\t");

                    }
                    switch (rowctr){
                    case 0:
                        steps_dv.add(i, tempStep);
                        break;
                        case 2:
                            time_dv.add(i, tempTime);

                            break;
                        case 3:
                            steps_mv.add(i,tempStep);
                            break;
                        case 5:
                            time_mv.add(i,tempTime);
                            break;

                    }
                  rowctr++;
                }
                i++;
            }

            int a=0, b=0, g=0, l=0, p=0;
            int lp=0;
            int total =0;
            for(List<String>  t : steps_dv ) {//System.out.println(Arrays.deepToString(t.toArray()));
                total=0;
                a=0; b=0; g=0; l=0; p=0;
                int trial =1;
                List<Integer> time = time_dv.get(lp);
                for(int j= 0; j< t.size(); j++)
                {

                    if (!t.get(j).equals("D") && !t.get(j).equals("E"))
                    {
                        switch (t.get(j)){
                            case "A":
                                a+=time.get(j);
                                break;
                            case "B":
                                b+=time.get(j);
                                break;
                            case "L":
                                l+=time.get(j);
                                break;
                            case "P":
                                p+=time.get(j);
                                break;
                            case "G":
                                g+=time.get(j);
                                break;
                        }

                    }
                    else {
                        String status = (t.get(j).equals("E")?"S":"F");
                        if((lp+1)<10) {
                            System.out.print("S00" + (lp + 1) + "\t" + trial + "\t" + status + "\t");
                        }
                        else
                        {
                            System.out.print("S00" + (lp + 1) + "\t" + trial + "\t" + status + "\t");
                        }
                        System.out.print( b + "\t");
                        System.out.print( g + "\t");
                        System.out.print(l + "\t");
                        System.out.print(a + "\t");
                        System.out.print(p + "\t");
                        System.out.println();
                        total =total +a+g+l+b+p;
                        a=0; b=0; g=0; l=0; p=0;
                        trial++;
                    }

                }
                if(a >0 || b>0 || l>0 || p>0|| g>0)
                {
                    if((lp+1)<10) {
                        System.out.print("S00" + (lp + 1) + "\t" + trial + "\t" + "U" + "\t");
                    }
                    else
                    {
                        System.out.print("S00" + (lp + 1) + "\t" + trial + "\t" + "U" + "\t");
                    }
                    System.out.print( b + "\t");
                    System.out.print( g + "\t");
                    System.out.print(l + "\t");
                    System.out.print(a + "\t");
                    System.out.print(p + "\t");
                    System.out.println();
                    total =total + a+g+l+b+p;
                    a=0; b=0; g=0; l=0; p=0;

                }
                //System.out.println("Total: " + total);
                lp++;
            }



            a=0; b=0; g=0; l=0; p=0;
            lp = 0;

            for(List<String>  t : steps_mv ) {//System.out.println(Arrays.deepToString(t.toArray()));
                a=0; b=0; g=0; l=0; p=0;
               total = 0;
                int trial =1;
                List<Integer> time = time_mv.get(lp);
                for(int j= 0; j< t.size(); j++)
                {

                    if (!t.get(j).equals("D") && !t.get(j).equals("E"))
                    {

                        switch (t.get(j)){
                            case "A":
                                a+=time.get(j);
                                break;
                            case "B":
                                b+=time.get(j);
                                break;
                            case "L":
                                l+=time.get(j);
                                break;
                            case "P":
                                p+=time.get(j);
                                break;
                            case "G":
                                g+=time.get(j);
                                break;
                        }

                    }
                    else {
                        String status = (t.get(j).equals("E")?"S":"F");
                        if((lp+1)<10) {
                            System.out.print("S00" + (lp + 1) + "\t" + trial + "\t" + status + "\t");
                        }
                        else
                        {
                            System.out.print("S00" + (lp + 1) + "\t" + trial + "\t" + status + "\t");
                        }
                        System.out.print( b + "\t");
                        System.out.print( g + "\t");
                        System.out.print(l + "\t");
                        System.out.print(a + "\t");
                        System.out.print(p + "\t");
                        System.out.println();
                        total = total + a+g+l+b+p;
                        a=0; b=0; g=0; l=0; p=0;
                        trial++;
                    }

                }
                if(a >0 || b>0 || l>0 || p>0|| g>0)
                {
                    if((lp+1)<10) {
                        System.out.print("S00" + (lp + 1) + "\t" + trial + "\t" + "U" + "\t");
                    }
                    else
                    {
                        System.out.print("S00" + (lp + 1) + "\t" + trial + "\t" + "U" + "\t");
                    }


                    System.out.print( b + "\t");
                    System.out.print( g + "\t");
                    System.out.print(l + "\t");
                    System.out.print(a + "\t");
                    System.out.print(p + "\t");
                    System.out.println();
                    total = total+ a+g+l+b+p;
                    a=0; b=0; g=0; l=0; p=0;

                }
                //System.out.println("Total: " + total);
                lp++;
            }





        }
        catch (IOException e) {
            e.printStackTrace();
        }
        */
    }
}
