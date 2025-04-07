package com.hibernate.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JScrollPane;

public class App {

	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 255, 64));
		frame.setBounds(100, 100, 1014, 566);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		modelo = new DefaultTableModel();
		modelo.addColumn("ID");
		modelo.addColumn("Título");
		modelo.addColumn("Nº Temporadas");
		modelo.addColumn("Total episodios");
		
		table = new JTable(modelo);
		table.setBounds(183, 11, 623, 259);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(183, 11, 623, 259);
		frame.getContentPane().add(scrollPane);
		
		
	}
}
