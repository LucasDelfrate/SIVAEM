package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;

import controllers.JSONController;
import models.Competencia;
import models.Vaga;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JCheckBox;

public class CadastrarVaga extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField campoNome;
	private JTextField campoFaixa;
	private JTextField campoDescricao;
	private AplicationHomeFrame app;
	private JTextField campoEstado;
	private List<Competencia> competencias;
	private JCheckBox[] checkBoxes;
	private String email;
	private String token;

	public CadastrarVaga(AplicationHomeFrame app, String email,String token) {
		this.email = email;
		this.token = token;
		this.competencias = new ArrayList();
		Vaga vaga = new Vaga();
		this.app = app;
		setBounds(100, 100, 450, 592);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastrar vaga");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(153, 25, 129, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(60, 65, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		campoNome = new JTextField();
		campoNome.setBounds(60, 88, 303, 19);
		contentPane.add(campoNome);
		campoNome.setColumns(10);
		
		campoFaixa = new JTextField();
		campoFaixa.setColumns(10);
		campoFaixa.setBounds(60, 141, 303, 19);
		contentPane.add(campoFaixa);
		
		JLabel lblNewLabel_1_1 = new JLabel("Faixa Salarial");
		lblNewLabel_1_1.setBounds(60, 118, 92, 13);
		contentPane.add(lblNewLabel_1_1);
		
		campoDescricao = new JTextField();
		campoDescricao.setColumns(10);
		campoDescricao.setBounds(60, 193, 303, 19);
		contentPane.add(campoDescricao);
		
		JLabel lblNewLabel_1_2 = new JLabel("Descrição");
		lblNewLabel_1_2.setBounds(60, 170, 192, 13);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Competências");
		lblNewLabel_1_3.setBounds(60, 298, 138, 13);
		contentPane.add(lblNewLabel_1_3);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vaga.setDescricao(campoDescricao.getText());
				vaga.setFaixaSalarial(campoFaixa.getText());
				vaga.setEstado(campoEstado.getText());
				vaga.setNome(campoNome.getText());
				
				cadastrarVaga(vaga);
			}
		});
		btnNewButton.setBounds(123, 478, 187, 38);
		contentPane.add(btnNewButton);
		
		JCheckBox python = new JCheckBox("Python");
		python.setHorizontalAlignment(SwingConstants.LEFT);
		python.setBounds(60, 328, 97, 23);
		contentPane.add(python);
		
		JCheckBox Java = new JCheckBox("Java");
		Java.setHorizontalAlignment(SwingConstants.LEFT);
		Java.setBounds(155, 328, 109, 23);
		contentPane.add(Java);
		
		JCheckBox ccharp = new JCheckBox("C#");
		ccharp.setHorizontalAlignment(SwingConstants.LEFT);
		ccharp.setBounds(266, 328, 97, 23);
		contentPane.add(ccharp);
		
		JCheckBox cmaismais = new JCheckBox("C++");
		cmaismais.setHorizontalAlignment(SwingConstants.LEFT);
		cmaismais.setBounds(60, 354, 97, 23);
		contentPane.add(cmaismais);
		
		JCheckBox javascript = new JCheckBox("JavaScript");
		javascript.setHorizontalAlignment(SwingConstants.LEFT);
		javascript.setBounds(155, 354, 109, 23);
		contentPane.add(javascript);
		
		JCheckBox ruby = new JCheckBox("Ruby");
		ruby.setHorizontalAlignment(SwingConstants.LEFT);
		ruby.setBounds(266, 354, 97, 23);
		contentPane.add(ruby);
		
		JCheckBox swift = new JCheckBox("Swift");
		swift.setHorizontalAlignment(SwingConstants.LEFT);
		swift.setBounds(60, 380, 97, 23);
		contentPane.add(swift);
		
		JCheckBox sql = new JCheckBox("SQL");
		sql.setHorizontalAlignment(SwingConstants.LEFT);
		sql.setBounds(155, 380, 109, 23);
		contentPane.add(sql);
		
		JCheckBox html = new JCheckBox("HTML");
		html.setHorizontalAlignment(SwingConstants.LEFT);
		html.setBounds(266, 380, 97, 23);
		contentPane.add(html);
		
		JCheckBox css = new JCheckBox("CSS");
		css.setHorizontalAlignment(SwingConstants.LEFT);
		css.setBounds(60, 406, 97, 23);
		contentPane.add(css);
		
		JCheckBox go = new JCheckBox("GO");
		go.setHorizontalAlignment(SwingConstants.LEFT);
		go.setBounds(155, 406, 109, 23);
		contentPane.add(go);
		
		JCheckBox php = new JCheckBox("PHP");
		php.setHorizontalAlignment(SwingConstants.LEFT);
		php.setBounds(266, 406, 97, 23);
		contentPane.add(php);
		
		JCheckBox flutter = new JCheckBox("Flutter");
		flutter.setHorizontalAlignment(SwingConstants.LEFT);
		flutter.setBounds(60, 432, 97, 23);
		contentPane.add(flutter);
		
		JCheckBox ts = new JCheckBox("TypeScript");
		ts.setHorizontalAlignment(SwingConstants.LEFT);
		ts.setBounds(155, 432, 109, 23);
		contentPane.add(ts);
		
		JCheckBox dart = new JCheckBox("Dart");
		dart.setHorizontalAlignment(SwingConstants.LEFT);
		dart.setBounds(266, 432, 97, 23);
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
		
		campoEstado = new JTextField();
		campoEstado.setColumns(10);
		campoEstado.setBounds(60, 246, 303, 19);
		contentPane.add(campoEstado);
		
		JLabel campoE = new JLabel("Estado");
		campoE.setBounds(60, 223, 192, 13);
		contentPane.add(campoE);
		
		
	}
	public void cadastrarVaga(Vaga vaga) {
		int length = this.checkBoxes.length;
		for(int i = 0; i < length; i++) {
			Competencia comp = new Competencia();
			if(this.checkBoxes[i].isSelected()) {
				comp.setDescricao(this.checkBoxes[i].getLabel());
				this.competencias.add(comp);				
			}
		}
		vaga.setCompetencias(this.competencias);
		vaga.setOperacao("cadastrarVaga");
		vaga.setEmail(this.email);
		vaga.setToken(this.token);
		
		JSONController json = new JSONController();
		JSONObject obj = json.changeReponseToJsonVaga(vaga);
		this.app.enviarDadosClienteCompetencia(obj);
		
	}
}
