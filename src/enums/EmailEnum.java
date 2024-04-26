package enums;

public enum EmailEnum {
	JA_CADASTRADO(1),
	CARACTERES_INVALIDOS(2),
	SUCESSO(3),
	ERRO(4),
	NAO_ENCONTRADO(5);
	
	private final int valor;
	EmailEnum(int valor) {
		this.valor = valor;
	}
}
