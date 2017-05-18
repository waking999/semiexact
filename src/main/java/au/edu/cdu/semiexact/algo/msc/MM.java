package au.edu.cdu.semiexact.algo.msc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import au.edu.cdu.semiexact.util.ConstantValue;

/**
 * 
 * use existing Edmonds max matching algorithm
 *
 */
public class MM {
	private int lca(Map<Integer, Integer> match, int[] base, int[] p, int a, int b) {
		boolean[] used = new boolean[match.size()+1];
		while (true) {
			a = base[a];
			used[a] = true;
			if (match.get(a) == ConstantValue.IMPOSSIBLE_VALUE)
				break;
			a = p[match.get(a)];
		}
		while (true) {
			b = base[b];
			if (used[b])
				return b;
			b = p[match.get(b)];
		}
	}

	private void markPath(Map<Integer, Integer> match, int[] base, boolean[] blossom, int[] p, int v, int b,
			int children) {
		for (; base[v] != b; v = p[match.get(v)]) {
			blossom[base[v]] = blossom[base[match.get(v)]] = true;
			p[v] = children;
			children = match.get(v);
		}
	}

	private int findPath(Map<Integer, List<Integer>> graph, Map<Integer, Integer> match, int[] p, int root) {
		int n = graph.size();
		boolean[] used = new boolean[n+1];
		Arrays.fill(p, ConstantValue.IMPOSSIBLE_VALUE);
		int[] base = new int[n+1];
		for (int i = 1; i <=n; ++i)
			base[i] = i;
		used[root] = true;
		int qh = 1;
		int qt = 1;
		int[] q = new int[n+1];
		q[qt++] = root;
		while (qh < qt) {
			int v = q[qh++];

			for (int to : graph.get(v)) {
				if (base[v] == base[to] || match.get(v) == to)
					continue;
				if (to == root || match.get(to) != ConstantValue.IMPOSSIBLE_VALUE
						&& p[match.get(to)] != ConstantValue.IMPOSSIBLE_VALUE) {
					int curbase = lca(match, base, p, v, to);
					boolean[] blossom = new boolean[n+1];
					markPath(match, base, blossom, p, v, curbase, to);
					markPath(match, base, blossom, p, to, curbase, v);
					for (int i = 1; i <= n; ++i)
						if (blossom[base[i]]) {
							base[i] = curbase;
							if (!used[i]) {
								used[i] = true;
								q[qt++] = i;
							}
						}
				} else if (p[to] == ConstantValue.IMPOSSIBLE_VALUE) {
					p[to] = v;
					if (match.get(to) == ConstantValue.IMPOSSIBLE_VALUE)
						return to;
					to = match.get(to);
					used[to] = true;
					q[qt++] = to;
				}
			}
		}
		return ConstantValue.IMPOSSIBLE_VALUE;
	}

	/**
	 * 
	 * @param graph,
	 *            vertex adjacency list
	 * @return, matching number and max matching 
	 */
	public MMObj maxMatching(Map<Integer, List<Integer>> graph) {
		int n = graph.size();
		Map<Integer, Integer> match = new HashMap<Integer, Integer>(n);

		Set<Integer> keySet = graph.keySet();
		for (int key : keySet) {
			match.put(key, ConstantValue.MATE_EXPOSE);
		}

		int[] p = new int[n+1];
		for (int key : keySet) {
			if (match.get(key) == ConstantValue.IMPOSSIBLE_VALUE) {
				int v = findPath(graph, match, p, key);
				while (v != ConstantValue.IMPOSSIBLE_VALUE) {
					int pv = p[v];
					int ppv = match.get(pv);
					match.put(v, pv);
					match.put(pv, v);

					v = ppv;
				}
			}
		}
		int matches = 0;

		for (int i : keySet)
			if (match.get(i) != ConstantValue.IMPOSSIBLE_VALUE)
				++matches;

		int mNum = ConstantValue.IMPOSSIBLE_VALUE;
		if (matches > 0) {
			mNum = matches / 2;
		}
		return new MMObj(mNum, match);
	}

}