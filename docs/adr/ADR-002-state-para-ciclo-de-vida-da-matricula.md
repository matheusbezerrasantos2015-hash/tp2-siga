# ADR-002 — Ciclo de vida da matrícula modelado com o padrão State

- **Status:** aceito
- **Onde está no código:** `Aula13/src/siga/` (`Matricula`, `EstadoMatricula` e os estados `PreMatriculada`, `Confirmada`, `Trancada`, `Concluida`, `Cancelada`)
- **Observação:** escrevi este registro depois de implementar a decisão, a partir da Etapa 4 e da Etapa 5 de `Aula13/README.md`.

## Contexto

Na versão inicial da Aula 13, a situação da matrícula era uma `String` e as regras de transição estavam espalhadas em condicionais dentro dos três métodos `confirmar`, `trancar` e `cancelar` de `Matricula`. Havia dois problemas: acrescentar uma situação nova (`CONCLUIDA`) obrigava a alterar os três métodos, e o nome da situação, sendo texto, podia ter erro de digitação que só apareceria em execução.

## Decisão

Cada situação virou uma classe que estende `EstadoMatricula`: `PreMatriculada`, `Confirmada`, `Trancada`, `Concluida` e `Cancelada`. A classe base faz toda transição falhar com `IllegalStateException`, e cada estado concreto sobrescreve só as transições que oferece. A `Matricula` apenas delega: seus três métodos públicos têm uma linha cada, sem `if`. Quem troca o estado são os próprios estados, por meio de `matricula.mudarPara(new ...)`, que tem visibilidade de pacote.

Para acrescentar `Concluida` com apenas dois arquivos (`Concluida.java` novo e `Confirmada.java` alterado), reaproveitei o evento `confirmar()`: o segundo `confirmar` de uma matrícula já confirmada a conclui.

## Consequências

- **Bom:** acrescentar `Concluida` custou 2 arquivos, em vez de alterar os 3 métodos de `Matricula`; `Matricula` e `EstadoMatricula` não mudaram nessa etapa.
- **Bom:** os nomes das situações passaram a ser classes, verificadas pelo compilador, e as transições proibidas falham todas com a mesma mensagem.
- **Custo:** são cinco classes pequenas no lugar de uma classe com condicionais. Ler "o que acontece em CONFIRMADA" exige abrir a classe `Confirmada`, e a visão de todas as transições só existe no diagrama do `README.md` e no Javadoc de `EstadoMatricula`.
- **Ponto fraco assumido:** `confirmar()` significa coisas diferentes conforme o estado (confirma, conclui ou retoma uma matrícula trancada). O nome do método não diz isso; por isso escrevi no Javadoc de `Matricula.confirmar()`. Um evento próprio `concluir()` seria mais claro, mas exigiria 4 arquivos em vez de 2 (método novo em `Matricula` e método padrão em `EstadoMatricula`).
- **Limite atual:** cada transição cria um objeto de estado novo (os estados não guardam dados), e não há registro do histórico de mudanças da matrícula.

## Alternativa descartada

**Manter a situação como `String` (ou enum) e as regras de transição como condicionais dentro de `Matricula`.** Seria uma única classe, mais fácil de ler de uma vez, e para um ciclo de vida de três situações teria sido suficiente. Descartei porque, com a quarta e a quinta situação, as regras ficaram espalhadas pelos três métodos e cada situação nova exigia alterar todos eles (violação do OCP). Com texto, havia ainda o risco de erro de digitação detectado só em execução.
