package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;

import controllers.JSONController;
import models.Cliente;
import models.Vaga;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VisualizarUmaVaga extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idVaga;
	private Cliente cliente;
	private String email;
	private String token;
	private AplicationHomeFrame app;


	public VisualizarUmaVaga(AplicationHomeFrame app, Cliente cliente, String email, String token) {
		this.app = app;
		this.cliente = cliente;
		this.email = email;
		this.token = token;
		setBounds(100, 100, 258, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Visualizar Vaga");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(44, 11, 154, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Id da vaga: ");
		lblNewLabel_1.setBounds(44, 46, 65, 14);
		contentPane.add(lblNewLabel_1);
		
		idVaga = new JTextField();
		idVaga.setBounds(119, 43, 94, 20);
		contentPane.add(idVaga);
		idVaga.setColumns(10);
		
		JButton btnNewButton = new JButton("Visualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(idVaga.getText()); 
				visualizar(id);
			}
		});
		btnNewButton.setBounds(61, 93, 127, 23);
		contentPane.add(btnNewButton);
	}
	public void visualizar(int id) {
		Vaga vaga = new Vaga();
		vaga.setId(id);
		vaga.setOperacao("visualizarVaga");
		vaga.setEmail(email);
		vaga.setToken(token);
		JSONController json = new JSONController();
		JSONObject obj = json.changeReponseToJsonVagaListar(vaga);
		this.app.enviarDadosClienteVaga(obj, id);
	}
}
