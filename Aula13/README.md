# SIGA - Aula 13: Template Method e State

Atividade prática de Técnicas de Programação II (Fatec de Porto Ferreira).

## Como compilar e executar

Requer JDK 17 ou superior, a partir da pasta raiz do projeto:

```
javac -d out src/siga/*.java
java -cp out siga.Main
```

## Etapa 1 - O rito duplicado e a declaração fora de ordem

O rito de emissão tem cinco passos: **validar, cabeçalho, corpo, rodapé e
registrar no log**. Ele está escrito por inteiro em `HistoricoEscolar`,
`DeclaracaoVinculo` e `AtestadoFrequencia`; só o corpo muda entre os três
(o atestado ainda se diferencia por não levar assinatura digital).

Em `DeclaracaoVinculo` os passos estão **fora de ordem**: a chamada
`LogAcademico.registrar(...)` vem **antes** da validação do aluno.

Consequência observável: ao emitir a declaração para um aluno irregular
(Joao Souza, 2024002), o log mostra `[log] DECLARACAO emitido para 2024002` e
logo depois o erro `Aluno irregular: 2024002`. O documento não foi emitido, mas
o registro `DECLARACAO - 2024002` já consta em `LogAcademico.getRegistros()`.
Nas outras duas classes o log vem por último, depois da validação, então a
falha acontece sem deixar rastro.

## Etapa 2 - O esqueleto em um único lugar

`DocumentoAcademico` é uma classe abstrata com o método `emitir`, declarado
**`final`**. Ele executa, sempre nesta ordem: validar, cabeçalho, corpo,
rodapé, assinatura (se exigida) e registro no log. O cabeçalho e o rodapé
existem só nessa classe. Como `emitir` é final, nenhuma subclasse consegue
inverter a ordem: tentar sobrescrevê-lo gera erro de compilação.

## Etapa 3 - Subclasses e gancho

`HistoricoEscolar`, `DeclaracaoVinculo` e `AtestadoFrequencia` agora só
implementam `montarCorpo` e informam o nome usado no log. O gancho
`exigeAssinatura()` retorna `true` por padrão, e o `AtestadoFrequencia` o
sobrescreve para `false`, declarando de forma explícita a única diferença que
antes estava escondida no código duplicado.

Como a validação passou a ser o primeiro passo do template, emitir qualquer
documento para um aluno irregular falha **antes** do log: na saída abaixo, os
registros contêm apenas as três emissões da Maria Silva.

## Etapa 4 - State

`EstadoMatricula` é a classe abstrata dos estados, e `PreMatriculada`,
`Confirmada`, `Trancada` e `Cancelada` são os estados concretos. Cada estado
sobrescreve apenas as transições que oferece; as demais falham por padrão com a
mesma mensagem de antes. Os três métodos públicos de `Matricula` têm uma linha
cada, sem `if`:

```java
public void confirmar() { estado.confirmar(this); }
public void trancar()   { estado.trancar(this); }
public void cancelar()  { estado.cancelar(this); }
```

### State não é Strategy

A linha que distingue os dois padrões está dentro de uma classe de estado, por
exemplo em `PreMatriculada`:

```java
matricula.mudarPara(new Confirmada());
```

Uma política de desconto (Strategy) calcula e devolve um valor, e jamais
trocaria a política do contexto: quem escolhe a estratégia é quem monta o
objeto. No State, os estados se conhecem e são eles próprios que executam as
transições, trocando o estado do contexto. O contexto (`Matricula`) apenas
delega.

## Etapa 5 - Acrescentar o estado Concluida

Arquivos tocados no commit da etapa 5:

| Arquivo               | Alteração                                             |
|-----------------------|-------------------------------------------------------|
| `Concluida.java`      | classe nova (estado final, sem transições)            |
| `Confirmada.java`     | passa a oferecer a transição para `Concluida`         |

**Total: 2 arquivos** (`git show --stat` do commit confirma). `Matricula` e
`EstadoMatricula` não foram alterados.

Comparação com a situação inicial: na versão com condicionais sobre texto,
acrescentar `CONCLUIDA` exigia alterar os **três** métodos (`confirmar`,
`trancar` e `cancelar`) de `Matricula`, porque as regras de transição estavam
espalhadas por eles, e o nome da situação continuava sendo uma `String`
sujeita a erro de digitação só detectado em execução. Agora a mudança fica
restrita ao novo estado e ao estado que passa a levar até ele, e os nomes são
classes verificadas pelo compilador.

Observação de projeto: para manter a conta em dois arquivos, a transição
`Confirmada` para `Concluida` reaproveita o evento existente `confirmar()` (o
segundo `confirmar` de uma matrícula já confirmada a conclui). Criar um evento
próprio `concluir()` exigiria também um método novo em `Matricula` e um método
padrão em `EstadoMatricula`, ou seja, quatro arquivos em vez de dois.

## Saída da execução (evidência)

```
=== SIGA - Aula 13: Template Method e State ===

1) Tres documentos, um unico esqueleto de emissao
   [log] HISTORICO emitido para 2024001
     FATEC DE PORTO FERREIRA
     Sistema de Gestao Academica
     ---
     HISTORICO ESCOLAR
     Maria Silva
     Carga horaria cumprida: 560h
     ---
     Emitido em 2026-11-08
     Assinado digitalmente pela secretaria academica.
   [log] DECLARACAO emitido para 2024001
     FATEC DE PORTO FERREIRA
     Sistema de Gestao Academica
     ---
     DECLARACAO DE VINCULO
     Declaramos que Maria Silva possui vinculo ativo com esta instituicao.
     ---
     Emitido em 2026-11-08
     Assinado digitalmente pela secretaria academica.
   [log] ATESTADO emitido para 2024001
     FATEC DE PORTO FERREIRA
     Sistema de Gestao Academica
     ---
     ATESTADO DE FREQUENCIA
     Maria Silva
     ---
     Emitido em 2026-11-08

2) Aluno irregular: nenhum documento e nenhum rastro no log
   [erro] Aluno irregular: 2024002
   [erro] Aluno irregular: 2024002
   [erro] Aluno irregular: 2024002
   Registros no log: [HISTORICO - 2024001, DECLARACAO - 2024001, ATESTADO - 2024001]

3) Ciclo de vida da matricula com o padrao State
   situacao inicial: PRE_MATRICULADA
   apos confirmar: CONFIRMADA
   apos trancar: TRANCADA
   apos confirmar: CONFIRMADA
   apos cancelar: CANCELADA
   [erro] Nao e possivel confirmar uma matricula CANCELADA

4) Estado Concluida
   apos confirmar: CONFIRMADA
   apos confirmar: CONCLUIDA
   [erro] Nao e possivel cancelar uma matricula CONCLUIDA
```

## Critérios de sucesso

- Cabeçalho e rodapé em um único lugar: `DocumentoAcademico`.
- Método-template `emitir` declarado `final`.
- Emitir para aluno irregular não deixa rastro no log (registros: apenas as
  três emissões da aluna regular).
- Nenhum `if` nos três métodos públicos de `Matricula`.
- Acrescentar `Concluida` custou dois arquivos.
