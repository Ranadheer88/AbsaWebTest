package utils.data;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.ExtentReports.User;

public class DataUtils {
    private static XSSFSheet ExcelWSheet;

    private static XSSFWorkbook ExcelWBook;

    private static XSSFCell Cell;

    private static XSSFRow Row;




    public static ArrayList<User> getTableArray() throws Exception {

        String FilePath = System.getProperty("user.dir")+"/Testdata.xlsx";

        String SheetName = "Sheet1";

        ArrayList<User> userList = null;

        User user = null;



        try {

            FileInputStream ExcelFile = new FileInputStream(FilePath);

            // Access the required test data sheet

            ExcelWBook = new XSSFWorkbook(ExcelFile);

            ExcelWSheet = ExcelWBook.getSheet(SheetName);

            int startRow = 1;

            int startCol = 0;

            int ci, cj;

            int totalRows = ExcelWSheet.getLastRowNum();

            // you can write a function as well to get Column count

            int totalCols = 6;

            userList = new ArrayList<User>();

            ci = 0;

            for (int i = startRow; i <= totalRows; i++, ci++) {
                cj = 0;
                user = new User();
                for (int j = startCol; j <= totalCols; j++, cj++) {
                    switch (j){
                        case 0:
                            user.setfName(getCellData(i, j));
                            break;
                        case 1:
                            user.setLName(getCellData(i, j));
                            break;
                        case 2:
                            user.setPassword(getCellData(i, j));
                            break;
                        case 3:
                            user.setCustomer(getCellData(i, j));
                            break;
                        case 4:
                            user.setRole(getCellData(i, j));
                            break;
                        case 5:
                            user.setEmail(getCellData(i, j));
                            break;
                        case 6:
                            user.setCell(getCellData(i, j));
                            break;
                    }
                }
                userList.add(user);

            }

        } catch (FileNotFoundException e) {

            System.out.println("Could not read the Excel sheet");

            e.printStackTrace();

        } catch (IOException e) {

            System.out.println("Could not read the Excel sheet");

            e.printStackTrace();

        }

        return userList;

    }

    public static String getCellData(int RowNum, int ColNum) throws Exception {

        try {

            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

            String CellData = "" ;

                switch (Cell.getCellType()){
                    case NUMERIC:
                        CellData = String.format("%.0f", Cell.getNumericCellValue());
                        break;
                    case STRING:
                        CellData = Cell.getStringCellValue();
                        break;
                }

            return CellData;



        } catch (Exception e) {

            System.out.println(e.getMessage());

            throw (e);

        }

    }

}