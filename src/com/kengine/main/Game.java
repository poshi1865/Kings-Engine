package com.kengine.main;

import com.kengine.entities.Beam;
import com.kengine.entities.Paddle;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {

    private int WIDTH;
    private int HEIGHT;
    private String title;

    private boolean running = true;

    private Keyboard key;
    private Paddle pad1;
    private Paddle pad2;
    private Beam beam;


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

        pad1 = new Paddle(200, 10);
        pad2 = new Paddle(800, 10);
        beam = new Beam(pad1.x + 10,pad1.y + 10,10, 5, 1, 20 );

    }

    /*
    Does everything that has to do with rendering on the screen
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        super.paintComponent(graphics);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //clear screen
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);


        //Draw Paddle 1
        graphics.setColor(Color.BLACK);
        graphics.fillRect(pad1.x, pad1.y, pad1.width, pad1.height);
        //Draw Paddle 2
        graphics.setColor(Color.BLACK);
        graphics.fillRect(pad2.x, pad2.y, pad2.width, pad2.height);

        //Draw Beam
        graphics.setColor(Color.GREEN);
        graphics.fillOval(beam.x, beam.y, beam.width, beam.height);

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
        if (beam.x == pad2.x-10 && beam.direction == 1)
        {
            beam.direction = -1;
        }
        else if (beam.x == pad1.x-10 && beam.direction == -1)
        {
            beam.direction = 1;
        }

        beam.x = beam.x + beam.direction * beam.speed;


        if (key.up) {
            pad1.y--;
            System.out.println("UP");
        }
        if (key.down) {
            pad1.y++;
            System.out.println("DOWN");
        }
        if (key.left) {
            pad1.x--;
            System.out.println("LEFT");
        }
        if (key.right) {
            pad1.x++;
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
