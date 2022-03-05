package com.kengine.main;

import com.kengine.entities.Beam;
import com.kengine.entities.Paddle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Game extends JPanel {

    public static int WIDTH;
    public static int HEIGHT;
    private String title;

    private boolean running = true;

    private Paddle pad1;
    private Paddle pad2;
    private ArrayList<Beam> beam;
    private int beamCount=0;

    private Keyboard key;

    public Game(String title, int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.title = title;
        init();
    }

    //Initialize the Game Panel
    private void init() {
        Dimension screenDimension = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(screenDimension);

        key = new Keyboard();
        key.update();

        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(key);

        beam = new ArrayList<Beam>();
        pad1 = new Paddle(150, 10);
        pad2 = new Paddle(900, 10);
        for(int i=0; i<beamCount;i++);
        {

        }

    }

    /*
    Does everything that has to do with rendering on the screen
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        //*****************************TURN ON ANTIALIASING******************************************
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //*******************************************************************************************

        //clear screen
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        pad1.render(graphics);
        pad2.render(graphics);

        //Beam rendering stuff
        for(int i=0; i<beamCount; i++) {
            beam.get(0).render(graphics);
        }
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

        /*beam collisions*/



        //Collision with paddles
        if (beam.x == pad2.x - beam.width && beam.directionX == 1) {
            if (beam.y >= pad2.y && beam.y <= pad2.y + pad2.height) {
                beam.directionX = -1;
            }
        }
        else if (beam.x == pad1.x + beam.width && beam.directionX == -1) {
            if (beam.y >= pad1.y && beam.y <= pad1.y + pad1.height) {
                beam.directionX = 1;
            }
        }

        //Collision with walls
        if (beam.x == WIDTH) {
            beam.directionX = -1;
        }
        if (beam.x == 0) {
            beam.directionX = 1;
        }
        if (beam.y == 0) {
            beam.directionY = 1;
        }
        if (beam.y == HEIGHT) {
            beam.directionY = -1;
        }

        //player 1 movement
        if(key.s) {
            pad1.y += pad1.speed;
        }
        if(key.w) {
            pad1.y -= pad1.speed;
        }
        if(key.q){
            beam.add(new Beam(pad1.x + 10, pad1.y + 10, 20, 20, 1, 1, 5));
            beam.x = pad1.x;
            beam.y = pad1.y + pad1.height/2;
        }

        //player 2 movement
        if(key.down) {
            pad2.y += pad2.speed;
        }
        if(key.up) {
            pad2.y -= pad2.speed;
        }
        if(key.pgUp){
            beam.x = pad1.x;
            beam.y = pad1.y + pad1.height/2;
        }
        //if(key.right) {
        //    pad1.x += pad1.speed;
        //}
        //if(key.left) {
        //    pad1.x -= pad1.speed;
        //}
        key.update();

        //beam.x = beam.x + beam.directionX * beam.speed;
        //beam.y = beam.y + beam.directionY * beam.speed;
    }

    private void render() {
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
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
