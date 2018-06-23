import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

import javax.swing.JOptionPane;

                                 /*@param Classe de verificação de entradas*/

						/*CALCULOS E VERIFICAÇÕES DE VALIDADE DAS ENTRADAS DO USUARIO*/

public class Entradas {

	
	public boolean isNumeric_(String s) {
		try {
			Long.parseLong(s);
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}

		/* verificação de validade do CPF e CNPJ */
	private static final int[] pesoCPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
	private static final int[] pesoCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

							/* CPF */
	
	public boolean isValidCPF(String cpf) {
		if ((cpf == null) || (cpf.length() != 11))
			return false;

		Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
		Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
		return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
	}

							/* CNPJ */
	
	
	public boolean isValidCNPJ(String cnpj) {
		if ((cnpj == null) || (cnpj.length() != 14))
			return false;

		Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
		Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
		return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
	}
	
	
					/* verificação da entrada númerica */
	
	public boolean isNumeric(String s) {
		try {
			if (s != null && s.length() > 0 && s.length() <= 10 && isNumeric_(s) == true) {
				return true;
			} else if (s.length() == 0 && s != null) {
				throw new DN();
			} else if (s != null && isNumeric_(s) == false || s.length() > 10 || s.indexOf(",") != -1) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Você digitou um valor não aceito!");
			return false;
		} catch (DN e) {
			return false;
		}
		return true;
	}

							/* moeda nacional */
	
	private static ThreadLocal<NumberFormat> brazilianCurrencyFormat = new ThreadLocal<NumberFormat>() {
		@Override
		protected NumberFormat initialValue() {
			return new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
		}
	};

							/* se é moeda válida */
	
	public boolean isCurrency_(String s) {
		s = s.trim();
		ParsePosition pos = new ParsePosition(0);
		brazilianCurrencyFormat.get().parse(s, pos);
		return pos.getIndex() == s.length();

	}

							/* moeda válida */
	public boolean isCurrency(String s) {
		try {
			if (s != null && s.length() > 0 && s.length() <= 13 && isCurrency_(s) == true && s.indexOf(",") == -1) {
				return true;
			} else if (s.length() == 0 && s != null) {
				throw new DN();
			} else if (s.length() > 13 || s.indexOf(",") != -1 || isCurrency_(s) == false) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Você digitou um valor não aceito!");
			return false;
		} catch (DN e) {
			return false;
		}
		return true;
	}



}
