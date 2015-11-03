import java.io.*;
import java.util.*;
public class main {
	public static void main(String [] args) throws FileNotFoundException{
		if(args.length==0){
			System.out.print("Introduce el nombre del fichero de entrada");
			return;
		}
		File n=new File(args[0]);
		Builder bb=new FileBuilder(n);
		Builder bd=new BiblioBuilder();
		Reader red = new Reader(args[0],bb);
		Reader r0d = new Reader(args[0],bd);
		red.parse();
		r0d.parse();
		((FileBuilder) bb).getFile();
		
	}
}
