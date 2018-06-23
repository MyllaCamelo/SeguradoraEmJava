import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DN extends Exception{
	
	String informativo = "<html><body><p width='120px' align='center'>Você não digitou nada!</p></body></html>";
	JLabel messageLabel = new JLabel(informativo);

	private static final long serialVersionUID = 2628380054499696578L;
	
	public DN(){
		JOptionPane.showMessageDialog(null, messageLabel);
	}

}
