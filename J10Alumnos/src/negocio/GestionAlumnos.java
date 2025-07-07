package negocio;

import java.util.Set;

import modelo.Alumno;

public interface GestionAlumnos {

	public Set<Alumno> getAprobados();

	public double getMediaNotas();

	public double getMediaAprobados();
	
	
	public Set<Alumno> getAlumnos();

	public Set<Alumno> getAlumnosByNombre(String nombre);
	
	public Alumno getAlumnoByDni(String dni);

	public void save(Alumno alumno);
	
	public void delete(Alumno alumno);
}
