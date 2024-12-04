# Aplicação de Transferências Bancárias

Este projeto implementa um sistema simples para realizar transferências bancárias, com validações de formato de conta, valores e persistência de dados em um banco de dados em memória H2. A aplicação foi desenvolvida utilizando o framework **Spring Boot**.

## Arquitetura e Decisões Técnicas

A arquitetura da aplicação foi projetada para ser simples e modular. Ela está dividida em camadas que separam responsabilidades, facilitando a manutenção e a evolução do sistema.

### Camadas da Aplicação:
- **Controller**: Expondo os endpoints da API e tratando as exceções lançadas pelos serviços.
- **Service**: Contém a lógica de negócios, como validações e execução das transferências.
- **Repository**: Responsável pela persistência das transferências utilizando o banco de dados H2.
- **Exceções Personalizadas**: Utiliza exceções customizadas (como `TransferenciaException`) para capturar e tratar erros específicos.

## Tecnologias e Ferramentas Utilizadas

- **Spring Boot**: Framework Java utilizado para o desenvolvimento da aplicação.
- **H2 Database**: Banco de dados em memória utilizado para persistir as transferências.
- **Java 17**: Linguagem utilizada para o desenvolvimento.
- **Maven**: Gerenciador de dependências e ferramentas de build.
- **Spring Data JPA**: Facilitador de operações de CRUD no banco de dados.

## Versões de Linguagem

- **Java**: 11
- **Spring Boot**: 2.7.15
- **Maven**: 3.4

## Funcionalidades Implementadas

- **Validação de Contas**: Valida se as contas de origem e destino têm exatamente 10 dígitos numéricos.
- **Validação de Valores**: Garante que o valor da transferência seja positivo.
- **Persistência em Banco de Dados H2**: As transferências realizadas são armazenadas em memória utilizando o banco H2, permitindo a persistência de dados durante a execução da aplicação.
- **API RESTful**: A aplicação expõe endpoints para realizar transferências bancárias, com validações automáticas antes de salvar no banco.

### linha de Raiciocio

Separação de Responsabilidades: A arquitetura foi dividida em camadas (Controller, Service, Repository) para seguir os princípios da boa prática de design de software, como a Separação de Responsabilidades. Cada camada possui uma função clara e distinta, o que facilita o desenvolvimento, testes e manutenção.

Controller: A camada responsável por expor as APIs e orquestrar o fluxo de entrada e saída de dados.

Service: Centraliza a lógica de negócios, como as validações e cálculos da taxa de transferência, garantindo que a regra do negócio esteja bem encapsulada e separada da parte de controle.

Repository: Responsável pela comunicação com o banco de dados, utilizando o Spring Data JPA para facilitar a persistência e recuperação dos dados.

Validações: Antes de executar qualquer transferência, validamos os dados inseridos, como o formato das contas bancárias (10 dígitos numéricos) e a validade do valor (positivo). Essas validações garantem a integridade do sistema, evitando transferências com dados inválidos que poderiam prejudicar o processo.

Persistência em Banco de Dados H2: Para facilitar o desenvolvimento e os testes, a escolha pelo banco H2 em memória foi feita. Ele permite que a aplicação seja executada de forma rápida e eficiente em um ambiente local sem a necessidade de uma infraestrutura de banco de dados complexa. Caso seja necessário escalar a aplicação, é possível facilmente configurar outro banco de dados mais robusto, sem necessidade de grandes modificações.

Lógica de Cálculo de Taxa: A lógica para o cálculo da taxa de transferência leva em consideração a data de agendamento e o valor a ser transferido. Essa abordagem permite que o sistema seja flexível e se adapte a diferentes cenários. A escolha de taxas baseadas no tempo de antecipação (de 0 a 50 dias) reflete práticas comuns em sistemas bancários.

API RESTful: A aplicação expõe um conjunto de endpoints RESTful, proporcionando uma interface de fácil utilização para interação com a aplicação. Isso garante que a integração com outros sistemas ou a criação de interfaces front-end seja simples e direta.
