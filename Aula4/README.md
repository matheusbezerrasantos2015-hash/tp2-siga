# SIGA — Modelagem UML do domínio (Aula 4)

**Técnicas de Programação II (TP2)** · CST em Desenvolvimento de Software Multiplataforma · Fatec de Porto Ferreira

Esta pasta contém a entrega da **Atividade Prática da Aula 4**, que modela em UML o domínio do SIGA construído nas aulas anteriores, usando a notação Mermaid.

## Conteúdo

- [`diagrama-siga.md`](diagrama-siga.md) — diagrama de classes do domínio (Pessoa, Aluno, Professor, Turma, Disciplina, Matricula e a interface Desconto) e diagrama de sequência do cálculo da mensalidade. Os blocos Mermaid são renderizados automaticamente pelo GitHub ao visualizar o arquivo.

## O que foi modelado

1. **Classes do domínio** — as seis entidades (`Pessoa`, `Aluno`, `Professor`, `Turma`, `Disciplina`, `Matricula`), com atributos e visibilidade fiéis ao código já implementado nas Aulas 1 e 3.
2. **Herança** — `Pessoa` como superclasse de `Aluno` e `Professor`.
3. **Agregação** — `Turma` e `Aluno`, com multiplicidade `0..*` em ambos os lados (um aluno pode estar em várias turmas; uma turma pode ter vários alunos).
4. **Interface e realização** — a interface `Desconto`, realizada por `DescontoBolsista`, `DescontoConvenio` e `DescontoFuncionario`.
5. **Diagrama de sequência** — a colaboração entre `Main`, `Matricula` e a implementação concreta de `Desconto` no cálculo da mensalidade.

Este diagrama toma como base o domínio já implementado em código nas atividades das Aulas 1 e 3, e será reutilizado na apresentação dos padrões criacionais a partir da Aula 5.
