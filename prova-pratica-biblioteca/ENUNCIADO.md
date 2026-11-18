# Prova Prática Individual — Sistema de Emprestimo da Biblioteca

**Técnicas de Programação II · 18 de novembro de 2026 · 19h10 às 22h20 · peso 25%**

CST em Desenvolvimento de Software Multiplataforma — Fatec de Porto Ferreira

Nome: ______________________________________________  RA: ______________

## 1. Regras

**Prova individual.** É vedada qualquer comunicação com colegas, por qualquer meio.

**Consulta permitida:** as apostilas da disciplina, os seus próprios repositórios
das atividades práticas e a documentação oficial do Java.

**Consulta proibida:** material de colegas, fóruns, sites de perguntas e respostas
e qualquer assistente de inteligência artificial — Copilot, ChatGPT, Gemini,
autocompletar por IA da IDE ou similar. Desligue o plugin antes de começar.

> Diante de indício de uso de I.A., a nota fica suspensa e você recebe uma
> devolutiva escrita descrevendo o que foi observado. Indício não é acusação: a
> devolutiva descreve, você responde, e a nota é decidida depois.

**O código entregue deve compilar.** O que não compila é avaliado pela leitura,
mas não alcança o nível máximo das Etapas 2 e 3.

**Não altere** `AcervoLegadoWS.java` nem `VerificacaoRapida.java`.

## 2. O que você recebeu

```
prova-pratica-biblioteca/
├── ENUNCIADO.md            este documento
├── .gitignore              já ignora bin/ e target/
├── RESPOSTAS.md            formulário das Etapas 1 e 4
└── src/siga/
    ├── Leitor.java, Exemplar.java        entidades, prontas
    ├── AcervoLegadoWS.java      NÃO ALTERAR
    ├── CatalogoExemplares.java    PROBLEMA 1
    ├── RelatorioColecao.java   PROBLEMA 1
    ├── Emprestimo.java    PROBLEMA 2
    ├── VerificacaoRapida.java   brinde, NÃO ALTERAR
    └── Main.java            demonstra os dois problemas
```

Comece compilando e executando:

```
javac -encoding UTF-8 -d bin src/siga/*.java
java -cp bin siga.Main
```

## 3. O brinde: a verificação rápida

Ao final da execução do `Main`, a classe `VerificacaoRapida` confere oito coisas
e imprime verde ou vermelho para cada uma. **Ela não vale nota** e não depende de
biblioteca nem de internet.

No ponto de partida você vai ver **um vermelho e sete verdes**:

- o **vermelho** é a prova de que o Problema 1 existe. Torná-lo verde é o critério
  de sucesso da Etapa 2;
- os **sete verdes** fixam o ciclo de vida de hoje. Eles devem **continuar verdes**
  depois da Etapa 3: se um virar vermelho, a sua refatoração mudou o comportamento,
  e o enunciado pede que ele seja preservado.

Use o brinde como conferência, não como tarefa. Ele não prova que você aplicou
padrão nenhum — isso se avalia lendo o seu código.

## 4. O cenário

O módulo de Emprestimo da Biblioteca do SIGA funciona e está em produção. Cresceu por acréscimos
sucessivos, e hoje apresenta dois problemas de estrutura que a coordenação pediu
para resolver antes da próxima atualização.

**O primeiro** aparece na integração com o acervo central, um serviço mantido por
outra equipe que devolve os exemplares como uma matriz de texto, com a situação
codificada em letra. O sistema não pode alterar esse serviço. A conversão do
formato foi escrita em dois lugares, e as duas versões já divergem: uma trata a
letra minúscula e a outra não, de modo que o TOM-2003 aparece indisponível no
catálogo e disponível no relatório.

**O segundo** aparece no ciclo de vida de Emprestimo, que passa por RESERVADO,
EMPRESTADO e ENCERRADO. As regras de transição foram escritas como condicionais
sobre um campo de texto, espalhadas pelos métodos públicos, e ninguém consegue ler
o ciclo de vida inteiro em um único lugar.

## 5. As quatro etapas

### Etapa 1 — Diagnóstico (2,5 pontos)

Preencha os campos da Etapa 1 do `RESPOSTAS.md`. Para **cada um** dos dois
problemas, os três campos são obrigatórios:

- **em que consiste** o problema e o que acontece quando o sistema cresce;
- **qual princípio SOLID** ele viola;
- **quais classes** são afetadas, pelo nome.

Sobre o Problema 1 há um quarto campo: além da duplicação, que **outra dependência
indevida** as duas classes têm em relação ao acervo central, e por que ela é um
problema.

### Etapa 2 — Padrão estrutural (3,0 pontos)

Aplique o padrão estrutural adequado ao Problema 1, de modo que a conversão do
formato exista em **um único lugar** do projeto e que as classes que consomem os
exemplares deixem de depender da classe concreta do serviço legado.

`AcervoLegadoWS` não pode ser alterada. Ao final:

- o nome `AcervoLegadoWS` não deve ser **referenciado** em `CatalogoExemplares` nem em
  `RelatorioColecao`;
- o TOM-2003 deve ter a mesma situação nos dois pontos do sistema — é o vermelho
  do brinde virando verde.

### Etapa 3 — Padrão comportamental (3,0 pontos)

Aplique o padrão comportamental adequado ao Problema 2, de modo que cada fase do
ciclo de vida saiba o que é permitido nela e para onde se pode ir a partir dela.

Ao final:

- os três métodos públicos de `Emprestimo` devem ter **uma linha cada, sem
  nenhum `if`**;
- deve ser possível ler o ciclo de vida abrindo as classes de fase;
- **o comportamento externo deve permanecer exatamente o mesmo.**

Sobre a última exigência, para que não reste dúvida: `getSituacao()` deve continuar
devolvendo as mesmas cadeias de hoje — `"RESERVADO"`, `"EMPRESTADO"` e
`"ENCERRADO"`, em maiúsculas — e as transições permitidas e proibidas continuam as
mesmas. Os sete verdes do brinde conferem isso.

### Etapa 4 — Justificativa (1,5 pontos)

Preencha os campos da Etapa 4 do `RESPOSTAS.md`. Para **cada um** dos dois padrões
aplicados, os três campos são obrigatórios:

- **o problema** que o padrão resolve, descrito antes de nomeá-lo;
- **o nome** do padrão;
- **a alternativa que você descartou** e por que ela não resolveria por completo.
  Para o Problema 1, considere alterar o serviço legado. Para o Problema 2,
  considere trocar a `String` por um `enum`.

## 6. O que não é avaliado

- A aparência das mensagens impressas, exceto o retorno de `getSituacao()`, que é
  contrato e está fixado na Etapa 3.
- O desempenho do código.
- A quantidade de padrões aplicados: aplicar padrões além dos dois pedidos não
  aumenta a nota.
- O resultado do brinde: ele orienta você, não compõe a nota.

## 7. Como a nota é formada

A nota é a soma das quatro etapas. Em cada uma avalia-se o resultado **e** o
raciocínio: uma solução parcialmente correta, com a intenção clara e justificada,
vale mais do que uma solução ausente.

O enunciado **não nomeia os padrões** a aplicar. Identificar qual padrão resolve
cada problema é parte do que se avalia. Uma escolha diferente da esperada pode
receber pontuação integral, desde que resolva o problema descrito e esteja
justificada na Etapa 4.

## 8. Entrega

A entrega é pelo **GitHub**. O professor indica no início da prova o link da
tarefa; aceitá-lo cria o seu repositório já com este projeto dentro.

Trabalhe normalmente e envie o que fez de tempos em tempos:

```
git add .
git commit -m "etapa 2"
git push
```

**Vale o último commit enviado até as 22h20.**

Enviar cedo e com frequência é a sua garantia, não uma exigência: um envio às
21h30 protege o seu trabalho se o computador travar no fim. Os commits **não
valem nota** — a nota são as quatro etapas da Seção 5.

> **Se o laboratório estiver sem internet**, o professor anuncia a alternativa
> no início da prova: apagar a pasta `bin/`, compactar a pasta do projeto em
> `RA_Nome.zip` e enviar pelo MS Teams. Não improvise — espere o anúncio.

## 9. Antes de entregar, confira

- [ ] `javac -encoding UTF-8 -d bin src/siga/*.java` compila sem erro.
- [ ] `java -cp bin siga.Main` executa.
- [ ] O brinde mostra **oito verdes**.
- [ ] O `RESPOSTAS.md` está na raiz e **nenhum campo** das Etapas 1 e 4 ficou vazio.
- [ ] O seu último `git push` foi aceito — confira o repositório no navegador.
- [ ] Se a entrega for por Teams, o `.zip` está nomeado como `RA_Nome.zip`.
