package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/icone.png")));
		setTitle("Principal");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 744, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = new Cliente();
				cliente.setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(Principal.class.getResource("/img/Pessoas2.png")));
		btnNewButton.setBounds(83, 103, 133, 124);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Estoque estoque = new Estoque();
				estoque.setVisible(true);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Principal.class.getResource("/img/Lupa.png")));
		btnNewButton_1.setBounds(514, 103, 133, 124);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Clientes");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 21));
		lblNewLabel.setBounds(119, 256, 66, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblEstoque = new JLabel("Estoque");
		lblEstoque.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 21));
		lblEstoque.setBounds(559, 251, 66, 24);
		contentPane.add(lblEstoque);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = new Usuario();
				usuario.setVisible(true);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(Principal.class.getResource("/img/UmaPessoa.png")));
		btnNewButton_2.setBounds(301, 103, 133, 124);
		contentPane.add(btnNewButton_2);
		
		JLabel lblUsurio = new JLabel("Funcion\u00E1rios");
		lblUsurio.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 21));
		lblUsurio.setBounds(328, 256, 93, 14);
		contentPane.add(lblUsurio);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon(Principal.class.getResource("/img/Interroga\u00E7\u00E3o3.png")));
		lblNewLabel_1.setBounds(639, 330, 66, 68);
		contentPane.add(lblNewLabel_1);
	}
}
