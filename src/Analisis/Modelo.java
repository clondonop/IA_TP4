package Analisis;

import java.util.ArrayList;

public abstract class Modelo {

	// Arreglo de valores x
	// x->Valor del nutriente en base a 100 g
	protected ArrayList<Double> vectorX;

	// Arreglo valores y
	// y->Calificacion arrojada por el data set
	protected ArrayList<Double> vectorY;

	// variable que me permite saber si ya se ejecutó el modelo
	protected boolean ejecutado;

	// Construtor
	public Modelo(ArrayList<Double> x, ArrayList<Double> y) {
		this.vectorX = x;
		this.vectorY = y;
		ejecutado = false; // Inicializo la variables ocmo falsa porque aun no se ha ejecutado el modelo
	}

	// metodos get
	public ArrayList<Double> getvectorX() {
		return this.vectorX;
	}

	public ArrayList<Double> getvectorY() {
		return this.vectorY;
	}

	// Metodo que permitira obtener los coeficioentes de a y b
	public abstract double[] obtenerCoeficientes();

	// Metodo que ejecuta la busqueda de los mejores coeficientes a y b, basado en
	// la regresion lineal
	public abstract void ejecutar();

	// Evaluo la funcion de regresion lineal con el parametro ingresado x (cantidad
	// de nutriente) y los coeficientes
	public abstract double evaluar(double x);
}