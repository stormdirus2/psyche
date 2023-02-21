package net.scirave.psyche;

public class Psyche {
    public static final String MOD_ID = "psyche";
    // We can use this if we don't want to use DeferredRegister
    
    public static void init() {
        System.out.println(PsychePlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
    }
}
