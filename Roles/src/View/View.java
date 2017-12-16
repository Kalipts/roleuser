package View;

import javax.swing.*;
import java.awt.*;

public class View  extends JFrame{


    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu searchMenu;
    private JMenu helpMenu;
    private JMenuItem loginItem;
    private JMenuItem logoutItem;
    private JMenuItem exitItem;
    private JPanel mainPanel;
    private JTable table;
    private JScrollPane scroller;
    private JLabel titleLabel;
    private JPanel titlePanel;

    public View() {
        super("User Management");

        titlePanel = new JPanel();
        titleLabel = new JLabel("<html><h1 style='color:#4d79ff'><i><b>"+"User Account Information" + "</b></i></h1><html>");
        titlePanel.add(titleLabel);
        table = new JTable();
        scroller = new JScrollPane(table);
        mainPanel = new JPanel();
        this.setSize(550,500);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        setJMenuBar(createMenuBar());
        this.setLayout(new BorderLayout());
        mainPanel.setLayout(new BorderLayout());
        titlePanel.setBorder(BorderFactory.createEtchedBorder());
        mainPanel.setBorder(BorderFactory.createTitledBorder("Nickname (Admin/user)"));

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(scroller, BorderLayout.CENTER);
        mainPanel.setVisible(false);
        this.setVisible(true);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
    private JMenuBar createMenuBar() {
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        searchMenu = new JMenu("Search");
        helpMenu = new JMenu("Help");

        menuBar.add(fileMenu);
        menuBar.add(searchMenu);
        menuBar.add(helpMenu);


        loginItem = new JMenuItem("Login");
        logoutItem = new JMenuItem("Logout");
        exitItem = new JMenuItem("Exit");
        fileMenu.add(loginItem);
        fileMenu.add(logoutItem);
        fileMenu.add(exitItem);

        this.add(menuBar);
        return menuBar;
    }

    public JTable getTable() {
        return table;
    }

    public JScrollPane getScroller() {
        return scroller;
    }

    public JMenuItem getExitItem() {
        return exitItem;
    }
    public JMenuItem getLoginItem() {
        return loginItem;
    }
}
