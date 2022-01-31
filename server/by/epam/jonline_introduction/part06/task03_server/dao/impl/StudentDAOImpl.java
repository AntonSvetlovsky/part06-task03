package by.epam.jonline_introduction.part06.task03_server.dao.impl;

import by.epam.jonline_introduction.part06.task03_server.bean.StudentRepository;
import by.epam.jonline_introduction.part06.task03_server.converter.ConverterProvider;
import by.epam.jonline_introduction.part06.task03_server.converter.ServerXmlConverter;
import by.epam.jonline_introduction.part06.task03_server.dao.StudentDAO;

public class StudentDAOImpl implements StudentDAO {

	private final ConverterProvider converterProvider = ConverterProvider.getInstance();
	private final ServerXmlConverter xmlConverter = converterProvider.getConverter();
	private StudentRepository repository;

	@Override
	public StudentRepository loadStudentRepository() {
		if (repository == null) {

			repository = xmlConverter.convertXmlFileToStudentRepository("resources\\studentRepository.xml");
		}
		return repository;
	}

	@Override
	public void saveStudentRepository() {

		xmlConverter.convertStudentRepositoryToXmlFile(repository, "resources\\studentRepository.xml");
	}

}
