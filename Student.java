/**
 * @author Nikita Soifer
**/

import java.util.*;

public class Student 
{
	private long dm_id;
	private ArrayList<Course> dm_courses;
	private ArrayList<Semester> dm_semesters;
	
	public Student(long id)
	{
		this.dm_id = id;
		this.dm_courses = new ArrayList<Course>();
		this.dm_semesters = new ArrayList<Semester>();
	}
	
	public void addCourse(Course c)
	{
		boolean found = false;
		int index = 0;
		
		for (int i = 0; i < dm_courses.size(); i++)
		{
			if (this.dm_courses.get(i).getDm_code().equals(c.getDm_code()) && 
				this.dm_courses.get(i).getDm_id() == c.getDm_id())
			{
				found = true;
				index = i;
				break;
			}
		}
		
		if (found == true)
		{
			this.dm_courses.get(index).addAttempt(new Course(this.dm_courses.get(index)));
			if (this.dm_courses.get(index).getDm_final() > c.getDm_final())
			{
				this.dm_courses.get(index).setDm_final(c.getDm_final());
				this.dm_courses.get(index).setDm_mid(c.getDm_mid());
				this.dm_courses.get(index).setDm_semester(c.getDm_semester());
			}
		}
		else
		{
			this.dm_courses.add(c);
		}
		
		found = false;
		for (int i = 0; i < this.dm_semesters.size(); i++)
		{
			if (c.getDm_semester().getDm_month() == this.dm_semesters.get(i).getDm_month() && 
				c.getDm_semester().getDm_year() == this.dm_semesters.get(i).getDm_year())
			{
				found = true;
			}
		}
		
		if (!found)
		{
			this.dm_semesters.add(c.getDm_semester());
		}
	}
	
	public Semester getMinSemester()
	{
		int min_month = this.dm_semesters.get(0).getDm_month();
		int min_year = this.dm_semesters.get(0).getDm_year();
		int min_index = 0;
		
		for (int i = 1; i < this.dm_semesters.size(); i++)
		{
			if (this.dm_semesters.get(i).getDm_year() < min_year)
			{
				min_month = this.dm_semesters.get(i).getDm_month();
				min_year = this.dm_semesters.get(i).getDm_year();
				min_index = i;
			}
			else if (this.dm_semesters.get(i).getDm_year() == min_year && 
					this.dm_semesters.get(i).getDm_month() < min_month)
			{
				min_month = this.dm_semesters.get(i).getDm_month();
				min_year = this.dm_semesters.get(i).getDm_year();
				min_index = i;
			}
		}
		
		return this.dm_semesters.get(min_index);
	}
	
	public Semester getMaxSemester()
	{
		int max_month = this.dm_semesters.get(0).getDm_month();
		int max_year = this.dm_semesters.get(0).getDm_year();
		int max_index = 0;
		
		for (int i = 1; i < this.dm_semesters.size(); i++)
		{
			if (this.dm_semesters.get(i).getDm_year() > max_year)
			{
				max_month = this.dm_semesters.get(i).getDm_month();
				max_year = this.dm_semesters.get(i).getDm_year();
				max_index = i;
			}
			else if (this.dm_semesters.get(i).getDm_year() == max_year && 
					this.dm_semesters.get(i).getDm_month() > max_month)
			{
				max_month = this.dm_semesters.get(i).getDm_month();
				max_year = this.dm_semesters.get(i).getDm_year();
				max_index = i;
			}
		}
		
		return this.dm_semesters.get(max_index);
	}
	
	public int getNumOfSemesters()
	{
		return this.dm_semesters.size();
	}
	
	public int getTotalNumOfCredits()
	{
		int n = 0;
		
		for (int i = 0; i < dm_courses.size(); i++)
		{
			if (dm_courses.get(i).getDm_credits() > (int)'F')
				continue;
			
			n += dm_courses.get(i).getDm_credits();
		}
		
		return n;
	}
	
	public int getTotalNumOfCredits(Semester s)
	{
		int n = 0;
		
		for (int i = 0; i < dm_courses.size(); i++)
		{
			if (dm_courses.get(i).getDm_credits() > (int)'F' ||
				dm_courses.get(i).getDm_semester().getDm_month() != s.getDm_month() ||
				dm_courses.get(i).getDm_semester().getDm_year() != s.getDm_year())
				continue;
			
			n += dm_courses.get(i).getDm_credits();
		}
		
		return n;
	}
	
	public int getTotalNumOfCreditsByCourseCode(String code)
	{
		int n = 0;
		
		for (int i = 0; i < dm_courses.size(); i++)
		{
			if (dm_courses.get(i).getDm_credits() > (int)'F' || dm_courses.get(i).getDm_code().compareTo(code) == 0)		
				n += dm_courses.get(i).getDm_credits();
		
		}
		return n;
	}
	
	public int getNumOfValidCredits()
	{
		int n = 0;
		
		for (int i = 0; i < dm_courses.size(); i++)
		{
			if (dm_courses.get(i).getDm_final() != 'F' && dm_courses.get(i).getDm_final() != 'W')
			{
				n += dm_courses.get(i).getDm_credits();
			}
		}
		
		return n;
	}
	
	public boolean didGraduate()
	{
		return this.getNumOfValidCredits() >= 128;
	}
	
	public double getCumulativeGPA()
	{
		double grade = 0;
		double credits = 0;
		double gpa;
		
		for (int i = 0; i < dm_courses.size(); i++)
		{	
			if (dm_courses.get(i).getDm_credits() > (int)'F')
				continue;
			
			grade += ('E' - dm_courses.get(i).getDm_final()) * dm_courses.get(i).getDm_credits();
		}
		
		credits = (double)this.getTotalNumOfCredits();
			
		gpa = grade / credits;
		gpa = Math.round(gpa * 100);
		gpa = gpa / 100;
		return gpa;
	}
	
	public String getCumulativeGPABySemester()
	{
		String s = new String();
		
		for (int i = 0; i < dm_semesters.size(); i++)
		{
			s += this.getCumulativeGPABySemester(dm_semesters.get(i)) + " ";
		}
		
		return s;
	}
	
	public double getCumulativeGPABySemester(Semester s)
	{
		double grade = 0;
		double credits = 0;
		double gpa;
		
		for (int i = 0; i < dm_courses.size(); i++)
		{
			if (dm_courses.get(i).getDm_credits() > (int)'F' ||
				dm_courses.get(i).getDm_semester().getDm_month() != s.getDm_month() ||
				dm_courses.get(i).getDm_semester().getDm_year() != s.getDm_year())
				continue;
			
			grade += ('E' - dm_courses.get(i).getDm_final()) * dm_courses.get(i).getDm_credits();
		}
		
		credits = (double)this.getTotalNumOfCredits(s);
			
		gpa = grade / credits;
		gpa = Math.round(gpa * 100);
		gpa = gpa / 100;
		return gpa;
	}
	
	public double getDisciplineGPA()
	{
		double grade = 0;
		double credits = 0;
		double gpa;
		
		for (int i = 0; i < dm_courses.size(); i++)
		{	
			if (dm_courses.get(i).getDm_credits() > (int)'F' || dm_courses.get(i).getDm_code().compareTo("CPSC") != 0)
				continue;
			
			grade += ('E' - dm_courses.get(i).getDm_final()) * dm_courses.get(i).getDm_credits();
		}
		
		credits = (double)this.getTotalNumOfCreditsByCourseCode("CPSC");
			
		gpa = grade / credits;
		gpa = Math.round(gpa * 100);
		gpa = gpa / 100;
		return gpa;
	}
	
	public int getTotalNumOfCreditsRepeated()
	{
		int n = 0;
		for (int i = 0; i < dm_courses.size(); i++)
		{
			if (dm_courses.get(i).get_times_taken() > 1)
			{
				n += dm_courses.get(i).getDm_credits();
			}
		}
		
		return n;
	}
	
	public String getSemestersTakenString(int c_id, String c_code)
	{
		String s = "";
		
		ArrayList<Semester> a = this.getSemestersTaken(c_id, c_code); 
		for (int i = 0; i < dm_courses.size(); i++)
		{
			s += "Year: " + a.get(i).getDm_year() + "\n" +
				"Month: " + a.get(i).getDm_month() + "\n\n";
		}
		
		return s;
	}
	
	public ArrayList<Semester> getSemestersTaken(int c_id, String c_code)
	{
		boolean found = false;
		int index = 0;
		ArrayList<Semester> tmp = new ArrayList<Semester>();
		
		for (int i = 0; i < dm_courses.size(); i++)
		{
			if (dm_courses.get(i).getDm_code().equals(c_code) && 
				dm_courses.get(i).getDm_id() == c_id)
			{
				found = true;
				index = i;
			}
		}
		
		if (found)
		{
			for (int i = 0; i < dm_courses.get(index).getAttempts().size(); i++)
			{
				tmp.add(dm_courses.get(index).getAttempts().get(i).getDm_semester());
			}
			
			return tmp;
		}
		
		return null;
	}
	
	public char getFinalGrade(int c_id, String c_code)
	{
		boolean found = false;
		int index = 0;
		
		for (int i = 0; i < dm_courses.size(); i++)
		{
			if (dm_courses.get(i).getDm_code().equals(c_code) && 
				dm_courses.get(i).getDm_id() == c_id)
			{
				found = true;
				index = i;
			}
		}
		
		if (found)
		{
			return dm_courses.get(index).getDm_final();
		}
		
		return '0';
	}
	
	public String getFinalGradesGraph()
	{
		String[] graph = new String[6];
		
		graph[0] = "A: ";
		graph[1] = "B: ";
		graph[2] = "C: ";
		graph[3] = "D: ";
		graph[4] = "F: ";
		graph[5] = "W: ";
		
		for (int i = 0; i < dm_courses.size(); i++)
		{
			if (dm_courses.get(i).getDm_final() == 'W')
			{
				graph[5] += "*"; 
			}
			else if (dm_courses.get(i).getDm_final() == 'F')
			{
				graph[4] += "*";
			}
			else
			{
				graph[dm_courses.get(i).getDm_final() - 'A'] += "*";
			}
		}
		
		graph[0] += "\n";
		graph[1] += "\n";
		graph[2] += "\n";
		graph[3] += "\n";
		graph[4] += "\n";
		graph[5] += "\n";
		
		return (new String(graph[0] + graph[1] + graph[2] + graph[3] + graph[4] + graph[5]));
	}
}