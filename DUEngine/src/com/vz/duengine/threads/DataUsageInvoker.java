package com.vz.duengine.threads;

import java.util.ArrayList;

import com.vz.duengine.dao.SelectDao;

public class DataUsageInvoker  extends Thread {
    
    public DataUsageInvoker() {
        setDaemon(true);
    }
    public void run(){
        System.out.println("Is this thread Daemon? - "+isDaemon());
    }
    public static void main(String a[]){
    	DataUsageInvoker dt = new DataUsageInvoker();
        // even you can set daemon constrain here also
        // it is like dt.setDeamon(true)
        dt.start();
        
        
        try  {
         ArrayList <String> states  = dt.getStateList();
         
         for (String state  :  states) {
        	 
         }
        	
        }catch  (Exception ex) {
        	
        }
    		
    }
    
    public  ArrayList <String> getStateList () {
		
    	SelectDao sd  = new SelectDao () ;
    	return sd.getStateList();
    	
    	
    }
}

