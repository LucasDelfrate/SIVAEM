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
		setBounds(100, 100, 813, 605);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SIVAEM");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 44));
		lblNewLabel.setBounds(10, 49, 775, 125);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("LOGIN EMPRESA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginEmpresaFrame login = new LoginEmpresaFrame();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(58, 185, 305, 57);
		contentPane.add(btnNewButton);
		
		JButton btnLoginCandidato = new JButton("LOGIN CANDIDATO");
		btnLoginCandidato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamarLoginCandidato();
			}
		});
		btnLoginCandidato.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLoginCandidato.setBounds(430, 185, 305, 57);
		contentPane.add(btnLoginCandidato);
		
		JButton btnRegistrar = new JButton("REGISTRAR CANDIDATO");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamarRegistroCandidato();
			}
		});
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRegistrar.setBounds(430, 261, 305, 57);
		contentPane.add(btnRegistrar);
		
		JButton btnRegistrarEmpresa = new JButton("REGISTRAR EMPRESA");
		btnRegistrarEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRegistrarEmpresa.setBounds(58, 261, 305, 57);
		contentPane.add(btnRegistrarEmpresa);
	
	}
	private void chamarLoginCandidato() {
		LoginCandidatoFrame login = new LoginCandidatoFrame(this.cliente);
		login.setVisible(true);
		dispose();
	}
	private void chamarRegistroCandidato(){
		RegistroCandidatoFrame regFrame = new RegistroCandidatoFrame(this.cliente);
		regFrame.setVisible(true);
		dispose();
	}
}
