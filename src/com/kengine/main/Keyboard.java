package com.kengine.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    public boolean[] keys = new boolean[180];
    public boolean i, e, w, a, s, d, q, o, l, k, semiColon;

    public void update() {

        //Player1
        w = keys[KeyEvent.VK_W];
        s = keys[KeyEvent.VK_S];
        a = keys[KeyEvent.VK_A];
        d = keys[KeyEvent.VK_D];
        q = keys[KeyEvent.VK_Q];
        e = keys[KeyEvent.VK_E];

        //Player2
        o = keys[KeyEvent.VK_O];
        l = keys[KeyEvent.VK_L];
        k = keys[KeyEvent.VK_K];
        semiColon = keys[KeyEvent.VK_SEMICOLON];
        i = keys[KeyEvent.VK_I];

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
