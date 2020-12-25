
package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javafx.scene.media.AudioEqualizer;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private String path;
    private MediaPlayer mediaPlayer;

    @FXML
    private MediaView mediaView;

    @FXML
    private Slider progressBar;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Label song_name;

    @FXML
    private TableView<Music> musicsTable;

    @FXML
    private TableColumn<Integer, Integer> table1;

    @FXML
    private TableColumn<String, Integer> table2;

    @FXML
    private TableColumn<Integer, Integer> table3;

    @FXML
    private TableColumn<Music, Integer> numberColumn;

    @FXML
    private TableColumn<Music, String> nameColumn;

    @FXML
    private TableColumn<Music, String> pathColumn;

    int indexCurTrack = -1;


    public void choose_method(javafx.event.ActionEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        File[] selected_files;
        FileChooser fileChooser = new FileChooser();
        List<File> files = fileChooser.showOpenMultipleDialog(null);
        if (files != null)
            for (File file : files) {
                if (file != null) {
                    musicsTable.getItems().addAll(new Music(musicsTable.getItems().size() + 1, file.getName(), file.toURI().toString()));
                }
            }

        if (files != null) {
            Media media = new Media(path);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);

            DoubleProperty widthProp = mediaView.fitWidthProperty();
            DoubleProperty heightProp = mediaView.fitHeightProperty();

            widthProp.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
            heightProp.bind(Bindings.selectDouble(mediaView.sceneProperty(), "heightProp"));

            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                    progressBar.setValue(newValue.toSeconds());

                }
            });

            progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue()));
                }
            });

            progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(progressBar.getValue()));
                }
            });

            mediaPlayer.setOnReady(new Runnable() {
                @Override
                public void run() {
                    Duration time = media.getDuration();
                    progressBar.setMax(time.toSeconds());
                }
            });
            volumeSlider.setValue(mediaPlayer.getVolume() * 100);
            volumeSlider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volumeSlider.getValue() / 100);
                }
            });

            mediaPlayer.play();

        }
    }


    public void play(javafx.event.ActionEvent event) {
        mediaPlayer.play();
    }

    public void pause(javafx.event.ActionEvent event) {
        mediaPlayer.pause();
    }

    public void stop(javafx.event.ActionEvent event) {
        mediaPlayer.stop();
    }

    public void slow(javafx.event.ActionEvent event) {
        mediaPlayer.setRate(0.5);
    }

    public void fast(javafx.event.ActionEvent event) {
        mediaPlayer.setRate(2);
    }


}
