package com.zsc.sparsearray;

import java.io.*;

/**
 * 稀疏数组
 *
 * @author zsc
 * @date 2021/4/12 19:33
 */
public class SparseArr {
    public static void main(String[] args) {
        //先创建一个原始的二维数组 11*11
        //0表示没有棋子 1表示黑子 2表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始的二维数组
        System.out.println("原始的二维数组~~~");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }


        //将二维数组转换为稀疏数组
        int[][] sparseArr = toSparseArr(chessArr1);
        //输出稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组为~~~~~");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.println(sparseArr[i][0] + "\t" + sparseArr[i][1] + "\t" + sparseArr[i][2]);
        }
        System.out.println();


        //将稀疏数组恢复成原始的二维数组
        int[][] chessArr2 = fromSparseArr(sparseArr);
        //输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }

        //将稀疏数组写入文件中
        writeToTxt(sparseArr,"D:/a.txt");
        //从文件中读取稀疏数组
        readFromTxt("D:/a.txt");
    }

    public static int[][] toSparseArr(int[][] chessArr1){
        //1.先遍历二维数组，得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("sum=" + sum);
        //2.创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        //遍历二维数组，将非0的值存放到sparseArr中
        int count = 0; //count用来记录是第几个非零数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        return sparseArr;
    }
    public static int[][] fromSparseArr(int[][] sparseArr){
        //1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2.在读取稀疏数组的后几行数据(第二行开始)，并赋给原始的二维数组
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        return chessArr2;
    }


    public static void writeToTxt(int[][] array, String path) {
        File file = new File(path);
        //创建字符输出流
        FileWriter writer = null;
        try {
            writer = new FileWriter(file, true);
            //写数据
            for (int i = 0; i < array.length; i++) {
                writer.write(array[i][0] + " ");
                writer.write(array[i][1] + " ");
                writer.write(+array[i][2] + " " + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //关闭流
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readFromTxt(String path){
        File file = new File(path);
        int[][] sparseArray;//稀疏数组
        BufferedReader bufferedReader=null;//缓冲流，提高效率，一行一行的读取
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            //获得稀疏数组的大小，方便初始化；
            int temp=0;
            while (bufferedReader.readLine()!=null){
                temp++;
            }
            sparseArray=new int[temp][3];
            //读完之后，关闭流，不然后面读到的为空
            bufferedReader.close();
            //重新打开流
            bufferedReader= new BufferedReader(new FileReader(file));

            String strs;//读的每一行都是字符串，用strs存储
            int i=0;//指向稀疏数组行的指针
            int j=0;//指向稀疏数组列的指针
            while ((strs=bufferedReader.readLine())!=null){//当读到的每一行不为空时循环
                String[] str = strs.split(" ");//将读到的字符串根据" "分开，得到一个数组
                for (String s : str) {//遍历该数组
                    sparseArray[i][j] = Integer.parseInt(s);//将字符串转换成int存储在字符串上
                    j++;//指向列的指针每遍历一下自增一次
                }
                j=0;//一次遍历后，指向列的指针要重新设为0；
                i++;//列指针自增
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bufferedReader!=null){
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
