package by.epam.jonline_introduction.part06.task03_server.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import by.epam.jonline_introduction.part06.task03_server.bean.Response;
import by.epam.jonline_introduction.part06.task03_server.bean.User;
import by.epam.jonline_introduction.part06.task03_server.bean.UserRole;
import by.epam.jonline_introduction.part06.task03_server.dao.DAOProvider;
import by.epam.jonline_introduction.part06.task03_server.dao.UserDAO;
import by.epam.jonline_introduction.part06.task03_server.service.UserService;

public class UserServiceImpl implements UserService {

	private final DAOProvider provider = DAOProvider.getInstance();
	private final UserDAO userDAO = provider.getUserDAO();

	@Override
	public String logIn(String userName, String userPassword) {

		String response = Response.NO_MATCHES.toString();
		String encryptedPassword = encryptString(userPassword);
		for (User user : userDAO.loadUserRepository().getUserMap().values()) {
			if (userName.equals(user.getUserName()) && encryptedPassword.equals(user.getUserPassword())) {
				String accessCode = UUID.randomUUID().toString();
				userDAO.getActiveUserMap().put(accessCode, user);
				response = Response.SUCCESSFUL + "|" + accessCode;
			}
		}

		return response;
	}

	@Override
	public String logOut(String accessCode) {

		String response = Response.ACCESS_DENIED.toString();
		if (userDAO.getActiveUserMap().containsKey(accessCode)) {
			userDAO.getActiveUserMap().remove(accessCode);
			response = Response.SUCCESSFUL.toString();
		}

		return response;
	}

	@Override
	public String createUserAccount(String userName, String userPassword) {

		String response = Response.INVALID_INPUT.toString();
		int minPasswordLength = 4;
		for (User user : userDAO.loadUserRepository().getUserMap().values()) {
			if (userName.equals(user.getUserName())) {
				return response;
			}
		}
		if (userPassword.length() < minPasswordLength) {
			return response;
		}
		String encryptedPassword = encryptString(userPassword);
		User user = new User(userName, encryptedPassword, UserRole.USER);
		userDAO.loadUserRepository().addUser(user);
		String accessCode = UUID.randomUUID().toString();
		userDAO.getActiveUserMap().put(accessCode, user);
		response = Response.SUCCESSFUL + "|" + accessCode;

		return response;
	}

	@Override
	public String createAdminAccount(String userName, String userPassword) {

		String response = Response.INVALID_INPUT.toString();
		int minPasswordLength = 4;
		for (User user : userDAO.loadUserRepository().getUserMap().values()) {
			if (userName.equals(user.getUserName())) {
				return response;
			}
		}
		if (userPassword.length() < minPasswordLength) {
			return response;
		}
		String encryptedPassword = encryptString(userPassword);
		User user = new User(userName, encryptedPassword, UserRole.ADMIN);
		userDAO.loadUserRepository().addUser(user);
		String accessCode = UUID.randomUUID().toString();
		userDAO.getActiveUserMap().put(accessCode, user);
		response = Response.SUCCESSFUL + "|" + accessCode;

		return response;
	}

	@Override
	public String removeAccount(String accessCode) {

		String response = Response.ACCESS_DENIED.toString();
		if (userDAO.getActiveUserMap().containsKey(accessCode)) {
			Integer id = userDAO.getActiveUserMap().get(accessCode).getId();
			userDAO.getActiveUserMap().remove(accessCode);
			userDAO.loadUserRepository().removeUser(id);
			response = Response.SUCCESSFUL.toString();
		}

		return response;
	}

	@Override
	public String checkAccess(String accessCode) {

		String response = UserRole.GUEST.toString();
		if (accessCode.equals(UserRole.GUEST.toString())) {
			return response;
		}
		if (userDAO.getActiveUserMap().containsKey(accessCode)) {
			response = userDAO.getActiveUserMap().get(accessCode).getUserRole().toString();
		}

		return response;
	}

	@Override
	public String saveUserRepository() {
		String response = Response.SUCCESSFUL.toString();

		if (userDAO.loadUserRepository().getUserMap().size() > 0) {
			userDAO.saveUserRepository();
		}

		return response;
	}

	@Override
	public boolean repositoryIsEmpty() {
		boolean response = false;

		if (userDAO.loadUserRepository().getUserMap().size() == 0) {
			response = true;
		}

		return response;
	}

	private String encryptString(String str) {
		StringBuilder encryptedStr = new StringBuilder();
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(str.getBytes());
			for (byte b : bytes) {
				encryptedStr.append(String.format("%02x", b));
			}
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		return encryptedStr.toString();
	}

}
