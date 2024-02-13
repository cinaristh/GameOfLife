import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Rectangle2D;


public class Main extends JFrame {
    Cell grid[][] = new Cell[40][40];
    Timer t;
    PaintSurface canvas = new PaintSurface();

    public static void main(String[] args) {
        // write your code here
        new Main();



    }

    public Main() {
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("Game of Life...");
        this.add(canvas);
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Game of Life");
        titlePanel.add(titleLabel);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(canvas, BorderLayout.CENTER);

        this.add(new LowerPanel(), BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);


        t = new Timer(1000, e -> {
            update();
            canvas.repaint();
        });

        this.setVisible(true);

        for (int x = 0; x < 40; x = x + 1) {
            for (int y = 0; y < 40; y = y + 1) {
                if (Math.random() > 0.5) {
                    grid[x][y] = new Cell(true);
                } else {
                    grid[x][y] = new Cell(false);
                }
            }
        }


    }

    class LowerPanel extends JPanel{
        public LowerPanel(){
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            this.add(Box.createHorizontalStrut(5));

            //JPanel lowerPanel = new JPanel();
            JButton startButton = new JButton("Start");
            startButton.addActionListener(e-> startButtonClick());
            JButton stopButton = new JButton("Stop");
            stopButton.addActionListener(e-> stopButtonClick());
            JButton stepButton = new JButton("Step");
            stepButton.addActionListener(e-> stepButtonClick());
            JButton resetButton = new JButton("Reset");
            resetButton.addActionListener(e-> resetButtonClick());


            this.add(startButton);
            this.add(Box.createRigidArea(new Dimension(5,40)));
            this.add(stopButton);
            this.add(Box.createRigidArea(new Dimension(5,40)));
            this.add(stepButton);
            this.add(Box.createHorizontalGlue());
            this.add(resetButton);
            this.add(Box.createRigidArea(new Dimension(5,40)));
            this.add(Box.createHorizontalStrut(5));




        }
    }


    class PaintSurface extends JComponent {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


            Shape s = new Rectangle2D.Float(0, 0, 400, 400);
            g2.setColor(Color.GRAY);
            g2.fill(s);

            for (int x = 0; x < 40; x = x + 1) {
                for (int y = 0; y < 40; y = y + 1) {

                    Shape d = new Rectangle2D.Float(x * 10, y * 10, 8, 8);
                    g2.setColor(Color.black);
                    g2.fill(d);
                    if (grid[x][y].getAlive() == 1) {
                        Shape sq = new Rectangle2D.Float((x * 10), (y * 10), 8, 8);
                        g2.setColor(Color.BLACK);
                        g2.fill(sq);

                    } else {
                        Shape sq = new Rectangle2D.Float((x * 10), (y * 10), 8, 8);
                        g2.setColor(Color.WHITE);
                        g2.fill(sq);

                    }


                }
            }
        }
    }

    public void update() {
        for (int x = 0; x < 40; x = x + 1) {
            for (int y = 0; y < 40; y = y + 1) {
                int newX = x;
                int newY = y;

                if (x - 1 < 0) {
                    newX = 39;
                } else if (x + 1 > 39) {
                    newX = 0;
                }

                if (y - 1 < 0) {
                    newY = 39;
                } else if (y + 1 >39) {
                    newY = 0;
                }

                int count = grid[(newX - 1 + 40) % 40][(newY - 1 + 40) % 40].getAlive() + grid[(newX - 1 + 40) % 40][newY].getAlive() + grid[(newX - 1 + 40) % 40][(newY + 1) % 40].getAlive() + grid[newX][(newY - 1 + 40) % 40].getAlive() + grid[newX][(newY + 1 + 40) % 40].getAlive() + grid[(newX + 1) % 40][(newY - 1 + 40) % 40].getAlive() + grid[(newX + 1) % 40][newY].getAlive() + grid[(newX + 1) % 40][(newY + 1) % 40].getAlive();
                if (count == 2) {
                    grid[x][y].setNextAlive(grid[newX][newY].Alive());
                } else if (count == 3) {
                    grid[x][y].setNextAlive(true);
                } else {
                    grid[x][y].setNextAlive(false);

                }


            }




        }
        for (int x = 0; x < 40; x = x + 1) {
            for (int y = 0; y < 40; y = y + 1) {
                grid[x][y].update();}
        }
    }

    public void setup(){
        for (int x = 0; x < 40; x = x + 1) {
            for (int y = 0; y < 40; y = y + 1) {
                if (Math.random() > 0.5) {
                    grid[x][y].setAlive(true);
                } else {
                    grid[x][y].setAlive(false);
                }
                t.stop();
                
                canvas.repaint();

            }
        }


    }
    private void startButtonClick(){
        t.start();
    }

    private void stopButtonClick(){
        t.stop();
    }

    private void stepButtonClick(){
        update();
        canvas.repaint();
    }


    private void resetButtonClick(){
        setup();
    }



}


