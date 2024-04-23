package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Cliente;
import models.Servidor;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Ligar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ligar frame = new Ligar();
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
	public Ligar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CONECTION PAGE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(134, 42, 196, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("LIGAR SERVIDOR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServerFrame server = new ServerFrame();
				server.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(134, 101, 196, 46);
		contentPane.add(btnNewButton);
		
		JButton btnLigarCliente = new JButton("LIGAR CLIENTE");
		btnLigarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientFrame client = new ClientFrame();
				client.setVisible(true);
				dispose();
			}
		});
		btnLigarCliente.setBounds(134, 169, 196, 46);
		contentPane.add(btnLigarCliente);
	}
}
