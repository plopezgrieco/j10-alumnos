package negocio;

import java.util.Collection;
import java.util.Set;

import modelo.Alumno;

public interface GestionAlumnos {

	Set<Alumno> getAprobados(Collection<Alumno> alumnos);
	
	double getMediaNotas(Collection<Alumno> alumnos);
	
	double getMediaAprobados(Collection<Alumno> alumnos);
}
