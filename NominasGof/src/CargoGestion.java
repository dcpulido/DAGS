
public class CargoGestion extends Extras {
	private boolean bl;
	private Nomina n;
	public CargoGestion(Nomina nom,String i){
		if(i=="SI")bl=true;
		else bl=false;
		n=nom;
	}
	public  int getNomina() {
		if(bl)return n.getNomina()+15;
		else return n.getNomina();
	}
}
