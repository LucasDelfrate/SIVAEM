package enums;

public enum LoginEnum {
	SUCESSO(1),
	ERRO_USUARIO_E_SENHA(2);

	private final int valor;
	LoginEnum(int valor) {
		this.valor = valor;
	}
}
