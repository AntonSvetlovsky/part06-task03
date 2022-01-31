package by.epam.jonline_introduction.part06.task03_client.controller.impl;

import by.epam.jonline_introduction.part06.task03_client.bean.Request;
import by.epam.jonline_introduction.part06.task03_client.bean.Response;
import by.epam.jonline_introduction.part06.task03_client.controller.Command;
import by.epam.jonline_introduction.part06.task03_client.controller.CommandProvider;
import by.epam.jonline_introduction.part06.task03_client.controller.StudentController;

public class StudentControllerImpl implements StudentController {

	private final CommandProvider provider = new CommandProvider();

	@Override
	public String doAction(String request) {

		String[] params = new String[2];
		String[] tmpArray;
		Request commandName;
		String response;

		request = request.trim();
		tmpArray = request.split(",", 2);
		for (int i = 0; i < tmpArray.length; i++) {
			params[i] = tmpArray[i].trim();
		}

		commandName = Request.getRequestByNumber(params[0]);

		if (commandName == null) {
			response = Response.INVALID_COMMAND.toString();
			return response;
		}

		Command currentCommand = provider.getCommand(commandName);
		response = currentCommand.execute(params[1]);

		return response;
	}

}
