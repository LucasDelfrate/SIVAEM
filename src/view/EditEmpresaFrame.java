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

public class EditEmpresaFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField razaoField;
	private JTextField ramoField;
	private PerfilFrame perfil;
	private Empresa empresa;
	private JTextField descricaoField;
	private JTextField cnpjField;
	private JTextField senhaField;

	public EditEmpresaFrame(PerfilFrame perfil, Empresa empresa) {
		this.perfil = perfil;
		this.empresa = empresa;
		setBounds(100, 100, 451, 556);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EDITAR PERFIL");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(60, 23, 314, 42);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Razão Social");
		lblNewLabel_1.setBounds(86, 76, 112, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Ramo");
		lblNewLabel_1_1_1.setBounds(86, 140, 46, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		razaoField = new JTextField();
		razaoField.setBounds(83, 101, 269, 28);
		contentPane.add(razaoField);
		razaoField.setColumns(10);
		razaoField.setText(this.empresa != null ? this.empresa.getRazaoSocial() : "");
		
		ramoField = new JTextField();
		ramoField.setColumns(10);
		ramoField.setBounds(83, 165, 269, 28);
		contentPane.add(ramoField);
		ramoField.setText(this.empresa != null ? this.empresa.getRamo() : "");
		
		JButton btnNewButton = new JButton("Confirmar Edição");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String razao = razaoField.getText();
				String ramo = ramoField.getText();
				String senha = senhaField.getText();
				String descricao = descricaoField.getText();
				String cnpj = cnpjField.getText();
				
				empresa.setCnpj(cnpj);
				empresa.setDescricao(descricao);
				empresa.setRamo(ramo);
				empresa.setRazaoSocial(razao);
				empresa.setSenha(senha);
				
				
				editar(empresa);
			}
		});
		btnNewButton.setBounds(144, 408, 146, 35);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(175, 454, 89, 23);
		contentPane.add(btnNewButton_1);
		
		descricaoField = new JTextField();
		descricaoField.setText("");
		descricaoField.setColumns(10);
		descricaoField.setBounds(83, 229, 269, 28);
		contentPane.add(descricaoField);
		descricaoField.setText(this.empresa != null ? this.empresa.getDescricao() : "");
		
		JLabel lblNewLabel_1_1 = new JLabel("Descrição");
		lblNewLabel_1_1.setBounds(86, 204, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Cnpj");
		lblNewLabel_1_1_1_1.setBounds(86, 268, 46, 14);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		cnpjField = new JTextField();
		cnpjField.setText("cnpj");
		cnpjField.setColumns(10);
		cnpjField.setBounds(83, 293, 269, 28);
		contentPane.add(cnpjField);
		cnpjField.setText(this.empresa != null ? this.empresa.getCnpj() : "");
		
		senhaField = new JTextField();
		senhaField.setText("senha");
		senhaField.setColumns(10);
		senhaField.setBounds(83, 357, 269, 28);
		contentPane.add(senhaField);
		senhaField.setText(this.empresa != null ? this.empresa.getSenha() : "");
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Senha");
		lblNewLabel_1_1_1_1_1.setBounds(86, 332, 46, 14);
		contentPane.add(lblNewLabel_1_1_1_1_1);
	}
	public void editar(Empresa emp){
		this.perfil.enviarDadosEdição(null, emp);
		
	}
}
