package banca.domain;

public class Identity {
			
			private double sum;
			private int numeroImpiegati;
			
			public Identity combina(Impiegato emp) {
				sum += emp.getStipendio();
				numeroImpiegati++;
				return this;
			}
			
			public double getStipendioMedio() {
				return sum/numeroImpiegati;
			}

			public double getSum() {
				return sum;
			}
			
			public Identity combina(Identity other) {
				this.sum+=other.sum;
				this.numeroImpiegati+=other.numeroImpiegati;
				return this;
			}
			
			
			
}
