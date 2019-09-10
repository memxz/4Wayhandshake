package main;  
  
import it.unisa.dia.gas.jpbc.*;  
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;  
import java.lang.reflect.Proxy;  
import java.util.Calendar;  
import java.text.SimpleDateFormat;  
/**
 * PMK Encrypted Solution
 * Elements of groups, rings and fields are accessible using the <code>Element</code>
 * interface. You can obtain an instance of an Element starting from an algebraic structure, such as a particular
 * message1 with PMK encryption with basic indent
 */
  
public class BasicIdent2 implements Ident {  
  
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
    @Override  
    public void message1() {  
       
    	BasicIdent2Test bt1= new BasicIdent2Test();
    	byte[]ha1=bt1.msg1.getBytes();
    	Tc = P.mulZn(c);// Compute Tb=b*P, must in order
       // c = Zr.newRandomElement().getImmutable();// Generate secret element
        M = pairing.getG1().newElement().setFromHash(ha1, 0, ha1.length)  
                .getImmutable();
        
      //  System.out.println("Authenticator Send Message 1 and produce MIC");  
      //  System.out.println("Message 1 hash value=" + M);  
     //   P = G1.newRandomElement().getImmutable();
     //   Y= M.mulZn(c);
        T1= pairing.pairing(Tc, M).getImmutable(); //Generate MIC code with encrypt by secret element c(PMK)
        T1=T1.powZn(b);
      //  System.out.println("T1=" + T1);  
      //  System.out.println("Supplicent Receive Message 1 and Verify MIC");
     // Tc = P.mulZn(c);//Compute Tc=c*P, must in order
        Y=M.mulZn(c);
        Tb = P.mulZn(b);
        T2= pairing.pairing(Tb, Y).getImmutable();//Compute the MIC
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
    @Override  
    public void message2() {  
    	 
    	BasicIdent2Test bt2= new BasicIdent2Test();
    	byte[]ha2=bt2.msg2.getBytes();
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
      //  Tc = P.mulZn(c);// Compute Tc=c*P, must in order
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
    @Override  
    public void message3() {  
        
    	BasicIdent2Test bt3= new BasicIdent2Test();
    	byte[]ha3=bt3.msg3.getBytes();
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
      //  Tc = P.mulZn(c);// Compute Tc=c*P, must in order
        
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
    @Override  
    public void message4() {  
       
    	BasicIdent2Test bt4= new BasicIdent2Test();
    	byte[]ha4=bt4.msg4.getBytes();
       // c = Zr.newRandomElement().getImmutable();// Generate secret element
        M = pairing.getG1().newElement().setFromHash(ha4, 0, ha4.length)  
                .getImmutable();
        
      //  System.out.println("Supplicent Send Message 4 and produce MIC");  
      //  System.out.println("Message 4 hash value=" + M);  
       // P = G1.newRandomElement().getImmutable();
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
    @Override  
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
  
    @Override  
    public void computePTK1() {  
    	System.out.println("\nSupplicent Computes PTK");  
        Tb = P.mulZn(b);// Compute Tb=b*P, must in order
        Ka= pairing.pairing(Tb, Tc).getImmutable(); 
        Ka = Ka.powZn(a).getImmutable();    
      //  System.out.println("Ka=" + Ka);  
       
    }  
  
    @Override  
    public void computePTK2() {  
       // System.out.println("\nAuthenticator Computes PTK");  
        
        Ta = P.mulZn(a);// Compute Ta=a*P, must in order
        Kb= pairing.pairing(Ta, Tc).getImmutable();
        Kb = Kb.powZn(b).getImmutable(); 
      //  System.out.println("Kb=" + Kb);    
    }  
  
    @Override  
    public void verifyEquiity() {  
       /* System.out.println("\n-------------------Veryfy Equility Of PTK between Supplicient and Authenticator----------------------"); 
        
        if (Ka.isEqual(Kb) )
        {
        int byt = Ka.getLengthInBytes(); // Compute the length of bytes of Ka
        System.out.println("PTK is verified and Send aknowledgement to Authenticator"); 
        System.out.println("Length Of PTK is : " + (byt + 128)); 
        System.out.println("PTK=Ka=Kb= " + Kb);
        }
      else {
    	 System.out.println("PTK is not same between Supplicent and Authenticator"); 
    	 }*/
    }	 
    public static void main(String[] args) {  
        BasicIdent2 ident = new BasicIdent2();  
        // Compute time usage  
        Ident identProxy = (Ident) Proxy.newProxyInstance(  
                BasicIdent2.class.getClassLoader(),  
                new Class[] { Ident.class }, new TimeCountProxyHandle(ident));  
     /*   System.out.println("-------------------4 Way handShake---------------------"  ) ;  
        
        System.out.println("\nSupplicent<<====================Authenticator <Meassage 1>");
        identProxy.buildSystem();
        identProxy.message1();  
        identProxy.computePTK1();  
        
       //identProxy.computePTK2();  
       //identProxy.verifyEquiity();  
        
        System.out.println("\nSupplicent=====================>>Authenticator <Meassage 2>");
        identProxy.message2();  
       //identProxy.buildSystem();  
       //identProxy.computePTK1();  
        identProxy.computePTK2();  
        //identProxy.verifyEquiity();
        System.out.println("\nSupplicent<<====================Authenticator <Meassage 3>");
        identProxy.message3();  
       //identProxy.buildSystem();  
       //identProxy.computePTK1();  
       //identProxy.computePTK2();  
        identProxy.verifyEquiity();
        
        System.out.println("\nSupplicent====================>>Authenticator <Meassage 4>");
        identProxy.message4();  
       // identProxy.buildSystem();  
       // identProxy.computePTK1();  
       // identProxy.computePTK2();  
       //identProxy.verifyEquiity();
        System.out.println("Aknowledgement and 4 way handshake Successfully"); */
    }  
  
}  
