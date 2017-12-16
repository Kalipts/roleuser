package app.controller;

import View.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.Vector;

import View.LoginView;
import model.DatabaseConnection;

public class Controller {
    private View view;
    private LoginView loginView;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;
    DatabaseConnection databaseConnection;
    public Controller(View view, DatabaseConnection databaseConnection) {
        this.view = view;
        this.databaseConnection = databaseConnection;
        exitEvent();
        loginEvent();
    }

    private void loginEvent() {
        view.getLoginItem().addActionListener(actionEvent -> {
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }


            java.awt.EventQueue.invokeLater(() -> {

                loginView = new LoginView();
                loginView.setAlwaysOnTop(true);
                loginView.setDefaultCloseOperation(3);
                loginView.getCancelButton().addActionListener(actionEvent12 -> loginView.dispose());

                loginView.getOkButton().addActionListener(actionEvent1 -> {
                    String sql = "select * from users where username=? and password=?";
                    try {
                        connection = databaseConnection.DatabaseConnectionMothod();

                        statement = connection.prepareStatement(sql);
                        statement.setString(1, loginView.getUserText().getText());
                        statement.setString(2, loginView.getPasswordText().getText());
                        result = statement.executeQuery();

                        if (result.next()) {
                            view.getMainPanel().setVisible(true);
                            connection = databaseConnection.DatabaseConnectionMothod();
                            String sql_ = "select roles.rolename, users.email, users.username, roles.rolename " +
                                    "from users inner join userrole on users.userid = userrole.userid inner join roles on userrole.roleid = roles.roleid";

                            statement = connection.prepareStatement(sql_);
                            result = statement.executeQuery();
                            Vector<Vector<String>> data = new Vector<>();
                            while (result.next()) {
                                Vector<String> user = new Vector<>();
                                user.addElement(result.getString(1));
                                user.addElement(result.getString(2));
                                user.addElement(result.getString(3));
                                user.addElement(result.getString(4));


                                data.add(user);
                            }
                            Vector<String> column = new Vector<>();

                            column.addElement("Full Name");
                            column.addElement("Email");
                            column.addElement("Nick Name");
                            column.addElement("Role");

                            view.getTable().setModel(new DefaultTableModel(data, column));

                            loginView.dispose();
                        } else  {

                            loginView.getIncoLabel().setVisible(true);
                        }
                    } catch (SQLException e) {
                        System.out.println("loi " + e.getMessage());
                    }
                });
            });
        });
    }

    private void exitKeyEvent() {
        view.getExitItem().setMnemonic(KeyEvent.VK_X);
    }
    private void exitEvent() {
        view.getExitItem().addActionListener(actionEvent -> {
           int result = JOptionPane.showConfirmDialog(null,"Are you sure?","exit",JOptionPane.YES_NO_CANCEL_OPTION);
            if(result == JOptionPane.YES_NO_OPTION) {
                System.exit(0);
            }
        });
    }
}
