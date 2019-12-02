package br.com.prova.prova.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.prova.manager.connection.HibernateConnection;
import br.com.prova.manager.exceptions.BackendException;
import br.com.prova.prova.model.AutorizacaoPO;

public final class AutorizacaoDAO{

	public void inserir( HibernateConnection hibernateConnection, final AutorizacaoPO PO ) throws BackendException {
		try {

			/** Invocando o método inserir do nosso componente de HibernateConnection */
			AutorizacaoPO persistido = (AutorizacaoPO) hibernateConnection.inserir( PO );

			/** Pegando o ID do aluno Recem inserido */
			PO.setId( persistido.getId() );

		} catch ( BackendException e ) {

			throw e;
		} catch ( Throwable t ) {

			throw new BackendException( "Erro ao inserir ", t );
		}

	}

	public void alterar( HibernateConnection hibernateConnection, final AutorizacaoPO PO ) throws BackendException {
		try {

			/** Invocando o método alterar do nosso componente de HibernateConnection */
			hibernateConnection.alterar( PO );

		} catch ( BackendException e ) {

			throw e;
		} catch ( Throwable t ) {

			throw new BackendException( "Erro ao alterar ", t );
		}

	}

	public void excluir( HibernateConnection hibernateConnection, final AutorizacaoPO PO ) throws BackendException {
		try {

			/** Invocando o método excluir do nosso componente de HibernateConnection */
			hibernateConnection.excluir( PO );

		} catch ( BackendException e ) {

			throw e;
		} catch ( Throwable t ) {

			throw new BackendException( "Erro ao excluir ", t );
		}

	}

	public AutorizacaoPO filtrarPorId( Long id ) throws BackendException {
		try {

			/**
			 * Invocando o método filtrarPorId do nosso componente de HibernateConnection
			 */
			return (AutorizacaoPO) new HibernateConnection().filtrarPorId( id, AutorizacaoPO.class );
		} catch ( BackendException e ) {

			throw e;
		} catch ( Throwable t ) {

			throw new BackendException( "Erro ao inserir ", t );
		}

	}

	@SuppressWarnings( { "rawtypes", "unchecked" } )
	public List< AutorizacaoPO > filtrar( AutorizacaoPO poFiltrar ) throws BackendException {

		HibernateConnection hibernateConnection = new HibernateConnection();

		try {
			hibernateConnection.iniciarTransacao();

			/**
			 * Criando a Criteria com base na CriteriaBuilder -> biblioteca que Faz select
			 */
			CriteriaBuilder builder = hibernateConnection.getSessaoCorrente().getCriteriaBuilder();
			CriteriaQuery criteria = builder.createQuery( AutorizacaoPO.class );

			// Definindo o from
			Root root = criteria.from( AutorizacaoPO.class );

			// Deixando a criteria preparada para a consulta
			criteria.select( root );

			// Definindo os parametros do WHERE
			ArrayList< Predicate > predicados = new ArrayList<>();

			if ( ( poFiltrar.getPermitido() != null ) && !poFiltrar.getPermitido().isEmpty() ) {

				Predicate perimitidoParam = builder.like( root.get( "permitido" ), poFiltrar.getPermitido().concat( "%" ) );
				predicados.add( perimitidoParam );
			}

			if ( ( poFiltrar.getIdade() != null ) && !poFiltrar.getIdade().isEmpty() ) {

				Predicate idadeParam = builder.like( root.get( "idade" ), poFiltrar.getIdade().concat( "%" ) );
				predicados.add( idadeParam );
			}

			if ( ( poFiltrar.getProcedimento() != null ) && !poFiltrar.getProcedimento().isEmpty() ) {

				Predicate procedimentoParam = builder.like( root.get( "procedimento" ), poFiltrar.getProcedimento().concat( "%" ) );
				predicados.add( procedimentoParam );
			}

			if ( ( poFiltrar.getSexo() != null ) && !poFiltrar.getSexo().isEmpty() ) {

				Predicate sexoParam = builder.like( root.get( "sexo" ), poFiltrar.getSexo().concat( "%" ) );
				predicados.add( sexoParam );
			}

			// Adicionando o Predicado no WHERE
			criteria.where( predicados.toArray( new Predicate[ predicados.size() ] ) );

			// Executando..

			List< AutorizacaoPO > encontrados = hibernateConnection.getSessaoCorrente().createQuery( criteria ).getResultList();

			hibernateConnection.commitTransacao();
			return encontrados;

		} catch ( BackendException e ) {
			hibernateConnection.rollbackTransacao();
			throw e;
		} catch ( Throwable t ) {
			hibernateConnection.rollbackTransacao();
			throw new BackendException( "Erro ao filtrar ", t );
		}
	}

	public String validar( String procedimento, String idade, String sexo ) throws BackendException {

		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();

			String hql = "SELECT autorizacao.permitido FROM AutorizacaoPO autorizacao WHERE autorizacao.procedimento = :procedimentoParam AND autorizacao.idade = :idadeParam AND autorizacao.sexo = :sexoParam";
			Query query = hibernate.getSessaoCorrente().createQuery( hql );
			query.setParameter( "procedimentoParam", procedimento );
			query.setParameter( "idadeParam", idade );
			query.setParameter( "sexoParam", sexo );

			String permitido = (String) query.getSingleResult();

			hibernate.commitTransacao();

			return permitido;
		} catch ( javax.persistence.NoResultException e ) {
			hibernate.rollbackTransacao();
			return null;
		} catch ( Throwable e ) {
			hibernate.rollbackTransacao();
			throw new BackendException( "Erro ao validar!", e );
		}
	}
}
