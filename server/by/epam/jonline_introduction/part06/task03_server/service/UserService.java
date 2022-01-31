package by.epam.jonline_introduction.part06.task03_server.service;

public interface UserService {

	String logIn(String userName, String userPassword);

	String logOut(String accessCode);

	String createUserAccount(String userName, String userPassword);

	String createAdminAccount(String userName, String userPassword);

	String removeAccount(String accessCode);

	String checkAccess(String accessCode);

	String saveUserRepository();

	boolean repositoryIsEmpty();
}
