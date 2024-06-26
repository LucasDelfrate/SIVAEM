package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.json.simple.JSONObject;

import controllers.JSONController;
import models.Cliente;
import models.Mensagem;
import models.RespostaFiltro;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CandidatosFiltrados extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private String email;
	private String token;
	private Cliente cliente;
	private DefaultTableModel tableModel;


	public CandidatosFiltrados(String email, String token, Cliente cliente, RespostaFiltro res) {
		this.email = email;
		this.token = token;
		this.cliente = cliente;
		setBounds(100, 100, 450, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Candidatos ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(103, 11, 228, 33);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 50, 352, 288);
		contentPane.add(scrollPane);
		
		this.tableModel = new DefaultTableModel(new Object[][] {
		},
		new String[] {
				"IdCandidato", "Nome", "Email","Competencia"
			});
		table = new JTable(this.tableModel);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Enviar mensagem");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarMensagem();
			}
		});
		btnNewButton.setBounds(76, 361, 281, 33);
		contentPane.add(btnNewButton);
		
		res.getCandidatos().forEach((candidato) -> {
			this.tableModel.addRow(new Object[]{candidato.getId(), candidato.getNome(), candidato.getEmail(), candidato.getCompExp().getCompetencias().get(0).getDescricao()});
		});
	}
	public void enviarMensagem() {
		Mensagem msg = new Mensagem();
		msg.setOperacao("EnviarMensagem");
		msg.setToken(this.token);
		msg.setEmail(this.email);
		int[] ids = {1,2,3};
		msg.setCandidatos(ids);
		JSONController json = new JSONController();
		JSONObject response = json.changeMsgToJson(msg);
		this.cliente.enviarMensagem(response);
	}

}
