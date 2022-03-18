package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtPesquisar_2;
	private JTable tableCliente_2;
	private JTextField txtIdCli;
	private JTextField txtCliente;
	private JTextField txtFoneCli;
	private JButton btnEditarCliente;
	private JButton btnExcluirCliente;
	private JButton btnAdicionarCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente frame = new Cliente();
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
	public Cliente() {
		setTitle("Clientes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cliente.class.getResource("/img/Logo.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtPesquisar_2 = new JTextField();
		txtPesquisar_2.setBounds(34, 33, 185, 20);
		contentPane.add(txtPesquisar_2);
		txtPesquisar_2.setColumns(10);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarCliente();
			}
		});
		btnNewButton.setIcon(new ImageIcon(Cliente.class.getResource("/img/Lupa24.png")));
		btnNewButton.setBounds(229, 22, 44, 41);
		contentPane.add(btnNewButton);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(34, 96, 532, 108);
		contentPane.add(desktopPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setViewportView(tableCliente_2);
		scrollPane.setBounds(0, 0, 532, 108);
		desktopPane.add(scrollPane);
		
		tableCliente_2 = new JTable();
		tableCliente_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
			}
		});
		scrollPane.setViewportView(tableCliente_2);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(34, 239, 46, 14);
		contentPane.add(lblNewLabel);
		
		txtIdCli = new JTextField();
		txtIdCli.setBounds(90, 236, 86, 20);
		contentPane.add(txtIdCli);
		txtIdCli.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(34, 279, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		txtCliente = new JTextField();
		txtCliente.setBounds(90, 276, 164, 20);
		contentPane.add(txtCliente);
		txtCliente.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Telefone");
		lblNewLabel_1_1.setBounds(336, 279, 62, 14);
		contentPane.add(lblNewLabel_1_1);
		
		txtFoneCli = new JTextField();
		txtFoneCli.setColumns(10);
		txtFoneCli.setBounds(408, 276, 164, 20);
		contentPane.add(txtFoneCli);
		
		btnAdicionarCliente = new JButton("");
		btnAdicionarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarCliente();
			}
		});
		btnAdicionarCliente.setIcon(new ImageIcon(Cliente.class.getResource("/img/add.png")));
		btnAdicionarCliente.setBounds(87, 317, 89, 73);
		contentPane.add(btnAdicionarCliente);
		
		btnExcluirCliente = new JButton("");
		btnExcluirCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
			}
		});
		btnExcluirCliente.setIcon(new ImageIcon(Cliente.class.getResource("/img/delete.png")));
		btnExcluirCliente.setBounds(281, 317, 89, 73);
		contentPane.add(btnExcluirCliente);
		
		
		btnEditarCliente = new JButton("");
		btnEditarCliente.setIcon(new ImageIcon(Cliente.class.getResource("/img/edit.png")));
		btnEditarCliente.setBounds(483, 317, 89, 73);
		contentPane.add(btnEditarCliente);

		
	}
	// fim do construtor importe a classe DAO crtl + shift + o


		// criar objeto para reutilizar a classe DAO(Conexao com o banco)
		DAO dao = new DAO();
		private JTextField txtPesquisar;
		private JTable tableCliente;
		//metodo para inserir um novo cliente
		private void adicionarCliente() {
		//validacao dos campos obrigatorios
		if (txtCliente.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Preencha o nome do cliente ");
		txtCliente.requestFocus();
		} else if (txtFoneCli.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Preencha o fone do cliente ");
		txtFoneCli.requestFocus();
		} else {
		// logica principal
		// String (query) SQL
		String create = "insert into cliente (nomec,telefonec) values(?,?)";
		try {
		//abrir a conexao com o banco
		Connection con = dao.conectar();
		//preparar a conexao com a instrucao sql(query)
		PreparedStatement pst = con.prepareStatement(create);
		//substituir os parametros(?,?) pelo conteudo das caixas texto
		pst.setString(1, txtCliente.getText());
		pst.setString(2, txtFoneCli.getText());
		//executar a query confirmando a inclusao do cliente
		int confirma = pst.executeUpdate();
		if (confirma == 1) {
		JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso.");
		}
		//encerrar a conexao
		con.close();
		//limpar os campos
		limpar();
		
		
		
		} catch (Exception e) {
		System.out.println(e);
		}
		}
		}
		
		// Pesquisa avançada de clientes usando abiblioteca rs2xml
		
		private void pesquisarCliente() {

			String read = "select * from cliente where nomec like ? order by nomec";
			try {
			//abrir a conexao com o banco
			Connection con = dao.conectar();
			//preparar a query(instrucao sql) para pesquisar no banco
			PreparedStatement pst = con.prepareStatement(read);
			//substituir o parametro(?) Atencao ao % para completar a query
			pst.setString(1, txtPesquisar_2.getText() + "%");
			//obter os dados do banco (resultado)
			ResultSet rs = pst.executeQuery();
			//popular(preencher) a tabela com os dados do banco
			tableCliente_2.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception e) {
			System.out.println(e);
			}
		}
		// setar os campos do formulario com o conteudo da tabela assim que colocar o cursor do mouse sobre o id ele vai preencher os campos
		private void setarCampos() {
		int setar = tableCliente_2.getSelectedRow();
		//(setar, 0) 0 -> 1º campo da tabela | 1 -> 2º campo da tabela ...
		txtIdCli.setText(tableCliente_2.getModel().getValueAt(setar, 0).toString());
		txtCliente.setText(tableCliente_2.getModel().getValueAt(setar, 1).toString());
		txtFoneCli.setText(tableCliente_2.getModel().getValueAt(setar, 2).toString());
		JButton btnEditarCcliente = new JButton();
		btnEditarCliente.setEnabled(true);
		btnExcluirCliente.setEnabled(true);
		btnAdicionarCliente.setEnabled(false);
		}

		// EDITAR CLIENTE (CRUD Update) EDITAR CLIENTE CADASTRADO
			private void editarCliente() {
				
				//confirmar a exclusao do usuario
				int editar = JOptionPane.showConfirmDialog(null, "Deseja realmente Editar o usuário?", "Atenção!", JOptionPane.YES_NO_OPTION);
				if(editar ==JOptionPane.YES_OPTION)
				// validacao dos campos obrigatorios
				if (txtCliente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o nome do Cliente");
					txtCliente.requestFocus();
				} else if (txtFoneCli.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o Login");
					txtFoneCli.requestFocus();
				} else {
					// instrucao sql para Editar usuario o mesmo comando que damos no sql para
					// Editar um usuário
					String update = "update cliente set nomec=?,telefonec=? where idcliente=?";
					try {
						// estabelecer uma conexao atraves da classe DAO que é responsável pela ligação
						// do sql abrindo a conexão
						Connection con = dao.conectar();
						// preparar a instrução sql PreparedStatement vai substituir (?) pelo conteúdo
						// cadastrado no sql cada
						PreparedStatement pst = con.prepareStatement(update);
						// substituir parametros setamos o 2 de acordo com a posição de cadastro de
						// usuário no sql (?)
						
						pst.setString(1, txtCliente.getText());
						pst.setString(2, txtFoneCli.getText());
						pst.setString(3, txtIdCli.getText());
						
						// exibir uma caixa de mensagem mostrando que o usuário foi editado com sucesso.
						int confirma = pst.executeUpdate();
						if (confirma == 1) {
							JOptionPane.showMessageDialog(null, "Dados de Cliente alterado com Sucesso");
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
			private void excluirCliente() {
				// validacao dos campos obrigatorios
				//confirmar a exclusao do usuario
				int confirma = JOptionPane.showConfirmDialog(null, "Deseja realmente Excluir o usuário?", "Atenção!", JOptionPane.YES_NO_OPTION);
				if(confirma ==JOptionPane.YES_OPTION) {
					// instrucao sql para deletar usuario o mesmo comando que damos no sql para
					// Editar um usuário
					String delete = "delete from cliente where idcliente=?";
					try {
						// estabelecer uma conexao atraves da classe DAO que é responsável pela ligação
						// do sql abrindo a conexão
						Connection con = dao.conectar();
						// preparar a instrução sql PreparedStatement vai substituir (?) pelo conteúdo
						// cadastrado no sql cada
						PreparedStatement pst = con.prepareStatement(delete);
						// substituir parametros setamos o 2 de acordo com a posição de cadastro de
						// usuário no sql (?)
						pst.setString(1, txtIdCli.getText());
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
		
		
		
		//limpar os campos e gerenciar os botoes
		private void limpar() {
		txtCliente.setText(null);
		txtFoneCli.setText(null);
		btnEditarCliente.setEnabled(false);
		btnExcluirCliente.setEnabled(false);
		btnAdicionarCliente.setEnabled(true);
		}
	}


