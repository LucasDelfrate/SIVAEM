package view;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import models.Vaga;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class VagasFiltradas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private List<Vaga> vagas;


	public VagasFiltradas(List<Vaga> vagas) {
		this.vagas = vagas;
		setBounds(100, 100, 1226, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Vagas Filtradas");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(252, 11, 103, 37);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 67, 1109, 416);
		contentPane.add(scrollPane);
		
		table = new JTable();
		this.tableModel = new DefaultTableModel(new Object[][] {
		},
		new String[] {
				"Id", "Nome", "Faixa Salarial","Descrição", "Estado","Competencias","Email"
			});
		table.setModel(tableModel);
		vagas.forEach((vaga)-> {
			this.tableModel.addRow(new Object[]{vaga.getId(), vaga.getNome(), vaga.getFaixaSalarial(), vaga.getDescricao(), vaga.getEstado(), String.join(", ", vaga.getCompetencias()), vaga.getEmail()});
		});
		scrollPane.setViewportView(table);
	}
}
