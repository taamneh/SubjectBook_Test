package Models;

/**
 * Created by staamneh on 2/24/2015.
 */
public class SignalType {
    // the code of each singal in database;
    private static  final int PERSPIRATION_CODE = 1;
    private static  final int SIMULATION_CODE = 2;
    private static  final int EDA_CODE = 3;
    private static  final int HRV_CODE = 4;
    private static  final int EXPRESSION_CODE = 5;
    private static  final int MOTION_CODE = 6;

    private static  final int BREATHING_CODE = 7;
    private static  final int HEART_RATE_CODE = 8;
    private static  final int BELT_BREATHING_CODE = 9;
    private static  final int TEMPERATURE_CODE = 10;

    private static  final int N_PERSPIRATION_CODE = 11;
    private static  final int BAR_CHART_CODE =12;
    private static  final int EYE_TRACKING_CODE =13;

    private static  final int VIDEO_CODE =100;
    private static  final int INFO_CODE = 101;
    private static  final int ACTIVITY_CODE =102;
    private static  final int PSYCHOMETRIC_CODE = 103;
    private static  final int PERFORMANCE_CODE =104;
    private static  final int OTHER_CODE =105;



    // the extension for each signal
    private static final String EDA_EXTNESION = "q_eda";
    private static final String HRV_EXTNESION = "hrv";
    private static final String EXPRESSION_EXTNESION = "expression";
    private static final String MOTION_EXTNESION ="q_motion";
    private static final String PERSPIRATION_EXTNESION = "perspiration";
    private static final String N_PERSPIRATION_EXTNESION = "nperspiration";
    private static final String BREATHING_EXTNESION = "breathing";
    private static final String HEART_RATE_EXTNESION = "z_ecg";
    private static final String BELT_BREATHING_EXTNESION = "z_breathing";
    private static final String TEMPERATURE_EXTNESION = "q_temperature";
    private static final String SIMULATION_EXTNESION = "sim";
    private static final  String VIDEO_EXTNESION ="avi";
    private static final String INFO_EXTNESION = "info";
    private static final String ACTIVITY_EXTNESION ="activity";
    private static final String PSYCHOMETRIC_EXTNESION = "pm";
    private static final String PERFORMANCE_EXTNESION ="prf";
    private static final String OTHER_EXTNESION ="other";
    private static final String BAR_CHART_EXTNESION ="bar";
    private static final String EYE_TRACKING_EXTNESION = "eye";

    public static boolean isEda(String extension) {
        return extension.equalsIgnoreCase(EDA_EXTNESION);
    }
    public static boolean isMotion(String extension) {
        return extension.equalsIgnoreCase(MOTION_EXTNESION);
    }
    public static boolean isPerspiration(String extension) { return extension.equalsIgnoreCase(PERSPIRATION_EXTNESION);    }
    public static boolean isNPerspiration(String extension) { return extension.equalsIgnoreCase(N_PERSPIRATION_EXTNESION);    }
    public static boolean isBreathing(String extension) {
        return extension.equalsIgnoreCase(BREATHING_EXTNESION);
    }
    public static boolean isHeartRate(String extension) {
        return extension.equalsIgnoreCase(HEART_RATE_EXTNESION);
    }
    public static boolean isTemperature(String extension) {
        return extension.equalsIgnoreCase(TEMPERATURE_EXTNESION);
    }
    public static boolean isBeltBreathing(String extension) { return extension.equalsIgnoreCase(BELT_BREATHING_EXTNESION);  }
    public static boolean isEyeTracking (String extension) { return extension.equalsIgnoreCase(EYE_TRACKING_EXTNESION);  }

    public static boolean isSimulation(String extension) {
        return extension.equalsIgnoreCase(SIMULATION_EXTNESION);
    }
    public static boolean isVideo(String extension) {
        return extension.equalsIgnoreCase(VIDEO_EXTNESION);
    }
    public static boolean isInfo(String extension) {
        return (extension.equalsIgnoreCase(INFO_EXTNESION) || extension.equalsIgnoreCase("b"));
    }
    public static boolean isActivity(String extension) {
        return (extension.equalsIgnoreCase(ACTIVITY_EXTNESION) || extension.equalsIgnoreCase("stimuli"));
    }
    public static boolean isPsychometric(String extension) {
        return extension.equalsIgnoreCase(PSYCHOMETRIC_EXTNESION);
    }
    public static boolean isPerfromance(String extension) {
        return extension.equalsIgnoreCase(PERFORMANCE_EXTNESION);
    }
    public static boolean isHRV(String extension) {
        return extension.equalsIgnoreCase(HRV_EXTNESION);
    }
    public static boolean isExpression(String extension) {
        return extension.equalsIgnoreCase(EXPRESSION_EXTNESION);
    }
    public static boolean isOther(String extension) {
        return extension.equalsIgnoreCase(OTHER_EXTNESION);
    }
    public static boolean isBar(String extension) {
        return extension.equalsIgnoreCase(BAR_CHART_EXTNESION);
    }



    //////////////////////////////////////

    public static int getEDACode() {
        return EDA_CODE;
    }
    public static int getHRV() {
        return HRV_CODE;
    }
    public static int getExpression () {
        return EXPRESSION_CODE;
    }
    public static int getMotionCode() {
        return MOTION_CODE;
    }
    public static int getPerspirationCode() {
        return PERSPIRATION_CODE;
    }
    public static int getNPerspirationCode() {  return N_PERSPIRATION_CODE;    }
    public static int getBreathingCode() {
        return BREATHING_CODE;
    }
    public static int getHeartRateCode() {
        return HEART_RATE_CODE;
    }
    public static int getTemperatureCode() {
        return TEMPERATURE_CODE;
    }
    public static int getBeltBreathingCode() {
        return BELT_BREATHING_CODE;
    }
    public static int getSimulationCode() {
        return SIMULATION_CODE;
    }
    public static int getVideoCode() {
        return VIDEO_CODE;
    }
    public static int getInfoCode() {
        return INFO_CODE;
    }
    public static int getActivityCode() {
        return ACTIVITY_CODE;
    }
    public static int getPsychometricCode() {
        return PSYCHOMETRIC_CODE;
    }
    public static int getPerformanceCode() {
        return PERFORMANCE_CODE;
    }
    public static int getOtherCode() {
        return OTHER_CODE;
    }
    public static int getBarChatCode() {
        return BAR_CHART_CODE;
    }
    public static int getEyeTrackingCode() {return EYE_TRACKING_CODE;}
}
