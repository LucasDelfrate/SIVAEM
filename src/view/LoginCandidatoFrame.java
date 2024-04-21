package view;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.LoginCandidatoController;
import models.Candidato;
import models.Cliente;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginCandidatoFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private Cliente client;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginCandidatoFrame frame = new LoginCandidatoFrame(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginCandidatoFrame(Cliente client) {
		
		this.client = client;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 433);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN CADIDATO");
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel.setBounds(92, 37, 227, 41);
		contentPane.add(lblNewLabel);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(92, 135, 227, 29);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Usuário");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(166, 112, 78, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Senha");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(166, 193, 78, 13);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("LOGAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 String username = txtUsername.getText();
				 char[] password = txtPassword.getPassword();
				 Candidato candidato = new Candidato();
				 candidato.setOperacao("loginCandidato");
				 candidato.setPassword(password);
				 candidato.setUser(username);
				 LoginCandidatoController loginController = new LoginCandidatoController();
				 loginController.changeToJSON(candidato);
			}
		});
		btnNewButton.setBounds(92, 268, 227, 41);
		contentPane.add(btnNewButton);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(92, 216, 227, 29);
		contentPane.add(txtPassword);
	}
}
