package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import java.awt.Label;
import javax.swing.border.LineBorder;

import org.json.simple.JSONObject;

import controllers.AplicationController;
import controllers.JSONController;
import models.Candidato;
import models.Cliente;
import models.Empresa;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.JSeparator;

public class AplicationHomeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String token;
	private Candidato candidato;
	private Empresa empresa;
	private Cliente cliente;
	private PerfilFrame perfil;
	private String email;
	private Label bemVindo;
	

	public AplicationHomeFrame(Cliente cliente, String token, String email) {
		this.candidato = new Candidato();
		this.empresa = new Empresa();
		this.email = email;
		this.perfil = new PerfilFrame(this);
		this.cliente = cliente;
		this.token = token;
		
		setBounds(100, 100, 1078, 671);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		Label textBemVindo = new Label("Not Found");
		textBemVindo.setFont(new Font("Dialog", Font.PLAIN, 25));
		textBemVindo.setAlignment(Label.CENTER);
		textBemVindo.setBounds(148, 84, 541, 63);
		this.bemVindo = textBemVindo;
		contentPane.add(textBemVindo);
		
		JPanel panel = new JPanel();
		panel.setBounds(831, 0, 233, 634);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(10, 26, 223, 11);
		panel.add(separator);
		
		JLabel lblNewLabel = new JLabel("MENU");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(66, 0, 101, 28);
		panel.add(lblNewLabel);
		
		JLabel perfil = new JLabel("");
		perfil.setBounds(205, 36, 29, 28);
		panel.add(perfil);
		Image img = new ImageIcon(this.getClass().getResource("/perfil_menu_24.png")).getImage();
		perfil.setIcon(new ImageIcon(img));
		
		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirPerfil();
			}
		});
		btnPerfil.setBounds(20, 36, 175, 28);
		panel.add(btnPerfil);
		Image imgBackP = new ImageIcon(this.getClass().getResource("/madeira.jpg")).getImage();
		
		JButton btnNewButton = new JButton("");
		Image logout = new ImageIcon(this.getClass().getResource("/logout_icon.png")).getImage();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deslogar();
			}
		});
		btnNewButton.setBounds(193, 596, 30, 28);
		panel.add(btnNewButton);
		btnNewButton.setIcon(new ImageIcon(logout));
		
		JLabel ImgBackPerfil = new JLabel("New label");
		ImgBackPerfil.setBounds(0, 0, 233, 634);
		panel.add(ImgBackPerfil);
		ImgBackPerfil.setIcon(new ImageIcon(imgBackP));
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(255, 255, 255));
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(0, 0, 833, 78);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("SIVAEM");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(282, 10, 269, 64);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Consolas", Font.PLAIN, 40));
		
	}
	
	public void getByEmail(Boolean isCandidato, String email){
		this.email = email;
		if(this.cliente == null) {
			 System.out.println("Cliente é nulo");
		}else {
			if(isCandidato) {
				Candidato candidato = new Candidato();
				candidato.setOperacao("visualizarCandidato");
				candidato.setEmail(this.email);
				JSONController showController = new JSONController();
				JSONObject res = showController.changeToJSON(candidato);
				this.cliente.enviarMensagem(res);	
			}else {
				Empresa empresa = new Empresa();
				empresa.setOperacao("visualizarEmpresa");
				empresa.setEmail(this.email);
				JSONController showController = new JSONController();
				JSONObject res = showController.changeEmpresaToJSON(empresa);
				this.cliente.enviarMensagem(res);	
			}	
		}
		 
	}
	public void receiveCandidatoByEmail(String user, String senha) {
		this.candidato.setEmail(this.email);
		this.candidato.setUser(user);
		this.candidato.setPassword(senha);
		this.bemVindo.setText("Bem vindo "+ user);
	}
	public void receiveEmpresaByEmail(String razaoSocial, String descricao, String ramo, String senha, String cnpj) {
		this.empresa.setCnpj(cnpj);
		this.empresa.setDescricao(descricao);
		this.empresa.setEmail(this.email);
		this.empresa.setRamo(ramo);
		this.empresa.setRazaoSocial(razaoSocial);
		this.empresa.setSenha(senha);
		this.bemVindo.setText("Bem vindo "+ razaoSocial);
	}
	public void enviarProClienteExcluir() {
		Candidato candidato = new Candidato();
		candidato.setEmail(this.email);
		candidato.setOperacao("apagarCandidato");
		JSONController deleteController = new JSONController();
		JSONObject res = deleteController.changeToJSON(candidato);
		this.cliente.enviarMensagem(res);
		dispose();
	}
	public void enviarProClienteExcluirEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setEmail(this.email);
		empresa.setOperacao("apagarEmpresa");
		JSONController deleteController = new JSONController();
		JSONObject res = deleteController.changeToJSONEmpresa(empresa);
		this.cliente.enviarMensagem(res);
		dispose();
	}
	public void abrirPerfil() {
		
		PerfilFrame perfil = new PerfilFrame(this);
		this.perfil = perfil;
		this.perfil.receiveInfo(this.empresa, this.candidato);
		this.perfil.setVisible(true);
	}
	public void deslogar() {
		Candidato candidato = new Candidato();
		candidato.setUUID(token);;
		candidato.setOperacao("logout");
		JSONController logoutController = new JSONController();
		JSONObject res = logoutController.changeToJSON(candidato);
		cliente.enviarMensagem(res);
		dispose();
	}
	public void respostaTela(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public void enviarDadosCliente(Candidato cand, Empresa emp){
		if(emp == null) {
			Candidato candidato = new Candidato();
			candidato.setOperacao("atualizarCandidato");
			candidato.setUser(cand.getUser());
			candidato.setPassword(cand.getPassword());
			candidato.setEmail(this.email);
			
			JSONController showController = new JSONController();
			JSONObject res = showController.changeToJSON(candidato);
			this.cliente.enviarMensagem(res);
			
		}else {
			Empresa empresa = new Empresa();
			empresa.setOperacao("atualizarEmpresa");
			empresa.setCnpj(emp.getCnpj());
			empresa.setDescricao(emp.getDescricao());
			empresa.setRamo(emp.getRamo());
			empresa.setRazaoSocial(emp.getRazaoSocial());
			empresa.setSenha(emp.getSenha());
			
			JSONController showController = new JSONController();
			JSONObject res = showController.changeEmpresaToJSON(empresa);
			this.cliente.enviarMensagem(res);
		}
	}
	public void sendoToPerfil(String msg) {
		this.perfil.respostaTela(msg);
	}

}
