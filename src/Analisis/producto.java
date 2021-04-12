package Analisis;
import java.io.*;
import java.util.*;
import Analisis.RL;
import matrix.*;

public class producto {
	private double sodio;
	private double azucar;
	private double grasas;
	private double saturadas;
	private double porcion;
	private String nombre;
	private double Csodio,Cazucar,Cgrasas,Csaturadas,Ctotal;
	private ArrayList<Double> GrasasX = new ArrayList<Double>() ;
	private ArrayList<Double> GrasasY = new ArrayList<Double>();
	private ArrayList<Double> SaturadasX = new ArrayList<Double>(); 
	private ArrayList<Double> SaturadasY= new ArrayList<Double>(); 
	private ArrayList<Double> AzucarX= new ArrayList<Double>()  ;
	private ArrayList<Double> AzucarY = new ArrayList<Double>(); 
	private ArrayList<Double> SodioX= new ArrayList<Double>() ;
	private ArrayList<Double> SodioY = new ArrayList<Double>();
	private ArrayList<Double>TotalY = new ArrayList<Double>();
	private Matrix CalificacionX;
	private Matrix CalificacionY;
	private Matrix Beta ;


	public producto(String nombre, double porcion,double azucar, double sodio,double saturadas,double grasas ) {
		this.porcion=porcion;
		this.grasas= (grasas*100)/this.porcion;
		this.azucar =(azucar*100)/this.porcion;
		this.saturadas=(saturadas*100)/this.porcion;
		this.nombre=nombre;
	}

	public double analizarTotal() throws NoSquareException, IOException {
		this.CalificacionY= leerTotalY();
		this.CalificacionX= new Matrix(transposeMatrix(new double[][]{convertir(GrasasY),convertir(SaturadasY),convertir(AzucarY),convertir(SodioY)}));
		MultiLinear MLR = new MultiLinear(CalificacionX,CalificacionY);
		Beta=MLR.calculate();
		this.Ctotal=redondearDecimales((Beta.getValueAt(0, 0))+(this.Cgrasas*Beta.getValueAt(1, 0))+(this.Csaturadas*Beta.getValueAt(2, 0))+(this.Cazucar*Beta.getValueAt(3, 0))+(this.Csodio*Beta.getValueAt(4, 0)),3);
		System.out.println("Valores de beta");
		imprimir(Beta);
		actualizarTotalY(Ctotal);
		return Ctotal;

	}

	public double analizarAzucar() throws IOException {
		leerAzucarX();
		leerAzucarY();
		RL MAzucar= new RL(AzucarX,AzucarY);
		MAzucar.ejecutar();
		this.Cazucar=aproximar(MAzucar.evaluar(azucar));
		actualizarAzucarX(azucar);
		actualizarAzucarY(Cazucar);
		return Cazucar;
	}

	public double analizarSodio() throws IOException {
		leerSodioX();
		leerSodioY();
		RL MSodio= new RL(SodioX,SodioY);
		MSodio.ejecutar();
		this.Csodio=aproximar(MSodio.evaluar(sodio));
		actualizarSodioX(sodio);
		actualizarSodioY(Csodio);
		return Csodio;

	}

	public double analizarGrasas() throws IOException {
		leerGrasasX();
		leerGrasasY();
		RL MGrasas= new RL(GrasasX,GrasasY);
		MGrasas.ejecutar();
		this.Cgrasas=aproximar(MGrasas.evaluar(grasas));
		actualizarGrasasX(grasas);
		actualizarGrasasY(Cgrasas);
		return Cgrasas;

	}

	public double analizarSaturadas() throws IOException {
		leerSaturadasX();
		leerSaturadasY();
		RL MSaturadas= new RL(SaturadasX,SaturadasY);
		MSaturadas.ejecutar();
		this.Csaturadas=aproximar(MSaturadas.evaluar(saturadas));
		actualizarSaturadasX(saturadas);
		actualizarSaturadasY(Csaturadas);
		return Csaturadas;
	}

	public Matrix leerTotalY() throws IOException {
		String cadena;
		FileReader f = new FileReader("src\\Analisis\\ficheros\\TotalY.txt");
		BufferedReader b = new BufferedReader(f);
		while((cadena = b.readLine())!=null) {

			TotalY.add(Double.parseDouble(cadena));
		}
		b.close();
		Matrix aux= new Matrix(transposeMatrix( new double[][] {convertir(TotalY)}));
		return aux;
		
	}	
	public void actualizarTotalY( Double dato){
		try {
			FileWriter fstream = new FileWriter("src\\Analisis\\ficheros\\TotalY.txt", true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("\n" + dato.toString());
			out.close();
		} catch (IOException ex) {
			System.out.println("Error: "+ex.getMessage());
		}
	}
	
	public void leerAzucarX() throws IOException {
		String cadena;
		FileReader f = new FileReader("src/Analisis/ficheros/AzucarX.txt");
		BufferedReader b = new BufferedReader(f);
		while((cadena = b.readLine())!=null) {

			AzucarX.add(Double.parseDouble(cadena));
		}
		b.close();
	}
	private void leerAzucarY() throws IOException {
		String cadena;
		FileReader f = new FileReader("src\\Analisis\\ficheros\\AzucarY.txt");
		BufferedReader b = new BufferedReader(f);
		while((cadena = b.readLine())!=null) {

			AzucarY.add(Double.parseDouble(cadena));
		}
		b.close();
	}
	
	public void actualizarAzucarX( Double dato){
		try {
			FileWriter fstream = new FileWriter("src\\Analisis\\ficheros\\AzucarX.txt", true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("\n" + dato.toString());
			out.close();
		} catch (IOException ex) {
			System.out.println("Error: "+ex.getMessage());
		}
	}
	public void actualizarAzucarY( Double dato){
		try {
			FileWriter fstream = new FileWriter("src\\Analisis\\ficheros\\AzucarY.txt", true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("\n" + dato.toString());
			out.close();
		} catch (IOException ex) {
			System.out.println("Error: "+ex.getMessage());
		}
	}

	public void leerSodioX() throws IOException {
		String cadena;
		FileReader f = new FileReader("src\\Analisis\\ficheros\\SodioX.txt");
		BufferedReader b = new BufferedReader(f);
		while((cadena = b.readLine())!=null) {

			SodioX.add(Double.parseDouble(cadena));
		}
		b.close();
	}
	private void leerSodioY() throws IOException {
		String cadena;
		FileReader f = new FileReader("src\\Analisis\\ficheros\\SodioY.txt");
		BufferedReader b = new BufferedReader(f);
		while((cadena = b.readLine())!=null) {
			//System.out.println(cadena);
			SodioY.add(Double.parseDouble(cadena));
		}
		b.close();
	}

	public void actualizarSodioX( Double dato){
		try {
			FileWriter fstream = new FileWriter("src\\Analisis\\ficheros\\SodioX.txt", true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("\n" + dato.toString());
			out.close();
		} catch (IOException ex) {
			System.out.println("Error: "+ex.getMessage());
		}
	}
	public void actualizarSodioY( Double dato){
		try {
			FileWriter fstream = new FileWriter("src\\Analisis\\ficheros\\SodioY.txt", true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("\n" + dato.toString());
			out.close();
		} catch (IOException ex) {
			System.out.println("Error: "+ex.getMessage());
		}
	}

	public void leerSaturadasX() throws IOException {
		String cadena;
		FileReader f = new FileReader("src\\Analisis\\ficheros\\SaturadasX.txt");
		BufferedReader b = new BufferedReader(f);
		while((cadena = b.readLine())!=null) {

			SaturadasX.add(Double.parseDouble(cadena));
		}
		b.close();
	}
	private void leerSaturadasY() throws IOException {
		String cadena;
		FileReader f = new FileReader("src\\Analisis\\ficheros\\SaturadasY.txt");
		BufferedReader b = new BufferedReader(f);
		while((cadena = b.readLine())!=null) {

			SaturadasY.add(Double.parseDouble(cadena));
		}
		b.close();
	}
	public void actualizarSaturadasX( Double dato){
		try {
			FileWriter fstream = new FileWriter("src\\Analisis\\ficheros\\SaturadasX.txt", true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("\n" + dato.toString());
			out.close();
		} catch (IOException ex) {
			System.out.println("Error: "+ex.getMessage());
		}
	}
	public void actualizarSaturadasY( Double dato){
		try {
			FileWriter fstream = new FileWriter("src\\Analisis\\ficheros\\SaturadasY.txt", true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("\n" + dato.toString());
			out.close();
		} catch (IOException ex) {
			System.out.println("Error: "+ex.getMessage());
		}
	}

	public void leerGrasasX() throws IOException {
		String cadena;
		FileReader f = new FileReader("src\\Analisis\\ficheros\\GrasasX.txt");
		BufferedReader b = new BufferedReader(f);
		while((cadena = b.readLine())!=null) {
			GrasasX.add(Double.parseDouble(cadena));
		}
		b.close();
	}
	private void leerGrasasY() throws IOException {
		String cadena;
		FileReader f = new FileReader("src\\Analisis\\ficheros\\GrasasY.txt");
		BufferedReader b = new BufferedReader(f);
		while((cadena = b.readLine())!=null) {
			GrasasY.add(Double.parseDouble(cadena));
		}

		b.close();
	}
	public void actualizarGrasasX( Double dato){
		try {
			FileWriter fstream = new FileWriter("src\\Analisis\\ficheros\\GrasasX.txt", true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("\n" + dato.toString());
			out.close();
		} catch (IOException ex) {
			System.out.println("Error: "+ex.getMessage());
		}
	}
	public void actualizarGrasasY( Double dato){
		try {
			FileWriter fstream = new FileWriter("src\\Analisis\\ficheros\\GrasasY.txt", true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("\n" + dato.toString());
			out.close();
		} catch (IOException ex) {
			System.out.println("Error: "+ex.getMessage());
		}
	}

	public void imprimir(Matrix matriz) {
		for (int x=0; x < matriz.getNrows(); x++) {
			System.out.print("|");
			for (int y=0; y < matriz.getNcols(); y++) {
				System.out.print (matriz.getValueAt(x, y));
				if (y!=matriz.getNcols()-1) System.out.print("\t");
			}
			System.out.println("|");
		}
	}
	public static double[][] transposeMatrix(double[][] ds){
		double[][] temp = new double[ds[0].length][ds.length];
		for (int i = 0; i < ds.length; i++) {
			for (int j = 0; j < ds[0].length; j++) {
				temp[j][i] = ds[i][j];}}
		return temp;
	}
	public double aproximar(double numero){
		if(numero<1.5)
			numero=1;
		else if(numero >=1.5 && numero<2.5)
			numero=2;
		else if(numero >=2.5)
			numero=3;
		return numero;
	}
	public double[] convertir(ArrayList<Double> l) {
		double ret[] = new double[l.size()];
		for(int i = 0; i < ret.length-1; i++){
		     ret[i] = l.get(i);
		}
		return ret;
	}
    public static double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
        resultado=Math.round(resultado);
        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
        return resultado;
    }	
	public double getCsodio() {
		return Csodio;
	}

	public void setCsodio(double csodio) {
		Csodio = csodio;
	}

	public double getCazucar() {
		return Cazucar;
	}

	public void setCazucar(double cazucar) {
		Cazucar = cazucar;
	}

	public double getCgrasas() {
		return Cgrasas;
	}

	public void setCgrasas(double cgrasas) {
		Cgrasas = cgrasas;
	}

	public double getCsaturadas() {
		return Csaturadas;
	}

	public void setCsaturadas(double csaturadas) {
		Csaturadas = csaturadas;
	}

	public double getSodio() {
		return sodio;
	}

	public void setSodio(double sodio) {
		this.sodio = sodio;
	}

	public double getAzucar() {
		return azucar;
	}

	public void setAzucar(double azucar) {
		this.azucar = azucar;
	}

	public double getGrasas() {
		return grasas;
	}

	public void setGrasas(double grasas) {
		this.grasas = grasas;
	}

	public double getSaturadas() {
		return saturadas;
	}

	public void setSaturadas(double saturadas) {
		this.saturadas = saturadas;
	}

	public double getPorcion() {
		return porcion;
	}

	public void setPorcion(double porcion) {
		this.porcion = porcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
