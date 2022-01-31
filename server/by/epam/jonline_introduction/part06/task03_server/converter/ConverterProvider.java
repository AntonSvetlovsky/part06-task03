package by.epam.jonline_introduction.part06.task03_server.converter;

import by.epam.jonline_introduction.part06.task03_server.converter.impl.ServerXmlConverterImpl;

public final class ConverterProvider {

	private static final ConverterProvider instance = new ConverterProvider();
	private final ServerXmlConverter converter = new ServerXmlConverterImpl();

	private ConverterProvider() {
	}

	public static ConverterProvider getInstance() {
		return instance;
	}

	public ServerXmlConverter getConverter() {
		return converter;
	}

}
