
public class Quinquenio extends Extras {
	private int años;
	private Nomina n;
	public Quinquenio(Nomina nom,int años){
		this.años=años;
		n=nom;
	}
	public  int getNomina() {
		int veces=años/5;
		return n.getNomina()+veces*20;
	}
}

