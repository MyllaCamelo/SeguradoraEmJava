import javax.swing.JOptionPane;

public class CPFincorreto extends Exception {

	private static final long serialVersionUID = -6346794817253321355L;

	public CPFincorreto(){
		JOptionPane.showMessageDialog(null, "Voc� digitou um CPF inv�lido, por favor tente novamente!");
    }
}

