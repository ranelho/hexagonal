# Hexagonal Architecture

```sh
src/main/java
└── com
└── rlti.hexagonal
├── adapters
│   ├── input        # Adaptadores primários (ex.: Controllers REST, consumidores do sistema)
│   │   └── EnderecoController.java
│   ├── output       # Adaptadores secundários (ex.: FeignClients, Repositórios, APIs externas)
│   │   ├── ViaCepFeignAdapter.java
│   │   ├── ViaCepFeignClient.java
│   │   └── response
│   │       └── ViaCepResponse.java
│   └── config       # Configurações relacionadas a portas/adaptadores (ex.: Beans, Feign, etc.)
│       └── ApplicationConfig.java
├── core
│   ├── domain       # Entidades do domínio (objetos principais do negócio)
│   │   └── Endereco.java
│   ├── port         # Portas que definem os contratos de entrada e saída
│   │   ├── input
│   │   │   └── BuscaEnderecoInputPort.java
│   │   └── output
│   │       └── ViaCepOutputPort.java
│   └── usecase      # Casos de uso que implementam a lógica de negócio
│       └── BuscaEnderecoUseCase.java
└── Application.java # Classe principal da aplicação

```
