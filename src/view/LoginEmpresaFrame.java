package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginEmpresaFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginEmpresaFrame frame = new LoginEmpresaFrame();
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
	public LoginEmpresaFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLoginEmpresa = new JLabel("LOGIN EMPRESA");
		lblLoginEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginEmpresa.setForeground(Color.BLACK);
		lblLoginEmpresa.setFont(new Font("Verdana", Font.BOLD, 20));
		lblLoginEmpresa.setBackground(Color.BLACK);
		lblLoginEmpresa.setBounds(106, 55, 227, 41);
		contentPane.add(lblLoginEmpresa);
		
		JLabel lblNewLabel_1 = new JLabel("Usuário");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(180, 130, 78, 13);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(106, 153, 227, 29);
		contentPane.add(textField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Senha");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(180, 211, 78, 13);
		contentPane.add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(106, 234, 227, 29);
		contentPane.add(textField_1);
		
		JButton btnNewButton = new JButton("LOGAR");
		btnNewButton.setBounds(106, 286, 227, 41);
		contentPane.add(btnNewButton);
	}

}
