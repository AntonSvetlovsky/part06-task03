package by.epam.jonline_introduction.part06.task03_client.controller.impl;

import by.epam.jonline_introduction.part06.task03_client.controller.Command;
import by.epam.jonline_introduction.part06.task03_client.service.ServiceProvider;
import by.epam.jonline_introduction.part06.task03_client.service.UserService;

public class CreateAdminAccountCommand implements Command {

	@Override
	public String execute(String request) {

		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserService();

		String userName;
		String userPassword;
		String[] paramsArray = new String[2];

		if (request != null) {
			String[] tmpArray = request.split(",", 2);
			for (int i = 0; i < tmpArray.length; i++) {
				paramsArray[i] = tmpArray[i].trim();
			}
		}
		userName = paramsArray[0];
		userPassword = paramsArray[1];

		return userService.createAdminAccount(userName, userPassword);
	}

}
