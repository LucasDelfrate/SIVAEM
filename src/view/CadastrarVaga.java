package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastrarVaga extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private AplicationHomeFrame app;

	public CadastrarVaga(AplicationHomeFrame app) {
		this.app = app;
		setBounds(100, 100, 450, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastrar vaga");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(153, 25, 129, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(60, 65, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(60, 88, 303, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(60, 141, 303, 19);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Faixa Salarial");
		lblNewLabel_1_1.setBounds(60, 118, 92, 13);
		contentPane.add(lblNewLabel_1_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(60, 193, 303, 19);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("Descrição");
		lblNewLabel_1_2.setBounds(60, 170, 45, 13);
		contentPane.add(lblNewLabel_1_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(60, 245, 303, 19);
		contentPane.add(textField_3);
		
		JLabel lblNewLabel_1_3 = new JLabel("Competências");
		lblNewLabel_1_3.setBounds(60, 222, 138, 13);
		contentPane.add(lblNewLabel_1_3);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarVaga();
			}
		});
		btnNewButton.setBounds(175, 312, 85, 21);
		contentPane.add(btnNewButton);
	}
	public void cadastrarVaga() {
		this.app.cadastrarVagaEmpresa();
	}
}
