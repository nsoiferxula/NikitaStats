/**
 * @author Nikita Soifer
**/

import java.util.*;

public class IndividualStatistics 
{
	
	public static void main(String[] args) 
	{				
        Student s;
        do
        {
        	long l = response();
        	if (l != 0)
        	{
        		s = StatisticsResource.getStudent(l, "data.xls");
        		err(s);
        	}
        	else
        	{
        		s = null;
        	}
        	
        }while (s == null);
        
        // first and last semester, duration in school, graduated or not
        System.out.println(s.getMinSemester().getDm_month() + " " + s.getMinSemester().getDm_year() + "\t" + s.getMaxSemester().getDm_month() + 
        		" " + s.getMaxSemester().getDm_year() + "\t" + s.getNumOfSemesters() + " " + s.didGraduate());
     
        //Cumulative, discipline GPA, GPA per semester
        System.out.print(s.getCumulativeGPA() + "    " + s.getDisciplineGPA());
        System.out.println("\t\t" + s.getCumulativeGPABySemester());
        
        // Hours taken, repeated
        System.out.print(s.getTotalNumOfCredits());     
        System.out.println("\t" + s.getTotalNumOfCreditsRepeated());
        
        // Grades' histogram
        System.out.println(s.getFinalGradesGraph());
        
        // Information on a class
        System.out.println(s.getSemestersTakenString(1010, "ECON"));
	}

	public static long response ()
	{
		long l;
		Scanner student = new Scanner(System.in);
        System.out.println("Enter student's ID: ");
        try
        {
        	l = student.nextInt();
        }
        catch (Exception e)
        {
        	System.out.println("You must enter a number!");
        	l = 0;
        }
        
		return l;
	}
	
	public static void err (Student s)
    {
        if (s == null)
        {
        	System.out.println("Student was not found");      	
        }     
    }
	
}
