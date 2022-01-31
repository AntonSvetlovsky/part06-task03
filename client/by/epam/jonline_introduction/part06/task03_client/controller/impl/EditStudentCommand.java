package by.epam.jonline_introduction.part06.task03_client.controller.impl;

import by.epam.jonline_introduction.part06.task03_client.controller.Command;
import by.epam.jonline_introduction.part06.task03_client.service.ServiceProvider;
import by.epam.jonline_introduction.part06.task03_client.service.StudentService;

public class EditStudentCommand implements Command {

	@Override
	public String execute(String request) {

		ServiceProvider provider = ServiceProvider.getInstance();
		StudentService service = provider.getStudentService();

		String id;
		String firstName;
		String lastName;
		String dateOfBirth;
		String description;

		String[] paramsArray = new String[5];
		if (request != null) {
			String[] tmpArray = request.split(",", 5);
			for (int i = 0; i < tmpArray.length; i++) {
				paramsArray[i] = tmpArray[i].trim();
			}
		}
		id = paramsArray[0];
		firstName = paramsArray[1];
		lastName = paramsArray[2];
		dateOfBirth = paramsArray[3];
		description = paramsArray[4];

		return service.editStudentInRepository(id, firstName, lastName, dateOfBirth, description);
	}

}
