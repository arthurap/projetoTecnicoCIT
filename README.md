Documentação Caso Técnico CI&T – Sistema Logística

Autor: Arthur Arico Pedro

Aspectos Técnicos
IDE Usada: Eclipse Kepler
Java: JDK1.7
Servidor de Aplicação: Apache Tomcat 7.0
Banco de Dados Utilizado: Mysql 5.6
Web Service: JAX-WS
Acesso a banco de dados: Acesso feito via data source. Data Source definido no arquivo “spring.xml”. Ao modificar o banco de dados para testes, é necessário modificar esse arquivo e também acrescentar o conector do banco de dados nas dependências do POM
Demais Bibliotecas: Demais bibliotecas descritas no arquivo pom.xml do projeto
Banco de Dados:
Foi criada uma tabela do banco de dados, cujo nome é “Dados” essa tabela tem três campos: Ponto Origem (String), Ponto Destino (String) e Distancia (Double). Onde a primary key é uma chave composta de Ponto Origem e Ponto Destino. 
Arquitetura Usada
View
Foi o framework JAX-SW para construir o Web Service da aplicação. A escolha por esse framework ocorreu devido a facilidade de uso do mesmo e também a compatibilidade com qualquer aplicação Java.
Transaction
Foi usado o framework spring transaction para realizar a comunicação entre a parte “View” e a parte “Negócio” do sistema. Foi usado esse framework devido a facilidade de implantação e também a facilidade de seu uso
Negócio
Para a camada de negócio foi usada uma classe de Use Case (implementação regra de negócio) e também uma classe DAO para comunicação com o banco de dados. A classe DAO de comunicação com o banco de dados foi feita usando Hibernante

Descrição do Sistema
Foi criada apena um webservice chamado “ReceiveTransportDataService”. Esse serviço tem dois métodos: “recebeDadosLogistica” e “retornaMenorCaminho”

RecebeDadosLogistica:
O método recebeDadosLogistica recebe os dados de logística para serem inseridos no banco de dados. O Request do método é um objeto com uma String, contendo o nome do mapa e uma lista de Dados Logística. O objeto Dados logística tem três campos: Ponto origem (String), Ponto Destino (String), Distancia (Double). Todos os campos são obrigatórios pela interface
Ao receber o request, o sistema chama o método “insereDados” da classe DadosManager (método implementado na classe DadosMaganerImpl). Esse método serve como transação entre a camada view e a camada de negócio. Assim, ele chama o método insereDados da classe DadosUC.
O método insere dados converte os dados da requisição em Dados da entidade “Dados” do sistema. Essa entidade “Dados” é um espelho da tabela “Dados” no banco de dados.
Após criar uma lista da entidade “Dados”, o sistema insere todos os valores da requisição no banco, usando o método saveOrUpdate, garantindo assim que a tupla “pontoOrigem” e “pontoDestino” sempre tenha apenas um elemento no banco de dados. E se já existir um elemento assim no banco, o sistema irá apenas atualizar o campo distância.
Se não houver nenhum erro, o sistema irá responder retornar um objeto ReceiveLogisticDataResponse com o status “OK” e sem mensagem de erro. Caso haja qualquer erro, o sistema irá retornar um objeto ReceiveLogisticDataResponse com status NOK e com a mensagem de erro com o erro apresentado
RetornaMenorCaminho
O método retornaMenorCaminho retorna o custo para o menor caminho a partir dos dados enviado no request. O request é um objeto com quatro campos: Ponto Origem(String), Ponto Destino (String), Autotomia (Double), Preço Combustível (Double)
Ao receber o reques, o método chama o método buscaMenorCaminho da classe DadosManager (método implementado na classe DadosMaganerImpl). Esse método serve como transação entre a camada view e a camada de negócio. Assim, ele chama o método buscaMenorCaminho da classe DadosUC.
O método buscaMenorCaminho busca todos os dados do banco de dados para a tabela “Dados” (necessário buscar todos os dados, pois não há como saber, de antemão, todas as rotas possíveis).
Após essa busca, agrupamos os elementos da tabela Dados de modo que seja possível utilizar o algoritmo de “Dijkstra” para buscar a menor distância entre dois pontos.
Após aplicarmos o algoritmo de Dijkstra, conseguimos obter a menor distância entre dois pontos. Essa distância é usada para calcular o custo. A fórmula do cálculo do custo é: (distancia * preço gasolina) /autonomia.
A menor rota também é fornecida pelo algoritmo de “Dijkstra”.
Após obtermos essas informações, colocamos o custo e a rota no objeto ReturnShortestPathResponse, com o status de OK. Caso haja qualquer erro, o sistema irá retornar um objeto ReturnShortestPathResponse com status NOK e com a mensagem de erro com o erro apresentado.

Considerações
O algoritmo de busca de menor caminho já um algoritmo conhecido na literatura, portanto não há necessidade de se reinventar a roda e fazer um novo algoritmo, sendo mais prático usar um algoritmo já comprovadamente eficiente.
Devido a falta de tempo para fazer o projeto, não foi possível criar classes de tratamento de Log e tratamento de exceção para o projeto.
Não foi gravada no banco a informação do nome do mapa, pois essa informação não é usada em nenhuma parte da aplicação.
Testes
Os testes foram executados usando a ferramenta SOAP UI. Prints dos testes presentes na documentação enviada por e-mail

Build/Implantação
Para compilar o código e gerar o arquivo “WAR” da aplicação, é necessário, na pasta raiz do projeto (mesma pasta que contém ao arquivo “pom.xml”) executar o comando mvn clean install (necessário ter o maven instalado)
Após a execução desse comando, será gerado ao arquivo “ReceiveTransportDataService.war” na pasta target.
Para que executar a aplicação é necessário incluir o arquivo “WAR” em um servidor de aplicação para que seja feito o deploy do mesmo. Após o deploy do arquivo, é possível verificar o WSDL do webservice no endereço: http://endereco_servidor:portaServidor/ReceiveTransportDataService/receiveTransportDataService?WSDL
O endpoint da aplicação é: http://endereco_servidor:portaServidor/ReceiveTransportDataService/receiveTransportDataService
Com esses dados é possível enviar arquivos para inserção no banco e também buscar o menor custo entre uma origem e um destino.

