import java.io.FileNotFoundException;


public class Main {
	public static void main(String [] args) throws FileNotFoundException{
		if(args.length==0){
			System.out.println("Introducir nombre del archivo");
		}
		Reader red=new Reader(args[0]);
		Empleado[] em=Main.llenarLista(red);
		System.out.print(em[0].toString());
	}
	public static String[] parser(String in){
		String[] toret=in.split("\t");
		return toret;
	}
	public static Empleado generaEmpleado(String in){
		String[] atrib=parser(in);
		boolean flag;
		if(atrib[3]=="SI")flag=true;
		else flag=false;
		Empleado toret=new Empleado(atrib[0],atrib[1],Integer.parseInt(atrib[2]),flag);
		return toret;
	}
	public static  Empleado[] llenarLista(Reader red){
		Empleado[] toret = null;
		String med = "";
		int cont=0;
		while(med!=null){
			med = red.getLine();
			if(med!=null){
				toret[cont]=generaEmpleado(med);
			}
		}
		red.getFile();
		return toret;
	}
	public int calculaNomina(Empleado e){
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
	public Nomina cebolla(Nomina tipo,Empleado e){
		return new CargoGestion(new Sexenio(new Quinquenio(new Trienio(tipo,e.getAntiguedad()),e.getAntiguedad()),e.getAntiguedad()),e.getCargo());
	}
	
}
