# SIGA — Aula 8: CRUD completo e Etapa 1

Técnicas de Programação II (TP2) · Fatec de Porto Ferreira

CRUD completo de alunos em Java (JDK 17 ou superior), organizado em camadas.

## Estrutura

```
Aula8/
├── src/siga/
│   ├── Aluno.java            entidade de domínio
│   ├── AlunoDAO.java         contrato de acesso a dados
│   ├── AlunoDAOMemoria.java  implementação em memória do DAO
│   ├── ServicoAluno.java     regras de domínio (validação centralizada)
│   └── Main.java             apresentação e tratamento de exceções
└── evidencias/saida-execucao.txt
```

## Como compilar e executar

Na pasta `Aula8`:

```bash
mkdir out
javac -encoding UTF-8 -d out src/siga/*.java
java -cp out siga.Main
```

## O que foi feito (etapas da atividade)

1. **CRUD no DAO:** `inserir` (impede matrícula duplicada), `buscarPorMatricula`,
   `listarTodos`, `atualizar` e `remover`.
2. **Camada de serviço:** `cadastrar`, `consultar`, `listar`, `alterar` e `excluir`,
   todas usando o método privado `validar(Aluno)`, sem duplicação de regras.
3. **Três deslizes corrigidos:**
   - validação da média duplicada: agora existe só no serviço (limite 0 a 10);
   - exclusão sem verificação: `remover` e `excluir` lançam `NoSuchElementException`
     quando a matrícula não existe;
   - coleção exposta: `listarTodos` devolve cópia defensiva (`new ArrayList<>(armazem)`).
4. **Exceções na apresentação:** o `Main` captura as exceções do serviço e mostra
   mensagens claras, sem blocos `catch` vazios.
5. **Evidência:** a saída da execução está em `evidencias/saida-execucao.txt`.

## Exceções usadas

| Situação | Exceção |
|---|---|
| Dados inválidos (nome, matrícula ou média) | `IllegalArgumentException` |
| Matrícula já cadastrada | `IllegalStateException` |
| Matrícula inexistente (consulta, alteração, exclusão) | `NoSuchElementException` |
