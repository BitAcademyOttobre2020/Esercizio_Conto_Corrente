package banca.domain;

import java.time.LocalDate;

public class Impiegato {
	
	private String nome;
	private String cognome;
	private double stipendio;
	private LocalDate birth;
	private Sesso sesso;
	
	public Impiegato(String nome, String cognome, double stipendio, LocalDate birth, Sesso sesso) {
		this.nome = nome;
		this.cognome = cognome;
		this.stipendio = stipendio;
		this.birth = birth;
		this.sesso = sesso;
	}

	public double getStipendio() {
		return stipendio;
	}

	public LocalDate getBirth() {
		return birth;
	}

	public Sesso getSesso() {
		return sesso;
	}
	
	public boolean isFemale() {
		return this.sesso == Sesso.FEMMINA;
	}

	@Override
	public String toString() {
		return "Impiegato [nome=" + nome + ", cognome=" + cognome + ", stipendio=" + stipendio + ", birth=" + birth
				+ ", sesso=" + sesso + "]";
	}
	
	

//	-caricare impiegati da fileSystem
//	- somma gli stipendi
//	- valor medio
//	- mediana
//	- è vero che lo stipendio più basso degli uomni + + alto di quello + basso delle donne
//	-lista di tutti gi impiegati maschi minori di 25 anni
//	
//	
//	- calcolare tutto con 1 operazione stream unica
	
	
}
