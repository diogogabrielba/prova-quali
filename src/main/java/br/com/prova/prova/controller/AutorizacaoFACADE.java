package br.com.prova.prova.controller;

import java.util.List;

import br.com.prova.manager.abstracts.AbstractPO;
import br.com.prova.manager.exceptions.BackendException;
import br.com.prova.manager.interfaces.Crud;
import br.com.prova.prova.model.AutorizacaoPO;
import br.com.prova.prova.model.AutorizacaoSERVICE;

public final class AutorizacaoFACADE implements Crud{

	private final AutorizacaoSERVICE SERVICE;

	public AutorizacaoFACADE(){
		this.SERVICE = new AutorizacaoSERVICE();
	}

	@Override
	public void inserir( AbstractPO po ) throws BackendException {
		this.SERVICE.inserir( po );

	}

	@Override
	public void alterar( AbstractPO po ) throws BackendException {
		this.SERVICE.alterar( po );

	}

	@Override
	public void excluir( AbstractPO po ) throws BackendException {
		this.SERVICE.excluir( po );

	}

	@SuppressWarnings( "rawtypes" )
	@Override
	public List filtrar( AbstractPO po ) throws BackendException {

		return this.SERVICE.filtrar( po );
	}

	@Override
	public AutorizacaoPO filtrarPorID( String id ) throws BackendException {

		return (AutorizacaoPO) this.SERVICE.filtrarPorID( id );
	}

	public String validar( String procedimento, String idade, String sexo ) throws BackendException {

		return this.SERVICE.validar( procedimento, idade, sexo );
	}

}
