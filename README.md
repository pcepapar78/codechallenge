# Bank - Microservice

Projecto demo de Microservicio para alta y consulta de transacciones de banco

# Entorno de Desarrollo

* [JDK 1.8 ](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)

* [Spring Boot (Spring Data) Release 2.1.9](https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/htmlsingle/)

* [Swagger 2.4.0](https://swagger.io/)

* [H2 Database](https://www.h2database.com/html/main.html)

* [Maven](https://maven.apache.org/guides/index.html)


# Arranque de aplicación

1.- Desde linea de comando:

	java -jar bank\target\bank-0.0.1-SNAPSHOT.jar
	
2.- Desde eclipse:

	Application.java > Run As > Java Aplicacion
	
### Interfaz de usuario (test de servicios)

* [swagger-ui](http://localhost:8080/swagger-ui.html)

### Base de datos h2

* [datebase - h2](http://localhost:8080/h2-console)

```
Database: bank
User/Password: demo
```
> Los ficheros schema.sql y data.sql contienen las sentecias SQL para la creación de la tabla TRANSACTIONS y 10 registros que se dan de alta al ejecutar la aplicación.

### Manejo de transacciones

> Desde el interfaz Swagger-UI se realizaran las operaciones de testeo sobre la tabla TRANSACTIONS.

> A continuación se detallan todas las operaciones que se pueden procesar:

* [Create transaction / nueva transación](http://localhost:8080/swagger-ui.html#!/transaction-controller/createTransaction)
* [Search transactions / busqueda de transaciones](http://localhost:8080/swagger-ui.html#!/transaction-controller/findAllTransactions)
* [Search transactions by account Iban / busqueda de transaciones por cuenta Iban](http://localhost:8080/swagger-ui.html#!/transaction-controller/findTransactionsByAccountIban)
* [Search transactions by account Iban and sorted amount / busqueda de transaciones por cuenta Iban y ordenado por cantidad (ASC/DESC)](http://localhost:8080/swagger-ui.html#!/transaction-controller/findTransactionsByAccountIbanSorted)
* [Transaction Status / Estado de transación](http://localhost:8080/swagger-ui.html#!/transaction-controller/getTransactionsStatus)

### Testeo de Manejo de transacciones

> Desde eclipse podemos ejecutar los test que realizaran las operaciones sobre la tabla TRANSACTIONS.

1.- Run application / arranque de aplicación

- Desde linea de comando:

	mvn -Dtest=ApplicationTest test

- Desde eclipse:

	ApplicationTest.java > Run As > JUnit Test

2.- Create transaction / nueva transación

- Desde linea de comando:

	mvn -Dtest=NewTransactionTest test

- Desde eclipse:

	NewTransactionTest.java > Run As > JUnit Test
	
* Sample / Ejemplo

`````
 ------------------------------------------------------------------------------------------------------------------------------------------------------
 New transaction:
 ------------------------------------------------------------------------------------------------------------------------------------------------------
 Transaction[reference='12345Z', accountIban='ES9820385888983000760236', date_transaction=2020-04-17 15:29:01.541, amount=300.25, fee=30.51, description='New transaction test']
 ------------------------------------------------------------------------------------------------------------------------------------------------------
`````
	
3.- Search transactions / busqueda de transaciones

- Desde linea de comando:

	mvn -Dtest=AllTransactionsTest test

- Desde eclipse:

	AllTransactionsTest.java > Run As > JUnit Test
	
* Sample / Ejemplo

`````
 ------------------------------------------------------------------------------------------------------------------------------------------------------
 All transactions:
 ------------------------------------------------------------------------------------------------------------------------------------------------------
 Transaction[reference='12345A', accountIban='ES9820385778983000760236', date_transaction=2020-04-18 15:26:21.217, amount=193.38, fee=3.18, description='Restaurant payment']
 Transaction[reference='12345B', accountIban='ES9820385778983000760236', date_transaction=2020-04-15 15:26:21.223, amount=130.73, fee=2.14, description='Electricity bil payment']
 Transaction[reference='12345C', accountIban='ES9820385778983000760236', date_transaction=2020-04-14 15:26:21.224, amount=89.91, fee=1.47, description='Community payment']
 Transaction[reference='12345D', accountIban='ES9820385778983000760236', date_transaction=2020-04-21 15:26:21.225, amount=58.52, fee=0.96, description='Water bil payment']
 Transaction[reference='12345E', accountIban='ES9820385778983000760236', date_transaction=2020-04-12 15:26:21.226, amount=355.85, fee=5.85, description='IBI Tax payment']
 Transaction[reference='12345F', accountIban='ES9820385888983000760236', date_transaction=2020-04-17 15:26:21.226, amount=67.45, fee=1.1, description='Purchase in commerce']
 Transaction[reference='12345G', accountIban='ES9820385888983000760236', date_transaction=2020-04-10 15:26:21.227, amount=36.65, fee=0.6, description='Health insurance payment']
 Transaction[reference='12345H', accountIban='ES9820385888983000760236', date_transaction=2020-04-09 15:26:21.228, amount=65.71, fee=1.08, description='Transport payment']
 Transaction[reference='12345I', accountIban='ES9820385888983000760236', date_transaction=2020-04-17 15:26:21.232, amount=22.39, fee=0.36, description='Taxi payment']
 Transaction[reference='12345J', accountIban='ES9820385888983000760236', date_transaction=2020-04-07 15:26:21.234, amount=658.88, fee=10.83, description='Travel payment']
 ------------------------------------------------------------------------------------------------------------------------------------------------------
`````
	
4.- Search transactions by account Iban / busqueda de transaciones por cuenta Iban

- Desde linea de comando:

	mvn -Dtest=AllTransactionsByAccountIbanTest test

- Desde eclipse:

	AllTransactionsByAccountIbanTest.java > Run As > JUnit Test

* Sample / Ejemplo

`````
 ------------------------------------------------------------------------------------------------------------------------------------------------------
 All transactions by Account IBAN: [ES9820385778983000760236]
 ------------------------------------------------------------------------------------------------------------------------------------------------------
 Transaction[reference='12345A', accountIban='ES9820385778983000760236', date_transaction=2020-04-18 15:24:27.143, amount=193.38, fee=3.18, description='Restaurant payment']
 Transaction[reference='12345B', accountIban='ES9820385778983000760236', date_transaction=2020-04-15 15:24:27.149, amount=130.73, fee=2.14, description='Electricity bil payment']
 Transaction[reference='12345C', accountIban='ES9820385778983000760236', date_transaction=2020-04-14 15:24:27.149, amount=89.91, fee=1.47, description='Community payment']
 Transaction[reference='12345D', accountIban='ES9820385778983000760236', date_transaction=2020-04-21 15:24:27.15, amount=58.52, fee=0.96, description='Water bil payment']
 Transaction[reference='12345E', accountIban='ES9820385778983000760236', date_transaction=2020-04-12 15:24:27.151, amount=355.85, fee=5.85, description='IBI Tax payment']
 ------------------------------------------------------------------------------------------------------------------------------------------------------
 ------------------------------------------------------------------------------------------------------------------------------------------------------
 All transactions by Account IBAN: [ES9820385888983000760236]
 ------------------------------------------------------------------------------------------------------------------------------------------------------
 Transaction[reference='12345F', accountIban='ES9820385888983000760236', date_transaction=2020-04-17 15:24:27.152, amount=67.45, fee=1.1, description='Purchase in commerce']
 Transaction[reference='12345G', accountIban='ES9820385888983000760236', date_transaction=2020-04-10 15:24:27.153, amount=36.65, fee=0.6, description='Health insurance payment']
 Transaction[reference='12345H', accountIban='ES9820385888983000760236', date_transaction=2020-04-09 15:24:27.154, amount=65.71, fee=1.08, description='Transport payment']
 Transaction[reference='12345I', accountIban='ES9820385888983000760236', date_transaction=2020-04-17 15:24:27.154, amount=22.39, fee=0.36, description='Taxi payment']
 Transaction[reference='12345J', accountIban='ES9820385888983000760236', date_transaction=2020-04-07 15:24:27.155, amount=658.88, fee=10.83, description='Travel payment']
 ------------------------------------------------------------------------------------------------------------------------------------------------------
`````
	
5.- Search transactions by account Iban and sorted amount / busqueda de transaciones por cuenta Iban y ordenado por cantidad (ASC/DESC)

- Desde linea de comando:

	mvn -Dtest=AllTransactionsByAccountIbanSortedTest test

- Desde eclipse:

	AllTransactionsByAccountIbanSortedTest.java > Run As > JUnit Test
	
* Sample / Ejemplo

`````
 ------------------------------------------------------------------------------------------------------------------------------------------------------
 All transactions by Account IBAN: [ES9820385778983000760236] sorted by amount [ASC]
 ------------------------------------------------------------------------------------------------------------------------------------------------------
 Transaction[reference='12345D', accountIban='ES9820385778983000760236', date_transaction=2020-04-21 15:20:56.075, amount=58.52, fee=0.96, description='Water bil payment']
 Transaction[reference='12345C', accountIban='ES9820385778983000760236', date_transaction=2020-04-14 15:20:56.075, amount=89.91, fee=1.47, description='Community payment']
 Transaction[reference='12345B', accountIban='ES9820385778983000760236', date_transaction=2020-04-15 15:20:56.074, amount=130.73, fee=2.14, description='Electricity bil payment']
 Transaction[reference='12345A', accountIban='ES9820385778983000760236', date_transaction=2020-04-18 15:20:56.068, amount=193.38, fee=3.18, description='Restaurant payment']
 Transaction[reference='12345E', accountIban='ES9820385778983000760236', date_transaction=2020-04-12 15:20:56.076, amount=355.85, fee=5.85, description='IBI Tax payment']
 ------------------------------------------------------------------------------------------------------------------------------------------------------
 ------------------------------------------------------------------------------------------------------------------------------------------------------
 All transactions by Account IBAN: [ES9820385778983000760236] sorted by amount [DESC]
 ------------------------------------------------------------------------------------------------------------------------------------------------------
 Transaction[reference='12345E', accountIban='ES9820385778983000760236', date_transaction=2020-04-12 15:20:56.076, amount=355.85, fee=5.85, description='IBI Tax payment']
 Transaction[reference='12345A', accountIban='ES9820385778983000760236', date_transaction=2020-04-18 15:20:56.068, amount=193.38, fee=3.18, description='Restaurant payment']
 Transaction[reference='12345B', accountIban='ES9820385778983000760236', date_transaction=2020-04-15 15:20:56.074, amount=130.73, fee=2.14, description='Electricity bil payment']
 Transaction[reference='12345C', accountIban='ES9820385778983000760236', date_transaction=2020-04-14 15:20:56.075, amount=89.91, fee=1.47, description='Community payment']
 Transaction[reference='12345D', accountIban='ES9820385778983000760236', date_transaction=2020-04-21 15:20:56.075, amount=58.52, fee=0.96, description='Water bil payment']
 ------------------------------------------------------------------------------------------------------------------------------------------------------
 ------------------------------------------------------------------------------------------------------------------------------------------------------
 All transactions by Account IBAN: [ES9820385888983000760236] sorted by amount [ASC]
 ------------------------------------------------------------------------------------------------------------------------------------------------------
 Transaction[reference='12345I', accountIban='ES9820385888983000760236', date_transaction=2020-04-17 15:20:56.08, amount=22.39, fee=0.36, description='Taxi payment']
 Transaction[reference='12345G', accountIban='ES9820385888983000760236', date_transaction=2020-04-10 15:20:56.078, amount=36.65, fee=0.6, description='Health insurance payment']
 Transaction[reference='12345H', accountIban='ES9820385888983000760236', date_transaction=2020-04-09 15:20:56.079, amount=65.71, fee=1.08, description='Transport payment']
 Transaction[reference='12345F', accountIban='ES9820385888983000760236', date_transaction=2020-04-17 15:20:56.077, amount=67.45, fee=1.1, description='Purchase in commerce']
 Transaction[reference='12345J', accountIban='ES9820385888983000760236', date_transaction=2020-04-07 15:20:56.081, amount=658.88, fee=10.83, description='Travel payment']
 ------------------------------------------------------------------------------------------------------------------------------------------------------
 ------------------------------------------------------------------------------------------------------------------------------------------------------
 All transactions by Account IBAN: [ES9820385888983000760236] sorted by amount [DESC]
 ------------------------------------------------------------------------------------------------------------------------------------------------------
 Transaction[reference='12345J', accountIban='ES9820385888983000760236', date_transaction=2020-04-07 15:20:56.081, amount=658.88, fee=10.83, description='Travel payment']
 Transaction[reference='12345F', accountIban='ES9820385888983000760236', date_transaction=2020-04-17 15:20:56.077, amount=67.45, fee=1.1, description='Purchase in commerce']
 Transaction[reference='12345H', accountIban='ES9820385888983000760236', date_transaction=2020-04-09 15:20:56.079, amount=65.71, fee=1.08, description='Transport payment']
 Transaction[reference='12345G', accountIban='ES9820385888983000760236', date_transaction=2020-04-10 15:20:56.078, amount=36.65, fee=0.6, description='Health insurance payment']
 Transaction[reference='12345I', accountIban='ES9820385888983000760236', date_transaction=2020-04-17 15:20:56.08, amount=22.39, fee=0.36, description='Taxi payment']
 ------------------------------------------------------------------------------------------------------------------------------------------------------
`````
	
5.- Transaction Status / Estado de transación

- Desde linea de comando:

	mvn -Dtest=GetTransactionStatusTest test

- Desde eclipse:

	GetTransactionStatusTest.java > Run As > JUnit Test
	
* Sample / Ejemplo

`````
Get Transaction Status: reference: [999999] channel: [ATM]
------------------------------------------------------------------------------------------------------------------------------------------------------
*******************************************************************
Response Transaction Status:
message: A transaction that is not stored in our system
reference: 999999
status: INVALID
`````

6.- Transaction Status / Estado de transación (todas las reglas de negocio)

- Desde linea de comando:

	mvn -Dtest=BussinesRulesTest test

- Desde eclipse:

	BussinesRulesTest.java > Run As > JUnit Test
	
* Sample / Ejemplo

`````
 ------------------------------------------------------------------------------------------------------------------------------
 Business Rules[C] - The system returns the status 'SETTLED' and The system returns the amount and the fee - CHANNEL: INTERNAL
 ------------------------------------------------------------------------------------------------------------------------------
 Get Transaction Status: reference: [12345G] channel: [INTERNAL]
 ------------------------------------------------------------------------------------------------------------------------------
 *******************************************************************
 Response Transaction Status: 
 message: A transaction that is stored in our system
 reference: 12345G
 status: SETTLED
 amount: 36.65
 fee: 0.6
 *******************************************************************
 ------------------------------------------------------------------------------------------------------------------------------
 Business Rules[D] - The system returns the status 'PENDING' and the amount substracting the fee - CHANNEL: CLIENT
 ------------------------------------------------------------------------------------------------------------------------------
 Get Transaction Status: reference: [12345F] channel: [CLIENT]
 ------------------------------------------------------------------------------------------------------------------------------
 *******************************************************************
 Response Transaction Status: 
 message: A transaction that is stored in our system
 reference: 12345F
 status: PENDING
 amount: 66.35000000000001
 *******************************************************************
 ------------------------------------------------------------------------------------------------------------------------------
 Business Rules[D] - The system returns the status 'PENDING' and the amount substracting the fee - CHANNEL: ATM
 ------------------------------------------------------------------------------------------------------------------------------
 Get Transaction Status: reference: [12345F] channel: [ATM]
 ------------------------------------------------------------------------------------------------------------------------------
 *******************************************************************
 Response Transaction Status: 
 message: A transaction that is stored in our system
 reference: 12345F
 status: PENDING
 amount: 66.35000000000001
 *******************************************************************
 ------------------------------------------------------------------------------------------------------------------------------
 Business Rules[E] - The system returns the status 'PENDING' and The system returns the amount and the fee - CHANNEL: INTERNAL
 ------------------------------------------------------------------------------------------------------------------------------
 Get Transaction Status: reference: [12345F] channel: [INTERNAL]
 ------------------------------------------------------------------------------------------------------------------------------
 *******************************************************************
 Response Transaction Status: 
 message: A transaction that is stored in our system
 reference: 12345F
 status: PENDING
 amount: 67.45
 fee: 1.1
 *******************************************************************
 ------------------------------------------------------------------------------------------------------------------------------
 Business Rules[F] - The system returns the status 'FUTURE' and the amount substracting the fee - CHANNEL: CLIENT
 ------------------------------------------------------------------------------------------------------------------------------
 Get Transaction Status: reference: [12345D] channel: [CLIENT]
 ------------------------------------------------------------------------------------------------------------------------------
 *******************************************************************
 Response Transaction Status: 
 message: A transaction that is stored in our system
 reference: 12345D
 status: FUTURE
 amount: 57.56
 *******************************************************************
 ------------------------------------------------------------------------------------------------------------------------------
 Business Rules[G] - The system returns the status 'PENDING' and the amount substracting the fee - CHANNEL: ATM
 ------------------------------------------------------------------------------------------------------------------------------
 Get Transaction Status: reference: [12345D] channel: [ATM]
 ------------------------------------------------------------------------------------------------------------------------------
 *******************************************************************
 Response Transaction Status: 
 message: A transaction that is stored in our system
 reference: 12345D
 status: PENDING
 amount: 57.56
 *******************************************************************
 ------------------------------------------------------------------------------------------------------------------------------
 Business Rules[H] - The system returns the status 'FUTURE' and The system returns the amount and the fee - CHANNEL: INTERNAL
 ------------------------------------------------------------------------------------------------------------------------------
 Get Transaction Status: reference: [12345D] channel: [INTERNAL]
 ------------------------------------------------------------------------------------------------------------------------------
 *******************************************************************
 Response Transaction Status: 
 message: A transaction that is stored in our system
 reference: 12345D
 status: FUTURE
 amount: 58.52
 fee: 0.96
 *******************************************************************
`````

