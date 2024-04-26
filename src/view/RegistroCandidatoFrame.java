package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;

import controllers.JSONController;
import models.Candidato;
import models.Cliente;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistroCandidatoFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField iptNome;
	private JTextField iptEmail;
	private JPasswordField iptSenha;
	private Cliente cliente;
	private HomeFrame home;

	public RegistroCandidatoFrame(HomeFrame home, Cliente cliente) {
		this.home = home;
		this.cliente = cliente;
		setBounds(100, 100, 664, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(Color.GRAY);
		contentPane_1.setBounds(0, 0, 648, 469);
		contentPane.add(contentPane_1);
		
		JLabel lblLoginCandidato = new JLabel("Registro Candidato");
		lblLoginCandidato.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginCandidato.setForeground(new Color(0, 0, 0));
		lblLoginCandidato.setFont(new Font("Verdana", Font.PLAIN, 44));
		lblLoginCandidato.setBounds(0, 0, 648, 125);
		contentPane_1.add(lblLoginCandidato);
		
		JButton btnRegistrar_1 = new JButton("REGISTRAR");
		btnRegistrar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Candidato candidato = new Candidato();
				candidato.setOperacao("cadastrarCandidato");
				candidato.setUser(iptNome.getText());
				candidato.setPassword(iptSenha.getText());
				candidato.setEmail(iptEmail.getText());
				JSONController registroController = new JSONController();
				JSONObject res = registroController.changeToJSON(candidato);
				 
				 registrarCandidato(res);
			}
		});
		btnRegistrar_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRegistrar_1.setBounds(217, 341, 211, 46);
		contentPane_1.add(btnRegistrar_1);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(290, 120, 57, 14);
		contentPane_1.add(lblNewLabel);
		
		iptNome = new JTextField();
		iptNome.setBounds(217, 145, 214, 29);
		contentPane_1.add(iptNome);
		iptNome.setColumns(10);
		
		iptEmail = new JTextField();
		iptEmail.setColumns(10);
		iptEmail.setBounds(217, 210, 214, 29);
		contentPane_1.add(iptEmail);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmail.setBounds(290, 185, 57, 14);
		contentPane_1.add(lblEmail);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSenha.setBounds(290, 250, 59, 14);
		contentPane_1.add(lblSenha);
		
		iptSenha = new JPasswordField();
		iptSenha.setBounds(217, 275, 214, 29);
		contentPane_1.add(iptSenha);
	}
	public void registrarCandidato(JSONObject res) {
		if(this.cliente == null) {
			System.out.println("O cliente está nulo, você deve primeiro inicializar o cliente e o servidor");
			
		}else {			
			System.out.println(res);
			this.cliente.setHomeFrame(this.home);
			this.cliente.enviarMensagem(res);
		}
	}
	public void respostaTela(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
}
