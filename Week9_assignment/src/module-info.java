module Week9_assignment {
	requires javafx.controls;
	requires java.sql;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
}
