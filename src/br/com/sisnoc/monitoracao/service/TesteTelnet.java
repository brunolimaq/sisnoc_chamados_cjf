package br.com.sisnoc.monitoracao.service;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.TimerTask;
import org.apache.commons.net.telnet.TelnetClient;


public class TesteTelnet extends TimerTask {
	
    public static void main(String args[])
    {
        try
        {
            System.out.println("Digite seu IP:");
            Scanner sc=new Scanner(System.in);
            String ip=sc.nextLine().trim();
            System.out.println("Digite sua porta:");
            TimerTask con  = new TesteTelnet();
            Scanner sc1=new Scanner(System.in);
            int port=sc1.nextInt();
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(con,1,1000);
            Socket s1=new Socket(ip,port);
            InputStream is=s1.getInputStream();
            DataInputStream dis=new DataInputStream(is);
            if(dis!=null)
            {
                System.out.println("Conectado usando o ip: "+ip+" e porta: "+port);
            }
            else
            {
                System.out.println("Nao conectado");
            }

            dis.close();
            s1.close();

        }
        catch(Exception e)
        {
            System.out.println("erro:" + e.toString());

        }

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

    }
}
