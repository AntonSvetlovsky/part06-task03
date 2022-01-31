package by.epam.jonline_introduction.part06.task03_server.controller;

import java.util.HashMap;
import java.util.Map;

import by.epam.jonline_introduction.part06.task03_server.bean.Request;
import by.epam.jonline_introduction.part06.task03_server.controller.impl.AddStudentCommand;
import by.epam.jonline_introduction.part06.task03_server.controller.impl.CreateAdminAccountCommand;
import by.epam.jonline_introduction.part06.task03_server.controller.impl.CreateUserAccountCommand;
import by.epam.jonline_introduction.part06.task03_server.controller.impl.EditStudentCommand;
import by.epam.jonline_introduction.part06.task03_server.controller.impl.ExitCommand;
import by.epam.jonline_introduction.part06.task03_server.controller.impl.LogInCommand;
import by.epam.jonline_introduction.part06.task03_server.controller.impl.LogOutCommand;
import by.epam.jonline_introduction.part06.task03_server.controller.impl.RemoveAccountCommand;
import by.epam.jonline_introduction.part06.task03_server.controller.impl.RemoveStudentCommand;
import by.epam.jonline_introduction.part06.task03_server.controller.impl.ShowStudentCommand;

public class CommandProvider {

	private final Map<Request, Command> commandMap = new HashMap<Request, Command>();

	public CommandProvider() {

		commandMap.put(Request.SHOW_STUDENT, new ShowStudentCommand());
		commandMap.put(Request.ADD_STUDENT, new AddStudentCommand());
		commandMap.put(Request.EDIT_STUDENT, new EditStudentCommand());
		commandMap.put(Request.REMOVE_STUDENT, new RemoveStudentCommand());
		commandMap.put(Request.STOP_SERVER, new ExitCommand());
		commandMap.put(Request.CREATE_ADMIN_ACCOUNT, new CreateAdminAccountCommand());
		commandMap.put(Request.CREATE_USER_ACCOUNT, new CreateUserAccountCommand());
		commandMap.put(Request.LOG_IN, new LogInCommand());
		commandMap.put(Request.LOG_OUT, new LogOutCommand());
		commandMap.put(Request.REMOVE_ACCOUNT, new RemoveAccountCommand());

	}

	public Command getCommand(String commandName) {
		Command command = null;

		command = commandMap.get(Request.valueOf(commandName));

		return command;
	}
}
