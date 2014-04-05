/**
 * @author Nikita Soifer
**/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class StatisticsResource 
{
	public static Student getStudent(long student_id, String path)
	{
		Student ret_s = null;
		try 
		{     
		    FileInputStream file = new FileInputStream(new File(path));
		     
		    //Get the workbook instance for XLS file 
		    HSSFWorkbook workbook = new HSSFWorkbook(file);
		 
		    //Get first sheet from the workbook
		    HSSFSheet sheet = workbook.getSheetAt(0);
		     
		    //Iterate through each rows from first sheet
		    Iterator<Row> rowIterator = sheet.iterator();
		    rowIterator.next();
		    while(rowIterator.hasNext()) 
		    {
		        Row row = rowIterator.next();		        		 
		        
		        if ((long)row.getCell(0).getNumericCellValue() == student_id)
		        {		             	       
			        if (ret_s == null)
			        {
			        	ret_s = new Student(student_id);
			        }
			        
			        ret_s.addCourse(new Course(	(int)row.getCell(3).getNumericCellValue(), row.getCell(2).getStringCellValue(), (int)row.getCell(4).getNumericCellValue(),
			        		row.getCell(5).getStringCellValue().charAt(0), row.getCell(6).getStringCellValue().charAt(0), (int)row.getCell(1).getNumericCellValue()));	        		       		    
		        }
		    }
		    file.close();
		} 
		catch (FileNotFoundException e) 
		{
		    e.printStackTrace();
		}
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
		
		return ret_s;
	}
}
