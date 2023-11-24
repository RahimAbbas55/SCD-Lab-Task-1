import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
//------------------------------------
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//------------------------------------


class mainFrame extends JFrame
{
    private JButton login;
    private JButton signup;
    public mainFrame()
    {
        makeGUI();
    }
    public void makeGUI()
    {
        setTitle("Example Platform");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250 , 400);
        login = new JButton("Login");
        signup = new JButton("Signup");
        JPanel heading = new JPanel();
        heading.add(new JLabel("Do you want to SignIn or LogIn?") , BorderLayout.CENTER);
        JPanel btnpane = new JPanel(new GridLayout(1 , 2));
        btnpane.add(login);
        btnpane.add(signup);
        add(heading , BorderLayout.NORTH);
        add(btnpane , BorderLayout.CENTER);
        pack();
        setVisible(true);

        //to assure that user if they really want to exit
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                int op = JOptionPane.showConfirmDialog
                (mainFrame.this , "Do you want to exit" , "Exit Confirm" , JOptionPane.YES_NO_OPTION );
                if (op == JOptionPane.YES_OPTION)
                {
                    dispose();
                }
                else
                {
                    //do nothing
                }
            }
            
        });
        //Login screen gui
        login.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                logIn obj = new logIn();
            }    
        });
        //SignUp screen gui
        signup.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                signUp obj = new signUp();
            }    
        });
    }
}


class signUp extends JFrame
{
    private JTextField name;
    private JTextField email;
    private JTextField password;
    private JButton signUp;
    public signUp()
    {
        makeGUI();
    }
    public void makeGUI()
    {
        setTitle("Sign Up Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200 , 300);
        name = new JTextField(30);
        email = new JTextField(30);
        password = new JTextField(30);
        signUp = new JButton("Sign Up");
        JPanel pane = new JPanel(new GridLayout(3 , 2));
        JPanel btnPanel = new JPanel();
        pane.add(new JLabel("Enter Username:"));
        pane.add(name);
        pane.add(new JLabel("Enter Email:"));
        pane.add(email);
        pane.add(new JLabel("Enter password"));
        pane.add(password);
        btnPanel.add(signUp);
        add(btnPanel , BorderLayout.SOUTH);
        add(pane , BorderLayout.CENTER);
        pack();
        setVisible(true);

        //Add database functionality later
        signUp.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String n = name.getText();
                String em = email.getText();
                String pass = password.getText();

                System.out.println(n  + " " + em + " " + pass);
            }
        });
    }
}


class logIn extends JFrame
{
    private JTextField name;
    private JTextField email;
    private JTextField password;
    private JButton login;
    public logIn()
    {
        makeGUI();
    }
    public void makeGUI()
    {
        setTitle("Login Form!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200 , 300);
        name = new JTextField(30);
        email = new JTextField(30);
        password = new JPasswordField(30);
        login = new JButton("Login");
        JPanel pane = new JPanel(new GridLayout(3,2)) , btnPane = new JPanel();
        pane.add(new JLabel("Enter Username:"));
        pane.add(name);
        pane.add(new JLabel("Enter Email:"));
        pane.add(email);
        pane.add(new JLabel("Enter password"));
        pane.add(password);
        btnPane.add(login);
        add(pane);
        add(btnPane , BorderLayout.SOUTH);
        setVisible(true);
        pack();

        //Add database functionality later
        login.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String n = name.getText() , em = email.getText() , pass = password.getText();
                System.out.println(n  + " " + em + " " + pass);
            }    
        });
    }
}

class dashboard extends JFrame
{
    public dashboard()
    {
        makeGUI();
    }
    public void makeGUI()
    {
        
    }
}


public class main
{
    public static void main(String [] args)
    {
        // new signUp();
        // new logIn();
        // new mainFrame();
        String jdbcUrl = "jdbc:mysql://localhost:1433/lab3";
        String username = "SA";
        String password = "Password123";

        try 
        {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection.close();
            System.out.println("Hello there!");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
}