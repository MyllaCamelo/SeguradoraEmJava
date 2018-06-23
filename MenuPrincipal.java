import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;


/*
 * 
 * PROJETO JAVA - SEGURADORA 2018
 * 
 * */


													/*Menu*/

public class MenuPrincipal extends Cliente {

	
	public static void main(String[] args) {
		
		 new UIManager();
		 UIManager.put("OptionPane.background", new ColorUIResource(255,230,255));
		 UIManager.put("Panel.background", new ColorUIResource(255,230,255));
		 
		 			/* JOptionPane */
		 String escolha1 = "<html><body><p width='365px' align='center'>Escolha a opção desejada:</p></body></html>";
		 JLabel messageLabel1 = new JLabel(escolha1);
		 
		
		 String escolha2 = "<html><body><p width='200px' align='center'>Escolha o tipo de contrato:</p></body></html>";
		 JLabel messageLabel2 = new JLabel(escolha2);
		 
		 		/* JOptionPane para cadastros */
		 String escolha3 = "<html><body><p width='215px' align='center'>Escolha o tipo dos contratos:</p></body></html>";
		 JLabel messageLabel3 = new JLabel(escolha3);
		 
		ContratoResidencial pessoaFisica = new ContratoResidencial();
		ContratoEmpresarial pessoaJuridica = new ContratoEmpresarial();
		
		Object[] menu = { "Novo Contrato", "Carregar Contrato", "Contratos Realizados", "\nSair" };
		Object[] pessoas = { "Pessoa Física", "Pessoa Jurídica", "Cancelar" };

		
		int opcao = 0;
		
		do {

												/* opções */	
			opcao = JOptionPane.showOptionDialog(null,
					messageLabel1,
					"Seguradora", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, menu, menu[0]);

			switch (opcao) {/* Switch Menu */
			case 0:
				int opcaoPessoaCadastro = JOptionPane.showOptionDialog(null,
						messageLabel2, "Seguradora", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, pessoas, pessoas[0]);

					/* pessoa física */
				if (opcaoPessoaCadastro == 0) {
					pessoaFisica.cadastro();
					
					if (saiu == false) {
						pessoaFisica.calculoSeguroResidencial();
						pessoaFisica.gerarContrato();
						pessoaFisica.salvarCadastro();
					}
				}

				
				/* pessoa jurídica */
				else if (opcaoPessoaCadastro == 1) {
					pessoaJuridica.cadastro();

					
					if (saiu == false) {
						pessoaJuridica.calculoSeguroEmpresarial();
						pessoaJuridica.gerarContrato();
						pessoaJuridica.salvarCadastro();
					}
				} else {
					break;
				}

				break;

				/* exibição de contrato */
			case 1:
				
				mostraContrato();
				break;

				/* cadastros no programa */
			case 2:
				int opcaoPessoa = JOptionPane.showOptionDialog(null, messageLabel3,
						"Seguradora", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, pessoas,
						pessoas[0]);
				if (opcaoPessoa == 0) {
					lerCadastroFisica();
				} else if (opcaoPessoa == 1) {
					lerCadastroJuridica();
				} else {
					break;
				}
				break;		
				
			case 3:
				
				System.exit(0);
				break;

			}
		} while (opcao != 3);
								
	}
}
