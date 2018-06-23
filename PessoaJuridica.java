import java.io.Serializable;

public class PessoaJuridica implements Serializable {

	private static final long serialVersionUID = 4991027442256172138L;
	
	private String cliente;
	private String cnpj;
	private String endereco;
	private double valor_imovel;
	private long numero_funcionarios;
	private long numero_visitas;
	private String ramoM;
	private double seguro;
	private boolean fezContrato = false;
	/**
	 * @param cliente
	 * @param cnpj
	 * @param endereco
	 * @param valor_imovel
	 * @param numero_funcionarios
	 * @param numero_visitas
	 * @param ramoM
	 * @param seguro
	 * @param fezContrato
	 */
	public PessoaJuridica(String cliente, String cnpj, String endereco, double valor_imovel, long numero_funcionarios,
			long numero_visitas, String ramoM, double seguro, boolean fezContrato) {
		super();
		this.cliente = cliente;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.valor_imovel = valor_imovel;
		this.numero_funcionarios = numero_funcionarios;
		this.numero_visitas = numero_visitas;
		this.ramoM = ramoM;
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
	 * @return the cnpj
	 */
	public String getCnpj() {
		return cnpj;
	}
	/**
	 * @param cnpj the cnpj to set
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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
	 * @return the numero_funcionarios
	 */
	public long getNumero_funcionarios() {
		return numero_funcionarios;
	}
	/**
	 * @param numero_funcionarios the numero_funcionarios to set
	 */
	public void setNumero_funcionarios(long numero_funcionarios) {
		this.numero_funcionarios = numero_funcionarios;
	}
	/**
	 * @return the numero_visitas
	 */
	public long getNumero_visitas() {
		return numero_visitas;
	}
	/**
	 * @param numero_visitas the numero_visitas to set
	 */
	public void setNumero_visitas(long numero_visitas) {
		this.numero_visitas = numero_visitas;
	}
	/**
	 * @return the ramoM
	 */
	public String getRamoM() {
		return ramoM;
	}
	/**
	 * @param ramoM the ramoM to set
	 */
	public void setRamoM(String ramoM) {
		this.ramoM = ramoM;
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

