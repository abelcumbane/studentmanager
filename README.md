**Sistema de Gestão de Estudantes** utilizando **Spring Boot, Vaadin e MySQL**

---

# **🎓 Sistema de Gestão de Estudantes - Spring Boot & Vaadin**  
📌 *Projeto para consolidar os conceitos de Spring Boot e Vaadin*  

## **📌 Visão Geral**  
Este projeto é um **sistema simples de gestão de estudantes**, onde é possível **cadastrar, listar, editar e excluir estudantes** por meio de uma interface gráfica moderna desenvolvida com **Vaadin**.  

O backend é construído com **Spring Boot**, utilizando **Spring Data JPA** para interação com a base de dados e **H2 Database** como base de dados em memória para testes rápidos.  

## **🛠️ Tecnologias Utilizadas**  
✅ **Spring Boot** - Gerenciamento do backend e configuração automática.  
✅ **Spring Data JPA** - Comunicação com a base de dados.  
✅ **H2 Database** - Banco de dados em memória para desenvolvimento e testes.  
✅ **Vaadin Flow** - Criação da interface gráfica totalmente em Java.  
✅ **MySql** - Gerenciamento da base de dados.  
✅ **Maven** - Gerenciamento de dependências.  

---

## **📌 Funcionalidades**  
✔ **Cadastro de Estudantes** com Name, Age, Status e Country.  
✔ **Listagem de Estudantes** em uma tabela interativa.  
✔ **Edição e Exclusão** de estudantes cadastrados.  
✔ **Busca Dinâmica** de estudantes pelo name.  
✔ **Interface Responsiva** sem necessidade de HTML ou JavaScript.  

---

## **🎯 Como Executar o Projeto**  

1️⃣ **Clone o repositório**  
```bash
git clone https://github.com/abelcumbane/studentmanager.git
cd seu-repositorio
```

2️⃣ **Execute o projeto** usando Maven ou um IDE como IntelliJ/Eclipse:  
```bash
mvn spring-boot:run
```

3️⃣ **Acesse a aplicação no navegador**  
```
http://localhost:8080
```

---

## **📌 Estrutura do Projeto**  
```
📂 src/main/java/com/mapulassapp
 ├── 📂 models          # Entidades do banco de dados
 ├── 📂 repositorys     # Interfaces do Spring Data JPA
 ├── 📂 services        # Regras de negócio
 ├── 📂 views           # Views e componentes Vaadin
 ├── 📂 constants       # Configurações das Constantes
 └── Application.java   # Classe principal
```

---

## 4 **Tela Principal**  


![vaadn](https://github.com/user-attachments/assets/01c28113-da5f-4077-a978-93ce99617b06)


## 5 **Tela Principal com Dark Mode Implementado** 

![dark-mode](https://github.com/user-attachments/assets/1a49960e-818f-4b0f-8ffb-db5f0d95c27a)
