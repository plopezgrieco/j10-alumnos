package modelo;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

public class Alumno {// implements Comparable<Alumno> {
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dni;
	private double nota;
	
	public Alumno() {
	}
	
	public Alumno(String nombre, String apellido1, String apellido2, String dni, double nota) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dni = dni;
		this.nota = nota;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public double getNota() {
		return nota;
	}
	public void setNota(double nota) {
		this.nota = nota;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return Objects.equals(dni, other.dni);
	}
	

	@Override
	public String toString() {
		return "Alumno (" + nombre + ", " + apellido1 + ", " + apellido2 + ", " + dni + ", " + nota + ")";
	}

//	@Override
	public int compareTo(Alumno o) {
		return this.dni.compareToIgnoreCase(o.dni);
	}

	public static Comparator<Alumno> getComparatorApellidos(){
		return new Comparator<Alumno>() {
			public int compare(Alumno a1, Alumno a2) {
				String nombre1 = a1.getApellido1() + a1.getApellido2() + a1.getNombre() + a1.getDni();
				String nombre2 = a2.getApellido1() + a2.getApellido2() + a2.getNombre() + a2.getDni();
				Collator col = Collator.getInstance(new Locale.Builder().setLanguage("es").build());
				return  col.compare(nombre1, nombre2);
			}
		};
	}

}
