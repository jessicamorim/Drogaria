package br.com.drogaria.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.ListDataModel;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.JSFUtil;

@ManagedBean(name = "MBFabricante")
@javax.faces.bean.ViewScoped


public class FabricanteBean {
	private Fabricante fabricante = new Fabricante();
	private ArrayList<Fabricante> itens;
	private ArrayList<Fabricante> itensFiltrados;
	
	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	
	public ArrayList<Fabricante> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fabricante> itens) {
		this.itens = itens;
	}

	public ArrayList<Fabricante> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Fabricante> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa(){
		try {
		FabricanteDAO dao = new FabricanteDAO();
		itens = dao.listar();
	
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void prepararNovo(){
		fabricante = new Fabricante();
	}
	
	public void novo(){
		try {
		FabricanteDAO dao = new FabricanteDAO();
		dao.salvar(fabricante);
		
		itens = dao.listar();
		
		JSFUtil.adicionarMensagemSucesso("Fabricante salvo com sucesso");
		}catch(SQLException ex){
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());		
		}
	}
	
	/*public void prepararExcluir() {
		fabricante = itens.getRowData();	
	}*/
	
	public void excluir() {
		try{
		FabricanteDAO dao = new FabricanteDAO();
		dao.excluir(fabricante);
		
		itens = dao.listar();
			
		JSFUtil.adicionarMensagemSucesso("Fabricante removido com sucesso.");
		}catch(SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	/*public void prepararEditar() {
		fabricante = itens.getRowData();
	}*/
	
	public void editar() {
		try{
			FabricanteDAO dao = new FabricanteDAO();
			dao.editar(fabricante);
			
			itens = dao.listar();
		
			JSFUtil.adicionarMensagemSucesso("Fabricante editado com sucesso.");
			}catch(SQLException ex) {
				ex.printStackTrace();
				JSFUtil.adicionarMensagemErro(ex.getMessage());
			}
	}
	
	
	
	
	
	
	
	
}
