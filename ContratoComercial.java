import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.NumberFormat;

import javax.swing.JOptionPane;

public class ContratoComercial extends ProcuraContratoCliente implements Interface {
	ChecarEntrada checar = new ChecarEntrada();
	DigitoVerificadorCPF_CNPJ cnpjVerificador = new DigitoVerificadorCPF_CNPJ();

	private long numeroF;
	private long numeroV;
	private int ramo;
	private String cnpj, ramoM;
	private String[] ramoA = { "Industria", "Comercio", "Agropecuaria" };

	public void cadastro() {
		do {
			cancelar = false;
			ok = false;
			saiu = false;
			
			do {
				try {
					razaoSocial = JOptionPane.showInputDialog("Digite o nome real da empresa:");

					if (razaoSocial != null && razaoSocial.length() > 0) {
						ok = true;
					} else if (razaoSocial.length() == 0 && razaoSocial != null) {
						throw new CampoVazio();
					}
				} catch (NullPointerException ex) {
					cancelar = true;
					saiu = true;
					break;
				} catch (CampoVazio e) {
				}
			} while (ok == false);

			if (cancelar == true)
				break;

			ok = false;
				
				do {
					try {
						cliente = JOptionPane.showInputDialog("Digite o nome fantasia da empresa:");

						if (cliente != null && cliente.length() > 0) {
							ok = true;
						} else if (cliente.length() == 0 && cliente != null) {
							throw new CampoVazio();
						}
					} catch (NullPointerException ex) {
						cancelar = true;
						saiu = true;
						break;
					} catch (CampoVazio e) {
					}
				} while (ok == false);

				if (cancelar == true)
					break;

				ok = false;

			do {
				try {
					cnpj = JOptionPane.showInputDialog("Digite o CNPJ da empresa:");

					File check = new File(cnpj + ".con");
					if (check.exists() == true) {
						throw new IOException();
					} else if (cnpjVerificador.isValidCNPJ(cnpj) == true && cnpj != null && cnpj.length() > 0) {
						ok = true;
					} else if (cnpjVerificador.isValidCPF(cnpj) == false && cnpj.equals("") == false) {
						throw new CNPJincorreto();
					} else if (cnpj.length() == 0) {
						throw new CampoVazio();
					}
				} catch (CNPJincorreto ex) {
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "J� existe um contrato para esse CNPJ!");
				} catch (NullPointerException ex) {
					cancelar = true;
					saiu = true;
					break;
				} catch (CampoVazio e) {
				}
			} while (ok == false);

			if (cancelar == true)
				break;

			ok = false;

			do {
				try {
					endereco = JOptionPane.showInputDialog("Digite o endereco da empresa:");

					if (endereco != null && endereco.length() > 0) {
						ok = true;
					} else if (endereco.length() == 0 && endereco != null) {
						throw new CampoVazio();
					}
				} catch (NullPointerException ex) {
					cancelar = true;
					saiu = true;
					break;
				} catch (CampoVazio e){
					}
			} while (ok == false);

			if (cancelar == true)
				break;

			ok = false;

			do {
				try {
					valor_imovel = Float.parseFloat(JOptionPane.showInputDialog("Digite o valor do imovel:"));

					check = String.valueOf(valor_imovel);

					if (check != null && check.length() > 0 && checar.isCurrency(check) == true) {
						ok = true;
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Voc� digitou um valor n�o aceito ou n�o digitou nada!");
				} catch (NullPointerException ex) {
					cancelar = true;
					saiu = true;
					break;
				}
			} while (ok == false);

			if (cancelar == true)
				break;

			ok = false;

			do {
				try {
					numeroF = Integer
							.parseInt(JOptionPane.showInputDialog("Digite o numero de funcionarios:"));

					check = String.valueOf(numeroF);

					if (check != null && check.length() > 0 && checar.isNumeric(check) == true) {
						ok = true;
					}

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Voc� digitou um valor n�o aceito ou n�o digitou nada!");
				} catch (NullPointerException ex) {
					cancelar = true;
					saiu = true;
					break;
				}
			} while (ok == false);

			if (cancelar == true)
				break;

			ok = false;

			do {
				try {
					numeroV = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero de visitas:"));

					check = String.valueOf(numeroV);

					if (check != null && check.length() > 0 && checar.isNumeric(check) == true) {
						ok = true;
					}

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Voc� digitou um valor n�o aceito ou n�o digitou nada!");
				} catch (NullPointerException ex) {
					cancelar = true;
					saiu = true;
					break;
				}
			} while (ok == false);

			if (cancelar == true)
				break;

			Object[] ramoEscolha = { "Industria", "Comercio", "Agropecuaria", "cancelar" };

			ramo = JOptionPane.showOptionDialog(null, "Escolha o ramo de atua��o da empresa:", "Seguradora",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, ramoEscolha, ramoEscolha[0]);

			if (ramo == 3) {
				cancelar = true;
				saiu = true;
			}

			cancelar = true;

		} while (cancelar == false);

	}

	public void calculoSeguroEmpresarial() {
		seguro += valor_imovel * 0.04;

		int i;
		double acPorcentagem = 0, porFunc = 0, porVis = 0;

		if (numeroF >= 10)
			porFunc = 0.002;

		if (numeroV >= 200)
			porVis = 0.003;

		for (i = 1; i <= numeroF; i++) {
			if (i % 10 == 0)
				porFunc += 0.002;
		}

		for (i = 1; i <= numeroV; i++) {
			if (i % 200 == 0) 
				porFunc += 0.003;
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
	
	public void salvarCadastro() {
		VariaveisArquivosBin c = new VariaveisArquivosBin(cliente, cnpj, seguro,false);

		try {

			FileOutputStream saidaArquivo = new FileOutputStream(cnpj+".bin");
			ObjectOutputStream saidaObjeto = new ObjectOutputStream(saidaArquivo);
			saidaObjeto.writeObject(c);

			saidaObjeto.close();
			saidaArquivo.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel realizar o cadastro!");
		}
	}

	public void gerarContrato() {
		NumberFormat f = NumberFormat.getCurrencyInstance();

		try {
			int i;

			for (i = 0; i <= 2; i++) {
				if (ramo == i) {
					ramoM = ramoA[i];
				}
			}

			int opcao = JOptionPane.showOptionDialog(null, "Clique na opera��o a qual deseja realizar:", "Opera��o",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			if (opcao == 0) {

				FileWriter arq = new FileWriter(cnpj + ".con");
				PrintWriter gravarArq = new PrintWriter(arq);
				gravarArq.printf("CONTRATO%n" + "%nRaz�o Social: " + razaoSocial + "%nNome ficticio: " + cliente + "%nCNPJ: " + cnpj + "%nEndere�o: "
						+ endereco + "%nRamo: " + ramoM + "%nValor do im�vel: " + f.format(valor_imovel)
						+ "%nN�mero de Funcion�rios: " + numeroF + "%nN�mero de visitas: " + numeroV
						+ "%nValor do seguro: " + f.format(seguro));

				JOptionPane.showMessageDialog(null, "Contrato salvo com sucesso como " + cnpj + ".con !");
				arq.close();

			} else if (opcao == 1) {
				JOptionPane.showMessageDialog(null, "Voc� n�o gerou o contrato!\nClique em OK para retornar ao menu");

			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar contrato!");
		}

	}
}
