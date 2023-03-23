Download project: git clone https://github.com/ShcherbaAlex/pstest-dev2.git
Go to the project folder and run the tests with the command: mvn clean test
Run report generation with the command: mvn allure:serve

Проект - сервлет с 2-мя методами:

Добавить клиента POST http://localhost:8088/pstest/customers
Body для добавления можно составить из public class Customer
Получить клиента GET http://localhost:8088/pstest/customers?customerId={customerId}


Тест класс SomeServletTest.
В тест классе уже есть метод старта web сервера, требуется написать реализацию 2 тестов  на Post и Get (добавлять еще тесты не запрещается).