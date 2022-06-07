package ru.gb.javafxapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Date;

public class ClientChat extends JFrame implements ActionListener {

    final String TITLE_OF_PROGRAM = "Chat";
    final int START_LOCATION = 200;
    final int WINDOW_WIDTH = 350;
    final int WINDOW_HEIGHT = 450;
    final String BTN_ENTER = "Enter";
    final String BTN_NEW_CHAT = "New chat";
    private JButton enter;
    private JButton clear;
    private File file;
    JTextArea dialogue;
    JTextField message;
    private FileDescriptor logFileName;

    public static void main(String[] args) {
        new ClientChat();
    }

    ClientChat() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, WINDOW_WIDTH, WINDOW_HEIGHT);
        Date date = new Date();
        String logFileName = convertedLogFileName(date.toString()) + ".txt";
        file = new File(logFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialogue = new JTextArea();
        dialogue.setEditable(false);
        JScrollPane scrollBar = new JScrollPane(dialogue);

        JPanel bp = new JPanel();
        bp.setLayout(new BoxLayout(bp, BoxLayout.X_AXIS));
        message = new JTextField();
        message.addActionListener(this);
        enter = new JButton(BTN_ENTER);
        clear = new JButton(BTN_NEW_CHAT);
        enter.addActionListener(this);
        clear.addActionListener(this);

        bp.add(message);
        bp.add(enter);
        bp.add(clear);
        add(BorderLayout.CENTER, scrollBar);
        add(BorderLayout.SOUTH, bp);
        setVisible(true);
    }

    private String convertedLogFileName(String name) {
        String temp1 = name.replace(" ", "");
        String temp2 = temp1.replace(":", "");
        return temp2;
    }




    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == enter || event.getSource() == message) {
            if (message.getText().trim().length() > 0) {
                try (FileWriter writer = new FileWriter(String.valueOf(logFileName), true)) {
                    writer.write(message.getText() + "\n");
                } catch (Exception e) {
                } finally {
                    try (FileReader reader = new FileReader(logFileName)) {
                        StringBuffer temp = new StringBuffer("");
                        int c;
                        while ((c = reader.read()) != -1) {
                            temp.append((char) c);
                        }
                        dialogue.setText(temp.toString());
                    } catch (Exception e) {

                    }
                }
                message.setText("");
                message.requestFocusInWindow();
            } else if (event.getSource() == clear) {
                new ClientChat();
            }

            }
        }
    }

