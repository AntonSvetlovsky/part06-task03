package by.epam.jonline_introduction.part06.task03_server.converter;

import java.util.List;

import by.epam.jonline_introduction.part06.task03_server.bean.Student;
import by.epam.jonline_introduction.part06.task03_server.bean.StudentRepository;
import by.epam.jonline_introduction.part06.task03_server.bean.UserRepository;

public interface ServerXmlConverter {

	String convertObjectListToXmlString(List<Student> studentList);

	Student convertXmlStringToObject(String xmlStr);

	StudentRepository convertXmlFileToStudentRepository(String filePath);

	void convertStudentRepositoryToXmlFile(StudentRepository repository, String filePath);

	UserRepository convertXmlFileToUserRepository(String filePath);

	void convertUserRepositoryToXmlFile(UserRepository repository, String filePath);

}
