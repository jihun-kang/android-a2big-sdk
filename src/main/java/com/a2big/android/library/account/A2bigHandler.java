package com.a2big.android.library.account;

/**
 * A2bigHandler interface has responsibility regarding connecting external system(web service)
 * for getting account information.
 *
 * @author jihun,kang
 *
 */
import com.a2big.android.library.core.ITaskHandler;
import com.a2big.android.library.core.ITaskType;
import com.a2big.android.library.core.SharedTask;
import com.a2big.android.library.init.A2BigApp;
import com.a2big.android.library.network.manager.NetworkManager;
import com.a2big.android.library.response.ResponseManager;
import com.a2big.android.library.utils.DevLog;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A2bigHandler implements ITaskHandler, IConnector {
    private static final String TAG = "a2big";

    private SharedTask mSharedTask = null;
    private NetworkManager mNetworkManager;
    private ResponseManager mResponseManager;

    private enum TaskType implements ITaskType {
        LOGIN_ACCOUNT(100),
        ;

        private final int value;

        TaskType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }


    static public class TaskParam {
        private List<? extends Object> mParameterValue = null;
        private IResponseEvent mResponse = null;

        public TaskParam(List<? extends Object> pParameterValue, IResponseEvent pResponse) {
            mParameterValue = pParameterValue;
            mResponse = pResponse;
        }

        public List<? extends Object> getParameterValue() {
            return mParameterValue;
        }

        public IResponseEvent getResponse() {
            return mResponse;
        }
    }

    public A2bigHandler(SharedTask pSharedTask) {
        if (pSharedTask == null) {
            DevLog.LoggingError(TAG, "SharedTask is null");
            throw new InvalidParameterException();
        }

        A2BigApp app = A2BigApp.getApplication();
        mSharedTask = pSharedTask;
        mNetworkManager = app.getNetworkManager();
        mResponseManager = app.getResponseManager();
    }

    @Override
    public void handleTask(ITaskType pTaskType, Object pParam) throws IllegalArgumentException {
        if (!(pTaskType instanceof TaskType)) {
            throw new IllegalArgumentException("pTaskType should be one of TaskTypes which is defined previous");
        }

        if (!(pParam instanceof TaskParam)) {
            throw new IllegalArgumentException("pParam should be TaskParam type");
        }

        TaskParam tp = (TaskParam) pParam;
        Object response = null;
        TaskType taskType = (TaskType) pTaskType;

        try {
            switch (taskType) {
                case LOGIN_ACCOUNT: {
                    response = loginAccount((List<Object>) tp.getParameterValue());
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            DevLog.LoggingError(TAG, "Cannot call task type: " + taskType.name());
        } finally {
            if (tp != null && tp.getResponse() != null) {
                tp.getResponse().onResponse(response);
            }
        }
    }

    @Override
    public void loginAccount(String pEmail, String pPassword, IResponseEvent<Object> pResponseEvent) {
        TaskParam task = new TaskParam(Arrays.asList(pEmail, pPassword), pResponseEvent);
        mSharedTask.addTask(this, TaskType.LOGIN_ACCOUNT, task);
    }

    private Object loginAccount(List<Object> pParams) throws UnsupportedEncodingException {
        if (pParams == null || pParams.size() != 2)
            return null;
        String email = (String) pParams.get(0);
        String password = (String) pParams.get(1);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", email));
        params.add(new BasicNameValuePair("password", password));
        return mResponseManager.analysePostResponse("api/login/username/", params);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////

}
