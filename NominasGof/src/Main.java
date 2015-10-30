import java.io.FileNotFoundException;
import java.util.*;


public class Main {
	public static void main(String [] args) throws FileNotFoundException{
		if(args.length==0){
			System.out.println("Introducir nombre del archivo");
		}
		Reader red=new Reader(args[0]);
		List<Empleado> em=new LinkedList<Empleado>();
		
		em.addAll(Main.llenarLista(red));
		for(Empleado k:em){
			System.out.print(k.toString()+"\n"+calculaNomina(k)+"\n");
		}		
	}
	public static String[] parser(String in){
		String[] toret=in.split("\t");
		return toret;
	}
	public static Empleado generaEmpleado(String in){
		String[] atrib=parser(in);
		Empleado toret=new Empleado(atrib[0],atrib[1],Integer.parseInt(atrib[2]),atrib[3]);
		return toret;
	}
	public static  List<Empleado> llenarLista(Reader red){
		List<Empleado> toret = new LinkedList<Empleado>();
		String med = "";
		while(med!=null){
			med = red.getLine();
			if(med!=null){
				toret.add(generaEmpleado(med));
			}
		}
		red.getFile();
		return toret;
	}
	public static int calculaNomina(Empleado e){
		String tipo = e.getEscala();
		Nomina nom=null;
		switch(tipo){
		case "A":
			nom= cebolla(new EscalaA(),e);
			break;
		 
		case "B":
			nom= cebolla(new EscalaB(),e);
			break;
		
		case "C":
			nom= cebolla(new EscalaC(),e);
			break;
		}
		return nom.getNomina();
	}
	public static Nomina cebolla(Nomina tipo,Empleado e){
		return new CargoGestion(new Sexenio(new Quinquenio(new Trienio(tipo,e.getAntiguedad()),e.getAntiguedad()),e.getAntiguedad()),e.getCargo());
	}
	
}
