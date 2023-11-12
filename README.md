# Sistema de RH FRIGG
Nós da
<span style="color:aqua;">FRIGG</span>
,  somos uma equipe de desenvolvedores, que nos unimos para oferecer uma
ferramenta de fácil uso.
Alguma de suas funções são:
* Demitir / Contratar contribuintes.
* Criar atividades de Desenvolvimento / Treinamento.
* Gerar folha de pagamento.
* Atribuir Notas e Feedbacks a seus contribuintes.

Sistema é construido em JAVA, utilizando SpringBoot.

## Acesse para testar a API
http://34.195.14.39:7000/swagger-ui/index.html#/

### Arquitetura
https://drive.google.com/file/d/1POjH3_ys3712xuKyak4DnA93TIJFTApc/view?usp=sharing

### Caso de uso
https://drive.google.com/file/d/1WAxQ-0RPivijUIVY7fygopdTZ82key6K/view?usp=sharing

### Classes
https://drive.google.com/file/d/13kyZG0fh5jCwbEpgr5tYrzq4EnshFx25/view?usp=sharing


### Padronização DataBase
Afim de manter o banco de dados padrão qualquer nova implentação deve seguir
está padronização:

| Prefixo |     Tipo     |   Conteúdo    |
|:-------:|:------------:|:-------------:|
|   id    |   Numeric    | Identificação |
|   cd    |   Varchar    |    Código     |
|   dt    |   Datatime   |     Datas     |
|   nu    |   Varchar    |    Números    |
|   ds    |   Varchar    |   Descrição   |
|   fl    | Numeric Char |     Flag      |
|   seq   |   Numeric    |  Sequencias   |
|   nm    |   Varchar    |     Nomes     |
|   vl    |   Numeric    |    Valores    |
|   qt    |   Numeric    |  Quantidade   |
|   hr    |   Varchar    |     Horas     |
|   sg    |   Varchar    |    Siglas     |
|   pc    |   Numeric    |  Percentual   |
|   dc    |   Numeric    |    Duração    |
|   tp    | Numeric Char |     Tipo      |
|   em    |   Varchar    |     Email     |

#
Em caso de dados que necessitam de serem manipulados para possuirem valor agregado é adicionado uma sigla 
indicando o que este dado representa

| Prefixo |   Conteúdo   |
|:-------:|:------------:|
|   ben   |  Benefícios  |
|   bon   | Bonificações |
|   com   |  Comissões   |
|   enc   |   Encargos   |


Caso não tenha o que deseja, contate algum membro da FRIGG,
para padronização futura.
