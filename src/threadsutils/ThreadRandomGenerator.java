package threadsutils;;
/**
* @author Vilvens
*/
public class ThreadRandomGenerator extends java.lang.Thread
{
    private UtilisateurNombre utilisateurThread;
    private int borneInferieure, borneSuperieure, multipleDeclenchement, tempsPause;
    private int nombreProduit;
    /**
     *
     * @param un
     * @param bi
     * @param bs
     * @param md
     * @param tp
     */
    public ThreadRandomGenerator(UtilisateurNombre un, int bi, int bs, int md, int tp)
    {
        utilisateurThread = un; borneInferieure=bi; borneSuperieure=bs;
        multipleDeclenchement = md; tempsPause = tp; nombreProduit = -1;
    }
    public void run()
    {
        Double dr;
        while (true)
        {
            dr = new Double(borneInferieure +
            Math.random()*(borneSuperieure - borneInferieure));
            nombreProduit = dr.intValue();
            System.out.println(utilisateurThread.getIdentifiant() +
            "> nombreProduit = " + nombreProduit);
            if (nombreProduit % multipleDeclenchement == 0)
            {
                System.out.println(utilisateurThread.getIdentifiant() +
                "> -------------- !!!!!!! " + nombreProduit + "!!!!");
                utilisateurThread.traiteNombre(nombreProduit);
            }
            try
            {
                Thread.sleep(tempsPause*1000);
            }
            catch (InterruptedException e)
            {
                System.out.println("Erreur de thread interrompu : " + e.getMessage());
            }
        }
    }
}
