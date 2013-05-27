/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlleveltool;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import xmlleveltool.LevelElement.LevelTypes;

/**
 * FXML Controller class
 *
 * @author Karl
 */
public class BuilderController implements Initializable {
    @FXML
    private ListView<ObjectsList> elementsList;
    @FXML
    private ListView<LevelElement> buildingList;
    @FXML
    private ComboBox<LevelTypes> elementChoice;
    @FXML
    private Button addElement;
    @FXML
    private Button addObjectsList;
    @FXML
    private Button buildXML;
    @FXML
    private TextField layerLevel;
    
    private ObservableList<ObjectsList> objetcsList = FXCollections.observableArrayList();
    private ObservableList<LevelElement> buildList = FXCollections.observableArrayList();
    
    @FXML
    private void addElement(){
        try {
            this.buildList.add(new LevelElement(this.elementChoice.getValue(), Integer.parseInt(this.layerLevel.getText())));
        } catch (Exception ex) {
            Logger.getLogger(BuilderController.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
    }
    
    @FXML
    private void addObjectsList(){
        ObjectsList ol = new ObjectsList();
        ol.getList().addAll(this.buildList);
        this.buildList.clear();
        this.objetcsList.add(ol);
    }
    
    @FXML
    private void buildXML(){
        try {
            Element root = new Element("lvl");
            Attribute s = new Attribute("s", "1");
            root.setAttribute(s);
            
            Document document = new Document(root);
            
            for(ObjectsList ol : this.objetcsList){
                Element e = new Element("e");
                for(LevelElement le : ol.getList()){
                    Element o = new Element("o");
                    o.setAttribute(new Attribute("t", String.valueOf(le.getType().getId())));
                    o.setAttribute(new Attribute("l", String.valueOf(le.getLayer())));
                    e.getChildren().add(o);
                }
                root.getChildren().add(e);
            }

            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(document, System.out);
            sortie.output(document, new FileOutputStream("lvl.xml"));
        } catch (IOException ex) {
            Logger.getLogger(BuilderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.elementsList.setItems(this.objetcsList);
        this.buildingList.setItems(this.buildList);
        
        ObservableList<LevelTypes> types = FXCollections.observableArrayList(LevelTypes.values());
        this.elementChoice.setItems(types);
        
        this.elementsList.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t) {
                if(t.isControlDown()){
                    if (elementsList.getSelectionModel().getSelectedItem() != null) {
                        BuilderController.this.objetcsList.remove(elementsList.getSelectionModel().getSelectedItem());
                    }
                }
            }
        });
        this.buildingList.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t) {
                if(t.isControlDown()){
                    if (buildingList.getSelectionModel().getSelectedItem() != null) {
                        BuilderController.this.buildList.remove(buildingList.getSelectionModel().getSelectedItem());
                    }
                }
            }
        });
    }    
}
