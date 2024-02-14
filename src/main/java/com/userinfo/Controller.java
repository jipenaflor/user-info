package com.userinfo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Controller {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private DatePicker birthDateField;

    @FXML
    private TextField emailField;

    @FXML
    private ComboBox<String> sexComboBox;

    @FXML
    private Text notification;

    @FXML
    private ListView<Person> personListView;

    private ObservableList<Person> personList;

    public void initialize() {
        personList = FXCollections.observableArrayList();
        personListView.setItems(personList);
        personListView.setCellFactory(param -> {
            return new ListCell<Person>() {
                @Override
                protected void updateItem(Person person, boolean empty) {
                    super.updateItem(person, empty);
                    if (empty || person == null) {
                        setGraphic(null);
                    } else {
                        TextFlow flow = new TextFlow();
                        Text name = new Text(String.format("%s %s ",
                                person.getFirstName(), person.getLastName()));
                        name.setStyle("-fx-font-weight: bold");
                        Text sex = new Text(String.format("(%s)\n", person.getSex()));
                        Text birthdate = new Text(String.format("Born on %s\n", person.getBirtDate()));
                        Hyperlink email = new Hyperlink(person.getEmail());
                        flow.getChildren().addAll(name, sex, birthdate, email);
                        setGraphic(flow);
                    }
                }
            };
        });
    }

    public String capitalize(String personName) {
        String[] names = personName.split(" ");
        int i = 0;
        for (String name: names) {
            names[i] = name.substring(0, 1).toUpperCase() + name.substring(1);
            i++;
        }
        return String.join(" ", names);
    }

    public boolean validateEmail(String email) {
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return Pattern.compile(regexPattern).matcher(email).matches();
    }

    public String validateBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            return null;
        }
        return birthDate.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
    }

    @FXML
    protected void onSubmitButtonClick() {
        String firstName = capitalize(firstNameField.getText().trim());
        String lastName = capitalize(lastNameField.getText().trim());
        String birthDate = validateBirthDate(birthDateField.getValue());
        String email = emailField.getText().trim();
        String sex = sexComboBox.getValue();

        if (firstName.isEmpty() || lastName.isEmpty() || birthDate == null || sex == null) {
            notification.setText("The fields are required and cannot be empty");
            return;
        }

        if (!validateEmail(email)) {
            notification.setText("Invalid email");
            return;
        }

        Person person = new Person(firstName, lastName, birthDate, email, sex);
        personList.add(person);

        firstNameField.clear();
        lastNameField.clear();
        birthDateField.setValue(null);
        emailField.clear();
        sexComboBox.getSelectionModel().clearSelection();
        notification.setText("");
    }
}
