package topcoder.SRM800;

import java.io.*;
import java.util.*;

public class TIEFighterAssembly {
	public int assemble(String salvagedParts) {
		if (salvagedParts == null || salvagedParts.length() == 0) {
			return 0;
		}
		int a = 0, b = 0, cd = 0;
		for (char c : salvagedParts.toCharArray()) {
			if (c == '|') a++;
			else if (c == '-') b++;
			else if (c == 'O') cd++;
		}

		return Math.min(cd, Math.min(a, b) / 2);
	}
}
