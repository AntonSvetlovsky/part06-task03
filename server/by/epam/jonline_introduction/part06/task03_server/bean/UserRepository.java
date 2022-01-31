package by.epam.jonline_introduction.part06.task03_server.bean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRepository {

	private static AtomicInteger idCounter;
	private Map<Integer, User> userMap;

	static {
		idCounter = new AtomicInteger(0);
	}

	{
		userMap = new ConcurrentHashMap<Integer, User>();
	}

	public UserRepository() {
		// TODO Auto-generated constructor stub
	}

	public static AtomicInteger getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(AtomicInteger idcounter) {
		UserRepository.idCounter = idcounter;
	}

	public Map<Integer, User> getUserMap() {
		return userMap;
	}

	public void addUser(User user) {
		int id = idCounter.incrementAndGet();
		user.setId(Integer.valueOf(id));
		userMap.put(Integer.valueOf(id), user);
	}

	public void removeUser(Integer id) {
		userMap.remove(id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userMap == null) ? 0 : userMap.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		UserRepository other = (UserRepository) obj;
		if (userMap == null) {
			if (other.userMap != null) {
				return false;
			}
		} else if (!userMap.equals(other.userMap)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "UserRepository [userMap=" + userMap + "]";
	}

}
