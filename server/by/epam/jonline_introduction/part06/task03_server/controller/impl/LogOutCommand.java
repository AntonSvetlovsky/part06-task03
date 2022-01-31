package by.epam.jonline_introduction.part06.task03_server.controller.impl;

import by.epam.jonline_introduction.part06.task03_server.bean.Response;
import by.epam.jonline_introduction.part06.task03_server.bean.UserRole;
import by.epam.jonline_introduction.part06.task03_server.controller.Command;
import by.epam.jonline_introduction.part06.task03_server.service.ServiceProvider;
import by.epam.jonline_introduction.part06.task03_server.service.UserService;

public class LogOutCommand implements Command {

	@Override
	public String execute(String request) {

		String response = Response.ACCESS_DENIED.toString();
		ServiceProvider provider = ServiceProvider.getInstance();
		UserService service = provider.getUserService();

		String accessCode = request;

		if (!accessCode.equals(UserRole.GUEST.toString())) {
			response = service.logOut(accessCode);
		}

		return response;
	}

}
