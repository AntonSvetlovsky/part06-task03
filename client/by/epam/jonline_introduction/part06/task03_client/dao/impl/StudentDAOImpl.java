package by.epam.jonline_introduction.part06.task03_client.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Map;
import by.epam.jonline_introduction.part06.task03_client.bean.Student;
import by.epam.jonline_introduction.part06.task03_client.bean.UserRole;
import by.epam.jonline_introduction.part06.task03_client.converter.ClientXmlConverter;
import by.epam.jonline_introduction.part06.task03_client.converter.ConverterProvider;
import by.epam.jonline_introduction.part06.task03_client.dao.StudentDAO;

public class StudentDAOImpl implements StudentDAO {

	private final ConverterProvider converterProvider = ConverterProvider.getInstance();
	private final ClientXmlConverter xmlConverter = converterProvider.getConverter();
	private Map<Integer, Student> studentMap = null;
	private String accessCode = UserRole.GUEST.toString();

	@Override
	public String connectToServer(String request) {

		String response = null;

		try (Socket socket = new Socket("127.0.0.1", 8000);
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

			writer.write(request);
			writer.newLine();
			writer.flush();
			response = reader.readLine();

		} catch (IOException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return response;
	}

	@Override
	public void loadStudentMap(String strXml) {

		studentMap = xmlConverter.convertXmlStringToObjectMap(strXml);
	}

	@Override
	public Map<Integer, Student> getStudentMap() {
		return studentMap;
	}

	@Override
	public String getAccessCode() {
		return accessCode;
	}

	@Override
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

}
