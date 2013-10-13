package net.thetabx.jmcgui.Frames;

import net.thetabx.jmcgui.DataTypes.MapCoord;
import net.thetabx.jmcgui.DataTypes.MapData;
import net.thetabx.jmcgui.MPWEntities.ESelf;
import net.thetabx.jmcgui.MPWEntities.EntityType;
import net.thetabx.jmcgui.MPWEntities.MPWEntity;
import net.thetabx.jmcgui.McGlobalData;
import net.thetabx.jmcgui.MinecraftProtocolWrapper;
import net.thetabx.jmcgui.Utils.AddressSpliter;
import net.thetabx.jmcgui.Utils.ChatColors;
import net.thetabx.jmcgui.Utils.IniFile;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel pan = new JPanel();
    private MinimapPanel pMinimap;
    private LoginFrame lFrame;
    private JButton btnDisconnect;
    private JButton btnSend;
    private JLabel lblLoginStatus;
    private JLabel lblConnectStatus;
    private JLabel lblEntities;
    private JLabel lblTicks;
    private JLabel lblMemory;
    private JLabel lblTime;
    private JLabel lblPing;

    private JLabel lblLife;
    private JLabel lblHunger;
    private JLabel lblLevel;
    private JLabel lblExp;
    private JLabel lblPos;

    private JTextField txtChatSend;
    private JTextArea txtChatbox;
    private JSlider sliderZoom;

    private FrameThread tFrame;
    private IniFile iniFile = new IniFile();
    private int FPS = iniFile.getFPS();

    private MinecraftProtocolWrapper MPW;


    public MainFrame() {
        this.setTitle("Minecraft Botty");

        this.setSize(960, 400);
        this.setResizable(false);

        this.setLocationRelativeTo(null);

        this.setContentPane(pan);
        pan.setLayout(new BorderLayout(0, 0));

        JMenuBar menuBar = new JMenuBar();
        pan.add(menuBar, BorderLayout.NORTH);

        JMenu mnFile = new JMenu("Connection");
        menuBar.add(mnFile);

        JMenuItem mntmLogin = new JMenuItem("Login");
        mntmLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lFrame = new LoginFrame(iniFile);
                lFrame.setVisible(true);
                lFrame.addComponentListener(new ComponentListener() {

                    @Override
                    public void componentHidden(ComponentEvent arg0) {
                        if (lFrame.isLoggedIn())
                            lblLoginStatus.setText(" Logged in as " + lFrame.getRealNickname() + " ");
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
        mnFile.add(mntmLogin);


        JMenuItem mntmConnect = new JMenuItem("Servers");
        mntmConnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final FavServersFrame favServersFrame = new FavServersFrame(iniFile);
                favServersFrame.setVisible(true);
                favServersFrame.addComponentListener(new ComponentListener() {

                    @Override
                    public void componentHidden(ComponentEvent e) {
                        favServersFrame.stopPingThread();
                        if (!favServersFrame.isConnecting())
                            return;

                        //if(lFrame == null || !lFrame.isLoggedIn()) {
                        //	JOptionPane.showMessageDialog(null, "You're not logged in !", "Error", JOptionPane.ERROR_MESSAGE);
                        //	return;
                        //}

                        int nSelectedServer = iniFile.getLastConnectedServer();
                        if (nSelectedServer == -1) {
                            JOptionPane.showMessageDialog(null, "Invalid server selection", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        AddressSpliter spliter = new AddressSpliter();
                        if (!spliter.split(iniFile.getServerAddress(nSelectedServer))) {
                            JOptionPane.showMessageDialog(null, "Invalid server address", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        connectToServer(spliter.getHost(), spliter.getPort());
                    }

                    @Override
                    public void componentMoved(ComponentEvent e) {
                    }

                    @Override
                    public void componentResized(ComponentEvent e) {
                    }

                    @Override
                    public void componentShown(ComponentEvent e) {
                    }
                });
            }
        });
        mnFile.add(mntmConnect);

        JMenuItem mntmLoginConnect = new JMenuItem("Login & Connect");
        mntmLoginConnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (lFrame == null || !lFrame.isLoggedIn()) {
                    lFrame = new LoginFrame(iniFile);
                    if (!lFrame.launcherLogin()) {
                        JOptionPane.showMessageDialog(null, "Can't log in", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                lblLoginStatus.setText(" Logged in as " + lFrame.getRealNickname() + " ");

                int nSelectedServer = iniFile.getLastConnectedServer();
                AddressSpliter spliter = new AddressSpliter();
                if (!spliter.split(iniFile.getServerAddress(nSelectedServer))) {
                    JOptionPane.showMessageDialog(null, "Invalid server address", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                connectToServer(spliter.getHost(), spliter.getPort());
            }
        });
        mnFile.add(mntmLoginConnect);

        JMenu mnSettings = new JMenu("Settings");
        menuBar.add(mnSettings);

        JMenuItem mntmOptions = new JMenuItem("Options");
        mntmOptions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                OptionsFrame oFrame = new OptionsFrame(iniFile);
                oFrame.setVisible(true);
                oFrame.addComponentListener(new ComponentListener() {

                    @Override
                    public void componentHidden(ComponentEvent e) {
                        FPS = iniFile.getFPS();
                    }

                    @Override
                    public void componentMoved(ComponentEvent e) {
                    }

                    @Override
                    public void componentResized(ComponentEvent e) {
                    }

                    @Override
                    public void componentShown(ComponentEvent e) {
                    }
                });
            }
        });
        mnSettings.add(mntmOptions);

        JPanel pStatusBar = new JPanel();
        pan.add(pStatusBar, BorderLayout.SOUTH);
        pStatusBar.setLayout(new BorderLayout(0, 0));


        btnDisconnect = new JButton("Disconnect");
        pStatusBar.add(btnDisconnect, BorderLayout.EAST);
        btnDisconnect.setEnabled(false);
        btnDisconnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (MPW == null || !MPW.getGData().isRunning())
                    return;

                disconnectFromServer();
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                if (MPW == null || !MPW.getGData().isRunning())
                    return;

                storeData();
                disconnectFromServer();
            }
        }));

        JPanel pStatusLabels = new JPanel();
        pStatusBar.add(pStatusLabels, BorderLayout.WEST);
        pStatusLabels.setLayout(new BorderLayout(0, 0));

        lblLoginStatus = new JLabel(" Not logged in ");
        pStatusLabels.add(lblLoginStatus, BorderLayout.WEST);

        JSeparator separator_1 = new JSeparator();
        separator_1.setOrientation(SwingConstants.VERTICAL);
        pStatusLabels.add(separator_1, BorderLayout.CENTER);

        lblConnectStatus = new JLabel(" Not connected ");
        pStatusLabels.add(lblConnectStatus, BorderLayout.EAST);

        JSeparator separator = new JSeparator();
        pStatusBar.add(separator, BorderLayout.NORTH);

        JPanel pCenter = new JPanel();
        pCenter.setOpaque(false);
        pan.add(pCenter, BorderLayout.CENTER);
        pCenter.setLayout(new BorderLayout(0, 0));

        txtChatbox = new JTextArea();
        txtChatbox.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtChatbox.setEditable(false);
        JScrollPane scollPane = new JScrollPane(txtChatbox);
        pCenter.add(scollPane, BorderLayout.CENTER);

        JPanel pTop = new JPanel();
        pCenter.add(pTop, BorderLayout.NORTH);
        pTop.setLayout(new BorderLayout(0, 0));

        JPanel pTopInfo = new JPanel();
        pTop.add(pTopInfo, BorderLayout.NORTH);

        lblTicks = new JLabel("0 ticks/s");
        pTopInfo.add(lblTicks);

        lblEntities = new JLabel("0 Entities");
        pTopInfo.add(lblEntities);

        lblMemory = new JLabel("Memory");
        pTopInfo.add(lblMemory);

        lblTime = new JLabel("Time");
        pTopInfo.add(lblTime);

        lblPing = new JLabel("Ping");
        pTopInfo.add(lblPing);

        JPanel pTopStats = new JPanel();
        pTop.add(pTopStats, BorderLayout.SOUTH);

        lblLife = new JLabel("Life");
        pTopStats.add(lblLife);

        lblHunger = new JLabel("Hunger");
        pTopStats.add(lblHunger);

        lblLevel = new JLabel("Level");
        pTopStats.add(lblLevel);

        lblExp = new JLabel("Experience");
        pTopStats.add(lblExp);

        lblPos = new JLabel("Position");
        pTopStats.add(lblPos);

        JPanel pChat = new JPanel();
        pCenter.add(pChat, BorderLayout.SOUTH);

        txtChatSend = new JTextField();
        txtChatSend.setEnabled(false);
        pChat.add(txtChatSend);
        txtChatSend.setColumns(38);

        btnSend = new JButton("Send");
        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String message = txtChatSend.getText();
                txtChatSend.setText("");
                if (message == null || message.isEmpty())
                    return;

                MPW.getGData().addToSendMessage(message);
            }
        });
        btnSend.setEnabled(false);
        pChat.add(btnSend);
        this.getRootPane().setDefaultButton(btnSend);

        JPanel pLeft = new JPanel();
        pLeft.addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent arg0) {
                if (arg0.getWheelRotation() < 0)
                    sliderZoom.setValue(sliderZoom.getValue() + 1);
                else
                    sliderZoom.setValue(sliderZoom.getValue() - 1);
            }
        });
        pan.add(pLeft, BorderLayout.EAST);
        pLeft.setLayout(new BorderLayout(0, 0));

        pMinimap = new MinimapPanel();
        pMinimap.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        pMinimap.setTexturePack("drawable/terrain.png");
        pLeft.add(pMinimap, BorderLayout.CENTER);

        sliderZoom = new JSlider();
        sliderZoom.setMinimum(1);
        sliderZoom.setMaximum(128);
        sliderZoom.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ev) {
                pMinimap.setZoom(sliderZoom.getValue());
            }
        });
        sliderZoom.setValue(iniFile.getZoom());

        pLeft.add(sliderZoom, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //pMinimap.setPreferredSize(new Dimension(pCenter.getHeight() - 12, pCenter.getHeight() - 12));
        pMinimap.setPreferredSize(new Dimension(pMinimap.getHeight() - 12, pMinimap.getHeight() - 12));
    }

    public void connectToServer(String host, int port) {
        if (MPW != null && MPW.getGData().isRunning())
            disconnectFromServer();

        MPW = new MinecraftProtocolWrapper(host, port);
        MPW.setNick((lFrame == null || !lFrame.isLoggedIn() ? iniFile.getLoginNickname() : lFrame.getRealNickname()));
        MPW.setSessionId((lFrame == null || !lFrame.isLoggedIn() ? null : lFrame.getSessionId()));
        if (!MPW.connect())
            JOptionPane.showMessageDialog(null, "Cannot connect to server", "Error", JOptionPane.ERROR_MESSAGE);

        tFrame = new FrameThread(MPW.getGData());
        tFrame.setConnectedUI(host, port);
        tFrame.start();
    }

    public void disconnectFromServer() {
        tFrame.stopNow();
        MPW.disconnect();
    }

    public void storeData() {
        iniFile.setZoom(sliderZoom.getValue());
        iniFile.store();
    }

    public void addTextToChatbox(String str) {
        txtChatbox.setText(txtChatbox.getText() + ChatColors.removeColorTags(str) + "\r\n");
        txtChatbox.setCaretPosition(txtChatbox.getText().length());
    }

    public class FrameThread extends Thread {
        private boolean run = false;
        private McGlobalData gData;

        public FrameThread(McGlobalData gData) {
            this.setName("GuiUpdateThread");
            this.gData = gData;
        }

        public void setConnectedUI(String address, int port) {
            lblConnectStatus.setText(" Connected to " + address + ":" + port + " ");
        }

        public void run() {
            run = true;

            // Static UI
            btnDisconnect.setEnabled(true);
            btnSend.setEnabled(true);
            txtChatSend.setEnabled(true);

            int tick = 0;
            long timeStart = System.currentTimeMillis();
            Runtime runtime = Runtime.getRuntime();
            while (run) {
                if (!gData.isRunning()) {
                    run = false;
                    continue;
                }

                // Entities
                int nEntities = gData.getEntitiesSize();
                lblEntities.setText(nEntities + " Entities");

                // FPS
                if (timeStart + 1000 < System.currentTimeMillis()) {
                    timeStart = System.currentTimeMillis();
                    lblTicks.setText(tick + " ticks/s");
                    tick = 0;
                }
                tick++;

                // Memory usage
                long freeMemory = runtime.freeMemory();
                long allocatedMemory = runtime.totalMemory();
                float memoryUsed = (float) (Math.round((float) ((allocatedMemory - freeMemory) / 1024.0 / 1024.0) * 10.0) / 10.0);
                lblMemory.setText(memoryUsed + "Mb");

                // Time
                int timeDay = (int) ((gData.getTimeTicks() + 6000) % 24000);
                int timeMin = (int) ((timeDay % 1000) / 20);
                lblTime.setText(timeDay / 1000 + "h" + (timeMin < 10 ? "0" + timeMin : timeMin));

                // Ping
                lblPing.setText("Ping");

                // Stats
                lblLife.setText("HP: " + gData.getHealth());
                lblHunger.setText("Hunger: " + gData.getHunger());
                lblLevel.setText("Lvl: " + gData.getLevel());
                lblExp.setText("Exp: " + gData.getExperience());


                // Minimap update
                ESelf self = null;
                synchronized (gData) {
                    self = ((ESelf) gData.getEntity(gData.getPlayerEntityId()));
                }

                Enumeration<Integer> entitiesKeysEnum = gData.getEntitiesKeys();
                while (entitiesKeysEnum.hasMoreElements()) {
                    MPWEntity e = null;
                    synchronized (gData) {
                        e = gData.getEntity(entitiesKeysEnum.nextElement());
                    }

                    if (self != null && e != null && e.getType() == EntityType.Player)
                        pMinimap.setDot(e.getX() - self.getX(), e.getZ() - self.getZ(), e.getY() - self.getY(), e.getName());
                }
                if (self != null) {
                    int[] xOffset = {-16, -16, -16, 0, 0, 0, 16, 16, 16};
                    int[] zOffset = {-16, 0, 16, -16, 0, 16, -16, 0, 16};
                    for (int i = 0; i < xOffset.length; i++) {
                        MapCoord coord = new MapCoord(self.getX() + xOffset[i], self.getZ() + zOffset[i]);
                        //System.out.println(coord.toString());
                        MapData map = gData.getMapData(coord);
                        if (map != null) {
                            byte[][] topLayer = map.getTopLayer();
                            if (topLayer != null) {
                                for (int iX = 0; iX < 16; iX++)
                                    for (int iZ = 0; iZ < 16; iZ++)
                                        pMinimap.setBlock(coord.getTrueX() + iX - self.getX(), coord.getTrueZ() + iZ - self.getZ(), topLayer[iX][iZ]);
                            }
                        }
                    }
                }
                pMinimap.repaint();

                // Position
                lblPos.setText(self != null ? "X:" + self.getX() + " Y:" + self.getY() + " Z:" + self.getZ() : "Position");

                // Chatmessage receive
                while (gData.hasReceivedMessagesWaiting()) {
                    synchronized (gData.receivedChatMessages) {
                        addTextToChatbox(gData.getNextAwaitingMessage());
                    }
                }

                // Sleep
                try {
                    Thread.sleep(1000 / FPS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            pMinimap.repaint();
            lblTicks.setText("0 ticks/s");
            lblEntities.setText("0 Entities");
            lblMemory.setText("Memory");
            lblTime.setText("Time");
            lblPing.setText("Ping");

            lblLife.setText("Life");
            lblHunger.setText("Hunger");
            lblExp.setText("Experience");
            lblPos.setText("Position");

            // Static UI
            lblConnectStatus.setText(" Not connected ");
            txtChatbox.setText("");
            btnDisconnect.setEnabled(false);
            btnSend.setEnabled(false);
            txtChatSend.setEnabled(false);
        }

        public void stopNow() {
            run = false;
        }
    }
}
