package api;
import system.*;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;
import java.awt.BorderLayout;
import java.awt.Container;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class Client extends JFrame{
    final private Space space;
    private Task task;

    public Client(Task task, String title, String domainName) throws RemoteException, NotBoundException, MalformedURLException{
    System.setSecurityManager( new SecurityManager() );
    setTitle( title );
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    this.task = task;
    final String url = "rmi://" + domainName + ":" + Space.PORT + "/" + Space.SERVICE_NAME;
    space = (Space) Naming.lookup( url );
    }

    public Space getSpace(){return space;}

    public void setTask(Task task){this.task = task;}

    private void view(final JLabel jLabel){
        final Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(new JScrollPane(jLabel), BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    public void run() throws RemoteException{
        try{
            final long startTime = System.nanoTime();
            space.putReady(task);
            Argument temp = space.getResult();
            //System.out.println("yosh " + temp);
            view(task.viewResult(temp.getValue()));
            //space.exit();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        Logger.getLogger( this.getClass().getCanonicalName() )
            .log( Level.INFO, "Job run time: {0} ms.", ( System.nanoTime() - startTime) / 1000000 );
    }
}
