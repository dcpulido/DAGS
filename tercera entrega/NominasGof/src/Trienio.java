
public class Trienio extends Extras {
	private int años;
	private Nomina n;
	public Trienio(Nomina nom,int años){
		this.años=años;
		n=nom;
	}
	public  int getNomina() {
		int veces=años/3;
		return n.getNomina()+veces*10;
	}
}
