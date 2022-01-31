package by.epam.jonline_introduction.part06.task03_client.converter;

import by.epam.jonline_introduction.part06.task03_client.converter.impl.ClientXmlConverterImpl;

public final class ConverterProvider {

	private static final ConverterProvider instance = new ConverterProvider();
	private final ClientXmlConverter converter = new ClientXmlConverterImpl();

	private ConverterProvider() {
	}

	public static ConverterProvider getInstance() {
		return instance;
	}

	public ClientXmlConverter getConverter() {
		return converter;
	}

}
