/*
*Создайте клиент-серверное приложение “Архив”.
*Общие требования к заданию:
*• В архиве хранятся Дела (например, студентов). Архив находится на сервере.
*• Клиент, в зависимости от прав, может запросить дело на просмотр, внести в него изменения, или создать новое дело.
*Требования к коду лабораторной работы:
*• Для реализации сетевого соединения используйте сокеты.
*• Формат хранения данных на сервере – xml-файлы.
*/

package by.epam.jonline_introduction.part06.task03_client.main;

import by.epam.jonline_introduction.part06.task03_client.view.StudentView;

public class MainClient {

	public static void main(String[] args) {

		StudentView view = new StudentView();
		view.show();

	}

}
