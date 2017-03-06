package br.com.thiago.loja.control;

import br.com.thiago.loja.dao.MunicipioDAO;
import br.com.thiago.loja.model.Municipio;

import java.util.List;

import javax.swing.JOptionPane;

public class MunicipioControl {
	
    public static Municipio buscar(Long id) throws Exception {
    	MunicipioDAO dao = new MunicipioDAO();
        return dao.buscar(id);
    }

    public static void salvar(Long codigo, String nome, String estado) {
    	Municipio m = new Municipio(codigo, nome, estado);
		new MunicipioDAO().salvar(m);
    }    

    public static void alterar(Long id, Long codigo, String nome, String estado) {
        Municipio m = new MunicipioDAO().buscar(id);        
		Municipio mTesteNome = new MunicipioDAO().buscarPorNome(nome);
		Municipio mTesteCodigo = new MunicipioDAO().buscarPorCodigo(codigo);

		if(mTesteNome != null || mTesteCodigo != null){
				// alterar mantendo nome
			if (mTesteNome != null && mTesteNome.getId() == m.getId() && mTesteCodigo == null){
				m.setCodigo(codigo);
		        m.setNome(nome);
		        m.setEstado(estado);
				new MunicipioDAO().alterar(m);
				// alterar mantendo codigo
			} else if (mTesteCodigo != null && mTesteCodigo.getId()==m.getId() && mTesteNome == null){
				m.setCodigo(codigo);
		        m.setNome(nome);
		        m.setEstado(estado);
				new MunicipioDAO().alterar(m);
				// alterar ambos
			} else if ((mTesteNome != null && mTesteNome.getId() == m.getId()) && (mTesteCodigo != null && mTesteCodigo.getCodigo() == m.getCodigo())){
				m.setCodigo(codigo);
		        m.setNome(nome);
		        m.setEstado(estado);
				new MunicipioDAO().alterar(m);
			} else {
				JOptionPane.showMessageDialog(null, "Já existe um município com este código ou nome.", "", JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
	        m.setCodigo(codigo);
	        m.setNome(nome);
	        m.setEstado(estado);
			new MunicipioDAO().alterar(m);
		}
    }
    
    public static void remover(Long id) throws Exception {
    	MunicipioDAO dao= new MunicipioDAO();
		Municipio m = dao.buscar(id);
    	dao.remover(m);
    }
    
    public static Municipio buscarCodigo(Long codigo) throws Exception {
    	MunicipioDAO dao = new MunicipioDAO();
        return dao.buscarPorCodigo(codigo);
    }

    public List<Municipio> listarTodos() throws Exception {
    	MunicipioDAO dao = new MunicipioDAO();
            return dao.listarTodos();
    }
}