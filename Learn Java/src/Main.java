import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    private LessonManager lessonManager = new LessonManager();
    private TextArea codeEditor = new TextArea();
    private TextArea outputArea = new TextArea();
    private Button runButton = new Button("Execută");
    private Button checkButton = new Button("Verifică");
    private Label lessonContent = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Configurarea interfeței grafice
        BorderPane root = new BorderPane();
        VBox lessonBox = new VBox(new Label("Lecția:"), lessonContent);
        lessonBox.setPrefWidth(300);

        VBox editorBox = new VBox(new Label("Editor de Cod:"), codeEditor, runButton, checkButton);
        VBox.setVgrow(codeEditor, Priority.ALWAYS);

        VBox outputBox = new VBox(new Label("Rezultate:"), outputArea);
        outputArea.setEditable(false);

        HBox mainContent = new HBox(lessonBox, editorBox);
        HBox.setHgrow(editorBox, Priority.ALWAYS);

        root.setCenter(mainContent);
        root.setBottom(outputBox);

        // Inițializarea primei lecții
        loadLesson(1);

        // Event Handlers
        runButton.setOnAction(e -> runCode());
        checkButton.setOnAction(e -> checkCode());

        // Configurarea scenei
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Learn Java");
        primaryStage.show();
    }

    private void loadLesson(int lessonNumber) {
        String lessonText = lessonManager.getLessonContent(lessonNumber);
        lessonContent.setText(lessonText);
        codeEditor.setText(lessonManager.getStarterCode(lessonNumber));
    }

    private void runCode() {
        String userCode = codeEditor.getText();
        String result = CodeRunner.run(userCode);
        outputArea.setText(result);
    }

    private void checkCode() {
        String userCode = codeEditor.getText();
        boolean isCorrect = lessonManager.checkCode(userCode);
        if (isCorrect) {
            outputArea.setText("Felicitări! Ai completat lecția.");
            if (lessonManager.unlockNextLesson()) {
                loadLesson(lessonManager.getCurrentLesson());
            }
        } else {
            outputArea.setText("Cod incorect. Încearcă din nou.");
        }
    }
}
