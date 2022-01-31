package by.epam.jonline_introduction.part06.task03_client.service;

public interface StudentService {

	String findStudentByLastName(String lastName);

	String addStudentToRepository(String firstName, String lastName, String dateOfBirth, String description);

	String editStudentInRepository(String id, String firstName, String lastName, String dateOfBirth,
			String description);

	String removeStudentFromRepository(String id);

	String stopServer();
}
