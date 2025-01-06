# Api Validação de Contas do Banco do Brasil

```sh
src/main/java
└── com
└── rlti.hexagonal.bbapi
├── adapters
│   ├── config  
│   │   └── ValidateAccountConfig.java 
│   ├── in 
│   │    ├── controller
│   │    │   └── EnderecoController.java
│   ├── out 
│   │   ├── response
│   │   │   └── ValidateResponse.java
│   │   ├── service
│   │   │   └── AccountValidationService.java
│   │   │   └── BancoDoBrasilAccountValidationServiceAdapter.java
│   │   │   └── OAuth2TokenProviderAdapter.java
├── application
│   ├── core
│   │   ├── domain      
│   │   │   └── ValidateAccount.java
│   │   ├── usecase
│   │   │   └── ValidateAccountUseCase.java  
│   ├── port        
│   │   ├── in
│   │   │   └── ValidateAccountInputPort.java
│   │   └── out
│   │   │   └── AccountValidationFeignClient.java
│   │   │   └── AccountValidationOutputPort.java
│   │   │   └── BancoDoBrasilAccountValidationOutputPort.java
│   │   │   └── OAuthFeignClient.java
│   │   │   └── TokenProvider.java
│   └── usecase      
│       └── BuscaEnderecoUseCase.java
└── Application.java 

```
