package com.hibernate.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.hibernate.dao.SerieDAO;
import com.hibernate.model.Serie;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class App {

	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel modelo;
	private JLabel lblSerie;
	private JLabel lblTemp;
	private JLabel lblCap;
	private JTextArea txtId;
	private JTextArea txtSerie;
	private JTextArea txtTemp;
	private JTextArea txtCap;
	private JButton btnGuardar;
	private JButton btnActualizar;
	private JButton btnBorrar;
	
	SerieDAO serieDAO=new SerieDAO();
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
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index=table.getSelectedRow();
				TableModel model=table.getModel();
				
				//Rellenar txtAreas con la fila seleccionada de la tabla
				txtId.setText(model.getValueAt(index, 0).toString());
				txtSerie.setText(model.getValueAt(index, 1).toString());
				txtTemp.setText(model.getValueAt(index, 2).toString());
				txtCap.setText(model.getValueAt(index, 3).toString());
			}
		});
		table.setBounds(183, 11, 623, 259);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(183, 11, 623, 259);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(95, 300, 46, 14);
		frame.getContentPane().add(lblId);
		
		lblSerie = new JLabel("Serie:");
		lblSerie.setBounds(95, 325, 46, 14);
		frame.getContentPane().add(lblSerie);
		
		lblTemp = new JLabel("Temporadas:");
		lblTemp.setBounds(95, 350, 95, 14);
		frame.getContentPane().add(lblTemp);
		
		lblCap = new JLabel("Capítulos:");
		lblCap.setBounds(95, 375, 65, 14);
		frame.getContentPane().add(lblCap);
		
		txtId = new JTextArea();
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setBounds(185, 295, 65, 19);
		frame.getContentPane().add(txtId);
		
		txtSerie = new JTextArea();
		txtSerie.setBounds(183, 320, 233, 19);
		frame.getContentPane().add(txtSerie);
		
		txtTemp = new JTextArea();
		txtTemp.setBounds(183, 345, 71, 19);
		frame.getContentPane().add(txtTemp);
		
		txtCap = new JTextArea();
		txtCap.setBounds(183, 370, 71, 19);
		frame.getContentPane().add(txtCap);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre=txtSerie.getText();
				int temp=Integer.parseInt(txtTemp.getText());
				int caps=Integer.parseInt(txtCap.getText());
				Serie s=new Serie(nombre, temp, caps);
				serieDAO.insertSerie(s);
				
				//Refrescar tabla
				modelo.setRowCount(0);
				List<Serie> series=serieDAO.selectAllSerie();
				Object[] row = new Object[4];
				for(Serie serie:series) {
					row[0] = serie.getId();
					row[1] = serie.getNombre();
					row[2] = serie.getTemporadas();
					row[3] = serie.getCapitulos();
					modelo.addRow(row);
				}
				
			}
		});
		btnGuardar.setBounds(95, 433, 89, 23);
		frame.getContentPane().add(btnGuardar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre=txtSerie.getText();
				int temp=Integer.parseInt(txtTemp.getText());
				int caps=Integer.parseInt(txtCap.getText());
				Serie s=new Serie(nombre, temp, caps);
				serieDAO.updateSerie(s);
				
				//Refrescar tabla
				modelo.setRowCount(0);
				List<Serie> series=serieDAO.selectAllSerie();
				Object[] row = new Object[4];
				for(Serie serie:series) {
					row[0] = serie.getId();
					row[1] = serie.getNombre();
					row[2] = serie.getTemporadas();
					row[3] = serie.getCapitulos();
					modelo.addRow(row);
				}
			}
		});
		btnActualizar.setBounds(194, 433, 123, 23);
		frame.getContentPane().add(btnActualizar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(327, 433, 89, 23);
		frame.getContentPane().add(btnBorrar);
		
		//Añadir series a la tabla
		List<Serie> series=serieDAO.selectAllSerie();
		Object[] row = new Object[4];
		for(Serie s:series) {
			row[0] = s.getId();
			row[1] = s.getNombre();
			row[2] = s.getTemporadas();
			row[3] = s.getCapitulos();
			modelo.addRow(row);
		}
	}
}
