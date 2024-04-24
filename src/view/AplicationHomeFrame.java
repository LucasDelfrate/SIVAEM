package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.Label;
import javax.swing.border.LineBorder;
import java.awt.Dimension;
import java.awt.Font;

public class AplicationHomeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AplicationHomeFrame frame = new AplicationHomeFrame();
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
	public AplicationHomeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 934, 332);
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
				PerfilFrame perfil = new PerfilFrame();
				perfil.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		Label textBemVindo = new Label("text");
		textBemVindo.setFont(new Font("Fira Code Medium", Font.PLAIN, 30));
		textBemVindo.setAlignment(Label.CENTER);
		textBemVindo.setBounds(258, 98, 398, 22);
		contentPane.add(textBemVindo);
		
		Label textBemVindo_1 = new Label("SIVAEM");
		textBemVindo_1.setFont(new Font("Fira Code Medium", Font.PLAIN, 20));
		textBemVindo_1.setAlignment(Label.CENTER);
		textBemVindo_1.setBounds(260, 57, 398, 22);
		contentPane.add(textBemVindo_1);
	}
}
