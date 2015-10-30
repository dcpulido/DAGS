
public class Empleado {
	private String nombre;
	private String escala;
	private int años;
	private String cargoGestion;
	public Empleado(){}
	public Empleado(String nom,String esc,int a,String cargo){
		nombre=nom;
		escala=esc;
		años=a;
		cargoGestion=cargo;
	}
	public String getNombre(){
		return nombre;
	}
	public String getEscala(){
		return escala;
	}
	public int getAntiguedad(){
		return años;
	}
	public String getCargo(){
		return cargoGestion;
	}
	public String toString(){
		String toret="";
		toret="Nombre: "+nombre+"\nEscala: "+escala+"\nAntiguedad: "+años+"\nCargo: "+cargoGestion;
		return toret;
	}
}
