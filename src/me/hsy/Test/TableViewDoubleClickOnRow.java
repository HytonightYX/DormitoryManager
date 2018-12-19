package me.hsy.Test;

import java.util.Random;
import java.util.function.Function;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class TableViewDoubleClickOnRow extends Application {

    @Override
    public void start(Stage primaryStage) {
        TableView<Item> table = new TableView<>();
        table.setRowFactory(tv -> {
            TableRow<Item> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Item rowData = row.getItem();
                    System.out.println("Double click on: "+rowData.getName());
                }
            });
            return row ;
        });
        table.getColumns().add(column("Item", Item::nameProperty));
        table.getColumns().add(column("Value", Item::valueProperty));

        Random rng = new Random();
        for (int i = 1 ; i <= 50 ; i++) {
            table.getItems().add(new Item("Item "+i, rng.nextInt(1000)));
        }

        Scene scene = new Scene(table);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static <S,T> TableColumn<S,T> column(String title, Function<S, ObservableValue<T>> property) {
        TableColumn<S,T> col = new TableColumn<>(title);
        col.setCellValueFactory(cellData -> property.apply(cellData.getValue()));
        return col ;
    }

    public static class Item {
        private final StringProperty name = new SimpleStringProperty();
        private final IntegerProperty value = new SimpleIntegerProperty();

        public Item(String name, int value) {
            setName(name);
            setValue(value);
        }

        public StringProperty nameProperty() {
            return name ;
        }

        public final String getName() {
            return nameProperty().get();
        }

        public final void setName(String name) {
            nameProperty().set(name);
        }

        public IntegerProperty valueProperty() {
            return value ;
        }

        public final int getValue() {
            return valueProperty().get();
        }

        public final void setValue(int value) {
            valueProperty().set(value);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}