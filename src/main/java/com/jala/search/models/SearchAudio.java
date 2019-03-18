/*
 * @(#) SearchAudio.java Copyright (c) 2019 Jala Foundation.
 * 2643 Av Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */

package com.jala.search.models;

import com.jala.utils.AssetFactory;
import com.jala.utils.Logs;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchAudio extends SearchCommon {
    private List<Asset> result;
    private CriteriaSearchMultimedia criteriaSearchAudio;
    private CriteriaSearch criteria;
    /** It creates to follow up the instruction of the class*/
    private Logger log = Logs.getInstance().getLog();

    private  String FFPROBE_PATH = SearchFile.class.getClassLoader()
            .getResource("ThirdParty/ffmpeg/bin/").getPath() + "ffprobe.exe";

    public SearchAudio(CriteriaSearch criteria) {
        super(criteria);
        this.criteria = criteria;
        criteriaSearchAudio = (CriteriaSearchMultimedia) criteria;
    }

    @Override
    public List<Asset> search() {
        result = new ArrayList<>();
        List<Asset> preview = super.search();
        for (int i = 0; i < preview.size(); i++) {
            FFprobe ffprobe;
            FFmpegProbeResult ffprobeResult;
            try {
                ffprobe = new FFprobe(FFPROBE_PATH);
                ffprobeResult = ffprobe.probe(preview.get(i).getPath());
                boolean resultSearchAudio = true;
                Asset asset;
                if (resultSearchAudio && !(ffprobeResult.getStreams().get(0).codec_long_name).contains(criteriaSearchAudio.getAudioCodec())) {
                    if (!criteriaSearchAudio.getAudioCodec().equals("")) {
                        resultSearchAudio = false;
                    }
                }
                if (!criteriaSearchAudio.getAudioSampleRate().equals("")) {
                    double sampleRate = Double.parseDouble(criteriaSearchAudio.getAudioSampleRate());
                    if (resultSearchAudio && !(sampleRate >= ffprobeResult.getStreams().get(0).sample_rate)) {
                        resultSearchAudio = false;
                    }
                }
                if (!criteriaSearchAudio.getDuration().equals("")) {
                    double durationCriteria = Double.parseDouble(criteriaSearchAudio.getDuration()) * 60;
                    if (resultSearchAudio && !(durationCriteria >= ffprobeResult.getStreams().get(0).duration)) {
                        resultSearchAudio = false;
                    }
                }
                if (resultSearchAudio && !(criteriaSearchAudio.getChannel().equals(ffprobeResult.getStreams().get(0).channel_layout))) {
                    if (!criteriaSearchAudio.getChannel().equals("")) {
                        resultSearchAudio = false;
                    }
                }
                if (resultSearchAudio) {
                    asset = AssetFactory.getAsset(preview.get(i),
                            ffprobeResult.getStreams().get(0).codec_long_name,
                            ffprobeResult.getStreams().get(0).channel_layout,
                            Integer.toString(ffprobeResult.getStreams().get(0).sample_rate),
                            String.valueOf(ffprobeResult.getStreams().get(0).duration));
                    result.add(asset);
                }

            } catch (IOException event) {
                log.error("The criteria values shouldn't be null...", event);
            } catch (NumberFormatException event) {
                log.error("Error in conversion in numbers...", event);
            }
        }
        return result;
    }
}
