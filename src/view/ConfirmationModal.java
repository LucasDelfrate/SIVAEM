package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConfirmationModal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Boolean response;
	private PerfilFrame perfil;


	public ConfirmationModal(PerfilFrame perfil, Boolean isCandidato) {
		this.perfil = perfil;
		setBounds(100, 100, 450, 215);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Você tem certeza que deseja excluir sua conta?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(43, 35, 337, 32);
		contentPane.add(lblNewLabel);
		
		JButton cancelBtn = new JButton("Cancelar");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				retornar(false, isCandidato);
			}
		});
		cancelBtn.setBounds(88, 91, 118, 33);
		contentPane.add(cancelBtn);
		
		JButton confirmBtn = new JButton("Confirmar");
		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				retornar(true, isCandidato);
			}
		});
		confirmBtn.setBounds(228, 91, 118, 33);
		contentPane.add(confirmBtn);
	}
	public void retornar(Boolean response, Boolean isCandidato){
		if(response) {
			dispose();
			if(isCandidato) {
				perfil.excluirPerfilCandidato();							
			}else {
				perfil.excluirPerfilEmpresa();
			}
		}else dispose();
	}
}
