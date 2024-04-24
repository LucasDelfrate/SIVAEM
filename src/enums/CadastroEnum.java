package enums;

public enum CadastroEnum {
	
	SUCESSO(1),
	ERRO_USUARIO(2),
    ERRO_EMAIL(3),
    ERRO_SENHA(4);
	
	private final int valor;
	
	CadastroEnum(int valor) {
		this.valor = valor;
	}
}
