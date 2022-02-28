package com.kengine.main;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {

    private int WIDTH;
    private int HEIGHT;
    private String title;

    public Game(String title, int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.title = title;
        init();
    }

    @Override
    public void paintComponent(Graphics g) {

    }

    //Initialize the Game Panel
    private void init() {
        setBackground(Color.BLACK);
        Dimension screenDimension = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(screenDimension);
    }

    private void update() {

    }

    private void render() {

    }

    public static void main(String[] args) {
        Game game = new Game("Kings-Engine", 1080, 720);

        //Making the JFrame
        JFrame frame = new JFrame(game.title);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
