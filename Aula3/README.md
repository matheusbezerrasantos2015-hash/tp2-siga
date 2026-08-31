# SIGA — Refatoração aplicando SOLID (Aula 3)

**Técnicas de Programação II (TP2)** · CST em Desenvolvimento de Software Multiplataforma · Fatec de Porto Ferreira

Esta pasta contém a entrega da **Atividade Prática da Aula 3**, que evolui o SIGA aplicando os princípios SOLID (SRP, OCP e DIP) sobre o código fornecido pelo professor.

## Estrutura do projeto

```
Aula3/
├── README.md
├── EVIDENCIAS.md
└── src/
    └── siga/
        ├── Aluno.java                   (entidade — não alterada nesta atividade)
        ├── FormatadorRelatorioAluno.java (SRP: responsável só pela formatação)
        ├── RepositorioRelatorio.java     (SRP: responsável só por salvar em arquivo)
        ├── ComunicadorRelatorio.java     (SRP: responsável só pelo envio por e-mail)
        ├── Desconto.java                 (OCP: abstração do cálculo de desconto)
        ├── DescontoBolsista.java
        ├── DescontoConvenio.java
        ├── DescontoFuncionario.java
        ├── SemDesconto.java
        ├── MatriculaRepositorio.java     (DIP: abstração de persistência)
        ├── GravadorMySQL.java            (implementa MatriculaRepositorio)
        ├── Matricula.java                (depende apenas de Desconto e MatriculaRepositorio)
        └── Main.java                     (demonstra o sistema já refatorado)
```

## Como compilar e executar

Pré-requisito: JDK 17 ou superior instalado (`java -version` para verificar).

A partir desta pasta (`Aula3/`):

```bash
# 1. Compilar (a saída vai para a pasta "bin")
javac -d bin src/siga/*.java

# 2. Executar
java -cp bin siga.Main
```

## O que foi feito nesta atividade (Aula 3)

1. **Diagnóstico do SRP** — identificadas por escrito as três responsabilidades misturadas em `RelatorioAluno` (formatação, persistência, comunicação) e por que cada uma representa um motivo independente de mudança. Detalhes em `EVIDENCIAS.md`.
2. **SRP aplicado** — `RelatorioAluno` foi eliminada; suas responsabilidades foram separadas em `FormatadorRelatorioAluno`, `RepositorioRelatorio` e `ComunicadorRelatorio`.
3. **OCP aplicado** — o bloco de `if/else` de `calcularMensalidade()` foi substituído por polimorfismo: a interface `Desconto` e quatro implementações (`DescontoBolsista`, `DescontoConvenio`, `DescontoFuncionario`, `SemDesconto`). Novos tipos de desconto podem ser adicionados sem modificar `Matricula`.
4. **DIP aplicado** — `Matricula` deixou de depender diretamente de `GravadorMySQL` e passou a depender da abstração `MatriculaRepositorio`, injetada via construtor. A implementação concreta pode ser trocada sem alterar `Matricula`.
5. **Code smells documentados** — God Class, Complexidade Condicional e acoplamento forte com implementação concreta. Detalhes em `EVIDENCIAS.md`.

## Padrão de entrega

Identificadores em português, código formatado pela IDE, entrega via repositório Git com commits incrementais, conforme as regras da ficha de atividade prática.
