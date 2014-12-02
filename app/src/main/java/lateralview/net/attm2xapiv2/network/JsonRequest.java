package lateralview.net.attm2xapiv2.network;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import lateralview.net.attm2xapiv2.listeners.ResponseListener;
import lateralview.net.attm2xapiv2.sharedPreferences.APISharedPreferences;
import lateralview.net.attm2xapiv2.utils.ArrayUtils;

/**
 * Created by Joaquin on 1/12/14.
 */
public class JsonRequest {

    public static void makePostRequest(final Context context,
                                       String url,
                                       JSONObject params,
                                       final ResponseListener listener,
                                       final int requestCode
                                       ) {

        final ApiV2Response apiResponse = new ApiV2Response();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,
                url,
                params,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject o) {
                apiResponse.set_json(o);
                apiResponse.set_clientError(Boolean.FALSE);
                apiResponse.set_error(Boolean.FALSE);
                apiResponse.set_serverError(Boolean.FALSE);
                apiResponse.set_success(Boolean.TRUE);
                listener.onRequestCompleted(apiResponse,requestCode);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                apiResponse.set_json(null);
                apiResponse.set_error(Boolean.TRUE);
                if(error.networkResponse.statusCode<500){
                    apiResponse.set_clientError(Boolean.TRUE);
                    apiResponse.set_serverError(Boolean.FALSE);
                }else{
                    apiResponse.set_clientError(Boolean.FALSE);
                    apiResponse.set_serverError(Boolean.TRUE);
                }
                apiResponse.set_success(Boolean.FALSE);
                listener.onRequestError(apiResponse,requestCode);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("X-M2X-KEY", APISharedPreferences.getApiKey(context));
                return params;
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    apiResponse.set_raw(new String(response.data,"utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }finally{
                    apiResponse.set_status(String.valueOf(response.statusCode));
                    apiResponse.set_headers(response.headers.toString());
                }
                return super.parseNetworkResponse(response);
            }
        };
        //It's better if the queue is obtained with an app context to keep it alive while the app is in foreground.
        VolleyResourcesSingleton.getInstance(context.getApplicationContext()).addToRequestQueue(jsonObjReq);
    }

    public static void makeGetRequest(final Context context,
                                      String url,
                                      HashMap<String,String> params,
                                      final ResponseListener listener,
                                      final int requestCode) {

        final ApiV2Response apiResponse = new ApiV2Response();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.GET,
                url.concat("?".concat(ArrayUtils.mapToQueryString(params))),
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject o) {
                        apiResponse.set_json(o);
                        apiResponse.set_clientError(Boolean.FALSE);
                        apiResponse.set_error(Boolean.FALSE);
                        apiResponse.set_serverError(Boolean.FALSE);
                        apiResponse.set_success(Boolean.TRUE);
                        listener.onRequestCompleted(apiResponse,requestCode);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                apiResponse.set_json(null);
                apiResponse.set_error(Boolean.TRUE);
                if(error.networkResponse.statusCode<500){
                    apiResponse.set_clientError(Boolean.TRUE);
                    apiResponse.set_serverError(Boolean.FALSE);
                }else{
                    apiResponse.set_clientError(Boolean.FALSE);
                    apiResponse.set_serverError(Boolean.TRUE);
                }
                apiResponse.set_success(Boolean.FALSE);
                listener.onRequestError(apiResponse,requestCode);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("X-M2X-KEY", APISharedPreferences.getApiKey(context));
                return params;
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    apiResponse.set_raw(new String(response.data,"utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }finally{
                    apiResponse.set_status(String.valueOf(response.statusCode));
                    apiResponse.set_headers(response.headers.toString());
                }
                return super.parseNetworkResponse(response);
            }
        };
        //It's better if the queue is obtained with an app context to keep it alive while the app is in foreground.
        VolleyResourcesSingleton.getInstance(context.getApplicationContext()).addToRequestQueue(jsonObjReq);
    }

    public static void makePutRequest(final Context context,
                                       String url,
                                       JSONObject params,
                                       final ResponseListener listener,
                                       final int requestCode) {

        final ApiV2Response apiResponse = new ApiV2Response();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.PUT,
                url,
                params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject o) {
                        apiResponse.set_json(o);
                        apiResponse.set_clientError(Boolean.FALSE);
                        apiResponse.set_error(Boolean.FALSE);
                        apiResponse.set_serverError(Boolean.FALSE);
                        apiResponse.set_success(Boolean.TRUE);
                        listener.onRequestCompleted(apiResponse,requestCode);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                apiResponse.set_json(null);
                apiResponse.set_error(Boolean.TRUE);
                if(error.networkResponse.statusCode<500){
                    apiResponse.set_clientError(Boolean.TRUE);
                    apiResponse.set_serverError(Boolean.FALSE);
                }else{
                    apiResponse.set_clientError(Boolean.FALSE);
                    apiResponse.set_serverError(Boolean.TRUE);
                }
                apiResponse.set_success(Boolean.FALSE);
                listener.onRequestError(apiResponse,requestCode);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("X-M2X-KEY", APISharedPreferences.getApiKey(context));
                return params;
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    apiResponse.set_raw(new String(response.data,"utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }finally{
                    apiResponse.set_status(String.valueOf(response.statusCode));
                    apiResponse.set_headers(response.headers.toString());
                }
                return super.parseNetworkResponse(response);
            }
        };
        //It's better if the queue is obtained with an app context to keep it alive while the app is in foreground.
        VolleyResourcesSingleton.getInstance(context.getApplicationContext()).addToRequestQueue(jsonObjReq);
    }

    public static void makeDeleteRequest(final Context context,
                                      String url,
                                      JSONObject params,
                                      final ResponseListener listener,
                                      final int requestCode) {

        final ApiV2Response apiResponse = new ApiV2Response();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.DELETE,
                url,
                params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject o) {
                        apiResponse.set_json(o);
                        apiResponse.set_clientError(Boolean.FALSE);
                        apiResponse.set_error(Boolean.FALSE);
                        apiResponse.set_serverError(Boolean.FALSE);
                        apiResponse.set_success(Boolean.TRUE);
                        listener.onRequestCompleted(apiResponse,requestCode);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                apiResponse.set_json(null);
                apiResponse.set_error(Boolean.TRUE);
                if(error.networkResponse.statusCode<500){
                    apiResponse.set_clientError(Boolean.TRUE);
                    apiResponse.set_serverError(Boolean.FALSE);
                }else{
                    apiResponse.set_clientError(Boolean.FALSE);
                    apiResponse.set_serverError(Boolean.TRUE);
                }
                apiResponse.set_success(Boolean.FALSE);
                listener.onRequestError(apiResponse,requestCode);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("X-M2X-KEY", APISharedPreferences.getApiKey(context));
                return params;
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    apiResponse.set_raw(new String(response.data,"utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }finally{
                    apiResponse.set_status(String.valueOf(response.statusCode));
                    apiResponse.set_headers(response.headers.toString());
                }
                return super.parseNetworkResponse(response);
            }
        };
        //It's better if the queue is obtained with an app context to keep it alive while the app is in foreground.
        VolleyResourcesSingleton.getInstance(context.getApplicationContext()).addToRequestQueue(jsonObjReq);
    }


}