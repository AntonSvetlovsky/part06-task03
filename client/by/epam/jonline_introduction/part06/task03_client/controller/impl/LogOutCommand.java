package by.epam.jonline_introduction.part06.task03_client.controller.impl;

import by.epam.jonline_introduction.part06.task03_client.controller.Command;
import by.epam.jonline_introduction.part06.task03_client.service.ServiceProvider;
import by.epam.jonline_introduction.part06.task03_client.service.UserService;

public class LogOutCommand implements Command {

	@Override
	public String execute(String request) {
		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserService();

		return userService.logOut();
	}

}
