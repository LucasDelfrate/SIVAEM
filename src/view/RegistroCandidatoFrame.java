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
		setBounds(100, 100, 774, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(Color.GRAY);
		contentPane_1.setBounds(0, 0, 542, 469);
		contentPane.add(contentPane_1);
		
		JButton btnRegistrar_1 = new JButton("Registrar");
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
		btnRegistrar_1.setFont(new Font("Unispace", Font.PLAIN, 15));
		btnRegistrar_1.setBounds(118, 362, 211, 29);
		contentPane_1.add(btnRegistrar_1);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setFont(new Font("Unispace", Font.PLAIN, 13));
		lblNewLabel.setBounds(118, 148, 57, 14);
		contentPane_1.add(lblNewLabel);
		
		iptNome = new JTextField();
		iptNome.setBounds(118, 167, 214, 29);
		contentPane_1.add(iptNome);
		iptNome.setColumns(10);
		
		iptEmail = new JTextField();
		iptEmail.setColumns(10);
		iptEmail.setBounds(118, 232, 214, 29);
		contentPane_1.add(iptEmail);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Unispace", Font.PLAIN, 13));
		lblEmail.setBounds(118, 207, 57, 14);
		contentPane_1.add(lblEmail);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Unispace", Font.PLAIN, 13));
		lblSenha.setBounds(118, 272, 59, 14);
		contentPane_1.add(lblSenha);
		
		iptSenha = new JPasswordField();
		iptSenha.setBounds(118, 297, 214, 29);
		contentPane_1.add(iptSenha);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 542, 95);
		contentPane_1.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Registro candidato");
		lblNewLabel_1.setBounds(163, 35, 216, 25);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Unispace", Font.PLAIN, 20));
		panel.add(lblNewLabel_1);
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
