/**
 * @(#)ISearchable.java Copyright (c) 2019 Jala Foundation.
 * 2643 Av Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 * <p>
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */

package com.jala.search.models;

import java.io.File;
import java.util.List;

/**
 * ISearchable interface to search by criteria.
 * @version 0.0.1
 * @author Areliez Vargas
 */
public interface ISearchable {

    /**
     *
     * @param criteria to do the search.
     * @return a list of files.
     */
    List<File> search(CriteriaSearch criteria);
}
