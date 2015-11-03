
public class Sexenio extends Extras {
	private int años;
	private Nomina n;
	public Sexenio(Nomina nom,int años){
		this.años=años;
		n=nom;
	}
	public  int getNomina() {
		int veces=años/6;
		return n.getNomina()+veces*50;
	}
}

