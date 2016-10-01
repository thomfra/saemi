package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GuiInflater {

    private final int HEIGHT = 800;
    private final int WIDTH = 400;
    private final int CONTENT_PANE_BORDER = 10;
    private JFrame frame;
    private JPanel contentPane;
    private JPanel documentPanel;
    private JPanel buttonGroupPanel;

    public GuiInflater() {
        initFrame();
        initContentPane();
        initDocumentPanel();
        initButtonGroupPanel();
        attachComponentsToContentPane();
        attachViewToFrame();
    }

    private void initFrame() {
        frame = new JFrame();
        frame.setSize(HEIGHT, WIDTH);
        frame.setTitle("Data converter for Saemi");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initContentPane() {
        contentPane = new JPanel();
        contentPane.setBackground(Color.GRAY);
        contentPane.setSize(HEIGHT, WIDTH);

        Border borderFactory = BorderFactory.createEmptyBorder(CONTENT_PANE_BORDER, CONTENT_PANE_BORDER, CONTENT_PANE_BORDER, CONTENT_PANE_BORDER);
        contentPane.setBorder(borderFactory);

        GridLayout layout = new GridLayout(0,2);
        contentPane.setLayout(layout);
    }

    private void initDocumentPanel() {
        documentPanel = new JPanel();
        documentPanel.setSize(HEIGHT, WIDTH/2);
        documentPanel.setBackground(Color.WHITE);
    }

    private void attachComponentsToContentPane() {
        contentPane.add(documentPanel);
        contentPane.add(buttonGroupPanel);
    }

    private void initButtonGroupPanel(){
        buttonGroupPanel = new JPanel();
        buttonGroupPanel.setBackground(Color.GRAY);
        buttonGroupPanel.setSize(HEIGHT, WIDTH/2);

        GridLayout layout = new GridLayout(0,2);
        buttonGroupPanel.setLayout(layout);

        buttonGroupPanel.add(new JButton("Upload files"));
        buttonGroupPanel.add(new JButton("Button 2"));
        buttonGroupPanel.add(new JButton("Button 3"));
        buttonGroupPanel.add(new JButton("Button 4"));
    }

    private void attachViewToFrame() {
        frame.add(contentPane);
    }

    public void showGui(){
        frame.setVisible(true);
    }
}
