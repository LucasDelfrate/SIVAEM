package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Cliente;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class ClientFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtClientPort;
	private JTextField txtClientIp;


	public ClientFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLigarCliente = new JLabel("LIGAR CLIENTE");
		lblLigarCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblLigarCliente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLigarCliente.setBounds(132, 30, 165, 25);
		contentPane.add(lblLigarCliente);
		
		JLabel lblNewLabel_1_1 = new JLabel("Porta");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(192, 82, 45, 13);
		contentPane.add(lblNewLabel_1_1);
		
		txtClientPort = new JTextField();
		txtClientPort.setColumns(10);
		txtClientPort.setBounds(132, 105, 165, 19);
		contentPane.add(txtClientPort);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Endereço IP");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(142, 136, 145, 13);
		contentPane.add(lblNewLabel_1_1_1);
		
		txtClientIp = new JTextField();
		txtClientIp.setColumns(10);
		txtClientIp.setBounds(132, 157, 165, 19);
		contentPane.add(txtClientIp);
		
		JButton btnLigarCliente = new JButton("LIGAR");
		btnLigarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int clientPort = Integer.parseInt(txtClientPort.getText());
				String clientIp = txtClientIp.getText();
				try {
					Cliente cliente = new Cliente(clientIp, clientPort);
					chamarHome(cliente);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLigarCliente.setBounds(172, 198, 85, 21);
		contentPane.add(btnLigarCliente);
	}
	void chamarHome(Cliente cliente){
		HomeFrame home = new HomeFrame(cliente);
		dispose();
		home.setVisible(true);
	}

}
