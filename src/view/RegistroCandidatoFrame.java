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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroCandidatoFrame frame = new RegistroCandidatoFrame(null);
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
	public RegistroCandidatoFrame(Cliente cliente) {
		this.cliente = cliente;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 664, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(Color.GRAY);
		contentPane_1.setBounds(0, 0, 648, 508);
		contentPane.add(contentPane_1);
		
		JLabel lblLoginCandidato = new JLabel("Registro Candidato");
		lblLoginCandidato.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginCandidato.setForeground(Color.WHITE);
		lblLoginCandidato.setFont(new Font("Verdana", Font.PLAIN, 44));
		lblLoginCandidato.setBounds(0, 0, 648, 125);
		contentPane_1.add(lblLoginCandidato);
		
		JButton btnRegistrar_1 = new JButton("REGISTRAR");
		btnRegistrar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Candidato candidato = new Candidato();
				candidato.setUser(iptNome.getText());
				candidato.setPassword(iptSenha.getText());
				candidato.setEmail(iptEmail.getText());
				candidato.setOperacao("cadastrarCandidato");
				JSONController registroController = new JSONController();
				JSONObject res = registroController.changeToJSON(candidato);
				 
				 registrarCandidato(res);
				cliente.enviarMensagem(res);
				
			}
		});
		btnRegistrar_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRegistrar_1.setBounds(220, 351, 211, 46);
		contentPane_1.add(btnRegistrar_1);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(301, 154, 46, 14);
		contentPane_1.add(lblNewLabel);
		
		iptNome = new JTextField();
		iptNome.setBounds(217, 179, 214, 20);
		contentPane_1.add(iptNome);
		iptNome.setColumns(10);
		
		iptEmail = new JTextField();
		iptEmail.setColumns(10);
		iptEmail.setBounds(217, 237, 214, 20);
		contentPane_1.add(iptEmail);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(301, 212, 46, 14);
		contentPane_1.add(lblEmail);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSenha.setBounds(301, 268, 46, 14);
		contentPane_1.add(lblSenha);
		
		iptSenha = new JPasswordField();
		iptSenha.setBounds(217, 295, 214, 20);
		contentPane_1.add(iptSenha);
	}
	public void registrarCandidato(JSONObject res) {
		if(this.cliente == null) {
			System.out.println("O cliente está nulo, você deve primeiro inicializar o cliente e o servidor");
			
		}else {			
			this.cliente.enviarMensagem(res);
		}
	}
}
