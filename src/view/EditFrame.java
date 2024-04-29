package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Candidato;
import models.Empresa;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private Candidato candidato;
	private PerfilFrame perfil;
	private Empresa emp;

	public EditFrame(PerfilFrame perfil, Candidato candidato) {
		this.perfil = perfil;
		this.candidato = candidato;
		setBounds(100, 100, 450, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EDITAR PERFIL");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(60, 23, 314, 42);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(85, 110, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Senha");
		lblNewLabel_1_1_1.setBounds(85, 174, 46, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		textField = new JTextField();
		textField.setBounds(82, 135, 269, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(this.candidato != null ? this.candidato.getUser() : "");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(82, 199, 269, 28);
		contentPane.add(textField_2);
		textField_2.setText(this.candidato != null ? this.candidato.getPassword() : "");
		
		JButton btnNewButton = new JButton("Confirmar Edição");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newNome = textField.getText();
				String newSenha = textField_2.getText();
				candidato.setUser(newNome);
				candidato.setPassword(newSenha);
				editar(candidato);
			}
		});
		btnNewButton.setBounds(144, 281, 146, 35);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(172, 327, 89, 23);
		contentPane.add(btnNewButton_1);
	}
	public void editar(Candidato cand){
		this.perfil.enviarDadosEdição(cand);
		
	}
}
