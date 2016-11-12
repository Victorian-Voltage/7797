package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="MainTeleOp", group="7797")
public class 7797TeleOp extends LinearTeleOp { 
    /* Public OpMode members. */
    public DcMotor  lfMotor   = null;
    public DcMotor  rfMotor  = null;
    public DcMotor  lbMotor   = null;
    public DcMotor  rbMotor  = null;
    public DcMotor  cannon  = null;
    public DcMotor  collect = null;
    
    /* local OpMode members. */
    HardwareMap hwMap           =  null;

    /* Constructor */
    public 7797TeleOp){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        lfMotor   = hwMap.dcMotor.get("lfd");
        rfMotor  = hwMap.dcMotor.get("rfd");
        lbMotor = hwMap.dcMotor.get("lbd");
        rbMotor = hwMap.dcMotor.get("rbd");
        cannon = hwMap.dcMotor.get("can");
        collect = hwMap.dcMotor.get("col");
        lfMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        lbMotor.setDirection(DcMotor.Direction.FORWARD);
        rfMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        rbMotor.setDirection(DcMotor.Direction.REVERSE);
         
        // Set all motors to zero power
        lfMotor.setPower(0);
        rfMotor.setPower(0);
        lbMotor.setPower(0);
        rbMotor.setPower(0);
        cannon.setPower(0);
        collect.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        lfMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rfMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lbMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rbMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        cannon.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        collect.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    
    public void runOpMode() {
      telemetry.addData("Status", "Intialized");
      telemetry.update();
      
      waitForStart();
      
      while(opModeIsActive()) {
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
        
        lfMotor.setPower(-gamepad1.left_stick_y);
        lbMotor.setPower(-gamepad1.left_stick_y);
        rfMotor.setPower(-gamepad1.right_stick_y);
        rbMotor.setPower(-gamepad1.right_stick_y);
        
        cannon.setPower(-gamepad1.left_stick_y);
        collect.setPower(-gamepad1.right_stick_y);
      }
    }
    
   long start_time = 0;
    
  @Override
  public void start() {
    super.start();
    // Save the system clock when start is pressed
    start_time = System.currentTimeMillis();
  }

  @Override
  public void loop() {
    float powerlevel = 0.0f;

    // If we're still with the first 3 seconds after pressing start keep driving forward
    if (System.currentTimeMillis() < start_time + 3000) {
      powerlevel = 0.5f;
    }
    lbMotor.setPower(powerlevel);
    lfMotor.setPower(powerlevel);
    rbMotor.setPower(powerlevel);
    rfMotor.setPower(powerlevel);
  }
}
