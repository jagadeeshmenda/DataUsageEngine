package com.vz.duengine.threads;

import java.util.ArrayList;

import com.vz.duengine.dao.SelectDao;

public class DataUsageInvoker  extends Thread {
    
    public DataUsageInvoker() {
        setDaemon(true);
    }
    public void run(){
    	 try  {
    		 DataUsageInvoker dt = new DataUsageInvoker();
             ArrayList <String> states  = dt.getStateList();
             
             for (String state  :  states) {
            	 NotifyProcessor np  =  new NotifyProcessor (state);
            	 np.start(); 
             }
            	
             this.sleep(10000);
            }catch  (Exception ex) {
            	
            }
        System.out.println("Is this thread Daemon? - "+isDaemon());
    }
    public static void main(String a[]){
    	DataUsageInvoker dt = new DataUsageInvoker();
        // even you can set daemon constrain here also
        // it is like dt.setDeamon(true)
        dt.start();
        
    		
    }
    
    public  ArrayList <String> getStateList () {
		
    	SelectDao sd  = new SelectDao () ;
    	return sd.getStateList();
    	
    	
    }
}

