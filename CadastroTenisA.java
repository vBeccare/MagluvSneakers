//interface gráfica 
import java.sql.*;
import java.text.ParseException;
import java.util.HashMap;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.MaskFormatter;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class CadastroTenisA extends JFrame{
	
	//PRIMEIRA JANELA
	private JButton btcadastro, btcompra,bttutorial;
	private JLabel logo,fundo,fundo2;
	ImageIcon imlogo,imcadastro,imcompra,imtutorial;

	//SEGUNDA JANELA (CADASTRO)
	private JLabel lbrg,lbnome,lbende,lbnasci,lbcpf,lbtell,cadastro;
	private JFormattedTextField tfrg,tfnasci,tfcpf,tftell;
	private JTextField tfnome,tfende;
	private MaskFormatter msrg, mscpf,msTel,msnasci;
	private JButton cadastrar,voltar;
	
	//QUARTA PAGINA
	private JLabel imgtutorial;
	ImageIcon telainicial,telacadastro,telacompra,prox,ant,voltartt;
	private JButton avancar,anterior,voltart;
	int cont = 0;
	//TERCEIRA PAGINA 
	
	//QUADRADO 1
	private JLabel rg1,nome1,end1,nasci1,cpf1,tel1;
	private JFormattedTextField tfrg1,tfnome1,tfend1,tfnasci1,tfcpf1,tftel1;
	private JButton pesquisar1,alterar1,excluir1;
	ImageIcon pesquisar,excluir,alterar;
	
	//QUADRADO 2
	private JLabel marca,codigo2,modelo2,marca2,sexo2,tamanho2,cor2,preco2,imgtenis,imtenis2;
	private JTextField tfmarca,tfcodigo2,tfmodelo2,tfsexo2,tftamanho2,tfcor2,tfpreco2,tfimg,tfimtenis2;
	private JButton pesquisar2,novo2,alterar2,excluir2,bttable,notafiscal;
	ImageIcon novo,ok,nota;
	//TENIS
	
	ImageIcon yezzyt,vaporff,airmaxracer,azul720,preto720,vans,airjordan,vaporbranco,jogger;
	
	
	private BDT bd;
	private PreparedStatement st;
	 Connection cc =null;
	private ResultSet rs;
	private TenisMetodos tenis;
	private JScrollPane scrollTable;
	private JPanel quadrado1,quadrado2;
	
	
	
	public static void main(String args[]) {
		JFrame frame = new CadastroTenisA();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public CadastroTenisA(){
		inicializarComponentes();
		definirEventos();
		cc= BDTT.conector();
	}
	
	public void inicializarComponentes(){
		//QUARTA PAGINA
		 prox = new ImageIcon("C:\\Users\\marco\\Desktop\\imagens\\avançar.png");
		ant = new ImageIcon("C:\\Users\\marco\\Desktop\\imagens\\anterior.png");
		telainicial  = new ImageIcon("C:\\Users\\marco\\Desktop\\imagens\\telainicial.png");
		telacadastro  = new ImageIcon("C:\\Users\\marco\\Desktop\\imagens\\telacadastro.png");
		telacompra  = new ImageIcon("C:\\Users\\marco\\Desktop\\imagens\\telacompra.png");
		
		imgtutorial= new JLabel(telainicial);
		imgtutorial.setBounds(190, 60, 600,500);
		imgtutorial.setVisible(false);
		add(imgtutorial);
		
		avancar= new JButton(prox);
		avancar.setBounds(820, 320, 80, 80);
		avancar.setBackground(Color.WHITE);
		avancar.setBorder(null);
		avancar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		avancar.setVisible(false);
		add(avancar);
		
		anterior= new JButton(ant);
		anterior.setBounds(50, 320, 80, 80);
		anterior.setBackground(null);
		anterior.setBorder(null);
		anterior.setCursor(new Cursor(Cursor.HAND_CURSOR));
		anterior.setVisible(false);
		add(anterior);
		
		voltartt = new ImageIcon("C:\\Users\\marco\\Desktop\\imagens\\voltart.png");
		voltart = new JButton(voltartt);
		voltart.setBounds(20, 20, 48,48);
		voltart.setCursor(new Cursor(Cursor.HAND_CURSOR));
		voltart.setVisible(false);
		voltart.setBorder(null);
		voltart.setBackground(Color.white);
		add(voltart);
		
		//QUADRADO 1
		try {
			msrg = new MaskFormatter("##.###.###-#");
			mscpf = new MaskFormatter("#########/##");
			msTel = new MaskFormatter("(##)*####-####");
			msnasci = new MaskFormatter("##/##/####");
			
			
			yezzyt = new ImageIcon("C:\\Users\\marco\\Desktop\\imagens\\yezzystatic.jpg");
			vaporff = new ImageIcon("C:\\Users\\marco\\Desktop\\imagens\\vaporoff.png");
			airmaxracer = new ImageIcon("C:\\Users\\marco\\Desktop\\imagens\\airmaxracer.jpg");
			vans = new ImageIcon("C:\\Users\\marco\\Desktop\\imagens\\vans.jpg");
			azul720 = new ImageIcon("C:\\Users\\marco\\Desktop\\imagens\\720azul.jpg");
			preto720 = new ImageIcon("C:\\Users\\marco\\Desktop\\imagens\\720preto.jpg");
			airjordan = new ImageIcon("C:\\Users\\marco\\Desktop\\imagens\\jordan1.jpg");
			vaporbranco = new ImageIcon("C:\\Users\\marco\\Desktop\\imagens\\vaporbranco.jpg");
			jogger= new ImageIcon("C:\\Users\\marco\\Desktop\\imagens\\jogger.jpg");
		quadrado1 = new JPanel();
		quadrado1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pessoa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times new roman", 0, 20), new java.awt.Color(0,0,0)));
		quadrado1.setBackground(Color.white);
		quadrado1.setBounds(30, 10, 900, 150);
		quadrado1.setVisible(false);
		quadrado1.setLayout(null);
		add(quadrado1);
		
		rg1= new JLabel("RG");
		rg1.setBounds(20, 30, 70, 20);
		Font xx = new Font("Lucida Console", Font.BOLD, 15);
		rg1.setFont(xx);
		quadrado1.add(rg1);
		
		tfrg1 = new JFormattedTextField(msrg);
		tfrg1.setBounds(60, 30, 100, 20);
		quadrado1.add(tfrg1);
	
		imgtenis = new JLabel();
		imgtenis.setBounds(370, 450, 250, 210);
		imgtenis.setOpaque(true);
		imgtenis.setVisible(false);
		imgtenis.setBackground(Color.WHITE);
		add(imgtenis);
		
		pesquisar = new ImageIcon("C:\\Users\\marco\\Desktop\\imagens\\pesquisar.png");
		alterar = new ImageIcon("C:\\Users\\marco\\Desktop\\imagens\\alterar.png");
		excluir = new ImageIcon("C:\\Users\\marco\\Desktop\\imagens\\delete.png");
		novo = new ImageIcon("C:\\Users\\marco\\Desktop\\imagens\\novo.png");
		ok = new ImageIcon("C:\\Users\\marco\\Desktop\\imagens\\ok.png");
		nota= new ImageIcon("C:\\Users\\marco\\Desktop\\imagens\\notafiscal.png");
		
		pesquisar1 = new JButton(pesquisar);
		pesquisar1.setBounds(175, 23, 32, 32);
		pesquisar1.setBackground(null);
		pesquisar1.setBorder(null);
		pesquisar1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		quadrado1.add(pesquisar1);
		
		alterar1 = new JButton(alterar);
		alterar1.setBounds(810, 26, 32, 32);
		alterar1.setBackground(null);
		alterar1.setBorder(null);
		quadrado1.add(alterar1);
		
		excluir1 = new JButton(excluir);
		excluir1.setBounds(850, 23, 32, 32);
		excluir1.setBackground(null);
		excluir1.setBorder(null);
		quadrado1.add(excluir1);
		
		nome1= new JLabel("Nome");
		nome1.setBounds(20, 80, 70, 20);
		nome1.setFont(xx);
		quadrado1.add(nome1);
		
		tfnome1 = new JFormattedTextField();
		tfnome1.setBounds(65, 80, 160, 20);
		quadrado1.add(tfnome1);
		
		end1= new JLabel("Endereço");
		end1.setBounds(280, 80, 80, 20);
		end1.setFont(xx);
		quadrado1.add(end1);
		
		tfend1 = new JFormattedTextField();
		tfend1.setBounds(370, 80, 180, 20);
		quadrado1.add(tfend1);
		
		nasci1= new JLabel("Nascimento");
		nasci1.setBounds(590, 80, 120, 25);
		nasci1.setFont(xx);
		quadrado1.add(nasci1);
		
		tfnasci1 = new JFormattedTextField(msnasci);
		tfnasci1.setBounds(700, 80, 80, 20);
		quadrado1.add(tfnasci1);
		
		cpf1= new JLabel("CPF");
		cpf1.setBounds(25, 120, 70, 20);
		cpf1.setFont(xx);
		quadrado1.add(cpf1);
		
		tfcpf1 = new JFormattedTextField(mscpf);
		tfcpf1.setBounds(65, 120, 100, 20);
		quadrado1.add(tfcpf1);
		
		tel1= new JLabel("Telefone");
		tel1.setBounds(280, 120, 90, 20);
		tel1.setFont(xx);
		quadrado1.add(tel1);
		
		tftel1 = new JFormattedTextField(msTel);
		tftel1.setBounds(370, 120, 120, 20);
		quadrado1.add(tftel1);
		
		//QUADRADO 2
		quadrado2 = new JPanel();
		quadrado2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times new roman", 0, 20), new java.awt.Color(0,0,0)));
		quadrado2.setBounds(30, 190, 900, 250);
		quadrado2.setBackground(Color.white);
		quadrado2.setVisible(false);
		quadrado2.setLayout(null);
		add(quadrado2);
		
		marca= new JLabel("Marca");
		marca.setBounds(20, 30, 80, 20);
		marca.setFont(xx);
		quadrado2.add(marca);
		
		tfmarca = new JTextField();
		tfmarca.setBounds(80, 30, 100, 20);
		quadrado2.add(tfmarca);
		
		pesquisar2 = new JButton(pesquisar);
		pesquisar2.setBounds(190, 23, 32, 32);
		pesquisar2.setBackground(null);
		pesquisar2.setBorder(null);
		pesquisar2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		quadrado2.add(pesquisar2);
		
		novo2 = new JButton(novo);
		novo2.setBounds(770, 23, 32, 32);
		novo2.setBackground(null);
		novo2.setBorder(null);
		quadrado2.add(novo2);
		
		alterar2 = new JButton(alterar);
		alterar2.setBounds(810, 26, 32, 32);
		alterar2.setBackground(null);
		alterar2.setBorder(null);
		quadrado2.add(alterar2);
		
		excluir2 = new JButton(excluir);
		excluir2.setBounds(850, 23, 32, 32);
		excluir2.setBackground(null);
		excluir2.setBorder(null);
		quadrado2.add(excluir2);
		
		bttable = new JButton(ok);
		bttable.setBounds(846, 184, 48, 48);
		bttable.setBackground(null);
		bttable.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bttable.setToolTipText("Atualizar dados");
		bttable.setBorder(null);
	
		quadrado2.add(bttable);
		
		notafiscal = new JButton(nota);
		notafiscal.setBounds(860, 585, 48, 48);
		notafiscal.setBackground(Color.white);
		notafiscal.setBorder(null);
		notafiscal.setCursor(new Cursor(Cursor.HAND_CURSOR));
		notafiscal.setToolTipText("Emitir Nota Fiscal");
		notafiscal.setVisible(false);
		add(notafiscal);
		
		codigo2= new JLabel("Codigo");
		codigo2.setBounds(23, 70, 80, 20);
		codigo2.setFont(xx);
		quadrado2.add(codigo2);
		
		tfcodigo2 = new JTextField();
		tfcodigo2.setBounds(98, 70, 100, 20);
		quadrado2.add(tfcodigo2);
		
		modelo2= new JLabel("Modelo");
		modelo2.setBounds(340, 70, 80, 20);
		modelo2.setFont(xx);
		quadrado2.add(modelo2);
		
		tfmodelo2 = new JTextField();
		tfmodelo2.setBounds(415, 70, 200, 20);
		quadrado2.add(tfmodelo2);
		
		sexo2= new JLabel("Sexo");
		sexo2.setBounds(720, 70, 80, 20);
		sexo2.setFont(xx);
		quadrado2.add(sexo2);
		
		tfsexo2 = new JTextField();
		tfsexo2.setBounds(770, 70, 40, 20);
		quadrado2.add(tfsexo2);
		
		tamanho2= new JLabel("Tamanho");
		tamanho2.setBounds(20, 100, 80, 20);
		tamanho2.setFont(xx);
		quadrado2.add(tamanho2);
		
		tftamanho2 = new JTextField();
		tftamanho2.setBounds(100, 100, 100, 20);
		quadrado2.add(tftamanho2);
		
		cor2= new JLabel("Cor");
		cor2.setBounds(240, 100, 80, 20);
		cor2.setFont(xx);
		quadrado2.add(cor2);
		
		tfcor2 = new JTextField();
		tfcor2.setBounds(285, 100, 100, 20);
		quadrado2.add(tfcor2);
		
		preco2= new JLabel("Preço");
		preco2.setBounds(410, 100, 80, 20);
		preco2.setFont(xx);
		quadrado2.add(preco2);
		
		tfpreco2 = new JTextField();
		tfpreco2.setBounds(470, 100, 100, 20);
		quadrado2.add(tfpreco2);
		
		imtenis2= new JLabel("Imagem");
		imtenis2.setBounds(650, 100, 80, 20);
		imtenis2.setFont(xx);
		quadrado2.add(imtenis2);
		
		tfimtenis2 = new JTextField();
		tfimtenis2.setBounds(730, 100, 100, 20);
		quadrado2.add(tfimtenis2);
		
		scrollTable = new JScrollPane();
		scrollTable.setBounds(15,140, 830, 100);
		quadrado2.add(scrollTable);
		
		/*QUADRADO 3
		quadrado3 = new JPanel();
		quadrado3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		quadrado3.setBounds(30, 470, 200, 190);
		quadrado3.setBackground(Color.white);
		quadrado3.setVisible(false);
		add(quadrado3);
		*/
		//PRIMEIRA JANELA
		setLayout(null);
		btcadastro = new JButton();
		btcompra = new JButton();
		bttutorial = new JButton();
		
		
		imlogo = new ImageIcon("C:\\Users\\marco\\Desktop\\imagens\\logo.png");
		logo = new JLabel(imlogo);
		logo.setBounds(158,10,200,150);
		add(logo);
		
		imcadastro = new ImageIcon("C:\\Users\\marco\\Desktop\\imagens\\cadastro.png");
		btcadastro = new JButton(imcadastro);
		btcadastro.setBounds(190,200,100,100);
		btcadastro.setBackground(Color.white);
		btcadastro.setBorder(null);
		btcadastro.setToolTipText("Cadastrar");
		btcadastro.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		add(btcadastro);
		
		imcompra = new ImageIcon("C:\\Users\\marco\\Desktop\\imagens\\compra.png");
		btcompra = new JButton(imcompra);
		btcompra.setBounds(190,350,100,100);
		btcompra.setToolTipText("Comprar");
		btcompra.setBackground(Color.white);
		btcompra.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		btcompra.setBorder(null);
		add(btcompra);
		
		imtutorial = new ImageIcon("C:\\Users\\marco\\Desktop\\imagens\\tutorial.png");
		bttutorial = new JButton(imtutorial);
		bttutorial.setBounds(190,500,100,100);
		bttutorial.setBackground(Color.white);
		bttutorial.setBorder(null);
		bttutorial.setToolTipText("Tutorial");
		bttutorial.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		add(bttutorial);
		//SEGUNDA JANELA (CADASTRO)
		Font f = new Font("SansSerif", Font.BOLD, 30);
		
		cadastro = new JLabel("Cadastro");
        cadastro.setBounds(400, 110, 200, 100);
        cadastro.setVisible(false);
        cadastro.setFont(f);
        add(cadastro);
		
		lbnome = new JLabel("Nome:");
        lbnome.setBounds(350, 210, 100, 25);
        lbnome.setVisible(false);
        add(lbnome);
		
        lbnasci = new JLabel("Nascimento:");
        lbnasci.setBounds(350, 260, 100, 25);
        lbnasci.setVisible(false);
        add(lbnasci);
        
        lbrg = new JLabel("RG:");
        lbrg.setBounds(350, 310, 100, 25);
        lbrg.setVisible(false);
        add(lbrg);
        
        lbende = new JLabel("Endereço:");
        lbende.setBounds(350, 360, 100, 25);
        lbende.setVisible(false);
        add(lbende);
        
        lbcpf = new JLabel("CPF:");
        lbcpf.setBounds(350, 410, 100, 25);
        lbcpf.setVisible(false);
        add(lbcpf);
        
        lbtell = new JLabel("Telefone:");
        lbtell.setBounds(350, 460, 100, 25);
        lbtell.setVisible(false);
        add(lbtell);
        
        tfnome = new JTextField();
		tfnome.setBounds(450, 210, 120, 25);
		tfnome.setVisible(false);
		add(tfnome);
		
		tfnasci = new JFormattedTextField(msnasci);
		tfnasci.setBounds(450, 260, 120, 25);
		tfnasci.setVisible(false);
		add(tfnasci);
		
		tfrg = new JFormattedTextField(msrg);
		tfrg.setBounds(450, 310, 120, 25);
		tfrg.setVisible(false);
		add(tfrg);
		
		tfende = new JTextField();
		tfende.setBounds(450, 360, 120, 25);
		tfende.setVisible(false);
		add(tfende);
		
		tfcpf = new JFormattedTextField(mscpf);
		tfcpf.setBounds(450, 410, 120, 25);
		tfcpf.setVisible(false);
		add(tfcpf);
		
		tftell = new JFormattedTextField(msTel);
		tftell.setBounds(450, 460, 120, 25);
		tftell.setVisible(false);
		add(tftell);
		
		cadastrar = new JButton("Cadastrar");
		cadastrar.setBounds(400, 510, 120, 25);
		cadastrar.setVisible(false);
		cadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cadastrar.setBackground(Color.darkGray);
		cadastrar.setForeground(Color.WHITE);
		add(cadastrar);
		
		voltar = new JButton("Voltar");
		voltar.setBounds(400, 540, 120, 25);
		voltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		voltar.setVisible(false);
		voltar.setBackground(Color.darkGray);
		voltar.setForeground(Color.WHITE);
		add(voltar);
		
		//PAGINA DO SISTEMA
		
		
		
		//FUNDO
		fundo = new JLabel();
		fundo.setBounds(0, 0, 1000, 1000);
		fundo.setOpaque(true);
		fundo.setBackground(Color.white);
		add(fundo);
		setBotoes(true,false,false,true,false,false,false,false);
		
		
		setTitle("Magluv Sneakers");
		setBounds(480, 40, 500, 700);
		setResizable(false);
		tenis = new TenisMetodos();
		if(!tenis.bd.getConnection()){
			JOptionPane.showMessageDialog(null,"Falha na conexão!");
			System.exit(0);
		}
		
} catch (ParseException e) {
			
			e.printStackTrace();
		}
	}
	public void definirEventos(){
		//PAGINA 1
		
		btcadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			btcadastro.setVisible(false);
			btcompra.setVisible(false);
			bttutorial.setVisible(false);
			tftell.setVisible(true);
			tfnome.setVisible(true);
			tfnasci.setVisible(true);
			tfende.setVisible(true);
			tfcpf.setVisible(true);
			tfrg.setVisible(true);
			lbtell.setVisible(true);
			lbnome.setVisible(true);
			lbnasci.setVisible(true);
			lbende.setVisible(true);
			lbcpf.setVisible(true);
			lbrg.setVisible(true);
			cadastrar.setVisible(true);
			cadastro.setVisible(true);
			voltar.setVisible(true);
			setTitle("Magluv Sneakers(Cadastro)");
			setBounds(200, 30, 960, 700);
			logo.setBounds(370,10,200,150);
			}
		});
		btcompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			btcadastro.setVisible(false);
			btcompra.setVisible(false);
			bttutorial.setVisible(false);
			quadrado1.setVisible(true);
			quadrado2.setVisible(true);
			//quadrado3.setVisible(true);
			imgtenis.setVisible(true);
			notafiscal.setVisible(true);
			tfrg1.requestFocus();
			setTitle("Magluv Sneakers(Compra)");
			setBounds(200, 30, 960, 700);
			
			}
		});
		
		//PAGINA DO CADASTRO
		cadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfrg.getText().equals("  .   .   - ")  ){
					JOptionPane.showMessageDialog(null, "Preencha todos os campos");
					tfrg.requestFocus();
					return;
				}
				
				if(tfnome.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Preencha todos os campos");
					tfnome.requestFocus();
					return;
				}
				if(tfende.getText().equals("")  ){
					JOptionPane.showMessageDialog(null, "Preencha todos os campos");
					tfende.requestFocus();
					return;
				}
				if(tfcpf.getText().equals("         /  ")){
					JOptionPane.showMessageDialog(null, "Preencha todos os campos");
					tfcpf.requestFocus();
					return;
				}
				if(tfnasci.getText().equals("  /  /    ")  ){
					JOptionPane.showMessageDialog(null, "Preencha todos os campos");
					tfnasci.requestFocus();
					return;
				}
				if(tftell.getText().equals("(  )     -    ")){
					JOptionPane.showMessageDialog(null, "Preencha todos os campos");
					tftell.requestFocus();
					return;
				}
				
				btcadastro.setVisible(true);
				btcompra.setVisible(true);
				bttutorial.setVisible(true);
				tftell.setVisible(false);
				tfnome.setVisible(false);
				tfnasci.setVisible(false);
				tfende.setVisible(false);
				tfcpf.setVisible(false);
				tfrg.setVisible(false);
				lbtell.setVisible(false);
				lbnome.setVisible(false);
				lbnasci.setVisible(false);
				lbende.setVisible(false);
				lbcpf.setVisible(false);
				lbrg.setVisible(false);
				cadastrar.setVisible(false);
				cadastro.setVisible(false);
				voltar.setVisible(false);
			
				setBounds(480, 40, 500, 700);
				setTitle("Magluv Sneakers");
				logo.setBounds(170,10,200,150);
				
				tenis.teniss.setRG(tfrg.getText());
				tenis.teniss.setNome(tfnome.getText());
				tenis.teniss.setEndereco(tfende.getText());
				tenis.teniss.setNascimento(tfnasci.getText());
				tenis.teniss.setCPF(tfcpf.getText());
				tenis.teniss.setTelefone(tftell.getText());
				JOptionPane.showMessageDialog(null,tenis.atualizar((TenisMetodos.inclusao)));
				
				limparcadastro();
					
				}
				
			
			});
		
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			btcadastro.setVisible(true);
			btcompra.setVisible(true);
			bttutorial.setVisible(true);
			tftell.setVisible(false);
			tfnome.setVisible(false);
			tfnasci.setVisible(false);
			tfende.setVisible(false);
			tfcpf.setVisible(false);
			tfrg.setVisible(false);
			lbtell.setVisible(false);
			lbnome.setVisible(false);
			lbnasci.setVisible(false);
			lbende.setVisible(false);
			lbcpf.setVisible(false);
			lbrg.setVisible(false);
			cadastrar.setVisible(false);
			cadastro.setVisible(false);
			voltar.setVisible(false);
			setBounds(480, 40, 500, 700);
			setTitle("Magluv Sneakers");
			logo.setBounds(158,10,200,150);
			limparcadastro();
			}
		});
		
		//PAGINA DO SISTEMA
		pesquisar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfrg1.getText().equals("  .   .   - ")){
					JOptionPane.showMessageDialog(null,"O RG não foi preenchido!");
					tfrg1.requestFocus();
					return;
				}
				tenis.teniss.setRG(tfrg1.getText());
				
				if(tenis.pesquisaPessoa(1)== 2){
					limparcadastro1();
				}else if(tenis.pesquisaPessoa(1)== 1){
				atualizarCampos1();
				}
			}
			});
		
		alterar1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				tenis.teniss.setRG(tfrg1.getText());
				tenis.teniss.setCPF(tfcpf1.getText());
				tenis.teniss.setNome(tfnome1.getText());
				tenis.teniss.setEndereco(tfend1.getText());
				tenis.teniss.setTelefone(tftel1.getText());
				tenis.teniss.setNascimento(tfnasci1.getText());
				JOptionPane.showMessageDialog(null,tenis.atualizar((TenisMetodos.alteracao)));
				limparcadastro1();
				
			}
		});
		
		excluir1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				tenis.teniss.setRG(tfrg1.getText());
				int n = JOptionPane.showConfirmDialog(null, tenis.teniss.getNome(),"Excluir o cadastro?", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION){
					JOptionPane.showMessageDialog(null,tenis.atualizar((TenisMetodos.exclusao)));
					limparcadastro1();
				}
				
				
			}
		});
		
		pesquisar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfmarca.getText().equals("")){
					JOptionPane.showMessageDialog(null,"A marca não foi preenchida!");
					tfmarca.requestFocus();
					return;
				}
				tenis.teniss.setMarca(tfmarca.getText());
				
				scrollTable.setViewportView(tenis.localizar());
			
			setBotoes(true,false,false,true,true,true,true,true);
			}
			});
		bttable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tenis.setarcampos();
				atualizarCampos2();
				verImg();
				
			}
			});
		novo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tenis.teniss.setMarca(tfmarca.getText());
				tenis.teniss.setCodigo(tfcodigo2.getText());
				tenis.teniss.setModelo(tfmodelo2.getText());
				tenis.teniss.setSexo(tfsexo2.getText());
				tenis.teniss.setTamanho(tftamanho2.getText());
				tenis.teniss.setCor(tfcor2.getText());
				tenis.teniss.setPreco(tfpreco2.getText());
				tenis.teniss.setImg(tfimtenis2.getText());
				JOptionPane.showMessageDialog(null,tenis.atualizar2((TenisMetodos.inclusao2)));
				limparcadastro2();
			}
			});
		alterar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tenis.teniss.setMarca(tfmarca.getText());
				tenis.teniss.setCodigo(tfcodigo2.getText());
				tenis.teniss.setModelo(tfmodelo2.getText());
				tenis.teniss.setSexo(tfsexo2.getText());
				tenis.teniss.setTamanho(tftamanho2.getText());
				tenis.teniss.setCor(tfcor2.getText());
				tenis.teniss.setPreco(tfpreco2.getText());
				tenis.teniss.setImg(tfimtenis2.getText());
				JOptionPane.showMessageDialog(null,tenis.atualizar2((TenisMetodos.alteracao2)));
				limparcadastro2();
				
			}
			});
		excluir2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tenis.teniss.setRG(tfrg1.getText());
				int n = JOptionPane.showConfirmDialog(null, tenis.teniss.getModelo(),"Excluir o tenis?", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION){
					JOptionPane.showMessageDialog(null,tenis.atualizar2((TenisMetodos.exclusao2)));
					limparcadastro2();
				}
				
			}
			});
		notafiscal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimir();
			}
			});
		bttutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			btcadastro.setVisible(false);
			btcompra.setVisible(false);
			bttutorial.setVisible(false);
			avancar.setVisible(true);
			anterior.setVisible(true);
			imgtutorial.setVisible(true);
			imgtutorial.setIcon(telainicial);
			voltart.setVisible(true);
			setTitle("Magluv Sneakers Tutorial");
			setBounds(200, 30, 960, 700);
			logo.setVisible(false);
			}
		});
		
		voltart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btcadastro.setVisible(true);
				btcompra.setVisible(true);
				bttutorial.setVisible(true);
				setBounds(480, 40, 500, 700);
				setTitle("Magluv Sneakers");
				logo.setBounds(158,10,200,150);
				voltart.setVisible(false);
				avancar.setVisible(false);
				imgtutorial.setVisible(false);
				anterior.setVisible(false);
				logo.setVisible(true);
			}
		});
		avancar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(imgtutorial.getIcon().equals(telainicial)){
					imgtutorial.setIcon(telacadastro);
				
				}else if(imgtutorial.getIcon().equals(telacadastro)){
					imgtutorial.setIcon(telacompra);
			
				}
				
			}
		});
		
		anterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(imgtutorial.getIcon().equals(telacadastro)){
					imgtutorial.setIcon(telainicial);
					
				}
				if(imgtutorial.getIcon().equals(telacompra)){
					imgtutorial.setIcon(telacadastro);
					cont--;
				}
				
			}
		});
			}

	public void imprimir(){
		int confirma = JOptionPane.showConfirmDialog(null, "Confirmar a impressão da nota?","Atenção",JOptionPane.YES_NO_OPTION);
		if(confirma == JOptionPane.YES_OPTION){
			try{
				 HashMap filtro = new HashMap();
				 filtro.put("RG",tfrg1.getText());
				 filtro.put("codigo", tfcodigo2.getText());
				JasperPrint print = JasperFillManager.fillReport("C:\\Users\\marco\\JaspersoftWorkspace\\MyReports\\notafiscal.jasper",filtro,cc);
				JasperViewer.viewReport(print,false);
				
				
			} catch (Exception ae){
				JOptionPane.showMessageDialog(null, ae);
			}
	}
	} 
	public void limparcadastro(){
		tfnome.setText(null);
		tfende.setText(null);
		tfrg.setText(null);
		tfcpf.setText(null);
		tfnasci.setText(null);
		tftell.setText(null);
	}
	
	public void limparcadastro1(){
		tfnome1.setText(null);
		tfend1.setText(null);
		tfrg1.setText(null);
		tfcpf1.setText(null);
		tfnasci1.setText(null);
		tftel1.setText(null);
		tenis.teniss.setRG("");
		tenis.teniss.setNome("");
		tenis.teniss.setEndereco("");
		tenis.teniss.setNascimento("");
		tenis.teniss.setCPF("");
		tenis.teniss.setTelefone("");
		tfrg1.requestFocus();
		setBotoes(true,false,false,true,false,false,false,false);
	}
	public void limparcadastro2() {
		tfcodigo2.setText("");
		tfmodelo2.setText("");
		tfsexo2.setText("");
		tftamanho2.setText("");
		tfcor2.setText("");
		tfpreco2.setText("");
		tfimtenis2.setText("");
		scrollTable.setViewportView(null);
		verImg();
	}
	
	public void atualizarCampos1(){
		
		tfnome1.setText(tenis.teniss.getNome());
		tfend1.setText(tenis.teniss.getEndereco());
		tftel1.setText(tenis.teniss.getTelefone());
		tfcpf1.setText(tenis.teniss.getCPF());
		tfnasci1.setText(tenis.teniss.getNascimento());
		setBotoes(true,true,true,true,false,false,false,false);
		
	
}
	
	public void atualizarCampos2(){
		tfmodelo2.setText(tenis.teniss.getModelo());
		tftamanho2.setText(tenis.teniss.getTamanho());
		tfsexo2.setText(tenis.teniss.getSexo());
		tfcor2.setText(tenis.teniss.getCor());
		tfpreco2.setText(tenis.teniss.getPreco());
		tfcodigo2.setText(tenis.teniss.getCodigo());
		tfimtenis2.setText(tenis.teniss.getImg());
		
	}
	public void verImg(){
		
		if(tenis.teniss.getImg().equals("yezzystatic")){
			imgtenis.setIcon(yezzyt);
		}else if(tenis.teniss.getImg().equals("720azul")){
			imgtenis.setIcon(azul720);
		}else if(tenis.teniss.getImg().equals("vaporoff")){
			imgtenis.setIcon(vaporff);
		}else if(tenis.teniss.getImg().equals("720preto")){
			imgtenis.setIcon(preto720);
		}else if(tenis.teniss.getImg().equals("vans")){
			imgtenis.setIcon(vans);
		}else if(tenis.teniss.getImg().equals("airmaxracer")){
			imgtenis.setIcon(airmaxracer);
		}else if(tenis.teniss.getImg().equals("airjordan1")){
			imgtenis.setIcon(airjordan);
		}else if(tenis.teniss.getImg().equals("vaporbranco")){
			imgtenis.setIcon(vaporbranco);
		}else if(tenis.teniss.getImg().equals("jogger")){
			imgtenis.setIcon(jogger);
		}else{
		
			imgtenis.setIcon(null);
		}
	}
	public void setBotoes(boolean pesquisa1, boolean altera, boolean exclui,boolean pesquisa2,boolean bNovo, boolean bAlterar, boolean bExcluir,boolean btable){
		pesquisar1.setEnabled(pesquisa1);
		alterar1.setEnabled(altera);
		excluir1.setEnabled(exclui);
		pesquisar2.setEnabled(pesquisa2);
		novo2.setEnabled(bNovo);
		alterar2.setEnabled(bAlterar);
		excluir2.setEnabled(bExcluir);
		bttable.setEnabled(btable);
	}

}