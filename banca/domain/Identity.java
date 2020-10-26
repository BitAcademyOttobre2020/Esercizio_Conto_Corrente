package banca.domain;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Identity {

	private double sum;
	private int numeroImpiegati;
	private double stipendioMmin = Double.MAX_VALUE;
	private double stipendioFmax;
	private List<Impiegato> listaMaschiMinori = new ArrayList<Impiegato>();

	public Identity combina(Impiegato emp) {
		sum += emp.getStipendio();
		numeroImpiegati++;

		if (emp.isFemale()) {
			if (emp.getStipendio() > stipendioFmax) {
				stipendioFmax = emp.getStipendio();
			}
		} else {
			if(Period.between(emp.getBirth(), LocalDate.now()).getYears()<25) {
				listaMaschiMinori.add(emp);
			}
			if (emp.getStipendio() < stipendioMmin) {
		
				stipendioMmin = emp.getStipendio();
			}
		}
		return this;
	}
	
	public List<Impiegato> getMaschiMinori(){
		return listaMaschiMinori;
	}
	
	public boolean stipendioDonneMinore() {
		return stipendioFmax<stipendioMmin;
	}

	public double getStipendioMedio() {
		return sum / numeroImpiegati;
	}

	public double getSum() {
		return sum;
	}

	public Identity combina(Identity other) {
		this.sum += other.sum;
		this.numeroImpiegati += other.numeroImpiegati;
		return this;
	}

}
