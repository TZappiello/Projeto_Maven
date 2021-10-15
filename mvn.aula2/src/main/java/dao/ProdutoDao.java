package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.Produto;
import solucao.Conexao;

public class ProdutoDao {

// Objeto de accesso de dados = DAO--------------------------------------------------
	public void inserir(Produto p) {

		try {

			Conexao conexao = new Conexao();

			String sql = "INSERT INTO produto (nome, preco, quantidade, dataValidade, dataCadastro) VALUES (?,?,?,?,?)";

			PreparedStatement ps = conexao.getConexao().prepareStatement(sql);

			ps.setString(1, p.getNome());
			ps.setDouble(2, p.getPreco());
			ps.setInt(3, p.getQuantidade());
			ps.setDate(4, p.getDataValidade());
			ps.setDate(5, p.getDataCadastro());

			ps.execute();

			System.out.println("Produto cadastrado com sucesso!");

		} catch (Exception e) {

			System.out.println("Erro de Cadastro " + e.getMessage());

		}
	}

//busca todos os produtos criados------------------------------------------------------
	public List<Produto> buscarTodos() {

		List<Produto> produto = new ArrayList<>();

		try {
			Conexao conexao = new Conexao();

			String sql = "SELECT * FROM produto";

			PreparedStatement ps = conexao.getConexao().prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Produto prod = new Produto();

				prod.setId(rs.getInt("id"));
				prod.setNome(rs.getString("nome"));
				prod.setPreco(rs.getDouble("preco"));
				prod.setQuantidade(rs.getInt("quantidade"));
				prod.setDataValidade(rs.getDate("dataValidade"));
				prod.setDataCadastro(rs.getDate("dataCadastro"));

				produto.add(prod);

			}

		} catch (Exception e) {
			System.out.println("Erro para listar o produtos " + e.getMessage());

		}
		return produto;

	}

//Faz a busca por nome do produto----------------------------------------------------
	public List<Produto> buscarNome() {
		Scanner sc = new Scanner(System.in);

		List<Produto> produtos = new ArrayList<Produto>();

		try {

			Conexao conexao = new Conexao();

			System.out.println("Digite o nome da pesquisa");
			String busca = sc.nextLine();

			String sql = "SELECT * FROM produto WHERE nome LIKE'" + busca + "%'";

			PreparedStatement ps = conexao.getConexao().prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Produto prod = new Produto();

				prod.setId(rs.getInt("id"));
				prod.setNome(rs.getString("nome"));
				prod.setQuantidade(rs.getInt("quantidade"));
				prod.setPreco(rs.getDouble("preco"));
				prod.setDataValidade(rs.getDate("dataValidade"));
				prod.setDataCadastro(rs.getDate("dataCadastro"));

				produtos.add(prod);

			}

		} catch (Exception e) {
			System.out.println("Erro ao listar produtos " + e.getMessage());
		}
		return produtos;

	}

//muda os dados da linha na tabela---------------------------------------------------------
	public void mudarDados(Produto p, int id) {

		try {

			Conexao conexao = new Conexao();

			String sql = "UPDATE produto SET nome =?, preco=?, quantidade=?, dataValidade=?, dataCadastro=? WHERE id=?";

			PreparedStatement ps = conexao.getConexao().prepareStatement(sql);

			ps.setString(1, p.getNome());
			ps.setDouble(2, p.getPreco());
			ps.setInt(3, p.getQuantidade());
			ps.setDate(4, p.getDataValidade());
			ps.setDate(5, p.getDataCadastro());
			ps.setInt(6, id);

			ps.execute();

		} catch (Exception e) {
			System.out.println("Erro ao alterar produtos " + e.getMessage());
		}

	}

}
