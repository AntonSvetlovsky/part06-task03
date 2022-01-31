package by.epam.jonline_introduction.part06.task03_server.dao;

import by.epam.jonline_introduction.part06.task03_server.dao.impl.StudentDAOImpl;
import by.epam.jonline_introduction.part06.task03_server.dao.impl.UserDAOImpl;

public final class DAOProvider {

	private static final DAOProvider instance = new DAOProvider();
	private final StudentDAO studentDAO = new StudentDAOImpl();
	private final UserDAO userDAO = new UserDAOImpl();

	private DAOProvider() {
	}

	public static DAOProvider getInstance() {
		return instance;
	}

	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

}
