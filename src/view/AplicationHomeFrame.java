package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
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

public class AplicationHomeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String token;
	private Candidato candidato;
	private Empresa empresa;
	private Cliente cliente;
	

	public AplicationHomeFrame(Cliente cliente, String token) {
		this.cliente = cliente;
		this.token = token;
		getByToken();
		setBounds(100, 100, 668, 650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0, 0, 0));
		menuBar.setBounds(0, 0, 918, 40);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		mnNewMenu.setPreferredSize(new Dimension(100, 26));
		mnNewMenu.setOpaque(true);
		mnNewMenu.setBackground(new Color(255, 255, 255));
		mnNewMenu.setBorder(new LineBorder(new Color(0, 0, 0)));
		mnNewMenu.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu.setForeground(new Color(0, 0, 0));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Perfil");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirPerfil();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		Label textBemVindo = new Label("Not Found");
		textBemVindo.setFont(new Font("Fira Code Medium", Font.PLAIN, 30));
		textBemVindo.setAlignment(Label.CENTER);
		textBemVindo.setBounds(127, 115, 398, 22);
		if(this.candidato == null) {
			//textBemVindo.setText(empresa);
		}else {
			textBemVindo.setText("Bem vindo "+this.candidato.getUser());			
		}
		contentPane.add(textBemVindo);
		
		Label textBemVindo_1 = new Label("SIVAEM");
		textBemVindo_1.setFont(new Font("Fira Code Medium", Font.PLAIN, 20));
		textBemVindo_1.setAlignment(Label.CENTER);
		textBemVindo_1.setBounds(127, 58, 398, 22);
		contentPane.add(textBemVindo_1);
	}
	
	public void getByToken(){
		AplicationController appControll = new AplicationController();
		try {
			this.candidato = appControll.getCandidatoByToken(this.token);
			if(this.candidato == null) {
				this.empresa = appControll.getEmpresaByToken(this.token);
				if(empresa == null) {
					System.out.println("ID não encontrado");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void abrirPerfil() {
		PerfilFrame perfil = new PerfilFrame(this, this.candidato, this.empresa);
		perfil.setVisible(true);
	}
	public void enviarProClienteExcluir() {
		Candidato candidato = new Candidato();
		candidato.setEmail(this.candidato.getEmail());
		candidato.setOperacao("apagarCandidato");
		JSONController deleteController = new JSONController();
		JSONObject res = deleteController.changeToJSON(candidato);
		cliente.enviarMensagem(res);
		dispose();
	}
}
