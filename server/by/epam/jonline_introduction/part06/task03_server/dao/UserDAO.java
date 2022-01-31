package by.epam.jonline_introduction.part06.task03_server.dao;

import java.util.Map;

import by.epam.jonline_introduction.part06.task03_server.bean.User;
import by.epam.jonline_introduction.part06.task03_server.bean.UserRepository;

public interface UserDAO {

	UserRepository loadUserRepository();

	void saveUserRepository();

	Map<String, User> getActiveUserMap();

}
