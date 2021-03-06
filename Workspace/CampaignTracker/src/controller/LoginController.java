package controller;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import animation.Fadeable;
import animation.Shakeable;
import database.SelectUserID;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.User;

public class LoginController implements Rootable, Fadeable, Frameable {
	
	@FXML private ResourceBundle resources;
	@FXML private URL location;

	//root fxml element & children:
    @FXML private AnchorPane rootAP;
    @FXML private Label errorLbl;
	@FXML private JFXTextField nameTxtFld;
	@FXML private JFXPasswordField pswrdTxtFld;
	@FXML private JFXButton loginBtn;
    
	@FXML
    void initialize() {
		//initially hide error label:
		errorLbl.setVisible(false);  //+++++++++++++make this better! :P
    	//set btn actions:
		loginBtn.setOnAction(event -> loginUser());
    }
	
	//id of logged in user:
	///////private static int userId; //HAVE A SINGLETON PLAYER OBJ HERE INSTEAD WITH ACCESS TO ITS ID THROUGH A GETTER
	
	//fxml root node:
  	private Parent root;
	
	//controllers:
	private final CampaignsController campaignsCtrlr;
	////////////////////private final SignupController signupCtrlr; //????????????????????????NEEDED???? (found in login controller too!!)
	
	//logged in user:
	private static final User user = new User();
	
	//constructor:
	LoginController() {
		setRoot(); //set root node
		campaignsCtrlr = new CampaignsController(); //create campaigns controller
		////////////////////////signupCtrlr = new SignupController(); //create signup controller
		//user = new User();
		//========================================
		/** this is just a placeholder for pushing to db, before login.
		 * should be a push done in frameController first to add ip to db, for socket creation.*/
		/*new Thread(() ->  {
			SelectUserID.select(
					nameTxtFld.getText().trim(), 
					pswrdTxtFld.getText()); 	
		}).start();*/
		//======================================
	}
	
	private void loginUser() {
		
		//====================================================
		//shortcut to log in: - REMEMKBER: YOU LOG IN TWICE IF YOU ENTER VALID INFO! :P
		////////////////userId = 1;  //test account of Jay
		///////////////user = new User(1, "Jay");
		user.setValues(1, "jay");
		//user.setValues(2, "jo");
		//user.setValues(3, "dan");
		//user.setValues(4, "laura");
		
		/////////////database.SelectCampaigns.select(LoginController.getUserId()); //++++++++++TEST
		Fadeable.fade(root, FadeOption.FADE_OUT); //fade out this view
		FrameController.getFrameCtrlr().loginMove(campaignsCtrlr); //move to campaigns view
		//============================================================
		
		errorLbl.setVisible(false); //ensure error label is off //+++++++++++PTOB RTEMOVE A USING POP UP BOX NOW! :P
		
		//if name & password fields aren't empty: 
		if(!nameTxtFld.getText().trim().equals("") && !pswrdTxtFld.getText().trim().equals("")) {
			
			//check db for id of player with given name & password:
			int idCheck = database.SelectUserID.select( 
					nameTxtFld.getText().trim(), 
					pswrdTxtFld.getText().trim());
					
			//if result is > 0 then a valid id was returned:
			if (idCheck > 0) {
				///userId = idCheck; //store id
				user.setValues(2, "jo");
				////////////FrameController.getFrameCtrlr().setPlayerLbl(nameTxtFld.getText().trim()); //set name
				//////////Fadeable.fade(root, FadeOption.FADE_OUT); //fade out this view
				//////////FrameController.getFrameCtrlr().loginMove(campaignsCtrlr); //move to campaigns view
			}else {
				//warn user with error msg that account not found.
				System.out.println("no dice");
			}
		
		}else { // a field was blank:
			/** +++++++++++++++++ make this label thing better :P USE A POP UP ALERT BOX +++++++++ */
			//errorLbl.setVisible(true); //inform user with label
			///////////////FrameController.getFrameCtrlr().setViewLbl("Enter Username and Password");
			//shake elements:
			Arrays.asList(nameTxtFld, pswrdTxtFld).forEach(fxml -> Shakeable.shake(fxml));
		} 
	}
	
	//get user values:
	static int getUserId() { return user.getId(); } //get user id
	public static String getUserName() { return user.getName(); } //get user name //++++++++CHANGE BACK TO DEFAULT
	
	
	//let SignupController set user values:
	void setUserVals(int id, String name) { user.setValues(id, name); }
		
	
	/*
	//+++++++++++++++++++++maybe use this & store map of userId to players in campaign??????????
	public static void setUserId(int id) {
		if(userId == 0) { userId = id; }
		//+++++++++++ ELSE THROW AN EXCEPTION HERE! ++++++++++++++++
	}*/
	
	@Override
	public void setRoot() { root = Rootable.getRoot(this, "/view/login.fxml"); } //set root
	@Override
	public Parent getRoot() { return root; } //get root
	
	@Override 
	public String getViewTitle() { return "Login"; } //get title

}
