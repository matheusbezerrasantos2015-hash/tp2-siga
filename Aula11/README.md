# SIGA - Aula 11: Decorator e Composite

Atividade prática de Técnicas de Programação II (Fatec de Porto Ferreira).

## Como compilar e executar

Requer JDK 17 ou superior, a partir da pasta raiz do projeto:

```
javac -d out src/siga/*.java
java -cp out siga.Main
```

## Etapa 1 - Quantas subclasses são necessárias?

Hoje existem **4 subclasses** de relatório (`RelatorioComCabecalho`,
`RelatorioComNumeracao`, `RelatorioComCabecalhoENumeracao` e
`RelatorioComCabecalhoENumeracaoEMarcaDagua`), além da classe base
`RelatorioDeAlunos`, para 3 características opcionais (cabeçalho, numeração e
marca d'água).

Cada característica opcional tem dois estados (ligada ou desligada) e as
escolhas são independentes. Com `n` características, o número de combinações é
`2 x 2 x ... x 2 = 2^n`:

| Características (n) | Combinações (2^n) | Classes na hierarquia atual | Classes com decoradores |
|---------------------|-------------------|-----------------------------|-------------------------|
| 3                   | 2^3 = 8           | 8 (1 base + 7 subclasses)   | 3 (+ 1 abstrata)        |
| 4                   | 2^4 = 16          | 16 (1 base + 15 subclasses) | 4 (+ 1 abstrata)        |

Ou seja, a hierarquia atual (4 subclasses) ainda está incompleta: faltam 3
combinações das 7 possíveis, e uma quarta característica dobraria o total para
16. Com decoradores, cada característica nova custa **uma** classe.

## Etapa 2 - Decoradores

Três classes substituíram as quatro subclasses, cada comportamento escrito uma
única vez:

- `DecoradorRelatorio`: base abstrata, é um `Relatorio` que envolve outro `Relatorio`.
- `DecoradorCabecalho`: o texto do cabeçalho existe somente aqui (constante `CABECALHO`).
- `DecoradorNumeracao`: numera as linhas do relatório decorado.
- `DecoradorMarcaDagua`: acrescenta a marca d'água ao final.

As quatro subclasses antigas (`RelatorioComCabecalho`, `RelatorioComNumeracao`,
`RelatorioComCabecalhoENumeracao` e `RelatorioComCabecalhoENumeracaoEMarcaDagua`)
foram removidas.

## Etapa 3 - Pilhas diferentes a partir da mesma classe base

No `Main`, três pilhas montadas em tempo de execução sobre `RelatorioDeAlunos`:

```java
Relatorio apenasCabecalho = new DecoradorCabecalho(new RelatorioDeAlunos());
Relatorio numeradoComMarca = new DecoradorMarcaDagua(
        new DecoradorNumeracao(new RelatorioDeAlunos()));
Relatorio completo = new DecoradorMarcaDagua(
        new DecoradorCabecalho(
                new DecoradorNumeracao(new RelatorioDeAlunos())));
```

A ordem da pilha importa: a numeração fica mais por dentro para não numerar o
cabeçalho, e a marca d'água fica por fora para aparecer por último.

## Etapa 4 - Composite

`ItemCurricular` é a interface comum (`getNome`, `calcularCarga`, `imprimir`).
`Disciplina` é a folha e `Modulo` é o composto, com
`List<ItemCurricular>` como coleção. A antiga `GradeCurricular` deixou de existir:
cada operação passou a ser polimórfica, então não há travessia duplicada nem
verificação de tipo, e a carga total permanece em **340h**.

## Etapa 5 - Terceiro nível

Foi acrescentado o submódulo `Optativas` dentro do `2o semestre`, com duas
disciplinas de 40h. O método `exibirEstrutura` do cliente **não foi alterado**:
só foram adicionadas as linhas que criam o submódulo.

## Saída da execução (evidência)

```
=== SIGA - Aula 11: Decorator e Composite ===

1) Relatorios: decoradores empilhaveis
   --- Cabecalho ---
   FATEC DE PORTO FERREIRA
   Sistema de Gestao Academica
   ---
   Maria Silva
   Joao Souza
   Ana Pereira
   --- Numeracao + marca d'agua ---
   1: Maria Silva
   2: Joao Souza
   3: Ana Pereira
   *** RASCUNHO - NAO E DOCUMENTO OFICIAL ***
   --- Numeracao + cabecalho + marca d'agua ---
   FATEC DE PORTO FERREIRA
   Sistema de Gestao Academica
   ---
   1: Maria Silva
   2: Joao Souza
   3: Ana Pereira
   *** RASCUNHO - NAO E DOCUMENTO OFICIAL ***

2) Estrutura curricular como Composite
   Antes do submodulo:
  [DSM - Desenvolvimento de Software Multiplataforma]
    [1o semestre]
      Algoritmos (80h)
      Banco de Dados I (60h)
    [2o semestre]
      Tecnicas de Programacao II (80h)
      Desenvolvimento Web II (80h)
    Projeto Integrador (40h)
   Carga total: 340h

   Depois do submodulo de terceiro nivel:
  [DSM - Desenvolvimento de Software Multiplataforma]
    [1o semestre]
      Algoritmos (80h)
      Banco de Dados I (60h)
    [2o semestre]
      Tecnicas de Programacao II (80h)
      Desenvolvimento Web II (80h)
      [Optativas]
        Computacao em Nuvem (40h)
        Seguranca da Informacao (40h)
    Projeto Integrador (40h)
   Carga total: 420h
```

## Decisão de projeto: onde ficam adicionar e remover

Os métodos `adicionar` e `remover` ficaram **apenas em `Modulo`** (composto),
fora da interface `ItemCurricular`. Motivos:

- Não fazem sentido para uma `Disciplina`: ela não tem filhos.
- Colocá-los na interface preservaria a uniformidade, mas obrigaria a folha a
  implementá-los lançando exceção, o que tensiona o princípio da substituição
  de Liskov (um `ItemCurricular` nem sempre poderia ser usado no lugar de outro).
- Mantê-los no composto preserva a segurança de tipos: o compilador impede
  `adicionar` em uma folha.

O custo é que o cliente precisa guardar a referência como `Modulo` quando quer
montar a estrutura (como feito no `Main`), mas pode tratar tudo como
`ItemCurricular` ao percorrê-la.

## Critérios de sucesso

- Texto do cabeçalho em um único lugar: `DecoradorCabecalho`.
- Três características opcionais atendidas por três classes (e não oito).
- Nenhum arquivo do projeto contém a verificação de tipo que a atividade proíbe
  (conferido com `grep -ril` na pasta do projeto: nenhum resultado).
- Coleção de `Modulo` do tipo `List<ItemCurricular>`.
- Carga total de 340h antes do terceiro nível (420h depois).
