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

### Tomcat 10:

### MySQL:

### Eclipse:





### Erros eventuais mapeados:

Caso o pojeto seja adicionado e o erro HttpServlet seja mostrado no arquivo index.jsp, basta clicar sobre o projeto com o botão direito: Properties->Targeted Runtimes. Na janela lateral selecione o servidor apache-tomcat.
