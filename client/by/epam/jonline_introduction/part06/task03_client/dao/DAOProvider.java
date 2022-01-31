package by.epam.jonline_introduction.part06.task03_client.dao;

import by.epam.jonline_introduction.part06.task03_client.dao.impl.StudentDAOImpl;

public final class DAOProvider {

	private static final DAOProvider instance = new DAOProvider();
	private final StudentDAO studentDAO = new StudentDAOImpl();

	private DAOProvider() {

	}

	public static DAOProvider getInstance() {
		return instance;
	}

	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

}
