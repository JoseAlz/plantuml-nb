/* 
 * The MIT License
 *
 * Copyright 2013 Venkat Ram Akkineni.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.netbeans.modules.plantumlnb.ui;

import java.awt.geom.AffineTransform;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import org.netbeans.modules.plantumlnb.DataObjectAccess;
import org.netbeans.modules.plantumlnb.SVGImagePreviewPanel;
import org.netbeans.modules.plantumlnb.ui.actions.ExportAction;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;

/**
 *
 * @author venkat
 */
public class Toolbar {

    private DataObjectAccess dataObjectAccess;
    private AffineTransform currentAt = null;
    private PUMLJSVGCanvas canvas = null;
    private SVGImagePreviewPanel svgImagePreviewPanel = null;

    private static final Logger logger = Logger.getLogger(SVGImagePreviewPanel.class.getName());

    private Toolbar() {}

    public JToolBar createToolBar() {

        // Definition of toolbar.
        JToolBar toolBar = new JToolBar();
        toolBar.putClientProperty("JToolBar.isRollover", Boolean.TRUE); //NOI18N
        toolBar.setFloatable(false);
        toolBar.setName(NbBundle.getMessage(Toolbar.class, "ACSN_Toolbar"));

        toolBar.add(getExportButton());
        toolBar.add(getZoomInButton());
        toolBar.add(getZoomOutButton());
        toolBar.add(getResetButton());
        toolBar.add(getCWRotateButton());
        toolBar.add(getCCWRotateButton());
        toolBar.add(getOpenInBrowserButton());
        toolBar.add(getQuickHelpLabel());

        return toolBar;
    }

    public JToolBar createToolBar(DataObjectAccess doa) {
        this.dataObjectAccess = doa;
        return createToolBar();
    }

    public JLabel getQuickHelpLabel() {
        JLabel label = new JLabel(ImageUtilities.loadImageIcon("org/netbeans/modules/plantumlnb/help.png", true));
        label.setToolTipText("<html><body>"
                + "<h4>Shortcuts</h4>"
                + "<br>"
                + "&rarr; <strong>Zoom</strong>         ( Ctrl + Left Click )<br>"
                + "&rarr; <strong>Realize zoom</strong> ( Shift + Right Click  )<br>"
                + "&rarr; <strong>Pan</strong>          ( SHIFT + Left Click )<br>"
                + "&rarr; <strong>Rotate</strong>       ( CTRL + Right Click )<br>"
                + "&rarr; <strong>Reset</strong>        ( CTRL + SHIFT + Right Click )<br>"
                + "<br>"
                + "</body></html>"
        );
        return label;
    }

    public JButton getExportButton() {
        JButton button = new JButton();
        button.setIcon(ImageUtilities.loadImageIcon("org/netbeans/modules/plantumlnb/save.png", true));
        button.addActionListener(new ExportAction());

        return button;
    }

    private JButton getZoomInButton() {
        JButton button = new JButton();
        button.setIcon(ImageUtilities.loadImageIcon("org/netbeans/modules/plantumlnb/zoom-in.png", true));
        button.setToolTipText("Zoom in");
        button.addActionListener(svgImagePreviewPanel.getZoomInActionInstance());

        return button;
    }

    private JButton getZoomOutButton() {
        JButton button = new JButton();
        button.setIcon(ImageUtilities.loadImageIcon("org/netbeans/modules/plantumlnb/zoom-out.png", true));
        button.setToolTipText("Zoom Out");
        button.addActionListener(svgImagePreviewPanel.getZoomOutActionInstance());

        return button;
    }

    /**
     * CCW = Counter Clockwise
     * @return
     */
    private JButton getCCWRotateButton() {
        JButton button = new JButton();
        button.setIcon(ImageUtilities.loadImageIcon("org/netbeans/modules/plantumlnb/rotate_ccw.png", true));
        button.setToolTipText("Rotate Counter Clockwise");
        button.addActionListener(svgImagePreviewPanel.getCCWRotateActionInstance());

        return button;
    }

    /**
     * Clockwise
     *
     * @return
     */
    private JButton getCWRotateButton() {
        JButton button = new JButton();
        button.setIcon(ImageUtilities.loadImageIcon("org/netbeans/modules/plantumlnb/rotate_cw.png", true));
        button.setToolTipText("Rotate Clockwise");
        button.addActionListener(svgImagePreviewPanel.getCWRotateActionInstance());

        return button;
    }

    private JButton getResetButton() {
        JButton button = new JButton();
        button.setIcon(ImageUtilities.loadImageIcon("org/netbeans/modules/plantumlnb/zoom-fit.png", true));
        button.setToolTipText("Reset");

        button.addActionListener(svgImagePreviewPanel.getResetTransformAction());

        return button;
    }

    private JButton getOpenInBrowserButton() {
        JButton button = new JButton();
        button.setIcon(ImageUtilities.loadImageIcon("org/netbeans/modules/plantumlnb/browser_generic_16x.png", true));
        button.setToolTipText("Open in Browser");

        button.addActionListener(svgImagePreviewPanel.getOpenInBrowserAction());

        return button;
    }

    private static Toolbar instance;

    public static Toolbar instance() {
        if (instance == null) {
            instance = new Toolbar();
        }

        return instance;
    }

    public DataObjectAccess getDataObjectAccess() {
        return dataObjectAccess;
    }

    public void setDataObjectAccess(DataObjectAccess dataObjectAccess) {
        this.dataObjectAccess = dataObjectAccess;
    }

    public AffineTransform getCurrentAt() {
        return currentAt;
    }

    public void setCurrentAt(AffineTransform currentAt) {
        this.currentAt = currentAt;
    }

    public PUMLJSVGCanvas getCanvas() {
        return canvas;
    }

    public void setCanvas(PUMLJSVGCanvas canvas) {
        this.canvas = canvas;
    }

    public SVGImagePreviewPanel getSvgImagePreviewPanel() {
        return svgImagePreviewPanel;
    }

    public void setSvgImagePreviewPanel(SVGImagePreviewPanel svgImagePreviewPanel) {
        this.svgImagePreviewPanel = svgImagePreviewPanel;
    }
}
