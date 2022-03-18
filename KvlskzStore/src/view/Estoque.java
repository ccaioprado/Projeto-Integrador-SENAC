package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;
import net.proteanit.sql.DbUtils;

import java.awt.Toolkit;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;


public class Estoque extends JFrame {

	private JPanel contentPane;
	private JTextField txtProduto;
	private DAO dao;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Estoque frame = new Estoque();
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
	public Estoque() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Estoque.class.getResource("/img/icone.png")));
		setTitle("Estoque");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(47, 115, 532, 161);
		contentPane.add(desktopPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 532, 160);
		desktopPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		txtProduto = new JTextField();
		txtProduto.setColumns(10);
		txtProduto.setBounds(47, 23, 197, 20);
		contentPane.add(txtProduto);
		
		JButton btnProduto = new JButton("");
		btnProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarProduto();
			}
		});
		btnProduto.setIcon(new ImageIcon(Estoque.class.getResource("/img/Lupa24.png")));
		btnProduto.setBounds(260, 11, 32, 32);
		contentPane.add(btnProduto);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Estoque.class.getResource("/img/estoque.png")));
		lblNewLabel.setBounds(270, 277, 148, 116);
		contentPane.add(lblNewLabel);
	}
	private void pesquisarProduto() {

		String read = "select * from estoque where produto like ? order by produto";
		try {
		dao = new DAO();
		//abrir a conexao com o banco
		Connection con = dao.conectar();
		//preparar a query(instrucao sql) para pesquisar no banco
		PreparedStatement pst = con.prepareStatement(read);
		//substituir o parametro(?) Atencao ao % para completar a query
		pst.setString(1, txtProduto.getText() + "%");
		//obter os dados do banco (resultado)
		ResultSet rs = pst.executeQuery();
		//popular(preencher) a tabela com os dados do banco
		table.setModel(DbUtils.resultSetToTableModel(rs));
		
	} catch (Exception e) {
		System.out.println(e);
		}
	}
}
