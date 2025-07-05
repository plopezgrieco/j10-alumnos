package tests;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import modelo.Alumno;
import negocio.GestionAlumnos;
import negocio.GestionAlumnosImpl;
import util.CargaAlumnos;
import vista.VistaAlumno;
import vista.VistaAlumnoImpl;

public class TestVarios {
	public static void main(String[] args) {
		List<Alumno> alumnos = new LinkedList<>();
		Collections.addAll(alumnos, CargaAlumnos.getArrayAlumnos());
		System.out.println(alumnos.size());
		
		
		VistaAlumno vista = new VistaAlumnoImpl();
		GestionAlumnos neg = new GestionAlumnosImpl();
		
//		Ejercicio 6		
		Set<Alumno> alumnosAprobados = neg.getAprobados(alumnos);
		System.out.println(alumnosAprobados.size());
		vista.muestrAlumnos(alumnosAprobados);
		
//		Ejercicio 7
		System.out.printf("Nota media total: %.2f\n", neg.getMediaNotas(alumnos));
		
//		Ejercicio 8
		System.out.printf("Nota media aprobados: %.2f\n", neg.getMediaAprobados(alumnos));
	}
}
