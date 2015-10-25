package uk.ac.ncl.reactivearch.client.notify;


import java.util.Random;

import uk.ac.ncl.reactivearch.client.notify.notifier.NotificationType;
import uk.ac.ncl.reactivearch.client.notify.notifier.NotifierDialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class Tester {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Parent shell");
        shell.setSize(60, 60);
        shell.setLayout(new FillLayout());
      
        Button tester = new Button(shell, SWT.PUSH);
        tester.setText("Push me!");
        tester.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(Event event) {
                int max = NotificationType.values().length;
                Random r = new Random();
                int toUse = r.nextInt(max);
                
                NotifierDialog.notify("Hi [Developer], notification widget!", "Developer [DEV12edft23456] has changed artefact(s) [art123456] for Project [Airlock]!", NotificationType.values()[toUse]);
            }
            
        });
        shell.open();        
        

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }
        display.dispose();

    }

}
