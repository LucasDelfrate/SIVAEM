package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;

import controllers.JSONController;
import models.Cliente;
import models.Empresa;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistroEmpresaFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailField;
	private JTextField razaoField;
	private JTextField ramoField;
	private JTextField descricaoField;
	private JPasswordField passwordField;
	private JTextField cnpjField;
	private Cliente cliente;

	public RegistroEmpresaFrame(Cliente cliente) {
		this.cliente = cliente;
		setBounds(100, 100, 503, 694);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registro empresa");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(99, 23, 288, 65);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(139, 99, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		emailField = new JTextField();
		emailField.setHorizontalAlignment(SwingConstants.CENTER);
		emailField.setBounds(139, 124, 209, 20);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		razaoField = new JTextField();
		razaoField.setHorizontalAlignment(SwingConstants.CENTER);
		razaoField.setColumns(10);
		razaoField.setBounds(139, 180, 209, 20);
		contentPane.add(razaoField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Razao Social");
		lblNewLabel_1_1.setBounds(139, 155, 170, 14);
		contentPane.add(lblNewLabel_1_1);
		
		ramoField = new JTextField();
		ramoField.setHorizontalAlignment(SwingConstants.CENTER);
		ramoField.setColumns(10);
		ramoField.setBounds(139, 247, 209, 20);
		contentPane.add(ramoField);
		
		JLabel lblNewLabel_1_2 = new JLabel("Ramo");
		lblNewLabel_1_2.setBounds(139, 222, 46, 14);
		contentPane.add(lblNewLabel_1_2);
		
		descricaoField = new JTextField();
		descricaoField.setHorizontalAlignment(SwingConstants.CENTER);
		descricaoField.setColumns(10);
		descricaoField.setBounds(139, 319, 209, 20);
		contentPane.add(descricaoField);
		
		JLabel lblNewLabel_1_3 = new JLabel("Descrição");
		lblNewLabel_1_3.setBounds(139, 294, 141, 14);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Senha");
		lblNewLabel_1_4.setBounds(139, 430, 46, 14);
		contentPane.add(lblNewLabel_1_4);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(139, 455, 209, 20);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Empresa empresa = new Empresa();
				empresa.setOperacao("cadastrarEmpresa"); 
				empresa.setRazaoSocial(razaoField.getText());
				empresa.setDescricao(descricaoField.getText());
				empresa.setRamo(ramoField.getText());
				empresa.setEmail(emailField.getText());
				empresa.setSenha(passwordField.getText());
				empresa.setCnpj(cnpjField.getText());
				JSONController registroEmpresaController = new JSONController();
				JSONObject res = registroEmpresaController.changeEmpresaToJSON(empresa);
				 
				 registarEmpresa(res);
			}
		});
		btnNewButton.setBounds(139, 555, 209, 40);
		contentPane.add(btnNewButton);
		
		cnpjField = new JTextField();
		cnpjField.setHorizontalAlignment(SwingConstants.CENTER);
		cnpjField.setColumns(10);
		cnpjField.setBounds(139, 387, 209, 20);
		contentPane.add(cnpjField);
		
		JLabel labelofds = new JLabel("Cnpj");
		labelofds.setBounds(139, 362, 46, 14);
		contentPane.add(labelofds);
	}
	public void registarEmpresa(JSONObject res){
		if(this.cliente == null) {
			System.out.println("O cliente está nulo, você deve primeiro inicializar o cliente e o servidor");
			
		}else {			
			this.cliente.setEmpresaFrame(this);
			this.cliente.enviarMensagem(res);
		}
	}
	public void mostrarRespota(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem);
	}
}
