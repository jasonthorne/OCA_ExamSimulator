package controller;

import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Period;
import model.Period.Block;
import model.Plane;
import model.Plane.Status;

public class PlanesTableController implements Rootable {
	
	@FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    
    //root fxml element:
    @FXML private VBox rootVB;
    
    @FXML
    void initialize() { }
    	
    private final ObservableList<Plane> observPlanes = FXCollections.observableArrayList(); //observable planes
    private final TableView<Plane> planesTable = new TableView<Plane>(); //table view for planes
   
  	private final Stage stage = new Stage(); //stage
    private final Scene scene = new Scene(Rootable.getRoot(this, "/view/planesTable.fxml")); //rooted scene
   
    //constructor:
	PlanesTableController(List<Plane>planes, String airForceName) {
		stage.setTitle(airForceName + " Planes"); //set title
		stage.setScene(scene); //add scene to stage	
		observPlanes.addAll(planes); //add planes to observable list
    	planesTable.setItems(observPlanes); //add observable list to table
    	buildTable(); //build table
    }
	
	private void buildTable() {
		
		//TreeMap of a plane's availabilities, sorted by period compareTo:
		TreeMap<Period,Status> sortedAvails = new TreeMap<Period,Status>( 
				observPlanes.get(0).getPlaneAvailabilities());
		
		Period start =  sortedAvails.firstKey(); //get start period
		Period end = sortedAvails.lastKey(); //get end period
		
    	//create plane column:
    	TableColumn<Plane,String> planeCol = new TableColumn<>("Plane");
    	
    	//set cell factory:
    	planeCol.setCellValueFactory(
    			new PropertyValueFactory<Plane,String>("name"));
        	    
    	//add plane column to table:
    	planesTable.getColumns().add(planeCol); 
    	
    	//year and block columns:
    	TableColumn<Plane,String> yearCol;
    	TableColumn<Plane,String> blockCol;
    	
    	//call back for populating block column cells with plane period availabilities:
    	Callback<TableColumn.CellDataFeatures<Plane, String>, ObservableValue<String>> callBack = 
                new Callback<TableColumn.CellDataFeatures<Plane, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Plane, String> param) {
            	 return new SimpleStringProperty(
            			 param.getValue().getPlaneAvailabilities().get(
            					 param.getTableColumn().getUserData()).toString());		
            }
        };/** https://stackoverflow.com/questions/21639108/javafx-tableview-objects-with-maps */
        
        
        int currYear = start.getYear(); //holds year values
		Block currBlock; //holds block values
    	Iterator<Block>blocksIterator; //blocks iterator
    	boolean canAdd = false; //flag for adding values
    	
    	outerWhile:
    	while(currYear <= end.getYear()) { //loop through years
    		
    		yearCol = new TableColumn<>(String.valueOf(currYear)); //create year column
    		blocksIterator = Arrays.asList(Block.values()).iterator(); //(re)set blocks iterator
    		
    		while(blocksIterator.hasNext()) { //loop through blocks
    			currBlock = blocksIterator.next(); //advance to next block
    			
    			//if found start date, allow adding of values:
    			if(currBlock.equals(start.getBlock()) && currYear == start.getYear()) {canAdd = true;}
    				
    			if(canAdd) {
    				blockCol = new TableColumn<>(String.valueOf(currBlock)); //create block column 
        			blockCol.setUserData(new Period(currBlock, currYear)); //add period to block column
        			blockCol.setCellValueFactory(callBack); //set block column cell factory
            		yearCol.getColumns().add(blockCol); //add block column to year column
            		
            		//if found end date:
    				if(currBlock.equals(end.getBlock()) && currYear == end.getYear()) {
    					planesTable.getColumns().add(yearCol); //add year column to table 
    					break outerWhile; //break from outer while
    				}
    			}
    		}
    		planesTable.getColumns().add(yearCol); //add year column to table
    		currYear++; //advance to next year
    	}
    	//add table to root:
    	rootVB.getChildren().setAll(planesTable);
	}

    //show stage:
    void showStage() { stage.showAndWait(); }
}