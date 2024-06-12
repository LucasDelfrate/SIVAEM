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

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import models.Vaga;

public class VisualizarVagas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private List<Vaga> vagas;
	private DefaultTableModel tableModel;


	public VisualizarVagas() {
		vagas = new ArrayList();
		this.vagas = vagas;
		setBounds(100, 100, 549, 592);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Vagas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(150, 11, 232, 42);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 70, 448, 337);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nome"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.getColumnModel().getColumn(1).setPreferredWidth(130);
		scrollPane.setViewportView(table);
		
		this.tableModel = new DefaultTableModel(new Object[][] {
		},
		new String[] {
				"Id", "Nome"
		});
		table = new JTable(this.tableModel) {
			 @Override
	            public Class<?> getColumnClass(int column) {
	                switch (column) {
	                    case 0:
	                        return String.class; 
	                    case 1:
	                        return String.class; 
	                    case 2:
	                    	return Boolean.class; 
	                    default:
	                        return String.class;
	                }
	            }
		};
	}
	public void setarCompetencias(List<Vaga> vagas){
		if(this.vagas != null) {
			this.vagas.forEach(vaga -> {
				System.out.println(vaga.getNome());
				this.tableModel.addRow(new Object[]{vaga.getId(), vaga.getNome(), false});
			});			
		}
	}
}
