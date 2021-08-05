package controller;

import conexao.FabricaDeConexoes;
import model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Matheus
 */
public class ProdutoDAO {
    
    public void create(Produto prod) {
        
        Connection con = FabricaDeConexoes.iniciarConexao();
        PreparedStatement sql;
        
        try {
            sql = con.prepareStatement("INSERT INTO produtos (nome, qtd, preco, descricao) VALUES(?,?,?,?)");
            sql.setString(1, prod.getNome());
            sql.setInt(2, prod.getQtd());
            sql.setDouble(3, prod.getPreco());
            sql.setString(4, prod.getDescricao());
            
            sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar produto: " + ex);
        }
        finally {
            FabricaDeConexoes.fecharConexao(con);
        }
    }
    
    public List<Produto> read() {
        
        Connection con = FabricaDeConexoes.iniciarConexao();
        PreparedStatement sql;
        ResultSet rs;

        List<Produto> produtos = new ArrayList<>();
        
        try {
            sql = con.prepareStatement("SELECT * FROM produtos");
            rs = sql.executeQuery();
            
            while (rs.next()) {
                Produto prod = new Produto();
                prod.setCod(rs.getInt("cod"));
                prod.setNome(rs.getString("nome"));
                prod.setQtd(rs.getInt("qtd"));
                prod.setPreco(rs.getDouble("preco"));
                prod.setDescricao(rs.getString("descricao"));
                
                produtos.add(prod);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            FabricaDeConexoes.fecharConexao(con);
        }
        return produtos;
    }
    
    public List<Produto> readNome(String nome) {
        
        Connection con = FabricaDeConexoes.iniciarConexao();
        PreparedStatement sql;
        ResultSet rs;

        List<Produto> produtos = new ArrayList<>();
        
        try {
            sql = con.prepareStatement("SELECT * FROM produtos WHERE nome LIKE ?");
            sql.setString(1, "%"+nome+"%");
            
            rs = sql.executeQuery();
            
            while (rs.next()) {
                Produto prod = new Produto();
                prod.setCod(rs.getInt("cod"));
                prod.setNome(rs.getString("nome"));
                prod.setQtd(rs.getInt("qtd"));
                prod.setPreco(rs.getDouble("preco"));
                prod.setDescricao(rs.getString("descricao"));
                
                produtos.add(prod);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            FabricaDeConexoes.fecharConexao(con);
        }
        return produtos;
    }
    
    public void update(Produto prod) {
        
        Connection con = FabricaDeConexoes.iniciarConexao();
        PreparedStatement sql;
        
        try {
            sql = con.prepareStatement("UPDATE produtos SET nome = ?, qtd = ?, preco = ?, descricao = ? WHERE cod = ?");
            sql.setString(1, prod.getNome());
            sql.setInt(2, prod.getQtd());
            sql.setDouble(3, prod.getPreco());
            sql.setString(4, prod.getDescricao());
            sql.setInt(5, prod.getCod());
            
            sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar produto: " + ex);
        }
        finally {
            FabricaDeConexoes.fecharConexao(con);
        }
    }
    
    public void delete(Produto prod) {
        
        Connection con = FabricaDeConexoes.iniciarConexao();
        PreparedStatement sql;
        
        try {
            sql = con.prepareStatement("DELETE FROM produtos WHERE cod = ?");
            sql.setInt(1, prod.getCod());

            sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto excluido com sucesso!");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir produto: " + ex);
        }
        finally {
            FabricaDeConexoes.fecharConexao(con);
        }
    }
}
