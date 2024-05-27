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

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginEmpresaFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailField;
	private JTextField passwordField;
	private Cliente cliente;


	public LoginEmpresaFrame(Cliente cliente) {
		this.cliente = cliente;
		setBounds(100, 100, 450, 515);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLoginEmpresa = new JLabel("LOGIN EMPRESA");
		lblLoginEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginEmpresa.setForeground(Color.BLACK);
		lblLoginEmpresa.setFont(new Font("Verdana", Font.BOLD, 20));
		lblLoginEmpresa.setBackground(Color.BLACK);
		lblLoginEmpresa.setBounds(106, 55, 227, 41);
		contentPane.add(lblLoginEmpresa);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(180, 130, 78, 13);
		contentPane.add(lblNewLabel_1);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(106, 153, 227, 29);
		contentPane.add(emailField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Senha");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(180, 211, 78, 13);
		contentPane.add(lblNewLabel_1_1);
		
		passwordField = new JTextField();
		passwordField.setColumns(10);
		passwordField.setBounds(106, 234, 227, 29);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("LOGAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Empresa empresa = new Empresa();
				empresa.setOperacao("loginEmpresa"); 
				empresa.setEmail(emailField.getText());
				saveEmail(emailField.getText());
				empresa.setSenha(passwordField.getText());
				JSONController registroEmpresaController = new JSONController();
				JSONObject res = registroEmpresaController.changeEmpresaToJSON(empresa);
				 
				 loginEmpresa(res);
			}
		});
		btnNewButton.setBounds(106, 319, 227, 41);
		contentPane.add(btnNewButton);
	}
	public void loginEmpresa(JSONObject res){
		if(this.cliente == null) {
			System.out.println("O cliente está nulo, você deve primeiro inicializar o cliente e o servidor");
			
		}else {			
			this.cliente.setLoginEmpresaFrame(this);
			this.cliente.enviarMensagem(res);
		}
	}
	public void mostrarRespota(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	public void saveEmail(String email) {
		this.cliente.saveEmail(email);
	}
}
