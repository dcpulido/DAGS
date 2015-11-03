
import java.util.*;
import java.util.LinkedList;
import java.util.List;

public abstract class Jugador {
	protected FactoriaSoldados factor;
	protected List<Soldado> artilleros = new LinkedList<Soldado>();
	protected List<Soldado> zapadores = new LinkedList<Soldado>();
	protected String tipo;
	public Jugador(String tipo){
		this.tipo = tipo;
		if(tipo=="Atreides") factor=new FactoriaSoldadoAtreides(); 
		if(tipo=="Harkonen") factor=new FactoriaSoldadoHarkonen();
		
		//creamos el ejercito inicial (todos de la misma raza, no se pueden mezclar!)
		
		//[PUNTO DE REFACTORIZACION]
		artilleros.add(factor.crearSoldado("artillero"));
		artilleros.add(factor.crearSoldado("artillero"));
		zapadores.add(factor.crearSoldado("zapador"));
		zapadores.add(factor.crearSoldado("zapador"));
			
	}
	public abstract void jugar();
}
