package banca.ui;

import java.util.Scanner;

import banca.domain.Banca;
import banca.domain.Cliente;
import banca.domain.ContoCorrente;
import banca.domain.exception.SaldoInsufficenteException;

public class BankInterface {

	public static void main(String[] args) {
			
		Banca b = Banca.getInstance();
		Scanner sc = new Scanner(System.in);
		
		//stampiamo tutti i conti clienti 
		Iterable<Cliente> ic = b.getClienti();
		
		for(Cliente c : ic) {
			System.out.println(c);
		}
		
		//
		System.out.println("Cosa vuoi fare: \n 1.preleva, \n 2.deposita, \n 3.bonifico");
		
	
		
		
		switch (sc.nextInt()) {
		case 1: {
			System.out.println("Quanto vuoi prelevare?");
			int amount = sc.nextInt();
			
		}
		case 2: {
			System.out.println("Quanto vuoi depositare?");
			int amount = sc.nextInt();
			
		}
		case 3: {
			System.out.println("Quanto vuoi bonificare?");

		}

		default:
			throw new IllegalArgumentException("Unexpected value: " + n);
		}
		
		
		
		
		
		b.deposita(100, 1, 1);
		
		for(Cliente c : ic) {
			System.out.println(c);
		}
		
		try {
			b.bonifica(1000, 36, 2, 3, 1);
		} catch (SaldoInsufficenteException e) {
			System.out.println(e.getMessage());
		}
		
		for(Cliente c : ic) {
			System.out.println(c);
		}
	}

}
