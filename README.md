# Todo List - Back-end 📃

<div align="center">
<h3 align="center">Stack</h3>
<img alt="Java v17.0.6 Badge" src="https://img.shields.io/badge/Java-17.0.6-blue">
<img alt="Apache Maven v3.9.5 Badge" src="https://img.shields.io/badge/Apache_Maven-3.9.5-blue">
</div>

#### Sobre do projeto

Este projeto possui a finalidade de disponibilizar uma API para cadastro de usuários, cadastro e edição de tarefas.
Foi elaborado durante o evento de lançamento do curso de Java da Rocketseat 🚀

#### Configurando o ambiente de desenvolvimento

O desenvolvimento foi realizado utilizando wsl2 e [VSCode](https://code.visualstudio.com/Download).

---

- Instalação do [Java](https://www.java.com/):

```shell
  sudo add-apt-repository ppa:linuxuprising/java
  sudo apt-get update
  sudo apt-get install oracle-java17-installer --install-recommends
  java -version
```

Obs: caso o Java não seja reconhecido no terminal, adicione nas variáveis (ex: bashrc ou zshrc):

```shell
  export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
```

---

- Instalação do [Maven](https://maven.apache.org/)

```shell
  sudo apt-get update
  sudo apt-get -y install maven
  mvn -version
```

Contudo, a instalação do Maven desta maneira trouxe ele na versão 3.6 e esta versão apresentou problemas de compatibilidade com o Java 17, então instalei a versão 3.9.5:

```shell
  wget https://dlcdn.apache.org/maven/maven-3/3.9.5/binaries/apache-maven-3.9.5-bin.tar.gz
  tar -xvf apache-maven-3.9.5-bin.tar.gz
  mv apache-maven-3.9.5 /opt/
```

Em seguida adicionei as variáveis "M2_HOME" e "PATH" no meu .zshrc

```shell
  #Maven variables
  M2_HOME='/opt/apache-maven-3.9.5'
  PATH="$M2_HOME/bin:$PATH"
  export PATH
```

Reinicie o terminal.

---

- Rest Client utilizado para executar os requests com Basic Auth => [apiDog](https://apidog.com/)

- [VSCode](https://code.visualstudio.com/Download) plugins:
  
  Recomendo as seguintes extensões do [VSCode](https://code.visualstudio.com/Download) para o projeto:

  - "vscjava.vscode-java-pack"
  - "vscjava.vscode-spring-initializr"
  - "vmware.vscode-spring-boot"
  - "vscjava.vscode-spring-boot-dashboard"

---

#### Executando a aplicação

- Execute no terminal

```shell
  mvn spring-boot:run
```

- Base path local => "<http://localhost:8080>"

- Endereço para acessar o banco de dados em memória H2 => "<http://localhost:8080/h2-console>"
- Cadastrar usuário => POST - "/users"
  - Body

```JSON
{
  "name": String;
  "password": String;
  "userName": String;
  "email": String;
}
```

- Cadastrar tarefa => POST - "/tasks"
  - Necessário enviar header de Authorization do tipo Basic Auth com as credenciais válidas de um usuário cadastrado.
  - Body

```JSON
{
  "description": String;
  "endAt": String;
  "priority": String;
  "startAt": String;
  "title": String;
}
```

- Buscar lista de tarefas cadastradas => GET - "/tasks"
  - Necessário enviar header de Authorization do tipo Basic Auth com as credenciais válidas de um usuário cadastrado.
- Editar tarefa => PUT - "/tasks/:id"
  - Necessário enviar header de Authorization do tipo Basic Auth com as credenciais válidas de um usuário cadastrado.
  - Body - (Campos opcionais)

```JSON
{
  "description": String;
  "endAt": String;
  "priority": String;
  "startAt": String;
  "title": String;
}
```

#### Subir aplicação com docker

Caso queira executar a API sem instalar os pré-requisitos para rodar um ambiente Java, acesse a raiz do projeto e execute no terminal:

```shell
  ./run-app.sh
```

<div align="center">
  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt="java" />
  <img src="https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white" alt="java" />
  <img src="https://img.shields.io/badge/apache%20tomcat-%23F8DC75.svg?style=for-the-badge&logo=apache-tomcat&logoColor=black" alt="java" />
</div>
