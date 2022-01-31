package by.epam.jonline_introduction.part06.task03_server.bean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class StudentRepository {

	private static AtomicInteger idCounter;
	private Map<Integer, Student> studentMap;

	static {
		idCounter = new AtomicInteger(0);
	}

	{
		studentMap = new ConcurrentHashMap<Integer, Student>();
	}

	public StudentRepository() {
	}

	public static AtomicInteger getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(AtomicInteger idCounter) {
		StudentRepository.idCounter = idCounter;
	}

	public Map<Integer, Student> getStudentMap() {
		return studentMap;
	}

	public void setStudentMap(Map<Integer, Student> studentMap) {
		this.studentMap = studentMap;
	}

	public void addStudent(Student student) {
		int id = idCounter.incrementAndGet();
		student.setId(Integer.valueOf(id));
		studentMap.put(Integer.valueOf(id), student);
	}

	public boolean removeStudent(Integer id) {
		if (studentMap.containsKey(id)) {
			studentMap.remove(id);
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((studentMap == null) ? 0 : studentMap.hashCode());
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
		StudentRepository other = (StudentRepository) obj;
		if (studentMap == null) {
			if (other.studentMap != null) {
				return false;
			}
		} else if (!studentMap.equals(other.studentMap)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "StudentRepository [studentMap=" + studentMap + "]";
	}

}
