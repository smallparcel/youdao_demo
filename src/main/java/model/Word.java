package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by BH00350 on 2015/12/10.
 */
//Serializable���л�����ӿڱȽ����⣬û�г��󷽷�
//alt + inset ��ݼ����Դ������췽����..
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Word implements Serializable{
    private Integer id;
    private String english;
    private String chinese;

   /* public Word() {
    }

    public Word(Integer id, String english, String chinese) {
        this.id = id;
        this.english = english;
        this.chinese = chinese;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }*/
}
/*
1�����л��Ǹ�ʲô�ģ�
       ��˵����Ϊ�˱������ڴ��еĸ��ֶ����״̬��Ҳ����ʵ�����������Ƿ�������
       ���ҿ��԰ѱ���Ķ���״̬�ٶ���������Ȼ����������Լ��ĸ��ָ����ķ���������object states��
       ����Java�����ṩһ��Ӧ�ñ����Լ��õı������״̬�Ļ��ƣ��Ǿ������л���
2��ʲô�������Ҫ���л�
    a��������ѵ��ڴ��еĶ���״̬���浽һ���ļ��л������ݿ���ʱ��
    b�����������׽����������ϴ��Ͷ����ʱ��
    c��������ͨ��RMI��������ʱ��
 */
