import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class invCNPJ extends Exception {
	
	String informativo = "<html><body><p width='150px' align='center'>Voc� digitou um CNPJ inv�lido!</p></body></html>";
	JLabel messageLabel = new JLabel(informativo);

	private static final long serialVersionUID = -5601310225608164318L;
	
	public invCNPJ(){
		JOptionPane.showMessageDialog(null, messageLabel);
    }

}

