package br.com.prova.prova.view;

import br.com.prova.manager.exceptions.BackendException;
import br.com.prova.prova.controller.AutorizacaoFACADE;
import br.com.prova.prova.model.AutorizacaoPO;

public class Autorizador{

	public static void main( String[ ] args ) {
		try {

			if ( args.length == 0 ) {
				System.out.println( "--------------------------------" );
				System.out.println( "Parâmetros insuficientes para o uso! " );
				System.out.println( "--------------------------------" );

				System.exit( 0 );
			}

			String acao = args[ 0 ];

			if ( acao.equalsIgnoreCase( "Cadastrar" ) ) {

				try {
					if ( args.length < 5 ) {
						System.out.println( "--------------------------------" );
						System.out.println( "Parâmetros insuficientes para a pesquisa! " );
						System.out.println( "Por favor preencha o procedimento, idade, sexo e a permissão. " );
						System.out.println( "--------------------------------" );
						System.exit( 0 );
					}

					AutorizacaoPO po = new AutorizacaoPO();
					po.setProcedimento( args[ 1 ] );
					po.setIdade( args[ 2 ] );
					po.setSexo( args[ 3 ] );
					po.setPermitido( args[ 4 ] );

					AutorizacaoFACADE facade = new AutorizacaoFACADE();
					facade.inserir( po );

					System.out.println( "--------------------------------" );
					System.out.println( "Autorizacao cadastrada com sucesso!" );
					System.out.println( "--------------------------------" );
				} catch ( BackendException e ) {
					System.out.println( "--------------------------------" );
					System.out.println( e.getMessage() );
					System.out.println( "--------------------------------" );
				}
			} else if ( acao.equalsIgnoreCase( "Validar" ) ) {

				if ( args.length < 4 ) {
					System.out.println( "--------------------------------" );
					System.out.println( "Parâmetros insuficientes para a pesquisa! " );
					System.out.println( "Por favor preencha o procedimento, idade e o sexo. " );
					System.out.println( "--------------------------------" );
					System.exit( 0 );
				}

				AutorizacaoFACADE facade = new AutorizacaoFACADE();
				String permitido = facade.validar( args[ 1 ], args[ 2 ], args[ 3 ] );

				System.out.println( "--------------------------------" );
				System.out.println( "Permitido: ".concat( permitido ) );
				System.out.println( "--------------------------------" );
			} else {
				System.out.println( "--------------------------------" );
				System.out.println( "Ação [" + acao + "] inválida" );
				System.out.println( "--------------------------------" );
			}

		} catch ( BackendException e ) {
			e.printStackTrace();
		} finally {
			System.exit( 0 );
		}

	}

}
