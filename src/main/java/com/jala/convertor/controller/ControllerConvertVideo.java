/*
 * @(#) ControllerConvertVideo.java Copyright (c) 2019 Jala Foundation.
 * 2643 Av Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */

package com.jala.convertor.controller;

import com.jala.convertor.models.ConvertMusic;
import com.jala.convertor.models.ConvertVideo;
import com.jala.convertor.models.CriteriaConverterAudio;
import com.jala.convertor.models.CriteriaConverterVideo;
import com.jala.utils.Logs;
import com.jala.view.JPanelConverterVideo;
import org.apache.log4j.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerConvertVideo implements ActionListener {
    /** It creates to follow up the instruction of the class*/
    private Logger log = Logs.getInstance().getLog();

    /** ViewConvert: create a pointer of JPanelSearchGral the object will provide other class.*/
    private JPanelConverterVideo viewConvert;

    private CriteriaConverterVideo criteriaConverterVideo;
    private CriteriaConverterAudio criteriaConverterAudio;

    /**
     * This constructor
     * @param viewConvert
     */
    public ControllerConvertVideo(JPanelConverterVideo viewConvert) {
        log.info("Initialize the Control of Search General");
        this.viewConvert = viewConvert;
        log.info("Initialize the adding of listener for the buttons in Convert General");
        viewConvert.getBtnConvertVideo().addActionListener(this);
        log.info("Finish the actionListener");
    }

    /**
     * It is override the method of ActionListener and the objective is listen if button is pressed
     * @param event this activates when a button is  pressed
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        log.info("Action Detected");
        if (event.getSource() == viewConvert.getBtnConvertVideo()) {
            if (viewConvert.isVideo()) {
                log.info("BtnConvert from Search General was pressed");
                sendPathToConvertVideo();
            }else{
                log.info("BtnConvert from Search General was pressed");
                sendPathToConvertAudio();
            }
        }
    }

    /**
     * This method sends to ConvertImage the criteria that was wrote by user, and that method convert
     * the image
     */
    private void sendPathToConvertVideo() {
        log.info("Preparing to send criteria converter to ConvertImage");
        ConvertVideo convertorVideo = new ConvertVideo();
        saveCriteriaVideo();
        convertorVideo.convert(criteriaConverterVideo);
    }

    private void sendPathToConvertAudio() {
        log.info("Preparing to send criteria converter to ConvertImage");
        ConvertMusic convertorAudio = new ConvertMusic();
        saveCriteriaAudio();
        convertorAudio.convert(criteriaConverterAudio);
    }

    /**
     * Save the information that the Ui provides into CriteriaConverter to send Converter
     */
    private void saveCriteriaVideo() {
        String pathOrigin = viewConvert.getTxtPathFileOrigin();
        String pathDestiny = viewConvert.getTxtFolderDestiny();
        String fileName = viewConvert.getTxtFileName();
        String extension = viewConvert.getComboExtension();
        String resolution = viewConvert.getComboVideoResolution();
        double frameRate = viewConvert.getComboFrameRate();
        double bitRate = viewConvert.getComboBitRate();
        int sampleRate = viewConvert.getComboSampleRate();
        boolean isAdvanced = viewConvert.isAdvanced();
        String audioChannel = viewConvert.isStereo()? "Stereo" :"Mono";

        criteriaConverterVideo = new CriteriaConverterVideo(pathOrigin, pathDestiny + "\\", "." + extension);
        criteriaConverterVideo.setNewFileName(fileName);
        criteriaConverterVideo.setVideoResolution(resolution);
        criteriaConverterVideo.setFrameRate(frameRate);
        criteriaConverterVideo.setBitRate((long)bitRate);
        criteriaConverterVideo.setSampleRate(sampleRate);
        criteriaConverterVideo.setIsAdvanced(isAdvanced);
        criteriaConverterVideo.setAudioChannel(audioChannel);

    }

    /**
     * Save the information that the Ui provides into CriteriaConverter to send Converter
     */
    private void saveCriteriaAudio() {
        String pathOrigin = viewConvert.getTxtPathFileOrigin();
        String pathDestiny = viewConvert.getTxtFolderDestiny();
        String fileName = viewConvert.getTxtFileName();
        String extension = viewConvert.getComboExtension();
        double bitRate = viewConvert.getComboBitRate();
        int sampleRate = viewConvert.getComboSampleRate();
        String audioChannel = viewConvert.isStereo()? "Stereo" :"Mono";

        criteriaConverterAudio = new CriteriaConverterAudio(pathOrigin, pathDestiny + "\\", "." + extension);
        criteriaConverterAudio.setNewFileName(fileName);
        criteriaConverterAudio.setBitRate((long)bitRate);
        criteriaConverterAudio.setSampleRate(sampleRate);
        criteriaConverterAudio.setAudioChannel(audioChannel);
    }
}