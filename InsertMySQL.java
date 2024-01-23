import java.util.*;
import java.sql.*;

public class InsertMySQL {
    public static void main(String[] args) {
        String status = "Nada aconteceu ainda.";
        System.out.println("Bem vindo ao cadastro de login.");
        Scanner scnTabela = new Scanner(System.in);
        Scanner scnLogin = new Scanner(System.in);
        Scanner scnNome = new Scanner(System.in);
        Scanner scnSenha = new Scanner(System.in);
        try {
            System.out.println("Insira o nome da tabela onde deseja realizar o cadastro:");
            String strTabela = scnTabela.nextLine();
            System.out.println("Insira o login para cadastro:");
            String strLogin = scnLogin.nextLine();
            System.out.println("Insira o Nome para cadastro:");
            String strNome = scnNome.nextLine();
            System.out.println("Insira a Senha para cadastro:");
            String strSenha = scnSenha.nextLine(); 
            String strInsertMySQL = "insert into `mysql_connector`.`"+ strTabela + "` (`login`,`nome`,`senha`) values ('" + strLogin + "','" + strNome + "','" + strSenha + "');";
            Connection conn = App.conectar();
            Statement stmSql = conn.createStatement();
            stmSql.addBatch(strInsertMySQL);
            stmSql.executeBatch();
            stmSql.close();
            status = "Ok! Registro concluido";
        } catch (Exception e) {
            System.err.println("Ops! Ocorreu o erro " + e);
        }
        scnTabela.close();
        scnLogin.close();
        scnNome.close();
        scnSenha.close();
        System.out.println(status);
    }
}
