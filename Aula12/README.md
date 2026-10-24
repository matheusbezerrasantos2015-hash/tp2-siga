# SIGA - Aula 12: Strategy e Observer

Atividade prática de Técnicas de Programação II (Fatec de Porto Ferreira).

## Como compilar e executar

Requer JDK 17 ou superior, a partir da pasta raiz do projeto:

```
javac -d out src/siga/*.java
java -cp out siga.Main
```

## Etapa 1 - As duas cadeias de condicionais e onde divergem

O cálculo do desconto está escrito duas vezes:

| Local                                         | Bolsista | Servidor | Egresso |
|-----------------------------------------------|----------|----------|---------|
| `Matricula.calcularValor`                     | 50%      | 20%      | 10%     |
| `GeradorBoleto.calcularValorDoBoleto`         | 50%      | 20%      | **ausente** |

Quando a política do egresso foi criada, só `Matricula` foi atualizada. O
`GeradorBoleto` continuou sem ela e cobra o valor cheio de quem é egresso.

**Aluna afetada: Ana Pereira (egresso).** Com a mensalidade base de R$ 800,00,
ela paga **R$ 720,00 na matrícula** (10% de desconto) e **R$ 800,00 no boleto**
(sem desconto).

## Etapa 2 - Interface e políticas concretas

`PoliticaDesconto` define `aplicar(valorBase)`. As quatro políticas concretas
são `SemDesconto`, `DescontoBolsista` (50%), `DescontoServidor` (20%) e
`DescontoEgresso` (10%). Cada percentual existe em uma única constante, dentro
da sua própria classe.

## Etapa 3 - Estratégia injetada

- `Matricula` recebe a `PoliticaDesconto` no construtor e apenas delega.
- `GeradorBoleto.emitir` recebe a mesma política e delega, então os dois pontos
  sempre calculam o mesmo valor.
- Nenhuma das duas classes tem `if` de desconto.
- Quem escolhe a política é quem monta a matrícula: `SeletorPoliticaDesconto`,
  usado pela `FachadaMatricula` e pelo `Main`. A decisão não voltou para dentro
  da `Matricula`.

## Etapa 4 - Observer

`OuvinteMatricula` define `aoMatricular(matricula, turmaLotada)`.
`PublicadorMatricula` guarda os ouvintes, percorre uma **cópia da lista** e
protege **cada chamada** com `try/catch`: a falha de um ouvinte é registrada no
console e os demais continuam. A `FachadaMatricula` manteve os passos (criar,
gravar) e passou a só publicar o fato consumado. `NotificadorEmail`,
`PainelVagas`, `RegistroAuditoria` e `AvisoCoordenacao` agora são ouvintes.

## Etapa 5 - Quinta reação

Foi criada a classe `NotificadorSms` e acrescentada uma linha de registro no
`Main` (`publicador.registrar(new NotificadorSms());`). A `FachadaMatricula`
não foi alterada nesse commit. As demais linhas novas no `Main` apenas
demonstram o resultado.

## Saída da execução (evidência)

```
=== SIGA - Aula 12: Strategy e Observer ===

1) Desconto calculado pela mesma politica na matricula e no boleto
   Maria Silva    matricula: R$ 400,00
   [boleto] Maria Silva: R$ 400,00
   Ana Pereira    matricula: R$ 720,00
   [boleto] Ana Pereira: R$ 720,00

2) Reacoes a matricula como observadores
   [e-mail] confirmacao para Maria Silva
   [painel] vagas de DSM-2A atualizadas
   [auditoria] MATRICULA - 2024001

   Turma lotada, com o aviso a coordenacao falhando:
   [e-mail] confirmacao para Ana Pereira
   [painel] vagas de DSM-2B atualizadas
   [falha no ouvinte] AvisoCoordenacao: servico de aviso indisponivel para DSM-2B
   [auditoria] MATRICULA - 2024003

3) Quinta reacao: uma classe nova e uma linha de registro
   [e-mail] confirmacao para Maria Silva
   [painel] vagas de DSM-2C atualizadas
   [auditoria] MATRICULA - 2024001
   [sms] confirmacao para Maria Silva

   Matriculas gravadas: 3
```

Pontos a observar na saída:

- Ana Pereira paga **R$ 720,00** na matrícula e **R$ 720,00** no boleto.
- Com o `AvisoCoordenacao` falhando em DSM-2B, a falha é registrada e a
  **auditoria continua acontecendo**.
- Depois do registro da quinta reação, o `[sms]` aparece sem nenhuma mudança na
  fachada.

## Critérios de sucesso

- Ana Pereira paga o mesmo valor na matrícula e no boleto.
- Cada percentual de desconto aparece em um único lugar.
- A quinta reação custou uma classe nova e uma linha de registro.
- Com o aviso à coordenação falhando, a auditoria continua acontecendo.
