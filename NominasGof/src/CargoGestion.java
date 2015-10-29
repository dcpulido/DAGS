
public class CargoGestion extends Extras {
	private boolean bl;
	private Nomina n;
	public CargoGestion(Nomina nom,boolean i){
		bl=i;
		n=nom;
	}
	public  int getNomina() {
		if(bl==true)return n.getNomina()+15;
		else return n.getNomina();
	}
}
