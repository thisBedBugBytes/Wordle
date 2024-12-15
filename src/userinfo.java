import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class userinfo implements ActionListener {
     JButton submit, next;
     JRadioButton yes, no;
    JTextField txt1;
    JPasswordField txt2;
    JLabel label1, label2;
    JFrame window;
    static GameWin nw;
    //static Stat stat;
    private char ch;
    JPanel panel;
 private User temp;
   private int right, wrong;



    public userinfo() {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());

        ButtonGroup group = new ButtonGroup();

        yes = new JRadioButton("yes");
        yes.setForeground(Color.MAGENTA);
        yes.setBackground(Color.DARK_GRAY);
        yes.setFont(new Font("Comic Sans", Font.BOLD, 20));
        // yes.setPreferredSize(new Dimension(50,20));
        no = new JRadioButton("no");
        no.setForeground(Color.MAGENTA);
        no.setBackground(Color.DARK_GRAY);
        no.setFont(new Font("Comic Sans", Font.BOLD, 20));
        //no.setPreferredSize(new Dimension(20,20));

        yes.addActionListener(this);
        no.addActionListener(this);
        group.add(yes);
        group.add(no);

        submit = new JButton("submit");
        submit.addActionListener(this);

        next = new JButton("next");
        next.addActionListener(this);

        txt1 = new JTextField();
        txt2 = new JPasswordField();

        label1 = new JLabel("Name");
        label1.setForeground(Color.pink);
        label1.setFont(new Font("Comic Sans", Font.BOLD, 20));

        label2 = new JLabel("Password");
        label2.setForeground(Color.pink);
        label2.setFont(new Font("Comic Sans", Font.BOLD, 20));

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel l = new JLabel("Are you playing for the first time?");
        l.setForeground(Color.pink);
        l.setFont(new Font("Comic Sans", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        panel.add(l, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        panel.add(yes, gbc);

        panel.setBackground(Color.DARK_GRAY);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        panel.add(no, gbc);


        window.add(panel, BorderLayout.CENTER);
        window.getContentPane().setBackground(Color.BLACK);
        window.setSize(500, 500);
        window.setVisible(true);

    }

    String password = "", name = "";

    @Override
    public void actionPerformed(ActionEvent e) {
        temp = new User();

        if (e.getSource() == yes) {
            panel.removeAll();
            panel.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            panel.add(label1, gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridwidth = 3;
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panel.add(txt1, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            panel.add(label2, gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 3;
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panel.add(txt2, gbc);

            gbc.gridx = 2;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            panel.add(submit, gbc);

            panel.repaint();
            panel.revalidate();

            ch = 'y';
        } else if (e.getSource() == no) {
            panel.removeAll();
            panel.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            panel.add(label1, gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridwidth = 3;
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panel.add(txt1, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            panel.add(label2, gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 3;
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panel.add(txt2, gbc);

            gbc.gridx = 2;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            panel.add(submit, gbc);

            panel.repaint();
            panel.revalidate();

            ch = 'n';
        } else if (e.getSource() == submit) {

            if (ch == 'y') {

                temp.setUser_name(txt1.getText());
                name = txt1.getText();
                password = new String(txt2.getPassword());
                temp.setPassword(password);

                try {
                    temp.Writer(temp.getPassword() + temp.getUser_name(), temp.getrAttempt(), temp.getwAttempt());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                panel.removeAll();
                panel.add(next);
                panel.revalidate();
                panel.repaint();

            } else if (ch == 'n') {
                name = txt1.getText();
                password = new String(txt2.getPassword());
                String str = password + txt1.getText();
                try {
                    if (temp.Verify(str)) {
                        int[] updated = temp.upDate(str, right, wrong);
                        right = updated[0];
                        wrong = updated[1];
                        panel.removeAll();
                        panel.add(next);
                        panel.revalidate();
                        panel.repaint();
                    } else {





                        JOptionPane.showMessageDialog(panel, "Entries don't match. Try again");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            }
        } else if (e.getSource() == next) {
            window.setVisible(false);
            try {
                nw = new GameWin(name, password, right, wrong);


            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        }

    }


}
