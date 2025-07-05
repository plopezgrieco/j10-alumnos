package tests;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import modelo.Alumno;
import util.CargaAlumnos;
import vista.VistaAlumno;
import vista.VistaAlumnoImpl;

public class TestEj05 {
	public static void main(String[] args) {
		
		List<Alumno> alumnosR = new LinkedList<>();
		List<Alumno> alumnosAs = new LinkedList<>();
		List<Alumno> alumnosAdd = new LinkedList<>();
		
		//Carga una lista desde un array
		//Opcion 1 - recorriendo el array
		for (Alumno alumno : CargaAlumnos.getArrayAlumnos()) {
			alumnosR.add(alumno);
		}
		alumnosR.remove(0);
		alumnosR.remove(1);
		alumnosR.remove(2);
		alumnosR.add(new Alumno());
		System.out.println(alumnosR.size());
		
		//Opcion 2 - con Arrays.asList
		// Retorna una lista de tamaño fijo, no se pueden agregar ni eliminar elementos
		alumnosAs = Arrays.asList(CargaAlumnos.getArrayAlumnos());
//		alumnosAs.remove(0);
//		alumnosAs.add(new Alumno());
		System.out.println(alumnosAs.size());
		
		//Opcion 3 - con Collections.addAll
		// Carga la colección pasada como primer parámetro con los elementos del array del seguno.
		Collections.addAll(alumnosAdd, CargaAlumnos.getArrayAlumnos());
		alumnosAdd.remove(0);
		alumnosAdd.remove(1);
		alumnosAdd.remove(2);
		alumnosAdd.add(new Alumno());
		System.out.println(alumnosAdd.size());
		
		
		// Creamos un objeto de la vista
		VistaAlumno v = new VistaAlumnoImpl();
		v.muestrAlumnos(alumnosAdd);
		
	}
}
