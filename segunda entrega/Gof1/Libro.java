
public class Libro {
	private String nombre;
	private String autor;
	private String isbn;
	public Libro(String a,String b,String c){
		nombre=a;
		autor=b;
		isbn=c;
	}
	public String getNom(){
		return nombre;
	}
	public String getAutor(){
		return autor;
	}
	public String getIsbn(){
		return isbn;
	}
}
