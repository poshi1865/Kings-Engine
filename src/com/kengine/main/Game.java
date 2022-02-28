package com.kengine.main;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {

    private int WIDTH;
    private int HEIGHT;
    private String title;

    private boolean running = true;

    private Keyboard key;

    private Paddle pad;

    public Game(String title, int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.title = title;
        init();

        this.setFocusable(true);
        this.requestFocus();
        addKeyListener(key);
    }

    //Initialize the Game Panel
    private void init() {
        Dimension screenDimension = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(screenDimension);

        key = new Keyboard();

        pad = new Paddle(10, 10);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        super.paintComponent(graphics);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //clear screen
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        //Draw
        graphics.setColor(Color.BLACK);
        graphics.fillRect(pad.x, pad.y, pad.width, pad.height);

        graphics.dispose();
    }

    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            repaint();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println(updates + " UPS, " + frames + " FPS");
                updates = 0;
                frames = 0;
            }
        }
    }

    private void update() {
        if (key.up) {
            pad.y--;
            System.out.println("UP");
        }
        if (key.down) {
            pad.y++;
            System.out.println("DOWN");
        }
        if (key.left) {
            pad.x--;
            System.out.println("LEFT");
        }
        if (key.right) {
            pad.x++;
            System.out.println("RIGHT");
        }
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

        game.run();
    }
}
