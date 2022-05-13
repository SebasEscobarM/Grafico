package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.GraphicData;

public class ControllerMainWindow implements Initializable{

    @FXML
    private Canvas canvas;

    private GraphicsContext gc;
    
    private double convertion(double m ,double b, double x) {
    	return m*x+b;
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		gc=canvas.getGraphicsContext2D();
		
		double deltaPXx = canvas.getWidth();
		double deltaDias = GraphicData.maxX - GraphicData.minX;
		double pendienteX = deltaPXx/deltaDias;
		double interceptoX = -pendienteX*GraphicData.minX;

		
		double deltaPXy = -canvas.getHeight();
		double deltaAccidentes = GraphicData.maxY - GraphicData.minY;
		double pendienteY = deltaPXy/deltaAccidentes;
		double interceptoY = -pendienteY*GraphicData.maxY;
		
		gc.setFill(Color.rgb(199, 185, 185));
		
		gc.fillRect(0,0, canvas.getWidth(), 2);
		gc.fillRect(0,canvas.getHeight()-2, canvas.getWidth(), 2);
		gc.fillRect(0,0, 2, canvas.getHeight());
		gc.fillRect(canvas.getWidth()-2,0, 2, canvas.getHeight());
		
		gc.fillText(""+GraphicData.maxY, 2, 15);
		gc.fillText(""+GraphicData.maxX, canvas.getWidth()-35, canvas.getHeight()-3);
		
		for(int i=1;i<4;i++) {
			gc.fillRect(0,(canvas.getHeight()*i)/4, canvas.getWidth(), 2);
			gc.fillText(""+(GraphicData.maxY-(GraphicData.maxY*i)/4), 2, ((canvas.getHeight()*i)/4)-2);
			gc.fillRect((canvas.getWidth()*i)/4 ,0, 2, canvas.getHeight());
			gc.fillText(""+(GraphicData.maxX*i)/4, ((canvas.getWidth()*i)/4)+2, canvas.getHeight()-3);
		}

		double xAnt=0;
		double yAnt=canvas.getHeight();
		gc.setFill(Color.INDIANRED);
		for(int i=0;i<GraphicData.x.size();i++) {
			gc.fillOval(convertion(pendienteX, interceptoX, GraphicData.x.get(i)),convertion(pendienteY, interceptoY, GraphicData.y.get(i)), 6, 6);
			gc.strokeLine(xAnt, yAnt, convertion(pendienteX, interceptoX, GraphicData.x.get(i))+3, convertion(pendienteY, interceptoY, GraphicData.y.get(i))+3);
			xAnt=convertion(pendienteX, interceptoX, GraphicData.x.get(i))+3;
			yAnt=convertion(pendienteY, interceptoY, GraphicData.y.get(i))+3;
		}
	}

}
