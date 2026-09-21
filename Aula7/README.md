# SIGA — Padrão DAO (Aula 7)

**Técnicas de Programação II (TP2)** · CST em Desenvolvimento de Software Multiplataforma · Fatec de Porto Ferreira

Esta pasta contém a entrega da **Atividade Prática da Aula 7**, que extrai a camada de acesso a dados do SIGA aplicando o padrão DAO.

## Estrutura do projeto

```
Aula7/
├── README.md
├── EVIDENCIAS.md
├── evidencias/
└── src/
    └── siga/
        ├── Aluno.java              (entidade — não alterada)
        ├── BancoSimulado.java      (simulação de banco — não alterada)
        ├── AlunoDAO.java           (interface do DAO, vocabulário do domínio)
        ├── AlunoDAOMemoria.java    (implementação com Map)
        ├── AlunoDAOLista.java      (implementação alternativa com List — Etapa 5)
        ├── ServicoMatricula.java   (regra de negócio, depende apenas de AlunoDAO)
        └── Main.java               (código cliente: escolhe a implementação do DAO)
```

## Como compilar e executar

Pré-requisito: JDK 17 ou superior instalado (`java -version` para verificar).

A partir desta pasta (`Aula7/`):

```bash
# 1. Compilar (a saída vai para a pasta "bin")
javac -d bin src/siga/*.java

# 2. Executar
java -cp bin siga.Main
```

## O que foi feito nesta atividade (Aula 7)

1. **Diagnóstico** — identificados por escrito os três problemas do código original: violação do SRP (regra de negócio misturada a SQL), violação do DIP (dependência direta de `BancoSimulado`) e duplicação do acesso a dados entre `matricular()` e `gerarRelatorio()`. Detalhes em `EVIDENCIAS.md`.
2. **Interface `AlunoDAO`** — definida com vocabulário do domínio (inserir, buscarPorMatricula, listarTodos, atualizar, remover), sem nenhum termo de SQL.
3. **`AlunoDAOMemoria`** — implementação funcional guardando os alunos em um `Map`, utilizável sem banco de dados.
4. **`ServicoMatricula` refatorado** — passou a receber o `AlunoDAO` pelo construtor, sem nenhum SQL, dependendo apenas da abstração.
5. **Troca de implementação demonstrada** — criada `AlunoDAOLista` (usando `List` em vez de `Map`) e usada no `Main.java` sem alterar uma linha sequer de `ServicoMatricula`, provando o DIP na prática.

## Padrão de entrega

Identificadores em português, código formatado pela IDE, entrega via repositório Git com commits incrementais, conforme as regras da ficha de atividade prática.
