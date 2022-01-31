package by.epam.jonline_introduction.part06.task03_server.dao;

import by.epam.jonline_introduction.part06.task03_server.bean.StudentRepository;

public interface StudentDAO {

	StudentRepository loadStudentRepository();

	void saveStudentRepository();

}
