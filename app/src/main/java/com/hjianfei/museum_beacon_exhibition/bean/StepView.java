package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;

/**
 * Created by HJianFei on 2016/11/23.
 */

public class StepView implements Serializable {

    /**
     * code : 200
     * stepView : {"beacon_id":"1","step_name":"[书画馆, 端砚馆, 广东历史馆, 瓷器馆, 青铜器馆, 自然馆]"}
     */

    private int code;
    private StepViewBean stepView;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public StepViewBean getStepView() {
        return stepView;
    }

    public void setStepView(StepViewBean stepView) {
        this.stepView = stepView;
    }

    public static class StepViewBean {
        /**
         * beacon_id : 1
         * step_name : [书画馆, 端砚馆, 广东历史馆, 瓷器馆, 青铜器馆, 自然馆]
         */

        private String beacon_id;
        private String step_name;

        public String getBeacon_id() {
            return beacon_id;
        }

        public void setBeacon_id(String beacon_id) {
            this.beacon_id = beacon_id;
        }

        public String getStep_name() {
            return step_name;
        }

        public void setStep_name(String step_name) {
            this.step_name = step_name;
        }

        @Override
        public String toString() {
            return "StepViewBean{" +
                    "beacon_id='" + beacon_id + '\'' +
                    ", step_name='" + step_name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "StepView{" +
                "code=" + code +
                ", stepView=" + stepView +
                '}';
    }
}
