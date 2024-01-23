import java.sql.*; //Importação da biblioteca do Banco de dados SQL
import java.util.*; //Importação da biblioteca Util do java

public class UpdateMySQL {//Criação do objeto UpdateMySQL
    public static void main(String[] args) {
        /* Declaração do methodo executor main
        *public: porque podera ser importado por outros objetos/classes.
        *static: porque o methodo nao podera ser alterado.
        *void pois este é um methodo sem retorno.
        * @param Strings[] poderao ser invocados metodos do tipo String e matrizes [] 
        */
        String status = "Nada aconteceu ainda..."; //Criação da variavel status
        Connection conn = App.conectar();  //Invcação da variavel conn do arquivo app, o qual sera responsavel pela conexão com o banco de dados.
        Scanner scnRespostas = new Scanner(System.in); //Criação de Scanner
        Scanner scnInput = new Scanner(System.in);  //Criação de Scanner
        Scanner scnSenha = new Scanner(System.in);  //Criação de Scanner
        System.out.print("\033[H\033[2J"); //Cleaner
        System.out.flush(); //Cleaner
        System.out.println("Digite o login:"); //Exibição para o usuario
        String strBusca = scnInput.nextLine(); //Interação com o usuario para atribuir valor a string strBusca
        System.out.print("\033[H\033[2J"); //Cleaner 
        System.out.flush(); //Cleaner
        System.out.println("Digite sua senha:"); //Exibição para o usuario
        String strSenha = scnSenha.nextLine(); //Interação com o usuario para atribuir valor a string strSenha
        try { //Criação da ação do tipo try
            String strSqlSelect = "select * from `mysql_connector`.`tbl_login` where `login` = '" + strBusca
                    + "' and `senha` = '" + strSenha + "' ;"; //String com o comando que sera injetado no Banco de dados SQL
            Statement stmSql = conn.createStatement(); //Criação de Statement conectado com o banco de dados 
            ResultSet rsSql = stmSql.executeQuery(strSqlSelect); //Criação do executor do comando inserido na String strSqlSelect
            String nome = ""; //String vazia

            if (rsSql.next()) { //Criação de ação do tipo condicional. caso a variavel rsSql tenha algum valor -->
                System.out.print("\033[H\033[2J"); //Cleaner
                System.out.flush(); //Cleaner
                status = "Login concluido. Entrando..."; //Atualização da variavel status
                nome += "[" + rsSql.getString("nome") + "] "; //Dando o valor para a variavel nome, com o valor que for encontrado no banco de dados.
            } /*fechamento de if */ else { //Caso o if assim nao ocorra -->
                throw new ArithmeticException("Login ou senha incorretos!"); //Criação de nova exceção aritimetica
            } //fechamento de else
            System.out.println(status); //Exibiçao da variavel status
            Thread.sleep(2000); //pause no codigo de 2000 milisegundos
            System.out.print("\033[H\033[2J"); //Cleaner
            System.out.flush(); //Cleaner
            System.out.println("Bem vindo! " + nome); //Exibição de mensagem mais o valor da variavel noeme
            System.out.println("\n\nDeseja fazer alguma(s) alteração em seu Nome e/ou senha? [s] ou [n]"); //Exibião de mensagem
            String strRespostas = scnRespostas.nextLine(); //Interação do usuario para atribuir valor para String
            if (strRespostas.equals("s") || strRespostas.equals("S")) { //Criação de ação do tipo condicional. se o valor da variavel strRespostas for "s" ou "S" -->
                System.out.print("\033[H\033[2J"); //Cleaner
                System.out.flush(); //Cleaner
                System.out.println(
                        "Qual informaçao você deseja alterar digite os numeros para selecionar a opção:\n[1] - Nome\n[2] - Senha\n[3] - Nome & Senha\n[4] - Sair"); //Exibição de mensagem
                strRespostas = scnRespostas.nextLine(); //Interação do usuario para atribuir valor para String
                switch (strRespostas) { //Criação de ação do tipo escolha
                    case "1": //Caso o valor de strRespostas for igual a "1"
                        try { //Criação da ação do tipo try
                            System.out.print("\033[H\033[2J"); //Cleaner
                            System.out.flush(); //Cleaner
                            System.out.println("Login atual: " + strBusca + "\n\n Nome atual: " + nome 
                                    + "\n\nPara qual nome você deseja alterar?"); //Exibição de mensagem mais o valor da variavel strBusca e nome
                            String strAltNome = scnRespostas.nextLine(); //Interação do usuario para atribuir valor para String
                            String strSqlUpdate = "UPDATE `mysql_connector`.`tbl_login` SET `nome` = '" + strAltNome
                                    + "' WHERE (`login` = '" + strBusca + "');";  //String com o comando que sera injetado no Banco de dados SQL
                            stmSql.addBatch(strSqlUpdate); //Criação de receptaculo para o comando do sql
                            stmSql.executeBatch(); //Executor do receptaculo anterior
                            stmSql.close(); //Fechamento do receptaculo
                            System.out.print("\033[H\033[2J"); //cleaner
                            System.out.flush(); //Cleaner
                            status = "Alteração feita com sucesso!"; //Atualização da variavel status
                        } /*Fechamento de try */ catch (Exception e) { //Criação de ação do tipo catch. Exeption e: criação de variavel que recebera o valor de qualquer erro
                            System.err.println("Algo deu errado " + e); //exibição de mensagem mais a variavel e
                        } // Fechamento de catch
                        break; //Quebra de ação
                    case "2": //Caso o valor de strRespostas for igual a "2"
                        try { //Criação da ação do tipo try
                            System.out.print("\033[H\033[2J"); //Cleaner
                            System.out.flush(); //Cleaner
                            System.out.println("Login atual: " + strBusca + "\nDigite a senha atual para permissão: "); //Exibição de mensagem mais o valor da variavel strBusca
                            String strAltSenha = scnRespostas.nextLine(); //Interação do usuario para atribuir valor para String
                            if (strAltSenha.equals(strSenha)) { //Criação de estrutura de condição, se a String strAltSenha for igual a String strSenha -->
                                System.out.print("\033[H\033[2J"); //Cleaner
                                System.out.flush(); //Cleaner
                                System.out.println("Permição concedida! Iniciando processo..."); //Exibiçao de mensagem
                                Thread.sleep(1000); //Pause no codigo de 1000 milisegundos
                                System.out.print("\033[H\033[2J"); //cleaner
                                System.out.flush(); //Cleaner
                                System.out.println("Senha atual: " + strSenha + "\n\nDigite a nova senha! "); //Exibição de mensagem mais a String strSenha
                                strAltSenha = scnRespostas.nextLine();  //Interação do usuario para atribuir valor para String
                                String strSqlUpdate = "UPDATE `mysql_connector`.`tbl_login` SET `senha` = '"
                                        + strAltSenha + "' WHERE (`login` = '" + strBusca + "');"; //String com o comando que sera injetado no Banco de dados SQL
                                stmSql.addBatch(strSqlUpdate);  //Criação de receptaculo para o comando do sql
                                stmSql.executeBatch(); //Executor do receptaculo anterior
                                stmSql.close(); //fechamento de receptaculo
                                System.out.print("\033[H\033[2J"); //Cleaner
                                System.out.flush(); //cleaner
                                status = "Alteração feita com sucesso!"; //Atualização de status
                            } /*Fechamento de if */ else { //caso receba as condiçoes do if acima -->
                                throw new ArithmeticException("Senha Incorreta!"); //Criação de nova exceção aritimetica
                            } //Fechamento de else
                        } /*fechamento de try */ catch (Exception e) { //Criação de ação do tipo catch. Exeption e: criação de variavel que recebera o valor de qualquer erro
                            System.err.println("Algo deu errado " + e); //exibição de mensagem mais a variavel e
                        } // Fechamento de catch
                        break;  //Quebra de ação
                    case "3": //Caso o valor de strRespostas for igual a "3"
                        try { //Criação da ação do tipo try
                            System.out.print("\033[H\033[2J"); //Cleaner
                            System.out.flush(); //Cleaner
                            System.out.println("Login atual: " + strBusca + "\n\n Nome atual: " + nome
                                    + "\n\nDigite a senha atual para permissão: ");  //Exibição de mensagem mais o valor da variavel strBusca e nome
                            String strAltSenha = scnRespostas.nextLine();  //Interação do usuario para atribuir valor para String
                            if (strAltSenha.equals(strSenha)) { //Criação de estrutura de condição, se a String strAltSenha for igual a String strSenha -->
                                System.out.print("\033[H\033[2J"); //cleaner
                                System.out.flush(); //cleaner
                                System.out.println("Permição concedida! Iniciando processo..."); //Exibição de mensagem
                                Thread.sleep(1000); //Pause no codigo por 1000 milisegundos
                                System.out.print("\033[H\033[2J"); //Cleaner
                                System.out.flush(); // Cleaner
                                System.out.println("Digite o novo nome:"); //Exibição de mensagem
                                String strAltNome = scnRespostas.nextLine(); //Interação do usuario para atribuir valor para String
                                System.out.print("\033[H\033[2J"); //Cleaner
                                System.out.flush(); //Cleaner
                                System.out.println("Senha atual: " + strSenha + "\n\nDigite a nova senha: ");  //Exibição de mensagem mais a String strSenha
                                strAltSenha = scnRespostas.nextLine(); //Interação do usuario para atribuir valor para String
                                String strSqlUpdate = "UPDATE `mysql_connector`.`tbl_login` SET `nome` = '"
                                        + strAltNome + "', `senha` = '" + strAltSenha + "' WHERE (`login` = '"
                                        + strBusca + "');"; //String com o comando que sera injetado no Banco de dados SQL
                                stmSql.addBatch(strSqlUpdate);  //Criação de receptaculo para o comando do sql
                                stmSql.executeBatch(); //Executor do receptaculo anterior
                                stmSql.close(); //fechamento de receptaculo
                                System.out.print("\033[H\033[2J"); //Cleaner
                                System.out.flush(); //Cleaner
                                status = "Alteração feita com sucesso!"; //Alteração da variavel status
                            } /*Fechamento de if */ else {   //caso receba as condiçoes do if acima -->
                                throw new ArithmeticException("Senha Incorreta!");  //Criação de nova exceção aritimetica
                            } //fechamento de else
                        } catch (Exception e) { //Criação de ação do tipo catch. Exeption e: criação de variavel que recebera o valor de qualquer erro
                            System.err.println("Algo deu errado " + e); //exibição de mensagem mais a variavel e
                        } //fechamento catch
                        break; //Quebra de ação
                    case "4": //Caso o valor de strRespostas for igual a "4"
                        status = "Saindo..."; //Mudança da variavel status
                        System.out.print("\033[H\033[2J"); //cleaner
                        System.out.flush(); //cleaner
                        break; //Quebra de ação
                    default: //Mensagem padrao do Switch/Case
                        status = "Saindo..."; //Mudança da variavel status
                        System.out.print("\033[H\033[2J"); //Cleaner
                        System.out.flush(); //Cleaner
                        break; //Quebra de açãp
                } // Fechamento de Switch/Case
            } /*Fechamento de if */ else { //caso receba as condiçoes do if acima -->
                status = "Saindo..."; //Mudança da variavel Status
            } //Fechamento de else
            stmSql.close(); //Fechamento de statement
            rsSql.close(); //fechamento de executavel
        } /*Fechamento try */ catch (Exception e) { //Criação de ação do tipo catch. Exeption e: criação de variavel que recebera o valor de qualquer erro
            System.out.println("Ops! Ocorreu o erro" + e); //exibição de mensagem mais a variavel e
            status = "Saindo..."; //Mudança da variavel status
        } //Fechamento catch
        System.out.println(status); //Exibição da variavel status
        scnRespostas.close(); //Fechamento de Scanner
        scnSenha.close(); //Fechamento de Scanner
        scnInput.close(); //Fechamento de Scanner
    } //Fechamento de metodo
} //Fechamento de objeto