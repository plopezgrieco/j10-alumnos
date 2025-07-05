package tests;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import modelo.Alumno;
import util.CargaAlumnos;

public class TestEj09 {
	public static void main(String[] args) {
		
//		Map<String, Alumno> alumnos = new HashMap<>(200, 0.75f);
		Map<String, Alumno> alumnos = new TreeMap<>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o2.compareTo(o1);
			}
		});
		
		for (Alumno alu : CargaAlumnos.getArrayAlumnos()) {
			alumnos.put(alu.getDni(), alu);
		}
		
		// Recorrer las claves
		for (String dni : alumnos.keySet()) {
			System.out.println(dni + " - ");
			// no hacer esto:
//				System.out.println(alumnos.get(dni));
		}
		
		// Recorrer los valores
		for (Alumno alu : alumnos.values()) {
			System.out.println(alu);
		}
		
		// Recorre el mapa
		// Recorremos un Set de Entry
		for (Entry<String, Alumno> entrada : alumnos.entrySet()) {
			System.out.println(entrada.getKey() + " - " + entrada.getValue());
		}
		
		Set<String> dnis = alumnos.keySet();
		Collection<Alumno> alus = alumnos.values();
		Set<Entry<String, Alumno>> entradas = alumnos.entrySet();
		
		
		Alumno buscado = alumnos.get("12345678");
		System.out.println(buscado);
		
		buscado = alumnos.get("82225055D");
		System.out.println(buscado);
	}
}
