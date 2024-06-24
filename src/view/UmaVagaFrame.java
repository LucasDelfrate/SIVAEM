package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.json.simple.JSONObject;

import controllers.JSONController;
import models.Cliente;
import models.Vaga;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UmaVagaFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField descricaoField;
	private JTextField estadoField;
	private JTextField faixaField;
	private JTable table;
	private DefaultTableModel tableModel;
	private int id;
	private String token;
	private Cliente cliente;
	private String email;

	public UmaVagaFrame() {
		setBounds(100, 100, 434, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Vaga");
		lblNewLabel.setBounds(152, 11, 114, 28);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descrição:");
		lblNewLabel_1.setBounds(56, 63, 96, 14);
		contentPane.add(lblNewLabel_1);
		
		this.descricaoField = new JTextField();
		this.descricaoField.setBounds(141, 63, 199, 34);
		contentPane.add(this.descricaoField);
		this.descricaoField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Estado:");
		lblNewLabel_1_1.setBounds(56, 108, 96, 16);
		contentPane.add(lblNewLabel_1_1);
		
		this.estadoField = new JTextField();
		this.estadoField.setBounds(141, 108, 199, 34);
		this.estadoField.setColumns(10);
		contentPane.add(this.estadoField);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Faixa salarial:");
		lblNewLabel_1_1_1.setBounds(56, 153, 96, 16);
		contentPane.add(lblNewLabel_1_1_1);
		
		this.faixaField = new JTextField();
		this.faixaField.setBounds(141, 153, 199, 35);
		this.faixaField.setColumns(10);
		contentPane.add(this.faixaField);
		
		JScrollPane competenciasTable = new JScrollPane();
		competenciasTable.setBounds(141, 199, 199, 132);
		contentPane.add(competenciasTable);
		
		table = new JTable();
		this.tableModel = new DefaultTableModel(new Object[][] {
		},
		new String[] {
				"Compet\u00EAncia"
			});
		table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(152);
		competenciasTable.setViewportView(table);
		
		JButton btnNewButton = new JButton("Editar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarVaga();
			}
		});
		btnNewButton.setBounds(56, 357, 199, 34);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("X");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirVaga();
			}
		});
		btnNewButton_1.setBounds(278, 360, 62, 31);
		contentPane.add(btnNewButton_1);
	}
	
	public void setVariables(Cliente cliente, int faixa, String descricao, String estado, List<String> competencias, int idVaga, String nome, String email, String token){
		this.cliente = cliente;
		this.descricaoField.setText(descricao);
		this.estadoField.setText(estado);
		String faixaS = Integer.toString(faixa);
		this.faixaField.setText(faixaS);
		for (String competencia : competencias) {
            String[] data = {competencia};
            this.tableModel.addRow(data);
        }
		this.email = email;
		this.token = token;
		System.out.println(this.token);
		this.id = idVaga;
		
	}
	public void editarVaga() {
		Vaga vaga1 = new Vaga();
		vaga1.setDescricao(this.descricaoField.getText());;
		vaga1.setId(this.id);;
		int faixa = Integer.parseInt(this.faixaField.getText());
		vaga1.setFaixaSalarial(faixa);;
		vaga1.setEmail(this.email);
		vaga1.setEstado(this.estadoField.getText());
		vaga1.setToken(this.token);
		vaga1.setOperacao("atualizarVaga");
		List<String> competencias = new ArrayList();

		for (int i = 0; i < this.tableModel.getRowCount(); i++) {
			String compAux =(String) tableModel.getValueAt(i, 0);
		    competencias.add(compAux);
		}
		vaga1.setCompetencias(competencias);
		JSONController json = new JSONController();
		JSONObject obj = json.changeReponseToJsonVagaListar(vaga1);
		this.cliente.enviarMensagem(obj);
	}
	public void excluirVaga() {
		Vaga vaga1 = new Vaga();
		vaga1.setEmail(this.email);
		vaga1.setToken(this.token);
		System.out.println(this.token);
		vaga1.setId(this.id);
		vaga1.setOperacao("apagarVaga");
		JSONController json = new JSONController();
		JSONObject obj = json.changeReponseToJsonVagaListar(vaga1);
		this.cliente.enviarMensagem(obj);
	}
}
