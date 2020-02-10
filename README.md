# weatherdemo-backend

Projeto back-end Java utilizando framework Spring afim de consumir APIs OpenWeatherMap.

# Pré-requisitos

```
OpenJDK 11
```

```
Apache Maven 3.6.3
```

```
Postgres 11
```

# Como utilizar

- Importar o projeto em sua IDE, criar novo runner como main class br.com.lucasfuck.weatherdemo.WeatherdemoApplication.
- Definir as configurações com o banco de dados no arquivo /weatherdemo/src/main/resources/application.properties.
- Rodar, aguardar scripts do Flyway e está pronto para receber requisições.

# Sobre o projeto

- Projeto criado a partir do Spring Initializr.

- Criado dois RestControllers responsáveis por gerenciar as chamadas REST.
	- Os dois controllers apenas recebem as requisições e chamam os respectivos services injetados, retornando então, seja um HttpResponse ou entidades a listar

- Para a entidade Cidade, existe o seu Service e Repository.
	- O RestController injeta o Service, e o Service injeta o Repository.

- Toda implementação/regras/validação são feitos nos respectivos Services.

- Importado biblioteca externa da OWM afim de facilitar as chamadas as suas APIs, no qual já está definido o que está disponível para uma key gratuito ou paga.

- Os scripts de migração se encontram na pasta default do Flyway, /weatherdemo/src/main/resources/db/migration.

- Há uma limitação intencional de campos na entidade Cidade e de países a ser utilizados. BR, UK ou USA.
	- Isso se deve ao fato de que, apesar da OWM oferecer suporte a pesquisa por ZIP code, o mesmo funciona por default para USA. Se eu permitir utilizar ZIP code, deveria então obrigar informar o país, depois criando um Converter para a classe Country exigida afim de validar o que for informado.
	- O estado também foi deixado de lado por estar disponível apenas para USA.

- Utilizando biblioteca do Apache Commons.

- Foi criado um teste unitário afim de testar inclusão de cidade inválida.

