package by.epam.jonline_introduction.part06.task03_server.bean;

public class User {

	private Integer id;
	private String userName;
	private String userPassword;
	private UserRole userRole;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(Integer id, String userName, String password, UserRole userRole) {
		this.id = id;
		this.userName = userName;
		this.userPassword = password;
		this.userRole = userRole;
	}

	public User(String userName, String password, UserRole userRole) {
		this.userName = userName;
		this.userPassword = password;
		this.userRole = userRole;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String password) {
		this.userPassword = password;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((userPassword == null) ? 0 : userPassword.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (userPassword == null) {
			if (other.userPassword != null) {
				return false;
			}
		} else if (!userPassword.equals(other.userPassword)) {
			return false;
		}
		if (userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!userName.equals(other.userName)) {
			return false;
		}
		if (userRole != other.userRole) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userPassword=" + userPassword + ", userRole=" + userRole
				+ "]";
	}

}
