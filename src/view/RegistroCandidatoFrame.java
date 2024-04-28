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
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

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
		contentPane_1.setBounds(0, 0, 770, 469);
		contentPane.add(contentPane_1);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 18));
		lblNewLabel.setBounds(456, 127, 57, 22);
		contentPane_1.add(lblNewLabel);
		
		iptEmail = new JTextField();
		iptEmail.setColumns(10);
		iptEmail.setBounds(455, 231, 214, 29);
		contentPane_1.add(iptEmail);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(new Font("Consolas", Font.BOLD, 18));
		lblEmail.setBounds(456, 206, 57, 22);
		contentPane_1.add(lblEmail);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setFont(new Font("Consolas", Font.BOLD, 18));
		lblSenha.setBounds(454, 281, 59, 22);
		contentPane_1.add(lblSenha);
		
		iptSenha = new JPasswordField();
		iptSenha.setBounds(455, 306, 214, 29);
		contentPane_1.add(iptSenha);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBackground(new Color(234, 234, 234));
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		panel.setBounds(372, 0, 388, 469);
		contentPane_1.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Registro candidato");
		lblNewLabel_1.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(49, 50, 289, 45);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 22));
		panel.add(lblNewLabel_1);
		
		JButton btnRegistrar_1 = new JButton("Registrar");
		btnRegistrar_1.setBounds(83, 369, 214, 29);
		panel.add(btnRegistrar_1);
		btnRegistrar_1.setForeground(new Color(0, 0, 0));
		btnRegistrar_1.setBackground(new Color(255, 255, 255));
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
		btnRegistrar_1.setFont(new Font("Consolas", Font.PLAIN, 16));
		
		iptNome = new JTextField();
		iptNome.setBounds(83, 162, 214, 29);
		panel.add(iptNome);
		iptNome.setColumns(10);
		
		JLabel fundoRegistro = new JLabel("New label");
		fundoRegistro.setBounds(-143, -107, 963, 576);
		contentPane_1.add(fundoRegistro);
		Image img = new ImageIcon(this.getClass().getResource("/plus-size-woman.jpg")).getImage();
		fundoRegistro.setIcon(new ImageIcon(img));
		
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
