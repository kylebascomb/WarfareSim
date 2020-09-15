package war.controller;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import war.components.ArmyTableView;
import war.model.Unit;
import war.model.Army;
import war.model.ArmyList;
import war.view.NewArmyGui;
import war.view.NewUnitGui;
import war.view.ArmyChoiceGui;


public class NewArmyController {


    public NewArmyController(ArmyList armyList) {
        this.armyList = armyList;
        newArmyGui = new NewArmyGui();
        army = new Army();
        initControllers();
    }

    public NewArmyController(ArmyList armyList, Army army) {
        this.armyList = armyList;
        newArmyGui = new NewArmyGui();
        this.army = army;
        initControllers();
    }


    private void initControllers(){
        newArmyGui.getNewUnitButton().setOnAction(e ->{
            army.setName(newArmyGui.getArmyName().getText());
            NewUnitController newUnitController = new NewUnitController(armyList, army);
            NewUnitGui newUnitGui = newUnitController.getNewUnitGui();
            Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
            primaryStage.setScene(newUnitGui.getScene());
            primaryStage.setMaximized(true);
        });
        newArmyGui.getSaveButton().setOnAction(e->{
            saveArmy();
            armyList.addArmy(army);

            ArmyChoiceController armyChoiceController = new ArmyChoiceController(armyList);
            ArmyChoiceGui armyChoiceGui = armyChoiceController.getArmyChoiceGui();
            Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
            primaryStage.setScene(armyChoiceGui.getScene());
            primaryStage.setMaximized(true);
        });

        fillUnitsView();
        updateArmyName();

    }

    private void updateArmyName() {
        if(!army.getName().equals("")){
            newArmyGui.getArmyName().setText(army.getName());
        }
    }

    private void saveArmy(){
        TextField armyName = newArmyGui.getArmyName();
        if(!armyName.getText().equals("Enter Army Name Here")){
            army.setName(armyName.getText());
        }
        else{
            army.setName(army.getUnits().get(0).getName());
        }
    }

    private void fillUnitsView(){
        ArmyTableView unitsView = newArmyGui.getUnitsView();
        unitsView.setArmy(army);
        unitsView.addNameColumn();
        unitsView.addAttackColumn();
        unitsView.addDefenseColumn();
        unitsView.addToughnessColumn();
        unitsView.addPowerColumn();
        unitsView.addMoraleColumn();
        unitsView.addCostColumn();
    }

    /**
     * Getter for newArmyGui
     *
     * @return newArmyGui
     */
    public NewArmyGui getNewArmyGui() {
        return newArmyGui;
    }

    /**
     * Setter for newArmyGui
     *
     * @param newArmyGui - newArmyGui
     */
    public void setNewArmyGui(NewArmyGui newArmyGui) {
        this.newArmyGui = newArmyGui;
    }

    /**
     * Getter for armyList
     *
     * @return armyList
     */
    public ArmyList getArmyList() {
        return armyList;
    }

    /**
     * Setter for armyList
     *
     * @param armyList - armyList
     */
    public void setArmyList(ArmyList armyList) {
        this.armyList = armyList;
    }

    private NewArmyGui newArmyGui;
    private ArmyList armyList;
    private Army army;
}
