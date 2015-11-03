import java.util.*;

class Gof32{
	public static void main(String[] args)throws Exception{
		Programa a = new Programa("a");
		Programa  b= new Programa("b");
		Programa  c= new Programa("c");
		Programa  d= new Programa("d");
		Programa  e= new Programa("e");

		List<Programa> lP=new LinkedList<Programa>();
		lP.add(a);
		lP.add(b);
		lP.add(c);
		lP.add(d);
		lP.add(e);
		Observer m1=new Miron();
		List<Trabajo> lT=new LinkedList<Trabajo>();
		String ord[]= {"a","c","d"};
		Trabajo t1=new TrabajoSerie("1",ord,lT,lP);
		t1.addObserver(m1);
		t1.ejecutaTrabajo();
	}
}
interface Observer {
	public void update(String str);
}
interface Observable{
	public void addObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers(String str);
}
class Miron implements Observer{
	private int cont;
	public void update(String str){
		if(str=="inicio")cont++;
		else cont--;
		System.out.println(str+"En curso: "+cont);
	}
}
class Programa implements Runnable{
	private String tipo;
	public Programa(String tipo){
		this.tipo=tipo;
	}
	public void ejecutaPrograma(){
		this.run();
	}
	public String getName(){
		return tipo;
	}
	public void run(){
		try{ 
      		System.out.println("[INICIO] "+ tipo); Thread.sleep(10); System.out.println("[FIN] "+ tipo); 
    	}catch(InterruptedException e){ throw new RuntimeException(e);} 
	}
	public String toString(){
		return "Programa generico";
	}
}
class Trabajo implements Observable {
	protected String name;
	protected List<Trabajo> listaTrabajo;
	protected List<Programa> listaPrograma;
	protected List<Observer> observadores;
	public void addObserver(Observer o){
		observadores.add(o);
	}
	public void removeObserver(Observer o){
		observadores.remove(o);
	}

	public void notifyObservers(String str){
		Iterator itr =observadores.iterator();
		while(itr.hasNext()){
			((Observer)itr.next()).update(str);
		}
	}

	public void setTrabajo(Trabajo a){
		listaTrabajo.add(a);
	}
	public void setPrograma(Programa a){
		listaPrograma.add(a);
	}
	public Iterator getTrabajos(){
		Iterator itr=listaTrabajo.iterator();
		return itr;
	}
	public Iterator getPrograma(){
		Iterator itr=listaPrograma.iterator();
		return itr;
	}
	public String getName(){
		return name;
	}
	public void ejecutaTrabajo()throws Exception{

	}
}
class TrabajoSerie extends Trabajo {
	private String [] orden;
	public TrabajoSerie(String nombre,String[] orden,List<Trabajo> a,List<Programa> b){
		name=nombre;
		this.orden=orden;
		listaTrabajo=a;
		listaPrograma=b;
		observadores=new LinkedList<Observer>();
	}
	@Override
	public void ejecutaTrabajo() throws Exception{
		boolean flag=false;
		Trabajo b=new Trabajo();
		Programa k=new Programa("n");;
		for(String a:orden){
			//recorre rabajos
			Iterator itr=super.getTrabajos();
			while(a!=b.getName()&&itr.hasNext()){
				b=((Trabajo)itr.next());
			}
			if(b.getName()==a){
				b.ejecutaTrabajo();
			}
			//Recorre Programas
			Iterator itr2=super.getPrograma();
			while(a!=k.getName()&&itr2.hasNext()){
				k=((Programa)itr2.next());
			}
			if(k.getName()==a){
				Thread t1=(new Thread(k));
				t1.start();
				notifyObservers("inicio");
				t1.join();
				notifyObservers("final");
			}
		}
	}
}
class TrabajoParalelo extends Trabajo {
	public TrabajoParalelo(String nombre,List<Trabajo> a,List<Programa> b){
		name=nombre;
		listaTrabajo=a;
		listaPrograma=b;
		observadores=new LinkedList<Observer>();
	}
	@Override
	public void ejecutaTrabajo() throws Exception{
		boolean flag=false;
		Trabajo b=new Trabajo();
		Programa k=new Programa("n");;
		//recorre rabajos
		Iterator itr=super.getTrabajos();
		while(itr.hasNext()){
			b=((Trabajo)itr.next());
			b.ejecutaTrabajo();
		}
		//Recorre Programas
		Iterator itr2=super.getPrograma();
		while(itr2.hasNext()){
			k=((Programa)itr2.next());
			Thread t1=(new Thread(k));
			t1.start();
			notifyObservers("inicio");
		}
	}
}

