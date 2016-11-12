package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IrSeekerSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.util.EnumMap;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Shaffer_904573 on 11/10/2016.
 */
public class BaseOpMode extends LinearOpMode {

    /** START CONFIGURATIONS **/

    public static final float BUTTON_THRESHOLD = 0.01f;
    public static final String BUTTON_TOGGLE_COLLECT =  "g2_a";
    public static final String BUTTON_TOGGLE_FIRE =     "g2_b";
    public static final String BUTTON_PUSH_LEFT =       "g2_lbump";
    public static final String BUTTON_PUSH_RIGHT =      "g2_rbump";
    public static final String BUTTON_PUSH_BOTH =       "g2_x";
    public static final String BUTTON_PUSH_RIGHT_INC =  "g2_rsticky";
    public static final String BUTTON_PUSH_LEFT_INC =   "g2_lsticky";
    public static final String BUTTON_STOP_ALL_MOTORS = "g1_b";
    public static final String BUTTON_DRIVE_LEFT =      "g1_lsticky";
    public static final String BUTTON_DRIVE_RIGHT =     "g1_rsticky";

    public static final String CONFIG_FL_DRIVE =    "fl_drive";
    public static final String CONFIG_FR_DRIVE =    "fr_drive";
    public static final String CONFIG_BL_DRIVE =    "bl_drive";
    public static final String CONFIG_BR_DRIVE =    "br_drive";
    public static final String CONFIG_COLLECT =     "collect";
    public static final String CONFIG_CANNON =      "cannon";
    public static final String CONFIG_COLOR =       "color";
    public static final String CONFIG_IR_SEEKER =   "ir_seeker";
    public static final String CONFIG_SERVO_CAP =   "capball";
    public static final String CONFIG_SERVO_PUSHL = "pushl";
    public static final String CONFIG_SERVO_PUSHR = "pushr";

    public static final DcMotor.RunMode MODE_DRIVE =            DcMotor.RunMode.RUN_USING_ENCODER;
    public static final DcMotor.RunMode MODE_AUX =              DcMotor.RunMode.RUN_WITHOUT_ENCODER;
    public static final IrSeekerSensor.Mode MODE_IR =           IrSeekerSensor.Mode.MODE_600HZ;
    public static final DcMotor.ZeroPowerBehavior MODE_ZERO =   DcMotor.ZeroPowerBehavior.FLOAT;
    public static final MotorMode MOTOR_MODE =                  MotorMode.PARALLEL;

    public static final double SERVO_PUSHL_INIT =   0.0;
    public static final double SERVO_PUSHL_MIN =    0.0;
    public static final double SERVO_PUSHL_MAX =    1.0;
    public static final double SERVO_PUSHR_INIT =   0.0;
    public static final double SERVO_PUSHR_MIN =    0.0;
    public static final double SERVO_PUSHR_MAX =    1.0;
    public static final double SERVO_CAP_INIT =     0.0;
    public static final double SERVO_CAP_MIN =      0.0;
    public static final double SERVO_CAP_MAX =      1.0;

    public static final boolean HAS_PUSH =  false;
    public static final boolean HAS_CAP =   false;
    public static final boolean HAS_COLOR = false;
    public static final boolean HAS_IR =    false;

    public static final float   GAMEPAD_ONE_DEADZONE = 1.0f;
    public static final float   GAMEPAD_TWO_DEADZONE = 1.0f;
    public static final boolean GAMEPAD1_INVERSE_STICKS =   false;
    public static final boolean GAMEPAD2_INVERSE_STICKS =   false;
    public static final boolean GAMEPAD1_INVERSE_TRIGGERS = false;
    public static final boolean GAMEPAD2_INVERSE_TRIGGERS = false;

    public static final boolean TEL_AUTO_CLEAR =        true;
    public static final int     TEL_MS_INTERVAL =       100;
    public static final String  TEL_ITEM_SEPARATOR =    "|";
    public static final String  TEL_CAPTION_SEPARATOR = ":";

    public static final double COLLECTION_SPEED =   1.0;
    public static final double CANNON_SPEED =       1.0;
    public static final double PUSHR_POS = 1.0;
    public static final double PUSHL_POS = 1.0;

    /** END CONFIGURATIONS **/

    public static DcMotor frontLeft;
    public static DcMotor frontRight;
    public static DcMotor backLeft;
    public static DcMotor backRight;

    public static DcMotor collection;
    public static DcMotor lowOrbitIonCannon;

    public static ColorSensor colorSensor;
    public static IrSeekerSensor irSeekerSensor;

    public static Servo pushButtonL;
    public static Servo pushButtonR;
    public static Servo capBall;

    public static HardwareMap hardwareMap;
    public static ElapsedTime runtime;

    enum Function {
        COLLECT, FIRE, PUSH_LEFT, PUSH_RIGHT, PUSH_BOTH, PUSH_RIGHT_INC,
        PUSH_LEFT_INC, STOP_ALL_MOTORS, DRIVE_LEFT, DRIVE_RIGHT
    }

    enum MotorMode {
        PARALLEL, SQUARE
    }

    private static final Map<Function, String> buttonMap = new EnumMap<Function, String>(Function.class) {{
        put(Function.COLLECT, BUTTON_TOGGLE_COLLECT);
        put(Function.FIRE, BUTTON_TOGGLE_FIRE);
        put(Function.PUSH_LEFT, BUTTON_PUSH_LEFT);
        put(Function.PUSH_RIGHT, BUTTON_PUSH_RIGHT);
        put(Function.PUSH_BOTH, BUTTON_PUSH_BOTH);
        put(Function.PUSH_RIGHT_INC, BUTTON_PUSH_RIGHT_INC);
        put(Function.PUSH_LEFT_INC, BUTTON_PUSH_LEFT_INC);
        put(Function.STOP_ALL_MOTORS, BUTTON_STOP_ALL_MOTORS);
        put(Function.DRIVE_LEFT, BUTTON_DRIVE_LEFT);
        put(Function.DRIVE_RIGHT, BUTTON_DRIVE_RIGHT);
    }};

    public boolean isActive(Function f) {
        switch(buttonMap.get(f)) {
            case "g1_a": return gamepad1.a;
            case "g1_b": return gamepad1.b;
            case "g1_x": return gamepad1.x;
            case "g1_y": return gamepad1.y;
            case "g2_a": return gamepad2.a;
            case "g2_b": return gamepad2.b;
            case "g2_x": return gamepad2.x;
            case "g2_y": return gamepad2.y;
            case "g1_back": return gamepad1.back;
            case "g2_back": return gamepad2.back;
            case "g1_dpdown": return gamepad1.dpad_down;
            case "g2_dpdown": return gamepad2.dpad_down;
            case "g1_dpleft": return gamepad1.dpad_left;
            case "g2_dpleft": return gamepad2.dpad_left;
            case "g1_dpright": return gamepad1.dpad_right;
            case "g2_dpright": return gamepad2.dpad_right;
            case "g1_dpup": return gamepad1.dpad_up;
            case "g2_dpup": return gamepad2.dpad_up;
            case "g1_lbump": return gamepad1.left_bumper;
            case "g2_lbump": return gamepad2.left_bumper;
            case "g1_rbump": return gamepad1.right_bumper;
            case "g2_rbump": return gamepad2.right_bumper;
            case "g1_rstickbut": return gamepad1.right_stick_button;
            case "g2_rstickbut": return gamepad2.right_stick_button;
            case "g1_lstickbut": return gamepad1.left_stick_button;
            case "g2_lstickbut": return gamepad2.left_stick_button;
            case "g1_lstickx": return Math.abs(gamepad1.left_stick_x) > BUTTON_THRESHOLD;
            case "g2_lstickx": return Math.abs(gamepad2.left_stick_x) > BUTTON_THRESHOLD;
            case "g1_lsticky": return Math.abs(gamepad1.left_stick_y) > BUTTON_THRESHOLD;
            case "g2_lsticky": return Math.abs(gamepad2.left_stick_y) > BUTTON_THRESHOLD;
            case "g1_rtrigger": return Math.abs(gamepad1.right_trigger) > BUTTON_THRESHOLD;
            case "g2_rtrigger": return Math.abs(gamepad2.right_trigger) > BUTTON_THRESHOLD;
            case "g1_ltrigger": return Math.abs(gamepad1.left_trigger) > BUTTON_THRESHOLD;
            case "g2_ltrigger": return Math.abs(gamepad2.left_trigger) > BUTTON_THRESHOLD;
            case "g1_start": return gamepad1.start;
            case "g2_start": return gamepad2.start;
            case "g1_guide": return gamepad1.guide;
            case "g2_guide": return gamepad2.guide;
            default: return false;
        }
    }

    public float getValue(Function f) {
        switch(buttonMap.get(f)) {
            case "g1_a": return gamepad1.a ? 1.0f : 0.0f;
            case "g1_b": return gamepad1.b ? 1.0f : 0.0f;
            case "g1_x": return gamepad1.x ? 1.0f : 0.0f;
            case "g1_y": return gamepad1.y ? 1.0f : 0.0f;
            case "g2_a": return gamepad2.a ? 1.0f : 0.0f;
            case "g2_b": return gamepad2.b ? 1.0f : 0.0f;
            case "g2_x": return gamepad2.x ? 1.0f : 0.0f;
            case "g2_y": return gamepad2.y ? 1.0f : 0.0f;
            case "g1_back": return gamepad1.back ? 1.0f : 0.0f;
            case "g2_back": return gamepad2.back ? 1.0f : 0.0f;
            case "g1_dpdown": return gamepad1.dpad_down ? 1.0f : 0.0f;
            case "g2_dpdown": return gamepad2.dpad_down ? 1.0f : 0.0f;
            case "g1_dpleft": return gamepad1.dpad_left ? 1.0f : 0.0f;
            case "g2_dpleft": return gamepad2.dpad_left ? 1.0f : 0.0f;
            case "g1_dpright": return gamepad1.dpad_right ? 1.0f : 0.0f;
            case "g2_dpright": return gamepad2.dpad_right ? 1.0f : 0.0f;
            case "g1_dpup": return gamepad1.dpad_up ? 1.0f : 0.0f;
            case "g2_dpup": return gamepad2.dpad_up ? 1.0f : 0.0f;
            case "g1_lbump": return gamepad1.left_bumper ? 1.0f : 0.0f;
            case "g2_lbump": return gamepad2.left_bumper ? 1.0f : 0.0f;
            case "g1_rbump": return gamepad1.right_bumper ? 1.0f : 0.0f;
            case "g2_rbump": return gamepad2.right_bumper ? 1.0f : 0.0f;
            case "g1_rstickbut": return gamepad1.right_stick_button ? 1.0f : 0.0f;
            case "g2_rstickbut": return gamepad2.right_stick_button ? 1.0f : 0.0f;
            case "g1_lstickbut": return gamepad1.left_stick_button ? 1.0f : 0.0f;
            case "g2_lstickbut": return gamepad2.left_stick_button ? 1.0f : 0.0f;
            case "g1_lstickx": return gamepad1.left_stick_x * (GAMEPAD1_INVERSE_STICKS? -1 : 1);
            case "g2_lstickx": return gamepad2.left_stick_x * (GAMEPAD2_INVERSE_STICKS? -1 : 1);
            case "g1_lsticky": return gamepad1.left_stick_y * (GAMEPAD1_INVERSE_STICKS? -1 : 1);
            case "g2_lsticky": return gamepad2.left_stick_y * (GAMEPAD2_INVERSE_STICKS? -1 : 1);
            case "g1_rtrigger": return gamepad1.right_trigger * (GAMEPAD1_INVERSE_TRIGGERS? -1 : 1);
            case "g2_rtrigger": return gamepad2.right_trigger * (GAMEPAD2_INVERSE_TRIGGERS? -1 : 1);
            case "g1_ltrigger": return gamepad1.left_trigger * (GAMEPAD1_INVERSE_TRIGGERS? -1 : 1);
            case "g2_ltrigger": return gamepad2.left_trigger * (GAMEPAD2_INVERSE_TRIGGERS? -1 : 1);
            case "g1_start": return gamepad1.start ? 1.0f : 0.0f;
            case "g2_start": return gamepad2.start ? 1.0f : 0.0f;
            case "g1_guide": return gamepad1.guide ? 1.0f : 0.0f;
            case "g2_guide": return gamepad2.guide ? 1.0f : 0.0f;
            default: return 0.0f;
        }
    }

    static final Map<DcMotor, Double> motorSpeeds = new HashMap<DcMotor, Double>() {{
        put(collection, COLLECTION_SPEED);
        put(lowOrbitIonCannon, CANNON_SPEED);
    }};

    public static void run(DcMotor motor) {
        if(!motorSpeeds.containsKey(motor)) return;
        motor.setPower(motorSpeeds.get(motor));
    }

    public static void run(DcMotor motor, double speed) {
        if(speed < BUTTON_THRESHOLD) return;
        motor.setPower(speed);
    }

    static final class MotorSet {
        HashSet<DcMotor> motors;
        public MotorSet() { motors = new HashSet<DcMotor>(); }
        public MotorSet(DcMotor one, DcMotor two) { this(); add(one); add(two); }
        public void add(DcMotor m) { motors.add(m); }
        public void run(double speed) {
            for(DcMotor m : motors)
                m.setPower(speed);
        }
        public HashSet<DcMotor> getMotors() { return motors; }
    }

    public static void runMotorSet(MotorSet s, double d) {
        for(DcMotor m : s.getMotors()) {
            m.setPower(d);
        }
    }

    public static MotorSet getMotorSet(DcMotor motor) {
        switch(MOTOR_MODE) {
            default:
            case PARALLEL:
                if(motor == frontLeft || motor == backLeft)
                    return new MotorSet(frontLeft, backLeft);
                else
                    return new MotorSet(frontRight, backRight);
            case SQUARE:
                if(motor == backLeft || motor == frontRight)
                    return new MotorSet(backLeft, frontRight);
                else
                    return new MotorSet(frontLeft, backRight);
        }
    }

    public static void stopAllMotors() {
        for(Map.Entry<DcMotor, Double> m : motorSpeeds.entrySet())
            m.getKey().setPower(0.0);
    }

    static final Map<Servo, Double> servoPositions = new HashMap<Servo, Double>() {{
        put(pushButtonL, PUSHL_POS);
        put(pushButtonR, PUSHR_POS);
    }};

    public static void set(Servo servo) {
        if(!servoPositions.containsKey(servo)) return;
        servo.setPosition(servoPositions.get(servo));
    }

    public static void set(Servo servo, double position) {
        servo.setPosition(position);
    }

    public final void init(HardwareMap hm) {
        hardwareMap = hm;
        runtime = new ElapsedTime();

        frontLeft = hm.dcMotor.get(CONFIG_FL_DRIVE);
        frontRight = hm.dcMotor.get(CONFIG_FR_DRIVE);
        backLeft = hm.dcMotor.get(CONFIG_BL_DRIVE);
        backRight = hm.dcMotor.get(CONFIG_BR_DRIVE);
        collection = hm.dcMotor.get(CONFIG_COLLECT);
        lowOrbitIonCannon = hm.dcMotor.get(CONFIG_CANNON);
        if(HAS_COLOR) colorSensor = hm.colorSensor.get(CONFIG_COLOR);
        if(HAS_IR) irSeekerSensor = hm.irSeekerSensor.get(CONFIG_IR_SEEKER);
        if(HAS_PUSH) pushButtonL = hm.servo.get(CONFIG_SERVO_PUSHL);
        if(HAS_PUSH) pushButtonR = hm.servo.get(CONFIG_SERVO_PUSHR);
        if(HAS_CAP) capBall = hm.servo.get(CONFIG_SERVO_CAP);

        frontLeft.setPower(0.0);
        frontRight.setPower(0.0);
        backLeft.setPower(0.0);
        backRight.setPower(0.0);
        collection.setPower(0.0);
        lowOrbitIonCannon.setPower(0.0);
        if(HAS_PUSH) pushButtonL.scaleRange(SERVO_PUSHL_MIN, SERVO_PUSHL_MAX);
        if(HAS_PUSH) pushButtonR.scaleRange(SERVO_PUSHR_MIN, SERVO_PUSHR_MAX);
        if(HAS_CAP) capBall.scaleRange(SERVO_CAP_MIN, SERVO_CAP_MAX);

        frontLeft.setMode(MODE_DRIVE);
        frontRight.setMode(MODE_DRIVE);
        backLeft.setMode(MODE_DRIVE);
        backRight.setMode(MODE_DRIVE);
        collection.setMode(MODE_AUX);
        lowOrbitIonCannon.setMode(MODE_AUX);
        if(HAS_IR) irSeekerSensor.setMode(MODE_IR);
        if(HAS_PUSH) pushButtonL.setPosition(SERVO_PUSHL_INIT);
        if(HAS_PUSH) pushButtonR.setPosition(SERVO_PUSHR_INIT);
        if(HAS_CAP) capBall.setPosition(SERVO_CAP_INIT);

        frontLeft.setZeroPowerBehavior(MODE_ZERO);
        frontRight.setMode(MODE_DRIVE);
        backLeft.setMode(MODE_DRIVE);
        backRight.setMode(MODE_DRIVE);
        collection.setMode(MODE_AUX);
        lowOrbitIonCannon.setMode(MODE_AUX);

        gamepad1.setJoystickDeadzone(GAMEPAD_ONE_DEADZONE);
        gamepad2.setJoystickDeadzone(GAMEPAD_TWO_DEADZONE);

        telemetry.setItemSeparator(TEL_ITEM_SEPARATOR);
        telemetry.setAutoClear(TEL_AUTO_CLEAR);
        telemetry.setMsTransmissionInterval(TEL_MS_INTERVAL);
        telemetry.setCaptionValueSeparator(TEL_CAPTION_SEPARATOR);
    }

    public void runOpMode() {
        while(opModeIsActive()) {
            for(Function f : Function.values()) {
                if(isActive(f)) {
                    switch(f) {
                        case COLLECT: run(collection); break;
                        case FIRE: run(lowOrbitIonCannon); break;
                        case PUSH_LEFT: set(pushButtonL); break;
                        case PUSH_RIGHT: set(pushButtonR); break;
                        case PUSH_BOTH: set(pushButtonL); set(pushButtonR); break;
                        case PUSH_RIGHT_INC: set(pushButtonR,
                                getValue(Function.PUSH_RIGHT_INC)); break;
                        case PUSH_LEFT_INC: set(pushButtonL,
                                getValue(Function.PUSH_LEFT_INC)); break;
                        case STOP_ALL_MOTORS: stopAllMotors(); break;
                        case DRIVE_LEFT: runMotorSet(getMotorSet(frontLeft),
                                getValue(Function.DRIVE_LEFT)); break;
                        case DRIVE_RIGHT: runMotorSet(getMotorSet(frontRight),
                                getValue(Function.DRIVE_RIGHT)); break;
                        default: break;
                    }
                }
            }
        }
    }
}
