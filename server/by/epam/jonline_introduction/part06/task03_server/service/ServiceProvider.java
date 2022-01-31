package by.epam.jonline_introduction.part06.task03_server.service;

import by.epam.jonline_introduction.part06.task03_server.service.impl.StudentServiceImpl;
import by.epam.jonline_introduction.part06.task03_server.service.impl.UserServiceImpl;

public final class ServiceProvider {

	private static final ServiceProvider instance = new ServiceProvider();
	private final StudentService studentService = new StudentServiceImpl();
	private final UserService userService = new UserServiceImpl();

	private ServiceProvider() {
	}

	public static ServiceProvider getInstance() {
		return instance;
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public UserService getUserService() {
		return userService;
	}

}
