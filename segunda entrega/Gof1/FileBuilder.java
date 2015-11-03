import java.io.*;
import java.util.*;
public class FileBuilder implements Builder {
	private PrintStream out;
	private File f;
	private int cont;
	public FileBuilder(File n) throws FileNotFoundException{
		out=new PrintStream(n.getName()+".out");
		cont=0;
		f=n;
	}
	public void addLibro(String autor,String titulo,String isbn){
		if (cont==0){
			out.println("<libros>");
			cont++;
		}
		out.println("<libro>\n<titulo>"+titulo+"</titulo>\n<autor>"+autor+"</autor>\n<isbn>"+isbn+"</isbn>\n</libro>");
	}
	public File getFile(){
		out.println("\n</libros>");
		out.close();
		return f;
	}
	
}

