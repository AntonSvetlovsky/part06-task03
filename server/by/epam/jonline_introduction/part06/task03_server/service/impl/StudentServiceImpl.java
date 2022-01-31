package by.epam.jonline_introduction.part06.task03_server.service.impl;

import java.util.ArrayList;
import java.util.List;
import by.epam.jonline_introduction.part06.task03_server.bean.Response;
import by.epam.jonline_introduction.part06.task03_server.bean.Student;
import by.epam.jonline_introduction.part06.task03_server.bean.StudentRepository;
import by.epam.jonline_introduction.part06.task03_server.converter.ConverterProvider;
import by.epam.jonline_introduction.part06.task03_server.converter.ServerXmlConverter;
import by.epam.jonline_introduction.part06.task03_server.dao.DAOProvider;
import by.epam.jonline_introduction.part06.task03_server.dao.StudentDAO;
import by.epam.jonline_introduction.part06.task03_server.service.StudentService;

public class StudentServiceImpl implements StudentService {

	private final DAOProvider daoProvider = DAOProvider.getInstance();
	private final ConverterProvider converterProvider = ConverterProvider.getInstance();
	private final StudentDAO studentDAO = daoProvider.getStudentDAO();
	private final ServerXmlConverter xmlConverter = converterProvider.getConverter();

	@Override
	public String findStudentByLastName(String lastName) {
		String response = Response.NO_MATCHES.toString();
		if (lastName == null) {
			response = Response.INVALID_INPUT.toString();
			return response;
		}
		List<Student> requestedList = new ArrayList<Student>();
		StudentRepository studentRepository = studentDAO.loadStudentRepository();
		List<Student> studentList = new ArrayList<Student>(studentRepository.getStudentMap().values());

		for (Student student : studentList) {
			if (student.getLastName().equalsIgnoreCase(lastName)) {
				requestedList.add(student);
			}
		}
		if (requestedList.size() != 0) {

			response = Response.SUCCESSFUL + "|" + xmlConverter.convertObjectListToXmlString(requestedList);
		}
		return response;
	}

	@Override
	public String addStudentToRepository(String xmlStudentData) {
		String response = Response.INVALID_INPUT.toString();
		if (xmlStudentData == null) {
			return response;
		}
		Student student = null;

		student = xmlConverter.convertXmlStringToObject(xmlStudentData);

		if (student.getFirstName() != null && student.getLastName() != null && student.getDateOfBirth() != null) {
			studentDAO.loadStudentRepository().addStudent(student);
			studentDAO.saveStudentRepository();
			response = Response.SUCCESSFUL.toString();
		}

		return response;
	}

	@Override
	public String editStudentInRepository(String xmlStudentData) {
		String response = Response.INVALID_INPUT.toString();
		if (xmlStudentData == null) {
			return response;
		}
		Student student = null;

		student = xmlConverter.convertXmlStringToObject(xmlStudentData);

		if (student.getId() != null && student.getFirstName() != null && student.getLastName() != null
				&& student.getDateOfBirth() != null
				&& studentDAO.loadStudentRepository().getStudentMap().containsKey(student.getId())) {
			studentDAO.loadStudentRepository().getStudentMap().put(student.getId(), student);
			studentDAO.saveStudentRepository();
			response = Response.SUCCESSFUL.toString();
		}
		return response;
	}

	@Override
	public String removeStudentFromRepository(String id) {
		String response = Response.NO_MATCHES.toString();
		if (id == null) {
			response = Response.INVALID_INPUT.toString();
			return response;
		}
		if (studentDAO.loadStudentRepository().removeStudent(Integer.parseInt(id))) {
			studentDAO.saveStudentRepository();
			response = Response.SUCCESSFUL.toString();
		}
		return response;
	}

	@Override
	public String saveRepository() {
		String response = Response.SUCCESSFUL.toString();
		if (studentDAO.loadStudentRepository().getStudentMap().size() > 0) {
			studentDAO.saveStudentRepository();
		}

		return response;
	}

}
