package ordenadores;

import java.util.Comparator;
import dados.Pessoa;

public class OrdenaNome implements Comparator<Pessoa> {
	@Override
	public int compare(Pessoa o1, Pessoa o2) {
		return o1.getNomeCompleto().compareToIgnoreCase(o2.getNomeCompleto());
	}
}
