package by.epam.jonline_introduction.part06.task03_client.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;

import by.epam.jonline_introduction.part06.task03_client.bean.Request;
import by.epam.jonline_introduction.part06.task03_client.bean.Response;
import by.epam.jonline_introduction.part06.task03_client.bean.Student;
import by.epam.jonline_introduction.part06.task03_client.converter.ClientXmlConverter;
import by.epam.jonline_introduction.part06.task03_client.converter.ConverterProvider;
import by.epam.jonline_introduction.part06.task03_client.dao.DAOProvider;
import by.epam.jonline_introduction.part06.task03_client.dao.StudentDAO;
import by.epam.jonline_introduction.part06.task03_client.service.StudentService;

public class StudentServiceImpl implements StudentService {

	private final DAOProvider daoProvider = DAOProvider.getInstance();
	private final ConverterProvider converterProvider = ConverterProvider.getInstance();
	private final StudentDAO studentDAO = daoProvider.getStudentDAO();
	private final ClientXmlConverter xmlConverter = converterProvider.getConverter();

	@Override
	public String findStudentByLastName(String lastName) {

		String response = Response.INVALID_INPUT.toString();
		Map<Integer, Student> requestedStudentMap = null;
		String xmlStr = null;
		String requestType = Request.SHOW_STUDENT.toString();
		if (lastName == null) {
			return response;
		}
		String serverResponse = studentDAO
				.connectToServer(requestType + "|" + studentDAO.getAccessCode() + "|" + lastName);
		String[] serverResponseArray = serverResponse.split("\\|", 2);
		response = serverResponseArray[0].trim();

		if (response.equalsIgnoreCase(Response.SUCCESSFUL.toString())) {
			xmlStr = serverResponseArray[1].trim();
			studentDAO.loadStudentMap(xmlStr);
			requestedStudentMap = studentDAO.getStudentMap();
			for (Student student : requestedStudentMap.values()) {
				response += "\n" + student.getId() + "|" + student.getFirstName() + "|" + student.getLastName() + "|"
						+ student.getDateOfBirth() + "|" + student.getDescription();
			}
		}

		return response;
	}

	@Override
	public String addStudentToRepository(String firstName, String lastName, String dateOfBirth, String description) {
		String response = Response.INVALID_INPUT.toString();
		String requestType = Request.ADD_STUDENT.toString();
		Student student = null;
		LocalDate date = null;
		if (firstName == null || lastName == null || dateOfBirth == null || description == null) {
			return response;
		}
		try {
			date = LocalDate.parse(dateOfBirth);
		} catch (DateTimeParseException e) {
			return response;
		}

		student = new Student(firstName, lastName, date, description);

		response = studentDAO.connectToServer(
				requestType + "|" + studentDAO.getAccessCode() + "|" + xmlConverter.convertObjectToXmlString(student));

		return response;
	}

	@Override
	public String editStudentInRepository(String id, String firstName, String lastName, String dateOfBirth,
			String description) {
		String response = Response.INVALID_INPUT.toString();
		String requestType = Request.EDIT_STUDENT.toString();
		Student student = null;
		LocalDate date = null;
		Integer key = null;
		if (id == null || !id.matches("\\d+")) {
			return response;
		}
		key = Integer.parseInt(id);
		if (studentDAO.getStudentMap() == null || !studentDAO.getStudentMap().containsKey(key)) {
			response = Response.NO_MATCHES.toString();
			return response;
		}
		if (firstName == null || lastName == null || dateOfBirth == null || description == null) {
			return response;
		}
		try {
			date = LocalDate.parse(dateOfBirth);
		} catch (DateTimeParseException e) {
			return response;
		}

		student = studentDAO.getStudentMap().get(key);
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setDateOfBirth(date);
		student.setDescription(description);

		response = studentDAO.connectToServer(
				requestType + "|" + studentDAO.getAccessCode() + "|" + xmlConverter.convertObjectToXmlString(student));

		return response;
	}

	@Override
	public String removeStudentFromRepository(String id) {
		String response = Response.INVALID_INPUT.toString();
		;
		String requestType = Request.REMOVE_STUDENT.toString();
		Integer key = null;
		if (id == null || !id.matches("\\d+")) {
			return response;
		}
		key = Integer.parseInt(id);
		if (studentDAO.getStudentMap() == null || !studentDAO.getStudentMap().containsKey(key)) {
			response = Response.NO_MATCHES.toString();
			return response;
		}

		response = studentDAO.connectToServer(requestType + "|" + studentDAO.getAccessCode() + "|" + id);

		return response;
	}

	@Override
	public String stopServer() {
		String response = null;

		String requestType = Request.STOP_SERVER.toString();
		response = studentDAO.connectToServer(requestType + "|" + studentDAO.getAccessCode());

		return response;
	}

}
