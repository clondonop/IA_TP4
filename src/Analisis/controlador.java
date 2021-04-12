package Analisis;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import matrix.NoSquareException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import Analisis.producto;

public class controlador {
	private double Csodio,Cazucar,Cgrasas,Csaturadas,Ctotal;
	@FXML
	private Label cal = new Label();

	@FXML
	private Button btn,ins;

	@FXML
	private TextField saturadas,sodio,grasas,azucar,nombre,porcion;

	@FXML
	private ImageView CA,CS,CGS,CGT,AC1,AC2,AC3,AC4,AC5,AvN,AvP,AvS,AvA,AvGT,AvGS,tag= new ImageView(new Image("Imagenes/tag.png")),dimension= new ImageView(new Image("Imagenes/dimensions.png")),salt= new ImageView(new Image("Imagenes/salt.png")),sugar = new ImageView(new Image("Imagenes/sugar.png")),olive= new ImageView(new Image("Imagenes/olive-oil.png")),butter= new ImageView(new Image("Imagenes/butter.png")),logo= new ImageView(new Image("Imagenes/Logo.png"));
	private Alert campos = new Alert(AlertType.WARNING,"Por favor llenar todos los campos \n"+"Los valores decimales van con (.)\n"+"Si no contiene el nutriente poner 0");
	@FXML
	private void verificarNombre(ActionEvent event) {
		//esPalabra(nombre.getText());
		//System.out.println(esPalabra(nombre.getText()));
		if(esPalabra(nombre.getText())==true){
			System.out.println("Nombre correcto");
			AvN.setImage(new Image("Imagenes/f.PNG"));
		}else {
			System.out.println("Nombre incorrecto");
			AvN.setImage(new Image("Imagenes/alert.png"));
		}
	}
	@FXML
	private void verificarPorcion(ActionEvent event) {
		if((isNumeric(porcion.getText())==true)) {
			System.out.println("Porcion correcta");
			AvP.setImage(new Image("Imagenes/f.PNG"));
		}else {
			System.out.println("Porcion incorrecta");
			AvP.setImage(new Image("Imagenes/alert.png"));
		}
	}
	@FXML
	private void verificarAzucar(ActionEvent event) {
		if((isNumeric(azucar.getText())==true)) {
			System.out.println("Azucar Correcta");
			AvA.setImage(new Image("Imagenes/f.PNG"));
		}else {
			System.out.println("Azucar incorrecta");
			AvA.setImage(new Image("Imagenes/alert.png"));
		}
	}
	@FXML
	private void verificarGrasas(ActionEvent event) {
		if((isNumeric(grasas.getText())==true)) {
			System.out.println("Grasas correcta");
			AvGT.setImage(new Image("Imagenes/f.PNG"));
		}else {
			System.out.println("Grasas incorrecta");
			AvGT.setImage(new Image("Imagenes/alert.png"));
		}
	}
	@FXML
	private void verificarSaturadas(ActionEvent event) {
		if((isNumeric(saturadas.getText())==true)) {
			System.out.println("Saturada correcta");
			AvGS.setImage(new Image("Imagenes/f.PNG"));
		}else {
			System.out.println("Saturadas incorrecta");
			AvGS.setImage(new Image("Imagenes/alert.png"));
		}
	}
	@FXML
	private void verificarSodio(ActionEvent event) {
		if((isNumeric(sodio.getText())==true)) {
			System.out.println("Sodio correcto");
			AvS.setImage(new Image("Imagenes/f.PNG"));
		}else {
			System.out.println("Sodio incorrecto");
			AvS.setImage(new Image("Imagenes/alert.png"));
		}
	}
	@FXML
	private void infoSodio(MouseEvent event) {
		Alert a = new Alert(AlertType.INFORMATION,"La sal consumida en exceso es una de las principales causas de la hipertensión"+" arterial, la cual provoca el 5% de las muertes por enfermedades"+" cardiovasculares, ya que el exceso de sodio hace que las arterias se pongan"+" rígidas, dificultando el trabajo del corazón y los riñones.");
		a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		a.show();
	}
	@FXML
	private void infoAzucar(MouseEvent event) {
		Alert a = new Alert(AlertType.INFORMATION,"El azúcar en exceso provoca deterioro de las cavidades dentales, mayor apetito, aumento de peso, diabetes, hipertensión y demás.");
		a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		a.show();
	}
	@FXML
	private void infoGrasas(MouseEvent event) {
		Alert a = new Alert(AlertType.INFORMATION,"El consumo excesivo de alimentos con alto contenido de grasas se ha asociado con el incremento de la obesidad, produciendo cambios conductuales antes de que se presente una ganancia de peso excesivo, lo que afecta principalmente los mecanismos de control de eficiencia alimentaria.");
		a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		a.show();
	}
	@FXML
	private void infoSaturadas(MouseEvent event) {
		Alert a = new Alert(AlertType.INFORMATION,"El exceso de grasa saturada puede causar la acumulación de colesterol en las arterias, elevando su colesterol LDL e incrementa el riesgo de enfermedad cardíaca y accidente cerebrovascular.");
		a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		a.show();
	}
/*	@FXML
	private void instrucciones(ActionEvent event) throws IOException {	
		BufferedImage img=ImageIO.read(new File("src/Imagenes/Guia.png"));
		ImageIcon icon=new ImageIcon(img);
		JFrame frame=new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(500,710);
		JLabel lbl=new JLabel();
		lbl.setIcon(icon);
		ImageIcon icon2=new ImageIcon("src/Imagenes/Logo Nutri-check.png");
		frame.setIconImage(icon2.getImage());
		frame.add(lbl);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
	}*/
	@FXML
	private void handleButtonAction(ActionEvent event) {
		try {
			producto p = new producto(nombre.getText(),Double.parseDouble(porcion.getText()),Double.parseDouble(azucar.getText()), Double.parseDouble(sodio.getText()), Double.parseDouble(saturadas.getText()), Double.parseDouble(grasas.getText()));
			//System.out.println(nombre.getText()+Double.parseDouble(porcion.getText())+""+Double.parseDouble(azucar.getText())+""+ Double.parseDouble(sodio.getText())+""+Double.parseDouble(saturadas.getText())+""+ Double.parseDouble(grasas.getText()));
			Cazucar=p.analizarAzucar();
			Cgrasas=p.analizarGrasas();
			Csaturadas=	p.analizarSaturadas();
			Csodio=	p.analizarSodio();
			Ctotal=p.analizarTotal();
			System.out.println("Calificacion total:"+Ctotal);
			System.out.println("Sodio: "+Csodio +" Saturadas: "+ Csaturadas+ " Grasas: "+ Cgrasas+"  Azucar:"+ Cazucar);
			imgAzucar();
			imgSodio();
			imgGrasas();
			imgSaturadas();
			imgTotal();
		}catch(IOException e) {
			//System.out.println(nombre.getText()+Double.parseDouble(porcion.getText())+""+Double.parseDouble(azucar.getText())+""+ Double.parseDouble(sodio.getText())+""+Double.parseDouble(saturadas.getText())+""+ Double.parseDouble(grasas.getText()));
			e.printStackTrace();
			campos.show();	
		} catch (NoSquareException e) {
			System.out.println("No cuadrada");
			e.printStackTrace();
		}

	}

	private boolean esPalabra(String text) {
		int cont=0;
		boolean resultado;
		for(int i=0; i<text.length();i++) {
			if(Character.isLetter(text.charAt(i))||text.charAt(i)==' ') {
				//System.out.println(text.charAt(i));
				cont++;  
			}
		}
		if(text.length()==cont) {
			resultado=true;
		}else {
			resultado=false;
		}
		return resultado;
	}
	private boolean isNumeric(String text) {
		boolean resultado;

		try {
			Integer.parseInt(text);
			resultado = true;
		} catch (NumberFormatException excepcion) {
			resultado = false;
		}
		return resultado;
	}
	private void imgboton() {
		Image icono1 = new Image("Imagenes/information.png",30,30,false,true);
		btn.setGraphic((new ImageView(icono1)));
		ins.setGraphic(new ImageView(new Image("Imagenes/faq.png",32,32,false,true)));
	}

	public void initialize() {
		imgboton();		
	}

	private void imgAzucar() 
	{
		if (Cazucar==1) {
			Image i1 = new Image("Imagenes/speedometer (2).png");
			CA.setImage(i1);
		} else if(Cazucar==2) {
			Image i1 = new Image("Imagenes/speedometer.png");
			CA.setImage(i1);
		}else {
			Image i1 = new Image("Imagenes/speedometer (1).png");
			CA.setImage(i1);
		}
	}
	private void imgSodio() 
	{
		if (Csodio==1) {
			Image i1 = new Image("Imagenes/speedometer (2).png");
			CS.setImage(i1);
		} else if(Csodio==2) {
			Image i1 = new Image("Imagenes/speedometer.png");
			CS.setImage(i1);
		}else {
			Image i1 = new Image("Imagenes/speedometer (1).png");
			CS.setImage(i1);
		}
	}
	private void imgSaturadas() 
	{
		if (Csaturadas==1) {
			Image i1 = new Image("Imagenes/speedometer (2).png");
			CGS.setImage(i1);
		} else if(Csaturadas==2) {
			Image i1 = new Image("Imagenes/speedometer.png");
			CGS.setImage(i1);
		}else {
			Image i1 = new Image("Imagenes/speedometer (1).png");
			CGS.setImage(i1);
		}
	}
	private void imgGrasas() 
	{
		if (Cgrasas==1) {
			Image i1 = new Image("Imagenes/speedometer (2).png");
			CGT.setImage(i1);
		} else if(Cgrasas==2) {
			Image i1 = new Image("Imagenes/speedometer.png");
			CGT.setImage(i1);
		}else {
			Image i1 = new Image("Imagenes/speedometer (1).png");
			CGT.setImage(i1);
		}
	}
	private void imgTotal() 
	{
		cal.setText("*Calificación total");
		Image i1 = new Image("Imagenes/star.png");
		Image i2 = new Image("Imagenes/halfstar.png");
		Image i3 = new Image("Imagenes/emptystar.png");
		if (Ctotal<=1) {

			AC1.setImage(i1);
			AC2.setImage(i1);
			AC3.setImage(i1);
			AC4.setImage(i1);
			AC5.setImage(i1);
		} else if(Ctotal>1 && Ctotal<=1.25) {

			AC1.setImage(i1);
			AC2.setImage(i1);
			AC3.setImage(i1);
			AC4.setImage(i1);
			AC5.setImage(i2);
		}else if(Ctotal>1.25 && Ctotal<=1.5) {

			AC1.setImage(i1);
			AC2.setImage(i1);
			AC3.setImage(i1);
			AC4.setImage(i1);
			AC5.setImage(i3);
		}else if(Ctotal>1.5 && Ctotal<=1.75) {
			AC1.setImage(i1);
			AC2.setImage(i1);
			AC3.setImage(i1);
			AC4.setImage(i2);
			AC5.setImage(i3);
		}else if(Ctotal>1.75 && Ctotal<=2) {
			AC1.setImage(i1);
			AC2.setImage(i1);
			AC3.setImage(i1);
			AC4.setImage(i3);
			AC5.setImage(i3);
		}else if(Ctotal>2 && Ctotal<=2.25) {
			AC1.setImage(i1);
			AC2.setImage(i1);
			AC3.setImage(i2);
			AC4.setImage(i3);
			AC5.setImage(i3);
		}
		else if(Ctotal>2.25 && Ctotal<=2.5) {
			AC1.setImage(i1);
			AC2.setImage(i1);
			AC3.setImage(i3);
			AC4.setImage(i3);
			AC5.setImage(i3);
		}else if(Ctotal>2.5 && Ctotal<=2.75) {
			AC1.setImage(i1);
			AC2.setImage(i2);
			AC3.setImage(i3);
			AC4.setImage(i3);
			AC5.setImage(i3);
		}
		else if(Ctotal>2.75 && Ctotal<=3) {
			AC1.setImage(i1);
			AC2.setImage(i3);
			AC3.setImage(i3);
			AC4.setImage(i3);
			AC5.setImage(i3);
		}
	}
}