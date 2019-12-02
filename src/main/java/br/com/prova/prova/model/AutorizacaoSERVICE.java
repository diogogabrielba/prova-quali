package br.com.prova.prova.model;

import java.util.List;

import br.com.prova.manager.abstracts.AbstractPO;
import br.com.prova.manager.connection.HibernateConnection;
import br.com.prova.manager.exceptions.BackendException;
import br.com.prova.manager.interfaces.Crud;
import br.com.prova.manager.utilidades.Utilidades;
import br.com.prova.prova.dao.AutorizacaoDAO;

public final class AutorizacaoSERVICE implements Crud{

	private final AutorizacaoDAO DAO;

	public AutorizacaoSERVICE(){
		this.DAO = new AutorizacaoDAO();
	}

	@Override
	public void inserir( final AbstractPO PO ) throws BackendException {
		HibernateConnection hibernateConnection = new HibernateConnection();

		try {
			hibernateConnection.iniciarTransacao();
			if ( PO == null ) {
				throw new BackendException( "Objeto nulo passado como parâmetro!" );
			}

			AutorizacaoPO autorizacao = null;

			if ( PO instanceof AutorizacaoPO ) {
				autorizacao = (AutorizacaoPO) PO;
			}

			if ( autorizacao == null ) {
				throw new BackendException( "Objeto nulo passado como parâmetro!" );
			}

			/** Normalizando as Strings para persistência */
			autorizacao.setSexo( autorizacao.getSexo().toUpperCase() );
			autorizacao.setPermitido( autorizacao.getPermitido().toUpperCase() );

			/** Passando o PO para a Camada de Persistencia */

			this.DAO.inserir( hibernateConnection, autorizacao );
			hibernateConnection.commitTransacao();
		} catch ( BackendException e ) {
			hibernateConnection.rollbackTransacao();
			throw e;
		} catch ( Throwable t ) {
			hibernateConnection.rollbackTransacao();
			throw new BackendException( "Erro ao inserir ", t );
		}

	}

	@Override
	public void alterar( final AbstractPO PO ) throws BackendException {
		HibernateConnection hibernateConnection = new HibernateConnection();

		try {
			hibernateConnection.iniciarTransacao();
			if ( PO == null ) {
				throw new BackendException( "Objeto nulo passado como parâmetro!" );
			}

			AutorizacaoPO autorizacao = null;

			if ( PO instanceof AutorizacaoPO ) {
				autorizacao = (AutorizacaoPO) PO;
			}

			if ( autorizacao == null ) {
				throw new BackendException( "Objeto nulo passado como parâmetro!" );
			}

			/** Normalizando as Strings para persistência */
			autorizacao.setSexo( autorizacao.getSexo().toUpperCase() );
			autorizacao.setPermitido( autorizacao.getPermitido().toUpperCase() );

			/** Passando o PO para a Camada de Persistencia */
			this.DAO.alterar( hibernateConnection, autorizacao );
			hibernateConnection.commitTransacao();
		} catch ( BackendException e ) {
			hibernateConnection.rollbackTransacao();
			throw e;
		} catch ( Throwable t ) {
			hibernateConnection.rollbackTransacao();
			throw new BackendException( "Erro ao alterar ", t );
		}

	}

	@Override
	public void excluir( final AbstractPO PO ) throws BackendException {
		HibernateConnection hibernateConnection = new HibernateConnection();

		try {
			hibernateConnection.iniciarTransacao();
			if ( PO == null ) {
				throw new BackendException( "Objeto nulo passado como parâmetro!" );
			}

			AutorizacaoPO autorizacao = null;

			if ( PO instanceof AutorizacaoPO ) {
				autorizacao = (AutorizacaoPO) PO;
			}

			if ( autorizacao == null ) {
				throw new BackendException( "Objeto nulo passado como parâmetro!" );
			}

			/** Passando o PO para a Camada de Persistencia */

			this.DAO.excluir( hibernateConnection, autorizacao );
			hibernateConnection.commitTransacao();
		} catch ( BackendException e ) {
			hibernateConnection.rollbackTransacao();
			throw e;
		} catch ( Throwable t ) {
			hibernateConnection.rollbackTransacao();
			throw new BackendException( "Erro ao excluir ", t );
		}

	}

	@Override
	public AbstractPO filtrarPorID( String ID ) throws BackendException {

		try {
			if ( ( ID == null ) || Utilidades.normalizeString( ID ).isEmpty() ) {
				throw new BackendException( "ID não informado!" );
			}

			/** Passando o PO para a Camada de Persistencia */

			return this.DAO.filtrarPorId( Long.valueOf( ID ) );

		} catch ( BackendException e ) {

			throw e;
		} catch ( Throwable t ) {

			throw new BackendException( "Erro ao filtrarPorID ", t );
		}
	}

	@Override
	public List< AutorizacaoPO > filtrar( final AbstractPO PO ) throws BackendException {

		try {
			AutorizacaoPO autorizacao = null;

			if ( PO == null ) {
				autorizacao = new AutorizacaoPO();
			} else if ( PO instanceof AutorizacaoPO ) {
				autorizacao = (AutorizacaoPO) PO;
			}

			/** Passando o PO para a Camada de Persistencia */

			return this.DAO.filtrar( autorizacao );

		} catch ( BackendException e ) {

			throw e;
		} catch ( Throwable t ) {

			throw new BackendException( "Erro ao filtrar ", t );
		}

	}

	public String validar( String procedimento, String idade, String sexo ) throws BackendException {

		try {
			if ( ( procedimento == null ) || Utilidades.normalizeString( procedimento ).isEmpty() ) {
				throw new BackendException( "Procedimento não informado!" );
			}

			if ( ( idade == null ) || Utilidades.normalizeString( idade ).isEmpty() ) {
				throw new BackendException( "Idade não informada!" );
			}

			if ( ( sexo == null ) || Utilidades.normalizeString( sexo ).isEmpty() ) {
				throw new BackendException( "Sexo não informado!" );
			}

			/** Passando o PO para a Camada de Persistencia */
			String permitido = this.DAO.validar( procedimento, idade, sexo );

			return permitido == null ? "NÃO" : permitido;

		} catch ( BackendException e ) {

			throw e;
		} catch ( Throwable t ) {

			throw new BackendException( "Erro ao filtrarPorID ", t );
		}
	}

}
