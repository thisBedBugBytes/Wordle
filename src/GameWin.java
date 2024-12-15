import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class GameWin extends wordle implements ActionListener, KeyListener {
    JFrame frame;
    JPanel mainpanel;
    JPanel[] border;
    JPanel[][] grid;
    JLabel[] sticker;
    JButton start, submit;
    StringBuilder sub;
    char ch = ' ';
    private int i, j, idx, right, wrong;
    boolean check;
    private User user;
    barchart bar;

    public GameWin(String s, String p, int r, int w) throws IOException {
        super();
        i = 56;
        j = 50;
        idx = 0;
        user = new User(s, p);
        this.right = r;
        this.wrong = w;
        check = true;
        sub = new StringBuilder();
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setSize(500, 500);

        mainpanel = new JPanel();
        mainpanel.setLayout(new BorderLayout());
        mainpanel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));

        //the borders
        border = new JPanel[5];
        for (int i = 0; i < 4; i++) {
            border[i] = new JPanel();
            if ((i + 1) % 2 == 0) {
                border[i].setPreferredSize(new Dimension(frame.getWidth() / 5, frame.getHeight()));
            } else {
                border[i].setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() / 5));
            }
            border[i].setBackground(Color.DARK_GRAY);


        }
        mainpanel.add(border[0], BorderLayout.NORTH);
        mainpanel.add(border[1], BorderLayout.EAST);
        mainpanel.add(border[2], BorderLayout.SOUTH);
        mainpanel.add(border[3], BorderLayout.WEST);

        border[4] = new JPanel();
        border[4].setLayout(new GridLayout(5, 5, 7, 7));
        border[4].setPreferredSize(new Dimension(3 * frame.getWidth() / 5, 3 * frame.getHeight() / 5));
        border[4].setBackground(Color.DARK_GRAY);

        grid = new JPanel[5][5];
        sticker = new JLabel[5];
        for (int i = 0; i < 5; i++) {
            sticker[i] = new JLabel();
            for (int j = 0; j < 5; j++) {
                grid[i][j] = new JPanel();
                grid[i][j].setBackground(Color.LIGHT_GRAY);
                grid[i][j].addKeyListener(this);
                border[4].add(grid[i][j]);
            }
        }
        border[4].addKeyListener(this);
        border[4].setFocusable(true);
        mainpanel.add(border[4], BorderLayout.CENTER);

        submit = new JButton("Submit");
        submit.addActionListener(this);
        start = new JButton("Start");
        start.addActionListener(this);
        border[2].add(start);


        frame.add(mainpanel);
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            i = 0;
            j = 0;
            border[2].remove(start);
            border[2].add(submit);
            border[4].requestFocusInWindow();
            border[2].revalidate();
        } else if (e.getSource() == submit) {
            border[4].requestFocusInWindow();
            //in case the player reaches the last grid
            if (jidx == 4) {
                submit.setEnabled(false);
                if (!super.getWod().equals(sub.toString())) {
                    JOptionPane.showMessageDialog(border[2], "You lost ;-; The word was "+super.getWod(), "Next Time <3", JOptionPane.PLAIN_MESSAGE);
                    wrong++;
                    }
                else{
                    JOptionPane.showMessageDialog(border[2], "You win!!!", "Done! <3", JOptionPane.PLAIN_MESSAGE);
                    right++;
                }
                    try {
                        user.reWrite(user.getPassword() + user.getUser_name(), right, wrong);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    bar = new barchart("Wordle", user.getUser_name()+"'s Wordle Stats", right, wrong);




            } else if (idx != 4) {
                JOptionPane.showMessageDialog(border[2], "Word needs to be 5 letters long!", "Problem", JOptionPane.PLAIN_MESSAGE);

            } else {
                if (super.containWord(sub.toString())) {
                    if(idx==4) {
                        i = 0;
                        jidx++;
                        System.out.println(jidx);
                        j = jidx;
                    }
                }

                    if (super.getWod().equals(sub.toString())) {
                        submit.setEnabled(false);
                        JOptionPane.showMessageDialog(border[2], "You win!!!", "Done! <3", JOptionPane.PLAIN_MESSAGE);
                        right++;
                        try {
                            String temp = user.getPassword() + user.getUser_name();
                            user.reWrite(temp, right, wrong);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        bar = new barchart("Wordle", user.getUser_name(), right, wrong);

                    } else {
                        int tempidx = 0;
                        while (tempidx < 5) {
                            char tempChar = sub.charAt(tempidx);
                            if (super.isWordle(tempChar)) {
                                if (super.isIdx(tempChar, sub.toString(), tempidx)) {

                              if(j!=50)      grid[j - 1][tempidx].setBackground(Color.green);

                                } else if (super.isCount(tempChar, sub.toString(), tempidx) == 1/*super.isCount(tempChar,super.getWod(),4)*/) {

                                    if(j!=50)    grid[j - 1][tempidx].setBackground(Color.yellow);

                                } else {

                                    if(j!=50)   grid[j - 1][tempidx].setBackground(new Color(35, 40, 50));
                                }
                            }
                            tempidx++;
                        }
                        sub = new StringBuilder();
                        check = true;
                    }

                }
                System.out.println(super.getWod());
            }


        }

    int jidx = 0;

    @Override
    public void keyTyped(KeyEvent e) {
        if(j!=50) jidx = j;
        if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
            ch = e.getKeyChar();
            if(j!=50){     grid[j][i].removeAll();
            grid[j][i].addKeyListener(this);
            grid[j][i].setFocusable(true);
            JLabel label = new JLabel(Character.toString(ch));
            grid[j][i].add(label);
            grid[j][i].revalidate();
            border[4].revalidate();}
            if(i!=56)idx = i;
            if (i < 4) {
                i++;
            } else {

                i = 56;
                j=50;

            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (i != 56) {

            if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE && check) {
                char str = e.getKeyChar();
                if (sub.length() < 6) {
                    sub.append(str);
                   // idx = sub.length()-1;
                } else {
                    check = false;
                }
idx = sub.length()-1;
                System.out.println(check + " " + idx);

            } else {
                if (i == 0) {
                  //  sub.delete(i + 4, i + 5);
                } else if (i > 0) {
                  //  sub.delete(i - 1, i);
                    check = true;

                }

            }
        }
    }
/*
    @Override
    publi0c void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {

            if (i > 0) {
                i--;

            } else if (i == 0 && j < 6) {
                j--;
                i = 4;

            }

if(j!=50)          {  grid[j][i].removeAll();
            JLabel space = new JLabel(" ");
            grid[j][i].add(space);
            grid[j][i].addKeyListener(this);
            grid[j][i].setFocusable(true);
            grid[j][i].revalidate();
        }


        }
    }
*/@Override
public void keyReleased(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
        if (i > 0) {
            i--;
        } else if (i == 0 && j < 6 && j > 0) {
            j--;
            i = 4;
        }
         if(i==3 && j==50){
             j=jidx;
         }


        if (j != 50 ) {
            grid[j][i].removeAll();
            JLabel space = new JLabel(" ");
            grid[j][i].add(space);
            grid[j][i].addKeyListener(this);
            grid[j][i].setFocusable(true);
            grid[j][i].revalidate();
        }
        // Remove the last character from the sub string
        if (sub.length() > 0) {
            sub.deleteCharAt(sub.length() - 1);
            idx = sub.length()-1;
            System.out.println(sub + " " +  idx);
            check = true;
        }
    }
}

}
