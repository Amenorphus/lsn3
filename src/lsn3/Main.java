package lsn3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) {
		try {
			HashMap<Integer, ArrayList<Integer>> hm = new HashMap<Integer, ArrayList<Integer>>();

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			String[] stringArray = reader.readLine().split("\\s");
			int connections = Integer.parseInt(stringArray[0]);

			for (int i = 0; i < connections; i++) {
				stringArray = reader.readLine().split("\\s");
				Integer v1 = Integer.parseInt(stringArray[0]);
				Integer v2 = Integer.parseInt(stringArray[1]);

				if (hm.get(v1) == null) {
					ArrayList<Integer> a = new ArrayList<Integer>();
					a.add(0);
					a.add(v2);
					hm.put(v1, a);
				} else {
					hm.get(v1).add(v2);
				}

				if (hm.get(v2) == null) {
					ArrayList<Integer> a = new ArrayList<Integer>();
					a.add(0);
					a.add(v1);
					hm.put(v2, a);
				} else {
					hm.get(v2).add(v1);
				}

			}

			int separateGraphCounter = 0;

			while (!hm.isEmpty()) {
				Iterator<Entry<Integer, ArrayList<Integer>>> iterator = hm.entrySet().iterator();
				visitVertice(iterator.next().getKey(), hm);
				separateGraphCounter++;
			}

			System.out.printf("%d%n", separateGraphCounter);
			
		} catch (IOException e) {
			System.err.print("I/O error");
			System.exit(0);
		} catch (NumberFormatException e) {
			System.err.print("Input format error");
			System.exit(0);
		}
	}

	private static void visitVertice(Integer vertice, HashMap<Integer, ArrayList<Integer>> hm) {
		hm.get(vertice).set(0, 1);

		for (int i = 1; i < hm.get(vertice).size(); i++) {
			if (hm.get(hm.get(vertice).get(i)) != null && hm.get(hm.get(vertice).get(i)).get(0) == 0) {
				visitVertice(hm.get(vertice).get(i), hm);
			}
		}

		hm.remove(vertice);
	}
}
