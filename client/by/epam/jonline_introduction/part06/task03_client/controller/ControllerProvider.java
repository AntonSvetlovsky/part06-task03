package by.epam.jonline_introduction.part06.task03_client.controller;

import by.epam.jonline_introduction.part06.task03_client.controller.impl.StudentControllerImpl;

public final class ControllerProvider {

	private static final ControllerProvider instance = new ControllerProvider();
	private final StudentController controller = new StudentControllerImpl();

	private ControllerProvider() {

	}

	public static ControllerProvider getInstance() {
		return instance;
	}

	public StudentController getController() {
		return controller;
	}

}
