package com.zsc.recursion;

/**
 * 迷宫回溯
 *
 * @author zsc
 * @date 2021/4/16 14:57
 */
public class MiGong {
    public static void main(String[] args) {
        //创建二维数组，模拟迷宫
        //地图
        int[][] map = new int[8][7];
        //使用1表示墙
        //把上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //把左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;

        /*
        map[1][2] = 1;
        map[2][2] = 1;
        */

        /*
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        */
        //使用递归回溯
        setWay2(map,1,1);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    //使用递归回溯
    //说明
    //1.map表示地图
    //2.i j表示从地图的哪个位置开始出发
    //3.如果小球能到map的[6][5]位置，则说明通路找到
    //4.当map[i][j]为0时表示该点没有走过，当为1时表示墙，当为2时表示可以走，当为3时表示该点已经走过但走不通
    //5.在走迷宫时，需要确定一个策略 下->右->上->左，如果该点走不通再回溯
    public static boolean setWay(int[][] map,int i,int j){
        if (map[6][5] == 2){
            return true;
        }else {
            if (map[i][j] == 0){ //如果当前这个点还没有走过
                //策略 下->右->上->左
                map[i][j] = 2;  //假定该点可以走通
                if (setWay(map,i+1,j)){ //向下走
                    return true;
                }else if (setWay(map,i,j+1)){    //向右走
                    return true;
                }else if (setWay(map,i-1,j)){    //向上走
                    return true;
                }else if (setWay(map,i,j-1)){    //向左走
                    return true;
                }else {
                    //说明该点走不通，是死路
                    map[i][j] =3;
                    return false;
                }
            }else { //如果map[i][j] != 0 ;
                return false;
            }
        }
    }

    //修改策略  上-右-下-左
    public static boolean setWay2(int[][] map,int i,int j){
        if (map[6][5] == 2){
            return true;
        }else {
            if (map[i][j] == 0){ //如果当前这个点还没有走过
                //策略 下->右->上->左
                map[i][j] = 2;  //假定该点可以走通
                if (setWay2(map,i-1,j)){ //向上走
                    return true;
                }else if (setWay2(map,i,j+1)){    //向右走
                    return true;
                }else if (setWay2(map,i+1,j)){    //向下走
                    return true;
                }else if (setWay2(map,i,j-1)){    //向左走
                    return true;
                }else {
                    //说明该点走不通，是死路
                    map[i][j] =3;
                    return false;
                }
            }else { //如果map[i][j] != 0 ;
                return false;
            }
        }
    }
}
