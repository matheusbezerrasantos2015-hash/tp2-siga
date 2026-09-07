# SIGA — Fábrica de painéis por perfil (Aula 5)

**Técnicas de Programação II (TP2)** · CST em Desenvolvimento de Software Multiplataforma · Fatec de Porto Ferreira

Esta pasta contém a entrega da **Atividade Prática da Aula 5**, que encapsula a criação dos painéis de usuário do SIGA usando Simple Factory e, em seguida, o padrão Factory Method.

## Estrutura do projeto

```
Aula5/
├── README.md
├── EVIDENCIAS.md
├── diagrama-solucao.md
├── evidencias/
└── src/
    └── siga/
        ├── Painel.java                    (produto — interface, não alterada)
        ├── PainelAluno.java                (produto concreto, não alterado)
        ├── PainelProfessor.java            (produto concreto, não alterado)
        ├── PainelCoordenador.java          (produto concreto, não alterado)
        ├── PainelSecretaria.java           (produto concreto — novo perfil, Etapa 4)
        ├── CriadorPainel.java              (criador abstrato — Factory Method)
        ├── CriadorPainelAluno.java
        ├── CriadorPainelProfessor.java
        ├── CriadorPainelCoordenador.java
        ├── CriadorPainelSecretaria.java    (novo criador — Etapa 4)
        ├── GerenciadorLogin.java           (depende apenas de CriadorPainel)
        └── Main.java                       (código cliente: escolhe o criador)
```

## Como compilar e executar

Pré-requisito: JDK 17 ou superior instalado (`java -version` para verificar).

A partir desta pasta (`Aula5/`):

```bash
# 1. Compilar (a saída vai para a pasta "bin")
javac -d bin src/siga/*.java

# 2. Executar
java -cp bin siga.Main
```

## O que foi feito nesta atividade (Aula 5)

1. **Diagnóstico** — identificado por escrito o acoplamento e a violação do OCP causados pelo bloco `if/else` com `new` direto em `GerenciadorLogin`. Detalhes em `EVIDENCIAS.md`.
2. **Simple Factory** — criada a classe `FabricaPainel`, centralizando a criação dos painéis (etapa intermediária, removida após a Etapa 3).
3. **Factory Method** — criado o criador abstrato `CriadorPainel` e um criador concreto por perfil (`CriadorPainelAluno`, `CriadorPainelProfessor`, `CriadorPainelCoordenador`). `GerenciadorLogin` passou a depender apenas da abstração.
4. **Extensão sem modificação** — adicionado o perfil `SECRETARIA` criando apenas dois arquivos novos (`PainelSecretaria`, `CriadorPainelSecretaria`), sem alterar nenhuma classe existente, demonstrando o respeito ao OCP.
5. **Diagrama de classes** — desenhada a solução final, com a interface do produto e a hierarquia de criadores. Disponível em `diagrama-solucao.md`.

## Padrão de entrega

Identificadores em português, código formatado pela IDE, entrega via repositório Git com commits incrementais, conforme as regras da ficha de atividade prática.
