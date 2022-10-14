package com.example.desktopapp;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;

public class List extends Application implements Initializable {
    ObservableList<Users> observableList;



    public TableView<Users> tableView;
    public TableColumn<Users,Integer> id;

    public TableColumn<Users,String>name;
    public  TableColumn<Users,Integer>phone;
    public  TableColumn<Users,Integer>age;
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(List.class.getResource("list.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");

        //addButtonToTable();
        //stage1 = stage;

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        observableList = FXCollections.observableArrayList();

        String urlHttp = "http://localhost:8000/uyeler";
        HttpClient client= HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlHttp))
                .build();

        try {
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            String jsonString = response.body();
            JsonParser parser = new JsonParser();
            Object object = parser.parse(jsonString);
            JsonArray array = (JsonArray) object;
            /*System.out.println(array);
            System.out.println(array.get(0));
            JsonObject jsonObject = (JsonObject) array.get(1);
            System.out.println("Name:" + jsonObject.get("name"));*/
            for (int i = 0;i<array.size();i++){
                JsonObject jsonObject = (JsonObject) array.get(i);
                String name1 = jsonObject.get("name").getAsString();
                int id1 = jsonObject.get("id").getAsInt();
                int phone1 = jsonObject.get("phone").getAsInt();
                int age1 = jsonObject.get("age").getAsInt();


                Users users = new Users(id1,name1,phone1,age1);
                observableList.add(users);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        age.setCellValueFactory(new PropertyValueFactory<>("Age"));

        tableView.setItems(observableList);



    }

    @FXML
    protected void onDeleteButtonClick(){
        Users users = tableView.getSelectionModel().getSelectedItem();
        System.out.println(users.id.get());

        String urlHttp = "http://localhost:8000/uyesil/"+users.id.get();
        HttpClient client= HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlHttp))
                .build();

        try {
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            observableList.remove(users);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
