import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class ContratoEmpresarial extends Cliente implements Interface {
	
	
	Entradas verifica = new Entradas();

	private long numero_funcionarios;
	private long numero_visitas;
	private int ramo;
	private String cnpj;
	private String[] ramoA = { "Industria", "Comercio", "Agropecuaria" };
	private String ramoM;
	int i;

	public void cadastro() {
		do {
			cancelar = false;
			ok = false;
			saiu = false;
			
			/* nome do cliente */
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

			/* CNPJ do cliente */
			
			do {
				try {
					cnpj = JOptionPane.showInputDialog("Digite o CNPJ do cliente:");

					File check = new File(cnpj + ".txt");
					if (check.exists() == true) {
						throw new IOException();
					} else if (verifica.isValidCNPJ(cnpj) == true && cnpj != null && cnpj.length() > 0) {
						ok = true;
					} else if (cnpj != null && verifica.isValidCPF(cnpj) == false) {
						throw new invCNPJ();
					} else if (cnpj.length() == 0) {
						throw new DN();
					}
				} catch (invCNPJ ex) {
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "Já existe um contrato para esse CNPJ!");
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

			/* endereço do cliente */
			
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

			/* valor do imóvel */
			
			do {
				try {
					check = JOptionPane.showInputDialog("Digite o valor do imovel:");

					if (verifica.isCurrency(check) == true) {
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

			ok = false;

			/* nº de funcionários da empresa */
			
			do {
				try {
					check = JOptionPane.showInputDialog("Digite o numero de funcionarios:");

					if (verifica.isNumeric(check) == true) {
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
				numero_funcionarios = Integer.parseInt(check);
			}

			ok = false;

			/* nº de visitas diárias a empresa  */
			do {
				try {
					check = JOptionPane.showInputDialog("Digite o numero de visitas:");

					if (verifica.isNumeric(check) == true) {
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
				numero_visitas = Integer.parseInt(check);
			}

			/* ramo de atuação da empresa */
			
			Object[] ramoEscolha = { "Industria", "Comercio", "Agropecuaria", "Cancelar" };

			ramo = JOptionPane.showOptionDialog(null, "Escolha o ramo de atuação da empresa:", "Seguradora",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, ramoEscolha, ramoEscolha[0]);

			for (i = 0; i <= 2; i++) {
				if (ramo == i) {
					ramoM = ramoA[i];
				}
			}

			if (ramo == 3) {
				cancelar = true;
				saiu = true;
			}

			break;

		} while (cancelar == false);

	}

	/* Calculo do valor do seguro empresarial */
	
	public void calculoSeguroEmpresarial() {
		seguro += valor_imovel * 0.04;

		int i;
		double acPorcentagem = 0, porFunc = 0, porVis = 0;

		if (numero_funcionarios >= 10) {
			porFunc = 0.002;
		}

		if (numero_visitas >= 200) {
			porVis = 0.003;
		}

		for (i = 1; i <= numero_funcionarios; i++) {
			if (i % 10 == 0) {
				porFunc += 0.002;
			}
		}

		for (i = 1; i <= numero_visitas; i++) {
			if (i % 200 == 0) {
				porFunc += 0.003;
			}
		}

		acPorcentagem = porFunc + porVis;

		seguro += valor_imovel * acPorcentagem;

		if (ramo == 0) {
			seguro += valor_imovel * 0.01;
		}

		if (ramo == 1) {
			seguro += valor_imovel * 0.005;
		}
	}

	/* Salva cadastro em ".bin" */
	public void salvarCadastro() {
		PessoaJuridica c = new PessoaJuridica(cliente, cnpj, endereco, valor_imovel, numero_funcionarios, numero_visitas,
				ramoM, seguro, fezContrato);

		try {

			FileOutputStream fos = new FileOutputStream(cnpj + ".PessoaJuridica.bin");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(c);

			oos.close();
			fos.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível realizar o cadastro!");
		}
	}

	/* Gera o contrato */
	
	public void gerarContrato() {
	
		try {
			/* Pergunta se quer gerar o contrato */
			
			int opcao = JOptionPane.showOptionDialog(null, "Clique na operação a qual deseja realizar:", "Operação",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			
			/* if salva arquivo */
			
			if (opcao == 0) {

				FileWriter arq = new FileWriter(cnpj + ".txt");
				PrintWriter gravarArq = new PrintWriter(arq);
				gravarArq.printf("**CONTRATO**%n%nNome do cliente: " + cliente + "%nCNPJ: " + cnpj + "%nEndereço: "
						+ endereco + "%nRamo: " + ramoM + "%nValor do imóvel: " + f.format(valor_imovel)
						+ "%nNúmero de Funcionários: " + numero_funcionarios + "%nNúmero de visitas: " + numero_visitas
						+ "%nValor do seguro: " + f.format(seguro));

				JOptionPane.showMessageDialog(null, "Contrato salvo com sucesso como " + cnpj + ".txt !");
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
