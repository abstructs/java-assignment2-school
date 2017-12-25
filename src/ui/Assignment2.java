package ui;
import exception.AccountNotFound;
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
    TextField custName,custAccNum,custBalance, withdrawAmount, depositAmount,fromAccountNumFld,
            toAccountNumFld,custAccNumDep, custAccNumWithdraw,transferAmount;
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
        depositAmount = new TextField();
        btnDeposit = new Button("Deposit");btnDeposit.setOnAction(this);
        VBox depositLayout = new VBox();
        depositLayout.getChildren().addAll(lblAccNumDep,custAccNumDep,lblDepositAmount, depositAmount,btnDeposit,btnHomeDep);
        depositScene = new Scene(depositLayout,500,500);

        // setting up Withdraw Scene
        Label lblAccNumWith = new Label("Account#:");
        Label lblWithdrawAmount = new Label("Withdrawl Amount:");
        btnHomeWith = new Button("Back");btnHomeWith.setOnAction(this);
        custAccNumWithdraw = new TextField();
        withdrawAmount = new TextField();
        btnWithdraw = new Button("Withdraw");btnWithdraw.setOnAction(this);
        VBox withdrawLayout = new VBox();
        withdrawLayout.getChildren().addAll(lblAccNumWith, custAccNumWithdraw,lblWithdrawAmount, withdrawAmount,btnWithdraw,btnHomeWith);
        withdrawScene = new Scene(withdrawLayout,500,500);

        // setting up Transfer Scene
        Label lblAccNumFrom = new Label("From Account#:");
        Label lblAccNumTo = new Label("To Account#:");
        Label lblTransferAmount = new Label("Transfer Amount:");
        btnTransferHome = new Button("Back");btnTransferHome.setOnAction(this);
        fromAccountNumFld = new TextField();
        toAccountNumFld = new TextField();
        transferAmount = new TextField();
        btnTransfer = new Button("Transfer");btnTransfer.setOnAction(this);
        VBox transferLayout = new VBox();
        transferLayout.getChildren().addAll(lblAccNumFrom,fromAccountNumFld,lblAccNumTo,toAccountNumFld,lblTransferAmount,
                transferAmount,btnTransfer,btnTransferHome);
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
            System.out.println("'action'/back account btn pressed");
            window.setScene(home);
        }
        if (e.getSource()==btnDepositMenu){
            System.out.println("deposit btn pressed (on deposit scene)");
            window.setScene(depositScene);
        }
        if (e.getSource()==btnWithdrawMenu){
            System.out.println("withdraw btn pressed (on withdraw scene)");
            window.setScene(withdrawScene);
        }
        if (e.getSource()==btnTransferMenu){
            System.out.println("transfer btn pressed (on transfer scene)");
            window.setScene(transferScene);
        }
        if(e.getSource() == btnAdd) {
            System.out.println("Add");
            try {
                bank.addAccount(Long.valueOf(custAccNum.getText()), Double.valueOf(custBalance.getText()),
                        custName.getText());
                window.setScene(home);
                showAlert("Successfully added an account for " + custName.getText() + "!");
            } catch(DuplicateAccountNumber exception) {
                System.out.println(exception);
                showAlert(exception.getMessage());
            }
            catch(Exception exception) {
                System.out.println(exception);
                showAlert("Something went wrong! Please ensure the values are set correctly.");
            }
        }
        if(e.getSource()==btnDeposit) {
            try {
                bank.depositAccount(Long.valueOf(custAccNumDep.getText()), Double.valueOf(depositAmount.getText()));
                window.setScene(home);
                showAlert("Successfully deposited " + Double.valueOf(depositAmount.getText()) + " dollars!");
            } catch(AccountNotFound exception) {
                System.out.println(exception);
                showAlert(exception.getMessage());
            }
            catch(Exception exception) {
                System.out.println(exception);
                showAlert("Something went wrong! Please ensure the values are set correctly.");
            }
            System.out.println("Deposit");
        }
        if(e.getSource()==btnWithdraw) {
            try {
                if(!bank.withdrawAccount(Long.valueOf(custAccNumWithdraw.getText()), Double.valueOf(withdrawAmount.getText()))) {
                    showAlert("Not enough funds!");
                } else {
                    window.setScene(home);
                    showAlert("Successfully withdrew " + Double.valueOf(withdrawAmount.getText()) + " dollars!");
                }
            } catch(AccountNotFound exception) {
                System.out.println(exception);
                showAlert(exception.getMessage());
            } catch (Exception exception) {
                System.out.println(exception);
                showAlert("Something went wrong! Please ensure the values are set correctly.");
            }
            System.out.println("Withdraw");
        }
        if(e.getSource()==btnTransfer) {
            System.out.println("Transfer");
            try {
                if(!bank.transfer(Long.valueOf(fromAccountNumFld.getText()), Long.valueOf(toAccountNumFld.getText()),
                        Double.valueOf(transferAmount.getText()))) {
                    showAlert("Not enough funds!");
                } else {
                    window.setScene(home);
                    showAlert("Successfully transferred " + Double.valueOf(transferAmount.getText()) + " dollars!");
                }
            } catch(AccountNotFound exception) {
                System.out.println(exception);
                showAlert(exception.getMessage());
            }
            catch(Exception exception) {
                System.out.println(exception);
                showAlert("Something went wrong! Please ensure the values are set correctly.");
            }
        }
        if(e.getSource() == btnListMenu) {
            System.out.println("List all");
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