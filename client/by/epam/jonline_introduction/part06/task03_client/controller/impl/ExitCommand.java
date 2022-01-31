package by.epam.jonline_introduction.part06.task03_client.controller.impl;

import by.epam.jonline_introduction.part06.task03_client.bean.Response;
import by.epam.jonline_introduction.part06.task03_client.controller.Command;

public class ExitCommand implements Command {

	@Override
	public String execute(String request) {

		return Response.EXIT.toString();
	}

}
