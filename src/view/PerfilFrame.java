package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Candidato;
import models.Cliente;
import models.Empresa;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PerfilFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private AplicationHomeFrame app;
	private Candidato candidato;
	private Empresa empresa;
	private EditFrame edit;
	private EditEmpresaFrame editEmpresa;
	private JLabel tipo;
	private JLabel email;
	private JLabel user;
	private JLabel razao;
	private JLabel ramo;
	private JLabel descricao;
	private JLabel cnpj;
	private JLabel senha;
	private JLabel nome;
	
	private JLabel ramoLabel;
	private JLabel descricaoLabel;
	private JLabel cnpjLabel;

	public PerfilFrame(AplicationHomeFrame app) {
		this.candidato = new Candidato();
		this.empresa = new Empresa();
		this.edit = new EditFrame(this, this.candidato);
		this.app = app;
		setBounds(100, 100, 452, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PERFIL");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(194, 33, 141, 52);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Editar perfil");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirEditar();
			}
		});
		btnNewButton.setBounds(247, 354, 131, 38);
		contentPane.add(btnNewButton);
		
		JButton btnExcluirPerfil = new JButton("Excluir perfil");
		btnExcluirPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmExclusion();
			}
		});
		btnExcluirPerfil.setBounds(58, 354, 131, 38);
		contentPane.add(btnExcluirPerfil);
		
		
		JLabel lblNewLabel_1 = new JLabel("Tipo:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(91, 118, 98, 14);
		contentPane.add(lblNewLabel_1); 
		
		this.tipo = new JLabel("New label");
		this.tipo.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.tipo.setHorizontalAlignment(SwingConstants.CENTER);
		this.tipo.setBounds(194, 118, 214, 14);
		contentPane.add(this.tipo);
		
		this.nome = new JLabel("Nome:");
		nome.setHorizontalAlignment(SwingConstants.CENTER);
		this.nome.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.nome.setBounds(91, 143, 98, 14);
		contentPane.add(this.nome);
		
		this.user = new JLabel("New Label");
		this.user.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.user.setHorizontalAlignment(SwingConstants.CENTER);
		this.user.setBounds(194, 143, 214, 14);
		contentPane.add(this.user);

		
		JLabel lblNewLabel_1_1_1 = new JLabel("Email:");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblNewLabel_1_1_1.setBounds(91, 168, 98, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		this.email = new JLabel("New Label");
		this.email.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.email.setHorizontalAlignment(SwingConstants.CENTER);
		this.email.setBounds(194, 168, 214, 14);
		contentPane.add(this.email);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Senha:");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1.setBounds(91, 193, 98, 14);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		this.senha = new JLabel("New Label");
		this.senha.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.senha.setHorizontalAlignment(SwingConstants.CENTER);
		this.senha.setBounds(194, 193, 214, 14);
		contentPane.add(this.senha);
		
		JLabel perfilLogo = new JLabel("");
		perfilLogo.setBounds(110, 10, 74, 99);
		contentPane.add(perfilLogo);
		Image logoPerfil = new ImageIcon(this.getClass().getResource("/perfil_64.png")).getImage();
		perfilLogo.setIcon(new ImageIcon(logoPerfil));
	

		this.cnpjLabel = new JLabel("Cnpj:");
		this.cnpjLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.cnpjLabel.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.cnpjLabel.setBounds(91, 218, 98, 14);
		contentPane.add(this.cnpjLabel);
		
		this.cnpj = new JLabel((String) null);
		this.cnpj.setHorizontalAlignment(SwingConstants.CENTER);
		this.cnpj.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.cnpj.setBounds(194, 218, 214, 14);
		contentPane.add(this.cnpj);
		
		this.ramoLabel = new JLabel("Ramo:");
		this.ramoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.ramoLabel.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.ramoLabel.setBounds(91, 245, 98, 14);
		contentPane.add(this.ramoLabel);
		
		this.ramo = new JLabel((String) null);
		this.ramo.setHorizontalAlignment(SwingConstants.CENTER);
		this.ramo.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.ramo.setBounds(194, 245, 214, 14);
		contentPane.add(this.ramo);
		
		this.descricaoLabel = new JLabel("Descrição:");
		this.descricaoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.descricaoLabel.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.descricaoLabel.setBounds(91, 273, 98, 14);
		contentPane.add(this.descricaoLabel);
		
		this.descricao = new JLabel((String) null);
		descricao.setHorizontalAlignment(SwingConstants.CENTER);
		descricao.setVerticalAlignment(SwingConstants.TOP);
		this.descricao.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.descricao.setBounds(194, 272, 214, 52);
		contentPane.add(this.descricao);
		
	}
	public void confirmExclusion() {
		if(this.empresa.getCnpj() == null) {
			ConfirmationModal confirm = new ConfirmationModal(this, true);			
			confirm.setVisible(true);
		}else {
			ConfirmationModal confirm = new ConfirmationModal(this, false);			
			confirm.setVisible(true);
		}
	}
	public void excluirPerfilCandidato() {
		app.enviarProClienteExcluir();
		dispose();
	}
	public void excluirPerfilEmpresa() {
		app.enviarProClienteExcluirEmpresa();
		dispose();
	}
	public void abrirEditar(){
		if(this.candidato.getEmail() == null) {
			EditEmpresaFrame editEmpresa = new EditEmpresaFrame(this, this.empresa);
			this.editEmpresa = editEmpresa;
			this.editEmpresa.setVisible(true);
			this.setVisible(false);
		}else {
			EditFrame edit = new EditFrame(this,this.candidato);
			this.edit = edit;
			this.edit.setVisible(true);
			this.setVisible(false);			
		}
	}
	public void enviarDadosEdição(Candidato cand, Empresa emp){
		if(cand == null) {
			this.app.enviarDadosCliente(null, emp);
		}else {
			this.app.enviarDadosCliente(cand, null);
		}
	}
	public void respostaTela(String msg){
		this.edit.dispose();
		JOptionPane.showMessageDialog(null, msg);
	}
	public void receiveInfo(Empresa empresa, Candidato candidato) {
		this.empresa = empresa;
		this.candidato = candidato;
		setarVariaveis();
	}
	public void setarVariaveis() {
		System.out.println(this.empresa.getEmail());
		if(this.empresa.getEmail() == null) {
			this.tipo.setText("Candidato");
			this.email.setText(this.candidato.getEmail());
			this.user.setText(this.candidato.getUser());
			this.senha.setText(this.candidato.getPassword());
			this.cnpj.setVisible(false);
			this.ramo.setVisible(false);
			this.descricao.setVisible(false);
			this.descricaoLabel.setVisible(false);
			this.ramoLabel.setVisible(false);
			this.cnpjLabel.setVisible(false);
		}else {
			this.tipo.setText("Empresa");
			this.email.setText(this.empresa.getEmail());
			this.cnpj.setText(this.empresa.getCnpj());
			this.ramo.setText(this.empresa.getRamo());
			this.user.setText(this.empresa.getRazaoSocial());
			this.nome.setText("Razão social");
			this.descricao.setText(this.empresa.getDescricao());
			this.senha.setText(this.empresa.getSenha());
		}
	}
}
