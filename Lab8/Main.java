package com.havryshkiv;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.awt.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;


public class Main extends Application {
    public Vacancy vacancy;
    private Desktop desktop = Desktop.getDesktop();
    private ObservableList<Vacancy> vacancies = FXCollections.observableArrayList();
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Lab8");
        primaryStage.getIcons().add(new Image("file:vacancy.png"));

        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select XML file");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        TableView<Vacancy> table = new TableView<Vacancy>(vacancies);
        table.prefHeightProperty().bind(primaryStage.heightProperty());
        TableColumn<Vacancy, Integer> idColumn = new TableColumn<Vacancy, Integer>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<Vacancy, Integer>("id"));
        idColumn.prefWidthProperty().bind(table.widthProperty().divide(2.05));
        table.getColumns().add(idColumn);
        TableColumn<Vacancy, String> nameColumn = new TableColumn<Vacancy, String>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Vacancy, String>("firm"));
        nameColumn.prefWidthProperty().bind(table.widthProperty().divide(2.05));
        table.getColumns().add(nameColumn);

        ScrollPane sp = new ScrollPane();
        sp.setPadding(new Insets(10));
        Button add_btn = new Button("Add");
        Button delete_btn = new Button("Delete");
        Button get_btn = new Button("Get from file(XML)");
        Button put_btn = new Button("Put to file(XML)");
        HBox buttons = new HBox(add_btn,delete_btn,get_btn,put_btn);
        VBox hb = new VBox();
        get_btn.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    fileChooser.getExtensionFilters().addAll(
                                            new FileChooser.ExtensionFilter("XML", "*.xml"));
                                    File file = fileChooser.showOpenDialog(primaryStage);
                                    if (file != null) {
                                        try (XMLDecoder decoder_ = new XMLDecoder(new BufferedInputStream(new FileInputStream(file.toString())))){
                                            Integer size_ = (Integer) decoder_.readObject();
                                            for (int i = 0; i < size_; i++) {
                                                vacancies.add((Vacancy) decoder_.readObject());
                                            }
                                        } catch (FileNotFoundException ex) {
                                            System.err.println("FileNotFound");
                                        }
                                    }
                                }
                            });
        put_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("XML", "*.xml"));
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file.toString())))){
                        encoder.writeObject(vacancies.size());
                        for (int i = 0; i < vacancies.size(); i++) {
                            encoder.writeObject(vacancies.get(i));
                        }
                        encoder.close();
                        System.out.println("Успішно записано!");
                    } catch (FileNotFoundException ex) {
                        System.err.println("Файл не знайдено!");
                    }

                }
            }
        });
        delete_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(vacancy != null){
                    vacancies.remove(vacancy);
                    hb.getChildren().clear();
                    table.refresh();
                    hb.getChildren().addAll(buttons);

                }
            }
        });
        add_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Label error_l = new Label();
                TextField firm_add = new TextField();
                TextField speciality_add = new TextField();
                TextField conditions_add = new TextField();
                TextField salary_add = new TextField();
                Button save = new Button("Save");
                HBox firm_g = new HBox(new Label("FirmName: "),firm_add);
                HBox speciality_g = new HBox(new Label("Speciality: "),speciality_add);
                HBox conditions_g = new HBox(new Label("Conditions: "),conditions_add);
                HBox salary_g = new HBox(new Label("Salary: "),salary_add);
                hb.getChildren().clear();
                hb.getChildren().addAll(firm_g,speciality_g,conditions_g,salary_g,save,error_l);

                save.setOnMouseClicked(e -> {
                    error_l.setText("");
                    if(firm_add.getText().length() > 0){
                        Vacancy add_vac = new Vacancy();
                        add_vac.setFirm(firm_add.getText());
                        add_vac.setSpeciality(speciality_add.getText());
                        add_vac.setConditions(conditions_add.getText());
                        if(salary_add.getText().matches("[0-9]*")) {
                            if(salary_add.getText().length() > 0) {
                                add_vac.setSalary(Integer.parseInt(salary_add.getText()));
                            }
                            else{
                                add_vac.setSalary(null);
                            }
                        }
                        vacancies.add(add_vac);
                        hb.getChildren().clear();
                        table.refresh();
                        hb.getChildren().addAll(buttons);
                    }
                    else{
                        error_l.setText("FirmName must be not empty!");
                    }
                });
            }
        });
        getVacancy(hb,table);
        hb.getChildren().addAll(buttons);
        TableView.TableViewSelectionModel<Vacancy> selectionModel = table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Vacancy>(){

            public void changed(ObservableValue<? extends Vacancy> val, Vacancy oldVal, Vacancy newVal){
                if(newVal != null){
                    vacancy = newVal;
                    hb.getChildren().clear();
                    hb.getChildren().addAll(buttons);
                    getVacancy(hb,table);

                }
            }
        });
        GridPane root = new GridPane();
        ColumnConstraints column1 = new ColumnConstraints(150,150,Double.MAX_VALUE);
        column1.setHgrow(Priority.ALWAYS);
        root.getColumnConstraints().add(column1);

        ColumnConstraints column2 = new ColumnConstraints(150,150,Double.MAX_VALUE);
        column2.setHgrow(Priority.ALWAYS);
        root.getColumnConstraints().add(column2);
        root.setColumnIndex(table, 0);
        sp.setContent(hb);
        root.setColumnIndex(sp, 1);

        root.getChildren().addAll(table, sp);

        Scene scene =  new Scene(root, 1000, 500);

        scene.getStylesheets().add((getClass().getResource("main.css")).toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void getVacancy(VBox hb,TableView<Vacancy> table){
        if(vacancies.size() > 0) {
            if (vacancy == null) {
                vacancy = vacancies.get(0);
            }
            Label lbl1 = new Label("ID:");
            TextField firm = new TextField();
            TextField speciality = new TextField();
            TextField conditions = new TextField();
            TextField salary = new TextField();
            lbl1.setText("ID: " + vacancy.getId());
            firm.setText(vacancy.getFirm());
            speciality.setText(vacancy.getSpeciality());
            conditions.setText(vacancy.getConditions());
            salary.setText("" + ((vacancy.getSalary() == null) ? "" : vacancy.getSalary()));
            HBox firm_g = new HBox(new Label("FirmName: "), firm);
            HBox speciality_g = new HBox(new Label("Speciality:"), speciality);
            HBox conditions_g = new HBox(new Label("Conditions: "), conditions);
            HBox salary_g = new HBox(new Label("Salary: "), salary);

            firm.textProperty().addListener((observable, oldValue, newValue) -> {
                if (vacancy != null) {
                    vacancy.setFirm(newValue);
                    table.refresh();
                }
            });
            speciality.textProperty().addListener((observable, oldValue, newValue) -> {
                if (vacancy != null) {
                    vacancy.setSpeciality(newValue);
                }
            });
            conditions.textProperty().addListener((observable, oldValue, newValue) -> {
                if (vacancy != null) {
                    vacancy.setConditions(newValue);
                }
            });
            salary.textProperty().addListener((observable, oldValue, newValue) -> {
                if (vacancy != null) {
                    if (newValue.matches("[0-9]*")) {
                        if (newValue.length() > 0) {
                            vacancy.setSalary(Integer.parseInt(newValue));
                        } else {
                            vacancy.setSalary(null);
                        }
                    } else {
                        salary.setText("" + ((vacancy.getSalary() == null) ? "" : vacancy.getSalary()));
                    }
                }
            });
            hb.getChildren().addAll(lbl1, firm_g, speciality_g, conditions_g, salary_g);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
