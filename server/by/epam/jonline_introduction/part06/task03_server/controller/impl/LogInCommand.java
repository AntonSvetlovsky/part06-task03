package by.epam.jonline_introduction.part06.task03_server.controller.impl;

import by.epam.jonline_introduction.part06.task03_server.bean.UserRole;
import by.epam.jonline_introduction.part06.task03_server.controller.Command;
import by.epam.jonline_introduction.part06.task03_server.service.ServiceProvider;
import by.epam.jonline_introduction.part06.task03_server.service.UserService;

public class LogInCommand implements Command {

	@Override
	public String execute(String request) {

		String response = null;
		String accessCode;
		String userName;
		String userPassword;
		String[] params = new String[3];
		String[] tmpArray;
		ServiceProvider provider = ServiceProvider.getInstance();
		UserService service = provider.getUserService();

		request = request.trim();
		tmpArray = request.split("\\|", 3);
		for (int i = 0; i < tmpArray.length; i++) {
			params[i] = tmpArray[i].trim();
		}
		accessCode = params[0];
		userName = params[1];
		userPassword = params[2];

		if (!accessCode.equals(UserRole.GUEST.toString())) {
			service.logOut(accessCode);
		}

		response = service.logIn(userName, userPassword);

		return response;
	}

}
