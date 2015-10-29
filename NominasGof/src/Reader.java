import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Reader {
	private File toret;
	private Scanner in;
	public Reader(String name) throws FileNotFoundException{
		toret=new File(name);
		in=new Scanner(new FileReader(toret));
	}
	public File getFile(){
		in.close();
		return toret;
	}
	public String getLine() {
		return in.nextLine();
	}
}
