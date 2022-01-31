/*
*Создайте клиент-серверное приложение “Архив”.
*Общие требования к заданию:
*• В архиве хранятся Дела (например, студентов). Архив находится на сервере.
*• Клиент, в зависимости от прав, может запросить дело на просмотр, внести в него изменения, или создать новое дело.
*Требования к коду лабораторной работы:
*• Для реализации сетевого соединения используйте сокеты.
*• Формат хранения данных на сервере – xml-файлы.
*/

package by.epam.jonline_introduction.part06.task03_server.main;

import by.epam.jonline_introduction.part06.task03_server.controller.StudentController;

public class MainServer {

	public static void main(String[] args) {

		StudentController controller = new StudentController();

		controller.go();
	}
}
