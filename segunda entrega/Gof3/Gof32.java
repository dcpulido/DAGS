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

		List<Trabajo> lT=new LinkedList<Trabajo>();
		String ord[]= {"a","c","d"};
		Trabajo t1=new TrabajoSerie("1",ord,lT,lP);
		t1.ejecutaTrabajo();
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
class Trabajo {
	protected String name;
	protected List<Trabajo> listaTrabajo;
	protected List<Programa> listaPrograma;
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
				t1.join();
			}
		}
	}
}
class TrabajoParalelo extends Trabajo {
	public TrabajoParalelo(String nombre,List<Trabajo> a,List<Programa> b){
		name=nombre;
		listaTrabajo=a;
		listaPrograma=b;
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
		}
	}
}
