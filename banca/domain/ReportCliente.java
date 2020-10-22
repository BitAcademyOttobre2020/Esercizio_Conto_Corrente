package banca.domain;

public class ReportCliente {
	private String name;
	private int nConti;
	private double sumSaldi;
	
	
	public ReportCliente() {}
	public ReportCliente(String name, int nConti, double sumSaldi) {
		this.name = name;
		this.nConti = nConti;
		this.sumSaldi = sumSaldi;
	}



	public String toString(){
		return new String("nome: "+name+" nConti: "+nConti+" somma saldi: "+sumSaldi);
	}
}
