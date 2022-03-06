package com.kengine.main;

import com.kengine.entities.Beam;
import com.kengine.entities.Paddle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game extends JPanel {

    public static int WIDTH;
    public static int HEIGHT;
    private String title;

    private boolean running = true;

    private Paddle pad1;
    private Paddle pad2;
    private ArrayList<Beam> beamArray;

    private Keyboard key;

    long currentTime;
    long lastBeamFiredTime = 0;

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

        beamArray = new ArrayList<Beam>();
        pad1 = new Paddle(150, 10);
        pad2 = new Paddle(900, 10);

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
        for(int i = 0; i< beamArray.size(); i++) {
            beamArray.get(i).render(graphics);
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
        currentTime = System.currentTimeMillis();

        //player 1 movement and firing
        if(key.s) {
            if (pad1.y != HEIGHT - pad1.height) {
                pad1.y += pad1.speed;
            }
        }
        if(key.w) {
            if (pad1.y != 0) {
                pad1.y -= pad1.speed;
            }
        }
        if(key.q){
            if (currentTime - lastBeamFiredTime > 200) {
                lastBeamFiredTime = System.currentTimeMillis();
                Beam tempBeam=new Beam(pad1.x + 10, pad1.y + pad1.height/2, 15, 8, 1, 0, 5);
                beamArray.add(tempBeam);
            }
        }

        //player 2 movement and firing
        if(key.down) {
            if (pad2.y != HEIGHT - pad2.height) {
                pad2.y += pad2.speed;
            }
        }
        if(key.up) {
            if (pad2.y != 0) {
                pad2.y -= pad2.speed;
            }
        }
        key.update();

        //beams location updation
        for (int i = 0; i < beamArray.size(); i++){
            beamArray.get(i).update();
        }

        /* CHECKING FOR COLLISIONS */

        //beam collision for paddle 1
        for (int i = 0; i < beamArray.size(); i++) {
            if (beamArray.get(i).intersects(pad2.x, pad2.y, pad2.width, pad2.height)) {
                beamArray.remove(i);
                System.out.println("Beam Array Size: " + beamArray.size());
            }
            else if (beamArray.get(i).x > WIDTH || beamArray.get(i).y > HEIGHT
                || beamArray.get(i).x < 0 || beamArray.get(i).y < 0) {
                beamArray.remove(i);
                System.out.println("Beam Array Size: " + beamArray.size());
            }
        }



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
