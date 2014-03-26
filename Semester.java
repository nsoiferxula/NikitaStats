
public class Semester 
{
	private int dm_month;
	private int dm_year;
	
	public Semester(int start_date)
	{
		this.dm_year = start_date / 100;
		this.dm_month = start_date % 100;
	}
	
	public Semester(Semester s)
	{
		this.dm_year = s.dm_year;
		this.dm_month = s.dm_month;
	}

	public int getDm_month() {
		return dm_month;
	}

	public void setDm_month(int dm_month) {
		this.dm_month = dm_month;
	}

	public int getDm_year() {
		return dm_year;
	}

	public void setDm_year(int dm_year) {
		this.dm_year = dm_year;
	}
	
	public boolean isEarlier(Semester s) {
		return (this.dm_year < s.dm_year || 
				this.dm_year == s.dm_year && this.dm_month < s.dm_month);
	}
}
