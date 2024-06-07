import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AnimationManager {
    private List<AnimatedWord> animatedWords;
    private int[] cellOffsets;

    public AnimationManager() {
        animatedWords = new ArrayList<>();
        cellOffsets = new int[5];
    }

    private int calculateCellOffset(int row) {
        int offset = cellOffsets[row];
        if (offset != 0) {
            offset += 50;
        }
        return offset;
    }

    public void addAnimatedWord(String word, int row) {
        int cellHeight = 60;
        int panelHeight = 600;
        int startY = panelHeight - (5 * cellHeight) - 80;
        int startX = 120;
        Point startPosition = new Point(startX, startY);

        int offset = calculateCellOffset(row);

        FontMetrics metrics = Toolkit.getDefaultToolkit().getFontMetrics(new Font("Arial", Font.PLAIN, 14));
        int wordWidth = metrics.stringWidth(word);
        
        Point targetPosition = new Point(startX + 200 + offset, startY + 10 + (row * cellHeight));
        cellOffsets[row] = offset + wordWidth;

        animatedWords.add(new AnimatedWord(word, startPosition, targetPosition, 7));
    }

    
    public void removeAnimatedWord(String word) {
        AnimatedWord wordToRemove = null;
        for (AnimatedWord animatedWord : animatedWords) {
            if (animatedWord.getWord().equals(word)) {
                wordToRemove = animatedWord;
                break;
            }
        }
        if (wordToRemove != null) {
            Point targetPosition = wordToRemove.getTargetPosition();
            int row = (targetPosition.y - (600 - (5 * 60) - 80) - 10) / 60;
            int wordWidth = Toolkit.getDefaultToolkit().getFontMetrics(new Font("Arial", Font.PLAIN, 14)).stringWidth(word);
    
            animatedWords.remove(wordToRemove);
    
            for (AnimatedWord animatedWord : animatedWords) {
                Point currentTargetPosition = animatedWord.getTargetPosition();
                if ((currentTargetPosition.y - (600 - (5 * 60) - 80) - 10) / 60 == row && currentTargetPosition.x > targetPosition.x) {
                    currentTargetPosition.x -= wordWidth + 50;
                    animatedWord.setTargetPosition(currentTargetPosition);
                }
            }
    
            cellOffsets[row] -= wordWidth + 50;
        }
    }
    
    public void updateAnimation() {
        for (AnimatedWord word : animatedWords) {
            word.updatePosition();
        }
    }

    public List<AnimatedWord> getAnimatedWords() {
        return animatedWords;
    }
}
