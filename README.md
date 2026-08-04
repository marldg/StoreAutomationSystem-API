# Sistema de Automação de Loja

API REST em Java/Spring Boot para gerenciar produtos, clientes e vendas de uma loja, com controle automático de estoque e endpoint de dashboard para integração com sistemas externos.

## Tecnologias
- Java + Spring Boot
- Spring Data JPA + MySQL
- Lombok + Maven

## Como rodar

### Pré-requisitos
- Java 17 ou superior
- MySQL rodando localmente

### Configuração
Renomeie o arquivo `application.properties.example` para `application.properties` e ajuste com seus dados:
```
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### Iniciando
```bash
git clone https://github.com/marldg/automacaodeloja.git
cd automacaodeloja
./mvnw spring-boot:run
```
Servidor sobe em `http://localhost:8080`. O banco e as tabelas são criados automaticamente.

## Endpoints

### Produtos
| Método | Rota | Descrição |
|---|---|---|
| POST | `/produtos` | Cadastrar produto |
| GET | `/produtos` | Listar todos |
| GET | `/produtos/{id}` | Buscar por id |
| PUT | `/produtos/{id}` | Atualizar produto |
| DELETE | `/produtos/{id}` | Deletar produto |

### Clientes
| Método | Rota | Descrição |
|---|---|---|
| POST | `/clientes` | Cadastrar cliente |
| GET | `/clientes` | Listar todos |
| GET | `/clientes/{id}` | Buscar por id |
| PUT | `/clientes/{id}` | Atualizar cliente |
| DELETE | `/clientes/{id}` | Deletar cliente |

### Vendas
| Método | Rota | Descrição |
|---|---|---|
| POST | `/vendas` | Registrar venda |
| GET | `/vendas` | Listar todas |
| GET | `/vendas/{id}` | Detalhar venda |

### Dashboard (integração externa)
| Método | Rota | Descrição |
|---|---|---|
| GET | `/api/dados` | Retorna total de vendas, quantidade de pedidos e status |

O endpoint `/api/dados` requer autenticação por API Key no header:
```
X-API-Key: sua-chave
```

**Exemplo de venda:**
```json
{
  "clienteId": 1,
  "itens": [
    { "produtoId": 1, "quantidade": 2 }
  ]
}
```
Ao registrar uma venda, o estoque dos produtos é decrementado automaticamente.

## Integração
Este projeto se integra com o [Sistema de Análise de Loja](https://github.com/marldg/analisedaloja), uma aplicação JavaFX que consome o endpoint `/api/dados` e exibe os dados em um dashboard visual.
