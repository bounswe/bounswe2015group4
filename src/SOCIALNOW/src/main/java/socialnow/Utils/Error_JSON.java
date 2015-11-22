package socialnow.Utils;

/**
 * Created by mertcan on 22.11.2015.
 */
public class Error_JSON {

        String message;
        int code;

        @Override
        public String toString() {
            return "{" +
                    "message='" + message + '\'' +
                    ", code=" + code +
                    '}';
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
}
