# prova-quali

Ajustando o banco de dados:
usei o MySql Workbench Versão 6.3, link para download: https://dev.mysql.com/downloads/file/?id=474210.

1 - Na aba do lado esquerdo, clique em Users and Privileges 

2 - Clique em Add count, na aba "Login", no campo Login Name digite: prova, em hosts matching digite: localhost e um password digite: prova
3 - Na aba "Schema Privileges" clique no botão Add Entry, na janela que irar abrir clique no botão Ok

4 - Irá habilitar as seleções de permissões.

5 - Clique no botão Select "ALL"

6 - Clique no botão Apply

------------------------------------------------------------------------------------------------------------------------------------------
Executando o jar:


OBS: O arquivo Autorizador.jar está dentro da pasta target, Para fazer o download clique na pasta target, clique em Autorizador.jar
e no botão Download.

1 - Coloque o Autorizador.jar em uma pasta de fácil acesso, no meu caso criei uma pasta chamada prova dentro de C:
Exemplo: C:\prova

2 - Abra o cmd, caminhe até a pasta onde o arquivo .jar se encontra.
Exemplo C:\prova>

3 - Para executar o jar e cadastrar uma nova autorização digite: [ CADASTRAR ] [ PROCEDIMENTO ] [ IDADE ] [ SEXO ] [ PERMITIDO ]
Exemplo: C:\prova>java -jar Autorizador.jar Cadastrar 1234 10 M SIM

4- Para executar o jar e Validar uma autorizacao digite: [ VALIDAR ] [ PROCEDIMENTO ] [ IDADE ] [ SEXO ] 
Exemplo: C:\prova>java -jar Autorizador.jar Validar 1234 10 M 
