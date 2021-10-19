package solucao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.ProdutoDao;
import modelo.Produto;

public class TesteConexao {

	public static void main(String[] args) {

		

		testar();

		// Pede as opçao de quais funcão quer
		// fazer------------------------------------------------------

		String continuar;
		
		do {
			System.out.println("(1) Inserir Produtos-> :  ");
			System.out.println("(2) Consultar Produtos-> : ");
			System.out.println("(3) Consultar por nome-> : ");
			System.out.println("(4) Alterar dados -> : ");
			System.out.println("(5) Deletar Dados-> : ");

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
				deletarDados();
				break;
								
			} 
			
			System.out.println("************************************");
			System.out.println("Deseja continuar? S = Sim ou N = Não");
			continuar = sc.next();
			

		
		} //while (continuar.equals("S") || continuar.equals("s"));
		
		while (continuar.equalsIgnoreCase("S"));
		
		System.out.println("Saindo...");
		
	}
	//Metodo para nao precisar ficar chamando o SC
	static Scanner sc = new Scanner(System.in);
	
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
		
		List<Produto> produto = new ArrayList<Produto>();

		ProdutoDao dao = new ProdutoDao();

		produto = dao.buscarTodos();
		
		

		Produto p = new Produto();
		System.out.println("------------Alterar Poduto--------------");
		System.out.println("----------------------------------------");

		
		System.out.println("Digite o id do produto para alterar ");
		int id = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Quer Alterar o nome? S= Sim N= Não ");
		String m = sc.nextLine();
		
		if(m.equals("S")) {
			System.out.println("Digite o Nome Novo:");
			p.setNome(sc.nextLine());
			
		}else if (m.equals("N")) {
			p.setNome(produto.get(id-1).getNome());
		}
				
		
		System.out.println("Quer alterar o preço? S= Sim N= Não ");
		String m1 = sc.nextLine();
		
		if (m1.equals("S")) {
			System.out.println("Digite o novo Preço: ");
			p.setPreco(sc.nextDouble());
			
		
		}else if (m1.equals("N")) {
			p.setPreco(produto.get(id-1).getPreco());
		}
		
		System.out.println("Quer alterar a quantidade? S= Sim N= Não ");
		String m3 = sc.nextLine();
		
		if (m3.equals("S")) {
			System.out.println("Digite a nova quantidade: ");
			p.setQuantidade(sc.nextInt());
			
		} else if (m3.equals("N")) {
			p.setQuantidade(produto.get(id-1).getQuantidade());
		}
		
		
		System.out.println("Alterar a Data Validade? ");
		String dataValidade = sc.next();
		Date dataV = Date.valueOf(dataValidade);
		p.setDataValidade(dataV);
		
//		if (m4.equals("S")) {
//			System.out.println("Digite a nova Data: ");	
//			String dataValidade = sc.next();
//			Date dataV = Date.valueOf(dataValidade);
//			p.setDataValidade(dataV);
//			
//		}else if (m4.equals("N")) {
//			p.setDataValidade(produto.get(id-1).getDataValidade());
//		}
		
		//String dataValidade = sc.next();

		//Date dataV = Date.valueOf(dataValidade);
		//p.setDataValidade(dataV);

		p.setDataCadastro(new Date(System.currentTimeMillis()));


		System.out.println("---------Poduto alterado OK!!!----------");
		System.out.println("----------------------------------------");
		

		dao.mudarDados(p, id);

	}
	//Deleta a linha----------------------------------------------------------
	public static void deletarDados() {
		
		Produto p = new Produto();
		System.out.println("Coloque o ID do produto para deletar");
		int id = sc.nextInt();
		
		ProdutoDao dao = new ProdutoDao();
		
		System.out.println("---------Poduto Deletado OK!!!----------");
		
		dao.deletarDados(p, id);
		
	}
	
}
