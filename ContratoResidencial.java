import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class ContratoResidencial extends Cliente implements Interface {
	
	
	Entradas verifica = new Entradas();

	private int zona;
	private int tipo;
	private String cpf;
	private String[] tipoA = { "Casa", "Apartamento" };
	private String[] zonaA = { "Urbana", "Suburbana", "Rural" };
	private String tipoM;
	private String zonaM;
	int i;

	public void cadastro() {
		do {
			cancelar = false;
			ok = false;
			saiu = false;
			/* Entrada do nome do cliente */
			do {
				try {
					cliente = JOptionPane.showInputDialog("Digite o nome do cliente:");

					if (cliente != null && cliente.length() > 0) {
						ok = true;
					} else if (cliente.length() == 0 && cliente != null) {
						throw new DN();
					}
				} catch (NullPointerException ex) {
					cancelar = true;
					saiu = true;
					break;
				} catch (DN ex) {

				}
			} while (ok == false);

			if (cancelar == true) {
				break;
			}

			ok = false;

			/* Entrada do CPF do cliente */
			do {
				try {
					cpf = JOptionPane.showInputDialog("Digite o CPF do cliente:");

					File check = new File(cpf + ".txt");
					if (check.exists() == true) {
						throw new IOException();
					} else if (verifica.isValidCPF(cpf) == true && cpf != null && cpf.length() > 0) {
						ok = true;
					} else if (cpf != null && verifica.isValidCPF(cpf) == false) {
						throw new invCPF();
					} else if (cpf.length() == 0) {
						throw new DN();
					}
				} catch (invCPF ex) {
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "Já existe um contrato para esse CPF!");
				} catch (NullPointerException ex) {
					cancelar = true;
					saiu = true;
					break;
				} catch (DN e) {
				}
			} while (ok == false);

			if (cancelar == true) {
				break;
			}

			ok = false;

			/* Entrada do endereço do cliente */
			do {
				try {
					endereco = JOptionPane.showInputDialog("Digite o endereco do cliente:");

					if (endereco != null && endereco.length() > 0) {
						ok = true;
					} else if (endereco.length() == 0 && endereco != null) {
						throw new DN();
					}
				} catch (NullPointerException ex) {
					cancelar = true;
					saiu = true;
					break;
				} catch (DN ex) {

				}
			} while (ok == false);

			if (cancelar == true) {
				break;
			}

			ok = false;

			/* Entrada do valor do imóvel do cliente */
			do {
				try {
					check = JOptionPane.showInputDialog("Digite o valor do imovel:");

					if (verifica.isCurrency(check)) {
						ok = true;
					}
				} catch (NullPointerException ex) {
					cancelar = true;
					saiu = true;
					break;
				}
			} while (ok == false);

			if (cancelar == true) {
				break;
			} else {
				valor_imovel = Float.parseFloat(check);
			}

			/*Entrada da zona da residencia do cliente*/
			Object[] zonaEscolha = { "Urbana", "Suburbana", "Rural", "Cancelar" };

			zona = JOptionPane.showOptionDialog(null, "Escolha a zona aonde a habitação se encontra:", "Seguradora",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, zonaEscolha, zonaEscolha[0]);

			/*
			 * Verifica qual zona o usuário escolheu e busca na String ramoA[] a
			 * String correspondente
			 */
			for (i = 0; i <= 2; i++) {
				if (zona == i) {
					zonaM = zonaA[i];
				}
			}

			if (zona == 3) {
				cancelar = true;
				saiu = true;
			}

			if (cancelar == true) {
				break;
			}

			/*Entrada do tipo de residência do cliente*/
			Object[] tipoEscolha = { "Casa", "Apartamento", "Cancelar" };

			tipo = JOptionPane.showOptionDialog(null, "Escolha o tipo da habitação:", "Seguradora",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, tipoEscolha, tipoEscolha[0]);

			/*
			 * Verifica qual tipo o usuário escolheu e busca na String ramoA[] a
			 * String correspondente
			 */
			for (i = 0; i <= 1; i++) {
				if (tipo == i) {
					tipoM = tipoA[i];
				}
			}

			if (tipo == 2) {
				cancelar = true;
				saiu = true;
			}

			break;

		} while (cancelar == false);

	}

	/* Calcula o valor do seguro residencial */
	public void calculoSeguroResidencial() {

		seguro += valor_imovel * 0.01;

		if (zona == 0) {
			seguro += valor_imovel * 0.01;
		}

		if (zona == 1) {
			seguro += valor_imovel * 0.025;
		}

		if (tipo == 0) {
			seguro += valor_imovel * 0.005;
		}
	}
	
	/* Salva cadastro em ".bin" */
	public void salvarCadastro() {
		PessoaFisica c = new PessoaFisica(cliente, cpf, endereco, valor_imovel, zonaM, tipoM, seguro, fezContrato);

		try {

			FileOutputStream fos = new FileOutputStream(cpf + ".PessoaFisica.bin");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(c);

			oos.close();
			fos.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível realizar o cadastro!");
		}
	}

	/* Gerador de contrato */
	public void gerarContrato() {

		try {

			/* Pergunta se o usuário quer gerar o contrato */
			int opcao = JOptionPane.showOptionDialog(null, "Clique na operação a qual deseja realizar:", "Operação",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			/* if salva arquivo */
			if (opcao == 0) {

				FileWriter arq = new FileWriter(cpf + ".txt");
				PrintWriter gravarArq = new PrintWriter(arq);
				gravarArq.printf("**CONTRATO**%n%nNome do cliente: " + cliente + "%nCPF: " + cpf + "%nEndereço: "
						+ endereco + "%nTipo de residência: " + tipoM + "%nZona: " + zonaM + "%nValor do imóvel: "
						+ f.format(valor_imovel) + "%nValor do seguro: " + f.format(seguro));

				JOptionPane.showMessageDialog(null, "Contrato salvo com sucesso como " + cpf + ".txt !");
				fezContrato = true;
				arq.close();

			} else if (opcao == 1) {/*
									 * if não salva arquivo e retorna ao menu
									 * principal
									 */
				JOptionPane.showMessageDialog(null, "Você não gerou o contrato!\nClique em OK para retornar ao menu");
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao gerar contrato!");
		}
	}

}
