import java.awt.*;

public class AnimatedWord {
    private String word;
    private Point currentPosition;
    private Point targetPosition;
    private int stepSize;
    private boolean verticalMoveComplete;
    private int targetWidth;
    private int targetHeight;
    private int currentWidth;
    private int currentHeight;
    private int sizeStep = 5;
    private float opacity;
    private Point currentTargetPosition;

    public AnimatedWord(String word, Point startPosition, Point targetPosition, int stepSize) {
        this.word = word;
        this.currentPosition = startPosition;
        this.targetPosition = targetPosition;
        this.stepSize = stepSize;
        this.verticalMoveComplete = false;

        FontMetrics metrics = Toolkit.getDefaultToolkit().getFontMetrics(new Font("Arial", Font.PLAIN, 14));
        this.targetWidth = metrics.stringWidth(word) + 10;
        this.targetHeight = metrics.getHeight();
        this.currentWidth = 0;
        this.currentHeight = targetHeight;
        this.opacity = 0.0f;
    }

    public void updatePosition() {
        
            if (!verticalMoveComplete) {
                // y
                if (currentPosition.y < targetPosition.y) {
                    currentPosition.y = Math.min(currentPosition.y + stepSize, targetPosition.y);
                } else {
                    verticalMoveComplete = true;
                }
            } else {
                // x
                if (currentPosition.x < targetPosition.x) {
                    currentPosition.x = Math.min(currentPosition.x + stepSize, targetPosition.x);
                } else if (currentPosition.x > targetPosition.x) {
                    currentPosition.x = Math.max(currentPosition.x - stepSize, targetPosition.x);
                }
            }

        if (opacity < 1.0f) {
            opacity = Math.min(opacity + 0.007f, 1.0f);
        }

        if (currentWidth < targetWidth) {
            currentWidth = Math.min(currentWidth + sizeStep, targetWidth);
        }
    }

    public String getWord() {
        return word;
    }

    public Point getCurrentPosition() {
        return currentPosition;
    }

    public Point getTargetPosition() {
        return targetPosition;
    }

    public int getCurrentWidth() {
        return currentWidth;
    }

    public int getCurrentHeight() {
        return currentHeight;
    }

    public float getOpacity() {
        return opacity;
    }

    public boolean reachedTargetPosition() {
        return currentPosition.equals(targetPosition);
    }

    void setTargetPosition(Point currentTargetPosition) {
        this.currentTargetPosition = currentTargetPosition;
    }

    void setCurrentPosition(Point currentPosition) {
        this.currentPosition = currentPosition;
    }
}
