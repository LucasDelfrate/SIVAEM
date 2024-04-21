package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Servidor;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ServerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtServerPort;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerFrame frame = new ServerFrame();
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
	public ServerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLigarServidor = new JLabel("LIGAR SERVIDOR");
		lblLigarServidor.setHorizontalAlignment(SwingConstants.CENTER);
		lblLigarServidor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLigarServidor.setBounds(126, 59, 165, 25);
		contentPane.add(lblLigarServidor);
		
		JLabel lblNewLabel_1 = new JLabel("Porta");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(186, 111, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		txtServerPort = new JTextField();
		txtServerPort.setColumns(10);
		txtServerPort.setBounds(126, 134, 165, 19);
		contentPane.add(txtServerPort);
		
		JButton btnLigarServidor = new JButton("LIGAR");
		btnLigarServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int serverPort = Integer.parseInt(txtServerPort.getText());
				Servidor servidor = new Servidor(serverPort);
				servidor.ligarServidor();
			}
		});
		btnLigarServidor.setBounds(168, 163, 85, 21);
		contentPane.add(btnLigarServidor);
	}

}
