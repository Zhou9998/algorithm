package com.zsc.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 图
 *
 * @author zsc
 * @date 2021/4/24 20:59
 */
public class Graph {
    private ArrayList<String> vertexList;   //存储定点集合
    private int[][] edges;  //存储图对应的邻结阵
    private int numOfEdges; //表示边的数目
    //定义给数组boolean[]，记录每个节点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        //测试图是否创建成功
        int n = 8;  //节点的个数
        //String[] vertexValue = {"A", "B", "C", "D", "E"};
        String[] vertexValue = {"1", "2", "3", "4", "5", "6", "7", "8"};
        //创建图对象
        Graph graph = new Graph(n);
        for (String vertex : vertexValue) {
            graph.insertVertex(vertex);
        }

        //添加边
        //graph.insertEdge(0, 1, 1);
        //graph.insertEdge(0, 2, 1);
        //graph.insertEdge(1, 2, 1);
        //graph.insertEdge(1, 3, 1);
        //graph.insertEdge(1, 4, 1);
        //更新边的关系
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);
        //显示邻结矩阵

        graph.showGraph();

        //测试dfs遍历
        System.out.println("深度遍历");
        graph.dfs();
        System.out.println();
        //测试bfs遍历
        System.out.println("广度遍历");
        graph.bfs();
    }

    public Graph(int n) {
        this.edges = new int[n][n];
        this.vertexList = new ArrayList<>(n);
        numOfEdges = 0;

    }

    /**
     * 得到第一个邻接节点的下标w
     *
     * @param index
     * @return 如果存在就返回对应的下标
     */
    public int getFirstNeighbour(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接结点的下标来获取下一个邻接结点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历算法
    //i第一次就是0
    private void dfs(boolean[] isVisited, int i) {

        //首先访问该节点
        System.out.print(getValueByIndex(i) + "->");
        //将该节点设置为已访问
        isVisited[i] = true;
        //查找节点i的第一个邻接节点
        int w = getFirstNeighbour(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //如果w节点已经被访问过
            w = getNextNeighbor(i, w);
        }
    }

    //对dfs进行重载，遍历所有节点，并进行dfs
    private void dfs() {
        isVisited = new boolean[8];
        //遍历所有节点，进行dfs
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    //广度优先遍历算法
    private void bfs(boolean[] isVisited, int i) {

        int u;  //表示队列的头结点对应的下标
        int w;  //邻接节点的下标
        //队列，记录节点访问的顺序
        LinkedList queue = new LinkedList();
        //访问节点
        System.out.print(getValueByIndex(i) + "->");
        //标记为已访问
        isVisited[i] = true;
        //将节点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()) {
            //取出队列的头结点下标
            u = (Integer) queue.removeFirst();
            //得到第一个邻接节点的下标w
            w = getFirstNeighbour(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "->");
                    //标记已经访问
                    isVisited[w] = true;
                    //入队
                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w);
            }
        }
    }

    //对bfs进行重载，遍历所有节点，并进行bfs
    private void bfs() {
        isVisited = new boolean[8];
        //遍历所有节点，进行dfs
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.err.println(Arrays.toString(link));
        }
    }

    //返回结点i(下标)对应的数据 0->"A" 1->"B" 2->"C"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //返回结点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     表示点的下标，既第几个顶点
     * @param v2     表示点的下标，既第几个顶点
     * @param weight
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
