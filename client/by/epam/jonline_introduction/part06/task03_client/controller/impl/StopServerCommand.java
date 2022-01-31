package by.epam.jonline_introduction.part06.task03_client.controller.impl;

import by.epam.jonline_introduction.part06.task03_client.controller.Command;
import by.epam.jonline_introduction.part06.task03_client.service.ServiceProvider;
import by.epam.jonline_introduction.part06.task03_client.service.StudentService;

public class StopServerCommand implements Command {

	@Override
	public String execute(String request) {

		ServiceProvider provider = ServiceProvider.getInstance();
		StudentService service = provider.getStudentService();

		return service.stopServer();
	}

}
