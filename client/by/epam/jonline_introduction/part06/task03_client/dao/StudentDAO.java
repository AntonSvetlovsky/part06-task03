package by.epam.jonline_introduction.part06.task03_client.dao;

import java.util.Map;

import by.epam.jonline_introduction.part06.task03_client.bean.Student;

public interface StudentDAO {

	void loadStudentMap(String strXml);

	Map<Integer, Student> getStudentMap();

	String connectToServer(String request);

	String getAccessCode();

	void setAccessCode(String accessCode);

}
