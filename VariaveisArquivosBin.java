import java.io.Serializable;

public class VariaveisArquivosBin implements Serializable {

	private static final long serialVersionUID = 7331615495936945583L;
	String cliente, cpf_cnpj;
	double seguro;
	boolean isPessoaFisica;

	public VariaveisArquivosBin(String cliente, String cpf_cnpj, double seguro, boolean isPessoaFisica) {
		super();
		this.cliente = cliente;
		this.cpf_cnpj = cpf_cnpj;
		this.seguro = seguro;
		this.isPessoaFisica = isPessoaFisica;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public double getSeguro() {
		return seguro;
	}

	public void setSeguro(double seguro) {
		this.seguro = seguro;
	}

	public boolean isPessoaFisica() {
		return isPessoaFisica;
	}

	public void setPessoaFisica(boolean isPessoaFisica) {
		this.isPessoaFisica = isPessoaFisica;
	}

}
