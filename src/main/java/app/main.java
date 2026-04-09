package app;

import models.Registro;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class main {

    private static List<Registro> generarDatos() {
        List<Registro> registros = new ArrayList<>();
        for(int i=0; i < 100; i ++) {
            registros.add(
                    new Registro(LocalDateTime.now().minusMinutes(i),
                            (Math.random() * ((50 - (-10)) + 1)) + (-10),
                            Math.random() * 100
                            )
            );
        }
        return registros;
    }

    static void main() {
        List<Registro> registros = generarDatos();
        IO.println("Registros generados: " + registros.size());

        //registros.forEach(IO::println);

        //Filtrar los registros de temperatura que sean mayores a 25 grados, la humedad sea menor a 70 y la
        //fecha sea anterior a la fecha actual, y mostrarlos
        IO.println("--- EJERCICIO 1 ---");
        registros.stream()
                .filter( p -> p.getTemperatura() > 25)
                .filter(p -> p.getHumedad() < 70)
                .filter(p -> p.getFechaHora().isBefore(LocalDateTime.now()))
                .forEach(IO::println);

        //Encontrar el registro con la temperatura más alta y mostrar el registro completo
        IO.println("--- EJERCICIO 2 ---");
        registros.stream()
                .sorted(Comparator.comparing(Registro::getTemperatura).reversed())
                .limit(1)
                .forEach(IO::println);

        //Obtener una lista con las fechas/horas de todas las tomas de datos.
        IO.println("--- EJERCICIO 3 ---");
        List<LocalDateTime> fechas = registros.stream()
                .map(Registro::getFechaHora)
                .toList();
        fechas.forEach(IO::println);

        //Incrementar en 5 unidades la humedad de todos los registros y mostrar las temperaturas,
        //humedades y fechas/horas actualizadas
        IO.println("--- EJERCICIO 4 ---");
        registros.stream()
                .map(r -> new Registro(r.getFechaHora(), r.getTemperatura(), r.getHumedad() + 5))
                .forEach(IO::println);

        //Encontrar el registro con la temperatura más baja que tenga una humedad mayor a 80 y mostrar la
        //temperatura, humedad y fecha
        IO.println("--- EJERCICIO 5 ---");
        registros.stream()
                .filter(p -> p.getHumedad() > 80)
                .min(Comparator.comparing(Registro::getTemperatura))
                .ifPresent(IO::println);

        //Verificar si algún registro tiene una temperatura mayor a 30 grados, humedad mayor a 90 y la fecha
        //es de hoy. Mostrar un mensaje indicando si hay algún registro que cumple esta condición o no
        IO.println("--- EJERCICIO 6 ---");
        boolean encontrado = registros.stream()
                .anyMatch(p -> p.getTemperatura() > 30
                        && p.getHumedad() > 90
                        && p.getFechaHora().toLocalDate().equals(LocalDate.now()));
        if (encontrado) {
            IO.println("Hay al menos un registro que cumple la condición.");
        } else {
            IO.println("No hay ningún registro que cumple la condición.");
        }

        //Muestra 10 registros saltándote los 5 primeros
        IO.println("--- EJERCICIO 7 ---");
        registros.stream()
                .skip(5)
                .limit(10)
                .forEach(IO::println);

        //Muestra los registros ordenados por fecha
        IO.println("--- EJERCICIO 8 ---");
        registros.stream()
                .sorted(Comparator.comparing(Registro::getFechaHora))
                .forEach(IO::println);

        //Cuenta los registros que tengan temperatura mayor a 35 grados (count())
        IO.println("--- EJERCICIO 9 ---");
        long cantidad = registros.stream()
                .filter(p -> p.getTemperatura() > 35)
                .count();
        IO.println("Cantidad de registros con temperatura mayor a 35: " + cantidad);

        //Calcular la temperatura promedio de todos los registros (transformarlo en Stream<Double> y
        //llamar a average()).
        IO.println("--- EJERCICIO 10 ---");
        double promedio = registros.stream()
                .mapToDouble(Registro::getTemperatura)
                .average()
                .orElse(0);
        IO.println("Promedio de temperaturas: " + promedio);

        promedio = registros.stream()
                .collect(Collectors.summarizingDouble(Registro::getTemperatura))
                .getAverage();
        IO.println("Promedio de temperaturas: " + promedio);




    }



}
