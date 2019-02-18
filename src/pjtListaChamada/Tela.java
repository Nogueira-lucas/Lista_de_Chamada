package pjtListaChamada;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.omg.CORBA.ARG_IN;
import org.xml.sax.HandlerBase;

public class Tela extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	public ArrayList <Aluno>  alunos   		= new ArrayList<Aluno>();
	public HashSet   <String> numeros  		= new HashSet<String>();
	public HashMap   <String, String> map 	= new HashMap<String, String>();
	
	JButton		btnChamada;
	JButton		btnAddAluno;
	JButton     btnListar;
	JButton 	btnBuscar;
	JLabel  	lblNomeAluno;
	JLabel  	lblNumero;
	JTextField 	txtNomeAluno;
	JTextField 	txtNumero;
	
	public Tela() {
		setTitle("Chamada");
		setBounds(150, 70, 500, 500);
		setLayout(null);
		init();
		setVisible(true);
	}
	
	public void init()
	{
		lblNomeAluno = new JLabel();
		lblNomeAluno.setText("Nome");
		lblNomeAluno.setBounds(0, 10, getWidth(), 35);
		lblNomeAluno.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNomeAluno);
		
		txtNomeAluno = new JTextField();
		txtNomeAluno.setBounds(0, 40, getWidth(), 35);
		add(txtNomeAluno);
		
		lblNumero = new JLabel();
		lblNumero.setText("Numero:");
		lblNumero.setBounds(0, 80, getWidth(), 35);
		lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(0, 110, getWidth(), 35);
		add(txtNumero);
		
		btnAddAluno = new JButton();
		btnAddAluno.setText("Novo Aluno");
		btnAddAluno.setBounds(150, 110+50, getWidth()-300, 40);
		add(btnAddAluno);
		
		btnChamada = new JButton();
		btnChamada.setText("Chamada");
		btnChamada.setBounds(150, 110+(50*2), getWidth()-300, 40);
		add(btnChamada);
		
		btnListar = new JButton();
		btnListar.setText("Listar");
		btnListar.setBounds(150, 110+(50*3), getWidth()-300, 40);
		add(btnListar);
		
		btnBuscar = new JButton();
		btnBuscar.setText("Buscar");
		btnBuscar.setBounds(150, 110+(50*4), getWidth()-300, 40);
		add(btnBuscar);
		
		btnAddAluno.addActionListener(this);
		btnChamada.addActionListener(this);
		btnListar.addActionListener(this);
		btnBuscar.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(btnListar.equals(e.getSource())) {
			//listar alunos com joptionpane
			listarAlunos();
		}
		else if(btnAddAluno.equals(e.getSource())) {
			//inserir aluno na lista e mandar uma mensagem com joptionpane (não pode inserir repetido)
			novoAluno(txtNomeAluno.getText(), txtNumero.getText());
		}
		else if(btnChamada.equals(e.getSource())) {
			//loop que passa po todos os indices da lista e controla se aluno veio ou faltou
			fazerChamada();
		}else if(btnBuscar.equals(e.getSource())) {
			//buscar aluno pelo numero e postar atributos no formulario
			String nome,
				   numero, 
				   status;
		
			numero = JOptionPane.showInputDialog(null, "Digite o número do aluno:");
			
			if(map.containsKey(numero)) {
				JOptionPane.showMessageDialog(null, map.get(numero));
			}
		}
	}

	private void novoAluno(String nome, String numero) {
		//instanciei e construi
		Aluno a = new Aluno();
		a.setNome(nome);
		a.setNumero(numero);
		
		if(nome.equals("")|| numero.equals("")) {
			JOptionPane.showMessageDialog(this, "Campo vazio!");
			return;
		}else {
			if(!jaExiste(numero)) {
				alunos.add(a);
				JOptionPane.showMessageDialog(this,"Aluno adicionado com sucesso!");
				limparCampos();
				return;
			}else {
				JOptionPane.showMessageDialog(this,"Ja existe!");
				return;
			}
			
		}
	}
		
	private void listarAlunos() {
		String mensagem = new String();
		
		for(Aluno a : alunos) {
			mensagem += "NOME:" + a.getNome()+" Nº:" + a.getNumero() + "\n";
		}
		
		if(mensagem.equals("")) {
			JOptionPane.showMessageDialog(this, "Não existem nenhum aluno ainda!");
			return;
		}else {
			JOptionPane.showMessageDialog(this, mensagem);
			return;
		}
	}
	
	private void fazerChamada() {
		String status;
		
		for(Aluno a : alunos) {
			status = JOptionPane.showInputDialog(a.getNumero()+" Digite aqui [PRESENTE/FALTOU]:");
			map.put(a.getNumero(), status);
		}
		
	}
	
	private boolean jaExiste(String n) {
		int largura = numeros.size();
		numeros.add(n);
		return (largura == numeros.size());
	}

	private void limparCampos() {
		txtNumero.setText("");
		txtNomeAluno.setText("");
	}
}