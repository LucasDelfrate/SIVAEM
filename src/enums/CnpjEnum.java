package enums;

public enum CnpjEnum {
	JA_CADASTRADO(1),
	CARACTERES_INVALIDOS(2),
	SUCESSO(3),
	ERRO(4),
	NAO_ENCONTRADO(5);
	
	private final int valor;
	CnpjEnum(int valor) {
		this.valor = valor;
	}
}
