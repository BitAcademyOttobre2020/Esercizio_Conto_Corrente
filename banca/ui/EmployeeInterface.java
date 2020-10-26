package banca.ui;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import banca.domain.Banca;
import banca.domain.Identity;
import banca.domain.Impiegato;
import banca.domain.Sesso;

public class EmployeeInterface {

	public static void main(String[] args) {

		Banca b = Banca.getInstance();

		List<Impiegato> lEmp = new ArrayList<Impiegato>();
//		b.getImpiegato().forEach(emp->lEmp.add(emp));
		b.getImpiegato().forEach(lEmp::add);

		// - somma gli stipendi
		double sommaStipendi = lEmp.stream().mapToDouble(emp -> emp.getStipendio()).sum();

		// - valor medio
		OptionalDouble mediaOptional = lEmp.stream().mapToDouble(emp -> emp.getStipendio()).average();
		double mediaStipendi = mediaOptional.orElse(0);

		// - mediana
		OptionalDouble medianaOptional = lEmp.stream()
				.sorted((e1, e2) -> (int) (Math.signum(e1.getStipendio() - e2.getStipendio())))
				.skip(Math.max(0, ((lEmp.size() + 1) / 2) - 1)).limit(1 + (1 + lEmp.size()) % 2)
				.mapToDouble(emp -> emp.getStipendio()).average();
		double medianaStipendi = medianaOptional.orElse(0);

		// - è vero che lo stipendio più basso degli uomni + + alto di quello + alto
		// delle donne
		boolean dirittiDonne = lEmp.stream()
				.sorted((e1, e2) -> (int) (Math.signum(e1.getStipendio() - e2.getStipendio())))
				.dropWhile(e->e.getSesso()==Sesso.FEMMINA)
				.anyMatch(Impiegato::isFemale);
		
		//-lista di tutti gi impiegati maschi minori di 25 anni
		List<Impiegato> minoriVC = lEmp.stream()
				.filter(e->Period.between(e.getBirth(), LocalDate.now()).getYears()<25)
				.collect(Collectors.toList());
		
		Identity x = lEmp.stream()
						.reduce(new Identity(), (id,emp)->id.combina(emp), (id1,id2)->id1.combina(id2));
	}

}

//	
//	
//- calcolare tutto con 1 operazione stream unica