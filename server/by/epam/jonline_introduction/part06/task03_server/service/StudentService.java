package by.epam.jonline_introduction.part06.task03_server.service;

public interface StudentService {

	String findStudentByLastName(String lastName);

	String addStudentToRepository(String xmlStudentData);

	String editStudentInRepository(String xmlStudentData);

	String removeStudentFromRepository(String id);

	String saveRepository();
}
