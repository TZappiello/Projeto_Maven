package solucao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.ProdutoDao;
import modelo.Produto;

public class TesteConexao {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		testar();
		
		System.out.println("(Inserir Produtos digite->'1')   (Consultar Produtos digite->'2')   (sair->'3')");
		String opcao_strg = sc.nextLine();
		int opcao = Integer.parseInt(opcao_strg);

		

			switch (opcao) {

			case 1:
				inserirDados();
				break;

			case 2:
				mostrar();
				break;
				
			case 3:
				System.out.println("Saindo....");
			}
		
	}
	

	// Vai mostrar os dados da tabela
	public static void mostrar() {

		List<Produto> produto = new ArrayList<Produto>();

		ProdutoDao dao = new ProdutoDao();

		produto = dao.buscarTodos();

		for (int i = 0; i < produto.size(); i++) {

			System.out.println("--------------------------");

			System.out.println("ID do Produto: " + produto.get(i).getId());
			System.out.println("Nome do Produto: " + produto.get(i).getNome());
			System.out.println("Preço do Produto: " + produto.get(i).getPreco());
			System.out.println("Quantidade de Produto: " + produto.get(i).getQuantidade());
			System.out.println("Data de Validade do Produto: " + produto.get(i).getDataValidade());
			System.out.println("Data de Cadastro: " + produto.get(i).getDataCadastro());
		}

	}

	// Testar a conexao com o banco
	public static void testar() {
		try {

			Conexao conexao = new Conexao();

			conexao.getConexao();

			System.out.println("Conexao com BD OK...");
			System.out.println("----------------------------------------");
		} catch (Exception e) {
			System.out.println("Erro no DB " + e.getMessage());

		}
	}

	// Colocar dados na tabela
	public static void inserirDados() {
		Scanner sc = new Scanner(System.in);

		Produto p = new Produto();

		System.out.println("Digite a quantidade para inserir: ");
		int num = sc.nextInt();

		for (int i = 0; i < num; i++) {

			System.out.println("Digite o nome do produto para cadastra ");
			p.setNome(sc.next());

			System.out.println("Digite o valor do produto ");
			p.setPreco(sc.nextDouble());

			System.out.println("Digite a quantidade produto ");
			p.setQuantidade(sc.nextInt());

			System.out.println("Digite a data de validade do produto ");

			String dataValidade = sc.next(); // Converter a data p string

			Date dataV = Date.valueOf(dataValidade);
			p.setDataValidade(dataV);
			p.setDataCadastro(new Date(System.currentTimeMillis()));

			System.out.println("----------------------------------------");

			ProdutoDao dao = new ProdutoDao();
			dao.inserir(p);

		}

	}

}
