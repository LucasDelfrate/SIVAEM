package view;

import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;

import controllers.JSONController;
import models.Candidato;
import models.Cliente;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

public class LoginCandidatoFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private Cliente client;
	private HomeFrame home;
	private final JPanel panel = new JPanel();

	public LoginCandidatoFrame(HomeFrame home, Cliente client) {
		this.home = home;
		this.client = client;
		setBounds(100, 100, 627, 433);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setForeground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN CADIDATO");
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 22));
		lblNewLabel.setBounds(92, 37, 227, 41);
		contentPane.add(lblNewLabel);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(92, 146, 227, 29);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(92, 119, 78, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Senha");
		lblNewLabel_1_1.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(92, 204, 78, 29);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("LOGAR");
		btnNewButton.setFont(new Font("Consolas", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 String email = txtUsername.getText();
				 String password = txtPassword.getText();
				 Candidato candidato = new Candidato();
				 candidato.setOperacao("loginCandidato");
				 candidato.setPassword(password);
				 candidato.setEmail(email);
				 JSONController loginController = new JSONController();
				 JSONObject res = loginController.changeToJSON(candidato);
				 
				 loginCandidato(res);
				 
				 
			}
		});
		btnNewButton.setBounds(92, 297, 227, 41);
		contentPane.add(btnNewButton);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(92, 227, 227, 29);
		contentPane.add(txtPassword);
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(404, 0, 209, 396);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(15, 108, 178, 180);
		panel.add(lblNewLabel_2);
		Image img2 = new ImageIcon(this.getClass().getResource("/perfil222.png")).getImage();
		lblNewLabel_2.setIcon(new ImageIcon(img2));
		
		JLabel fundo = new JLabel("New label");
		fundo.setBounds(0, 0, 405, 396);
		contentPane.add(fundo);
		Image img = new ImageIcon(this.getClass().getResource("/novo-fundo.jpg")).getImage();
		fundo.setIcon(new ImageIcon(img));
	}
	
	public void loginCandidato(JSONObject res) {
		if(this.client == null) {
			System.out.println("O cliente está nulo, você deve primeiro inicializar o cliente e o servidor");
			
		}else {			
			this.client.setLoginFrame(this);
			this.client.enviarMensagem(res);
		}
	}
	public void respostaTela(String msg) {
		JOptionPane.showMessageDialog(null, msg);

	}
}
