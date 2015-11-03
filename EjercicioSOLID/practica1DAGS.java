package converterapp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

interface Reader{
	public String getLine();
}
interface Writer{
	public void writeLine(String line);
	public void cierra();
}
interface Formateador{
	public String darFormato(String str, boolean isLast);
}
interface IU{
	public String obtenerFicheroEntrada();
	public String obtenerFicheroSalida();
}
class IUpantalla implements IU{
	public String obtenerFicheroEntrada() {
		System.out.println("input filename: ");
		Scanner n = new Scanner(System.in);
		String a= n.nextLine();
		return a;
	}
	public String obtenerFicheroSalida() {
		System.out.println("output filename: ");
		Scanner n = new Scanner(System.in);
		String a= n.nextLine();
		return a;
	}
}
class ConverterApp {

	public static void main(String args[]) throws FileNotFoundException{
		IU interfaz=new IUpantalla();
		String nameEntrada = interfaz.obtenerFicheroEntrada();
		String nameSalida = interfaz.obtenerFicheroSalida();
		Reader entrada = new ArchivoEntrada(nameEntrada);		
		Writer salida = new ArchivoSalida(nameSalida);
		Formateador fmt = new TABaXMLFormateador();
		Transformacion ts=new Transformacion(entrada, salida, fmt);
		ts.transforma();
	}
}
class ArchivoEntrada implements Reader{
	private File entrada;
	public static Scanner in;
	public ArchivoEntrada(String n) throws FileNotFoundException{	
		entrada=new File(n);
		existeEntrada(entrada);
		in = new Scanner(entrada);
	}
	public File getArchivo(){
		return entrada;
	}
	
	private boolean existeEntrada(File input){
		Scanner scanner = null;
		try {
			scanner = new Scanner(input);
		} catch (FileNotFoundException e) {
			System.err.println("the file " + input.getAbsolutePath()
					+ " does not exists: " + e.getMessage());
			System.exit(1);
		}
		return true;
	}

	@Override
	public String getLine(){
		return in.nextLine();
	}
}
class ArchivoSalida implements Writer{
	private File salida;
	private int cont;
	private PrintStream out;

	public ArchivoSalida(String n){
		salida=new File(n);
		try{
			out= new PrintStream(n);
		} catch (FileNotFoundException e1){
			System.err.println("the file ");
		}
		existeSalida(salida);
		cont=0;
	}
	
	private boolean existeSalida(File output){
	
		PrintStream out = null;
		try {
			out = new PrintStream(new FileOutputStream(output));
		} catch (FileNotFoundException e1) {
			System.err.println("the file " + output.getAbsolutePath()
					+ " does cannot be created: " + e1.getMessage());
			System.exit(1);
		}
		return true;
	}
	public void writeLine(String line){
		out.println(line);
	}
	public void cierra(){
		out.close();
	}
}
class Transformacion {
	private Reader entrada;
	private Writer salida;
	private Formateador formateador;
	public static String toret;
	public Transformacion(Reader a,Writer b, Formateador f){
		entrada=a;
		salida=b;
		formateador=f;
		toret="";
	}
	public void transforma(){
		String lineaEntrada = entrada.getLine();
		if (lineaEntrada!=null)
		{
			do {
				String tmp = lineaEntrada;
				lineaEntrada = entrada.getLine();
				String lineaTransformada = formateador.darFormato(tmp, lineaEntrada == null);
				salida.writeLine(lineaTransformada);

			} while (lineaEntrada!=null);
		}
		salida.cierra();
	}
}
class TABaXMLFormateador implements Formateador{
	private boolean isFirst = true;
	
	public String darFormato(String line, boolean lastLine){

		StringBuilder stringBuilder = new StringBuilder();
		if(isFirst){
			isFirst = false;
			stringBuilder.append("<products>");
		}
		String[] tokens = line.split("\t");
		if (tokens.length != 2) {
			throw new IllegalArgumentException(
					"the line does not contain 3 tokens");
		}

		String xmlString="<product>\n\t<name>" + tokens[0] + "</name>\n\t<price>"
				+ tokens[1] + "</price>\n</product>";

		stringBuilder.append(xmlString);

		if (lastLine) {
			stringBuilder.append("</products>");
		}

		return stringBuilder.toString();
	}
}

