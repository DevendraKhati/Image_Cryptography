/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jvd;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author vikas
 */
public class Functions {
    
               static int key []=new int[10];
              static  String keyhex[]=new String[10];
              static  int a;
              static int i;
              static String k456,binary567;
              static double x0,x1,xi_1,xi,xinitial,x00,x11;
             static String gethex[]=new String[10];
             static BufferedImage image=null;
             static int key1[]=new int[10];
              static int org_key[][]= new int [10][10];
              static int pixelgap=5;
              static int outloop=5;
              static int decryptkeyset=4;
    
    /*To convert the number to binary equivalent*/
    public static String convertTobinary(int number) {
    String binString = Integer.toBinaryString(number);
    while (binString.length() < 8) {    //pad with 16 0's
    binString = "0" + binString;}
    return binString;
    }
    /*To not a binary number */
    public static String not(String number) {
    char[] chars = new char[number.length()];
    for(int i = 0; i < number.length(); i++)
    chars[i] = (char) (number.charAt(i) ^ 1); // flip the bottom bit so 0=>1 and 1=>0
    String flipped = new String(chars);
    return flipped;
    }
    /*To convert hexadecimal to binary*/
    public static String hexToBin(String hex){
    String bin = "";
    String binFragment = "";
    int iHex;
    hex = hex.trim();
    hex = hex.replaceFirst("0x", "");

    for(int i = 0; i < hex.length(); i++){
        iHex = Integer.parseInt(""+hex.charAt(i),16);
        binFragment = Integer.toBinaryString(iHex);

        while(binFragment.length() < 4){
            binFragment = "0" + binFragment;
        }
        bin += binFragment;
        
    }
    if(bin.length()<=4)
        {
            bin="0000"+bin;
        }  
    return bin;
}

public static int xor(int dec,String hexa)
{
     
     int hexx = Integer.parseInt(hexa,16);
     int c= dec^hexx;
     return c; 
}
      
       public static void generateKey()
    {
    	try
        {
            for(i=0;i<10;i++)
                {
                    a=(int)(127*Math.random());
                  
                     key[i]=a;
                    String s =Integer.toHexString(a);
                    keyhex[i]=s;
                    gethex[i]=s;
              
                }
            for(int i=0;i<10;i++)
            {key1[i]=key[i];}
           
        }catch(Exception e)
        {
        
    }
    }
           
          public static void storekey(int height,int width)
    {   
        try{
         
        int b=(int)((height*width)/pixelgap);
         int xx=1;
                                                for(i=0;i<10;i++){org_key[0][i]=key[i];}
         for(int bi=0;bi<b+1;bi++){keychange();}for(i=0;i<10;i++){org_key[1][i]=key[i];}
         for(int bi=0;bi<b+1;bi++){keychange();}for(i=0;i<10;i++){org_key[2][i]=key[i];}
         for(int bi=0;bi<b+1;bi++){keychange();}for(i=0;i<10;i++){org_key[3][i]=key[i];}
         for(int bi=0;bi<b+1;bi++){keychange();}for(i=0;i<10;i++){org_key[4][i]=key[i];}
         for(int bi=0;bi<b+1;bi++){keychange();}for(i=0;i<10;i++){org_key[5][i]=key[i];}
         for(int bi=0;bi<b+1;bi++){keychange();}for(i=0;i<10;i++){org_key[6][i]=key[i];}
         for(int bi=0;bi<b+1;bi++){keychange();}for(i=0;i<10;i++){org_key[7][i]=key[i];}
         for(int bi=0;bi<b+1;bi++){keychange();}for(i=0;i<10;i++){org_key[8][i]=key[i];}
         for(int bi=0;bi<b+1;bi++){keychange();}for(i=0;i<10;i++){org_key[9][i]=key[i];}
         
         }catch(Exception e){}
}
     public static void keychange()
 {    int temp[] =new int[10];
   
    for(int i=0;i<10;i++)
       {
           temp[i]=key[i];
       }
       for(int i=0;i<10;i=i+2)
       {
     key[i]=(int) (((2*temp[i])+temp[i+1])%255);
     key[i+1]=(int) ((temp[i]+temp[i+1])%255);
       }
       for(int i=0;i<10;i++)
       {   
          
           keyhex[i]=Integer.toHexString(key[i]);
       }
 }
      
 
 public static double initialcondition()
    {
              String k0= hexToBin(keyhex[0]);
              String k1= hexToBin(keyhex[1]);
              String k2= hexToBin(keyhex[2]);
              String k3= hexToBin(keyhex[3]);
              String k4= hexToBin(keyhex[4]);
              String k5= hexToBin(keyhex[5]);
              String k6= hexToBin(keyhex[6]);
              String k7= hexToBin(keyhex[7]);
              String k8= hexToBin(keyhex[8]);
              String k9= hexToBin(keyhex[9]);
              
              k456=k4+k5+k6;
               String k_3=hexToBin(keyhex[3]);
               double sum=0;
               for(i=0;i<k456.length();i++)
               {
                   if(k456.charAt(i)=='1')
                   {    
                       sum=sum+Math.pow(2,i);
                   }
               }
                x00=sum;
               x0=(x00/(Math.pow(2,24)));
              
                double k3sum=0;
               for(i=0;i<k_3.length();i++)
               {
                   if(k_3.charAt(i)=='1')
                   {    
                       k3sum=k3sum+Math.pow(2,i);
                   }
               }
            
               x11=k3sum;  
               x1=x11/96;
               xinitial=(x0+x1)%1;
               xi=(xinitial*193+0.6828*0)%1;
               xi_1=((3.569946)*(xi)*(1-xi));
                return (double) xi_1;
    }
  public static void resize(String inputImagePath,String outputImagePath, int scaledWidth, int scaledHeight)throws IOException {
 File inputFile = new File(inputImagePath);
 BufferedImage inputImage = ImageIO.read(inputFile);
BufferedImage outputImage = new BufferedImage(scaledWidth,
scaledHeight, inputImage.getType());
Graphics2D g2d = outputImage.createGraphics();
g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
g2d.dispose();
String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);
ImageIO.write(outputImage, formatName, new File(outputImagePath));
}

 public static void encrypt(String path,String encryptpath)
 {
     try{ 
            generateKey();
            double bb;
              int width,height;
            double st1 =0.10,st2 =0.13,st3 =0.34,st4 =0.37,st5 =0.58,st6 =0.62;
            double st7 =0.16,st8 =0.40,st9 =0.62,st10=0.66,st11 =0.19,st12=0.43;
            double st13=0.70,st14=0.22,st15=0.46,st16=0.74,st17=0.25,st18=0.49;
            double st19=0.78,st20=0.28,st21=0.52,st22=0.82,st23=0.31,st24=0.55;
            double st25=0.86,st26=0.31,st27=0.34,st28=0.58,st29=0.86,st30=0.90;
            int count =0;String s1,s2,s3;
         encryptpath=encryptpath+"/encrypt.png";   
         File f=null;
         f= new File(path);
          image = ImageIO.read(f);
         int r,g,b,xx=1;
          width = image.getWidth();
          height = image.getHeight();
         
         if(width>=1024 && height>=720)
         {   
            
             resize(path,encryptpath,1024,720);
             
             f= new File(encryptpath);
             image = ImageIO.read(f);
             width = image.getWidth();
             height = image.getHeight();
             pixelgap=10;
         }
         else if((width%pixelgap!=0)||(height%pixelgap!=0))
         {
         int rem;
         f= new File(path);
         image = ImageIO.read(f);
         width = image.getWidth();
         height = image.getHeight();
         rem=width%pixelgap;
         resize(path,encryptpath,width-rem,height);
           f= new File(encryptpath);
             image = ImageIO.read(f);
             width = image.getWidth();
             height = image.getHeight();
         }
          else 
         {
         f= new File(path);
         image = ImageIO.read(f);
         width = image.getWidth();
         height = image.getHeight();
         
         }
        
        
         String red[]=new String[height*width];
         String green[]=new String[height*width];
         String blue[]=new String[height*width];
         
          for(int di=0;di<10;di++)
            {
                key[di]=key1[di];
            }
         for(int i=0;i<outloop;i++)
            { 
             for(int ai=0; ai<height; ai++){
         for(int j=0; j<width; j++){
             
          Color c = new Color(image.getRGB(j, ai));
               r=c.getRed();
               g=c.getGreen();
               b=c.getBlue();
               bb=initialcondition();
             if(bb>=st1 && bb<st2 || bb>=st3 && bb<st4|| bb>=st5 && bb<st6)
            { 
               red[j]=convertTobinary(r);
               green[j]=convertTobinary(g);
               blue[j]=convertTobinary(b);
               red[j]=not(red[j]);
               green[j]=not(green[j]);
               blue[j]=not(blue[j]);
               r=Integer.parseInt(red[j],2);
               g=Integer.parseInt(green[j],2);
               b=Integer.parseInt(blue[j],2);
             }
            else if(bb>=st2 && bb<st7 || bb>=st4 && bb<st8|| bb>=st9 && bb<st10)
                    {
                     r=xor(r,keyhex[4]);
                     g=xor(g,keyhex[5]);
                     b=xor(b,keyhex[6]);
                    }
            else if(bb>=st7 && bb<st11 || bb>=st8 && bb<st12|| bb>=st10 && bb<st13)
                    {  
                        r=(((r+key[4])+key[5])%256);
                        g=(((g+key[5])+key[6])%256);
                        b=(((b+key[6])+key[4])%256);
                    }
            else if(bb>=st11 && bb<st14  || bb>=st12 && bb<st15|| bb>=st13 && bb<st16)
                    {
                        s1=not(convertTobinary(xor(r,keyhex[4])));
                        s2=not(convertTobinary(xor(g,keyhex[5])));
                       s3=not(convertTobinary(xor(b,keyhex[6])));
                       r=Integer.parseInt(s1,2);
                       g=Integer.parseInt(s2,2);
                       b=Integer.parseInt(s3,2);}
             else if(bb>=st14 && bb<st17 || bb>=st15 && bb<st18|| bb>=st16 && bb<st19)
                    {
                     r=xor(r,keyhex[7]);
                     g=xor(g,keyhex[8]);
                     b=xor(b,keyhex[9]);}
            else if(bb>=st17 && bb<st20 || bb>=st18 && bb<st21|| bb>=st19 && bb<st22)
                    {   r=(((r+key[7])+key[8])%256);
                        g=(((g+key[8])+key[9])%256);
                        b=(((b+key[9])+key[7])%256);
                    }
            else if(bb>=st20 && bb<st26 || bb>=st21 && bb<st24|| bb>=st22 && bb<st25)
                    { 
                       s1=not(convertTobinary(xor(r,keyhex[7])));
                       s2=not(convertTobinary(xor(g,keyhex[8])));
                        s3=not(convertTobinary(xor(b,keyhex[9])));
                       r=Integer.parseInt(s1,2);
                       g=Integer.parseInt(s2,2);
                       b=Integer.parseInt(s3,2);}
            else if(bb>=st26 && bb<st27 || bb>=st24 && bb<st28 || bb>=st29 && bb<st30 )
                  {    
                    r=r;
                    g=g;
                    b=b; }
            else{ 
                    r=r;
                    g=g;
                    b=b;
                }
                 
                 if(xx==pixelgap)
                 {
                 keychange();
                 xx=0;
                 }
                 xx++;
                int p = (r<<16) | (g<<8) | b;
                image.setRGB(j, ai, p);
            }
          }
              
         keychange();
           xx=1;
            f = new File(encryptpath);
            ImageIO.write(image,"png",f);
         }
         
          }catch(Exception e)
       {}
  }
 public static void decrypt(String path,String decryptpath,int []setkey)
 {        
     try
 {
      double bb;
              int width,height;
            double st1 =0.10,st2 =0.13,st3 =0.34,st4 =0.37,st5 =0.58,st6 =0.62;
            double st7 =0.16,st8 =0.40,st9 =0.62,st10=0.66,st11 =0.19,st12=0.43;
            double st13=0.70,st14=0.22,st15=0.46,st16=0.74,st17=0.25,st18=0.49;
            double st19=0.78,st20=0.28,st21=0.52,st22=0.82,st23=0.31,st24=0.55;
            double st25=0.86,st26=0.31,st27=0.34,st28=0.58,st29=0.86,st30=0.90;
            int count =0;String s1,s2,s3;
           decryptpath=decryptpath+"/decrypt.png";
          File f=null;
           f= new File(path);
           int r,g,b,xx=1;
           image = ImageIO.read(f);
        width = image.getWidth();
          height = image.getHeight();
            if(width==1024 && height==720)
         { 
             pixelgap=10;
         }
           for(int k=0;k<10;k++)
               {    
                   key[k]=setkey[k];
                  
                }
         String red[]=new String[height*width];
         String green[]=new String[height*width];
         String blue[]=new String[height*width];
           storekey(height,width);
           
           for(int i=0;i<outloop;i++)
           {   
               for(int k=0;k<10;k++)
               {    
                   key[k]=org_key[decryptkeyset][k];
                  
                }decryptkeyset--;
               
              for(int ai=0; ai<height; ai++){
              for(int j=0; j<width; j++){
            
               Color c = new Color(image.getRGB(j,ai));
               r=c.getRed();
               g=c.getGreen();
               b=c.getBlue();
               bb=initialcondition();
               if(bb>=st1 && bb<st2 || bb>=st3 && bb<st4|| bb>=st5 && bb<st6)
            {
                 red[j]=convertTobinary(r);
                 green[j]=convertTobinary(g);
                 blue[j]=convertTobinary(b);
                 red[j]=not(red[j]);
                 green[j]=not(green[j]);
                 blue[j]=not(blue[j]);
                 r=Integer.parseInt(red[j],2);
                 g=Integer.parseInt(green[j],2);
                 b=Integer.parseInt(blue[j],2);
               
              
            }
            else if(bb>=st2 && bb<st7 || bb>=st4 && bb<st8|| bb>=st9 && bb<st10)
                    {
                     r=xor(r,keyhex[4]);
                     g=xor(g,keyhex[5]);
                     b=xor(b,keyhex[6]);
                     
                    }
            else if(bb>=st7 && bb<st11 || bb>=st8 && bb<st12|| bb>=st10 && bb<st13)
                    {  
                        
                      r = (((r)  + 256 - (key[4]) - (key[5]))%256);
                      g = (((g)  + 256 - (key[5]) - (key[6]))%256);
                      b = (((b)  + 256 - (key[6]) - (key[4]))%256);
                      while(r<0){r=r+256;}
                      while(g<0){g=g+256;}
                      while(b<0){b=b+256;}
                                        
                      
                    }
            else if(bb>=st11 && bb<st14  || bb>=st12 && bb<st15|| bb>=st13 && bb<st16)
                    {
                     red[j]=convertTobinary(r);
                 green[j]=convertTobinary(g);
                 blue[j]=convertTobinary(b);
                  red[j]=not(red[j]);
                 green[j]=not(green[j]);
                 blue[j]=not(blue[j]);
                    r=Integer.parseInt(red[j],2);
                 g=Integer.parseInt(green[j],2);
                 b=Integer.parseInt(blue[j],2);
                 r=xor(r,keyhex[4]);
                     g=xor(g,keyhex[5]);
                     b=xor(b,keyhex[6]);;
                     
                    }
             else if(bb>=st14 && bb<st17 || bb>=st15 && bb<st18|| bb>=st16 && bb<st19)
                    {
                     r=xor(r,keyhex[7]);
                     g=xor(g,keyhex[8]);
                     b=xor(b,keyhex[9]);
                     
                    }
            else if(bb>=st17 && bb<st20 || bb>=st18 && bb<st21|| bb>=st19 && bb<st22)
                    {   
                      r = (((r)  + 256 - (key[7]) - (key[8]))%256);
                      g = (((g)  + 256 - (key[8]) - (key[9]))%256);
                      b = (((b)  + 256 - (key[9]) - (key[7]))%256);
                      while(r<0){r=r+256;}
                      while(g<0){g=g+256;}
                      while(b<0){b=b+256;}
                     
                   
                    }
            else if(bb>=st20 && bb<st26 || bb>=st21 && bb<st24|| bb>=st22 && bb<st25)
                    { 
                      red[j]=convertTobinary(r);
                 green[j]=convertTobinary(g);
                 blue[j]=convertTobinary(b);
                  red[j]=not(red[j]);
                 green[j]=not(green[j]);
                 blue[j]=not(blue[j]);
                    r=Integer.parseInt(red[j],2);
                 g=Integer.parseInt(green[j],2);
                 b=Integer.parseInt(blue[j],2);
                 r=xor(r,keyhex[7]);
                     g=xor(g,keyhex[8]);
                     b=xor(b,keyhex[9]);
                     
                  
                    
                    }
            else if(bb>=st26 && bb<st27 || bb>=st24 && bb<st28 || bb>=st29 && bb<st30 )
                  {    
                    r=r;
                    g=g;
                    b=b;
                    
                  }
             else{  
                    r=r;
                    g=g;
                    b=b;
                  
                
            } 
                if(xx==pixelgap)
                 {
                 keychange();
                 xx=0;
                 }
                 xx++;
        
                int p = (r<<16) | (g<<8) | b;
                image.setRGB(j,ai, p);
            }
          }
            f = new File(decryptpath);
            ImageIO.write(image,"png",f);
          
         }
          
 }catch(Exception e){}
 }
     

}


