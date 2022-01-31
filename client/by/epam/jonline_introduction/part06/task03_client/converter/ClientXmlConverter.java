package by.epam.jonline_introduction.part06.task03_client.converter;

import java.util.Map;

import by.epam.jonline_introduction.part06.task03_client.bean.Student;

public interface ClientXmlConverter {

	Map<Integer, Student> convertXmlStringToObjectMap(String xmlStr);

	String convertObjectToXmlString(Student student);
}
