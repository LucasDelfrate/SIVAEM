package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Competencia;
import models.CompetenciaExperiencia;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Choice;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class CadastrarCompetencia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CompetenciaExperiencia competencias;
	private int tamanho = 1;
	private CadastrarCompetenciasFrame comps;

	public CadastrarCompetencia(CadastrarCompetenciasFrame comps) {
		this.comps = comps;
		this.competencias = new CompetenciaExperiencia();
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Adicionar competência");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(137, 11, 159, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Competência:");
		lblNewLabel_1.setBounds(80, 52, 96, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Experiência");
		lblNewLabel_2.setBounds(80, 115, 96, 14);
		contentPane.add(lblNewLabel_2);
		
		Choice choice = new Choice();
		choice.setBounds(64, 72, 306, 20);
		contentPane.add(choice);
		choice.addItem("Python");
		choice.addItem("C#");
		choice.addItem("JS");
		choice.addItem("PHP");
		choice.addItem("Swift");
		choice.addItem("Java");
		choice.addItem("Go");
		choice.addItem("SQL");
		choice.addItem("Ruby");
		choice.addItem("HTML");
		choice.addItem("CSS");
		choice.addItem("Flutter");
		choice.addItem("TypeScript");
		
		Choice choice2 = new Choice();
		choice2.setBounds(64, 138, 306, 20);
		contentPane.add(choice2);
		choice2.addItem("1");
		choice2.addItem("2");
		choice2.addItem("3");
		choice2.addItem("4");
		choice2.addItem("5");
		
		
		JButton btnNewButton = new JButton("Adicionar competência");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tituloCompetencia = choice.getSelectedItem();
				String experiencia = choice2.getSelectedItem();
				adicionarCompetencia(tituloCompetencia, experiencia);
			}
		});
		btnNewButton.setBounds(64, 186, 306, 23);
		contentPane.add(btnNewButton);
	}
	public void adicionarCompetencia(String titulo, String exp){
		Competencia comp = new Competencia();
		comp.setDescricao(titulo);
		comp.setExperiencia(exp);
		this.comps.receberCompetencia(comp);
		dispose();
	}
}
