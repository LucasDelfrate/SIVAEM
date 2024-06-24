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

import models.Cliente;
import models.Vaga;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VisualizarVagas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private List<Vaga> vagas;
	private DefaultTableModel tableModel;
	private String token;
	private Cliente cliente;


	public VisualizarVagas(Cliente cliente, String token) {
		this.cliente = cliente;
		this.token = token;
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
		
		this.tableModel = new DefaultTableModel(new Object[][] {
		},
				new String[] {
						"Id", "Nome"
		});
		table = new JTable();
		table.setModel(this.tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.getColumnModel().getColumn(1).setPreferredWidth(130);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Filtrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarVaga();
			}
		});
		btnNewButton.setBounds(396, 418, 89, 23);
		contentPane.add(btnNewButton);
		
	}
	public void setarCompetencias(List<Vaga> vagas){
		if(vagas != null) {
			vagas.forEach(vaga -> {
				System.out.println("NOME: " + vaga.getNome());
				this.tableModel.addRow(new Object[]{vaga.getId(), vaga.getNome(), false});
			});			
		}
	}
	public void filtrarVaga() {
		FiltrarVagasFrame filt = new FiltrarVagasFrame(this.token, this.cliente);
		filt.setVisible(true);
	}
}
