package net.thetabx.jmcgui.Frames;

import net.thetabx.jmcgui.MPWPinger;
import net.thetabx.jmcgui.McConstants;
import net.thetabx.jmcgui.Utils.AddressSpliter;
import net.thetabx.jmcgui.Utils.IniFile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class FavServersFrame extends JFrame {

    /**
     *
     */

    private boolean connecting = false;
    private PingThread tPing;

    private static final long serialVersionUID = 1L;
    private JPanel pan = new JPanel();
    private JTable table;
    private DefaultTableModel model;
    private final JPanel pButtons = new JPanel();
    private final JButton btnAdd = new JButton("+");
    private final JButton btnDel = new JButton("-");

    public FavServersFrame(final IniFile iniFile) {
        this.setTitle("Favorite servers");

        this.setSize(554, 245);

        this.setLocationRelativeTo(null);

        this.setContentPane(pan);
        pan.setLayout(new BorderLayout(0, 0));

        int nServers = iniFile.getNbFavServers();
        Object[][] jTableData = new Object[nServers][3];
        for (int i = 0; i < nServers; i++) {
            jTableData[i] = new Object[]{iniFile.getServerName(i), iniFile.getServerAddress(i), "", "", 0};
        }
        String jTableCNames[] = {"Name", "Address", "Description", "Users", "Ping"};
        model = new DefaultTableModel(jTableData, jTableCNames);

        table = new JTable(model) {
            private static final long serialVersionUID = 1L;
            /*public boolean isCellEditable(int rowIndex, int colIndex) {
				  return false; //Disallow the editing of any cell
			  }*/
        };
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //table.getColumnModel().getColumn(3).setPreferredWidth(40);
        //table.getColumnModel().getColumn(4).setPreferredWidth(40);
        JScrollPane scollPane = new JScrollPane(table);
        pan.add(scollPane, BorderLayout.CENTER);

        pan.add(pButtons, BorderLayout.SOUTH);

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                FavServersAddFrame favServersAdd = new FavServersAddFrame(iniFile);
                favServersAdd.setVisible(true);
                favServersAdd.addComponentListener(new ComponentListener() {

                    @Override
                    public void componentHidden(ComponentEvent arg0) {
                        if (table.getRowCount() != iniFile.getNbFavServers())
                            model.addRow(new Object[]{iniFile.getServerName(iniFile.getNbFavServers() - 1), iniFile.getServerAddress(iniFile.getNbFavServers() - 1), "", "", 0});
                    }

                    @Override
                    public void componentMoved(ComponentEvent arg0) {
                    }

                    @Override
                    public void componentResized(ComponentEvent arg0) {
                    }

                    @Override
                    public void componentShown(ComponentEvent arg0) {
                    }
                });
            }
        });
        pButtons.add(btnAdd);

        btnDel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1)
                    return;
                model.removeRow(selectedRow);
                iniFile.removeServer(selectedRow);
                iniFile.store();
            }
        });
        pButtons.add(btnDel);

        JButton btnConnect = new JButton("Connect");
        btnConnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                iniFile.setLastConnectedServer(table.getSelectedRow());
                iniFile.store();
                hideFrame();
            }
        });
        pButtons.add(btnConnect);
        this.getRootPane().setDefaultButton(btnConnect);
        if (nServers > 0)
            table.setRowSelectionInterval(iniFile.getLastConnectedServer(), iniFile.getLastConnectedServer());

        tPing = new PingThread();
        tPing.start();
    }

    public void hideFrame() {
        this.connecting = true;
        this.setVisible(false);
    }

    public boolean isConnecting() {
        return connecting;
    }

    public void stopPingThread() {
        tPing.stopNow();
    }

    public class PingThread extends Thread {

        private boolean run = false;

        public PingThread() {
            this.setName("FavServersPingThread");
        }

        public void run() {
            run = true;
            while (run) {
                for (int i = 0; i < table.getRowCount(); i++) {
                    AddressSpliter spliter = new AddressSpliter();
                    if (!spliter.split((String) table.getValueAt(i, 1))) {
                        setRow("Invalid address", "0", "0", -1, i);
                        continue;
                    }

                    MPWPinger pinger = new MPWPinger(spliter.getHost(), spliter.getPort());

                    if (!pinger.ping()) {
                        setRow("Can't ping", "0", "0", -1, i);
                        continue;
                    }

                    setRow(pinger.getDescription(), pinger.getNUsers(), pinger.getNUsersMax(), pinger.getPing(), i);
                }

                try {
                    Thread.sleep(McConstants.PINGDELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void setRow(String description, String nUsers, String nUsersMax, long ping, int row) {
            table.setValueAt(description, row, 2);
            table.setValueAt(nUsers + "/" + nUsersMax, row, 3);
            table.setValueAt(ping, row, 4);
        }

        public void stopNow() {
            run = false;
        }
    }
}
