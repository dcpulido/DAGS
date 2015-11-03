import java.util.*;
import java.io.*;
public class BiblioBuilder implements Builder {
	private ArrayList<Libro> libretos;

	public void addLibro(String autor,String titulo,String isbn){
		String str="";
		str+=autor;
		str+=" "+titulo;
		str+=" "+isbn;
		str+="\n";
		System.out.println(str);
	}
	public void getFile(){
		/*String str="";
		for(Libro lib:libretos){
			str+=lib.getAutor();
			str+=" "+lib.getNom();
			str+=" "+lib.getIsbn();
			str+="\n";
		}
		System.out.println(str);*/
		System.out.println(libretos.get(0).getAutor());
	}
}
