package model;

import controller.UsuarioDAO;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

public class Usuario {
    
    private int id;
    private String nome, login, senha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public boolean entrar(String login, char[] senha) {
        
        List<Usuario> u = new UsuarioDAO().readLogin(login);
        for (Usuario user : u) {
            if (user.getLogin().equalsIgnoreCase(login) && user.getSenha().equals(String.valueOf(senha))) {
                JOptionPane.showMessageDialog(null, "Bem vindo " + user.getNome() + "!");
                return true;
            }
        }
        return false;
    }
    
    public boolean verificaLogin(String login) {
        
        List<Usuario> u = new UsuarioDAO().readLogin(login);
        for (Usuario user : u) {
            if (user.getLogin().equalsIgnoreCase(login)) {
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "Usuário não encontrado!");
        return false;
    }
    
    public boolean verificaSenha(char[] senha1, char[] senha2) {
        
        if (!Arrays.equals(senha1, senha2)) {
            JOptionPane.showMessageDialog(null, "Senhas não coincidem!");
            return false;
        }
        else if (senha1.length == 0 || senha2.length == 0) {
            JOptionPane.showMessageDialog(null, "Senhas não podem ficar em branco!");
            return false;
        }
        return true;
    }    
}
