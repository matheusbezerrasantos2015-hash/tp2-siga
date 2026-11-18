# Respostas — Sistema de Emprestimo da Biblioteca

**Nome completo:** Matheus Gabriel Bezerra Santos  
**RA:** Não sei meu RA

---

# Etapa 1 — Diagnóstico

## Problema 1 — a conversão do acervo central

**Em que consiste o problema, e o que acontece quando o sistema cresce:**

O mesmo código de conversão dos dados foi feito em duas classes, mas elas tratam a letra "D" de formas diferentes. Por isso, o mesmo exemplar pode aparecer com situações diferentes. Se o sistema crescer, esse código vai acabar sendo repetido em mais lugares.

**Princípio SOLID violado:**

SRP (Responsabilidade Única), porque as classes fazem suas funções e também cuidam da conversão dos dados.

**Classes afetadas (pelo nome):**

`CatalogoExemplares` e `RelatorioColecao`.

**Que outra dependência indevida essas classes têm em relação ao acervo central, e por que ela é um problema:**

As duas dependem diretamente de `AcervoLegadoWS`, que é de outra equipe. Isso fere o DIP, pois dificulta trocar o serviço e fazer testes.

## Problema 2 — o ciclo de vida de Emprestimo

**Em que consiste o problema, e o que acontece quando o sistema cresce:**

Os estados do empréstimo são controlados por textos (`String`) e vários `if` repetidos. Se surgir um estado novo, será preciso mudar vários métodos, aumentando a chance de erros.

**Princípio SOLID violado:**

OCP (Aberto/Fechado), porque é preciso alterar vários métodos para adicionar um estado. Também prejudica o SRP.

**Classes afetadas (pelo nome):**

`Emprestimo`.

---

# Etapa 4 — Justificativa

## Padrão aplicado ao Problema 1

**O problema que ele resolve (descreva antes de nomear o padrão):**

O sistema precisa usar um serviço antigo que não pode ser alterado e que fornece os dados em um formato diferente do esperado.

**Nome do padrão:**

Adapter (estrutural).

**Alternativa descartada e por que ela não resolveria por completo:**

Alterar o serviço antigo, porque ele pertence a outra equipe. Além disso, o sistema continuaria dependendo diretamente dessa classe.

## Padrão aplicado ao Problema 2

**O problema que ele resolve (descreva antes de nomear o padrão):**

O empréstimo muda de comportamento conforme seu estado, mas as regras estão espalhadas em vários `if`.

**Nome do padrão:**

State (comportamental).

**Alternativa descartada e por que ela não resolveria por completo:**

Usar `enum` no lugar de `String`. Isso evitaria erros de digitação, mas ainda seria necessário alterar os `if` ou `switch` em vários métodos quando surgisse um estado novo.

