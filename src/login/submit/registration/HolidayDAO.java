package login.submit.registration;

import java.util.ArrayList;

public interface HolidayDAO {
	public Holiday getHoliday(String holidayName, int year) ;
	public int insertHoliday(Holiday h);
	public ArrayList<Holiday> getAllHoliday(int year);
}
