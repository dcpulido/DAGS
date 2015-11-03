import java.util.*;
import java.util.LinkedList;
import java.util.List;
public class JugadorMaquina extends Jugador {
	
	public JugadorMaquina(String tipo) {
		super(tipo);
	}

	@Override
	public void jugar() {
		//disparamos!
		for (Soldado artillero : artilleros){
			((Artillero)artillero).disparar();
		}
		
		//ponemos bombas!
		for (Soldado zapador: zapadores){
			((Zapador)zapador).ponerBomba();
		}

	}

}
