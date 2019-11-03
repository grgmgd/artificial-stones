package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.javatuples.Pair;

public class Visualization extends JFrame {
    JPanel main;
    JLabel bg;
    private String bglink;
    ArrayList<SearchTreeNode> states;
    String grid;
    JLabel[][] mygrid;
    JPanel p_map = new JPanel();

    public Visualization(ArrayList<SearchTreeNode> pathStates, String grid) throws InterruptedException {
        this.grid = grid;
        this.states = pathStates;
        initiatMap();
    }

    public void initiatMap() throws InterruptedException {
        Border border = LineBorder.createGrayLineBorder();
        String[] parts = grid.split(";");
        String[] dimentions = parts[0].split(",");
        FrameDragListener frameDragListener = new FrameDragListener(this);
        mygrid = new JLabel[Integer.parseInt(dimentions[0])][Integer.parseInt(dimentions[1])];
        main = new JPanel();
        main.setLayout(new BorderLayout());
        bglink = "assets/background.jpg";
        setTitle("Searching Agent");
        setSize(1200, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(main, BorderLayout.CENTER);
        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon(bglink)));
        p_map.setLayout(new GridLayout(Integer.parseInt(dimentions[0]), Integer.parseInt(dimentions[1])));
        p_map.setBounds(200, 50, 800, 600);
        p_map.setOpaque(true);
        p_map.setBackground(new Color(0, 0, 0, 90));
        for (int i = 0; i < Integer.parseInt(dimentions[0]); i++) {
            for (int j = 0; j < Integer.parseInt(dimentions[1]); j++) {
                mygrid[i][j] = new JLabel();
                mygrid[i][j].setOpaque(true);
                mygrid[i][j].setBackground(new Color(0, 0, 0, 90));
                mygrid[i][j].setBorder(border);
                mygrid[i][j].setVisible(true);
                p_map.add(mygrid[i][j]);
            }
        }
        setSize(1199, 699);
        setSize(1200, 700);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);
        this.setLocationRelativeTo(null);
        this.add(p_map);
        this.setVisible(true);
        startGame(this.states);
    }

    public void setMap(SearchTreeNode stateNode, String grid) {
        State state = stateNode.getState();
        String[] parts = grid.split(";");
        String[] thanosLocationString = parts[2].split(",");
        Pair<Integer, Integer> thanosPosition = new Pair<Integer, Integer>(Integer.parseInt(thanosLocationString[0]),
                Integer.parseInt(thanosLocationString[1]));
        mygrid[thanosPosition.getValue0()][thanosPosition.getValue1()].setIcon(new ImageIcon("assets/thanos_1.png"));
        mygrid[thanosPosition.getValue0()][thanosPosition.getValue1()].setVisible(true);
        mapStones(state.getRemainingStones(), mygrid);
        updateIronMan(stateNode, mygrid);
        mapWariors(stateNode, mygrid);
        if (state.snapped) {
            mygrid[thanosPosition.getValue0()][thanosPosition.getValue1()].setIcon(new ImageIcon());
            mygrid[thanosPosition.getValue0()][thanosPosition.getValue1()].setVisible(true);
        }
        this.setVisible(true);
    }

    public void snap() {
        this.remove(p_map);
        Border border = LineBorder.createGrayLineBorder();
        JLabel snap = new JLabel();
        snap.setBounds(100, 70, 1000, 550);
        snap.setOpaque(true);
        snap.setBorder(border);
        snap.setIcon(new ImageIcon("assets/i_am_iron_man.gif"));
        this.add(snap);
    }

    public void updateIronMan(SearchTreeNode node, JLabel[][] grid) {
        State prevState = node.getParent().getState();
        Pair<Integer, Integer> prevIronMan = prevState.getPosition();
        grid[prevIronMan.getValue0()][prevIronMan.getValue1()].setIcon(new ImageIcon());
        ;
        grid[prevIronMan.getValue0()][prevIronMan.getValue1()].setVisible(true);
        State state = node.getState();
        Pair<Integer, Integer> ironMan = state.getPosition();
        grid[ironMan.getValue0()][ironMan.getValue1()].setIcon(new ImageIcon("assets/iron_man.png"));
        ;
        grid[ironMan.getValue0()][ironMan.getValue1()].setVisible(true);
        if (state.getRemainingStones().contains(prevIronMan))
            mapStones(state.getRemainingStones(), grid);
    }

    public void mapWariors(SearchTreeNode node, JLabel[][] grid) {
        State prevState = node.getParent().getState();
        ArrayList<Pair<Integer, Integer>> prevWariors = prevState.getWarriorsLocations();
        for (Pair<Integer, Integer> position : prevWariors) {
            grid[position.getValue0()][position.getValue1()].setIcon(new ImageIcon());
            ;
            grid[position.getValue0()][position.getValue1()].setVisible(true);
        }
        State state = node.getState();
        ArrayList<Pair<Integer, Integer>> wariors = state.getWarriorsLocations();
        for (Pair<Integer, Integer> position : wariors) {
            grid[position.getValue0()][position.getValue1()].setIcon(new ImageIcon("assets/warrior.png"));
            ;
            grid[position.getValue0()][position.getValue1()].setVisible(true);
        }
    }

    public void mapStones(ArrayList<Pair<Integer, Integer>> stones, JLabel[][] grid) {
        for (Pair<Integer, Integer> stone : stones) {
            grid[stone.getValue0()][stone.getValue1()].setIcon(new ImageIcon("assets/stone_1.png"));
            grid[stone.getValue0()][stone.getValue1()].setVisible(true);
        }
    }

    public void startGame(ArrayList<SearchTreeNode> states) throws InterruptedException {
        for (SearchTreeNode node : states) {
            Thread.sleep(500);
            setMap(node, grid);
            this.setSize(1200, 699);
            this.setSize(1200, 700);
        }
    }

    public static void main(String[] args) throws Exception {
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
