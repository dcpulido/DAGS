
import java.util.*;
import java.util.LinkedList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JugadorHumano extends Jugador {	
	public JugadorHumano(String tipo) {
		super(tipo);
	}

	private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	@Override
	public void jugar() {
		System.out.println("Qué hago? [d=disparar, b=poner bombas, a=crear_artillero, z=crear_zapador");
		String line;
		try {
			line = in.readLine();
			if (line.startsWith("d")){
				for (Soldado a : artilleros){
					((Artillero)a).disparar();
				}
			}
			if (line.startsWith("b")){
				for (Soldado z : zapadores){
					((Zapador)z).ponerBomba();
				}
			}
			
			//[PUNTO DE REFACTORIZACION]
			if (line.startsWith("a")){
					Soldado a=factor.crearSoldado("artillero");
					this.artilleros.add(a);
			}	
			if (line.startsWith("z")){
					this.zapadores.add(factor.crearSoldado("zapador"));
			}
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		

	}

}
