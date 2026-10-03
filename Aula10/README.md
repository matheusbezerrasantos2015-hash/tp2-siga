# SIGA — Aula 10: Adapter e Facade

Técnicas de Programação II (TP2) · Fatec de Porto Ferreira

Integração do sistema legado (Adapter) e fachada de matrícula (Facade).
Java (JDK 17 ou superior).

## Como compilar e executar

Na pasta `Aula10`:

```bash
mkdir out
javac -encoding UTF-8 -d out src/siga/*.java
java -cp out siga.Main
```

## Estrutura

```
Aula10/
├── src/siga/
│   ├── SecretariaLegadoWS.java        componente externo (não alterado)
│   ├── FonteDeAlunos.java             abstração usada pelo domínio
│   ├── AdaptadorSecretariaLegado.java Adapter (única conversão do formato legado)
│   ├── ImportadorAlunos.java          cliente de FonteDeAlunos
│   ├── RelatorioSituacao.java         cliente de FonteDeAlunos
│   ├── FachadaMatricula.java          Facade do subsistema de matrícula
│   ├── TelaMatricula.java             apresentação (só chama a fachada)
│   └── ...                            entidades, DAOs e demais peças do subsistema
└── evidencias/
    ├── saida-antes.txt                execução do código inicial
    └── saida-depois.txt               execução após a refatoração
```

## Etapa 1 — A duplicação da conversão e onde ela diverge

O legado devolve uma matriz de texto: coluna 0 é a matrícula, coluna 1 o nome e
coluna 2 a situação em letra (`A` ou `I`). A conversão para `Aluno` estava escrita
em dois lugares:

- `ImportadorAlunos.importar()`: `aluno.setAtivo("A".equals(linha[2]))`
- `RelatorioSituacao.imprimir()`: `aluno.setAtivo("A".equalsIgnoreCase(linha[2]))`

**Divergência:** o relatório trata a letra minúscula e o importador não. A aluna
2024003 vem do legado com situação `a`, então aparecia **inativa** na importação e
**ativa** no relatório (veja `evidencias/saida-antes.txt`).

## Etapas 2 e 3 — Adapter

A conversão passou a existir só em `AdaptadorSecretariaLegado`, que implementa
`FonteDeAlunos` e recebe o `SecretariaLegadoWS` por composição. A comparação com
`equalsIgnoreCase` é aplicada uma única vez. `ImportadorAlunos` e
`RelatorioSituacao` dependem apenas de `FonteDeAlunos`; a palavra
`SecretariaLegadoWS` não aparece em nenhum dos dois. Ela aparece só no adaptador e
no `Main`, que monta os objetos.

## Etapa 4 — Facade

A orquestração da matrícula (validar CPF, localizar aluno, verificar vaga,
calcular desconto, registrar, reservar vaga e notificar) foi movida para
`FachadaMatricula.matricular(...)`. A `TelaMatricula` agora tem um único
colaborador e só chama a fachada e trata os erros.

## Etapa 5 — Por que cada padrão

**Adapter.** O problema era que o domínio precisava de alunos, mas o legado
entrega uma matriz de texto com a situação codificada em letra, e esse
componente é de outra equipe e não pode ser alterado. Cada cliente que quis
usar esses dados teve de aprender o formato e reescrever a conversão, e as
duas cópias divergiram. Precisávamos de um ponto único que traduzisse a
interface existente na interface esperada pelo domínio, sem tocar no
componente original. Isso é o **Adapter**: `AdaptadorSecretariaLegado`
converte o formato uma só vez, e os clientes passam a depender da abstração
`FonteDeAlunos`.

**Facade.** O problema era que a tela de matrícula precisava conhecer seis
classes e a ordem correta de chamá-las, o que é regra de negócio dentro da
camada de apresentação. Qualquer nova tela (por exemplo, matrícula em lote)
teria de copiar essa sequência, e as cópias divergiriam como aconteceu com a
conversão. Precisávamos de uma porta única que escondesse o subsistema e
guardasse a sequência. Isso é a **Facade**: `FachadaMatricula` expõe uma
operação simples e a tela só a chama.

## Critério de sucesso

- A aluna 2024003 aparece **ativa** na importação e no relatório
  (`evidencias/saida-depois.txt`).
- `SecretariaLegadoWS.java` permanece idêntico ao original.
- As matrículas e os erros são os mesmos de antes: matrícula de Maria Silva com
  50% de desconto, `CPF inválido.`, `Aluno não encontrado: 2024009` e
  `Turma sem vagas: DSM-2B`.
