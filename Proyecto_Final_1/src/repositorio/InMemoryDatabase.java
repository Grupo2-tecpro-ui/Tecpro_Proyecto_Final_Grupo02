package repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import Modelado.Motorizado;

public class InMemoryDatabase {
	
	private static final AtomicInteger seqMotorizado = new AtomicInteger(1);
    private static final List<Motorizado> MOTORIZADOS = new ArrayList<>();

    public static int nextIdMotorizado() {
        return seqMotorizado.getAndIncrement();
    }

    public static void addMotorizado(Motorizado m) {
        MOTORIZADOS.add(m);
    }

    public static List<Motorizado> getMotorizados() {
        return new ArrayList<>(MOTORIZADOS);
    }

}
