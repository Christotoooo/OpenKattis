import java.io.*;
import java.util.*;

public class Dominoes2 {

	public static void main(String[] args) throws IOException {
		IO io = new IO(System.in);

		int nCases = io.nextInt();
		int nDominoes, nRelations, nKnocked;
		for (int c = 0; c < nCases; c++) {
			nDominoes = io.nextInt();
			nRelations = io.nextInt();
			nKnocked = io.nextInt();

			N[] dominoes = new N[nDominoes];
			for (int i = 0; i < nDominoes; i++) {
				dominoes[i] = new N();
			}

			E[] relations = new E[nRelations];
			for (int i = 0; i < nRelations; i++) {
				dominoes[io.nextInt() - 1].adjacents.add(dominoes[io.nextInt() - 1]);
			}

			int counter = 0;
			for (int i = 0; i < nKnocked; i++) {
				counter += dominoes[io.nextInt() - 1].knockOver();
			}
			
			System.out.println(counter);
		}	
		
		io.close();
	}

	static class N {
		boolean active;
		ArrayList<N> adjacents;

		N() {
			active = true;
			adjacents = new ArrayList<N>();
		}

		public int knockOver() {
			if (active) {
				active = false;
				int knocked = 1;
				for (N dom : adjacents) {
					knocked += dom.knockOver();
				}
				return knocked;
			}
			return 0;
		}
	}

	static class E {
		N start, end;

		E(N start, N end) {
			this.start = start;
			this.end = end;
		}
	}

	static class IO extends PrintWriter {
		static BufferedReader r;
		static StringTokenizer t;

		public IO(InputStream i) {
			super(new BufferedOutputStream(System.out));
			r = new BufferedReader(new InputStreamReader(i));
			t = new StringTokenizer("");
		}

        public String nextLine() throws IOException {
            return r.readLine();
        }

		public String next() throws IOException {
			while (!t.hasMoreTokens()) {
				t = new StringTokenizer(nextLine());
			}
			return t.nextToken();
		}

		public int nextInt() throws IOException{
			return Integer.parseInt(next());
		}

		public long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		public double nextDouble() throws IOException {
			return Double.parseDouble(next());
		}
	}
}
