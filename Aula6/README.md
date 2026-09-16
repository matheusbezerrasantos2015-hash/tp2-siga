# SIGA — Padrões Criacionais II (Aula 6)

**Técnicas de Programação II (TP2)** · CST em Desenvolvimento de Software Multiplataforma · Fatec de Porto Ferreira

Esta pasta contém a entrega da **Atividade Prática da Aula 6**, que aplica em conjunto os padrões Abstract Factory, Builder e Singleton sobre o código de acesso a dados do SIGA.

> **Nota:** a Seção 3 da ficha descreve um contexto de geração de documentos (histórico, declaração, certificado), mas o código inicial fornecido trabalha com acesso a banco de dados (MySQL/PostgreSQL). A refatoração seguiu o código real fornecido — detalhes em `EVIDENCIAS.md`.

## Estrutura do projeto

```
Aula6/
├── README.md
├── EVIDENCIAS.md
├── diagrama-solucao.md
├── evidencias/
└── src/
    └── siga/
        ├── Conexao.java              (produto — interface, não alterada)
        ├── Comando.java              (produto — interface, não alterada)
        ├── ObjetosAcessoDados.java   (implementações concretas MySQL/PostgreSQL, não alteradas)
        ├── FabricaBanco.java         (Abstract Factory — criador abstrato)
        ├── FabricaMySQL.java
        ├── FabricaPostgreSQL.java
        ├── ConsultaBuilder.java      (Builder)
        ├── AcessoDados.java          (Singleton)
        └── Main.java                 (código cliente)
```

## Como compilar e executar

Pré-requisito: JDK 17 ou superior instalado (`java -version` para verificar).

A partir desta pasta (`Aula6/`):

```bash
# 1. Compilar (a saída vai para a pasta "bin")
javac -d bin src/siga/*.java

# 2. Executar
java -cp bin siga.Main
```

## O que foi feito nesta atividade (Aula 6)

1. **Diagnóstico** — identificados por escrito os dois problemas do código original: mistura de fornecedores em `conectar` (sem garantia de coerência entre `Conexao` e `Comando`) e construtor telescópico em `montarConsulta` (7 parâmetros pouco legíveis). Detalhes em `EVIDENCIAS.md`.
2. **Abstract Factory** — criada `FabricaBanco` (abstrata) e as fábricas concretas `FabricaMySQL`/`FabricaPostgreSQL`, garantindo que conexão e comando sejam sempre do mesmo fornecedor.
3. **Builder** — criada `ConsultaBuilder`, substituindo o método telescópico por uma construção passo a passo, legível e encadeável.
4. **Singleton** — `AcessoDados` transformado em instância única, com construtor privado e ponto de acesso controlado via `getInstancia()`.
5. **Diagrama de classes** — desenhada a solução final, evidenciando os três padrões aplicados. Disponível em `diagrama-solucao.md`.

## Padrão de entrega

Identificadores em português, código formatado pela IDE, entrega via repositório Git com commits incrementais, conforme as regras da ficha de atividade prática.
