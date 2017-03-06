package br.com.thiago.loja.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.thiago.loja.control.ClienteControl;
import br.com.thiago.loja.dao.ClienteDAO;
import br.com.thiago.loja.model.Cliente;

public class ListaClienteFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTable tabela;
	private ArrayList<Long> idHidden = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaClienteFrame window = new ListaClienteFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ListaClienteFrame() {
		initialize();
		listar();
	}
	
    public void listar() {
        List<Cliente> lista = new LinkedList<>();
        ClienteDAO dao = new ClienteDAO();

        lista = dao.listarTodos();
        DefaultTableModel modeloTable = new DefaultTableModel(new Object[][]{},
                new Object[]{"Codigo", "Nome", "Municipio", "UF"}){
				private static final long serialVersionUID = 1L;
			@Override
        	public boolean isCellEditable(int row, int col){
        	return false;}
			};
        

        for (Cliente c : lista) {
            modeloTable.addRow(new String[]{String.valueOf(c.getCodigo()), c.getNome(), 
            		c.getMunicipio().getNome(), c.getMunicipio().getEstado()});
            idHidden.add(c.getId());
        }

        tabela.setModel(modeloTable);
    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Lista de Clientes");
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		panel.setLayout(new BorderLayout());
		tabela = new JTable();
		tabela.setFont(new Font("Calibri", Font.PLAIN, 15));
		tabela.getTableHeader().setReorderingAllowed(false);
		JScrollPane scroll =  new JScrollPane(tabela);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add("Center", scroll);
		tabela.addMouseListener(new MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
            	if (evt.getClickCount() == 2){
            	listaClienteTableMousePressed(evt);
            	}
            }
        });
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                okButtonAct(evt);
            }
        });
		panel_1.add(btnNewButton);
		
		JButton btnCancela = new JButton("Cancelar");
		btnCancela.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelarButtonAct(evt);
            }
        });
		panel_1.add(btnCancela);
	}
	
    private void okButtonAct (ActionEvent evt) {
    	if (tabela.getSelectionModel().isSelectionEmpty() == false){
	        int rowIndex = tabela.getSelectedRow();
	        Long codigo = idHidden.get(rowIndex);
	        try{
		        Cliente cliente = ClienteControl.buscar(codigo);
		        ClienteFrame.main(codigo, cliente.getCodigo(), cliente.getNome(), cliente.getEndereco(), cliente.getNumero(), cliente.getBairro(), cliente.getCep(), cliente.getMunicipio());
		        frame.dispose();
	        } catch (Exception e) {
	        	e.printStackTrace();
			}
	    } else {
    	JOptionPane.showMessageDialog(null, "Selecione um cliente.", "", JOptionPane.INFORMATION_MESSAGE);
	    }
    	return;
    }
    
    private void cancelarButtonAct(ActionEvent evt) {
            ClienteFrame.main(null);
            frame.dispose();
    }

    private void listaClienteTableMousePressed(MouseEvent evt) {
        int rowIndex = tabela.getSelectedRow();
        Long codigo = idHidden.get(rowIndex);
        try{
        	Cliente cliente = ClienteControl.buscar(codigo);
	        ClienteFrame.main(codigo, cliente.getCodigo(), cliente.getNome(), cliente.getEndereco(), cliente.getNumero(), cliente.getBairro(), cliente.getCep(), cliente.getMunicipio());
	        frame.dispose();
        } catch (Exception e) {
        	e.printStackTrace();
		}
    }

}
