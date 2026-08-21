# SIGA — Sistema de Gestão Acadêmica Simplificado

**Técnicas de Programação II (TP2)** · CST em Desenvolvimento de Software Multiplataforma · Fatec de Porto Ferreira

Este é o projeto incremental da disciplina. Esta pasta contém a entrega da **Atividade Prática da Aula 1**, com o código inicial já refatorado conforme o enunciado.

## Estrutura do projeto

```
Aula1/
├── README.md
├── EVIDENCIAS.md
├── evidencias/
└── src/
    └── siga/
        ├── Pessoa.java      (superclasse: nome protegido, validado)
        ├── Aluno.java       (extends Pessoa; matrícula, média [0-10] e status, todos validados)
        ├── Professor.java   (extends Pessoa; SIAPE e status, validados)
        ├── Turma.java       (agrega Aluno por composição, com validação de duplicidade)
        ├── Matricula.java   (ainda não refatorada — assunto da Aula 2 / OCP)
        └── Main.java        (demonstra a criação e uso das classes refatoradas)
```

## Como compilar e executar

Pré-requisito: JDK 17 ou superior instalado (`java -version` para verificar).

A partir desta pasta (`Aula1/`):

```bash
# 1. Compilar (a saída vai para a pasta "bin")
javac -d bin src/siga/*.java

# 2. Executar
java -cp bin siga.Main
```

## O que foi feito nesta atividade (Aula 1)

1. **Identificação da violação de encapsulamento** — analisada a classe `Aluno` original, com atributos `public` e sem construtor, permitindo estados inválidos (ex.: média negativa). Registro detalhado em `EVIDENCIAS.md`.
2. **Encapsulamento aplicado** — `Aluno` refatorada para atributos `private`, com métodos acessores validados (nome e matrícula não podem ser vazios; média deve estar entre 0 e 10).
3. **Herança aplicada** — criada a superclasse `Pessoa` (atributo `nome`), da qual `Aluno` e `Professor` derivam, eliminando a duplicação de código entre as duas classes.
4. **Composição aplicada** — criada a classe `Turma`, que agrega múltiplos `Aluno` por meio de uma lista, com validação de duplicidade e retorno de cópia defensiva na consulta.
5. **Diagrama de classes** — desenhado o diagrama UML do domínio resultante (`Pessoa`, `Aluno`, `Professor`, `Turma`), disponível em `EVIDENCIAS.md`.

> A classe `Matricula` e o cálculo de mensalidade não foram alterados nesta atividade — são retomados na **Aula 2**, ao estudar os princípios SOLID.

## Padrão de entrega

Identificadores em português, código formatado pela IDE, entrega via repositório Git com commits incrementais, conforme as regras da ficha de atividade prática.
