package by.epam.jonline_introduction.part06.task03_server.dao.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import by.epam.jonline_introduction.part06.task03_server.bean.User;
import by.epam.jonline_introduction.part06.task03_server.bean.UserRepository;
import by.epam.jonline_introduction.part06.task03_server.converter.ConverterProvider;
import by.epam.jonline_introduction.part06.task03_server.converter.ServerXmlConverter;
import by.epam.jonline_introduction.part06.task03_server.dao.UserDAO;

public class UserDAOImpl implements UserDAO {

	private final ConverterProvider converterProvider = ConverterProvider.getInstance();
	private final ServerXmlConverter xmlConverter = converterProvider.getConverter();
	private UserRepository repository;
	private Map<String, User> activeUserMap = new ConcurrentHashMap<String, User>();

	@Override
	public UserRepository loadUserRepository() {

		if (repository == null) {

			repository = xmlConverter.convertXmlFileToUserRepository("resources\\userRepository.xml");
		}

		return repository;
	}

	@Override
	public void saveUserRepository() {

		xmlConverter.convertUserRepositoryToXmlFile(repository, "resources\\userRepository.xml");
	}

	@Override
	public Map<String, User> getActiveUserMap() {

		return activeUserMap;
	}

}
