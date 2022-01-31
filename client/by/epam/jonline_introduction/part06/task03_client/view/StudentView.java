package by.epam.jonline_introduction.part06.task03_client.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import by.epam.jonline_introduction.part06.task03_client.bean.Response;
import by.epam.jonline_introduction.part06.task03_client.controller.ControllerProvider;
import by.epam.jonline_introduction.part06.task03_client.controller.StudentController;

public class StudentView {

	private final ControllerProvider provider = ControllerProvider.getInstance();
	private final StudentController controller = provider.getController();
	private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public void show() {

		try (reader) {
			printMessage("Student Database" + "\n");
			boolean flag = true;
			while (flag) {
				printMessage("-".repeat(48) + "\n" + " ".repeat(15) + "Available Options" + " ".repeat(16) + "\n"
						+ "-".repeat(48) + "\n"

						+ "1.  Create New User Account." + "\n"
						+ "    Enter user name and password after the number of command separated by comma." + "\n"
						+ "    Input example : 1,userName,userPassword ." + "\n"

						+ "2.  Log In." + "\n"
						+ "    Enter user name and password after the number of command separated by comma." + "\n"
						+ "    Input example : 2,userName,userPassword ." + "\n"

						+ "3.  Find Student By Last Name." + "\n"
						+ "    Enter the requested last name after the number of command separated by comma." + "\n"
						+ "    Input example : 3,lastName ." + "\n"

						+ "4.  Add Student To The Database." + "\n"
						+ "    Enter first name, last name, date of birth, description after the number" + "\n"
						+ "    of command dividing by comma." + "\n"
						+ "    Input example : 4,firstName,lastName,YYYY-MM-DD,description ." + "\n"

						+ "5.  Edit Student Data." + "\n"
						+ "    Enter id(of student to edit), first name, last name, date of birth, description" + "\n"
						+ "    after the number of command dividing by comma." + "\n"
						+ "    Input example : 5,id,firstName,lastName,YYYY-MM-DD,description ." + "\n"

						+ "6.  Remove Student From Database." + "\n"
						+ "    Enter id(of student to remove) after the number of command separated by comma." + "\n"
						+ "    Input example : 6,id ." + "\n"

						+ "7.  Log Out." + "\n"

						+ "8.  Create New Admin Account." + "\n"
						+ "    Enter user name and password after the number of command separated by comma." + "\n"
						+ "    Input example : 8,userName,userPassword ." + "\n"

						+ "9.  Remove Account." + "\n"

						+ "10. Stop Server." + "\n"

						+ "11. Exit." + "\n" + "-".repeat(48) + "\n" + "Enter Your Choice:" + "\n");

				printMessage(">>");
				String request = reader.readLine();

				String response = controller.doAction(request);
				String[] result = response.split("\n", 2);

				Response resp = Response.valueOf(result[0]);
				printMessage(resp.toString());
				if (resp.equals(Response.EXIT)) {
					flag = false;
				}
				if (result.length > 1) {
					printStudent(result[1]);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void printMessage(String message) {
		System.out.println(message);
	}

	private void printStudent(String result) {

		String[] studentArray = result.split("\n");

		for (String student : studentArray) {
			String[] studentData = student.split("\\|");
			System.out.println("Student ID: " + studentData[0]);
			System.out.println("First Name: " + studentData[1]);
			System.out.println("Last Name: " + studentData[2]);
			System.out.println("Date Of Birth: " + studentData[3]);
			System.out.println("Description: " + studentData[4]);
			System.out.println();
		}
	}

}
