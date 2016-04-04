package com.a2big.android.library.init;

import android.net.Uri;
import android.util.Log;

import org.acra.ACRA;
import org.acra.ACRAConstants;
import org.acra.ReportField;
import org.acra.collector.CrashReportData;
import org.acra.sender.HttpSender;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderException;
import org.acra.util.HttpRequest;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.acra.ACRA.LOG_TAG;


public class HttpPostSender implements ReportSender {

    private final Uri mFormUri;
    private final Map<ReportField, String> mMapping;

    /**
     * <p>
     * Create a new HttpPostSender instance.
     * </p>
     *
     * @param formUri
     *            The URL of your server-side crash report collection script.
     * @param mapping
     *            If null, POST parameters will be named with
     *            {@link ReportField} values converted to String with
     *            .toString(). If not null, POST parameters will be named with
     *            the result of mapping.get(ReportField.SOME_FIELD);
     */
    public HttpPostSender(String formUri, Map<ReportField, String> mapping) {
        mFormUri = Uri.parse(formUri);
        mMapping = mapping;
    }

    @Override
    public void send(CrashReportData report) throws ReportSenderException {

        try {
            final Map<String, String> finalReport = remap(report);
            final URL reportUrl = new URL(mFormUri.toString());
            Log.d(LOG_TAG, "Connect to " + reportUrl.toString());

            final String login = isNull(ACRA.getConfig().formUriBasicAuthLogin()) ? null : ACRA.getConfig().formUriBasicAuthLogin();
            final String password = isNull(ACRA.getConfig().formUriBasicAuthPassword()) ? null : ACRA.getConfig().formUriBasicAuthPassword();

            final HttpRequest request = new HttpRequest();
            request.setConnectionTimeOut(ACRA.getConfig().connectionTimeout());
            request.setSocketTimeOut(ACRA.getConfig().socketTimeout());
            request.setMaxNrRetries(ACRA.getConfig().maxNumberOfRequestRetries());
            request.setLogin(login);
            request.setPassword(password);
            request.send(reportUrl, HttpSender.Method.POST, finalReport.toString(), HttpSender.Type.JSON);

        } catch (IOException e) {
            throw new ReportSenderException("Error while sending report to Http Post Form.", e);
        }
    }

    private static boolean isNull(String aString) {
        return aString == null || ACRAConstants.NULL_VALUE.equals(aString);
    }

    private Map<String, String> remap(Map<ReportField, String> report) {

        ReportField[] fields = ACRA.getConfig().customReportContent();
        if(fields.length == 0) {
           /// fields = ACRA.DEFAULT_REPORT_FIELDS;
        }

        final Map<String, String> finalReport = new HashMap<String, String>(report.size());
        for (ReportField field : fields) {
            if (mMapping == null || mMapping.get(field) == null) {
                finalReport.put(field.toString(), report.get(field));
            } else {
                finalReport.put(mMapping.get(field), report.get(field));
            }
        }
        return finalReport;
    }
}
