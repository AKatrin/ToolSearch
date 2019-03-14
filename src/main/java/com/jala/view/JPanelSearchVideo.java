/*
 * @(#) JPanelConverter.java Copyright (c) 2019 Jala Foundation.
 * 2643 Av Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */

package com.jala.view;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * JPanelSearchVideo class
 *
 * @author Raúl Choque
 * @version 0.0.1
 */
public class JPanelSearchVideo extends JPanel implements ActionListener {
    private JLabel lbFramRat, lbVideoCod,
                     lbAudioCod, lbAspectRadio, lbDimention, lbExtension;
    private JButton btSerach;
    private JComboBox cbFramRat, cbVideoCod, cbAudioCod, cbAspectRadio,
                        cbDimention, cbExtension;

    /**
     * The constructor method of the ViewConsole class to build an instance
     */
    public JPanelSearchVideo() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        lbFramRat = new JLabel("Frame Rate: ");
        constraintComponent(lbFramRat, 0, 2, GridBagConstraints.EAST);
        lbVideoCod = new JLabel("Video Codec: ");
        constraintComponent(lbVideoCod, 0, 3, GridBagConstraints.EAST);
        lbAudioCod = new JLabel("Audio Codec: ");
        constraintComponent(lbAudioCod, 0, 4, GridBagConstraints.EAST);
        cbFramRat = new JComboBox();
        String[] myFrameRat = {"SELECT", "24", "24.976", "25", "27", "29.7", "30", "60"};
        addItemsComboBox(myFrameRat, cbFramRat);
        constraintComponent(cbFramRat, 1, 2, GridBagConstraints.WEST);
        cbVideoCod = new JComboBox();
        String[] myVideoCod = {"SELECT", "H.264/MPEG-4 AVC", "H.265/MPEG-H HEVC", "H.263/MPEG-4 parte2 codecs",
                    "H.262/MPEG-2 codecs", "MPEG-1", "WMV", "MJPEG", "DV and HDV", "MPEG-2", "Microsoft VC-1"};
        addItemsComboBox(myVideoCod, cbVideoCod);
        constraintComponent(cbVideoCod, 1, 3, GridBagConstraints.WEST);
        cbAudioCod = new JComboBox();
        String[] myAudioCod = {"SELECT", "WAV", "FLAC", "MP3", "WMA", "MPEG-1", "WMV", "ALAC", "Ogg Vorbis",
                                "AAC", "AIFF", "DSD"};
        addItemsComboBox(myAudioCod, cbAudioCod);
        constraintComponent(cbAudioCod, 1, 4, GridBagConstraints.WEST);
        lbAspectRadio = new JLabel("Aspect Ratio: ");
        constraintComponent(lbAspectRadio, 2, 2, GridBagConstraints.EAST);
        lbDimention = new JLabel("Dimension: ");
        constraintComponent(lbDimention, 2, 3, GridBagConstraints.EAST);
        cbAspectRadio = new JComboBox();
        lbExtension = new JLabel("Extension: ");
        constraintComponent(lbExtension, 2, 4, GridBagConstraints.EAST);
        cbAspectRadio = new JComboBox();
        String[] myAspectRatio = {"SELECT", "3:2", "4:3", "5:4", "16:9", "16:10", "1.35:1", "1.85:1"};
        addItemsComboBox(myAspectRatio, cbAspectRadio);
        constraintComponent(cbAspectRadio, 3, 2, GridBagConstraints.WEST);
        cbDimention = new JComboBox();
        String[] myDimention = {"SELECT", "400/300", "1280/750", "1000/780"};
        addItemsComboBox(myDimention, cbDimention);
        constraintComponent(cbDimention, 3, 3, GridBagConstraints.WEST);
        cbExtension = new JComboBox();
        String[] myExtension = {"SELECT", "FLV", "AVI", "MOV", "MP4", "MPG", "WMV"};
        addItemsComboBox(myExtension, cbExtension);
        constraintComponent(cbExtension, 3, 4, GridBagConstraints.WEST);
        btSerach = new JButton("Search");
        btSerach.addActionListener(this);
        constraintComponent(btSerach, 3, 5, GridBagConstraints.EAST);
    }

    /**
     * This method add items in combo box
     * @param myItems are data for add at cbFrameRat
     * @param cbFramRat is a data where add items
     */
    private void addItemsComboBox(String[] myItems, JComboBox cbFramRat) {
        for (String item : myItems) {
            cbFramRat.addItem(item);
        }
    }

    /**
     * Section of getter methods
     */
    public JComboBox getCbFramRat() {
        return cbFramRat;
    }

    public JComboBox getCbVideoCod() {
        return cbVideoCod;
    }

    public JComboBox getCbAudioCod() {
        return cbAudioCod;
    }

    public JComboBox getCbAspectRadio() {
        return cbAspectRadio;
    }

    public JComboBox getCbDimention() {
        return cbDimention;
    }

    public JComboBox getCbExtension() {
        return cbExtension;
    }

    /**
     * The method constraintComponent add constraint for a component
     * @param component is data that receive a constraint
     * @param posX is data for position on row
     * @param posY is data for position on column
     * @param align is data for place the component.
     */
    private void constraintComponent(Component component, int posX, int posY, int align) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = posX;
        constraints.gridy = posY;
        constraints.insets = new Insets(2, 2, 2, 2);
        constraints.anchor = align;
        add(component, constraints);
    }

    /**
     * method that listen action on a button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("You are searching video files");
    }
}
