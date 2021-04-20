package com.zsc.hashtab;

import java.security.PublicKey;
import java.util.Scanner;

/**
 * ��ϣ��
 *
 * @author zsc
 * @date 2021/4/19 18:51
 */
public class HashTabDemo {
    public static void main(String[] args) {
        //������ϣ��
        HashTab hashTab = new HashTab(7);

        //дһ���򵥵Ĳ˵�
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:  ��ӹ�Ա");
            System.out.println("list: ��ʾ��Ա");
            System.out.println("find: ���ҹ�Ա");
            System.out.println("del: ɾ��ϵͳ");
            System.out.println("exit: �˳�ϵͳ");


            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("����id");
                    int id = scanner.nextInt();
                    System.out.println("��������");
                    String name = scanner.next();
                    //���� ��Ա
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("������Ҫ���ҵ�id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "del":
                    System.out.println("������Ҫɾ����Ա��");
                    id = scanner.nextInt();
                    hashTab.delEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }

    }
}

//����HashTab
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size;   //��ʾ�ж���������

    public HashTab(int size) {
        this.size = size;
        //��ʼ��empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        //�ֱ��ʼ��ÿһ������
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp) {
        //����Ա����id���õ���Ա��Ӧ�ü��뵽��������
        int empLinkedListNo = hashFun(emp.id);
        //��emp��ӵ���Ӧ��������
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //������������
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //���������id�����ҹ�Ա
    public void findEmpById(int id) {
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if (emp != null) {
            System.out.println("�ڵ�" + (empLinkedListNO + 1) + "�������У��ҵ���Աid=" + id);
        } else {
            System.out.println("�ڹ�ϣ���У�û���ҵ��ù�Ա");
        }
    }
    //���������id��ɾ����Ա
    public void delEmpById(int id){
        int empLinkedListNO = hashFun(id);
        empLinkedListArray[empLinkedListNO].delEmpById(id);
    }
    //��дһ��ɢ�к�����ʹ��һ����ȡģ��
    public int hashFun(int id) {
        return id % size;
    }
}

class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class EmpLinkedList {
    //ͷָ�룬ָ���һ��Emp����������head��ֱ��ָ���һ��Emp
    private Emp head;   //Ĭ��Ϊnull

    //������ӹ�Աʱ��id����������
    public void add(Emp emp) {
        //�������ӵ�һ����Ա
        if (head == null) {
            head = emp;
            return;
        }
        //������ǵ�һ����Ա����ʹ��һ��������ָ�룬������λ�����
        Emp curEmp = head;
        while (curEmp.next != null) {
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    //�����б�
    public void list(int no) {
        if (head == null) {
            System.out.println("��" + (no + 1) + "������Ϊ�գ�");
            return;
        }
        System.out.print("�� " + (no + 1) + " �������ϢΪ");
        Emp temp = head;
        while (temp != null) {
            System.out.printf(" => id=%d name=%s\t", temp.id, temp.name);
            temp = temp.next;
        }
    }

    //����id���ҹ�Ա
    public Emp findEmpById(int id) {
        //�ж������Ƿ�Ϊ��
        if (head == null) {
            System.out.println("����Ϊ�գ�");
            return null;
        }
        //����ָ��
        Emp temp = head;
        while (true) {
            if (temp.id == id) {//�ҵ�
                break;//��ʱcurEmp��ָ��Ҫ���ҵĹ�Ա
            }
            //�˳�
            if (temp.next == null) {//˵��������ǰ����û���ҵ��ù�Ա
                temp = null;
                break;
            }
            temp = temp.next;//�Ժ�
        }
        return temp;
    }

    //����idɾ����Ա
    public void delEmpById(int id) {
        //�ж������Ƿ�Ϊ��
        if (head == null) {
            System.out.println("����Ϊ�գ��޷�ɾ��");
            return;
        }
        if (head.id == id) {
            head = head.next;
            return;
        }
        //����ָ��
        Emp temp = head;
        while (true) {
            if (temp.next == null) {
                System.out.println("�Ҳ����ýڵ㣡");
                return;
            }
            if (temp.next.id == id) {
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
    }
}