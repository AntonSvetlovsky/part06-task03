package by.epam.jonline_introduction.part06.task03_client.converter.impl;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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

import by.epam.jonline_introduction.part06.task03_client.bean.Student;
import by.epam.jonline_introduction.part06.task03_client.converter.ClientXmlConverter;

public class ClientXmlConverterImpl implements ClientXmlConverter {

	@Override
	public Map<Integer, Student> convertXmlStringToObjectMap(String xmlStr) {

		return convertDocumentToObjectMap(convertXmlStringToDocument(xmlStr));
	}

	@Override
	public String convertObjectToXmlString(Student student) {

		return convertDocumentToXmlString(convertObjectToDocument(student));
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

	private Map<Integer, Student> convertDocumentToObjectMap(Document document) {

		Map<Integer, Student> map = new HashMap<Integer, Student>();

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
			map.put(student.getId(), student);
		}

		return map;
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

	private Document convertObjectToDocument(Student student) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document document = null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.newDocument();
			Element root = document.createElement("students");

			Element studentElement = document.createElement("student");
			if (student.getId() != null) {
				studentElement.setAttribute("id", student.getId().toString());
			}
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
			document.appendChild(root);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return document;
	}
}
