
import java.sql.*;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class TenisMetodos {
	public Tenis teniss;
	public BDT bd;
	private PreparedStatement st;
	private ResultSet rs;
	private JTable table;
	private String men, sql;
	public static final byte inclusao = 1;
	public static final byte alteracao = 2;
	public static final byte exclusao = 3;

	public static final byte inclusao2 = 1;
	public static final byte alteracao2 = 2;
	public static final byte exclusao2 = 3;

	public TenisMetodos() {
		bd = new BDT();
		teniss = new Tenis();
	}

	public void setarcampos() {
		int setar = table.getSelectedRow();
		if(setar >=0){
		teniss.setCodigo(table.getModel().getValueAt(setar, 0).toString());
		teniss.setModelo(table.getModel().getValueAt(setar, 1).toString());
		teniss.setSexo(table.getModel().getValueAt(setar, 3).toString());
		teniss.setTamanho(table.getModel().getValueAt(setar,4).toString());
		teniss.setCor(table.getModel().getValueAt(setar,5).toString());
		teniss.setPreco(table.getModel().getValueAt(setar,6).toString());
		teniss.setImg(table.getModel().getValueAt(setar, 7).toString());
		}else{
			
			JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada","Seleção", JOptionPane.WARNING_MESSAGE);
		}
		
	}

	public int pesquisaPessoa(int a) {
		a = 0;
		sql = "select *from cadastro where rg = ?";
		try {
			st = bd.c.prepareStatement(sql);
			st.setString(1, teniss.getRG());
			rs = st.executeQuery();
			if (rs.next()) {
				teniss.setNome(rs.getString(2));
				teniss.setEndereco(rs.getString(3));
				teniss.setNascimento(rs.getString(4));
				teniss.setCPF(rs.getString(5));
				teniss.setTelefone(rs.getString(6));
				a=1;
				
			} else {
				JOptionPane.showMessageDialog(null, "Pessoa não cadastrada");
				a=2;
			}
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, erro);
		}
		return a;
	}

	public JTable localizar() {
		sql = "select *from tenis where marca = ? order by modelo,tamanho";
		try {
			st = bd.c.prepareStatement(sql);
			st.setString(1, teniss.getMarca());
			rs = st.executeQuery();
			if (rs.next()) {
				rs.previous();
				DefaultTableModel tableModel = new DefaultTableModel(new String[] {}, 0) {
				};
				int qtdeColunas = rs.getMetaData().getColumnCount();

				for (int indice = 1; indice <= qtdeColunas; indice++) {
					tableModel.addColumn(rs.getMetaData().getColumnName(indice));

				}
				table = new JTable(tableModel);
				table.getColumnModel().getColumn(0).setPreferredWidth(40);
				table.getColumnModel().getColumn(1).setPreferredWidth(260);
				table.getColumnModel().getColumn(2).setPreferredWidth(90);
				table.getColumnModel().getColumn(3).setPreferredWidth(40);
				table.getColumnModel().getColumn(4).setPreferredWidth(40);
				table.getColumnModel().getColumn(5).setPreferredWidth(90);
				table.getColumnModel().getColumn(6).setPreferredWidth(130);
				table.getColumnModel().getColumn(7).setPreferredWidth(0);
				table.getColumnModel().getColumn(7).setMaxWidth(0);
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();

				while (rs.next()) {
					try {
						String[] dados = new String[qtdeColunas];
						for (int i = 1; i <= qtdeColunas; i++) {
							dados[i - 1] = rs.getString(i);

						}
						dtm.addRow(dados);

					} catch (SQLException erro) {

					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Marca não encontrada");

			}

		} catch (SQLException erro) {

		}
		return table;
	}

	public String atualizar(int operacao) {
		men = "Operação realizada com sucesso";
		try {
			if (operacao == inclusao) {
				st = bd.c.prepareStatement(
						"insert into cadastro (rg,nome,endereco,nascimento,CPF,telefone) values ( ?,?,?,?,?,?)");
				st.setString(1, teniss.getRG());
				st.setString(2, teniss.getNome());
				st.setString(3, teniss.getEndereco());
				st.setString(4, teniss.getNascimento());
				st.setString(5, teniss.getCPF());
				st.setString(6, teniss.getTelefone());
			} else if (operacao == alteracao) {
				sql = "update cadastro set rg = ?, nome = ?,endereco = ?, nascimento= ?,CPF = ?,telefone = ?  where rg = ?";
				st = bd.c.prepareStatement(sql);
				st.setString(1, teniss.getRG());
				st.setString(2, teniss.getNome());
				st.setString(3, teniss.getEndereco());
				st.setString(4, teniss.getNascimento());
				st.setString(5, teniss.getCPF());
				st.setString(6, teniss.getTelefone());
				st.setString(7, teniss.getRG());
			} else if (operacao == exclusao) {
				sql = "delete from cadastro where rg = ?";
				st = bd.c.prepareStatement(sql);
				st.setString(1, teniss.getRG());
			}
			if (st.executeUpdate() == 0) {
				men = "Falha na operação";
			}
		} catch (SQLException erro) {
			men = "Falha na operação" + erro.toString();
		}
		return men;
	}

	public String atualizar2(int operacao2) {
		men = "Operação realizada com sucesso";
		try {
			if (operacao2 == inclusao2) {
				sql = "insert into tenis values(?,?,?,?,?,?,?,?)";
				st = bd.c.prepareStatement(sql);
				st.setString(1, teniss.getCodigo());
				st.setString(2, teniss.getModelo());
				st.setString(3, teniss.getMarca());
				st.setString(4, teniss.getSexo());
				st.setString(5, teniss.getTamanho());
				st.setString(6, teniss.getCor());
				st.setString(7, teniss.getPreco());
				st.setString(8, teniss.getImg());
			} else if (operacao2 == alteracao2) {
				sql = "update tenis set codigo = ?, modelo = ?,marca = ?, sexo= ?,tamanho = ?,cor = ?, preco = ?,img= ?  where codigo = ?";
				st = bd.c.prepareStatement(sql);
				st.setString(1, teniss.getCodigo());
				st.setString(2, teniss.getModelo());
				st.setString(3, teniss.getMarca());
				st.setString(4, teniss.getSexo());
				st.setString(5, teniss.getTamanho());
				st.setString(6, teniss.getCor());
				st.setString(7, teniss.getPreco());
				st.setString(8, teniss.getImg());
				st.setString(9, teniss.getCodigo());
			} else if (operacao2 == exclusao2) {
				sql = "delete from tenis where codigo = ?";
				st = bd.c.prepareStatement(sql);
				st.setString(1, teniss.getCodigo());
			}
			if (st.executeUpdate() == 0) {
				men = "Falha na operação";
			}
		} catch (SQLException erro) {
			men = "Falha na operação" + erro.toString();
		}
		return men;
	}

}