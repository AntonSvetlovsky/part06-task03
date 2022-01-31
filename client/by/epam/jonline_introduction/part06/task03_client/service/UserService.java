package by.epam.jonline_introduction.part06.task03_client.service;

public interface UserService {

	String logIn(String userName, String userPassword);

	String logOut();

	String createUserAccount(String userName, String userPassword);

	String createAdminAccount(String userName, String userPassword);

	String removeAccount();

}
