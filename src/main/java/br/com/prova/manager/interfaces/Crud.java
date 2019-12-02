package br.com.prova.manager.interfaces;

import java.util.List;

import br.com.prova.manager.abstracts.AbstractPO;
import br.com.prova.manager.exceptions.BackendException;

public interface Crud{

	void inserir( AbstractPO po ) throws BackendException;

	void alterar( AbstractPO po ) throws BackendException;

	void excluir( AbstractPO po ) throws BackendException;

	@SuppressWarnings( "rawtypes" )
	List filtrar( AbstractPO po ) throws BackendException;

	AbstractPO filtrarPorID( String ID ) throws BackendException;

}
