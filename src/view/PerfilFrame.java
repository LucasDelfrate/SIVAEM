package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Candidato;
import models.Cliente;
import models.Empresa;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PerfilFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private AplicationHomeFrame app;

	public PerfilFrame(AplicationHomeFrame app, Candidato candidato, Empresa empresa) {
		this.app = app;
		setBounds(100, 100, 450, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PERFIL");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(194, 33, 141, 52);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Editar perfil");
		btnNewButton.setBounds(248, 244, 131, 38);
		contentPane.add(btnNewButton);
		
		JButton btnExcluirPerfil = new JButton("Excluir perfil");
		btnExcluirPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmExclusion();
			}
		});
		btnExcluirPerfil.setBounds(71, 244, 131, 38);
		contentPane.add(btnExcluirPerfil);
		
		
		JLabel lblNewLabel_1 = new JLabel("Tipo:");
		lblNewLabel_1.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(138, 120, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel tipoLabel = new JLabel("New label");
		tipoLabel.setFont(new Font("Consolas", Font.PLAIN, 12));
		tipoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tipoLabel.setBounds(194, 118, 141, 14);
		contentPane.add(tipoLabel);
		tipoLabel.setText(candidato != null ? "Candidato" : "Empresa");
		
		JLabel lblNewLabel_1_1 = new JLabel("Nome:");
		lblNewLabel_1_1.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(138, 145, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel nomeLabel = new JLabel("New Label");
		nomeLabel.setFont(new Font("Consolas", Font.PLAIN, 12));
		nomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nomeLabel.setBounds(194, 143, 141, 14);
		contentPane.add(nomeLabel);
		nomeLabel.setText("nome");
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Email:");
		lblNewLabel_1_1_1.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblNewLabel_1_1_1.setBounds(138, 170, 46, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel emailLabel = new JLabel("New Label");
		emailLabel.setFont(new Font("Consolas", Font.PLAIN, 12));
		emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		emailLabel.setBounds(194, 168, 141, 14);
		contentPane.add(emailLabel);
		emailLabel.setText("email");
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Senha:");
		lblNewLabel_1_1_1_1.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1.setBounds(138, 195, 46, 14);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JLabel emailLabel_1 = new JLabel("New Label");
		emailLabel_1.setFont(new Font("Consolas", Font.PLAIN, 12));
		emailLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		emailLabel_1.setBounds(194, 193, 141, 14);
		contentPane.add(emailLabel_1);
		emailLabel_1.setText("senha");
		
		JLabel perfilLogo = new JLabel("");
		perfilLogo.setBounds(110, 10, 74, 99);
		contentPane.add(perfilLogo);
		Image logoPerfil = new ImageIcon(this.getClass().getResource("/perfil_64.png")).getImage();
		perfilLogo.setIcon(new ImageIcon(logoPerfil));
	}
	public void confirmExclusion() {
		ConfirmationModal confirm = new ConfirmationModal(this);
		confirm.setVisible(true);
	}
	public void excluirPerfil() {
		app.enviarProClienteExcluir();
		dispose();
	}
		
}
