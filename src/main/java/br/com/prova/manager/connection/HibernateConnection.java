package br.com.prova.manager.connection;
/**
 * Componente.
 * Classe criada a fim de se definir um componente de Conectividade dos nossos Softwares.
 *
 * Este componente será resposável por gerenciar as Sessoes, Transações, Commits e Rollbacks
 * Ele possuirá todos o métodos básicos de persistência e de consulta.
 * 
 *
 * @author Diogo Gabriel Barbosa <diogo.gabriel259@gmail.com.br>
 * @since 27 de mai de 2018 15:20:52
 * @version 1.0
 */

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import br.com.prova.manager.abstracts.AbstractPO;
import br.com.prova.manager.exceptions.BackendException;
import br.com.prova.manager.utilidades.Utilidades;

public final class HibernateConnection{

	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;
	private Session sessaoCorrente;
	private Transaction transacao;

	public SessionFactory getSessionFactory() {
		/** Usando Singleton */
		if ( sessionFactory == null ) {
			try {

				/**
				 * Criando um registry builder
				 * Usando para definir os property do hibernate.cfg.xml aqui no java.
				 * Ex: registryBuilder.applySetting(Environment.DRIVER, "com.mysql.jdbc.Driver");
				 */
				StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

				/** Criando o Registry */
				registry = registryBuilder.configure( "hibernate.cfg.xml" ).build();

				/**
				 * Criando o MetadataSource
				 * Usado para informar os mapeamentos dos POs caso queira fazer pelo Java
				 * Ex: sources.addAnnotatedClass(AbstractPO.class)
				 */
				MetadataSources sources = new MetadataSources( registry );

				/** Criando o MetadataBuilder */
				MetadataBuilder metadataBuilder = sources.getMetadataBuilder();
				/** Especificando o SCHEMA padrão que será usado para as tabelas. PS: Funciona apenas quando não informado explicitamente */
				metadataBuilder.applyImplicitSchemaName( Utilidades.SCHEMA );

				/** Criando o Metadata */
				Metadata metadata = metadataBuilder.build();

				/** Criando o SessionFactory */
				sessionFactory = metadata.getSessionFactoryBuilder().build();

			} catch ( Throwable t ) {
				t.printStackTrace();
				this.destroy();
			}
		}
		return sessionFactory;
	}

	public void destroy() {
		if ( registry != null ) {
			StandardServiceRegistryBuilder.destroy( registry );
		}
	}

	public Session getSessaoCorrente() throws BackendException {
		if ( this.sessaoCorrente == null ) {
			throw new BackendException( "Sessão não está aberta" );
		}
		return this.sessaoCorrente;
	}

	public void setSessaoCorrente( Session sessaoCorrente ) {
		this.sessaoCorrente = sessaoCorrente;
	}

	private void abrirSessao() {
		this.setSessaoCorrente( this.getSessionFactory().openSession() );
	}

	private void fecharSessao() throws BackendException {
		if ( this.sessaoCorrente == null ) {
			throw new BackendException( "Sessão não está aberta" );
		}
		this.getSessaoCorrente().close();
		this.setSessaoCorrente( null );
	}

	/**
	 * Metodos de Transação
	 * 
	 * @throws BackendException
	 */
	public void iniciarTransacao() throws BackendException {
		this.abrirSessao();
		this.transacao = this.getSessaoCorrente().beginTransaction();
	}

	public void commitTransacao() throws BackendException {
		try {
			if ( this.transacao == null ) {
				throw new BackendException( "Transação não inicializada" );
			}

			this.transacao.commit();
			this.fecharSessao();
			this.transacao = null;
		} catch ( javax.persistence.PersistenceException e ) {
			if ( e.getCause() != null ) {
				if ( e.getCause().getCause() != null ) {
					if ( e.getCause().getCause() instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ) {
						throw new BackendException( "Erro ao persistir! Registros já inseridos com os dados informados no banco", e );
					}
				}
			}

			//org.hibernate.exception.ConstraintViolationException
			// Tcom.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException
			throw new BackendException( "Erro ao inserir!", e );
		} catch ( Throwable t ) {
			throw new BackendException( "Erro desconhecido", t );
		}
	}

	public void rollbackTransacao() throws BackendException {
		try {
			if ( this.transacao == null ) {
				throw new BackendException( "Transação não inicializada" );
			}

			this.transacao.rollback();
			this.fecharSessao();
			this.transacao = null;
		} catch ( Throwable t ) {
			throw new BackendException( "Erro desconhecido", t );
		}
	}

	/** Métodos Base de PERSISTÊNCIA */
	public AbstractPO inserir( AbstractPO po ) throws BackendException {
		try {
			return (AbstractPO) this.getSessaoCorrente().merge( po );

		} catch ( javax.persistence.PersistenceException e ) {
			if ( e.getCause() != null ) {
				if ( e.getCause().getCause() != null ) {
					if ( e.getCause().getCause() instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ) {
						throw new BackendException( "Erro ao inserir! Registros já inseridos com os dados informados no banco", e );
					}
				}
			}

			//org.hibernate.exception.ConstraintViolationException
			// Tcom.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException
			throw new BackendException( "Erro ao inserir!", e );
		} catch ( Throwable t ) {
			throw new BackendException( "Erro ao inserir", t );
		}
	}

	public void alterar( AbstractPO po ) throws BackendException {
		try {
			this.getSessaoCorrente().merge( po );
		} catch ( javax.persistence.PersistenceException e ) {
			if ( e.getCause() != null ) {
				if ( e.getCause().getCause() != null ) {
					if ( e.getCause().getCause() instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ) {
						throw new BackendException( "Erro ao alterar! Registros já inseridos com os dados informados no banco", e );
					}
				}
			}

			//org.hibernate.exception.ConstraintViolationException
			// Tcom.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException
			throw new BackendException( "Erro ao alterar!", e );
		} catch ( Throwable t ) {
			throw new BackendException( "Erro ao Alterar", t );
		}
	}

	public void excluir( AbstractPO po ) throws BackendException {
		try {
			this.getSessaoCorrente().delete( po );
		} catch ( Throwable t ) {
			throw new BackendException( "Erro ao Excluir", t );
		}
	}

	@SuppressWarnings( { "rawtypes", "unchecked" } )
	public AbstractPO filtrarPorId( Long id, Class clazz ) throws BackendException {
		try {
			this.iniciarTransacao();
			/** Criando a Criteria com base na CriteriaBuilder -> biblioteca que Faz select */
			CriteriaBuilder builder = this.getSessaoCorrente().getCriteriaBuilder();
			CriteriaQuery criteria = builder.createQuery( clazz );

			// Definindo o from
			Root root = criteria.from( clazz );

			// Deixando a criteria preparada para a consulta
			criteria.select( root );

			// Definindo os parametros do WHERE
			Predicate idParam = builder.equal( root.get( "id" ), id );

			// Adicionando o Predicado no WHERE
			criteria.where( idParam );

			// Executando..

			Object encontrado = this.getSessaoCorrente().createQuery( criteria ).getSingleResult();

			this.commitTransacao();
			return (AbstractPO) encontrado;

		} catch ( Throwable t ) {
			this.rollbackTransacao();
			throw new BackendException( "Erro ao filtrar por id", t );
		}
	}

}
