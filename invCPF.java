import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class invCPF extends Exception {
	
	 String informativo = "<html><body><p width='150px' align='center'>Voc� digitou um CPF inv�lido!</p></body></html>";
	 JLabel messageLabel = new JLabel(informativo);

	private static final long serialVersionUID = -6346794817253321355L;

	public invCPF(){
		JOptionPane.showMessageDialog(null, messageLabel);
    }
}

