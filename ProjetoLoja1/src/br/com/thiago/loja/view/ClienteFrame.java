package br.com.thiago.loja.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import br.com.thiago.loja.control.ClienteControl;
import br.com.thiago.loja.control.MunicipioControl;
import br.com.thiago.loja.model.Municipio;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ClienteFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private static JTextField textField;
	private static JTextField textField_1;
	private static JTextField textField_2;
	private static JTextField textField_3;
	private static JTextField textField_4;
	private static JTextField textField_5;
	private static JTextField textField_6;
	private static JTextField textField_7;
	private static JTextField textField_8;
	private static Long idCheck = 0L;
	private static Municipio municipioCheck;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteFrame window = new ClienteFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void main(Long id, Long codigo, String nome, String endereco, Integer numero, 
			String bairro, String cep, Municipio municipio){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteFrame window = new ClienteFrame();
					window.frame.setVisible(true);
					idCheck = id;
			    	municipioCheck = municipio;
			    	textField.setText(String.valueOf(codigo));
			    	textField_1.setText(nome);
			    	textField_2.setText(endereco);
			    	textField_3.setText(String.valueOf(numero));
			    	textField_4.setText(bairro);
			    	textField_5.setText(cep);
			    	textField_6.setText(String.valueOf(municipioCheck.getCodigo()));
			    	textField_7.setText(municipioCheck.getNome());
			    	textField_8.setText(municipioCheck.getEstado());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
    public static void limpar() {
    	textField.setText("");
    	textField_1.setText("");
    	textField_2.setText("");
    	textField_3.setText("");
    	textField_4.setText("");
    	textField_5.setText("");
    	textField_6.setText("");
    	textField_7.setText("");
    	textField_8.setText("");
    	idCheck = 0L;
    	municipioCheck = new Municipio();
    } 
    private void cancelarButtonAct(ActionEvent evt) {
        MenuFrame.main(null);
        limpar();
        frame.dispose();
    }
    private void localizarClienteButtonAct(ActionEvent evt) {
    	ListaClienteFrame.main(null);
    	frame.dispose();
    }
    private void salvarButtonAct(ActionEvent evt) {
        if (textField.getText().equals("") || textField_1.getText().equals("") 
        		|| textField_2.getText().equals("") || textField_3.getText().equals("") 
        		|| textField_4.getText().equals("") || textField_5.getText().equals("") 
        		|| textField_6.getText().equals("") || textField_7.getText().equals("") 
        		|| textField_8.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios.", "", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
        	try{
        		Municipio m = MunicipioControl.buscar(municipioCheck.getId());
        		ClienteControl.salvar(Long.valueOf(textField.getText()), 
        				(textField_1.getText().toString()), (textField_2.getText().toString()), 
        				(Integer.valueOf(textField_3.getText())), (textField_4.getText().toString()), 
        				(textField_5.getText().toString()), m);
        	} catch (Exception p) {
                p.printStackTrace();
            }
        }
    }
    
    private void alterarButtonAct(ActionEvent evt) {
        if (textField.getText().equals("") || textField_1.getText().equals("") 
        		|| textField_2.getText().equals("") || textField_3.getText().equals("") 
        		|| textField_4.getText().equals("") || textField_5.getText().equals("") 
        		|| textField_6.getText().equals("") || textField_7.getText().equals("") 
        		|| textField_8.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios.", "", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
        	try{
        		Municipio m = MunicipioControl.buscar(municipioCheck.getId());
        		ClienteControl.alterar(idCheck, (Long.valueOf(textField.getText())), 
        				(textField_1.getText().toString()), (textField_2.getText().toString()), 
        				(Integer.valueOf(textField_3.getText())), (textField_4.getText().toString()), 
        				(textField_5.getText().toString()), m);
        	} catch (Exception p) {
                p.printStackTrace();
            }
        }
    }

	/**
	 * Create the application.
	 */
	public ClienteFrame() {
		initialize();
        this.pack();
        this.setLocationRelativeTo(null);		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Cadastro de Clientes");
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 7, 20));
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		panel.add(lblCdigo);
		
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
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		panel.add(lblEndereo);
		
		textField_2 = new JTextField();
		panel.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setDocument(new TextLimit(30));
		
		JLabel lblNmero = new JLabel("N\u00FAmero");
		panel.add(lblNmero);
		
		textField_3 = new JTextField();
		panel.add(textField_3);
		textField_3.setColumns(5);
		textField_3.setDocument(new TextLimit(8));
		
		JLabel lblBairro = new JLabel("Bairro");
		panel.add(lblBairro);
		
		textField_4 = new JTextField();
		panel.add(textField_4);
		textField_4.setColumns(10);
		textField_4.setDocument(new TextLimit(20));
		
		JLabel lblCep = new JLabel("CEP");
		panel.add(lblCep);
		
		textField_5 = new JTextField();
		panel.add(textField_5);
		textField_5.setColumns(10);
		textField_5.setDocument(new TextLimit(10));
		
		JLabel lblCdigoDoMunicpio = new JLabel("C\u00F3digo do Munic\u00EDpio");
		panel.add(lblCdigoDoMunicpio);
		
		textField_6 = new JTextField();
		textField_6.setColumns(4);
		textField_6.setDocument(new TextLimit(6));
		textField_6.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent de) {
        		textField_7.setText("");
        		textField_8.setText("");
            	municipioCheck = new Municipio();
				// TODO Auto-generated method stub
			}
			@Override
			public void insertUpdate(DocumentEvent de) {
            	if((textField_6.getText().length())==6){
            		try {
        				municipioCheck = MunicipioControl.buscarCodigo(Long.valueOf(textField_6.getText()));
        		    	if(municipioCheck != null){
        		    		textField_7.setText(municipioCheck.getNome());
            		    	textField_8.setText(municipioCheck.getEstado());
        		    	} else {
        		    		municipioCheck = new Municipio();
        		    	};
        			} catch (NumberFormatException e) {
        				e.printStackTrace();
        			} catch (Exception e) {
        				e.printStackTrace();
        			}
            	};
				// TODO Auto-generated method stub
			}
			@Override
			public void changedUpdate(DocumentEvent de) {
				// TODO Auto-generated method stub
			}
		});
		panel.add(textField_6);
		
		JLabel lblMunicpio = new JLabel("Munic\u00EDpio");
		panel.add(lblMunicpio);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		panel.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblUf = new JLabel("UF");
		panel.add(lblUf);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		panel.add(textField_8);
		textField_8.setColumns(3);
		
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
                localizarClienteButtonAct(evt);
            }
        });
		panel.add(btnLocalizar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelarButtonAct(evt);
            }
        });
		panel.add(btnCancelar);
	}
	
	public static Long getIdCheck() {
		return idCheck;
	}

	public static void setIdCheck(Long idCheck) {
		ClienteFrame.idCheck = idCheck;
	}

}
