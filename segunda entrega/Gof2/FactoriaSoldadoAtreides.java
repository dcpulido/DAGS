
public class FactoriaSoldadoAtreides implements FactoriaSoldados {
	@Override
	public Soldado crearSoldado(String clase){
		if(clase=="artillero") return new ArtilleroAtreides();
		if(clase=="zapador") return new ZapadorAtreides();
		return null;
	}
}