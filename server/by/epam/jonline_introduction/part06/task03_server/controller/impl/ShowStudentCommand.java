package by.epam.jonline_introduction.part06.task03_server.controller.impl;

import by.epam.jonline_introduction.part06.task03_server.bean.Response;
import by.epam.jonline_introduction.part06.task03_server.bean.UserRole;
import by.epam.jonline_introduction.part06.task03_server.controller.Command;
import by.epam.jonline_introduction.part06.task03_server.service.ServiceProvider;
import by.epam.jonline_introduction.part06.task03_server.service.StudentService;
import by.epam.jonline_introduction.part06.task03_server.service.UserService;

public class ShowStudentCommand implements Command {

	@Override
	public String execute(String request) {

		String response = Response.ACCESS_DENIED.toString();
		String accessCode;
		String studentLastName;
		String[] params = new String[2];
		String[] tmpArray;

		ServiceProvider provider = ServiceProvider.getInstance();
		StudentService studentService = provider.getStudentService();
		UserService userService = provider.getUserService();

		request = request.trim();
		tmpArray = request.split("\\|", 2);
		for (int i = 0; i < tmpArray.length; i++) {
			params[i] = tmpArray[i].trim();
		}
		accessCode = params[0];
		studentLastName = params[1];

		if (userService.checkAccess(accessCode).equals(UserRole.USER.toString())
				|| userService.checkAccess(accessCode).equals(UserRole.ADMIN.toString())) {
			response = studentService.findStudentByLastName(studentLastName);
		}

		return response;
	}

}
