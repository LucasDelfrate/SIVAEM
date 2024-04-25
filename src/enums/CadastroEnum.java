package enums;

public enum CadastroEnum {
	
	SUCESSO(1),
	ERRO(2),
	EMAIL_CADASTRADO(3);
	
	private final int valor;
	
	CadastroEnum(int valor) {
		this.valor = valor;
	}
}
