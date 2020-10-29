package com.khanh.message;

public class StandardRequestMsg extends StandardMessage{

        private StandardResponseMsg response;

        public StandardRequestMsg() {
        }
        public <T extends StandardResponseMsg> T getResponse(Class<T> type) {
            return (T) response;
        }

        public StandardResponseMsg getResponse() {
            if (response == null) {
                response = new StandardResponseMsg();
                response.setAgent(getAgent());
                response.setRequestId(getRequestId());
                response.setData(getData());
                response.setServiceId(getServiceId());
            }
            return response;
        }

        public void setResponse(StandardResponseMsg response) {
            this.response = response;
        }

        public StandardResponseMsg fail(int resultCode) {
            setStopFlag(resultCode);
            return response;

        }

        private void setStopFlag(int resultCode) {
            getResponse().setResultCode(resultCode);
            setStopFlag();
        }

        public <T extends StandardResponseMsg> T createResponse(Class<T> type) {

            try {
                response = type.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                return null;
            }
            response.setAgent(getAgent());
            response.setRequestId(getRequestId());
            response.setData(getData());
            response.setServiceId(getServiceId());
            setResponse(response);
            return (T) response;
        }

    }
