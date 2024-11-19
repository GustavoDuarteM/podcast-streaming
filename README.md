# Projeto Final Arquitetura Java [24E4_2] - Cadastro de Podcasts e Albums

Este projeto faz parte da disciplina de **Arquitetura Java** e foi desenvolvido em etapas.

## Funcionalidades Entregues

### Features

- **Cadastro de Publisher**: Permite o cadastro de publishers, a entidade responsável por criar Canais de conteúdo.
- **Cadastro de StreamChannel**: Permite o cadastro de StreamChannels, que representam os canais de conteúdo. Cada StreamChannel possui um Publisher associado. Cada StreamChannel pode ter vários Podcasts e Albums.
- **Cadastro de Podcast**: Permite o cadastro de Podcasts, que representam episódios de áudio. Cada Podcast possui um StreamChannel associado. Cada Podcast uma midia de áudio associada.
- **Cadastro de album**: Permite o cadastro de Albums, que representam coleções de músicas. Cada Album possui um StreamChannel associado. Cada Album possui várias músicas associadas.
- **Leitura de Arquivo**: O sistema lê um arquivo de texto com informações de publishers, streamchannels, podcasts e albums e carrega os dados no sistema.

### Estrutura do Projeto

- **`com.podcast_streaming.gustavo_duarte.GustavoDuarteApplication`**: Classe principal do projeto Spring Boot.
- **`com.podcast_streaming.gustavo_duarte.Loader`**: Classe responsável por carregar dados iniciais a partir de um arquivo de texto.
- **Domínio**:
  - `Publisher`: Entidade que representa um Publisher.
  - `StreamChannel`: Entidade que representa um StreamChannel e contém uma lista de Podcasts e Albums.
  - `Podcast`: Entidade que representa um Podcast e contém uma Midia de áudio.
  - `Album`: Entidade que representa um Album e contém uma lista de Músicas.
  - `Midia`: Entidade que representa uma Midia de áudio.

### Arquivo de Entrada

Os vendedores e produtos são cadastrados automaticamente a partir do arquivo `src/main/resources/db/seeds/start_loader.yml`


### Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 2.7.5**
- **Maven**
- **Lombok**
- **PostgreSQL**
- **Flyway**
- **Docker**


### Como Executar

1. Clone este repositório.
2. Certifique-se de que o Java 21 e o Maven estão instalados em sua máquina.
3. Execute o docker-compose para subir o banco de dados:
```bash
docker-compose up
```
4. Compile o projeto executando:
```bash
mvn clean install
```
5. Execute o projeto com o comando:
```bash
mvn spring-boot:run
```
6. O sistema irá carregar os dados a partir do arquivo `start_loader.yml`.

### Endpoints
Os endpoints foram testados utilizando o Insomnia.
Nesse projeto, foram criados endpoints para as entidades `Publisher`, `StreamChannel`, `Podcast` e `Album`.
O arquivo [`Insomnia-gustavo-duarte.json`](https://raw.githubusercontent.com/GustavoDuarteM/podcast-streaming/refs/heads/main/Insomnia-gustavo-duarte) na raiz do prjeto contém as requisições para testar os endpoints.
Nesse arquivo, há exemplos de requisições para cada entidade, como criar, listar, atualizar e deletar.
O link para download do Insomnia é: [Insomnia](https://insomnia.rest/download)

## Autor

- **Gustavo Duarte** - [gustavoduarte](gustavo_dmuniz@hotmail.com)
