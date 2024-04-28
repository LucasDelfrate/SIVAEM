package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Cliente;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class HomeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeFrame frame = new HomeFrame(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomeFrame(Cliente cliente) {
		this.cliente = cliente;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 685);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(233, 233, 233));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamarRegistroCandidato();
			}
		});
		btnRegistrar.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnRegistrar.setBounds(495, 386, 210, 46);
		contentPane.add(btnRegistrar);
		
		JButton btnLoginCandidato = new JButton("Login");
		btnLoginCandidato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamarLoginCandidato();
			}
		});
		btnLoginCandidato.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnLoginCandidato.setBounds(495, 324, 210, 46);
		contentPane.add(btnLoginCandidato);
		
		JLabel lblCandidato = new JLabel("Candidato");
		lblCandidato.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblCandidato.setHorizontalAlignment(SwingConstants.CENTER);
		lblCandidato.setFont(new Font("Consolas", Font.PLAIN, 23));
		lblCandidato.setBounds(495, 250, 210, 46);
		contentPane.add(lblCandidato);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 121, 400, 525);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnNewButton_1.setBounds(98, 203, 210, 46);
		panel.add(btnNewButton_1);
		
		JLabel lblEmpresa_1 = new JLabel("Empresa");
		lblEmpresa_1.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		lblEmpresa_1.setForeground(new Color(255, 255, 255));
		lblEmpresa_1.setBackground(new Color(221, 221, 221));
		lblEmpresa_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpresa_1.setFont(new Font("Consolas", Font.PLAIN, 23));
		lblEmpresa_1.setBounds(98, 130, 210, 46);
		panel.add(lblEmpresa_1);
		
		JButton btnRegistrarEmpresa_1 = new JButton("Registrar");
		btnRegistrarEmpresa_1.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnRegistrarEmpresa_1.setBounds(98, 265, 210, 46);
		panel.add(btnRegistrarEmpresa_1);
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(-40, -113, 635, 707);
		panel.add(lblNewLabel_1);
		
		
		Image img = new ImageIcon(this.getClass().getResource("/home_backgroung_medio.png")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(img));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(0, 0, 792, 122);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SIVAEM");
		lblNewLabel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "teste", TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(243, 243, 243));
		lblNewLabel.setFont(new Font("Corbel", Font.PLAIN, 40));
		lblNewLabel.setBounds(314, 32, 163, 57);
		panel_1.add(lblNewLabel);
		
		JLabel fundoTextura = new JLabel("New label");
		fundoTextura.setBounds(397, 123, 405, 523);
		contentPane.add(fundoTextura);
		Image textura = new ImageIcon(this.getClass().getResource("/novo-fundo.jpg")).getImage();
		fundoTextura.setIcon(new ImageIcon(textura));
		
	
	}
	private void chamarLoginCandidato() {
		LoginCandidatoFrame login = new LoginCandidatoFrame(this, this.cliente);
		login.setVisible(true);
	}
	private void chamarRegistroCandidato(){
		RegistroCandidatoFrame regFrame = new RegistroCandidatoFrame(this, this.cliente);
		regFrame.setVisible(true);
	}
	public void respostaTela(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
}
