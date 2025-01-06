# Estetify

## Índice
1. [Introdução](#introdução)
2. [Modelagem](#modelagem)
    - [Quadro Scrum](#quadro-scrum)
    - [Diagrama de Casos de Uso](#diagrama-de-casos-de-uso)
    - [Diagrama de Classes](#diagrama-de-classes)
    - [Diagrama Entidade-Relacionamento](#diagrama-entidade-relacionamento)
    - [Diagramas de Sequência](#diagramas-de-sequência)
3. [Tecnologias Utilizadas](#tecnologias-utilizadas)
4. [Instalação](#instalação)
5. [Primeiro Login](#primeiro-login)

---

## Introdução

O **Estetify** é uma plataforma desenvolvida para o gerenciamento completo de clínicas de estética. Nosso objetivo é otimizar a organização e a eficiência das operações clínicas, proporcionando uma experiência aprimorada para administradores, profissionais e clientes.

Com o Estetify, é possível realizar o cadastro e gerenciamento de clientes, agendamentos, procedimentos e relatórios. Além disso, a plataforma oferece segurança no armazenamento de dados, com acesso protegido por login e senha, e diferentes níveis de permissão para usuários. Também é possível gerar relatórios detalhados, facilitando a análise e o acompanhamento das atividades da clínica.

---

## Modelagem

### Quadro Scrum

O desenvolvimento do Estetify segue a metodologia ágil **Scrum**, permitindo um gerenciamento eficiente do projeto através de sprints e priorização de tarefas. O quadro de tarefas está disponível no Trello, onde é possível visualizar o backlog, as sprints em andamento e as tarefas concluídas.

Acesse o quadro Scrum [aqui](https://trello.com/invite/b/670e74754ad3442f24634e35/ATTIdc5159a42914a0ad99b494a518cd1b0bA314F45A/2024-12-13-tcc).

---

### Diagrama de Casos de Uso

O Diagrama de Casos de Uso do Estetify apresenta as principais interações entre os usuários e o sistema, ilustrando cenários como:
- Cadastro de clientes
- Agendamento de procedimentos
- Gerenciamento de profissionais e agenda
- Geração de relatórios

![Diagrama de Casos de Uso](./docs/estetifyDiagramaUso.png)

---

### Diagrama de Classes

Este diagrama descreve a estrutura de classes que compõem o sistema, destacando as relações entre clientes, profissionais, agendamentos e relatórios. É um modelo detalhado que auxilia na compreensão da lógica do sistema e sua implementação.

![Diagrama de Classes](./docs/estetifyDiagramaClasses.jpg)

---

### Diagrama Entidade-Relacionamento

O Diagrama Entidade-Relacionamento (DER) demonstra a organização dos dados no banco de dados, incluindo entidades como:
- Clientes
- Profissionais
- Procedimentos
- Agendamentos

Este diagrama é essencial para entender como os dados são estruturados no sistema.

![Diagrama Entidade-Relacionamento](./docs/estetifyDiagramaER.png)

---

### Diagramas de Sequência

#### 1. Agendamento de Procedimentos
Este diagrama mostra o fluxo para realizar um agendamento, desde a escolha do cliente até a confirmação do procedimento.

![Diagrama de Sequência de Agendamento](./docs/diagramaSequenciaAgendamento.png)

#### 2. Geração de Relatórios
Ilustra o processo de geração de relatórios de desempenho da clínica e histórico de procedimentos.

![Diagrama de Sequência de Relatórios](./docs/diagramaSequenciaRelatorios.png)

---

## Tecnologias Utilizadas

### Desenvolvimento
- **Java**: Linguagem principal para o backend
- **SQLite**: Banco de dados leve e eficiente
- **Hibernate**: Framework ORM para mapeamento objeto-relacional
- **JavaMail**: Envio de notificações e e-mails automatizados
- **iTextPDF**: Geração de relatórios e documentos em PDF
- **BCrypt**: Criptografia de senhas para segurança

### Interface Gráfica
- **Swing**: Desenvolvimento da interface desktop

### Ferramentas de Apoio
- **Maven**: Gerenciamento de dependências
- **Git/GitHub**: Controle de versão e colaboração
- **StarUML**: Criação de diagramas UML
- **Trello**: Gerenciamento do projeto

---

## Instalação

1. Clone o repositório:
   ```bash
   git clone https://github.com/VS-7/plataforma-estetify.git
