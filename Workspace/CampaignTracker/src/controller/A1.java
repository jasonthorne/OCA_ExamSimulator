package controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import view.FxmlPath;

public class A1 implements Rootable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootAP;

    @FXML
    private JFXButton a2Btn;

    @FXML
    private JFXButton bBtn;

    @FXML
    void initialize() {
    	a2Btn.setOnAction(event -> goToA2());
    	bBtn.setOnAction(event -> goToB());
    }
    
  //root element for this controller:
  	private final Parent root = loadRoot.apply(this, FxmlPath.a1);
    
    FrameController frameCtrlr;
    A2 a2 = new A2();
    B b = new B();
    
    //constructor:
    A1(FrameController frameCtrlr){
    	this.frameCtrlr = frameCtrlr;
    }
    
    
    void goToA2(){
    	frameCtrlr.moveFwrd(a2.getRoot());
    }
    
    
    void goToB(){
    	frameCtrlr.moveFwrd(b.getRoot());
    }
    
    
    
    
    Parent getRoot() { return this.root; }
    
    
    
    
}
