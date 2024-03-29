package com.urise.webapp.util;

import com.urise.webapp.model.Organization;

/**
 * @author p.kondakov
 */
public class HtmlUtil {
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String formatDates(Organization.Position period) {
        return DateUtil.format(period.getStartDate()) + " - " + DateUtil.format(period.getEndDate());
    }

}
