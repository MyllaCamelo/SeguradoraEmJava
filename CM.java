import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import javax.swing.JOptionPane;

public class ContratoMoradia extends ProcuraContratoCliente implements Interface {
	ChecarEntrada checar = new ChecarEntrada();
	DigitoVerificadorCPF_CNPJ cpfVerificador = new DigitoVerificadorCPF_CNPJ();

	private int zona;
	private int tipo;
	private String cpf, tipoM, zonaM;
	private String[] tipoA = { "Casa", "Apartamento" };
	private String[] zonaA = { "Urbana", "Suburbana", "Rural" };

	public void cadastro() {
		do {
			cancelar = false;
			ok = false;
			saiu = false;
			
			do {
				try {
					cliente = JOptionPane.showInputDialog("Digite o nome do cliente:");

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
					cpf = JOptionPane.showInputDialog("Digite o CPF do cliente:");

					File check = new File(cpf + ".con");
					if (check.exists() == true) {
						throw new IOException();
					} else if (cpfVerificador.isValidCPF(cpf) == true && cpf != null && cpf.length() > 0) {
						ok = true;
					} else if (cpfVerificador.isValidCPF(cpf) == false && cpf.equals("") == false) {
						throw new CPFincorreto();
					} else if (cpf.length() == 0) {
						throw new CampoVazio();
					}
				} catch (CPFincorreto ex) {
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "Já existe um contrato para esse CPF!");
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
					endereco = JOptionPane.showInputDialog("Digite o endereco do cliente:");

					if (endereco != null && endereco.length() > 0) {
						ok = true;
					} else if (endereco.length() == 0 && endereco != null) {
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
					valor_imovel = Float.parseFloat(JOptionPane.showInputDialog("Digite o valor do imovel:"));

					check = String.valueOf(valor_imovel);

					if (check != null && check.length() > 0 && checar.isCurrency(check) == true) {
						ok = true;
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Você digitou um valor não aceito ou não digitou nada!");
				} catch (NullPointerException ex) {
					cancelar = true;
					saiu = true;
					break;
				}
			} while (ok == false);

			if (cancelar == true)
				break;

			Object[] escolhaLocal = { "Urbana", "Suburbana", "Rural", "Cancelar" };

			zona = JOptionPane.showOptionDialog(null, "Escolha o local da habitação:", "Seguradora",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, escolhaLocal, escolhaLocal[0]);

			if (zona == 3) {
				cancelar = true;
				saiu = true;
			}

			if (cancelar == true)
				break;

			Object[] tipoEscolha = { "Casa", "Apartamento", "Cancelar" };

			tipo = JOptionPane.showOptionDialog(null, "Escolha o tipo da habitação:", "Seguradora",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, tipoEscolha, tipoEscolha[0]);

			if (tipo == 2) {
				cancelar = true;
				saiu = true;
			}

			cancelar = true;

		} while (cancelar == false);

	}

	public void calculoSeguroResidencial() {

		seguro += valor_imovel * 0.01;

		if (zona == 0)
			seguro += valor_imovel * 0.01;

		if (zona == 1)
			seguro += valor_imovel * 0.025;

		if (tipo == 0)
			seguro += valor_imovel * 0.005;
	}

	public void salvarCadastro() {
		VariaveisArquivosBin c = new VariaveisArquivosBin(cliente, cpf, seguro,true);

		try {

			FileOutputStream saidaArquivo = new FileOutputStream(cpf+".bin");
			ObjectOutputStream saidaObjeto = new ObjectOutputStream(saidaArquivo);
			saidaObjeto.writeObject(c);

			saidaObjeto.close();
			saidaArquivo.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível realizar o cadastro!");
		}
	}

	public void gerarContrato() {
		NumberFormat f = NumberFormat.getCurrencyInstance();

		try {

			int i;

			for (i = 0; i <= 2; i++) {
				if (zona == i)
					zonaM = zonaA[i];
			}

			for (i = 0; i <= 1; i++) {
				if (tipo == i)
					tipoM = tipoA[i];
			}

			int opcao = JOptionPane.showOptionDialog(null, "Clique na operação a qual deseja realizar:", "Operação",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			if (opcao == 0) {

				FileWriter arq = new FileWriter(cpf + ".con");
				PrintWriter gravarArq = new PrintWriter(arq);
				gravarArq.printf("CONTRATO%n%nNome do cliente: " + cliente + "%nCPF: " + cpf + "%nEndereço: "
						+ endereco + "%nTipo de residência: " + tipoM + "%nZona: " + zonaM + "%nValor do imóvel: "
						+ f.format(valor_imovel) + "%nValor do seguro: " + f.format(seguro));

				JOptionPane.showMessageDialog(null, "Contrato salvo como " + cpf + ".con !");
				arq.close();

			} else if (opcao == 1) {
				JOptionPane.showMessageDialog(null, "Contrato não salvo\nClique em OK para retornar ao menu");
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar contrato!");
		}
	}

}
