package com.lizijian.officeauto.utils;

import com.tencent.cloud.asr.offline.sdk.http.OasrRequesterSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OasrUtils {

    @Autowired
    public OasrUtils() {
        OasrRequesterSender oasrRequesterSender = new OasrRequesterSender();
    }
}
