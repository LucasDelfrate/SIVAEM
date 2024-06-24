package view;

import java.awt.EventQueue;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;

import controllers.JSONController;
import models.Cliente;
import models.Filtro;
import models.FiltroDentro;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class FiltrarVagasFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JCheckBox[] checkBoxes;
	private JTextField textField;
	private JLabel tipo;
	private List<String> competencias;
	private String token;
	private Cliente cliente;

	public FiltrarVagasFrame(String token, Cliente cliente) {
		this.competencias = new ArrayList();
		this.token = token;
		this.cliente = cliente;
		setBounds(100, 100, 442, 294);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Filtrar VAGAS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(173, 10, 80, 31);
		contentPane.add(lblNewLabel);
		
		JCheckBox python = new JCheckBox("Python");
		python.setHorizontalAlignment(SwingConstants.LEFT);
		python.setBounds(72, 48, 97, 23);
		contentPane.add(python);
		
		JCheckBox Java = new JCheckBox("Java");
		Java.setHorizontalAlignment(SwingConstants.LEFT);
		Java.setBounds(167, 48, 109, 23);
		contentPane.add(Java);
		
		JCheckBox ccharp = new JCheckBox("C#");
		ccharp.setHorizontalAlignment(SwingConstants.LEFT);
		ccharp.setBounds(278, 48, 97, 23);
		contentPane.add(ccharp);
		
		JCheckBox cmaismais = new JCheckBox("C++");
		cmaismais.setHorizontalAlignment(SwingConstants.LEFT);
		cmaismais.setBounds(72, 74, 97, 23);
		contentPane.add(cmaismais);
		
		JCheckBox javascript = new JCheckBox("JavaScript");
		javascript.setHorizontalAlignment(SwingConstants.LEFT);
		javascript.setBounds(167, 74, 109, 23);
		contentPane.add(javascript);
		
		JCheckBox ruby = new JCheckBox("Ruby");
		ruby.setHorizontalAlignment(SwingConstants.LEFT);
		ruby.setBounds(278, 74, 97, 23);
		contentPane.add(ruby);
		
		JCheckBox swift = new JCheckBox("Swift");
		swift.setHorizontalAlignment(SwingConstants.LEFT);
		swift.setBounds(72, 100, 97, 23);
		contentPane.add(swift);
		
		JCheckBox sql = new JCheckBox("SQL");
		sql.setHorizontalAlignment(SwingConstants.LEFT);
		sql.setBounds(167, 100, 109, 23);
		contentPane.add(sql);
		
		JCheckBox html = new JCheckBox("HTML");
		html.setHorizontalAlignment(SwingConstants.LEFT);
		html.setBounds(278, 100, 97, 23);
		contentPane.add(html);
		
		JCheckBox css = new JCheckBox("CSS");
		css.setHorizontalAlignment(SwingConstants.LEFT);
		css.setBounds(72, 126, 97, 23);
		contentPane.add(css);
		
		JCheckBox go = new JCheckBox("GO");
		go.setHorizontalAlignment(SwingConstants.LEFT);
		go.setBounds(167, 126, 109, 23);
		contentPane.add(go);
		
		JCheckBox php = new JCheckBox("PHP");
		php.setHorizontalAlignment(SwingConstants.LEFT);
		php.setBounds(278, 126, 97, 23);
		contentPane.add(php);
		
		JCheckBox flutter = new JCheckBox("Flutter");
		flutter.setHorizontalAlignment(SwingConstants.LEFT);
		flutter.setBounds(72, 152, 97, 23);
		contentPane.add(flutter);
		
		JCheckBox ts = new JCheckBox("TypeScript");
		ts.setHorizontalAlignment(SwingConstants.LEFT);
		ts.setBounds(167, 152, 109, 23);
		contentPane.add(ts);
		
		JCheckBox dart = new JCheckBox("Dart");
		dart.setHorizontalAlignment(SwingConstants.LEFT);
		dart.setBounds(278, 152, 97, 23);
		contentPane.add(dart);

		this.checkBoxes = new JCheckBox[15];
		this.checkBoxes[0] = python;
		this.checkBoxes[1] = Java;
		this.checkBoxes[2] = ccharp;
		this.checkBoxes[3] = cmaismais;
		this.checkBoxes[4] = css;
		this.checkBoxes[5] = go;
		this.checkBoxes[6] = html;
		this.checkBoxes[7] = javascript;
		this.checkBoxes[8] = php;
		this.checkBoxes[9] = ts;
		this.checkBoxes[10] = flutter;
		this.checkBoxes[11] = ruby;
		this.checkBoxes[12] = swift;
		this.checkBoxes[13] = sql;
		this.checkBoxes[14] = dart;
		
		JButton btnNewButton = new JButton("Filtrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarVaga();
			}
		});
		btnNewButton.setBounds(173, 201, 145, 31);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(55, 205, 80, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		this.tipo = new JLabel("tipo");
		this.tipo.setBounds(55, 180, 46, 14);
		contentPane.add(this.tipo);
	}
	public void filtrarVaga() {
		int length = this.checkBoxes.length;
		for(int i = 0; i < length; i++) {
			List<String> comp = new ArrayList();
			if(this.checkBoxes[i].isSelected()) {
				this.competencias.add(this.checkBoxes[i].getLabel());				
			}
		}
		FiltroDentro dentro = new FiltroDentro();
		Filtro filtro = new Filtro();
		filtro.setOperacao("filtrarVagas");
		dentro.setCompetencias(this.competencias);
		filtro.setToken(this.token);
		dentro.setTipo(this.textField.getText());
		filtro.setFiltros(dentro);
		JSONController json = new JSONController();
		JSONObject obj = json.changeReponseToJsonFiltroVaga(filtro);
		this.cliente.enviarMensagem(obj);
	}
}
