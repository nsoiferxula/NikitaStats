import java.util.ArrayList;


public class Course 
{
	private int dm_id;
	private String dm_code;
	private int dm_credits;
	private char dm_mid;
	private char dm_final;
	private Semester dm_semester;
	private ArrayList<Course> dm_attempts;
	
	public Course(int id, String code, int credits, char mid, char final_res, int start_date)
	{
		this.dm_id = id;
		this.dm_code = code;
		this.dm_credits = credits;
		this.dm_mid = mid;
		this.dm_final = final_res;
		this.dm_semester = new Semester(start_date);
		this.dm_attempts = new ArrayList<Course>();
		this.dm_attempts.add(this);
	}
	
	public Course(Course c)
	{
		this.dm_id = c.dm_id;
		this.dm_code = c.dm_code;
		this.dm_credits = c.dm_credits;
		this.dm_mid = c.dm_mid;
		this.dm_final = c.dm_final;
		this.dm_semester = new Semester(c.dm_semester);
		this.dm_attempts = new ArrayList<Course>(c.dm_attempts);
	}

	public int getDm_id() {
		return dm_id;
	}
	
	public ArrayList<Course> getAttempts() {
		return dm_attempts;
	}
	
	public void addAttempt(Course c) {
		c.dm_attempts.clear();
		for (int i = 0; i < dm_attempts.size(); i++)
		{
			if (c.getDm_semester().isEarlier(this.dm_attempts.get(i).getDm_semester()))
			{
				this.dm_attempts.add(i, c);
			}
		}
	}
	
	public int get_times_taken() {
		return this.dm_attempts.size();
	}

	public void setDm_id(int dm_id) {
		this.dm_id = dm_id;
	}

	public String getDm_code() {
		return dm_code;
	}
	
	public void setDm_code(String dm_code) {
		this.dm_code = dm_code;
	}

	public int getDm_credits() {
		return dm_credits;
	}

	public void setDm_credits(int dm_credits) {
		this.dm_credits = dm_credits;
	}

	public char getDm_mid() {
		return dm_mid;
	}

	public void setDm_mid(char dm_mid) {
		this.dm_mid = dm_mid;
	}

	public char getDm_final() {
		return dm_final;
	}

	public void setDm_final(char dm_final) {
		this.dm_final = dm_final;
	}

	public Semester getDm_semester() {
		return dm_semester;
	}

	public void setDm_semester(Semester dm_semester) {
		this.dm_semester = dm_semester;
	}
}