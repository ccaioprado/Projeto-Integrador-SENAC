package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/icone.png")));
		setTitle("Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/img/Logo2.png")));
		lblNewLabel.setBounds(306, 61, 84, 57);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Login ");
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 35));
		lblNewLabel_1.setBounds(306, 129, 74, 38);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(185, 194, 46, 18);
		contentPane.add(lblNewLabel_2);

		textLogin = new JTextField();
		textLogin.setBounds(241, 192, 185, 20);
		contentPane.add(textLogin);
		textLogin.setColumns(10);

		JLabel lblNewLabel_2_1 = new JLabel("Senha");
		lblNewLabel_2_1.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(185, 243, 46, 14);
		contentPane.add(lblNewLabel_2_1);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnEntrar.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 21));
		btnEntrar.setBounds(291, 308, 89, 23);
		contentPane.add(btnEntrar);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(241, 241, 185, 20);
		contentPane.add(txtSenha);
	}// fim do construtor
		// Criar um objeto para conectar com o banco de dados

	DAO dao = new DAO();
	private JPasswordField txtSenha;

// Status da conexao
	private void status() {
		try {
			// estabelecer a conexao com o banco
			Connection con = dao.conectar();
			// status da conexao
			if (con == null) {
				// imprimir a mensagem no console
				System.out.println("Erro de conexão");
			} else {
				// imprimir a mensagem no console
				System.out.println("Conexão estabelecida com sucesso");
			}
			// encerrar a conexao
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

// login no sistema
	private void logar() {
// validacao de campos obrigatorios
		if (textLogin.getText().isEmpty()) {
// caixa de mensagem
			JOptionPane.showMessageDialog(null, "Preencha o nome do usuário");
// posicionar o cursor na caixa de texto do usuario
			textLogin.requestFocus();
		} else {
// logica principal do login
			try {
// query (comando sql)
				String read = "select * from funcionario where usuariof=? and senhaf=md5(?)";
// abrir a conexao
				Connection con = dao.conectar();
// Uso do PrepareStatement(JDBC) para substituir as ? pelo conteudo das caixas
// de texto
				PreparedStatement pst = con.prepareStatement(read);
// substituir as ? pelo conteudo das caixas de texto
				pst.setString(1, textLogin.getText());
				pst.setString(2, txtSenha.getText());
//Uso do ResutSet para obter os dados do banco
				ResultSet rs = pst.executeQuery();
//se existir usuario cadastrado com login e senha
				if (rs.next()) {
//ativar a tela principal
					Principal principal = new Principal();
					principal.setVisible(true);
					this.dispose(); // fechar a tela de login
				} else {
					JOptionPane.showMessageDialog(null, "login e/ou senha inválido(s)");
				}
				con.close(); // encerrar a conexao
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
