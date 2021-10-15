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
		

// Pede as opçao de quais funcão quer fazer------------------------------------------------------
		System.out.println("(1) Inserir Produtos-> :  ");
		System.out.println("(2) Consultar Produtos-> : ");
		System.out.println("(3) Consultar por nome-> : ");
		System.out.println("(4) Alterar dados -> : ");
		System.out.println("(5) Sair-> : ");
		
		int opcao = sc.nextInt();

		switch (opcao) {

		case 1:
			inserirDados();
			break;

		case 2:
			mostrar();
			break;

		case 3:
			mostrarNome();
			break;

		case 4:
			mudarDados();
			break;
			
		case 5:
			System.out.println("Saindo....");
			break;

		default:
			System.out.println("Erro na Escolha");
		}

	}

	public static void mostrarNome() {

		List<Produto> produtos = new ArrayList<Produto>();

		ProdutoDao dao = new ProdutoDao();

		produtos = dao.buscarNome();

		int cont = 0;

		for (int i = 0; i < produtos.size(); i++) {

			System.out.println("*************");

			System.out.println("ID do Produto: " + produtos.get(i).getId());
			System.out.println("Nome do Produto: " + produtos.get(i).getNome());
			System.out.println("Quantidade do Produto: " + produtos.get(i).getQuantidade());
			System.out.println("Preço do Produto: " + produtos.get(i).getPreco());
			System.out.println("Data de validade do Produto: " + produtos.get(i).getDataValidade());
			System.out.println("Data de cadastro do Produto: " + produtos.get(i).getDataCadastro());

			System.out.println("*************");
		
			cont++;
			System.out.println("Total da pesquisa: " + cont);

		}
	}

// Vai mostrar os dados da tabela--------------------------------------------------------------
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

// Testar a conexao com o banco--------------------------------------------------
	public static void testar() {
		try {

			Conexao conexao = new Conexao();

			conexao.getConexao();

			System.out.println("Conexao com Banco de Dados OK...");
			System.out.println("********************************");
		} catch (Exception e) {
			System.out.println("Erro no DB " + e.getMessage());

		}
	}

// Colocar dados na tabela-------------------------------------------------------
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
		sc.close();
	}

//Mudar os dados do produto a ser mudado---------------------------------------------
	public static void mudarDados() {
		Scanner sc = new Scanner(System.in);
		
		
		Produto p = new Produto();
		System.out.println("------------Alterar Poduto--------------");
		System.out.println("----------------------------------------");
		
		
		System.out.println("Digite o nome do produto para alterar ");
		p.setNome(sc.nextLine());
		
		
		System.out.println("Digite o preço para alterar ");
		p.setPreco(sc.nextDouble());
		
		
		System.out.println("Digite o quantidade do produto para alterar ");
		p.setQuantidade(sc.nextInt());
		
		
		System.out.println("Digite a data de validade do produto para alterar ");
		String dataValidade = sc.next();
		
		Date dataV = Date.valueOf(dataValidade);
		p.setDataValidade(dataV);
		
		p.setDataCadastro(new Date(System.currentTimeMillis()));
		
		System.out.println("Digite o id do produto para alterar ");
		int id = sc.nextInt();
		sc.nextLine();
		
		System.out.println("---------Poduto alterado OK!!!----------");
		System.out.println("----------------------------------------");
		ProdutoDao dao = new ProdutoDao();
		
		dao.mudarDados(p, id);
		
		}

}
