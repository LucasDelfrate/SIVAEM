package view;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Servidor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class ClientesConectadosFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private List<String> clientes;

	public ClientesConectadosFrame() {
		this.clientes = new ArrayList();
		setBounds(100, 100, 450, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Clientes conectados");
		lblNewLabel.setBounds(141, 11, 151, 27);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane =  new JScrollPane();
		scrollPane.setBounds(45, 45, 344, 242);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"IP"
			}
		));
		this.tableModel = new DefaultTableModel(new Object[][] {
		},
		new String[] {
				"Ip"
			});
		scrollPane.setViewportView(table);
	}
	
	public void atualizarConectados(String ip) {
		this.clientes.add(ip);
	}
	public void removerConectado(String ip) {
		for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(ip)) {
            	tableModel.removeRow(i);
                break; 
            }
        }
	}

}
