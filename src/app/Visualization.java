package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
// import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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

    public Visualization(ArrayList<SearchTreeNode> pathStates, String grid) throws InterruptedException {
        this.grid = grid;
        this.states = pathStates;
        initiatMap();
    }

    public void initiatMap() throws InterruptedException {
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
        bglink = "BackGround.jpeg";
        this.setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon(bglink)));
        JButton b_exit = new JButton();
        b_exit.setName("close");
        b_exit.setBounds(500, 595, 200, 100);
        // b_exit.addActionListener((ActionListener) this);
        b_exit.setOpaque(true);
        b_exit.setContentAreaFilled(false);
        b_exit.setIcon(new ImageIcon("EXIT.png"));
        b_exit.setVisible(true);
        b_exit.setBorderPainted(false);
        add(b_exit);
        String[] parts = grid.split(";");
        String[] dimentions = parts[0].split(",");
        JPanel p_map = new JPanel();
        p_map.setLayout(new GridLayout(Integer.parseInt(dimentions[0]),Integer.parseInt(dimentions[1])));
        p_map.setBounds(200,50,800,600);
        p_map.setOpaque(true);
        p_map.setBackground(new Color(0,0,0,90));
        this.add(p_map);
        mygrid = new JLabel[Integer.parseInt(dimentions[0])][Integer.parseInt(dimentions[1])];
        Border border = LineBorder.createGrayLineBorder();
        for(int i = 0;i<Integer.parseInt(dimentions[0]);i++)
        {
            for(int j =0;j<Integer.parseInt(dimentions[1]);j++)
            {
                mygrid[i][j] = new JLabel();
                mygrid[i][j].setOpaque(true);
                // mygrid[i][j].setName("mapcell-"+i+""+j);
                // mygrid[i][j].setIcon(new ImageIcon("AvengersLogo.png"));
                mygrid[i][j].setBorder(border);
                mygrid[i][j].setVisible(true);
                p_map.add(mygrid[i][j]);
            }
        }
        setSize(1199,699);
        setSize(1200,700);
        this.setVisible(true);
        startGame(this.states);
    }

    public void setMap(SearchTreeNode stateNode, String grid){
        State state = stateNode.getState();
        String[] parts = grid.split(";");
        String[] thanosLocationString = parts[2].split(",");
        Pair<Integer, Integer> thanosPosition = new Pair<Integer, Integer>(Integer.parseInt(thanosLocationString[0]),
				Integer.parseInt(thanosLocationString[1]));
        mygrid[thanosPosition.getValue0()][thanosPosition.getValue1()].setName("Thanos");
        mygrid[thanosPosition.getValue0()][thanosPosition.getValue1()].setText("Thanos");
        mygrid[thanosPosition.getValue0()][thanosPosition.getValue1()].setHorizontalTextPosition(JLabel.CENTER);
        mygrid[thanosPosition.getValue0()][thanosPosition.getValue1()].setVerticalTextPosition(JLabel.CENTER); 
        mygrid[thanosPosition.getValue0()][thanosPosition.getValue1()].setVisible(true);
        mapStones(state.getRemainingStones(), mygrid);
        mapWariors(state.getWarriorsLocations(), mygrid);
        updateIronMan(stateNode, mygrid);
        if(state.snapped){
            mygrid[thanosPosition.getValue0()][thanosPosition.getValue1()].setName("");
            mygrid[thanosPosition.getValue0()][thanosPosition.getValue1()].setText("");
            mygrid[thanosPosition.getValue0()][thanosPosition.getValue1()].setVisible(true);
        }
        this.setVisible(true);
    }

    public void updateIronMan(SearchTreeNode node, JLabel[][] grid){
        State prevState = node.getParent().getState();
        Pair<Integer, Integer> prevIronMan = prevState.getPosition();
        grid[prevIronMan.getValue0()][prevIronMan.getValue1()].setName("");
        grid[prevIronMan.getValue0()][prevIronMan.getValue1()].setText("");
        grid[prevIronMan.getValue0()][prevIronMan.getValue1()].setVisible(true);
        State state = node.getState();
        Pair<Integer, Integer> ironMan = state.getPosition();
        grid[ironMan.getValue0()][ironMan.getValue1()].setName("IronMan");
        grid[ironMan.getValue0()][ironMan.getValue1()].setText("IronMan");
        grid[ironMan.getValue0()][ironMan.getValue1()].setHorizontalTextPosition(JLabel.CENTER);
        grid[ironMan.getValue0()][ironMan.getValue1()].setVerticalTextPosition(JLabel.CENTER); 
        grid[ironMan.getValue0()][ironMan.getValue1()].setVisible(true);
    }

    public void mapWariors(ArrayList<Pair<Integer, Integer>> wariors, JLabel[][] grid){
        for(Pair<Integer, Integer> position : wariors) {
            grid[position.getValue0()][position.getValue1()].setName("Warrior");
            grid[position.getValue0()][position.getValue1()].setText("Warrior");
            grid[position.getValue0()][position.getValue1()].setHorizontalTextPosition(JLabel.CENTER);
            grid[position.getValue0()][position.getValue1()].setVerticalTextPosition(JLabel.CENTER); 
            grid[position.getValue0()][position.getValue1()].setVisible(true);
        }
    }

    public void mapStones(ArrayList<Pair<Integer, Integer>> stones, JLabel[][] grid){
        for(Pair<Integer, Integer> stone : stones) {
            grid[stone.getValue0()][stone.getValue1()].setName("Stone");
            grid[stone.getValue0()][stone.getValue1()].setText("Stone");
            grid[stone.getValue0()][stone.getValue1()].setHorizontalTextPosition(JLabel.CENTER);
            grid[stone.getValue0()][stone.getValue1()].setVerticalTextPosition(JLabel.CENTER); 
            grid[stone.getValue0()][stone.getValue1()].setVisible(true);
        }
    }

    public void startGame(ArrayList<SearchTreeNode> states) throws InterruptedException {
        for(SearchTreeNode node : states){
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
