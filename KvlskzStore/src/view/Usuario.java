package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Usuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtUsuario;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private JButton btnAdicionar_1;
	private JButton btnExcluir_1;
	private JButton btnPesquisar_1;
	private JButton btnEditar_1;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario frame = new Usuario();
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
	public Usuario() {
		setTitle("Cadastrar Usu\u00E1rio ");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuario.class.getResource("/img/icone.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(57, 317, 610, 33);
		contentPane.add(buttonPane);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(263, 56, 61, 20);
		contentPane.add(txtId);
		
		lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(197, 59, 31, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNome = new JLabel("* Usu\u00E1rio");
		lblNome.setBounds(187, 93, 64, 14);
		contentPane.add(lblNome);
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(261, 87, 226, 20);
		contentPane.add(txtUsuario);
		
		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		txtLogin.setBounds(261, 118, 226, 20);
		contentPane.add(txtLogin);
		
		JLabel lblUsurio = new JLabel("* Login");
		lblUsurio.setBounds(187, 124, 46, 14);
		contentPane.add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(187, 155, 46, 14);
		contentPane.add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(263, 152, 224, 20);
		contentPane.add(txtSenha);
		
		btnAdicionar_1 = new JButton("");
		btnAdicionar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarUsuario();
			}});
		btnAdicionar_1.setIcon(new ImageIcon(Usuario.class.getResource("/img/add.png")));
		btnAdicionar_1.setToolTipText("Adicionar usu\u00E1rio");
		btnAdicionar_1.setEnabled(false);
		btnAdicionar_1.setBounds(155, 191, 87, 80);
		contentPane.add(btnAdicionar_1);
		
		btnPesquisar_1 = new JButton("");
		btnPesquisar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarUsuario();
			}
		});
		btnPesquisar_1.setIcon(new ImageIcon(Usuario.class.getResource("/img/LupaColorida.png")));
		btnPesquisar_1.setToolTipText("Pesquisar usu\u00E1rio");
		btnPesquisar_1.setBounds(258, 191, 80, 80);
		contentPane.add(btnPesquisar_1);
		
		btnEditar_1 = new JButton("");
		btnEditar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarUsuario();
			}
		});
		btnEditar_1.setIcon(new ImageIcon(Usuario.class.getResource("/img/edit.png")));
		btnEditar_1.setToolTipText("Editar usu\u00E1rio");
		btnEditar_1.setEnabled(false);
		btnEditar_1.setBounds(347, 191, 80, 80);
		contentPane.add(btnEditar_1);
		
		btnExcluir_1 = new JButton("");
		btnExcluir_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		btnExcluir_1.setIcon(new ImageIcon(Usuario.class.getResource("/img/delete.png")));
		btnExcluir_1.setToolTipText("Excluir usu\u00E1rio");
		btnExcluir_1.setEnabled(false);
		btnExcluir_1.setBounds(442, 191, 80, 80);
		contentPane.add(btnExcluir_1);
		
		JLabel lblNewLabel_1 = new JLabel("*  Campos Obrigat\u00F3rios");
		lblNewLabel_1.setBounds(187, 11, 137, 14);
		contentPane.add(lblNewLabel_1);
	}
	
	DAO dao = new DAO();

	// pesquisar usuario (CRUD READ) Esse metodo sempre começa com a validaçao dos
	// campos e ele entra na logica principal
	private void pesquisarUsuario() {
		// validacao
		// se o campo txtId estiver vazio
		if (txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o ID");
			txtId.requestFocus();
		} else {
			// logica principal Instrução sql para pesquisar um usuário. esse comando é dado
			// no Mysql para essa integração de verificar
			// pelo id se existe um usuário cadastrado com esse Id
			String read = "select * from funcionario where idfuncionario=?";
			// tratamento de excecoes(ex: servidor indisponivel o try catch ele avisa e
			// mostra onde está dando erro )
			try {
				// estabelecer uma conexao atraves da classe DAO que é responsável pela ligação
				// do sql abrindo a conexão
				Connection con = dao.conectar();
				// preparar a instrução sql PreparedStatement vai substituir (?) pelo conteúdo
				// cadastrado no sql
				PreparedStatement pst = con.prepareStatement(read);
				// substituir parametros (?)
				pst.setString(1, txtId.getText());
				// resultado (executar a query que é a instrução do banco de dados mysql para
				// obter os dados)
				ResultSet rs = pst.executeQuery();
				// Criando condições se existir este usuario no banco execute
				if (rs.next()) {
					// setar campos do usuário se existir usuário cadastrado observe no sql os nomes
					// das colunas
					// essas numerações equivale ao sql a ordem da coluna já que o id é da pesquisa
					// ele não se aplica aqui por ser 1
					txtUsuario.setText(rs.getString(2));
					txtLogin.setText(rs.getString(3));
					txtSenha.setText(rs.getString(4));
					// gerenciar botoes para acesso as determinadas modificações(UX)
					btnPesquisar_1.setEnabled(false);
					btnEditar_1.setEnabled(true);
					btnExcluir_1.setEnabled(true);
					// desativar o txtId quando gera um usuário cadastrado
					txtId.setEnabled(false);

					// senão existir usuário cadastrado mostre a seguinte mensagem
				} else {
					JOptionPane.showMessageDialog(null, "Usuário inexistente");
					// limpar o campo ID
					txtId.setText(null);
					// gerenciar os botoes
					btnAdicionar_1.setEnabled(true);
					btnPesquisar_1.setEnabled(false);
					btnEditar_1.setEnabled(false);
					btnExcluir_1.setEnabled(false);
					// desabilitar o txtId e posicionar o cursor no usuario
					txtId.setEnabled(false);
					txtUsuario.requestFocus();
				}
			} catch (Exception e) {
				// caso ocorra algum problema, mostre na tela o erro que está relacionado na
				// infraestrutura
				System.out.println(e);
			}
		}
	}

	// adicionar usuario (CRUD Create) Criação de usuário pela janela interativa
	// Java
	private void adicionarUsuario() {
		// validacao dos campos obrigatorios
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Usuário");
			txtUsuario.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login");
			txtLogin.requestFocus();
		} else {
			// instrucao sql para inserir um usuario o mesmo comando que damos no sql para
			// criar um usuário
			String create = "insert into funcionario(nomef,usuariof,senhaf) values (?,?,md5(?))";
			// Bloco aula 10 linha copiada no bloco de cima
			try {
				// estabelecer uma conexao atraves da classe DAO que é responsável pela ligação
				// do sql abrindo a conexão
				Connection con = dao.conectar();
				// preparar a instrução sql PreparedStatement vai substituir (?) pelo conteúdo
				// cadastrado no sql cada
				PreparedStatement pst = con.prepareStatement(create);
				// substituir parametros setamos o 2 de acordo com a posição de cadastro de
				// usuário no sql (?)
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtSenha.getText());
				// exibir uma caixa de mensagem mostrando que foi feito o cadastro com sucesso.
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Usuário adicionado com Sucesso");
				}
				// criando acao limpar caixa de texto quando os dados forem cadastrado
				limpar();
				con.close();
				// a linha abaixo trata o problema do campo unique no login, devolvendo uma mensagem amigavel ao usuario se o login ja existir
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login já existente\nCadastre outro login");
				txtLogin.setText(null);
				txtLogin.requestFocus();
				}
				
				
			
			catch (Exception e) {
				System.out.println(e);
			}
		}

	}
	// FIM DO BLOCO AULA 10

	// Editar usuario (CRUD Update) Criação de usuário pela janela interativa Java
	private void editarUsuario() {
		// validacao dos campos obrigatorios
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Usuário");
			txtUsuario.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login");
			txtLogin.requestFocus();
		} else {
			// instrucao sql para Editar usuario o mesmo comando que damos no sql para
			// Editar um usuário
			String update = "update funcionario set nomef=?,usuariof=?,senhaf=md5(?) where idfuncionario=?";
			try {
				// estabelecer uma conexao atraves da classe DAO que é responsável pela ligação
				// do sql abrindo a conexão
				Connection con = dao.conectar();
				// preparar a instrução sql PreparedStatement vai substituir (?) pelo conteúdo
				// cadastrado no sql cada
				PreparedStatement pst = con.prepareStatement(update);
				// substituir parametros setamos o 2 de acordo com a posição de cadastro de
				// usuário no sql (?)
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtSenha.getText());
				pst.setString(4, txtId.getText());
				// exibir uma caixa de mensagem mostrando que o usuário foi editado com sucesso.
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Dados do usuário alterado com Sucesso");
				}
				// criando acao limpar caixa de texto quando os dados forem cadastrado
				limpar();
				con.close();
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	// Exluir usuario (CRUD Delete) excluir um usuário pela janela interativa Java
	private void excluirUsuario() {
		// validacao dos campos obrigatorios
		//confirmar a exclusao do usuario
		int confirma = JOptionPane.showConfirmDialog(null, "Deseja realmente Excluir o usuário?", "Atenção!", JOptionPane.YES_NO_OPTION);
		if(confirma ==JOptionPane.YES_OPTION) {
			// instrucao sql para deletar usuario o mesmo comando que damos no sql para
			// Editar um usuário
			String delete = "delete from funcionario where idfuncionario=?";
			try {
				// estabelecer uma conexao atraves da classe DAO que é responsável pela ligação
				// do sql abrindo a conexão
				Connection con = dao.conectar();
				// preparar a instrução sql PreparedStatement vai substituir (?) pelo conteúdo
				// cadastrado no sql cada
				PreparedStatement pst = con.prepareStatement(delete);
				// substituir parametros setamos o 2 de acordo com a posição de cadastro de
				// usuário no sql (?)
				pst.setString(1, txtId.getText());
				// exibir uma caixa de mensagem mostrando que o usuário foi excluido  com sucesso.
				int verifica = pst.executeUpdate();
				if (verifica == 1) {
					JOptionPane.showMessageDialog(null, "Dados do Usuário Deletado com Sucesso");
				}
				// criando acao limpar caixa de texto quando os dados forem cadastrado
				limpar();
				con.close();
				
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	// Anotação Aula 10 Limpar campos e gerenciar os botoes
	// assim limpamos a caixa de texto apos criar um usuário para não duplicar o
	// cadastro
	private void limpar() {
		// habilitar a pesquisa por id
		txtId.setEnabled(true);
		// posicionar o cursor na caixa ID
		txtId.requestFocus();
		// Limpar as caixas de texto
		txtId.setText(null);
		txtUsuario.setText(null);
		txtLogin.setText(null);
		txtSenha.setText(null);
		// ativar o botao de pesquisa
		btnPesquisar_1.setEnabled(true);
		// desativar os demais botoes
		btnAdicionar_1.setEnabled(false);
		btnEditar_1.setEnabled(false);
		btnExcluir_1.setEnabled(false);
	}


}
