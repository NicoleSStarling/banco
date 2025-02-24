package dao;

import model.CartaoModel;
import conexao.ConexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartaoDAO {  //Será usada para fazer operações no BD relacionadas a CartaoModel

    public List<CartaoModel> listarTodos() { //Retorna uma lista de todos os cartoes cadastrados no banco.
        List<CartaoModel> cartoes = new ArrayList<>(); //Cria uma lista vazia
        String sql = "SELECT id, numero, nome_titular, data_expiracao, cvv, limite_total, saldo_utilizado FROM cartao"; //Seleciona as colunas

        try (Connection conexao = ConexaoBanco.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CartaoModel cartao = new CartaoModel();
                cartao.setId(rs.getInt("id"));
                cartao.setNumero(rs.getString("numero"));
                cartao.setNomeTitular(rs.getString("nome_titular"));
                cartao.setDataExpiracao(rs.getString("data_expiracao"));
                cartao.setCvv(rs.getInt("cvv"));
                cartao.setLimiteTotal(rs.getDouble("limite_total"));
                cartao.setSaldoUtilizado(rs.getDouble("saldo_utilizado"));

                cartoes.add(cartao);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar cartões: " + e.getMessage());
            e.printStackTrace();
        }
        return cartoes;
    }

    public void inserirCartao(CartaoModel cartao) {
        String sql = "INSERT INTO cartao (numero, nome_titular, data_expiracao, cvv, limite_total, saldo_utilizado) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoBanco.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, cartao.getNumero());
            stmt.setString(2, cartao.getNomeTitular());
            stmt.setString(3, cartao.getDataExpiracao());
            stmt.setInt(4, cartao.getCvv());
            stmt.setDouble(5, cartao.getLimiteTotal());
            stmt.setDouble(6, cartao.getSaldoUtilizado());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao inserir cartão: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
