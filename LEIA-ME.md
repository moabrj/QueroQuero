# Quero Quero Systems
## Codebrain Challenge

Guia para consumo de serviços:

O software apresenta uma API Rest, logo, os serviços devem ser produzidos e consumidos em formato JSON atraves de verbos HTTP.

O endereço base para os endpoints é: localhost:8080/rest/score/*

### VENDEDOR
Para enviar dados para a API Rest todas as mensagens devem estar no formato JSON.

**Cadastrar** *(@POST)*
- Endereço: localhost:8080/rest/score/vendedor
- Exemplo de arquivo {"matricula": "0", "nome"="José"}
- A matrícula será ingnorada no cadastro, sendo adicionada um número autômaticamente pelo SGBD.

**Atualizar** *(@PUT)*
- Endereço: localhost:8080/rest/score/vendedor
- Exemplo de arquivo {"matricula": "4", "nome"="José"}
- O número de matrícula deve estar correto, caso contrário não será realizada a atualização

**Deletar** *(@DELETE)
- Endereço: localhost:8080/rest/score/vendedor
- Exemplo de arquivo: 
```
{"matricula": "4", "nome"="sera ignorado"}
```
- O campo de nome deve estar presente, mas não será considerado para remoção do vendedor 

**Buscar um vendedor por número de matrícula** *(@GET)
- Endereço: localhost:8080/rest/score/vendedor/{id}
- Exemplo request: localhost:8080/rest/score/vendedor/3
- Exemplo response: {"matricula": "3", "nome"="Marta"}

**Listar todos os vendedores** *(@GET)
- Endereço: localhost:8080/rest/score/vendedor/todos
- Será devolvida uma lista contendo todos os vendedores cadastrados na base de dados.
- Exemplo de arquivo: 
```
[{"matricula": "1", "nome"="Marta"}, {"matricula": "2", "nome"="Joana"}]
```

**Listar todos os vendedores por quantidade de vendas** *(@GET)
- Endereço: localhost:8080/rest/score/vendedor/maiorqnt
- Será devolvida uma lista contendo todos os vendedores cadastrados na base de dados.
- Exemplo de arquivo: 
```
[{"matricula": "1", "nome"="Marta"}, {"matricula": "2", "nome"="Joana"}]
```

**Listar todos os vendedores por valor total em vendas** *(@GET)
- Endereço: localhost:8080/rest/score/vendedor/maiorvalor
- Será devolvida uma lista contendo todos os vendedores cadastrados na base de dados.
- Exemplo de arquivo:
```
[{"matricula": "1", "nome"="Marta"}, {"matricula": "2", "nome"="Joana"}]
```

### Produto:

**Listar todos os produtos** *(@GET)
- Endereço: localhost:8080/rest/score/produto
- Será devolvida uma lista contendo todos os produtos cadastrados na base de dados.
- Exemplo de arquivo:
```
[{"id": "1", "nome"="Ventilador", "preco": "200.0"}, {"id": "2", "nome"="Televisão", "preco":"1500.0"}]
```

**Cadastrar um novo produto** *(@POST)
- Endereço: localhost:8080/rest/score/produto
- Exemplo de arquivo de entrada (JSON):
```
{"id": "0", "nome"="Ventilador", "preco": "200.0"}
```
- O campo id devem conter um valor inteiro, porém a API não considera este campo, pois o valor correto será fornecido pelo SGBD.

**Atualizar um produto** *(@PUT)
- Endereço: localhost:8080/rest/score/produto
- Exemplo de arquivo de entrada (JSON):
```
{"id": "1", "nome"="Ventilador", "preco": "200.0"}
```

**Remover um produto** *(@POST)
- Endereço: localhost:8080/rest/score/produto
- Exemplo de arquivo de entrada (JSON):
```
{"id": "1", "nome"="Ventilador", "preco": "200.0"}
```
- Os campos nome e preço devem estar presentes e preenchidos, poré não são considerados pela API.

### Venda:

**Cadastrar uma nova venda** *(@POST)
- Endereço: localhost:8080/rest/score/venda
- Exemplo de arquivo de entrada (JSON):
```
{"id": 0, "matricula": 11, "produtos": [6, 6, 6, 5]}
```
- O campo id pode ser deve conter um valor inteiro, porém a API não considera o valor contido neste campo.
- O campo matrícula faz referência a identificação do vendedor.
- O campo 'produtos' é do tipo array. Os valores deste array são id's de produtos.

**Obter lista dos produtos mais vendidos** *(@GET)
- Endereço: localhost:8080/rest/score/venda/maisvendidos
- Exemplo de respota:
```
[{"id": "1", "nome"="Ventilador", "preco": "200.0"}, {"id": "2", "nome"="Televisão", "preco":"1500.0"}]
```

## Configurações:
Nesta seção apresentamos as ferramentas utilizadas no desenvolvimento e as configurações essenciais para replicação do ambiente de desenvolvimento.
Três softwares precisam ser mencionados: Eclipse, Tomcat 10 e MySQL.
O único software que necessita de configurações para correto funcionamento da aplicação é o MySQL (veja na subseção abaixo).
No caso de utilização do Tomcat, temos duas opções, criar um servidor associado a IDE Eclipse (usado para desenvolvimento), ou utilizar um servidor Tomcat 10 já presente na máquina. No segundo cenário, será necessário realizar a build da aplicação gerando o WAR e em seguida copiar o WAR gerado para a pasta respectiva do Tomcat.

### MySQL:
A versão utilizada do MySQL foi a 8.0.27.
O usuário e senha para acesso ao banco de dados podem ser alterados no aquivo Reposity.java (Aplicação). Caso você deseje manter o código inalterado, será necessário a criação de um usuário e senha no MySQL com a seguintes informações:

**Usuário**: queroquero
**Senha**: queroquero

Na pasta (/assets/dumps) você encontrará o arquivo SchemaAndTables.sql para criação e inserção de alguns dados fictícios utilizados em testes.

## Modelagem alternativa

### Diagrama
O diagrama abaixo apresenta uma estrutura simplifica para servidores de aplicação. O objetivo do mapeamento ilustrado no diagrama é o atendimento escalável de requisições, isto é, o sistema possui um servidor base utilizado para atender requsições de CRUD e servidores com microserviços dedicados ao atendimento de solicitações de estatisticas (alto número de requsições).

![Estrutura simplificada para sistema de alta carga escalável](/assets/images/infra.svg)

Para exemplificar o poder de processamento da estrutura acima, suponha que cada servidor seja capaz de atender 5000 requisições por minuto (300000/hora). Imaginando então que o sistema necessita atender 80000 requisições por minuto, bastaria aumentar de forma automatizada (uso de sistemas cloud expansíveis, exemplo Amazon Cloud) o número de servidores. Estes servidores atenderiam exclusivamente a requisições de estatisticas (endpoints especificos).

### Opções de modelagem da Aplicação
**Endpoints e classes em português, mas métodos e comentários em inglês**
Normalmente é adotado um idioma padrão, mas houve dúvidas com relação ao usuário final e equipe de desenvolvimento do frontend, então foi escolhido que certas partes (mais visíveis) seriam escritas em português, enquanto detalhes mais ocultos ficariam em inglês.

**Implementação de consultas ao banco de dados usando opções nativas do Java**
A alternativa mais simples e utilizada neste caso seria a combinação do Jersey e Hibernate. O Hibernate é um framework para mapeamento entre objetos (java) e tabelas de bases de dados relacionais. Com o uso de HIbernate a tarefa de CRUD seria facilitada, pois bastaria configurar e criar arquivos (hbm.xml) ou anotações nos objetos que deveriam ser salvos. O Hibernate não foi escolhido, pois o objetivo era demonstrar o conhecimento dos princípios das operações CRUD sem auxílio de ferramentas facilitadoras.

## Erros eventuais mapeados:
Nesta seção são mapeados alguns erros encontrados durante o desenvolvimento e que podem acontecer em caso de importação do projeto.

### Eclipse
**HttpServlet**
Caso o pojeto seja adicionado e o erro HttpServlet seja mostrado no arquivo index.jsp, basta clicar sobre o projeto com o botão direito: Properties->Targeted Runtimes. Na janela lateral selecione o servidor apache-tomcat.

