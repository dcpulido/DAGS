import java.util.*;
//Entrega 4 de DAGGS 
///David Coello Pulido
///Daniel Rodriguez Cacheiro

///MAIN
class Framework{
	public static void main(String [] args){
		Control ejecuta=new Control();
		ejecuta.Ejecuta();
	}
}
class Control implements Observable{
	private List<Observer> observers;
	private List<Operacion> operaciones;
	private Reader red;
	private Writer wrt;
	public Control(){
		red=new ReaderConsola();
		wrt=new WriterConsola();
		operaciones=new ArrayList<Operacion>();
		Operacion suma=new Suma();
		operaciones.add(suma);
		observers=new ArrayList<Observer>();
		Observer mon=new Monitoreo();
		observers.add(mon);
	}
	public void Ejecuta(){
		notifyObservers("inicio");
		Scanner in=new Scanner(System.in);
		System.out.print(wrt.write(getArgs()));
		int op=Integer.parseInt(in.next())-1;
		red.readOperandos();
		operaciones.get(op).setOperandos(red.getOp1(),red.getOp2());
		System.out.println(operaciones.get(op).toString());
		notifyObservers("ejecucion terminada");
		toWriteaObservers();

	}
	public void addObserver(Observer ob){
		observers.add(ob);
	}
	public void notifyObservers(String str){
		for(Observer ob:observers){
			ob.update(str);
		}
	}
	private void toWriteaObservers(){
		for(Observer ob:observers){
			ob.toWrite();
		}
	}


	private List<String> getArgs(){
		List<String> toret=new ArrayList<String>();
		for(Operacion op:operaciones){
			toret.add(op.getNombre());
		}
		return toret;
	}
}
///OBSERVERS
///interfaces
interface Observable{
	public void addObserver(Observer ob);
	public void notifyObservers(String str);
}
interface Observer{
	public void update(String str);
	public void toWrite();
}
///implementaciones
class Monitoreo implements Observer{
	private List<String> strs;
	Writer wrt=new WriterMonitoreo();
	public Monitoreo(){
		strs=new ArrayList<String>();
	}
	public void update(String str){
		strs.add(str);
	}
	@Override
	public void toWrite(){
		wrt.write(strs);
	}
}
//READERS Y WRITERS
///interfaces
interface Reader{
	public int getOp1();
	public int getOp2();
	public void readOperandos();
}
interface Writer{
	public String write(List<String> args);
}
///implementaciones
///lector de operandos
class ReaderConsola implements Reader{
	private int arg1;
	private int arg2;
	public ReaderConsola(){}
	public ReaderConsola(String a,String b){
		arg1=Integer.parseInt(a);
		arg2=Integer.parseInt(b);
	}
	public void readOperandos(){
		Scanner in=new Scanner(System.in);
		System.out.print("Op1: ");
		arg1=Integer.parseInt(in.next());
		System.out.print("Op2: ");
		arg2=Integer.parseInt(in.next());
		in.close();
	}
	public int getOp1(){
		return arg1;
	}
	public int getOp2(){
		return arg2;
	}
}
///menu de operaciones
class WriterConsola implements Writer{
	public WriterConsola(){}
	public String write(List<String> args){
		String toret="";
		int cont=0;
		while(cont < args.size()){
			toret += cont+1+"_"+args.get(cont)+"\n";	
			cont++;
		}
		toret+="seleccion: ";
		return toret;
	}
}
class WriterMonitoreo implements Writer{
	public WriterMonitoreo(){}
	public String write(List<String> args){
		String toret="";
		int cont=0;
		while(cont < args.size()){
			toret +="_"+args.get(cont)+"\n";	
			cont++;
		}
		System.out.println(toret);
		return toret;
	}
}
////OPERACIONES
///interfaces
interface Operacion{
	public void setOperandos(int a,int b);
	public int opera();
	public String toString();
	public String getNombre();
}
///implementaciones
class Suma implements Operacion{
	private int op1;
	private int op2;
	public Suma(){
		op1=0;
		op2=0;
	}
	public Suma(int a,int b){
		op1=a;
		op2=b;
	}
	public void setOperandos(int a,int b){
		op1=a;
		op2=b;
	}
	public int opera(){
		return op1+op2;
	}
	public String toString(){
		return "suma: "+ opera();
	}
	public String getNombre(){
		return "suma";
	}
}

