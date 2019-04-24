/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.cc.s4.prog.m1.dotask.util;

import java.util.Date;

/**
 *
 * @author 5586658
 */
public class DateConverter {
    public static Date dateSqlToJava(java.sql.Timestamp data){
        return new Date(data.getTime());
    }
    public static java.sql.Timestamp dateJavaToSql (Date data){
        return new java.sql.Timestamp(data.getTime());
    }
}
