package br.com.prova.prova.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.prova.manager.abstracts.AbstractPO;

@Entity
@Table( name = "autorizacoes", uniqueConstraints = @UniqueConstraint( columnNames = { "procedimento", "idade", "sexo" } ) )
public class AutorizacaoPO extends AbstractPO{

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;

	@Column( length = 4, nullable = false )
	private String procedimento;

	@Column( length = 3, nullable = false )
	private String idade;

	@Column( length = 1, nullable = false )
	private String sexo;

	@Column( length = 3, nullable = false )
	private String permitido;

	public Long getId() {
		return this.id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getProcedimento() {
		return this.procedimento;
	}

	public void setProcedimento( String procedimento ) {
		this.procedimento = procedimento;
	}

	public String getIdade() {
		return this.idade;
	}

	public void setIdade( String idade ) {
		this.idade = idade;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo( String sexo ) {
		this.sexo = sexo;
	}

	public String getPermitido() {
		return this.permitido;
	}

	public void setPermitido( String permitido ) {
		this.permitido = permitido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = ( prime * result ) + ( ( this.idade == null ) ? 0 : this.idade.hashCode() );
		result = ( prime * result ) + ( ( this.permitido == null ) ? 0 : this.permitido.hashCode() );
		result = ( prime * result ) + ( ( this.sexo == null ) ? 0 : this.sexo.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( this.getClass() != obj.getClass() ) {
			return false;
		}
		AutorizacaoPO other = (AutorizacaoPO) obj;
		if ( this.idade == null ) {
			if ( other.idade != null ) {
				return false;
			}
		} else if ( !this.idade.equals( other.idade ) ) {
			return false;
		}
		if ( this.permitido == null ) {
			if ( other.permitido != null ) {
				return false;
			}
		} else if ( !this.permitido.equals( other.permitido ) ) {
			return false;
		}
		if ( this.sexo == null ) {
			if ( other.sexo != null ) {
				return false;
			}
		} else if ( !this.sexo.equals( other.sexo ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "Autorizacao :\n\tid=" );
		builder.append( this.id );
		builder.append( "\n\t procedimento=" );
		builder.append( this.procedimento );
		builder.append( "\n\t idade=" );
		builder.append( this.idade );
		builder.append( "\n\t sexo=" );
		builder.append( this.sexo );
		builder.append( "\n\t permitido=" );
		builder.append( this.permitido );
		return builder.toString();
	}

}
