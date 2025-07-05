package negocio;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import modelo.Alumno;

public class GestionAlumnosImpl implements GestionAlumnos {

	@Override
	public Set<Alumno> getAprobados(Collection<Alumno> alumnos) {
		Set<Alumno> aprobados = new TreeSet<Alumno>(Alumno.getComparatorApellidos());
		aprobados.addAll(alumnos.stream().filter(a -> a.getNota() >=5).toList());
		return aprobados;
	}
	
//	@Override
//	public Set<Alumno> getAprobados(Collection<Alumno> alumnos) {
//		Set<Alumno> aprobados = new TreeSet<Alumno>(Alumno.getComparatorApellidos());
//		for (Alumno alumno : alumnos) {
//			if(alumno.getNota() >= 5)
//				aprobados.add(alumno);
//		}
//		return aprobados;
//	}

	@Override
	public double getMediaNotas(Collection<Alumno> alumnos) {
		return getMedia(alumnos);
	}

	private double getMedia(Collection<Alumno> alumnos) {
		double sumNotas = 0;
		for (Alumno alumno : alumnos) {
			sumNotas += alumno.getNota();
		}
		return sumNotas / alumnos.size();
	}

//	private double getMedia(Collection<Alumno> alumnos) {
//		return alumnos.stream().mapToDouble(Alumno::getNota).average().getAsDouble();
//	}
	
	@Override
	public double getMediaAprobados(Collection<Alumno> alumnos) {
		return getMedia(getAprobados(alumnos));
	}

}
