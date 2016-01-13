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
    private static final String TAG = "BluepeggCreamStyle";

    private SharedTask mSharedTask = null;
    private NetworkManager mNetworkManager;
    private ResponseManager mResponseManager;

    private enum TaskType implements ITaskType {
        USER_REGISTRATION(1),
        MANAGER_REGISTRATION(2),
        LOGIN_ACCOUNT(100),
        GET_SIDO_ADDR(101);

        private final int value;

        TaskType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    ;

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
        DevLog.defaultLogging("handleTask...");

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
                    DevLog.defaultLogging("loginAccount...");

                    response = loginAccount((List<Object>) tp.getParameterValue());
                    break;
                }


                case GET_SIDO_ADDR: {
                    response = getSiDoAddr();
                    break;
                }


                case USER_REGISTRATION: {
                    DevLog.defaultLogging("USER_REGISTRATION...");
                    response = regUser((List<Object>) tp.getParameterValue());
                    break;
                }


                case MANAGER_REGISTRATION: {
                    DevLog.defaultLogging("MANAGER_REGISTRATION...");
                    response = regManager((List<Object>) tp.getParameterValue());
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
        DevLog.defaultLogging("loginAccount...");

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
        DevLog.defaultLogging("loginAccount...mResponseManager");

        return mResponseManager.analysePostResponse("api/login/username/", params);
    }

    ////////////////
    @Override
    public void getSiDoAddr(IResponseEvent<Object> pResponseEvent) {
        TaskParam task = new TaskParam(null, pResponseEvent);
        mSharedTask.addTask(this, TaskType.GET_SIDO_ADDR, task);

    }

    private Object getSiDoAddr() throws UnsupportedEncodingException {
        //List<NameValuePair> params = new ArrayList<NameValuePair>();
        //params.add(new BasicNameValuePair("type", type));
        //params.add(new BasicNameValuePair("data", data));

        return mResponseManager.analyseGetResponse("api/login/find_sido_addr/");
        //return mResponseManager.analyseGetResponse("api/login/find_addr");
    }
//////////////


    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Outer API
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
      서버에 API전송
      사용자 등록 : handleTask에서 호출
     */
    private Object regUser(List<Object> pParams) throws UnsupportedEncodingException {
        DevLog.defaultLogging("######### regUser...");

        if (pParams == null || pParams.size() != 18)
            return null;

        String email = (String) pParams.get(0);
        String password = (String) pParams.get(1);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("name",  (String) pParams.get(0)));
        params.add(new BasicNameValuePair("sex",  (String) pParams.get(1)));
        params.add(new BasicNameValuePair("email",  (String) pParams.get(2)));
        params.add(new BasicNameValuePair("image1",  (String) pParams.get(3)));
        params.add(new BasicNameValuePair("image2",  (String) pParams.get(4)));
        params.add(new BasicNameValuePair("image3",  (String) pParams.get(5)));
        params.add(new BasicNameValuePair("image4",  (String) pParams.get(6)));
        params.add(new BasicNameValuePair("image5",  (String) pParams.get(7)));
        params.add(new BasicNameValuePair("birthdate",  (String) pParams.get(8)));
        params.add(new BasicNameValuePair("height",  (String) pParams.get(9)));
        params.add(new BasicNameValuePair("weight",  (String) pParams.get(10)));
        params.add(new BasicNameValuePair("phone",  (String) pParams.get(11)));
        params.add(new BasicNameValuePair("address",  (String) pParams.get(12)));
        params.add(new BasicNameValuePair("education",  (String) pParams.get(13)));
        params.add(new BasicNameValuePair("education_state",  (String) pParams.get(14)));
        params.add(new BasicNameValuePair("etc",  (String) pParams.get(15)));
        params.add(new BasicNameValuePair("career",  (String) pParams.get(16)));
        params.add(new BasicNameValuePair("prize_giving_details",  (String) pParams.get(17)));
        DevLog.defaultLogging("loginAccount...mResponseManager");

       /// return mResponseManager.analysePostResponse("api/register/user/", params);
        return mResponseManager.imageUploadPostResponse("api/upload/image/", params);



    }

    /*
     서버에 API전송
     사용자 등록 : handleTask에서 호출
    */
    private Object regManager(List<Object> pParams) throws UnsupportedEncodingException {
        if (pParams == null || pParams.size() != 18)
            return null;

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("name",  (String) pParams.get(0)));
        params.add(new BasicNameValuePair("sex",  (String) pParams.get(1)));
        params.add(new BasicNameValuePair("email",  (String) pParams.get(2)));
        params.add(new BasicNameValuePair("image1",  (String) pParams.get(3)));
        params.add(new BasicNameValuePair("image2",  (String) pParams.get(4)));
        params.add(new BasicNameValuePair("image3",  (String) pParams.get(5)));
        params.add(new BasicNameValuePair("image4",  (String) pParams.get(6)));
        params.add(new BasicNameValuePair("image5",  (String) pParams.get(7)));
        params.add(new BasicNameValuePair("birthdate",  (String) pParams.get(8)));
        params.add(new BasicNameValuePair("height",  (String) pParams.get(9)));
        params.add(new BasicNameValuePair("weight",  (String) pParams.get(10)));
        params.add(new BasicNameValuePair("phone",  (String) pParams.get(11)));
        params.add(new BasicNameValuePair("address",  (String) pParams.get(12)));
        params.add(new BasicNameValuePair("education",  (String) pParams.get(13)));
        params.add(new BasicNameValuePair("education_state",  (String) pParams.get(14)));
        params.add(new BasicNameValuePair("etc",  (String) pParams.get(15)));
        params.add(new BasicNameValuePair("career",  (String) pParams.get(16)));
        params.add(new BasicNameValuePair("prize_giving_details",  (String) pParams.get(17)));

        DevLog.defaultLogging("loginAccount...mResponseManager");

        return mResponseManager.analysePostResponse("api/register/manager/", params);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
        add Task (IConnector Func)
     */
    @Override
    public void addTaskRegUser(String pName,
                               String pSex,
                               String pEmail,
                               String pImage1,
                               String pImage2,
                               String pImage3,
                               String pImage4,
                               String pImage5,
                               String pBirthDay,
                               String pHeight,
                               String pWeight,
                               String pPhone,
                               String pAddress,
                               String pEducation,
                               String pEducationState,
                               String pEtc,
                               String pCareer,
                               String pPrizeGivingDetails,
                               IResponseEvent<Object> pResponseEvent){
        DevLog.defaultLogging("######### addTaskRegUser...");

        TaskParam task = new TaskParam(Arrays.asList(pName, pSex,pEmail,pImage1,
                                    pImage2,pImage3,pImage4,pImage5,pBirthDay,pHeight,
                                    pWeight,pPhone,pAddress, pEducation,pEducationState,
                                    pEtc,pCareer,pPrizeGivingDetails),pResponseEvent);

        mSharedTask.addTask(this, TaskType.USER_REGISTRATION, task);

    }

    @Override
    public void addTaskRegManager(String pEmail, String pPassword, IResponseEvent<Object> pResponseEvent) {

    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////

}
