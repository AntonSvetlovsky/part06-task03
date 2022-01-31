package by.epam.jonline_introduction.part06.task03_server.converter.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import by.epam.jonline_introduction.part06.task03_server.bean.Student;
import by.epam.jonline_introduction.part06.task03_server.bean.StudentRepository;
import by.epam.jonline_introduction.part06.task03_server.bean.User;
import by.epam.jonline_introduction.part06.task03_server.bean.UserRepository;
import by.epam.jonline_introduction.part06.task03_server.bean.UserRole;
import by.epam.jonline_introduction.part06.task03_server.converter.ServerXmlConverter;

public class ServerXmlConverterImpl implements ServerXmlConverter {

	@Override
	public String convertObjectListToXmlString(List<Student> studentList) {

		return convertDocumentToXmlString(convertObjectListToDocument(studentList));
	}

	@Override
	public Student convertXmlStringToObject(String xmlStr) {

		return convertDocumentToObject(convertXmlStringToDocument(xmlStr));
	}

	@Override
	public StudentRepository convertXmlFileToStudentRepository(String filePath) {

		return convertDocumentToStudentRepository(convertXmlFileToDocument(filePath));
	}

	@Override
	public void convertStudentRepositoryToXmlFile(StudentRepository repository, String filePath) {

		convertDocumentToXmlFile(convertStudentRepositoryToDocument(repository), filePath);

	}

	@Override
	public UserRepository convertXmlFileToUserRepository(String filePath) {

		return convertDocumentToUserRepository(convertXmlFileToDocument(filePath));
	}

	@Override
	public void convertUserRepositoryToXmlFile(UserRepository repository, String filePath) {

		convertDocumentToXmlFile(convertUserRepositoryToDocument(repository), filePath);

	}

	private Document convertObjectListToDocument(List<Student> studentList) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document document = null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.newDocument();
			Element root = document.createElement("students");

			for (Student student : studentList) {

				Element studentElement = document.createElement("student");
				studentElement.setAttribute("id", student.getId().toString());

				Element firstName = document.createElement("firstName");
				Text firstNameText = document.createTextNode(student.getFirstName());
				firstName.appendChild(firstNameText);

				Element lastName = document.createElement("lastName");
				Text lastNameText = document.createTextNode(student.getLastName());
				lastName.appendChild(lastNameText);

				Element dateOfBirth = document.createElement("dateOfBirth");
				Text dateOfBirhtText = document.createTextNode(String.valueOf(student.getDateOfBirth().toEpochDay()));
				dateOfBirth.appendChild(dateOfBirhtText);

				Element description = document.createElement("description");
				Text descriptionText = document.createTextNode(student.getDescription());
				description.appendChild(descriptionText);

				studentElement.appendChild(firstName);
				studentElement.appendChild(lastName);
				studentElement.appendChild(dateOfBirth);
				studentElement.appendChild(description);
				root.appendChild(studentElement);
			}

			document.appendChild(root);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return document;
	}

	private String convertDocumentToXmlString(Document document) {

		String result = null;
		TransformerFactory factory = TransformerFactory.newInstance();
		try (StringWriter stringWriter = new StringWriter()) {
			Transformer transformer = factory.newTransformer();
			transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
			result = stringWriter.toString();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	private Student convertDocumentToObject(Document document) {

		Student student = new Student();
		NodeList nodeList = document.getElementsByTagName("student");
		Node node = nodeList.item(0);

		Element element = (Element) node;
		if (element.hasAttribute("id")) {
			student.setId(Integer.parseInt(element.getAttribute("id")));
		}

		NodeList nodeListFirstName = element.getElementsByTagName("firstName");
		Node nodeFirstName = nodeListFirstName.item(0);
		student.setFirstName(nodeFirstName.getTextContent());

		NodeList nodeListLastName = element.getElementsByTagName("lastName");
		Node nodeLastName = nodeListLastName.item(0);
		student.setLastName(nodeLastName.getTextContent());

		NodeList nodeListDateOfBirth = element.getElementsByTagName("dateOfBirth");
		Node nodeDateOfBirth = nodeListDateOfBirth.item(0);
		student.setDateOfBirth(LocalDate.ofEpochDay(Long.parseLong(nodeDateOfBirth.getTextContent())));

		NodeList nodeListDescription = element.getElementsByTagName("description");
		Node nodeDescription = nodeListDescription.item(0);
		student.setDescription(nodeDescription.getTextContent());

		return student;
	}

	private Document convertXmlStringToDocument(String strXml) {
		Document document = null;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try (StringReader reader = new StringReader(strXml)) {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(new InputSource(reader));
			document.getDocumentElement().normalize();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return document;
	}

	private Document convertXmlFileToDocument(String filePath) {
		Document document = null;

		File file = new File(filePath);
		if (file.length() == 0) {
			return document;
		}

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(file);
			document.getDocumentElement().normalize();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return document;
	}

	private StudentRepository convertDocumentToStudentRepository(Document document) {

		StudentRepository repository = new StudentRepository();

		if (document == null) {
			return repository;
		}

		Map<Integer, Student> studentMap = new HashMap<Integer, Student>();

		AtomicInteger idCounter = new AtomicInteger(
				Integer.parseInt(document.getDocumentElement().getAttribute("idCounter")));
		StudentRepository.setIdCounter(idCounter);
		NodeList nodeList = document.getElementsByTagName("student");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			Student student = new Student();
			if (node.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) node;
				student.setId(Integer.parseInt(element.getAttribute("id")));

				NodeList nodeListFirstName = element.getElementsByTagName("firstName");
				Node nodeFirstName = nodeListFirstName.item(0);
				student.setFirstName(nodeFirstName.getTextContent());

				NodeList nodeListLastName = element.getElementsByTagName("lastName");
				Node nodeLastName = nodeListLastName.item(0);
				student.setLastName(nodeLastName.getTextContent());

				NodeList nodeListDateOfBirth = element.getElementsByTagName("dateOfBirth");
				Node nodeDateOfBirth = nodeListDateOfBirth.item(0);
				student.setDateOfBirth(LocalDate.ofEpochDay(Long.parseLong(nodeDateOfBirth.getTextContent())));

				NodeList nodeListDescription = element.getElementsByTagName("description");
				Node nodeDescription = nodeListDescription.item(0);
				student.setDescription(nodeDescription.getTextContent());
			}
			studentMap.put(student.getId(), student);
		}

		repository.getStudentMap().putAll(studentMap);

		return repository;
	}

	private Document convertStudentRepositoryToDocument(StudentRepository studentRepository) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document document = null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.newDocument();
			Element root = document.createElement("students");
			root.setAttribute("idCounter", StudentRepository.getIdCounter().toString());

			for (Student student : studentRepository.getStudentMap().values()) {

				Element studentElement = document.createElement("student");
				studentElement.setAttribute("id", student.getId().toString());

				Element firstName = document.createElement("firstName");
				Text firstNameText = document.createTextNode(student.getFirstName());
				firstName.appendChild(firstNameText);

				Element lastName = document.createElement("lastName");
				Text lastNameText = document.createTextNode(student.getLastName());
				lastName.appendChild(lastNameText);

				Element dateOfBirth = document.createElement("dateOfBirth");
				Text dateOfBirhtText = document.createTextNode(String.valueOf(student.getDateOfBirth().toEpochDay()));
				dateOfBirth.appendChild(dateOfBirhtText);

				Element description = document.createElement("description");
				Text descriptionText = document.createTextNode(student.getDescription());
				description.appendChild(descriptionText);

				studentElement.appendChild(firstName);
				studentElement.appendChild(lastName);
				studentElement.appendChild(dateOfBirth);
				studentElement.appendChild(description);
				root.appendChild(studentElement);
			}

			document.appendChild(root);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return document;
	}

	private void convertDocumentToXmlFile(Document document, String filePath) {
		TransformerFactory factory = TransformerFactory.newInstance();
		try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
			Transformer transformer = factory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(document), new StreamResult(fileOutputStream));
			fileOutputStream.flush();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private UserRepository convertDocumentToUserRepository(Document document) {

		UserRepository userRepository = new UserRepository();

		if (document == null) {
			return userRepository;
		}

		Map<Integer, User> userMap = new HashMap<Integer, User>();

		AtomicInteger idCounter = new AtomicInteger(
				Integer.parseInt(document.getDocumentElement().getAttribute("idCounter")));
		UserRepository.setIdCounter(idCounter);
		NodeList nodeList = document.getElementsByTagName("user");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			User user = new User();
			if (node.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) node;
				user.setId(Integer.parseInt(element.getAttribute("id")));

				NodeList nodeListUserName = element.getElementsByTagName("userName");
				Node nodeUserName = nodeListUserName.item(0);
				user.setUserName(nodeUserName.getTextContent());

				NodeList nodeListUserPassword = element.getElementsByTagName("userPassword");
				Node nodeUserPassword = nodeListUserPassword.item(0);
				user.setUserPassword(nodeUserPassword.getTextContent());

				NodeList nodeListUserRole = element.getElementsByTagName("userRole");
				Node nodeUserRole = nodeListUserRole.item(0);
				user.setUserRole(UserRole.valueOf(nodeUserRole.getTextContent()));
			}
			userMap.put(user.getId(), user);
		}

		userRepository.getUserMap().putAll(userMap);

		return userRepository;
	}

	private Document convertUserRepositoryToDocument(UserRepository userRepository) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document document = null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.newDocument();
			Element root = document.createElement("users");
			root.setAttribute("idCounter", UserRepository.getIdCounter().toString());

			for (User user : userRepository.getUserMap().values()) {

				Element userElement = document.createElement("user");
				userElement.setAttribute("id", user.getId().toString());

				Element userName = document.createElement("userName");
				Text userNameText = document.createTextNode(user.getUserName());
				userName.appendChild(userNameText);

				Element userPassword = document.createElement("userPassword");
				Text userPasswordText = document.createTextNode(user.getUserPassword());
				userPassword.appendChild(userPasswordText);

				Element userRole = document.createElement("userRole");
				Text userRoleText = document.createTextNode(user.getUserRole().toString());
				userRole.appendChild(userRoleText);

				userElement.appendChild(userName);
				userElement.appendChild(userPassword);
				userElement.appendChild(userRole);

				root.appendChild(userElement);
			}

			document.appendChild(root);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return document;
	}

}
