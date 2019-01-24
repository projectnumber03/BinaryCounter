package Model;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class Counter {
    private Thread td;
    private volatile int ones = 0;
    private volatile int zeroes = 0;
    public Counter(Label lbl) {
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                int i = 0;
                int j = 0;
                while (true) {
                    final int finalJ = j;
                    Platform.runLater(() -> {
                        lbl.setText(String.valueOf("Прошло " + finalJ + " сек."));
                        long randInt = Math.round(Math.random());
                        if (randInt > 0) ones++;
                        else zeroes++;
                    });
                    i++;
                    if (i % 5 == 0) j++;
                    Thread.sleep(200);
                }
            }
        };
        td = new Thread(task);
        td.setDaemon(true);
        td.start();
    }

    public void stopCount(){
        td.interrupt();
    }

    public void getResult(){
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Результат");
            alert.setHeaderText("Программа завершила свою работу");
            alert.setContentText("Сгенерировано единиц: " + ones + "\n Сгенерировано нулей: " + zeroes);
            alert.showAndWait();
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Подсчет не запускался, нажмите Start!");
            alert.showAndWait();
        }
    }
}
