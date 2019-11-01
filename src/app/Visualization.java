package app;

import java.util.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;

public class Visualization extends JFrame {
    JPanel main;
    JLabel bg;
    private String bglink;

    public Visualization() {
        this.setSize(1200, 700);
        setUndecorated(true);
        setTitle("Search Agend");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        main = new JPanel();
        main.setLayout(new BorderLayout());
        add(main, BorderLayout.CENTER);
        FrameDragListener frameDragListener = new FrameDragListener(this);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);
        bglink = "AvengersLogo.png";
        this.setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon(bglink)));
        JButton b_exit = new JButton();
        b_exit.setName("close");
        b_exit.setBounds(500, 595, 200, 100);
        b_exit.addActionListener((ActionListener) this);
        b_exit.setOpaque(true);
		b_exit.setContentAreaFilled(false);
		b_exit.setIcon(new ImageIcon("EXIT.png"));
		b_exit.setVisible(true);
        b_exit.setBorderPainted(false);
        add(b_exit);
        setSize(1199,699);
        setSize(1200,700);
		this.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
		Visualization p = new Visualization();
	}
    
    public static class FrameDragListener extends MouseAdapter {
    
        private final JFrame frame;
        private Point mouseDownCompCoords = null;
    
        public FrameDragListener(JFrame frame) {
            this.frame = frame;
        }
    
        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
        }
    
        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
        }
    
        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
        }
    }
}
