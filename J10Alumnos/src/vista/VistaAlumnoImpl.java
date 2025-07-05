package vista;

import java.util.Collection;

import modelo.Alumno;

public class VistaAlumnoImpl implements VistaAlumno {

	@Override
	public void muestrAlumnos(Collection<Alumno> alumnos) {
		alumnos.forEach(System.out::println);
	}

}
