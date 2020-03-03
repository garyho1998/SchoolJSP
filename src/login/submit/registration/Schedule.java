package login.submit.registration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Schedule {
	private ArrayList<Calendar> lectureList = new ArrayList<>();
	
	public Schedule(Lecture l, ArrayList<Holiday> holidayList){
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		start.setTime(l.getStart());
		end.setTime(l.getEnd());
		//System.out.println("start day: "+start.get(Calendar.DAY_OF_WEEK));
		Calendar tempC= start;
		do {
			int DayofWeek=tempC.get(Calendar.DAY_OF_WEEK)-1;
			//System.out.println("Schedule(): LectureTime: "+ l.getLectureStartTime(DayofWeek));
			//System.out.println("Schedule(): tempC.getTime(): "+ tempC.getTime());
			if(l.getLectureStartTime(DayofWeek)!=null && !isHoliday(tempC,holidayList)){
				
				Calendar tempC_Copy = (Calendar) tempC.clone();
				Calendar timeCal = Calendar.getInstance();
				timeCal.setTime(l.getLectureStartTime(DayofWeek));
				tempC_Copy.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
				tempC_Copy.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
				tempC_Copy.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));
				lectureList.add(tempC_Copy);
				//System.out.println("Schedule(): tempC.getTime(): "+ tempC.getTime());
			}
			tempC.add(Calendar.DATE,1);
		}while(tempC.compareTo(end)<=0);

	}
	
	public String toString() {
		String result = "";
		for(Calendar c: lectureList) {
			SimpleDateFormat format1 = new SimpleDateFormat("EEE yyyy-MM-dd HH:mm");
			String formatted = format1.format(c.getTime());
			result += formatted + ";";
		}
		return result;
	}
	
	public String toCalendarString(Calendar c) {
		SimpleDateFormat format1 = new SimpleDateFormat("EEE yyyy-MM-dd HH:mm");
		String formatted = format1.format(c.getTime());
		return formatted;
	}
	
	public ArrayList<Calendar> getList() {
		return lectureList;
	}
	public boolean isHoliday(Calendar c, ArrayList<Holiday> holidayList) {
		for(Holiday h : holidayList) {
			Calendar hStart = Calendar.getInstance();
			hStart.setTime(h.getStart());
			Calendar hEnd = Calendar.getInstance();
			hEnd.setTime(h.getEnd());
			if(c.compareTo(hStart)<0) {
				//System.out.println("hStart.getTime() "+ hStart.getTime());
				return false;
			}else {
				if(c.compareTo(hEnd)<0) {
					//System.out.println("h.getHolidayName(): "+ h.getHolidayName());
					return true;
				}else {
					//System.out.println("hStart.getTime() "+ hStart.getTime());
					return false;
				}
			}
		}
		return false;
		
	}
	
}
