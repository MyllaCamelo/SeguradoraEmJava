import java.io.Serializable;

public class PessoaFisica implements Serializable {

	private static final long serialVersionUID = 4042322851451794298L;
	
	private String cliente;
	private String cpf;
	private String endereco;
	private double valor_imovel;
	private String tipoM;
	private String zonaM;
	private double seguro;
	private boolean fezContrato = false;

	/**
	 * @param cliente
	 * @param cpf
	 * @param endereco
	 * @param valor_imovel
	 * @param tipoM
	 * @param zonaM
	 * @param seguro
	 * @param fezContrato
	 */
	public PessoaFisica(String cliente, String cpf, String endereco, double valor_imovel, String tipoM, String zonaM,
			double seguro, boolean fezContrato) {
		super();
		this.cliente = cliente;
		this.cpf = cpf;
		this.endereco = endereco;
		this.valor_imovel = valor_imovel;
		this.tipoM = tipoM;
		this.zonaM = zonaM;
		this.seguro = seguro;
		this.fezContrato = fezContrato;
	}
	/**
	 * @return the cliente
	 */
	public String getCliente() {
		return cliente;
	}
	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}
	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	/**
	 * @return the endereco
	 */
	public String getEndereco() {
		return endereco;
	}
	/**
	 * @param endereco the endereco to set
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	/**
	 * @return the valor_imovel
	 */
	public double getValor_imovel() {
		return valor_imovel;
	}
	/**
	 * @param valor_imovel the valor_imovel to set
	 */
	public void setValor_imovel(double valor_imovel) {
		this.valor_imovel = valor_imovel;
	}
	/**
	 * @return the tipoM
	 */
	public String getTipoM() {
		return tipoM;
	}
	/**
	 * @param tipoM the tipoM to set
	 */
	public void setTipoM(String tipoM) {
		this.tipoM = tipoM;
	}
	/**
	 * @return the zonaM
	 */
	public String getZonaM() {
		return zonaM;
	}
	/**
	 * @param zonaM the zonaM to set
	 */
	public void setZonaM(String zonaM) {
		this.zonaM = zonaM;
	}
	/**
	 * @return the seguro
	 */
	public double getSeguro() {
		return seguro;
	}
	/**
	 * @param seguro the seguro to set
	 */
	public void setSeguro(double seguro) {
		this.seguro = seguro;
	}
	
	/**
	 * @return the fezContrato
	 */
	public boolean isFezContrato() {
		return fezContrato;
	}
	/**
	 * @param fezContrato the fezContrato to set
	 */
	public void setFezContrato(boolean fezContrato) {
		this.fezContrato = fezContrato;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

