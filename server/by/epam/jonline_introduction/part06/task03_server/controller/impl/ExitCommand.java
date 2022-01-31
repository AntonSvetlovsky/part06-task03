package by.epam.jonline_introduction.part06.task03_server.controller.impl;

import by.epam.jonline_introduction.part06.task03_server.bean.Response;
import by.epam.jonline_introduction.part06.task03_server.bean.UserRole;
import by.epam.jonline_introduction.part06.task03_server.controller.Command;
import by.epam.jonline_introduction.part06.task03_server.service.ServiceProvider;
import by.epam.jonline_introduction.part06.task03_server.service.StudentService;
import by.epam.jonline_introduction.part06.task03_server.service.UserService;

public class ExitCommand implements Command {

	@Override
	public String execute(String request) {

		String response = Response.ACCESS_DENIED.toString();
		String userServiceResponse;
		String studentServiceResponse;
		String accessCode = request;

		ServiceProvider provider = ServiceProvider.getInstance();
		StudentService studentService = provider.getStudentService();
		UserService userService = provider.getUserService();

		if (userService.checkAccess(accessCode).equals(UserRole.ADMIN.toString())) {
			userServiceResponse = userService.saveUserRepository();
			studentServiceResponse = studentService.saveRepository();
			if (userServiceResponse.equals(Response.SUCCESSFUL.toString())
					&& studentServiceResponse.equals(Response.SUCCESSFUL.toString())) {
				response = Response.STOP_SERVER.toString();
			}
		}
		return response;
	}

}
