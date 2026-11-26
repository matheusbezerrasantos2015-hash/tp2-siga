# ADR-001 — Acesso a dados de aluno por meio de uma interface DAO

- **Status:** aceito
- **Onde está no código:** `Aula7/` (decisão) e `Aula8/` (contrato fechado); usado também em `Aula10/`
- **Observação:** escrevi este registro depois de implementar a decisão, a partir do diagnóstico que fiz na Aula 7 (`Aula7/EVIDENCIAS.md`).

## Contexto

No código inicial da Aula 7, o `ServicoMatricula` fazia duas coisas: validar a média do aluno (regra de negócio) e montar e executar SQL direto no `BancoSimulado` (acesso a dados). Isso trazia três problemas:

1. qualquer mudança na tabela `aluno` obrigava a mexer na classe de regra de negócio, mesmo sem nenhuma regra acadêmica ter mudado (SRP);
2. o serviço dependia da classe concreta `BancoSimulado`, então trocar a tecnologia de persistência obrigava a alterar o serviço (DIP);
3. o mesmo acesso a dados estava repetido em `matricular()` e em `gerarRelatorio()`, com o risco de alterar só um dos dois.

## Decisão

Criei a interface `AlunoDAO`, escrita só com vocabulário do domínio (`inserir`, `buscarPorMatricula`, `listarTodos`, `atualizar`, `remover`), sem nenhum termo de SQL. O serviço (`ServicoMatricula`, depois `ServicoAluno`) recebe um `AlunoDAO` pelo construtor e não conhece nenhuma implementação concreta.

Na Aula 7 provei a troca de implementação com duas classes, `AlunoDAOMemoria` (com `Map`) e `AlunoDAOLista` (com `List`), sem alterar uma linha do serviço. Na Aula 8 o contrato ficou explícito: `inserir` com matrícula repetida lança `IllegalStateException`, e `atualizar` e `remover` com matrícula inexistente lançam `NoSuchElementException`. As regras de validação (nome, matrícula e média de 0 a 10) ficaram só no `ServicoAluno`.

## Consequências

- **Bom:** a regra de negócio não tem SQL e pode ser lida e alterada sem tocar na persistência; trocar `Map` por `List` (ou, no futuro, por um banco de verdade) é só instanciar outra classe em `Main`.
- **Bom:** o acesso a dados deixou de estar duplicado.
- **Custo:** passou a existir uma interface e pelo menos uma classe a mais para manter. Para um sistema tão pequeno isso é bastante estrutura.
- **Problema que apareceu:** na Aula 7 o contrato não dizia o que fazer com matrícula repetida ou inexistente, e as duas implementações se comportaram de forma diferente (`AlunoDAOMemoria` substituía o aluno, `AlunoDAOLista` guardava um duplicado). Só a Aula 8 fechou essa lacuna, e por isso a versão da Aula 7 continua com essa divergência (documentada no Javadoc de `Aula7/src/siga/AlunoDAO.java`).
- **Limite atual:** as implementações guardam tudo em memória; nenhum dado sobrevive ao fim do programa.

## Alternativa descartada

**Manter o SQL dentro do serviço e apenas isolar o código repetido num método privado.** Resolveria a duplicação (problema 3), e daria menos classes. Descartei porque não resolve os problemas 1 e 2: o serviço continuaria dependendo da tecnologia de persistência e mudaria sempre que a estrutura da tabela mudasse. Também impediria provar a troca de implementação, que foi o critério da Aula 7.
