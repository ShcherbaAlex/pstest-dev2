Проект - сервлет с 2-мя методами:

Добавить клиента POST http://localhost:8088/pstest/customers
Body для добавления можно составить из public class Customer
Получить клиента GET http://localhost:8088/pstest/customers?customerId={customerId}


Тест класс SomeServletTest.
В тест классе уже есть метод старта web сервера, требуется написать реализацию 2 тестов  на Post и Get (добавлять еще тесты не запрещается).