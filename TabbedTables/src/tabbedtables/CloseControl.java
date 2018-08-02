/*
 * Copyright (c) 2018 William H. Beebe, Jr.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tabbedtables;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * Creates a button to be added to the tab on a JTabbedPane allowing the end
 * user to close any arbitrary tab. This is a feature that's been available for
 * about a decade (at least) on just about any major application you care to
 * name, such as Firefox and Chrome.
 */
public class CloseControl extends JPanel {
    private JTabbedPane parentTabbedPane;
    private JSplitPane parentSplitPane;
    private CloseButton closeButton;
    private boolean repaintButton = false;

    /**
     * Constructed with the parent JTabbledPane reference.
     * @param pane parent JTabbedPane
     */
    public CloseControl(final JTabbedPane pane) {
        super(new FlowLayout(FlowLayout.LEFT,0,0));

        if (pane == null) throw new NullPointerException("JTabbedPane is null");

        parentTabbedPane = pane;

        init(pane);
    }

    private void init(final JTabbedPane pane) {
        setOpaque(false);
        
        JLabel label = new JLabel() {
            @Override
            public String getText() {
                int index = pane.indexOfTabComponent(CloseControl.this);
                if (index != -1) return pane.getTitleAt(index);
                return null;
            }
        };

        label.setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
        add(label);
        add(Box.createHorizontalStrut(10));
        closeButton = new CloseButton();
        closeButton.addActionListener( event -> {
                int index = parentTabbedPane.indexOfTabComponent(CloseControl.this);
                if (index != -1) parentTabbedPane.remove(index);
            });
        add(closeButton);
        setBorder(BorderFactory.createEmptyBorder(1,2,1,2));
    }


    /**
     * 
     * @param title
     * @param component 
     */
    public CloseControl(final String title, final JSplitPane component) {
        super(new BorderLayout());
        parentSplitPane = component;
        init(title, component);
    }

    private void init(final String title, final JSplitPane component) {
        setOpaque(false);
        JLabel label = new JLabel() {
            @Override
            public String getText() {
                return title;
            }
        };
        label.setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
        add(label, BorderLayout.LINE_START);
        closeButton = new CloseButton();
        closeButton.addActionListener(event -> {
                parentSplitPane.setRightComponent(null);
                parentSplitPane.setDividerSize(0);
            });
        add(closeButton, BorderLayout.LINE_END);
        setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        repaintButton = true;
        
    }

    public void setButtonEnabled(boolean enable) {
        closeButton.setEnabled(enable);
    }

    /*
    * This is where the key work gets done. Create a button and then
    * associate mouse listeners to indicate when the mouse moves into and
    * out of a given instance, as well as close the associated tab when
    * mouse clicked.
    */
    private class CloseButton extends JButton {
        public CloseButton() {
            init();
        }

        private void init() {
            setPreferredSize(new Dimension(18, 18));
            setToolTipText("Close");
            setUI(new BasicButtonUI());
            setContentAreaFilled(false);
            setOpaque(true);
            setFocusable(false);
            setBorder(BorderFactory.createEmptyBorder());
            setBorderPainted(false);
            setRolloverEnabled(true);
        }

        @Override
        public void updateUI() {}

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON));
            g2.setStroke(new BasicStroke(3, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));

            if (getModel().isRollover()) {
                g2.setColor(Color.decode("0xCF0000"));
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(Color.WHITE);
            }
            else {
                if (repaintButton == true) {
                    g2.setColor(UIManager.getColor("Button.background"));
                    g2.fillRect(0, 0, getWidth(), getHeight());
                }
                g2.setColor(Color.decode("0x707070"));
            }

            // Draw an 'X'. Firt stroke is from upper left to lower right.
            // Second stroke is from lower left to upper right.
            //
            int cornerOffset = 4;
            g2.drawLine(cornerOffset-1, cornerOffset-1, getWidth() - cornerOffset, getHeight() - cornerOffset);
            g2.drawLine(getWidth() - cornerOffset, cornerOffset-1, cornerOffset-1, getHeight() - cornerOffset);
            g2.dispose();
        }
    }
}
