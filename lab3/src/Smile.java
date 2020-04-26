import java.io.FileNotFoundException;
import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Smile extends Application {
    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600);

        Color foreheadColor = Color.rgb(255, 250, 125);
        Color appleColor = Color.rgb(13, 167, 234);

        LinearGradient bodyGradient = new LinearGradient(0, 0, 0, 1, true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(246, 246, 24)),
                new Stop(1, Color.rgb(255, 153, 0)));

        Circle body = new Circle(400, 300, 100);
        body.setFill(bodyGradient);

        Ellipse forehead = new Ellipse(400, 245, 70, 30);
        forehead.setFill(foreheadColor);

        Arc leftWhiteEye = new Arc(360, 295, 25, 45, 5, 180);
        leftWhiteEye.setFill(Color.WHITE);

        Arc leftApple = new Arc(362, 294, 15, 23, 5, 180);
        leftApple.setFill(appleColor);

        Arc leftBlackEye = new Arc(362, 294, 12, 18, 5, 180);

        Circle leftCrystalline = new Circle(368, 280, 3, Color.WHITE);

        QuadCurve leftEyebrow = new QuadCurve(335, 250, 358, 230, 370, 240);
        leftEyebrow.setFill(foreheadColor);
        leftEyebrow.setStroke(Color.BLACK);
        leftEyebrow.setStrokeWidth(3.5);

        Arc rightWhite = new Arc(440, 295, 25, 45, -5, 180);
        rightWhite.setFill(Color.WHITE);

        Arc rightApple = new Arc(440, 294, 15, 23, -5, 180);
        rightApple.setFill(appleColor);

        Arc rightEye = new Arc(440, 294, 12, 18, -5, 180);

        Circle rightCrystalline = new Circle(434, 280, 3, Color.WHITE);

        QuadCurve rightEyebrow = new QuadCurve(430, 240, 442, 230, 465, 250);
        rightEyebrow.setFill(foreheadColor);
        rightEyebrow.setStroke(Color.BLACK);
        rightEyebrow.setStrokeWidth(3.5);

        Shape lips = Shape.subtract(
                new Arc(400, 330, 60, 40, 0, -180),
                new Arc(400, 330, 60, 27, 0, -180));

        Shape teeth = Shape.subtract(
                new Arc(400, 330, 60, 27, 0, -180),
                new Arc(400, 330, 60, 15, 0, -180)
        );
        teeth.setFill(Color.WHITE);

        root.getChildren().addAll(
                body, forehead,
                leftWhiteEye, leftApple, leftBlackEye, leftCrystalline, leftEyebrow,
                rightWhite, rightApple, rightEye, rightCrystalline, rightEyebrow,
                lips, teeth);

        //Animation
        Path movement = new Path(
                new MoveTo(120, 120),
                new CubicCurveTo(200, 10, 400, 430, 600, 400)
        );

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(3000));
        pathTransition.setPath(movement);
        pathTransition.setNode(root);
        pathTransition.setAutoReverse(true);

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(2000), root);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(2);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(2000), root);
        scaleTransition.setToX(0.4);
        scaleTransition.setToY(0.4);
        scaleTransition.setAutoReverse(true);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                rotateTransition,
                scaleTransition,
                pathTransition
        );
        parallelTransition.setCycleCount(Timeline.INDEFINITE);
        parallelTransition.setAutoReverse(true);
        parallelTransition.play();

        primaryStage.setResizable(false);
        primaryStage.setTitle("Smile :)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
