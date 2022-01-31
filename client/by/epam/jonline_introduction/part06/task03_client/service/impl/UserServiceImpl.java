package by.epam.jonline_introduction.part06.task03_client.service.impl;

import by.epam.jonline_introduction.part06.task03_client.bean.Request;
import by.epam.jonline_introduction.part06.task03_client.bean.Response;
import by.epam.jonline_introduction.part06.task03_client.bean.UserRole;
import by.epam.jonline_introduction.part06.task03_client.dao.DAOProvider;
import by.epam.jonline_introduction.part06.task03_client.dao.StudentDAO;
import by.epam.jonline_introduction.part06.task03_client.service.UserService;

public class UserServiceImpl implements UserService {

	private final DAOProvider provider = DAOProvider.getInstance();
	private final StudentDAO studentDAO = provider.getStudentDAO();

	@Override
	public String logIn(String userName, String userPassword) {

		String serverResponse;
		String[] serverResponseArray;
		String response = Response.INVALID_INPUT.toString();
		String requestType = Request.LOG_IN.toString();

		if (userName == null && userPassword == null) {
			return response;
		}

		serverResponse = studentDAO
				.connectToServer(requestType + "|" + studentDAO.getAccessCode() + "|" + userName + "|" + userPassword);
		serverResponseArray = serverResponse.split("\\|", 2);
		response = serverResponseArray[0];

		if (response.equals(Response.SUCCESSFUL.toString())) {
			studentDAO.setAccessCode(serverResponseArray[1]);
		}

		return response;
	}

	@Override
	public String logOut() {

		String serverResponse;
		String response = Response.ACCESS_DENIED.toString();
		String requestType = Request.LOG_OUT.toString();
		if (studentDAO.getAccessCode().equals(UserRole.GUEST.toString())) {
			return response;
		}

		serverResponse = studentDAO.connectToServer(requestType + "|" + studentDAO.getAccessCode());

		response = serverResponse;

		return response;
	}

	@Override
	public String createUserAccount(String userName, String userPassword) {

		String serverResponse;
		String[] serverResponseArray;
		String response = Response.INVALID_INPUT.toString();
		String requestType = Request.CREATE_USER_ACCOUNT.toString();

		if (userName == null && userPassword == null) {
			return response;
		}

		serverResponse = studentDAO
				.connectToServer(requestType + "|" + studentDAO.getAccessCode() + "|" + userName + "|" + userPassword);
		serverResponseArray = serverResponse.split("\\|", 2);
		response = serverResponseArray[0];

		if (response.equals(Response.SUCCESSFUL.toString())) {
			studentDAO.setAccessCode(serverResponseArray[1]);
		}

		return response;
	}

	@Override
	public String createAdminAccount(String userName, String userPassword) {

		String serverResponse;
		String[] serverResponseArray;
		String response = Response.INVALID_INPUT.toString();
		String requestType = Request.CREATE_ADMIN_ACCOUNT.toString();

		if (userName == null && userPassword == null) {
			return response;
		}

		serverResponse = studentDAO
				.connectToServer(requestType + "|" + studentDAO.getAccessCode() + "|" + userName + "|" + userPassword);
		serverResponseArray = serverResponse.split("\\|", 2);
		response = serverResponseArray[0];

		if (response.equals(Response.SUCCESSFUL.toString())) {
			studentDAO.setAccessCode(serverResponseArray[1]);
		}

		return response;
	}

	@Override
	public String removeAccount() {

		String serverResponse;
		String response = Response.ACCESS_DENIED.toString();
		String requestType = Request.REMOVE_ACCOUNT.toString();
		if (studentDAO.getAccessCode().equals(UserRole.GUEST.toString())) {
			return response;
		}

		serverResponse = studentDAO.connectToServer(requestType + "|" + studentDAO.getAccessCode());

		response = serverResponse;

		return response;
	}

}
