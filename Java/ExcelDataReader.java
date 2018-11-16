package regression;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelDataReader {

    private String inputFile;

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }
    
    public double[][] setFeatureArray(double[][] x) throws IOException
    {
    	//Set workbook object to Microsoft Excel File (.xls format)
    	File workbookIn = new File(inputFile);
    	Workbook w;
    	
    	try 
    	{
			w = Workbook.getWorkbook(workbookIn);
            Sheet s = w.getSheet(0);
            
            //Set elements of input 2d array to corresponding workbook cells
            for (int col = 0; col < x[0].length; col++) 
            {
                for (int row = 0; row < x.length; row++) 
                {
                    Cell cell = s.getCell(row, col);
                    x[row][col] = Integer.parseInt(cell.getContents());
                }
            }
            
            
    	}
    	catch(BiffException e) 
    	{
    		e.printStackTrace();
    	}
    	
    	return x;
    }
    
    public void printFeatureArray(double[][] x)
    {
    	for (int row = 0; row < x.length; row++) 
    	{
            for (int col = 0; col < x[0].length; col++) 
            {
            	if(x[row][col] < 0)
            	{
            		System.out.printf("%.5f\t\t", x[row][col]);
            	}
            	else
            	{
            		System.out.printf(" %.5f\t\t", x[row][col]);
            	}
            }
            System.out.println();
        }
    	System.out.println();
    }
    
    public void printVector(double[] v)
    {
    	for(int i = 0; i < v.length; i++)
    	{
    		System.out.printf("%f\t", v[i]);
    	}
    }
}