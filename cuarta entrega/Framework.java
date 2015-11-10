import java.util.*;

///MAIN
class Framework{
	public static void main(String [] args){
		Control ejecuta=new Control();
		ejecuta.Ejecuta();
	}
}
class Control{
	private List<Operacion> operaciones;
	private Reader red;
	private Writer wrt;
	public Control(){
		red=new ReaderConsola();
		wrt=new WriterConsola();
		operaciones=new ArrayList<Operacion>();
		Operacion suma=new Suma();
		operaciones.add(suma);
	}
	public void Ejecuta(){
		Scanner in=new Scanner(System.in);
		System.out.print(wrt.write(getArgs()));
		int op=Integer.parseInt(in.next())-1;
		red.readOperandos();
		operaciones.get(op).setOperandos(red.getOp1(),red.getOp2());
		System.out.println(operaciones.get(op).toString());
	}
	private List<String> getArgs(){
		List<String> toret=new ArrayList<String>();
		for(Operacion op:operaciones){
			toret.add(op.getNombre());
		}
		return toret;
	}
}
//READERS Y WRITERS
interface Reader{
	public int getOp1();
	public int getOp2();
	public void readOperandos();
}
interface Writer{
	public String write(List<String> args);
}
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
////OPERACIONES
interface Operacion{
	public void setOperandos(int a,int b);
	public int opera();
	public String toString();
	public String getNombre();
}
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

