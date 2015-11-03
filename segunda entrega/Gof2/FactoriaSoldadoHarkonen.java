
public class FactoriaSoldadoHarkonen implements FactoriaSoldados {
	public Soldado crearSoldado(String clase){
		if(clase=="artillero") return new ArtilleroHarkonen();
		if(clase=="zapador") return new ZapadorHarkonen();
		return null;
	}
}