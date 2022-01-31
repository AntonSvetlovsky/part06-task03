package by.epam.jonline_introduction.part06.task03_client.controller;

import java.util.HashMap;
import java.util.Map;

import by.epam.jonline_introduction.part06.task03_client.bean.Request;
import by.epam.jonline_introduction.part06.task03_client.controller.impl.AddStudentCommand;
import by.epam.jonline_introduction.part06.task03_client.controller.impl.CreateAdminAccountCommand;
import by.epam.jonline_introduction.part06.task03_client.controller.impl.CreateUserAccountCommand;
import by.epam.jonline_introduction.part06.task03_client.controller.impl.EditStudentCommand;
import by.epam.jonline_introduction.part06.task03_client.controller.impl.ExitCommand;
import by.epam.jonline_introduction.part06.task03_client.controller.impl.FindStudentByLastNameCommand;
import by.epam.jonline_introduction.part06.task03_client.controller.impl.LogInCommand;
import by.epam.jonline_introduction.part06.task03_client.controller.impl.LogOutCommand;
import by.epam.jonline_introduction.part06.task03_client.controller.impl.RemoveAccountCommand;
import by.epam.jonline_introduction.part06.task03_client.controller.impl.RemoveStudentCommand;
import by.epam.jonline_introduction.part06.task03_client.controller.impl.StopServerCommand;

public class CommandProvider {

	private Map<Request, Command> commandMap = new HashMap<Request, Command>();

	public CommandProvider() {

		commandMap.put(Request.CREATE_USER_ACCOUNT, new CreateUserAccountCommand());
		commandMap.put(Request.LOG_IN, new LogInCommand());
		commandMap.put(Request.SHOW_STUDENT, new FindStudentByLastNameCommand());
		commandMap.put(Request.ADD_STUDENT, new AddStudentCommand());
		commandMap.put(Request.EDIT_STUDENT, new EditStudentCommand());
		commandMap.put(Request.REMOVE_STUDENT, new RemoveStudentCommand());
		commandMap.put(Request.LOG_OUT, new LogOutCommand());
		commandMap.put(Request.CREATE_ADMIN_ACCOUNT, new CreateAdminAccountCommand());
		commandMap.put(Request.REMOVE_ACCOUNT, new RemoveAccountCommand());
		commandMap.put(Request.STOP_SERVER, new StopServerCommand());
		commandMap.put(Request.EXIT, new ExitCommand());

	}

	public Command getCommand(Request commandName) {

		Command command = null;
		command = commandMap.get(commandName);

		return command;
	}
}
