## Curso Android Kotlin - Proway - Capgemini

![Kafka](https://img.shields.io/badge/Android-Kafka-lightgreen)

### Atividade 01 
- Foi criada uma classe Empresa.
- Nesta model, foi implementada uma lista mutável de funcionários.
- Na funcionalidade de cadastrar, verifica-se a existência do funcionário, pelo CPF, antes da inclusão.
- Na funcionalidade de listar, são exibidos os CPFs, nomes e emails dos funcionários cadastrados.
- Na funcionalidade de busca, é utilizado o CPF e, uma vez encontrado, também é exibido o CPF, nome e email.
- Na funcionalidade de alterar, verifica-se a existência do funcionário e, uma vez encontrado, conforme índice na lista, é atribuída uma nova instância de funcionário com os novos dados.
- Na funcionalidade de remover, verifica-se a existência do funcionário e, uma vez encontrado, é retirado da lista.
- Na função main consta 
  - Uso do Scanner para receber dados.
  - Inicialização da Empresa
  - Menu com todas as funcionalidades (Cadastro, listagem, pesquisa, alteração, remoção e finalização)
  - Nas funcionalidades existem validações, conforme abaixo
    - Para os CPFs, existe validação de tamanho 11.
    - Para os nomes, existe validação de tamanho mínimo 3 caracteres.
    - Para os emails, existe validação de sufixo @capgemini.com.