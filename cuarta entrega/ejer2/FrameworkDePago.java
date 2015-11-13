import java.util.*;
import java.io.*;
import java.lang.Math;
public class FrameworkDePago{
	public static void main(String [] args)throws FileNotFoundException{
		if(args.length==0){
			System.out.println("di si es de pago o no true/false de argumento");
			return ;
		}
		LicenseManager lic=new LicenseManager(args[0]);
		boolean dePago=lic.checkIsFullVersion();
		if(dePago==false){
			Control ejecuta=new Control();
			ejecuta.Ejecuta();
		}
		else{
			ControlPago ejecuta2=new ControlPago();
			ejecuta2.Ejecuta();
		}
	}
	
}
class LicenseManager{
	private boolean is;
	public LicenseManager(String a){
		if(a.equals("true")){
			is=true;
		}
		else is=false;
	}
	public  boolean checkIsFullVersion(){
		return is;
	}
}
class ControlPago{
	private List<Observer> observers;
	private List<Operacion> operaciones;
	private Reader red;
	private Writer wrt;
	private Writer wrtXml;
	private Calendar fecha;
	private logging msg;
	public ControlPago()throws FileNotFoundException{
		fecha=Calendar.getInstance();
		red=new ReaderConsola();
		wrt=new WriterConsola();

		operaciones=new ArrayList<Operacion>();
		Operacion suma=new Suma();
		Operacion raiz=new Raiz();
		Operacion division=new Division();
		operaciones.add(suma);
		operaciones.add(division);
		operaciones.add(raiz);
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
class Raiz implements Operacion{
	private int op1;
	private int op2;
	public Raiz(){
		op1=1;
		op2=2;
	}
	public Raiz(int a){
		op1=a;
		op2=2;
	}
	public void setOperandos(int a,int b){
		op1=a;
		op2=b;
	}
	public float opera(){
		float k=(float)op2;
		double y= Math.pow(op1,1.0/k);
		k=(float)y;
		return k;
	}
	public String toString(){
		return "raiz: "+ opera();
	}
	public String getNombre(){
		return "raiz (op1 base op2 indice)";
	}
}
