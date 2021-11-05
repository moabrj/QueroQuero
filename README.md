# Quero Quero Systems
Codebrain Challenge

Guia para consumo de serviços:

O software apresenta uma API Rest, logo, os serviços devem ser produzidos e consumidos em formato JSON atraves de verbos HTTP.

O endereço base para os endpoints é: localhost:8080/rest/score/*

VENDEDOR
Para enviar dados para a API Rest todas as mensagens devem estar no formato JSON.
Cadastrar (@POST)
- Endereço: localhost:8080/rest/score/vendedor
- Exemplo de arquivo {"matricula": "0", "nome"="José"}
- A matrícula será ingnorada no cadastro, sendo adicionada um número autômaticamente pelo SGBD.
Atualizar (@PUT)
- Endereço: localhost:8080/rest/score/vendedor
- Exemplo de arquivo {"matricula": "4", "nome"="José"}
- O número de matrícula deve estar correto, caso contrário não será realizada a atualização
Deletar (@DELETE)
- Endereço: localhost:8080/rest/score/vendedor
- Exemplo de arquivo {"matricula": "4", "nome"="sera ignorado"}
- O campo de nome deve estar presente, mas não será considerado para remoção do vendedor 
Buscar um vendedor por número de matrícula
- Endereço: localhost:8080/rest/score/vendedor/{id}
- Exemplo request: localhost:8080/rest/score/vendedor/3
- Exemplo response: {"matricula": "3", "nome"="Marta"}
Listar todos os vendedores (@GET)
- Endereço: localhost:8080/rest/score/vendedor/todos
- Será devolvida uma lista contendo todos os vendedores cadastrados na base de dados.
- Exemplo de arquivo [{"matricula": "1", "nome"="Marta"}, {"matricula": "2", "nome"="Joana"}]
Listar todos os vendedores por quantidade de vendas
- Endereço: localhost:8080/rest/score/vendedor/maiorqnt
- Será devolvida uma lista contendo todos os vendedores cadastrados na base de dados.
- Exemplo de arquivo [{"matricula": "1", "nome"="Marta"}, {"matricula": "2", "nome"="Joana"}]
Listar todos os vendedores por valor total em vendas
- Endereço: localhost:8080/rest/score/vendedor/maiorvalor
- Será devolvida uma lista contendo todos os vendedores cadastrados na base de dados.
- Exemplo de arquivo [{"matricula": "1", "nome"="Marta"}, {"matricula": "2", "nome"="Joana"}]

Produto:

Venda:


Configurações:

Tomcat 10:

MySQL:

Eclipse:





Observações importantes:

Caso o pojeto seja adicionado e o erro HttpServlet seja mostrado no arquivo index.jsp, basta clicar sobre o projeto com o botão direito: Properties->Targeted Runtimes. Na janela lateral selecione o servidor apache-tomcat.
