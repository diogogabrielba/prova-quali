package testes;

import org.junit.Test;

import br.com.prova.manager.exceptions.BackendException;
import br.com.prova.prova.controller.AutorizacaoFACADE;
import br.com.prova.prova.model.AutorizacaoPO;

public class TestaBackEndAutorizacao{

	@Test
	public void executar() {
		try {
			AutorizacaoFACADE facade = new AutorizacaoFACADE();
			AutorizacaoPO encontrado = new AutorizacaoPO();

			encontrado = facade.filtrarPorID( String.valueOf( 1 ) );

			System.out.println( encontrado );

		} catch ( BackendException e ) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
