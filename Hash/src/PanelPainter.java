import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.List;

public class PanelPainter {
    private HashTable model;
    private AnimationManager animationManager;
    private Image image;

    public PanelPainter(HashTable model, AnimationManager animationManager, Image image) {
        this.model = model;
        this.animationManager = animationManager;
        this.image = image;
    }

    public void paintComponent(Graphics g, int panelWidth, int panelHeight) {
        Graphics2D g2d = (Graphics2D) g;

        if (image != null) {
            g2d.drawImage(image, 0, 0, panelWidth, panelHeight, null);
        }

        g2d.setFont(new Font("Arial", Font.CENTER_BASELINE, 54));
        g2d.setColor(new Color(255, 95, 31));
        g2d.drawString("HashTable", 250, 100);

        int rows = model.getCapacity();
        int cols = 1;
        int cellWidth = 150;
        int cellHeight = 60;

        Color borderColor = Color.BLACK;
        Color fillColor = Color.LIGHT_GRAY;
        Color arrowColor = new Color(255, 95, 31);

        int startX = 100;
        int startY = panelHeight - (rows * cellHeight) - 50;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // Draw cell
                int x = startX + col * cellWidth;
                int y = startY + row * cellHeight;
                g.setColor(fillColor);
                g.fillRect(x, y, cellWidth, cellHeight);
                g.setColor(borderColor);
                g.drawRect(x, y, cellWidth, cellHeight);

                // Draw arrows
                int arrowStartX = x + cellWidth + 10;
                int arrowStartY = y + (cellHeight / 2) - 5;
                g.setColor(arrowColor);
                g.fillRect(arrowStartX, arrowStartY, 20, 10);

                GeneralPath triangle = new GeneralPath();
                triangle.moveTo(arrowStartX + 20, arrowStartY - 5);
                triangle.lineTo(arrowStartX + 20, arrowStartY + 15);
                triangle.lineTo(arrowStartX + 30, arrowStartY + 5);
                triangle.closePath();
                g2d.fill(triangle);
            }
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));

        FontMetrics metrics = g.getFontMetrics(g.getFont());

        List<AnimatedWord> animatedWords = animationManager.getAnimatedWords();
        for (AnimatedWord word : animatedWords) {
            Point currentPos = word.getCurrentPosition();
            Point targetPos = word.getTargetPosition();
            String text = word.getWord();
            int wordWidth = metrics.stringWidth(text) + 10;
            int wordHeight = metrics.getHeight();

            // rect
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, word.getOpacity()));
            g.setColor(fillColor);
            g.fillRect(targetPos.x, targetPos.y, wordWidth, wordHeight);
            g.setColor(borderColor);
            g.drawRect(targetPos.x, targetPos.y, wordWidth, wordHeight);

            // word
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            g.drawString(text, currentPos.x + 5, currentPos.y + wordHeight - 5);
        }
    }
}
