# Quero Quero Systems
## Codebrain Challenge

Guide for consumption of services::

The software presents a Rest API, so services must be produced and consumed in JSON format through HTTP verbs.

The base address for endpoints is: localhost:8080/rest/score/*

### VENDEDOR
To send data to the Rest API all messages must be in JSON format.

**Create** *(@POST)*
- Address: localhost:8080/rest/score/vendedor
- Sample file:
```
{"matricula": "0", "nome"="José"}
```
- The 'matricula' will be ignored in the registration, and a number will be added automatically by the SGBD.

**Update** *(@PUT)*
- Address: localhost:8080/rest/score/vendedor
- Sample file:
```
{"matricula": "4", "nome"="José"}
```
- The 'matricula' number must be correct, otherwise the update will not be performed.

**Delete** *(@DELETE)
- Address: localhost:8080/rest/score/vendedor
- sample file: 
```
{"matricula": "4", "nome"="sera ignorado"}
```
- The name field must be present but will not be considered for seller removal. 

**Search a seller by 'matricula' number** *(@GET)
- Address: localhost:8080/rest/score/vendedor/{id}
- Request sample: localhost:8080/rest/score/vendedor/3
- Response sample:
```
{"matricula": "3", "nome"="Marta"}
```

**List all sellers** *(@GET)
- Endereço: localhost:8080/rest/score/vendedor/todos
- Será devolvida uma lista contendo todos os vendedores cadastrados na base de dados.
- Exemplo de arquivo: 
```
[{"matricula": "1", "nome"="Marta"}, {"matricula": "2", "nome"="Joana"}]
```

**List all sellers by sales quantity** *(@GET)
- Address: localhost:8080/rest/score/vendedor/maiorqnt
- A list containing all sellers registered in the database will be returned..
- Sample file: 
```
[{"matricula": "1", "nome"="Marta"}, {"matricula": "2", "nome"="Joana"}]
```

**List all sellers by total sales amount** *(@GET)
- Address: localhost:8080/rest/score/vendedor/maiorvalor
- A list containing all sellers registered in the database will be returned (based on criteria).
- Sample file:
```
[{"matricula": "1", "nome"="Marta"}, {"matricula": "2", "nome"="Joana"}]
```

### Product:

**List all** *(@GET)
- Address: localhost:8080/rest/score/produto
- A list containing all products registered in the database will be returned.
- Sample file:
```
[{"id": "1", "nome"="Ventilador", "preco": "200.0"}, {"id": "2", "nome"="Televisão", "preco":"1500.0"}]
```

**Create** *(@POST)
- Address: localhost:8080/rest/score/produto
- Input file sample (JSON):
```
{"id": "0", "nome"="Ventilador", "preco": "200.0"}
```
- The id field must contain an integer value, but the API does not consider this field, as the correct value will be provided by the DBMS.

**Update** *(@PUT)
- Address: localhost:8080/rest/score/produto
- Input file sample (JSON):
```
{"id": "1", "nome"="Ventilador", "preco": "200.0"}
```

**Delete** *(@POST)
- Address: localhost:8080/rest/score/produto
- Input file sample (JSON):
```
{"id": "1", "nome"="Ventilador", "preco": "200.0"}
```
- The name and price fields must be present and filled in, but they are not considered by the API.

### Sale:

**Create** *(@POST)
- Address: localhost:8080/rest/score/venda
- Input file sample (JSON):
```
{"id": 0, "matricula": 11, "produtos": [6, 6, 6, 5]}
```
- The id field must to contain an integer value, however the API does not consider the value contained in this field.
- The 'matricula' field refers to the seller's identification.
- The 'products' field is of the array type. The values of this array are product id's.

**Get list of best selling products** *(@GET)
- Address: localhost:8080/rest/score/venda/maisvendidos
- Response sample:
```
[{"id": "1", "nome"="Ventilador", "preco": "200.0"}, {"id": "2", "nome"="Televisão", "preco":"1500.0"}]
```

## Configurations:
In this section we present the tools used in development and the essential settings for replication of the development environment.
Three softwares need to be mentioned: Eclipse, Tomcat 10 e MySQL.
The only software that needs settings for the application to work correctly is MySQL (see the subsection below).
In the case of using Tomcat, we have two options, create a server associated with the Eclipse IDE (used for development), or use a Tomcat 10 server already present on the machine. In the second scenario, it will be necessary to build the application generating the WAR and then copy the generated WAR to the respective Tomcat folder.

### MySQL:
The MySQL version used was 8.0.27.
The user and password for accessing the database can be changed in the Repository.java (Application) file. If you want to keep the code unchanged, you will need to create a MySQL username and password with the following informations:

**User**: queroquero
**Password**: queroquero

In the folder (/assets/dumps) you will find the SchemaAndTables.sql file for creating and inserting some sample data used in tests.

## Alternative modeling

### Diagram
The diagram below presents a simplified structure for application servers. The purpose of the mapping illustrated in the diagram is the scalable service of requests. The system has a base server used to attend CRUD requests and servers with microservices dedicated to the attendance of statistical requests (high number of requests).

![Simplified structure for scalable high load system](/assets/images/infra.svg)

To exemplify the processing power of the structure above, suppose that each server is capable of serving 5000 requests per minute (300000/hour). Assuming then that the system needs to serve 80000 requests per minute, it would be enough to automatically increase (use of expandable cloud systems, for example Amazon Cloud) the number of servers (16 servers in this scenario). These servers would exclusively serve statistical requests (specific endpoints).

### Application Modeling Options
**Endpoints and classes in Portuguese, but methods and comments in English**
Normally a default language is adopted, but there were doubts regarding the end user and frontend development team, so it was chosen that certain (most visible) parts would be written in Portuguese, while more hidden details would be in English.

**Implementing database queries using native Java options**
The simplest alternative used in this case would be the combination of Jersey and Hibernate. Hibernate is a framework for mapping between objects (java) and relational database tables. With the use of Hibernate the CRUD task would be easier, as it would be enough to configure and create files (hbm.xml) or annotations on the objects that should be saved. Hibernate was not chosen beacause the aim was to demonstrate knowledge of the principles of CRUD operations, without the aid of facilitators tools.

## Occasional errors:
In this section, some errors found during development were mapped, some of them can happen when the project is imported.

### Eclipse
**HttpServlet**
If the project is added and the HttpServlet error is shown in the index.jsp file, just click on the project with the right button: Properties->Targeted Runtimes. In the side window select the apache-tomcat server, then click apply.
