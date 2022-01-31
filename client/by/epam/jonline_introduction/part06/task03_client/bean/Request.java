package by.epam.jonline_introduction.part06.task03_client.bean;

public enum Request {

	CREATE_USER_ACCOUNT("1"), LOG_IN("2"), SHOW_STUDENT("3"), ADD_STUDENT("4"), EDIT_STUDENT("5"), REMOVE_STUDENT("6"),
	LOG_OUT("7"), CREATE_ADMIN_ACCOUNT("8"), REMOVE_ACCOUNT("9"), STOP_SERVER("10"), EXIT("11");

	private String title;

	private Request(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public static Request getRequestByNumber(String number) {
		Request request = null;
		Request[] requestArray = Request.values();
		for (Request r : requestArray) {
			if (r.title.equals(number)) {
				request = r;
			}
		}
		return request;
	}

}
