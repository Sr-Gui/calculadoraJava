# calculadoraJava

Este projeto consiste em uma calculadora Java que utiliza a Notação Polonesa Reversa (RPN) para tratar a precedência das operações matemáticas. Foram implementadas as operações de soma, subtração, multiplicação, divisão, porcentagem, potenciação e raiz com qualquer expoente. A interface gráfica foi desenvolvida com JavaFX.

## RPN (Notação Polonesa Reversa)

A RPN é uma notação matemática na qual os operadores seguem seus operandos. Na RPN, os operadores vêm após seus operandos. Por exemplo, a expressão `1 + 2` em RPN seria escrita como `1 2 +`.

Para converter uma expressão infixa para pós-fixa (RPN), é necessário usar uma estrutura de dados de pilha. 
Por exemplo, `1 + 2 * 3 / 4 * 5 - 6 ⇒ 1 2 3 * 4 / 5 * + 6 -`

O algoritmo de conversão infixToRPN foi baseado em [Outra aplicação: notação polonesa](https://www.ime.usp.br/~pf/algoritmos/aulas/pilha.html).

## Desenvolvimento

Este projeto foi desenvolvido para a disciplina de Programação Orientada a Objetos em Java. 

- Desenvolvedor: Guilherma Souza Rocha
- Professor: Jotair Elio Kwiatkowski Junior
- Disciplina: POO - MAC2 - Unicentro

## Estrutura do Projeto

- `src`: Contém os arquivos fonte do projeto.
- `lib`: Contém as dependências do projeto.
- `bin`: Contém os arquivos compilados gerados a partir do código fonte.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
