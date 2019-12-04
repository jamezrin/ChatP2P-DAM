package chatp2p;
public class ControladorPrincipal {
	private static final boolean actualizarAutomaticamente = true;
	private VistaPrincipal vista;
	
	public ControladorPrincipal(VistaPrincipal vista) {
		this.vista = vista;
		
		/*
		vista.insertarButton.addActionListener(new InsertarListener());
		vista.eliminarButton.addActionListener(new EliminarListener());
		vista.consultarButton.addActionListener(new ConsultarListener());
		vista.modificarButton.addActionListener(new ModificarListener());
		vista.listarButton.addActionListener(new ListarListener());
		vista.salirButton.addActionListener(new SalirListener());
		*/
	}
	
	/*
	public void vaciarCampos() {
		vista.dniTextField.setText("");
		vista.nombreTextField.setText("");
		vista.apellidoTextField.setText("");
		vista.edadTextField.setText("");
	}
	
	public class InsertarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!vista.dniTextField.getText().equals("") && !vista.nombreTextField.equals("") && !vista.apellidoTextField.equals("") && !vista.edadTextField.equals("")) {
				try {
					int edadInteger = Integer.parseInt(vista.edadTextField.getText());
					ObRegistro registro = new ObRegistro(
						vista.dniTextField.getText(), 
						vista.nombreTextField.getText(), 
						vista.apellidoTextField.getText(),
						edadInteger
					);
					
					if (new ObConsultar(db, registro.getDni()).posicionEncontrada() == 0) {
						new ObInsercion(db, registro);
						JOptionPane.showMessageDialog(
							vista.getFrame(), 
							"Insertado con exito", 
							"Insertado con exito",
							JOptionPane.INFORMATION_MESSAGE);
						vaciarCampos();
						
						if (actualizarAutomaticamente) {
							vista.tableModel.addRow(new Object[] {
								registro.getDni(), 
								registro.getNombre(), 
								registro.getApellido(), 
								registro.getEdad()
							});
						}
					} else {
						JOptionPane.showMessageDialog(
							vista.getFrame(), 
							"DNI ya existe", 
							"Error de validacion",
							JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(
						vista.getFrame(), 
						"Edad no valida", 
						"Error de validacion",
						JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(
					vista.getFrame(), 
					"Faltan datos",
					"Error de validacion",
					JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public class EliminarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!vista.dniTextField.getText().equals("")) {
				ObConsultar consulta = new ObConsultar(db, vista.dniTextField.getText());
				int numFila = consulta.posicionEncontrada();
				if (numFila != 0) {
					new ObBorrado(db, numFila);
					JOptionPane.showMessageDialog(
						vista.getFrame(), 
						"Borrado con exito",
						"Borrado con exito",
						JOptionPane.INFORMATION_MESSAGE);
					vaciarCampos();
					
					if (actualizarAutomaticamente) {
						vista.tableModel.removeRow(numFila - 1);
					}
				} else {
					JOptionPane.showMessageDialog(
						vista.getFrame(), 
						"DNI no existe",
						"Validacion",
						JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(
					vista.getFrame(), 
					"DNI no introducido",
					"Validacion",
					JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public class ConsultarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!vista.dniTextField.getText().equals("")) {
				ObConsultar consulta = new ObConsultar(db, vista.dniTextField.getText());
				if (consulta.posicionEncontrada() != 0) {
					vaciarCampos();
					vista.tableModel.setRowCount(0);
					ObRegistro registro = consulta.registroEncontrado();
					
					vista.tableModel.addRow(new Object[] {
						registro.getDni(), 
						registro.getNombre(), 
						registro.getApellido(), 
						registro.getEdad()
					});
				} else {
					JOptionPane.showMessageDialog(
						vista.getFrame(),
						"No se ha encontrado una persona con ese DNI",
						"No encontrado",
						JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(
					vista.getFrame(),
					"DNI no introducido",
					"Validacion",
					JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public class ModificarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!vista.dniTextField.getText().equals("")) {
				ObConsultar consulta = new ObConsultar(db, vista.dniTextField.getText());
				if (consulta.posicionEncontrada() != 0) {
					if (!vista.nombreTextField.equals("") && !vista.apellidoTextField.equals("") && !vista.edadTextField.equals("")) {
						try {
							int edadInteger = Integer.parseInt(vista.edadTextField.getText());
							
							ObRegistro registroNuevo = new ObRegistro(
									vista.dniTextField.getText(),
									vista.nombreTextField.getText(),
									vista.apellidoTextField.getText(), 
									edadInteger);
							
							new ObModificacion(db, 
									consulta.posicionEncontrada(), 
									registroNuevo);
							vaciarCampos();
							
							if (actualizarAutomaticamente) {
								vista.tableModel.removeRow(consulta.posicionEncontrada() - 1);
								vista.tableModel.insertRow(consulta.posicionEncontrada() - 1, new Object[] {
									registroNuevo.getDni(), 
									registroNuevo.getNombre(), 
									registroNuevo.getApellido(), 
									registroNuevo.getEdad()
								});
							}
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(
								vista.getFrame(),
								"Edad no valida", 
								"Error de validacion",
								JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(
							vista.getFrame(), 
							"Faltan datos",
							"Error de validacion",
							JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(
						vista.getFrame(),
						"No se ha encontrado una persona con ese DNI",
						"No encontrado",
						JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(
					vista.getFrame(),
					"DNI no introducido",
					"Validacion",
					JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public class ListarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			vista.tableModel.setRowCount(0);
			new ObListar(db, vista.tableModel);
		}
	}
	
	public class SalirListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				cerrarConexion();
				vista.getFrame().dispose();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(vista.getPanel(), 
					"Ha ocurrido este error: " + ex.getCause().toString(),
					"No se ha podido cerrar la conexion",
					JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	*/
}