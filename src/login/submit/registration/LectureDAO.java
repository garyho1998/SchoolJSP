package login.submit.registration;

import java.util.ArrayList;

public interface LectureDAO {
	public Lecture getLecture(String lectureName, int year) ;
	public int insertLecture(Lecture s);
	public int updateLecture(Lecture l, String string, String string2, String s);
	public ArrayList<Lecture> getAllLecture(int year2);
}
