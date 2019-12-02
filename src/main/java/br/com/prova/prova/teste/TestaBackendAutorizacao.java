package br.com.prova.prova.teste;

import java.util.ArrayList;

import br.com.prova.manager.exceptions.BackendException;
import br.com.prova.prova.controller.AutorizacaoFACADE;
import br.com.prova.prova.model.AutorizacaoPO;

public class TestaBackendAutorizacao {

	public static void main(String[] args) {
		try {
			AutorizacaoFACADE facade = new AutorizacaoFACADE();
			AutorizacaoPO encontrado = new AutorizacaoPO();
			encontrado = facade.filtrarPorID("1");
			
			System.out.println(encontrado);
			
			ArrayList<AutorizacaoPO> encontrados = (ArrayList<AutorizacaoPO>)facade.filtrar(null);
			
			System.out.println(encontrados);
		} catch (BackendException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
