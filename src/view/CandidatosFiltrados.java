package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.json.simple.JSONObject;

import controllers.JSONController;
import models.CandidatosResposta;
import models.Cliente;
import models.Competencia;
import models.Mensagem;
import models.RespostaFiltro;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class CandidatosFiltrados extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private String email;
	private String token;
	private Cliente cliente;
	private DefaultTableModel tableModel;
	private List<Integer> checkboxes;


	public CandidatosFiltrados(String email, String token, Cliente cliente, RespostaFiltro res) {
		this.email = email;
		this.token = token;
		this.cliente = cliente;
		this.checkboxes = new ArrayList();
		setBounds(100, 100, 762, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Candidatos ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(259, 11, 228, 33);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 50, 658, 288);
		contentPane.add(scrollPane);
		
		this.tableModel = new DefaultTableModel(new Object[][] {
		},
		new String[] {
				"IdCandidato", "Nome", "Email","Competencia", "Selecionado"
			});
		table = new JTable(tableModel) {
			 @Override
	            public Class<?> getColumnClass(int column) {
	                switch (column) {
	                    case 0:
	                        return String.class;
	                    case 1:
	                        return String.class; 
	                    case 2:
	                        return String.class; 
	                    case 3:
	                        return String.class; 
	                    case 4:
	                        return Boolean.class; 
	                    default:
	                        return String.class;
	                }
	            }
		};
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Enviar mensagem");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarMensagem();
			}
		});
		btnNewButton.setBounds(232, 365, 281, 33);
		contentPane.add(btnNewButton);
		res.getCandidatos().forEach((candidato) -> {
			List<Competencia> lista = candidato.getCompExp().getCompetencias();
			List<String> nomes = new ArrayList();
			lista.forEach((comp)->{
				nomes.add(comp.getDescricao());
			});
			
			this.tableModel.addRow(new Object[]{candidato.getId(), candidato.getNome(), candidato.getEmail(),  String.join(", ", nomes), false});
		});
		mostrarCandidatos(res.getCandidatos());
	}
	public void enviarMensagem() {
		int linhas = this.tableModel.getRowCount();
		for(int i = 0; i < linhas; i++) {
			 Boolean selected = (Boolean) this.tableModel.getValueAt(i, 4);
			if(selected) {
				int id = (int) this.tableModel.getValueAt(i,0);
				this.checkboxes.add(id);
			}
		}
		
		
		Mensagem msg = new Mensagem();
		msg.setOperacao("enviarMensagem");
		msg.setToken(this.token);
		msg.setEmail(this.email);
		msg.setCandidatos(this.checkboxes);
		JSONController json = new JSONController();
		JSONObject response = json.changeMsgToJson(msg);
		this.cliente.enviarMensagem(response);
	}
	
	public void mostrarCandidatos(List<CandidatosResposta> candidatos) {
		candidatos.forEach((candidato) -> {
			System.out.println("Nome: " + candidato.getNome());
		});
	}

}
