import java.util.*;
import java.io.*;
//Entrega 4 de DAGGS 
///David Coello Pulido
///Daniel Rodriguez Cacheiro

///MAIN
class Framework {
	public static void main(String [] args)throws FileNotFoundException{
		Control ejecuta=new Control();
		ejecuta.Ejecuta();
	}
}
class Control implements Observable{
	private List<Observer> observers;
	private List<Operacion> operaciones;
	private Reader red;
	private Writer wrt;
	private Writer wrtXml;
	private Calendar fecha;
	private logging msg;
	public Control()throws FileNotFoundException{
		fecha=Calendar.getInstance();
		red=new ReaderConsola();
		wrt=new WriterConsola();

		operaciones=new ArrayList<Operacion>();
		Operacion suma=new Suma();
		Operacion division=new Division();
		operaciones.add(suma);
		operaciones.add(division);
		observers=new ArrayList<Observer>();
		Observer mon=new Monitoreo();
		observers.add(mon);

	}
	public void Ejecuta()throws FileNotFoundException{
		List<String> recoMsgs=new ArrayList<String>();
		msg=new logger("INFO",5,"inicio "+getHora());
		notifyObservers(msg.toString());
		recoMsgs.add(msg.toString());
		
		Scanner in=new Scanner(System.in);
		System.out.print(wrt.write(getArgs()));
		int op=Integer.parseInt(in.next())-1;

		red.readOperandos();

		operaciones.get(op).setOperandos(red.getOp1(),red.getOp2());
		System.out.println(operaciones.get(op).toString());
		

		msg=new logger("INFO",5,"Finaliza "+getHora());
		notifyObservers(msg.toString());
		recoMsgs.add(msg.toString());
		wrtXml=new WriterArchivoXml("salida");
		wrtXml.write(recoMsgs);
		toWriteaObservers();
	}
	private String getHora(){
		String toNotify="";
		toNotify+=fecha.get(Calendar.HOUR_OF_DAY)+"h ";
		toNotify+=fecha.get(Calendar.MINUTE)+"m ";
		toNotify+=fecha.get(Calendar.SECOND)+"s ";
		toNotify+=fecha.get(Calendar.MILLISECOND)+"ms";
		return toNotify;
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
class WriterArchivoXml implements Writer{
	private PrintStream out;
	private File f;
	private int cont;
	public WriterArchivoXml(String name) throws FileNotFoundException{
		f=new File(name+".out");
		out=new PrintStream(f.getName());
		cont=0;
	}
	public String write(List<String> args){
		for(String st:args){
			parse(st);
		}
		close();
		return "archivo guardado";
	}
	private void parse(String str){
		if (cont==0){
			out.println("<loggers>");
			cont++;
		}
		out.println("<log>\n<msg>"+str+"</msg>\n</log>");
	}
	private void close(){
		out.println("\n</loggers>");
		out.close();
	}
}
////OPERACIONES
///interfaces
interface Operacion{
	public void setOperandos(int a,int b);
	public float opera();
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
	public float opera(){
		return op1+op2;
	}
	public String toString(){
		return "suma: "+ opera();
	}
	public String getNombre(){
		return "suma";
	}
}
class Division implements Operacion{
	private int op1;
	private int op2;
	public Division(){
		op1=0;
		op2=1;
	}
	public Division(int a,int b){
		op1=a;
		if(b==0)b=1;
		op2=b;
	}
	public void setOperandos(int a,int b){
		op1=a;
		if(b==0)b=1;
		op2=b;
	}
	public float opera(){
		return op1/op2;
	}
	public String toString(){
		return "divide: "+ String.valueOf(opera());
	}
	public String getNombre(){
		return "divide";
	}
}
///LOGGING
///interfgace
interface logging{
	public int getPriority();
	public void setPriority(int prioridad);
	public String getType();
	public void setType(String tipo);
	public String getMessage();
	public void setMessage(String mensaje);
	public String toString();
}
///Implementaciones
class logger implements logging{
	private int prio;
	private String mess;
	private String tipo;
	public logger(String tipo,int prioridad,String mensaje){
		this.tipo=tipo;
		setPriority(prioridad);
		mess=mensaje;	
	}
	public int getPriority(){
		return prio;
	}
	public void setPriority(int prioridad){
		if(tipo=="INFO"){
			if(prioridad>7){
				prioridad = 7;
				prio=prioridad;
			}
			else{
				if(prioridad<4){
					prioridad = 4;
					prio=prioridad;
				}
				else prio=prioridad;
			}
		}
		if(tipo=="DEBUG"){
			if(prioridad>3){
				prioridad = 3;
				prio=prioridad;
			}
			else{
				prio=prioridad;
			}
		}
		if(tipo=="ERROR"){
			if(prioridad<8){
				prioridad = 8;
				prio=prioridad;
			}
			else{
				prio=prioridad;
			}
		}
		
	}
	public String getType(){
		return tipo;
	}
	public void setType(String tipo){
		this.tipo=tipo;
		setPriority(prio);
	}
	public String getMessage(){
		return mess;
	}
	public void setMessage(String mensaje){
		mess=mensaje;
	}
	public String toString(){
		String toret="tipo "+ tipo+"\n";
		toret+="Prioridad "+ prio+"\n";
		toret+="Mensaje "+ mess+"\n";
		return toret;
	}
}
