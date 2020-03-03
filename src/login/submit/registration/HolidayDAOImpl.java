package login.submit.registration;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

public class HolidayDAOImpl implements HolidayDAO {

	static Connection con;
	static PreparedStatement ps; 	
	
	@Override
	public int insertHoliday(Holiday h) {
		int status =0;
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("insert into holiday value(?,?,?,?)");
			ps.setInt(1, h.getYear());
			ps.setString(2, h.getHolidayName());
			ps.setDate(3, (Date) h.getStart());
			ps.setDate(4, (Date) h.getEnd());
			status=ps.executeUpdate();
			System.out.println("insertHoliday(): status: "+ status);
			con.close();
		}catch(Exception e) {
			System.out.println("Insert fail: " + e);
		}
		return status;
	}

	@Override
	public Holiday getHoliday(String holidayName, int year) {
		System.out.println("getholiday(): holidayName: "+ holidayName);
		System.out.println("getholiday(): year: "+ year);
		
		Holiday h = new Holiday();
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from holiday where holidayName=? and year=?");
			ps.setString(1, holidayName);
			ps.setInt(2, year);

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				h.setHolidayName(rs.getString(2));
				h.setYear(rs.getInt(1));
				h.setStart(rs.getDate(3));
				h.setEnd(rs.getDate(4));
			}
	
		}catch(Exception e) {
			System.out.println("select  fail: " + e);
		}
		System.out.println("getholiday(): returnl");
		System.out.println("getholiday(): h: "+ h);
		System.out.println("getholiday(): h.getholidayName(): "+ h.getHolidayName());
		return h;
	}
	
	public ArrayList<Holiday> getAllHoliday(int year) {		
		
		ArrayList<Holiday> HolidayList = new ArrayList<>();
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from holiday where year=?");
			ps.setInt(1, year);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Holiday h = new Holiday();
				h.setYear(rs.getInt(1));
				h.setHolidayName(rs.getString(2));
				h.setStart(rs.getDate(3));
				h.setEnd(rs.getDate(4));
				HolidayList.add(h);
			}
			
		}catch(Exception e) {
			System.out.println("select  fail: " + e);
		}
		System.out.println("getholiday(): HolidayList.size(): "+ HolidayList.size());
		return HolidayList;
	}
	
    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.trim().isEmpty())
            return false;
        return true;
    }
}
