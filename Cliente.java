import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Cliente {

	/* DECLARAÇÃO DE VARIAVEIS GLOBAIS */
	
			/*referentes ao cliente*/
		public String cliente;
		public String endereco;
		public double valor_imovel;
		public double seguro;
	
									/*Opções*/
	public static boolean saiu = false, ok = false, cancelar = false, fezContrato = false;;
	public String check;
	Object[] options = { "Gerar Contrato", "Sair" };
	static Object[] escolha = { "Sim", "Não" };
	
	
			static NumberFormat f = NumberFormat.getCurrencyInstance();

	
	
								/* Exibição do contrato */
	public static void mostraContrato() {
		
		String nomeArq = JOptionPane.showInputDialog(null, "Insira aqui o seu CPF/CNPJ:\n");
				
					/*SESSÃO DE BLOCOS DE CÓDIGO PERIGOSOS*/
		
		try {
			
			if (nomeArq != null && nomeArq.equals("") == false) {
				
				JTextArea ta = new JTextArea(20, 20);
				
				ta.read(new FileReader(nomeArq + ".txt"), null);
				
				/* arquivo não editavel */
				ta.setEditable(false);
				
				JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Seguradora", 2, null);
			}
			
			
			
			/*EXCEÇÃO PERSONALIZADA (vide: DigitouNada)*/
			else if (nomeArq != null && nomeArq.equals("") == true) {
				throw new DN();
			}
		} catch (NullPointerException ex) {

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Atenção, contrato inexistente!");
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(null, "Atenção, contrato inexistente!");
		} catch (DN e) {
		}
	}

	
	public static void lerCadastroFisica() {

		
		String dadosPF = null;

		try {
			
							/* Filtro "PessoaFisica.bin" */
			FileFilter filter = new FileFilter() {
				public boolean accept(File file) {
					return file.getName().endsWith("PessoaFisica.bin");
				}
			};

			
			/* Localiza diretorio */
			File dir = new File("./");
			File[] files = dir.listFiles(filter);

			
							/*Arraylists*/
			List<String> listaClientesPF = new ArrayList<>();

			
			for (int i = 0; i < files.length; i++) {
				FileInputStream fis = new FileInputStream(files[i]);
				ObjectInputStream ois = new ObjectInputStream(fis);

				PessoaFisica lerPF = (PessoaFisica) ois.readObject();
				
				
							/* Conversão de dados para String String */
				dadosPF = ("\n\nCliente\n\nNome : " + lerPF.getCliente() + "\nCPF : " + lerPF.getCpf()
						+ "\nValor do imóvel: " + f.format(lerPF.getValor_imovel()));
				/* Se os dados lidos não forem nulos, adiciona ao ArrayList */
				if (dadosPF != null)
					listaClientesPF.add(dadosPF);

				/* Checa se os usuários cadastrados tem contrato */
				if (lerPF.isFezContrato() == false) {
					try {

						/* Pergunta se o usuário quer gerar o contrato */
						int opcao = JOptionPane.showOptionDialog(null,
								"Cliente " + lerPF.getCliente() + " de CPF : " + lerPF.getCpf()
										+ " não tem contrato...\nDeseja gerar um?",
								"Operação", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, escolha,
								escolha[0]);
						/* if salva arquivo */
						if (opcao == 0) {

							/*
							 * Pega os dados do arquivo e coloca em Arrays
							 * temporários
							 */
							String[] temp1 = { lerPF.getCliente(), lerPF.getCpf(), lerPF.getEndereco(),
									lerPF.getZonaM(), lerPF.getTipoM() };
							double[] temp2 = { lerPF.getValor_imovel(), lerPF.getSeguro() };

							/* Deleta arquivo */
							files[i].delete();

							PessoaFisica c = new PessoaFisica(temp1[0], temp1[1], temp1[2], temp2[0], temp1[3],
									temp1[4], temp2[1], true);

							/* Cria novo arquivo */
							try {

								FileOutputStream fos = new FileOutputStream(temp1[1] + ".PessoaFisica.bin");
								ObjectOutputStream oos = new ObjectOutputStream(fos);
								oos.writeObject(c);

								oos.close();
								fos.close();
							} catch (IOException e) {
							}

							/* Gera contrato pessoa física */
							FileWriter arq = new FileWriter(temp1[1] + ".txt");
							PrintWriter gravarArq = new PrintWriter(arq);
							gravarArq.printf("**CONTRATO**%n%nNome do cliente: " + temp1[0] + "%nCPF: " + temp1[1]
									+ "%nEndereço: " + temp1[2] + "%nTipo de residência: " + temp1[4] + "%nZona: "
									+ temp1[3] + "%nValor do imóvel: " + f.format(temp2[0]) + "%nValor do seguro: "
									+ f.format(temp2[1]));

							JOptionPane.showMessageDialog(null,
									"Contrato salvo com sucesso como " + temp1[1] + ".txt !");

							arq.close();

						} else if (opcao == 1) {
							continue;
						}
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Erro ao salvar contrato!");
					}
				}

				fis.close();
				ois.close();

			}

			/* String contendo a ArrayList */
			String TextArea = ("PESSOA FISICA:" + listaClientesPF);
			/* Janela de texto 20 x 25 */
			JTextArea ta = new JTextArea(20, 25);
			/* Adiciona conteúdo da string "TextArea" na Janela de texto */
			ta.append(TextArea);
			/* Torna o arquivo não editável */
			ta.setEditable(false);
			/* Mostra o contrato na Janela de Texto */
			JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Seguradora", 2, null);

		} catch (NullPointerException | FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Não há cadastros ainda...");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir arquivos!");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir arquivos!");
		}

	}

	/* Mostra todos os cadastros de Pessoa Jurídica */
	public static void lerCadastroJuridica() {

		/* Strings para armazenar os dados de Pessoa Jurídica */
		String dadosPJ = null;

		try {
			/* Filtro para arquivos "PessoaJuridica.bin" */
			FileFilter filter = new FileFilter() {
				public boolean accept(File file) {
					return file.getName().endsWith("PessoaJuridica.bin");
				}
			};

			/* Abre diretório aonde o programa está localizado */
			File dir = new File("./");
			/* Usa o filtro no diretório */
			File[] files = dir.listFiles(filter);

			/*
			 * Arraylists para armazenar Strings de dados de Pessoa Jurídica
			 */
			List<String> listaClientesPJ = new ArrayList<>();

			/* For pra ler arquivos no diretório */
			for (int i = 0; i < files.length; i++) {
				/* Lê arquivos */
				FileInputStream fis = new FileInputStream(files[i]);
				ObjectInputStream ois = new ObjectInputStream(fis);

				PessoaJuridica lerPJ = (PessoaJuridica) ois.readObject();
				
				/* Transforma dados lidos de PessoaJuridica em String */
				dadosPJ = ("\n\nCliente\n\nNome : " + lerPJ.getCliente() + "\nCNPJ : " + lerPJ.getCnpj()
						+ "\nValor do imóvel: " + f.format(lerPJ.getValor_imovel()));
				/* Se os dados lidos não forem nulos, adiciona ao ArrayList */
				if (dadosPJ != null)
					listaClientesPJ.add(dadosPJ);

				/* Checa se os usuários cadastrados tem contrato */
				if (lerPJ.isFezContrato() == false) {
					try {

						/* Pergunta se o usuário quer gerar o contrato */
						int opcao = JOptionPane.showOptionDialog(null,
								"Cliente " + lerPJ.getCliente() + " de CNPJ : " + lerPJ.getCnpj()
										+ " não tem contrato...\nDeseja gerar um?",
								"Operação", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, escolha,
								escolha[0]);
						/* if salva arquivo */
						if (opcao == 0) {

							/*
							 * Pega os dados do arquivo e coloca em Arrays
							 * temporários
							 */
							String[] temp1 = { lerPJ.getCliente(), lerPJ.getCnpj(), lerPJ.getEndereco(),
									lerPJ.getRamoM() };
							double[] temp2 = { lerPJ.getValor_imovel(), lerPJ.getSeguro() };
							long[] temp3 = { lerPJ.getNumero_funcionarios(), lerPJ.getNumero_visitas() };

							/* Cria novo arquivo */
							files[i].delete();

							PessoaJuridica c = new PessoaJuridica(temp1[0], temp1[1], temp1[2], temp2[0], temp3[0],
									temp3[1], temp1[3], temp2[1], true);

							/* Cria novo arquivo */
							try {

								FileOutputStream fos = new FileOutputStream(temp1[1] + ".PessoaJuridica.bin");
								ObjectOutputStream oos = new ObjectOutputStream(fos);
								oos.writeObject(c);

								oos.close();
								fos.close();
							} catch (IOException e) {
							}

							/* Gera contrato pessoa jurídica */
							FileWriter arq = new FileWriter(temp1[1] + ".txt");
							PrintWriter gravarArq = new PrintWriter(arq);
							gravarArq.printf("**CONTRATO**%n%nNome do cliente: " + temp1[0] + "%nCNPJ: " + temp1[1]
									+ "%nEndereço: " + temp1[2] + "%nRamo: " + temp1[3] + "%nValor do imóvel: "
									+ f.format(temp2[0]) + "%nNúmero de Funcionários: " + temp3[0]
									+ "%nNúmero de visitas: " + temp3[1] + "%nValor do seguro: " + f.format(temp2[1]));

							JOptionPane.showMessageDialog(null,
									"Contrato salvo com sucesso como " + temp1[1] + ".txt !");

							arq.close();

						} else if (opcao == 1) {
							continue;
						}
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Erro ao gerar contrato!");
					}
				}

				fis.close();
				ois.close();

			}

			/* String contendo a ArrayList */
			String TextArea = ("PESSOA JURIDICA:" + listaClientesPJ);
			/* Janela de texto 20 x 25 */
			JTextArea ta = new JTextArea(20, 25);
			/* Adiciona conteúdo da string "TextArea" na Janela de texto */
			ta.append(TextArea);
			/* Torna o arquivo não editável */
			ta.setEditable(false);
			/* Mostra o contrato na Janela de Texto */
			JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Seguradora", 2, null);

		} catch (NullPointerException | FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Não há cadastros ainda...");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir arquivos!");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir arquivos!");
		}

	}
}
