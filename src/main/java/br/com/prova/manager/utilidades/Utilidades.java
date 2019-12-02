package br.com.prova.manager.utilidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Utilidades{

	public static final String SCHEMA = "prova";

	public static final LocalDate parseLocalDate( String data ) {

		if ( data == null || data.isEmpty() || !data.contains( "/" ) ) {
			return null;
		}

		LocalDate novaData = LocalDate.parse( data, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ).withResolverStyle( ResolverStyle.LENIENT ) );
		return novaData;
	}

	public static final String parseLocalDate( LocalDate data ) {

		if ( data == null ) {
			return null;
		}

		String novaData = data.format( DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) );
		return novaData;
	}

	public static final BigDecimal parseBigDecimal( String valor ) {

		if ( valor == null || valor.isEmpty() ) {
			return null;
		}

		BigDecimal novoValor = new BigDecimal( valor.replace( ".", "" ).replace( ",", "." ).trim() );
		return novoValor;

	}

	public static final String parseBigDecimal( BigDecimal valor ) {

		if ( valor == null ) {
			return null;
		}

		return valor.toString();
	}

	public static final String normalizeString( String texto ) {
		if ( ( texto == null ) || texto.isEmpty() ) {
			return null;
		}

		texto = texto.replaceAll( "[^a-zA-Z0-9·‡‚„ÈËÍÌÔÛÙıˆ˙ÁÒ¡¿¬√…»Õœ”‘’÷⁄«— ][.,!?:...]", "" );
		String padrao = "\\s{2,}";// caso tenha 2 espaÁos ou mais
		Pattern regPat = Pattern.compile( padrao );
		Matcher matcher = regPat.matcher( texto );
		texto = matcher.replaceAll( " " ).trim();
		return texto;
	}
}
