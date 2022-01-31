package by.epam.jonline_introduction.part06.task03_client.controller.impl;

import by.epam.jonline_introduction.part06.task03_client.controller.Command;
import by.epam.jonline_introduction.part06.task03_client.service.ServiceProvider;
import by.epam.jonline_introduction.part06.task03_client.service.StudentService;

public class AddStudentCommand implements Command {

	@Override
	public String execute(String request) {

		ServiceProvider provider = ServiceProvider.getInstance();
		StudentService service = provider.getStudentService();

		String firstName;
		String lastName;
		String dateOfBirth;
		String description;

		String[] paramsArray = new String[4];
		if (request != null) {
			String[] tmpArray = request.split(",", 4);
			for (int i = 0; i < tmpArray.length; i++) {
				paramsArray[i] = tmpArray[i].trim();
			}
		}
		firstName = paramsArray[0];
		lastName = paramsArray[1];
		dateOfBirth = paramsArray[2];
		description = paramsArray[3];

		return service.addStudentToRepository(firstName, lastName, dateOfBirth, description);
	}

}
