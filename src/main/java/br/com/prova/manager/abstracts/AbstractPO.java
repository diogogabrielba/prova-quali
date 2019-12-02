package br.com.prova.manager.abstracts;

import javax.persistence.MappedSuperclass;

/**
 * 
 * Classe respons�vel por representar unifica��o das Classes POs
 * do sistema. Com ela podemos arquiteturar melhor nossa estrutura
 *
 * @MappedSuperclass: Informa ao Hibernate que esta classe n�o possui
 *                    Tabela, ela � apenas uma SuperClasse.
 * 
 *                    Ou Seja
 * 
 *                    Mapeia esta classe como apenas uma classe de Heran�a, onde todos
 *                    os atributos ser�o implementados nas tabelas que representam as
 *                    classes FILHAS
 *
 * @author Diogo Gabriel Barbosa <diogo.gabriel259@gmail.com.br>
 * @since 22 de abr de 2018 14:57:17
 * @version 1.0
 */

@MappedSuperclass
public abstract class AbstractPO{

	@Override
	public abstract int hashCode();

	@Override
	public abstract boolean equals( Object obj );

	@Override
	public abstract String toString();

}
