Sobre a aplicação:
   - Nesta aplicação se tem um CRUD de cidade. Onde é possível se obter a previsão do tempo.
   - Para acessar a API basta colocar "localhost:8100/city". Obs: A porta é possíve configurar em "src/main/resources/application.properties".
   - Utilizado no Front-end o Single Page.

Utilizado no back-end:
   - Spring boot;
      * Facilitar a criação da aplicação.
   - Lombok;
      * Deixar o código mais limpo.
   - JUnit 4;
      * Realizar os testes da API.
   - Mysql;
      * Para pode conectar ao DB.
   - Flyway;
      * Controlar o versionamento do banco.
   - Gson;
      * Conversor tanto de texto para JSON quanto JSON para texto;

O que precisa ser feito para rodar:
   - Necessário ter o java instalado.
   - Instalar o Lombok:
      * Pricisa ir até a pasta do repositorio e executar o jar do lombok para instalar;
   - Ter um banco de dados do MySQL;
   - Configurar as informações do DB no arquivo "src/main/resources/application.properties".
   - Rodar o seguinte código para criação do DB:
      * CREATE DATABASE `hbsis` CHARSET = UTF8 COLLATE = utf8_swedish_ci;

Utilizado no front-end:
   - AngularJS;
      * Para ter mais produtividade.
   - BootStrap;
      * Auxiliar na criação de telas responsivas.
   - Font Awesome;
      * Utilizado para obter os ícones, pois BootStrap não utiliza mais Glyphicons.
   - jQuery;
      * Utilizado no BootStrap.

O que precisa ser feito para rodar:
   - Necessário utilizar servidor http. 
   - Se for mudado a porta do back-end deve ser configurado o arquivo "frontend\constant\configValue.js".