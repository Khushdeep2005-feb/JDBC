package application;
	

import java.util.logging.Level;
import java.util.logging.Logger;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
	
	private static final Logger logger = Logger.getLogger(Main.class.getName());
	private UserDao userDao = new UserDao();
	
	
	@Override
	public void start(Stage primaryStage) {
		
		
		
		
		primaryStage.setTitle("JavaFX Welcome");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Student Registration Form");
		scenetitle.setFont(Font.font("Algerian", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);
		
		Label fillLabel = new Label("Fill in this form to register");
		grid.add(fillLabel, 0, 1);

		Label firstnameLabel = new Label("First Name");
		grid.add(firstnameLabel, 0, 2);

		TextField firstnameTextField = new TextField();
		grid.add(firstnameTextField, 1, 2);

		Label lastNameLabel = new Label("Last Name");
		grid.add(lastNameLabel, 0, 3);

		TextField lastNameTextField = new TextField();
		grid.add(lastNameTextField, 1, 3);

		Label emailLabel = new Label("E-mail");
		grid.add(emailLabel, 0, 4);

		TextField emailTextField = new TextField();
		grid.add(emailTextField, 1, 4);
		
		Label dateofbirthLabel= new Label("Date of Birth");
		grid.add(dateofbirthLabel, 0, 5);
		
		DatePicker d = new DatePicker();
		grid.add(d, 1, 5);
		
		Label usernameLabel = new Label("Set Username");
		grid.add(usernameLabel, 0, 6);
		
		TextField usernameTextField = new TextField();
		grid.add(usernameTextField, 1, 6);
		
		Label passwordLabel = new Label("Password");
		grid.add(passwordLabel, 0, 7);
		
		PasswordField passwordField = new PasswordField();
		grid.add(passwordField, 1, 7);
		
		




				
			
			
			

		
		Button saveButton = new Button("Register");
		HBox hBox = new HBox(10);
		hBox.setAlignment(Pos.BOTTOM_RIGHT);
		hBox.getChildren().add(saveButton);
		grid.add(hBox, 0, 8);
		
		
		/*
		 * ADD THE EVENT HANDLING --ONACTION METHOD.. TO DEAL WITH SAVE OPERATION
		 */
		
		saveButton.setOnAction(actionEvent -> {

			String username = usernameTextField.getText().trim();
			String lastName = lastNameTextField.getText().trim();
			String firstName = firstnameTextField.getText().trim();
			String password = passwordField.getText();
			String dateofbirth = dateofbirthLabel.getText();
			String email = emailTextField.getText();
			
			
		
			
			
			if (!StringPool.BLANK.equals(username) && !StringPool.BLANK.equals(lastName)
					&& !StringPool.BLANK.equals(firstName) && !StringPool.BLANK.equals(password) && !StringPool.BLANK.equals(dateofbirth) && !StringPool.BLANK.equals(email))
			
				try {
					if (!userDao.userExists(username)) {
						User user = this.createUserObject(username, lastName, firstName, password , dateofbirth , email );
						int userId = userDao.saveUser(user);
						if (userId > 0) {
							this.alert("Save", "Successful!", AlertType.INFORMATION);
						} else {
							this.alert("Error", "Failed!", AlertType.ERROR);
						}
					} else {
						this.alert("Error", "User already exists!", AlertType.ERROR);
					}
				} catch (Exception exception) {
					logger.log(Level.SEVERE, exception.getMessage());
				}
			else {
				this.alert("Error", "Please complete fields!", AlertType.ERROR);
			}

			
		});
			
		Scene scene = new Scene(grid, 300, 275);
		primaryStage.setScene(scene);
		primaryStage.show();
		}
		
	
		
	

	public void alert(String title, String message, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
	}

	public User createUserObject(String username, String lastName, String firstName, String password, String dateofbirth, String email) {
		User user = new User();
		user.setUsername(username);
		user.setLastName(lastName);
		user.setFirstName(firstName);
		user.setPassword(password);
		user.setDateofbirth(dateofbirth);
		user.setEmail(email);

		return user;
	
	
	

	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
	
