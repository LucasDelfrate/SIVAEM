package view;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Cliente;
import models.Competencia;
import models.CompetenciaExperiencia;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.json.simple.JSONObject;

import controllers.JSONController;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JCheckBox;

public class EditarCompetencias extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private List<Competencia> competencias;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnNewButton;
	private String email;
	private String token;
	private Cliente cliente;
	private JButton btnNewButton_1;


	public EditarCompetencias(List<Competencia> competencias, Cliente cliente, String token, String email) {
		this.cliente = cliente;
		this.token = token;
		this.email = email;
		this.competencias = competencias;
		setBounds(100, 100, 450, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Competências");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(90, 11, 253, 14);
		contentPane.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 48, 337, 383);
		contentPane.add(scrollPane);
		
		DefaultTableModel tableModel = new DefaultTableModel(new Object[][] {
			},
			new String[] {
					"Compet\u00EAncia", "Experi\u00EAncia", "Selecionado"
				});
		table = new JTable(tableModel) {
			 @Override
	            public Class<?> getColumnClass(int column) {
	                switch (column) {
	                    case 0:
	                        return String.class; // Descrição
	                    case 1:
	                        return String.class; // Experiência
	                    case 2:
	                        return Boolean.class; // Checkbox
	                    default:
	                        return String.class;
	                }
	            }
		};
		scrollPane.setViewportView(table);
		
		btnNewButton = new JButton("Editar competencia");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linhas = tableModel.getRowCount();
				List<Competencia> competencias2 = new ArrayList();
				for(int i = 0; i < linhas ; i++) {
					Competencia compAux = new Competencia();
					compAux.setDescricao(competencias.get(i).getDescricao());
					Object value = tableModel.getValueAt(i,1);
					compAux.setExperiencia(value.toString());
					competencias2.add(compAux);	
				}
				editarCompetencias(competencias2);
			}
		});
		btnNewButton.setBounds(64, 442, 239, 33);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("X");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCompetenciasSelecionadas(table);
			}
		});
		btnNewButton_1.setBounds(328, 442, 57, 33);
		contentPane.add(btnNewButton_1);
		setarCompetencias(tableModel);
	}
	public void setarCompetencias(DefaultTableModel table){
		if(this.competencias != null) {
			this.competencias.forEach(comp -> {
				table.addRow(new Object[]{comp.getDescricao(), comp.getExperiencia(), false});
			});			
		}
	}
	public void editarCompetencias(List<Competencia> competencias) {
		CompetenciaExperiencia compExp = new CompetenciaExperiencia();
		compExp.setToken(this.token);
		compExp.setEmail(this.email);
		compExp.setOperacao("atualizarCompetenciaExperiencia");
		compExp.setCompetencias(competencias);
		JSONController json = new JSONController();
		JSONObject obj = json.changeReponseToJsonCompetencia(compExp);
		this.cliente.enviarMensagem(obj);
	}
	public void excluirCompetenciasSelecionadas(JTable table) {
		List<Competencia> competenciasSelecionadas = new ArrayList();
		for (int i = 0; i < table.getRowCount(); i++) {
            Boolean isChecked = (Boolean) table.getValueAt(i, 2);
            if (isChecked != null && isChecked) {
            	competenciasSelecionadas.add(this.competencias.get(i));
            }
        }
		
		CompetenciaExperiencia compExp = new CompetenciaExperiencia();
		compExp.setToken(this.token);
		compExp.setEmail(this.email);
		compExp.setOperacao("apagarCompetenciaExperiencia");
		compExp.setCompetencias(competenciasSelecionadas);
		JSONController json = new JSONController();
		JSONObject obj = json.changeReponseToJsonCompetencia(compExp);
		this.cliente.enviarMensagem(obj);
		dispose();
	}
}
