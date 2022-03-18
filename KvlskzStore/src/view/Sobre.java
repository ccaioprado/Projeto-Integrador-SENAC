package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Sobre extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		setModal(true);
		setResizable(false);
		setTitle("InfoX-Sobre");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/Logo.png")));
		setBounds(100, 100, 508, 323);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Kvlskz Store - Aplicativo para cadastro de clientes e usu\u00E1rios");
		lblNewLabel.setBounds(53, 61, 349, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Autor - Caio Ryan");
		lblNewLabel_1.setBounds(53, 86, 349, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Sobre.class.getResource("/img/Escudo.png")));
		lblNewLabel_2.setBounds(384, 125, 64, 64);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Vers\u00E3o 1.0");
		lblNewLabel_1_1.setBounds(53, 198, 64, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Vers\u00E3o 1.0");
		lblNewLabel_1_1_1.setBounds(-94, 240, 349, 14);
		getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Sob a licen\u00E7a GPL");
		lblNewLabel_1_1_2.setBounds(53, 180, 103, 14);
		getContentPane().add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("        www.kvlskzstore.com.br");
		lblNewLabel_1_1_3.setBounds(169, 270, 198, 14);
		getContentPane().add(lblNewLabel_1_1_3);

	}
}
