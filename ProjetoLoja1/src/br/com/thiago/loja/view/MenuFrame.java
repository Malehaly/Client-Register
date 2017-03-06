package br.com.thiago.loja.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MenuFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JFrame frame = new JFrame();


	/**
	 * Create the application.
	 */
	public MenuFrame() {
		initialize();
        this.pack();
        this.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame.setBounds(100, 100, 450, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Menu - Projeto Loja1");
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 25));
		
		JButton btnCadastroDeClientes = new JButton("Cadastro de Clientes");
		btnCadastroDeClientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	clienteButtonAct(evt);
            }
        });
		panel.add(btnCadastroDeClientes);
		
		JButton btnCadastoDeMunicpio = new JButton("Cadasto de Munic\u00EDpio");
		btnCadastoDeMunicpio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	municipioButtonAct(evt);
            }
        });
		panel.add(btnCadastoDeMunicpio);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	sairButtonAct(evt);
            }
        });
		panel.add(btnSair);
	}
    private void sairButtonAct(ActionEvent evt) {
        this.setVisible(false);
        System.exit(0);
    }
    private void municipioButtonAct(ActionEvent evt) {
    	MunicipioFrame.main(null);
    	frame.dispose();
    }
    private void clienteButtonAct(ActionEvent evt) {
    	ClienteFrame.main(null);
    	frame.dispose();
    }
    
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuFrame window = new MenuFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
