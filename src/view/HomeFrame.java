package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Cliente;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

public class HomeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeFrame frame = new HomeFrame(null);
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
	public HomeFrame(Cliente cliente) {
		this.cliente = cliente;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 514);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginEmpresaFrame login = new LoginEmpresaFrame();
				login.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(79, 222, 210, 46);
		contentPane.add(btnNewButton);
		
		JButton btnLoginCandidato = new JButton("Login");
		btnLoginCandidato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamarLoginCandidato();
			}
		});
		btnLoginCandidato.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLoginCandidato.setBounds(354, 222, 210, 46);
		contentPane.add(btnLoginCandidato);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamarRegistroCandidato();
			}
		});
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRegistrar.setBounds(354, 279, 210, 46);
		contentPane.add(btnRegistrar);
		
		JButton btnRegistrarEmpresa = new JButton("Registrar");
		btnRegistrarEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRegistrarEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRegistrarEmpresa.setBounds(79, 279, 210, 46);
		contentPane.add(btnRegistrarEmpresa);
		
		JLabel lblCandidato = new JLabel("Candidato");
		lblCandidato.setHorizontalAlignment(SwingConstants.CENTER);
		lblCandidato.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblCandidato.setBounds(354, 165, 210, 46);
		contentPane.add(lblCandidato);
		
		JLabel lblEmpresa = new JLabel("Empresa");
		lblEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblEmpresa.setBounds(79, 165, 210, 46);
		contentPane.add(lblEmpresa);
		
		JLabel lblNewLabel = new JLabel("SIVAEM");
		lblNewLabel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 44));
		lblNewLabel.setBounds(28, 11, 588, 78);
		contentPane.add(lblNewLabel);
	
	}
	private void chamarLoginCandidato() {
		LoginCandidatoFrame login = new LoginCandidatoFrame(this.cliente);
		login.setVisible(true);
	}
	private void chamarRegistroCandidato(){
		RegistroCandidatoFrame regFrame = new RegistroCandidatoFrame(this.cliente);
		regFrame.setVisible(true);
	}
}
