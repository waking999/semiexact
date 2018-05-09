package au.edu.cdu.se.util;

import au.edu.cdu.se.util.is.ISGlobalVariable;

import java.util.*;


public class ConnectComponents {
    private ISGlobalVariable g;

    private List<Set<Integer>> components;

    public ConnectComponents() {
        this.components = new ArrayList<>();
    }

    public void setG(ISGlobalVariable g) {
        this.g = g;
    }

    public List<Set<Integer>> getConnectComponents() {
        int verCnt = g.getVerCnt();
        boolean[] visited = new boolean[verCnt];
        Arrays.fill(visited, false);
        int[] idxLst = g.getIdxLst();

        int[][] idxAL = g.getIdxAL();

        for (int i = 0; i < verCnt; i++) {
            int vIdx = idxLst[i];
            if (!visited[vIdx]) {
                bfs(vIdx, idxAL, verCnt, visited);
            }

        }
        return components;
    }


    private void bfs(int vIdx, int[][] idxAL, int verCnt, boolean[] visited) {
        Queue q = new Queue(verCnt);
        Set<Integer> component = new HashSet<>();
        q.add(vIdx);
        visited[vIdx] = true;
        while (!q.isEmpty()) {
            int uIdx = q.poll();
            component.add(uIdx);
            int[] uIdxNeigs = idxAL[uIdx];
            for (int wIdx : uIdxNeigs) {
                if (!visited[wIdx]) {
                    q.add(wIdx);
                    visited[wIdx] = true;
                }
            }
        }
        components.add(component);
    }

}
