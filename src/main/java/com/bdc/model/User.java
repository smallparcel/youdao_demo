package com.bdc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by BH00350 on 2015/12/29.
 */

//ģ����  �����ַ�������
// 1����ͳ���ļ��ﶨ������Ӧ��setter����
// 2��ʹ��ע�ⷽʽ@��������������1������ǰ��@Data @AllArgsConstructor @NoArgsConstructor
// 2��setting��plugins��lombok��install plugin,����idea
// 3��setting��Build����compiler��Annotation Process��Enable Annotation Process

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User extends BaseModel {
    private Integer id;
    private String username;
    private String password;
}
