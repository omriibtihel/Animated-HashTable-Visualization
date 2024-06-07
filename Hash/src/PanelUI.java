import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PanelUI extends JPanel {
    JTextField textField;
    HashTable model;
    AnimationManager animationManager;
    Timer timer;
    Image image;
    PanelPainter panelPainter;

    public PanelUI() {
        setLayout(null);

        textField = new JTextField();
        textField.setBounds(100, 170, 150, 30);
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));

        model = new HashTable(5);
        animationManager = new AnimationManager();

        try {
            image = ImageIO.read(new File("C:\\Users\\ibtih\\OneDrive\\Bureau\\bg.jpg"));
        } catch (IOException e) {
        }

        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addWord();
            }
        });

        add(textField);

        // Add btn
        RoundedButton addButton = new RoundedButton("Add");
        addButton.setBounds(270, 170, 80, 30);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addWord();
            }
        });
        add(addButton);

        // Remove btn
        RoundedButton removeButton = new RoundedButton("Remove");
        removeButton.setBounds(360, 170, 100, 30);
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeWord();
            }
        });
        add(removeButton);

        timer = new Timer(30, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                animationManager.updateAnimation();
                repaint();
            }
        });
        timer.start();

        panelPainter = new PanelPainter(model, animationManager, image);
    }

    private void addWord() {
        String texteSaisi = textField.getText();
        if (model.size() < 100 && !model.contains(texteSaisi)) {
            model.add(texteSaisi);
            int row = model.hash(texteSaisi);
            animationManager.addAnimatedWord(texteSaisi, row);
            textField.setText("");
            repaint();
        } else {
            JOptionPane.showMessageDialog(null, "Word already exists");
        }
    }

    private void removeWord() {
        String wordToRemove = textField.getText();
        if (!wordToRemove.isEmpty()) {
            boolean removed = model.remove(wordToRemove);
            if (removed) {
                animationManager.removeAnimatedWord(wordToRemove);
                textField.setText("");
                repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Word doesn't exist!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Enter a word");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        panelPainter.paintComponent(g, getWidth(), getHeight());
    }
}
