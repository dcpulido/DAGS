
public class Empleado {
	private String nombre;
	private String escala;
	private int años;
	private boolean cargoGestion;
	public Empleado(String nom,String esc,int a,boolean cargo){
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
	public boolean getCargo(){
		return cargoGestion;
	}
}
