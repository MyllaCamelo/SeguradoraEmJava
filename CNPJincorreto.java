import javax.swing.JOptionPane;

public class CNPJincorreto extends Exception {

	private static final long serialVersionUID = -5601310225608164318L;
	
	public CNPJincorreto(){
		JOptionPane.showMessageDialog(null, "Você digitou um CNPJ inválido, por favor tente novamente!");
    }

}

