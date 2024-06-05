package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;

import controllers.JSONController;
import models.Competencia;
import models.CompetenciaExperiencia;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class CadastrarCompetenciasFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CompetenciaExperiencia compExp;
	private AplicationHomeFrame app;
	private String email;
	private String token;

	public CadastrarCompetenciasFrame(AplicationHomeFrame app, String email, String token) {
		this.compExp = new CompetenciaExperiencia();
		this.email = email;
		this.app = app;
		this.token = token;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 190);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Adicionar Competencia");
		lblNewLabel.setBounds(96, 26, 145, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirCadastroCompetencia();
			}
		});
		btnNewButton.setBounds(251, 22, 89, 23);
		contentPane.add(btnNewButton);
		
		JList list = new JList();
		list.setBounds(140, 89, 101, 0);
		contentPane.add(list);
		
		JButton btnNewButton_1 = new JButton("Enviar competências");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarCompetencias();
			}
		});
		btnNewButton_1.setBounds(80, 104, 268, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel num = new JLabel("New label");
		num.setBounds(96, 64, 244, 14);
		contentPane.add(num);
		int num1 = this.compExp.competencias.size();
		num.setText("Competências adicionadas "+ num1);
	}
	
	public void receberCompetencia(Competencia comp){
		System.out.println("receber comp: " + comp.getDescricao());
		this.compExp.adicionarCompetencia(comp);
		//preciso ver porque meu array ta indo vazio
	}
	
	public void abrirCadastroCompetencia(){
		CadastrarCompetencia comp = new CadastrarCompetencia(this);
		comp.setVisible(true);
	}
	public void enviarCompetencias() {
		this.compExp = new CompetenciaExperiencia();
		this.compExp.setOperacao("cadastrarCompetenciaExperiencia");
		this.compExp.setEmail(this.email);
		this.compExp.setToken(this.token);
		JSONController json = new JSONController();
		JSONObject obj = json.changeReponseToJsonCompetencia(this.compExp);
		this.app.enviarDadosClienteCompetencia(obj);
	}
}
