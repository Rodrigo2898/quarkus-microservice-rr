# Sistema de Eleição - Microsserviço
Este projeto implementa um microsserviço de um sistema de eleição utilizando o Quarkus Framework e diversas tecnologias para garantir desempenho, escalabilidade e observabilidade. O projeto segue princípios de Arquitetura Cebola e práticas de Desenvolvimento Orientado a Testes (TDD).

## Tecnologias Utilizadas
  - Quarkus Framework: Framework Java nativo para Kubernetes, otimizando a performance e o consumo de memória.
  - Redis: Armazenamento de cache para otimização de operações de leitura.
  - MongoDB: Banco de dados NoSQL utilizado para armazenamento de documentos.
  - MariaDB: Banco de dados relacional utilizado para persistência de dados estruturados.
  - OpenSearch: Ferramenta de busca e análise em tempo real para dados armazenados.
  - Traefik: Proxy reverso para roteamento e balanceamento de carga dos microsserviços.
  - Jaeger: Ferramenta para rastreamento distribuído, utilizada para monitorar o fluxo de requisições entre os microsserviços.
  - Docker e Docker Compose: Para a conteinerização dos serviços e orquestração local.
  - Arquitetura Cebola (Onion Architecture): Abordagem arquitetural para separação de responsabilidades e garantia de flexibilidade na evolução do software.
  - TDD (Test-Driven Development): Prática de desenvolvimento onde os testes são escritos antes do código de produção.
## Funcionalidades
  - Cadastro de Candidatos: Armazenamento e gerenciamento dos dados de eleitores.
  - Criação e Gerenciamento de Eleições: Permite a criação de novas eleições e a gestão das existentes.
  - Votação: Processo seguro de votação com persistência e validação dos votos.
  - Resutados: Sistema de contagem de votos em tempo real e publicação dos resultados.
  - Monitoramento e Observabilidade: Através de OpenSearch e Jaeger, o sistema pode ser monitorado para performance e rastreamento de requisições.
## Estrutura do Projeto
O projeto segue os princípios da Arquitetura Cebola, com as seguintes camadas:

- Domínio: Define as entidades e regras de negócio.
- Aplicação: Camada de serviços que orquestra as operações do sistema.
- Infraestrutura: Implementa a persistência de dados, comunicação com APIs externas e integrações com tecnologias como Redis, MongoDB e MariaDB.
- Interface: Exposição de APIs RESTful utilizando Quarkus.

## Monitoramento
  - OpenSearch: Disponível para consultas de logs e métricas em tempo real.
  - Jaeger: Utilizado para rastrear transações distribuídas e verificar a performance dos microsserviços.
## Docker Compose
O projeto inclui um arquivo docker-compose.yml que orquestra os seguintes serviços:

  - Traefik: Proxy reverso que roteia as requisições HTTP.
  - Election Management Service: Serviço principal de gestão de eleições.
  - Voting App: Responsável pela coleta e processamento dos votos.
  - Result App: Exibe os resultados das eleições em tempo real.
  - MariaDB: Banco de dados relacional para persistência dos dados de eleição.
  - Redis: Sistema de cache para otimização de operações.
  - MongoDB: Banco de dados para armazenamento de logs e outras informações.
  - OpenSearch: Utilizado para análise de logs e dados.
  - Graylog: Centraliza e exibe os logs de todos os serviços.
  - Jaeger: Para rastreamento distribuído das requisições entre microsserviços.

## Como Executar o projeto
1. Clone o repositório:
   - git clone https://github.com/Rodrigo2898/quarkus-microservice-rr
   - cd quarkus-microservice-rr
2. Execute os seguintes comandos para subir os containers:
   - docker-compose up -d reverse-proxy
   - docker-compose up -d jaeger
   - docker-compose up -d mongodb opensearch
   - docker-compose up -d graylog
   - docker-compose up -d caching database
3. Execute os comandos shell:
   - ./cicd-build.sh election-management
   - ./cicd-build.sh voting-app
   - ./cicd-build.sh result-app
4. Faça o deploy com a versão gerada no ./cicd-buil.sh:
   - ./cicd-blue-green-deployment.sh election-management VERSÃO
   - ./cicd-blue-green-deployment.sh voting-app VERSÃO
   - ./cicd-blue-green-deployment.sh result-app VERSÃO
