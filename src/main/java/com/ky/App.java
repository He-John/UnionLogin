package com.ky;

import com.alibaba.fastjson.JSONObject;
import com.ky.entity.UserInfo;
import com.ky.param.OpenRequest;
import com.ky.param.OpenResponse;
import com.ky.vmk.JsonUtils;
import com.ky.vmk.SecurityUtils;
import com.ky.vmk.SignUtils;
import org.apache.commons.codec.binary.Base64;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Calendar;

/**
 * Hello world!
 */

public class App {


    public static final String KEY_ALGORTHM = "RSA";


    /**
     * 平台提供的公钥
     */
    public static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDQQnUS4x1SHDte4LgMF4MWgwk7\n" +
            "s5ua7lITqvo0mVbwlQGkHzyzTD6vdtNJB22D5w9eEhZLHWZdKEaISbWmZq7YxIn/\n" +
            "TYTvT5ttdMUR8zYuSbQ+9ueTXn0drKcP5p56/dC7b8y6IHL0/Y3rFS/YizDw7x/l\n" +
            "CjJeSuGdqXEt1SN9TwIDAQAB";

    /**
     * 平台提供的私钥
     */
    public static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANBCdRLjHVIcO17g\n" +
            "uAwXgxaDCTuzm5ruUhOq+jSZVvCVAaQfPLNMPq9200kHbYPnD14SFksdZl0oRohJ\n" +
            "taZmrtjEif9NhO9Pm210xRHzNi5JtD7255NefR2spw/mnnr90LtvzLogcvT9jesV\n" +
            "L9iLMPDvH+UKMl5K4Z2pcS3VI31PAgMBAAECgYEAxv15F97ieT54jCN0ODmPC8eQ\n" +
            "3vayy8rDqf9Iq1GI/L+jncorGbbUfpuvLqxmPA5fZKcrngIteP2uxzKG2sRqcwOL\n" +
            "T3ZU2qsfE0kJ0+gfeny2J7arTpFtf5WI1/89mfSMNmhNMWQ8uyp9JGWCXG7h0fC7\n" +
            "8FhUGlyhMQZar5GZ1sECQQDtfVTBzdc8ajfMYAtpTni4bUrjdc6xf9CDdBKkk6sU\n" +
            "v0xn7xX766WHEVXpjNnuyfjZS2fBfnNG32cRj908md4HAkEA4H3lS0CAOYxdkvOq\n" +
            "WeFJ0cTqcN7u5SkOjP9TPVeuTAqioy83p1c4Ry1kP703WXIJU6r7aRhxXZs78UO/\n" +
            "6CcUeQJAbHOXiQlfCQ/Ye1RA4c4cAtymHklmIlij9+PBv7ZPoiHZ2nysJWbvKEca\n" +
            "XJUd0JYbNd/hedMtAWhzwOlZtprtcQJBALrUS9Xa8lvyk5XPMEDpG9R2e15ASrVw\n" +
            "++kgPRhaNnaWi1Af5cNg9TdXcY0SXlQUceqt69pFO2PZ44MhDT+Z9pECQFNq5s3G\n" +
            "tuFDlBvUkD4mezyjFDtz15LuuT4b0zXXIOPyb+QQWKbfbXbwd4ovjPEDWojCEbnG\n" +
            "vxW2I+HZPilwxDY=";

    /**
     * 平台提供的商家编号
     */
    public static final String UA = "00001";

    /**
     * 撞库URL地址
     */
    public static final String CALL = "User.unionLogin";

    public static void main(String[] args) throws Exception {


        UserInfo userInfo = UserInfo.builder().userIdcard("userIdcard").userName("名字").userPhone("18305165").build();
        String contant = JsonUtils.toJsonString(userInfo);
        String timeString = String.valueOf(Calendar.getInstance().getTimeInMillis());

        SecurityUtils securityUtils = new SecurityUtils();
        PrivateKey privateKeyAs = securityUtils.getPrivateKey("MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCc6S3RcaPq2zSBkCOflkAIbPyXcwpZDSs0I7VY2l5QSkYmOhtutmqtmsepLt+xPK6WtGNZd+FgBXwR+cdx7rMEpk88FoiL3jFq8lmKSCY5GM8+pCBhxFRh1hjG4lJI4jYt2Qbzcrx70EuvQMxReCAA4z65IL9B1gc892UyrtrXwsy2gO6o0GO5u5P7sroZ0cOS2Ia+raph6pgLQ2zUVn1TwVuz+8UhrB/5L9GV/RI3mOpOo2Lq0bL7wblpN6ETQch35SoUT2OTE6AI2Wm6WihQNGFODWeFfzRNIHg0K36X10EwYwmHalFkUYW5YuDaZA7xmL42EAy7j/rWQkW/KkVzAgMBAAECggEAMazwwHoFtvim6s3DOWbL5t76KMhW4lJ09xDG/NZFPY4GH1wOC0LdCxERXNJmJypa64M4YL6/LzMNUHOPXNaA/lvYgeFwYTd61azcBBdLYpAkLGSxi8IoJMEvHufPvRtAo8stlC0i1t/e1iDhjnlFSIKo/NsNMb6clASOmTkaN2wJTkEFuKv6/MHtJFcXOv1VeQ8+nfIRgimCfPvOhFXtbUO7WfTEtpUSwWPQCCbYElQmIuvzZrKL+6sTfmCEZGHHoScQL6MZMxK5gVEPX9wAqvHPdGq++lIw3Rm9DXx8DoifU+KmTT2MnjcPG9dHwI1gW0DuMtgZA4ZOLm/h15UPYQKBgQDV//jxMMmeV+E55imPtDGYAkdJNkFFz1iHk5/CtaMzFuoiRPAsg7SEHBvyQKCegcwxU9R6kuOo1vOKR3YvBFIm2RYi+ejkB14pZTqm3aReph/5LyBiaghobCLypUQROh9rnv0HXq4hAbJ5JWmkc5ZoZJvKpKprMfmmilGByvuYLwKBgQC7tN+xWAYiNVL/1jYoP8OZytiDAkU2X8wYxwWW3rtcwWRFW01aMSsq3I1qBODdzp9C/ENaz/wF69zBATsrBtCvpmzlKdtoa9O13H5s+X3Dp/nsXMnvxes7oBtaunb6un7bSWpsXuDitGe08EklNQpYUQ5nptZ008ZhEfkm9wJR/QKBgEzFarJkNUBn+HXD+08BbvVAhABgxWPc8b6ZmCvK68RNSSuIHb1qdl0alHAA/V4/5Fgh4c0jWybcDjyiTGSroSksogNkMkdz2TTyiusIAoquADRt0qlbrq5/dL4lydiASHtcaxu0vLU8naFWaaLEkMXcqgM1tamFITG812a/wiijAoGAEAt7+4rMq2vndlr3MiWRMLo4G6q8A9PYfF22ypirZ1hYm90cFZOSXw2PQPq9xff9gRpTH5Ybst3gdtcGGb1xZTJSqJwVVy2wYSFB5UrGR006fcvIryg53VToQa8isHf0lAYIhM7vZjGJk9veRMHt6nKzSoTmXAImOPHyXDRNDHkCgYA80FyskPQpE6miXz7AowQWE9iqsV5vIm7aJvV1wpc51vd6q2EOAGTCfg9PSYtnXrjzeIPsybPlKLKBqmu9fEPzq/lruwa7q+EhnID2mRksnRpUcmKJz4Za+6uURGrL7r14I235pTvJs9dTIbSXsDBqCI46YUyKbk9V/5BEKL2xWQ==");
        PublicKey publicKeyAs = securityUtils.getPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnOkt0XGj6ts0gZAjn5ZACGz8l3MKWQ0rNCO1WNpeUEpGJjobbrZqrZrHqS7fsTyulrRjWXfhYAV8EfnHce6zBKZPPBaIi94xavJZikgmORjPPqQgYcRUYdYYxuJSSOI2LdkG83K8e9BLr0DMUXggAOM+uSC/QdYHPPdlMq7a18LMtoDuqNBjubuT+7K6GdHDktiGvq2qYeqYC0Ns1FZ9U8Fbs/vFIawf+S/Rlf0SN5jqTqNi6tGy+8G5aTehE0HId+UqFE9jkxOgCNlpulooUDRhTg1nhX80TSB4NCt+l9dBMGMJh2pRZFGFuWLg2mQO8Zi+NhAMu4/61kJFvypFcwIDAQAB");

        byte[] privateKey = privateKeyAs.getEncoded();
        String privateKeyBase64 = Base64.encodeBase64String(privateKey);
        System.out.println("私钥:" + privateKeyBase64);
        byte[] publicKey = publicKeyAs.getEncoded();
        String publicKeyBase64 = Base64.encodeBase64String(publicKey);
        System.out.println("公钥:" + publicKeyBase64);

        System.out.println("开始");
        System.out.println("客户端");
        System.out.println("客户端-使用私钥加密");
        System.out.println("原始数据:" + contant);
        String cEncryptContant = securityUtils.encryptText(contant, privateKeyAs);
        JSONObject jsonObject = new JSONObject();	
        jsonObject.put("data", cEncryptContant);
        System.out.println("客户端-构建加密报文");
        OpenRequest<JSONObject> openRequest = OpenRequest.<JSONObject>builder()
                .ua(UA)
                .call(CALL)
                .timestamp(timeString)
                .args(jsonObject)
                .build();
        String signString = SignUtils.sign(openRequest);
        openRequest.setSign(signString);

        System.out.println("客户端加密的请求报文:" + openRequest);
        System.out.println("JSON传输...");
        System.out.println("服务器");
        System.out.println("服务器-收到请求报文:" + openRequest);
        String requestBodyString = JsonUtils.toJsonString(openRequest);
        System.out.println("服务器-收到请求报文-toJSONString:" + requestBodyString);

        System.out.println("服务器-验签开始");
        if (!SignUtils.verify(openRequest)) {
            System.out.println("验签失败");
        } else {
            System.out.println("验签成功");
        }

        JSONObject serverArgs = openRequest.getArgs();
        cEncryptContant = serverArgs.getString("data");
        System.out.println("服务器-收到客户端的加密请求报文:" + cEncryptContant);
        String bDecryptContant = securityUtils.decryptText(cEncryptContant, publicKeyAs);
        System.out.println("服务器-使用公钥解密:" + bDecryptContant);
        System.out.println("服务器-业务处理开始" + bDecryptContant);


//        JSONObject responseJson = new JSONObject();
//        responseJson.put("status", 0);
//        responseJson.put("reason", "未命中");
//        String res = JsonUtils.toJsonString(responseJson);
//
//        System.out.println("服务器-使用私钥加密");
//        System.out.println("原始数据:" + res);
//        String bEncryptContant = securityUtils.encryptText(res, publicKeyAs);
//        System.out.println("服务器-使用公钥加密之后" + bEncryptContant);
//
//        JSONObject resDate = new JSONObject();
//        resDate.put("data", bEncryptContant);
//
//        OpenResponse openResponse = OpenResponse.builder()
//                .status(0)
//                .message("操作成功")
//                .response(resDate)
//                .build();
//        System.out.println("服务器-业务处理结束" + openResponse);
//        String responseJsonString = JsonUtils.toJsonString(responseJson);
//        System.out.println("服务器加密的响应报文-JSON:" + responseJsonString);
//        System.out.println("服务器加密的响应报文:" + openResponse);
//
//        System.out.println("JSON传输...");
//
//        System.out.println("客户端");
//
//
//        System.out.println("客户端-使用私钥解密");
//        System.out.println("客户端-收到响应报文:" + openResponse);
//
//        JSONObject resJson = JsonUtils.toObject(openResponse.getResponse(), JSONObject.class);
//
//        String cbEncryptContant = resJson.getString("data");
//        String cDecryptContant = securityUtils.decryptText(cbEncryptContant, privateKeyAs);
//        System.out.println("客户端-解密后的的服务器响应报文:" + cDecryptContant);
//        System.out.println("结束");
    }
}
