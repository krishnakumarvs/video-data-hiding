/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package General;

/**
 *
 * @author kakes
 */
public class Nimbus {

    public static void loadLoogAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
