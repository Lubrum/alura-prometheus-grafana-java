# Alura - Formação DevOps

# 4.1 Observabilidade: coletando métricas de uma aplicação com Prometheus

# 4.2 Monitoramento: Prometheus, Grafana e Alertmanager

A ideia deste projeto foi a de praticar os conceitos de observabilidade e monitoramento de aplicações com o Prometheus, Alertmanager e o Grafana. 

Temos uma aplicação de exemplo Spring Boot, com MySQL, para exercitar esses conceitos.

Para gerar as métricas para serem visualizadas foi criado um contâiner com um cliente fictício fazendo requisições na aplicação Spring, incluindo requisições bem sucedidas e de erro. 
Esse cliente está no diretório /client.


## Pré-requisitos para execução do projeto: 

- docker;
- docker compose;

## Para execução do projeto e subir os contâiners:

```bash
docker compose up -d
```

URLs:
- endpoints da aplicação: http://localhost/
- endpoint do prometheus: http://localhost:9090
- endpoint do alertmanager: http://localhost:9093
- endpoint do grafana: http://localhost:3000

### Para visualizar as métricas no Grafana:

Acesse http://localhost:3000. Você deve ver a interface do Grafana.

Faça login com as credenciais padrão: nome de usuário "admin" e senha "admin".

Depois de fazer login, clique em "Add data source" na página inicial do Grafana.

Selecione "Prometheus" como o tipo de fonte de dados e configure o endereço do servidor Prometheus como "http://prometheus:9090".

Clique em "Save & Test".

Agora, você pode criar um novo painel no Grafana com os indicadores desejados.

## Para verificar o estado dos contâiners:

```bash
docker compose ps
# ou
docker ps
```

## Para parar os contâiners:

```bash
docker compose down
```

