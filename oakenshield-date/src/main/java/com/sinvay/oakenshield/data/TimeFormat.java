package com.sinvay.oakenshield.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Time kits
 *
 * @author pizzalord
 * @since 1.0
 */
public enum TimeFormat {
    // ************************** YDM **************************
    /**
     * 格式:年月日紧凑排列
     */
    FORMAT_YMD_NUMBERS_ONLY(new SimpleDateFormat("yyyyMMdd")),

    /**
     * 格式:年月日以横杠("-")分隔)),精确到:年月日
     */
    FORMAT_YMD_LINE(new SimpleDateFormat("yyyy-MM-dd")),

    /**
     * 格式:年月日以斜杠("/")分隔)),精确到:年月日
     */
    FORMAT_YMD_SLASH(new SimpleDateFormat("yyyy/MM/dd")),

    /**
     * 英语习惯
     */
    FORMAT_YMD_ENGLISH(new SimpleDateFormat("dd/MM/yyyy")),

    /**
     * 中文习惯
     */
    FORMAT_YMD_CHINESE(new SimpleDateFormat("yyyy年MM月dd日")),

    // ************************** YMD + HM **************************
    /**
     * 格式:年月日紧凑排列
     */
    FORMAT_YMD_HM_NUMBERS_ONLY(new SimpleDateFormat("yyyyMMddHHmm")),

    /**
     * 格式:年月日以横杠("-")分隔)),精确到:年月日时分
     */
    FORMAT_YMD_HM_LINE(new SimpleDateFormat("yyyy-MM-dd HH:mm")),

    /**
     * 格式:年月日以斜杠("/")分隔)),精确到:年月日时分
     */
    FORMAT_YMD_HM_SLASH(new SimpleDateFormat("yyyy/MM/dd HH:mm")),

    // ************************** YMD + HMS **************************

    /**
     * 格式:年月日时分秒紧凑排列
     */
    FORMAT_YMD_HMS_NUMBERS_ONLY(new SimpleDateFormat("yyyyMMddHHmmss")),

    /**
     * 格式:年月日以横杠("-")分隔)),精确到:年月日时分秒
     */
    FORMAT_YMD_HMS_LINE(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")),

    /**
     * 格式:年月日以斜杠("/")分隔)),精确到:年月日时分秒
     */
    FORMAT_YMD_HMS_SLASH(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")),

    // ************************** YMD + HMS + MS **************************

    /**
     * 格式:年月日时分秒紧凑排列
     */
    FORMAT_YMD_HMS_MS_NUMBERS_ONLY(new SimpleDateFormat("yyyyMMddHHmmssSSS")),

    /**
     * 格式:年月日以横杠("-")分隔)),精确到:年月日时分秒毫秒
     */
    FORMAT_YMD_HMS_MS_LINE(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")),

    /**
     * 格式:年月日以斜杠("/")分隔)),精确到:年月日时分秒毫秒
     */
    FORMAT_YMD_HMS_MS_SLASH(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS")),

    // ************************** HMS **************************

    /**
     * 格式:时分秒
     */
    FORMAT_CLOCK(new SimpleDateFormat("HH:mm:ss"));

    private SimpleDateFormat simpleDateFormat;

    TimeFormat(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
    }

    public String format(Date date) {
        return this.simpleDateFormat.format(date);
    }

    public Date parse(String dateStr) throws ParseException {
        return this.simpleDateFormat.parse(dateStr);
    }
}
