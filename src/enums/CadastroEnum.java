package enums;

public enum CadastroEnum {
	
	SUCESSO(0),
	ERRO_USUARIO(1),
    ERRO_EMAIL(2),
    ERRO_SENHA(3);
	
	private final int valor;
	
	CadastroEnum(int valor) {
		this.valor = valor;
	}
}
