package ui;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Bank;
import exception.DuplicateAccountNumber;
import javafx.scene.control.Alert.AlertType;
// Andrew Wilson - 101069860

public class Assignment2 extends Application implements EventHandler<ActionEvent> {
    private Scene home,addScene,depositScene,withdrawScene,listScene,transferScene;
    Stage window;  // represents main Stage globally
    Button btnAddMenu,btnDepositMenu,btnWithdrawMenu,btnTransferMenu,btnListMenu,btnAdd,btnHome,btnListHome,
        btnWithdraw,btnDeposit,btnTransfer,btnHomeDep,btnHomeWith,btnTransferHome;
    TextField custName,custAccNum,custBalance,custWithdraw,custDeposit,fromAccountNumFld,
            toAccountNumFld,custAccNumDep,custAccNumWith;
    TextArea accountList;
    Bank bank;

    public void init(){
    }

    public void start(Stage primaryStage) throws  Exception {
        bank = new Bank();
        window = primaryStage;
        // setting up Home Scene
        Label lblHomeMenu = new Label("Welcome to Trusty Bank. Please select an option from below");
        btnAddMenu = new Button("Add");btnAddMenu.setOnAction(this);btnAddMenu.setMaxWidth(Double.MAX_VALUE);
        btnDepositMenu = new Button("Deposit");btnDepositMenu.setOnAction(this);btnDepositMenu.setMaxWidth(Double.MAX_VALUE);
        btnWithdrawMenu = new Button("Withdraw");btnWithdrawMenu.setOnAction(this);btnWithdrawMenu.setMaxWidth(Double.MAX_VALUE);
        btnTransferMenu = new Button("Transfer");btnTransferMenu.setOnAction(this);btnTransferMenu.setMaxWidth(Double.MAX_VALUE);
        btnListMenu = new Button("List");btnListMenu.setOnAction(this);btnListMenu.setMaxWidth(Double.MAX_VALUE);
        VBox homeLayout = new VBox();
        homeLayout.setAlignment(Pos.CENTER);
        homeLayout.getChildren().addAll(lblHomeMenu,btnAddMenu,btnDepositMenu,btnWithdrawMenu,btnTransferMenu,btnListMenu);
        home = new Scene(homeLayout,500,500);

        // setting up Add Scene
        Label lblName =new Label("Name:");
        custName = new TextField();
        Label lblAccNum =new Label("Account#:");
        custAccNum=new TextField();
        Label lblBalance =new Label("Balance:");
        custBalance = new TextField();
        btnAdd = new Button("Add Account");btnAdd.setOnAction(this);
        btnHome = new Button("Back");btnHome.setOnAction(this);
        VBox addLayout =new VBox();
        addLayout.getChildren().addAll(lblName,custName,lblAccNum,custAccNum,lblBalance,custBalance,btnAdd,btnHome);
        addScene = new Scene(addLayout,500,500);

        // setting up List Scene
        Label lblShow = new Label("List of accounts...");
        accountList = new TextArea();
        btnListHome = new Button("Back");btnListHome.setOnAction(this);btnListHome.setMaxWidth(Double.MAX_VALUE);
        VBox listLayout =new VBox();
        listLayout.getChildren().addAll(lblShow,accountList,btnListHome);
        listScene = new Scene (listLayout,500,500);

        // setting up Deposit Scene
        Label lblAccNumDep = new Label("Account#:");
        Label lblDepositAmount = new Label("Deposit Amount:");
        btnHomeDep = new Button("Back");btnHomeDep.setOnAction(this);
        custAccNumDep = new TextField();
        custDeposit = new TextField();
        btnDeposit = new Button("Deposit");
        VBox depositLayout = new VBox();
        depositLayout.getChildren().addAll(lblAccNumDep,custAccNumDep,lblDepositAmount,custDeposit,btnDeposit,btnHomeDep);
        depositScene = new Scene(depositLayout,500,500);

        // setting up Withdraw Scene
        Label lblAccNumWith = new Label("Account#:");
        Label lblWithdrawAmount = new Label("Withdrawl Amount:");
        btnHomeWith = new Button("Back");btnHomeWith.setOnAction(this);
        custAccNumWith = new TextField();
        custWithdraw = new TextField();
        btnWithdraw = new Button("Withdraw");
        VBox withdrawLayout = new VBox();
        withdrawLayout.getChildren().addAll(lblAccNumWith,custAccNumWith,lblWithdrawAmount,custWithdraw,btnWithdraw,btnHomeWith);
        withdrawScene = new Scene(withdrawLayout,500,500);

        // setting up Transfer Scene
        Label lblAccNumFrom = new Label("From Account#:");
        Label lblAccNumTo = new Label("To Account#:");
        Label lblTransferAmount = new Label("Transfer Amount:");
        btnTransferHome = new Button("Back");btnTransferHome.setOnAction(this);
        fromAccountNumFld = new TextField();
        toAccountNumFld = new TextField();
        btnTransfer = new Button("Transfer");
        VBox transferLayout = new VBox();
        transferLayout.getChildren().addAll(lblAccNumFrom,fromAccountNumFld,lblAccNumTo,toAccountNumFld,lblTransferAmount,
                btnTransfer,btnTransferHome);
        transferScene = new Scene(transferLayout,500,500);

        window.setScene(home);
        window.show();
    }

    public void stop(){

    }


    public void handle(ActionEvent e){

        if (e.getSource()==btnAddMenu){
            System.out.println("add Menu btn pressed (on menu scene)");
            window.setScene(addScene);
        }
        if (e.getSource()==btnListMenu){
            System.out.println("list accounts btn pressed (on menu scene)");
            window.setScene(listScene);
        }
        if (e.getSource()==btnHome||e.getSource()==btnListHome||e.getSource()==btnHomeDep||e.getSource()==btnHomeWith
                || e.getSource()==btnTransferHome){
            System.out.println("add account btn pressed (on add scene or list scene or home)");
            window.setScene(home);
        }
        if (e.getSource()==btnDepositMenu){
            System.out.println("add account btn pressed (on deposit scene)");
            window.setScene(depositScene);
        }
        if (e.getSource()==btnWithdrawMenu){
            System.out.println("add account btn pressed (on withdraw scene)");
            window.setScene(withdrawScene);
        }
        if (e.getSource()==btnTransferMenu){
            System.out.println("add account btn pressed (on transfer scene)");
            window.setScene(transferScene);
        }
        if(e.getSource() == btnAdd) {
            System.out.println("Add");
            try {
                bank.addAccount(Long.valueOf(custAccNum.getText()), Double.valueOf(custBalance.getText()),
                        custName.getText());
                window.setScene(home);
            } catch(DuplicateAccountNumber exception) {
                System.out.println(exception);
                showAlert("That account number is already in use! Please try a different one.");
            }
            catch(Exception exception) {
                System.out.println(exception);
                showAlert("Something went wrong! Please set the values correctly.");
            }
        }
        if(e.getSource()==btnDeposit) {
            System.out.println("Deposit");
        }
        if(e.getSource()==btnWithdraw) {
            System.out.println("Withdraw");
        }
        if(e.getSource()==btnTransfer) {
            System.out.println("Transfer");
        }
        if(e.getSource() == btnListMenu) {
            accountList.setText(bank.printAccounts());
        }
    }
    private static void showAlert(String message) {
        Alert alert = new Alert(AlertType.NONE, message, ButtonType.OK);
        alert.showAndWait();
    }
    public static void main(String[] args) {
        launch(args);
    }

}