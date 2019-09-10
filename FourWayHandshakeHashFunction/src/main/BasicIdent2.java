package main;  
  
import it.unisa.dia.gas.jpbc.*;  
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;  
import java.lang.reflect.Proxy;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;  
import java.text.SimpleDateFormat;  
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  
import java.util.Random;
  
/**
 * Elements of groups, rings and fields are accessible using the <code>Element</code>
 * interface. You can obtain an instance of an Element starting from an algebraic structure, such as a particular
 * message1 with hash solution(SHA256) self signed encryption
 */
  
public class BasicIdent2  {  
  
    private Element a,b,c,P,Ta,Tb,Tc,Kc,M,Y; 
    protected Element T1,T2,T3,T4,T5,T6,T7,T8,Ka,Kb;
    private Field G1, Zr;  
    private Pairing pairing;  

    
    public BasicIdent2() {  
        init();  
    }  
  
    /** 
    * Initialize
    */  
    protected void init() {  
        pairing = PairingFactory.getPairing("a.properties");//  use type a.property
        PairingFactory.getInstance().setUsePBCWhenPossible(true);  
        checkSymmetric(pairing);  
        //Initial a,b,c as element of Zr
        Zr = pairing.getZr();  
        a = Zr.newElement();  
        b = Zr.newElement(); 
        c = Zr.newElement(); 
        //Initial M,P,Ta,Tb,Tc,T1-T8 and Y as element of G1 and G1 is group of addition
        G1 = pairing.getG1();
        M = G1.newElement(); 
        P = G1.newElement();  
        Ta = G1.newElement();  
        Tb = G1.newElement();  
        Tc = G1.newElement();
        T1 = G1.newElement();
        T2 = G1.newElement();
        T3 = G1.newElement();
        T4 = G1.newElement();
        T5 = G1.newElement();
        T6 = G1.newElement();
        T7 = G1.newElement();
        T8 = G1.newElement();
        Y = G1.newElement();
        //initial variable Ka,Kb,Kc as element of GT and GT is a multiply Group
        Field GT = pairing.getGT();  
        Ka = GT.newElement();  
        Kb = GT.newElement(); 
        Kc = GT.newElement(); 
        
    }  
  
    /** 
     * Check whether pairing,If not output Not pairing
     *  
     * @param pairing 
     */  
    private void checkSymmetric(Pairing pairing) {  
        if (!pairing.isSymmetric()) {  
            throw new RuntimeException("Not pairing Based Crypt!");  
        }  
    } 
   
    /** 
     * Send message 1 from authenticator to supplicent
     */
  //  @Override   
    	public void messageOne(){
    		 String  msg1="message1";
    		 String encryptedText =message1(msg1,"");
    		 //System.out.println(encryptedText);
    	}
 
    	 public String message1(String strSrc, String encName) {
    	    
 	        MessageDigest md = null;
 	        String strDes = null;

 	        byte[] bt = strSrc.getBytes();
 	        try {
 	            if (encName == null || encName.equals("")) {
 	                encName = "SHA-256";
 	            }
 	            md = MessageDigest.getInstance(encName);
 	            md.update(bt);
 	            strDes = bytes2Hex(md.digest()); // to HexString
 	        } catch (NoSuchAlgorithmException e) {
 	            return null;
 	        }
 	        return strDes;
 	    }

 	    public static String bytes2Hex(byte[] bts) {
 	        String des = "";
 	        String tmp = null;
 	        for (int i = 0; i < bts.length; i++) {
 	            tmp = (Integer.toHexString(bts[i] & 0xFF));
 	            if (tmp.length() == 1) {
 	                des += "0";
 	            }
 	            des += tmp;
 	        }
 	        return des;
 	    
    /*	BasicIdent2Test bt1= new BasicIdent2Test();//used for BasicIdent2Test in 4way handshake2
    	  byte[]ha1=bt1.msg1.getBytes();
       // c = Zr.newRandomElement().getImmutable();// Generate secret element
        M = pairing.getG1().newElement().setFromHash(ha1, 0, ha1.length)  
                .getImmutable();
        
      //  System.out.println("Authenticator Send Message 1 and produce MIC");  
      //  System.out.println("Message 1 hash value=" + M);  
      //  P = G1.newRandomElement().getImmutable();
        Y= M.mulZn(c);
        T1= pairing.pairing(P, Y).getImmutable(); //Generate MIC code with encrypt by secret element c(PMK)
      //  System.out.println("T1=" + T1);  
      //  System.out.println("Supplicient Receive Message 1 and Verify MIC");
      // Tc = P.mulZn(c);//Compute Tc=c*P, must in order
        
        T2= pairing.pairing(Tc, M).getImmutable();//Compute the MIC
      //  System.out.println("T2=" + T2); 
      /*  if(T1.isEqual(T2)){
        	System.out.println("Message 1 is authenticated and not changed");  
        }
        	else {
        		System.out.println("Message 1 was modified");
        		throw new RuntimeException("Communication is Not Safe!"); 
        }*/ 
    }  
 
    /** 
     * Send message2 from supplicent to authenticator
     */
 //   @Override  
    public void message2() {  
    	 
    	String msg2="message2wwwww";
    	byte[]ha2=msg2.getBytes();
       // c = Zr.newRandomElement().getImmutable();// Generate secret element
        M = pairing.getG1().newElement().setFromHash(ha2, 0, ha2.length)  
                .getImmutable();
        
       // System.out.println("Supplicent Send Message 2 and Produce MIC");  
       // System.out.println("Message 2 hash value=" + M);  
       // P = G1.newRandomElement().getImmutable();
        Y= M.mulZn(c);  //Generate secret Y
        T3= pairing.pairing(P, Y).getImmutable(); //Generate MIC code with encrypt by secret element c(PMK)
       // System.out.println("T3=" + T3);  
       // System.out.println("Authenticator Receive Message 2 and Verify MIC");//
       // Tc = P.mulZn(c);// Compute Tc=c*P, must in order
        T4= pairing.pairing(Tc, M).getImmutable(); //Generate MIC code with encrypt by secret element c(PMK)
       // System.out.println("T4=" + T4); 
      /*  if(T3.isEqual(T4)){
        	System.out.println("Message 2 is authenticated and not changed");  
        }
        	else {
        		System.out.println("Message 2 was modified");
        		throw new RuntimeException("Communication is Not Safe!"); 
        }*/
    }  
 
    /** 
     * Send message4 from authenticator to supplicent
     */
 //   @Override  
    public void message3() {  
        
    	String msg3="message3";
    	byte[]ha3=msg3.getBytes();
       // c = Zr.newRandomElement().getImmutable();// Generate secret element
        M = pairing.getG1().newElement().setFromHash(ha3, 0, ha3.length)  
                .getImmutable();
        
        //System.out.println("Authenticator Send Message 3 and produce MIC");  
       // System.out.println("Message 3 hash value=" + M);  
       // P = G1.newRandomElement().getImmutable();
        Y= M.mulZn(c);
        T5= pairing.pairing(P, Y).getImmutable(); 
       // System.out.println("T5=" + T5);  
       // System.out.println("Supplicient Receive Message 3 and Verify MIC");
       // Tc = P.mulZn(c);// Compute Tc=c*P, must in order
        
        T6= pairing.pairing(Tc, M).getImmutable();
       /* System.out.println("T6=" + T6); 
        if(T5.isEqual(T6)){
        	System.out.println("Message 3 is authenticated and not changed");  
        }
        	else {
        		System.out.println("Message 3 was modified");
        		throw new RuntimeException("Communication is Not Safe!");  
        }*/
    }
    
    /** 
     * Send message4 from supplicent to authenticator
     */   
 //   @Override  
    public void message4() {  
       
    	String msg4="message4wwwww";
    	byte[]ha4=msg4.getBytes();
       // c = Zr.newRandomElement().getImmutable();// Generate secret element
        M = pairing.getG1().newElement().setFromHash(ha4, 0, ha4.length)  
                .getImmutable();
        
      //  System.out.println("Supplicent Send Message 4 and produce MIC");  
      //  System.out.println("Message 4 hash value=" + M);  
      //  P = G1.newRandomElement().getImmutable();
        Y= M.mulZn(c);
        T7= pairing.pairing(P, Y).getImmutable(); 
       // System.out.println("T7=" + T7);  
       // System.out.println("Authenticator Receive Message 4 and Verify MIC");
       // Tc = P.mulZn(c);// Compute Tc=c*P, must in order
        
        T8= pairing.pairing(Tc, M).getImmutable();
      //  System.out.println("T8=" + T8); 
      /*  if(T7.isEqual(T8)){
        	System.out.println("Message 4 is authenticated and not changed");  
        }
        	else {
        		System.out.println("Message 4 was modified");
        		throw new RuntimeException("Communication is Not Safe!"); 
        }*/
    }  
 //   @Override  
    public void buildSystem() {  
      //  System.out.println("\n-------------------Given PMK(secret) --------------------\n");  
        c = Zr.newRandomElement().getImmutable();// //Generate element c of Zr 
        P = G1.newRandomElement().getImmutable();// Generate element p of G1
        Tc = P.mulZn(c);// Compute Tc=c*P, must in order
        a = Zr.newRandomElement().getImmutable();
        b = Zr.newRandomElement().getImmutable();
        
       // System.out.println("P=" + P);  
       // System.out.println("c=" + c);  
       // System.out.println("Tc=" + Tc);  
    }  
  
 //   @Override  
    public void computePTK1() {  
    	//System.out.println("\nSupplicent Computes PTK");  
        Tb = P.mulZn(b);// Compute Tb=b*P, must in order
        Ka= pairing.pairing(Tb, Tc).getImmutable(); 
        Ka = Ka.powZn(a).getImmutable();    
      //  System.out.println("Ka=" + Ka);  
       
    }  
  
  //  @Override  
    public void computePTK2() {  
       // System.out.println("\nAuthenticator Computes PTK");  
        
        Ta = P.mulZn(a);// Compute Ta=a*P, must in order
        Kb= pairing.pairing(Ta, Tc).getImmutable();
        Kb = Kb.powZn(b).getImmutable(); 
      //  System.out.println("Kb=" + Kb);    
    }  
  
  //  @Override  
    public void verifyEquiity() {  
        if (!Ka.isEqual(Kb) )
        {throw new RuntimeException("Not pairing");  
        }
      
    }		 
    public static void main(String[] args) {  
    	BasicIdent2 ident = new BasicIdent2();  
        Random numberGenerator = new Random ();
      
        int[] arrayOfGenerator = new int[15];
        int[] countOfArray     = new int[5];
        int count;
        // Compute time usage  
        long total0=0;
        long total1=0;
        long total2=0;
        long total3=0;
        long total4=0;
        System.out.println("-------------------4 Way handShake---------------------"  ) ;  
        
        for (int countOfGenerator = 0; countOfGenerator < 1000; countOfGenerator++)
        {
            int prob=numberGenerator.nextInt(100)+1;
            if (prob<=80){
        	count = numberGenerator.nextInt(4)+1;
            }else {
            	count=0;
            }
            countOfArray[count]++;
            
            if(count==0){
            	long startTime = System.currentTimeMillis();
                ident.buildSystem();
                ident.messageOne(); 
                ident.computePTK1();
                ident.message2(); 
                ident.message3();
                ident.computePTK2();
                ident.verifyEquiity();
                ident.message4();
                long stopTime = System.currentTimeMillis();
                long elapsedTime = stopTime - startTime;
                total0=total0+elapsedTime;
            }
            else if(count==1){
            	long startTime = System.currentTimeMillis();
                ident.buildSystem();
                long stopTime = System.currentTimeMillis();
                long elapsedTime = stopTime - startTime;
                total1=total1+elapsedTime;
                
            }
            else if(count==2){
            	long startTime = System.currentTimeMillis();
                ident.buildSystem();
                ident.messageOne(); 
                ident.computePTK1();
                long stopTime = System.currentTimeMillis();
                long elapsedTime = stopTime - startTime;
                total2=total2+elapsedTime;
            }
            else if(count==3){
            	long startTime = System.currentTimeMillis();
                ident.buildSystem();
                ident.messageOne(); 
                ident.computePTK1();
                ident.message2(); 
                long stopTime = System.currentTimeMillis();
                long elapsedTime = stopTime - startTime;
                total3=total3+elapsedTime;
            }
            else {
            	long startTime = System.currentTimeMillis();
                ident.buildSystem();
                ident.messageOne(); 
                ident.computePTK1();
                ident.message2(); 
                ident.message3();
                ident.computePTK2();
                ident.verifyEquiity();
                long stopTime = System.currentTimeMillis();
                long elapsedTime = stopTime - startTime;
                total4=total4+elapsedTime;
            }
        }
  
  
        for (int countOfNumbers = 0; countOfNumbers < countOfArray.length; countOfNumbers++)
        {	
            System.out.println("The number " + (countOfNumbers + 0) + " occurs " + countOfArray[countOfNumbers] + " times."); 
           
        }
        long Time0=(long) ((0.023*4*(countOfArray[0])+total0));
		System.out.println("The total communication time is " + Time0+"ms,when there is no attacks");
	
		long Time1=(long) ((0.022*(countOfArray[1])+total1));
		System.out.println("The total communication time is " +Time1+"ms, when there is attack on message 1");

		long Time2=(long) ((0.023*(countOfArray[2])+total2));
		System.out.println("The total communication time is " +Time2+"ms, when there is attack on message 2");
	
		long Time3=(long) ((0.023*2*(countOfArray[3])+total3));
		System.out.println("The total communication time is " +Time3+"ms, when there is attack on message 3");

		long Time4=(long) ((0.023*3*(countOfArray[4])+total4));
		System.out.println("The total communication time is " +Time4+"ms, when there is attack on message 4");
		System.out.println("Average communication time is "+(Time0+Time1+Time2+Time3+Time4)/200+"ms");
        
    }  
  
}  
