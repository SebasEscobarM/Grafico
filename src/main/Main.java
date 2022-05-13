package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.GraphicData;

public class Main extends Application{

	public static void main(String[] args) {
		
		String info="";
		try {
			File file=new File("data.csv");
			FileInputStream fis=new FileInputStream(file);
			BufferedReader br=new BufferedReader(new InputStreamReader(fis));
			br.readLine();
			while((info=br.readLine())!=null) {
				
				String received="";
				for(int j=0;j<info.length()-1;j++) {
					received+=info.charAt(j);
				}
				
				String[] rslt=received.split(",");
				int nwX=Integer.parseInt(rslt[0]);
				int nwY=Integer.parseInt(rslt[1]);
				GraphicData.x.add(nwX);
				GraphicData.y.add(nwY);
				if(nwX<GraphicData.minX) {
					GraphicData.minX=nwX;
				}
				if(nwY<GraphicData.minY) {
					GraphicData.minY=nwY;
				}
				if(nwX>GraphicData.maxX) {
					GraphicData.maxX=nwX;
				}
				if(nwY>GraphicData.maxY) {
					GraphicData.maxY=nwY;
				}
			}
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader=new FXMLLoader(Main.class.getResource("../ui/MainWindow.fxml"));
		Parent p=(Parent) loader.load();
		Stage stage=new Stage();
		Scene scene= new Scene(p);
		stage.setScene(scene);
		stage.show();		
	}

}
