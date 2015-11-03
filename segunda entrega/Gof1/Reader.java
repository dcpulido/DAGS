import java.io.*;
import java.util.*;
public class Reader {
	private Builder bul;
	private File archivo;
	private Scanner in;
	public Reader(String name,Builder b) throws FileNotFoundException{
		archivo=new File(name);
		bul=b;
		in=new Scanner(archivo);
	}
	public void parse(){
		while(in.hasNextLine()){
			String[] tokens=in.nextLine().split("\t");
			if(tokens.length==3){
				bul.addLibro(tokens[0],tokens[1],tokens[2]);
			}
			else{System.out.println("rompe");}
		}
	}
}
