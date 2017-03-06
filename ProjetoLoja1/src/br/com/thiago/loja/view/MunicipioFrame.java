package br.com.thiago.loja.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import br.com.thiago.loja.control.MunicipioControl;

import javax.swing.JComboBox;
import javax.swing.JButton;

public class MunicipioFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private static JTextField textField;
	private static JTextField textField_1;
	@SuppressWarnings("rawtypes")
	private static JComboBox comboBox;
	private static Long idCheck = 0L;

	/**
	 * Launch the application.
	 */    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MunicipioFrame window = new MunicipioFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void main(Long id, Long codigo, String nome, String uf){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MunicipioFrame window = new MunicipioFrame();
					window.frame.setVisible(true);
					idCheck = id;
			    	textField.setText(String.valueOf(codigo));
			    	textField_1.setText(nome);
			    	comboBox.setSelectedItem(uf);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
    public static void limpar() {
    	textField.setText("");
    	textField_1.setText("");
    	comboBox.setSelectedIndex(0);
    	idCheck = 0L;
    } 
    private void cancelarButtonAct(ActionEvent evt) {
        MenuFrame.main(null);
        limpar();
        frame.dispose();
    }
    private void localizarMunicipioButtonAct(ActionEvent evt) {
    	ListaMunicipioFrame.main(null);
    	frame.dispose();
    }
    private void salvarButtonAct(ActionEvent evt) {
        if (textField.getText().equals("") || textField_1.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios.", "", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
        	try{
                MunicipioControl.salvar(Long.valueOf(textField.getText()),
                		(textField_1.getText().toString()), (comboBox.getSelectedItem()).toString());
        	} catch (Exception p) {
                p.printStackTrace();
            }
        }
    }
    
    private void alterarButtonAct(ActionEvent evt) {
        if (textField.getText().equals("") || textField_1.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios.", "", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
        	try{
                MunicipioControl.alterar((idCheck),(Long.valueOf(textField.getText())),
                		(textField_1.getText().toString()), (comboBox.getSelectedItem()).toString());
        	} catch (Exception p) {
                p.printStackTrace();
            }
        }
    }

	/**
	 * Create the application.
	 */
	public MunicipioFrame(){
		initialize();
        this.pack();
        this.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize(){
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Cadastro de Municipios");
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 40));
		
		JLabel lblCodMun = new JLabel("C\u00F3digo do Munic\u00EDpio");
		panel.add(lblCodMun);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(4);
		textField.setDocument(new TextLimit(6));
		
		JLabel lblNome = new JLabel("Nome");
		panel.add(lblNome);
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setDocument(new TextLimit(30));
		
		JLabel lblEstado = new JLabel("Estado");
		panel.add(lblEstado);
		
		/*String[] ufs = { " ", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", 
				"MG", "PR", "PB", "PA", "PE", "PI", "RN", "RJ", "RO", "RR", "RS", "SC", "SE", "SP", 
				"TO"};
		JComboBox<String> comboBox = new JComboBox<>(ufs);*/
		
		//a collection de comboBox não está pegando para salvar de objeto para string. há erros.
		comboBox = new JComboBox();
		comboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AC", "AL", "AP", 
				"AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PR", "PB", "PA", "PE", 
				"PI", "RN", "RJ", "RO", "RR", "RS", "SC", "SE", "SP", "TO"}));
		panel.add(comboBox);
		
		JButton btnOkaltera = new JButton("Ok/Alterar");
		btnOkaltera.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt){
            	if(idCheck > 0L){
            		alterarButtonAct(evt);
            	} else {
            		salvarButtonAct(evt);}
            }
        });
		panel.add(btnOkaltera);
		
		JButton btnLocalizar = new JButton("Localizar");
		btnLocalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                localizarMunicipioButtonAct(evt);
            }
        });
		panel.add(btnLocalizar);
		
		JButton btnCancela = new JButton("Cancelar");
		btnCancela.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelarButtonAct(evt);
            }
        });
		panel.add(btnCancela);
	}

	public static Long getIdCheck() {
		return idCheck;
	}

	public static void setIdCheck(Long idCheck) {
		MunicipioFrame.idCheck = idCheck;
	}
}
