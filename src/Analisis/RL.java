package Analisis;

import java.util.ArrayList;

import Analisis.StatUtils;

public class RL extends Modelo {

	// Creación variables para la función a+bx;
	private double a;// Intercepto eje y
	private double b;// Intercepto eje x

	// x->Valor del nutriente en base a 100 g
	// y->Calificacion arrojada por el data set
	public RL(ArrayList<Double> x, ArrayList<Double> y) {
		super(x, y);
		a = b = 0;// Aseguro que las variables sean 0
	}

	// Metodo que permitira obtener los coeficioentes de a y b
	public double[] obtenerCoeficientes() {

		if (!ejecutado)
			throw new IllegalStateException("Model has not yet computed");

		return new double[] { a, b };
	}

	// Metodo que ejecuta la busqueda de los mejores coeficientes a y b, basado en
	// la regresion lineal
	public void ejecutar() {

		// si se tienen menos de 2 valores, se debe arrojar una excepcion
		if (vectorX.size() < 2 | vectorY.size() < 2) {
			throw new IllegalArgumentException(
					"Se deben tener por lo menos 2 valores de cada parametro para poder analizar");
		}

		// se obtiene el valor de b bajo la siguiente formula b = cov[x,y] / var[x]
		b = StatUtils.covariance(vectorX, vectorY) / StatUtils.variance(vectorX);

		// se obtiene el valor de a bajo la siguiente formula a = promedio(y) + b *
		// promedio(x)
		a = StatUtils.mean(vectorY) - b * StatUtils.mean(vectorX);

		// cambia la variable a true cuando se obtiene a, b
		ejecutado = true;
	}

	// Evaluo la funcion de regresion lineal con el parametro ingresado x (cantidad
	// de nutriente) y los coeficientes
	public double evaluar(double x) {
		if (!ejecutado)
			throw new IllegalStateException("Modelo no se ha ejecutado");

		return a + b * x;
	}

}
