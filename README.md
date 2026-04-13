## Curso Android Kotlin - Proway - Capgemini

![Kafka](https://img.shields.io/badge/Android-Kafka-lightgreen)

### Atividade 02 - BRADESpesas
- Foram criadas 5 telas, sendo elas: Splash, Home, Ganhos, Despesas e Sonhos.
- Na tela Splash, foi configurada uma duração de 3 segundos.
- Foi implementado um Navigation Bar com 4 opções.
- A tela acionada após a Splash é a Home, constando a barra de navegação mencionada acima.
- Nesta tela consta o somatório de ganhos e despesas, assim como o saldo.
- Nesta tela ainda, é onde realizam-se sonhos, com impacto no saldo.
- Caso o usuário queira saber informações sobre ganhos, despesas e sonhos, deverá navegar na barra inferior.
- Na tela de ganhos e despesas, os itens aparecem em uma lista constando o seu detalhamento.
- Na tela de sonhos, os itens também aparece com o seu detalhamento, mas existe uma funcionalidade de contexto.
- Para os sonhos, existe uma diferenciação de sonhos já realizados, realizáveis, prestes a realizar e não realizáveis.
- Os sonhos já realizados são pretos, os realizáveis são verdes, os prestes a realizar (saldo 70% do sonho) são azuis e os não realizáveis são vermelhos.
- Conforme os sonhos vão sendo realizados e o saldo vai sendo reduzido, os sonhos podem mudar de categoria e de cor.
- Não foi solicitado necessariamente que fosse feito um CRUD de ganhos, despesas e sonhos, mas após a atividade será implementada uma evolução com essa possibilidade, visando dar uma dinamicidade maior na aplicação.
- A ideia é que possa ser incluído 1 ou mais ganhos de salário ao mês, N vendas ou compras no mês, dentre outros lançamentos.
- Conforme está hoje, os valores ainda estão em memória.
- Outra evolução seria implementar integração com banco de dados, porém, como ainda não chegamos nesta parte, não avancei neste sentido.