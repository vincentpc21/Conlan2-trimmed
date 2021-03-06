/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package club.towr5291.training;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import club.towr5291.functions.TOWR5291Toggle;


/**
 * This file contains the TeleOp code for FTC 11230 ElektraKatz for Velocity Vortex competition
 * The robot uses the TileRunner BD base with team built accessories attached to it.
 * The drive system consists of four AndyMark NeveRest 60 motors (2 left, 2 right)
 * The lift is driven by 1 AndyMark NeveRest 40 motor
 *
 * The motors are defined as follows:
 * leftMotor1   = "left motor1"
 * leftMotor2   = "left motor2"
 * rightMotor1  = "right motor1"
 * rightMotor2  = "right motor2"
 * upMotor      = "up motor"
 * <p>
 * This OpMode started with the FIRST example "TemplateOpMode_Linear"
 * Team member Conlan Houston modified the Template code to create this TeleOp OpMode
 *
 */
//@Disabled
@TeleOp(name = "Conmanbot: Old OpMode", group = "ElektraKatz")
public class chouston3_trimmed extends LinearOpMode {

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();
    public DcMotor leftMotor1 = null;
    public DcMotor rightMotor1 = null;
    public DcMotor leftMotor2 = null;
    public DcMotor rightMotor2 = null;
    // TODO: uncomment these when arm is added
    public DcMotor LanderMotor1 = null;
    //public DcMotor LanderArm2 = null;
    //public Servo landerClaw = null;
   /* public Servo ElementClaw = null;

    static double LANDER_CLAW_CLOSE = 1;

*/

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        /* eg: Initialize the hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names assigned during the robot configuration
         * st   ep (using the FTC Robot Controller app on the phone).
         */
        //Test
        //things like FLM means front left motor
        leftMotor1 = hardwareMap.get(DcMotor.class, "leftMotor1");
        rightMotor1 = hardwareMap.get(DcMotor.class, "rightMotor1");
        leftMotor2 = hardwareMap.get(DcMotor.class, "leftMotor2");
        rightMotor2 = hardwareMap.get(DcMotor.class, "rightMotor2");
        LanderMotor1 = hardwareMap.get(DcMotor.class, "LanderArm1");
        // TODO: uncomment these when arm is added
        //LanderArm1 = hardwareMap.get(DcMotor.class, "LanderArm1");
        //LanderArm2 = hardwareMap.get(DcMotor.class, "LanderArm2");
        //landerClaw = hardwareMap.servo.get("LanderServo1");

        // get a reference to our digitalTouch object.
        // liftLower = hardwareMap.get(DigitalChannel.class, "lift lower");


//        beaconHit   = hardwareMap.servo.get("actuator2");
//        frontClaw   = hardwareMap.servo.get("actuator3");

        // eg: Set the drive motor directions:
        // "Reverse" the motor that runs backwards when connected directly to the battery
        leftMotor1.setDirection(DcMotor.Direction.FORWARD);
        leftMotor2.setDirection(DcMotor.Direction.FORWARD);// Set to REVERSE if using AndyMark motors
        rightMotor1.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        rightMotor2.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        LanderMotor1.setDirection(DcMotor.Direction.FORWARD);

        /*//right strafe
        leftMotor1.setDirection(DcMotor.Direction.FORWARD);
        leftMotor2.setDirection(DcMotor.Direction.REVERSE);// Set to REVERSE if using AndyMark motors
        rightMotor1.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        rightMotor2.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors

        //left strafe
        leftMotor1.setDirection(DcMotor.Direction.REVERSE);
        leftMotor2.setDirection(DcMotor.Direction.FORWARD);// Set to REVERSE if using AndyMark motors
        rightMotor1.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        rightMotor2.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        //forward
        leftMotor1.setDirection(DcMotor.Direction.FORWARD);
        leftMotor2.setDirection(DcMotor.Direction.FORWARD);// Set to REVERSE if using AndyMark motors
        rightMotor1.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        rightMotor2.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors

        //reverse
        leftMotor1.setDirection(DcMotor.Direction.REVERSE);
        leftMotor2.setDirection(DcMotor.Direction.REVERSE);// Set to REVERSE if using AndyMark motors
        rightMotor1.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        rightMotor2.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        */

        // TODO: uncomment these when arm is added
        //LanderArm1.setDirection(DcMotor.Direction.FORWARD);
        //LanderArm2.setDirection(DcMotor.Direction.REVERSE);


        // set the digital channel to input.
        // liftLower.setMode(DigitalChannel.Mode.INPUT);

        double left = 0;
        double right = 0;
        double max = 0;
        double leftDrive = 0;
        double rightDrive = 0;
        double velocity = 0;
        double turn = 0;


        TOWR5291Toggle toggleButton = new TOWR5291Toggle(gamepad1.a);
        toggleButton.setDebounce(250);

//        upMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
//            upMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            // eg: Run wheels in tank mode (note: The joystick goes negative when pushed forwards)
            // rightMotor1.setPower(-gamepad1.left_stick_y);
            // rightMotor2.setPower(-gamepad1.left_stick_y);
            // leftMotor1.setPower(-gamepad1.right_stick_y);
            //leftMotor2.setPower(-gamepad1.right_stick_y);
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//            if (gamepad2.left_stick_y < 0 && upMotor.getCurrentPosition() < 10000) {
//                upMotor.setPower(gamepad2.left_stick_y);
//            } else if (gamepad2.left_stick_y>=0) {
//                upMotor.setPower(gamepad2.left_stick_y);
//            }

//            upMotor.setPower(0);
//            upMotor.setPower(gamepad2.left_stick_y);
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            if (toggleButton.toggleState(gamepad1.a)) {
                left = gamepad1.right_stick_x;//(-gamepad1.left_stick_y + gamepad1.right_stick_x);
                right = -gamepad1.right_stick_x;//(gamepad1.left_stick_y + -gamepad1.right_stick_x);

                /*if (toggleButton.toggleState(gamepad2.b)) {
                    left =
                }*/

                //turn = gamepad1.right_stick_x;
                //velocity = -gamepad1.left_stick_y;

                //leftDrive = Range.clip(velocity - turn, -1.0, 1.0);
                //rightDrive = Range.clip(velocity + turn, -1.0, 1.0);

                //leftMotor1.setPower(leftDrive);
                //leftMotor2.setPower(leftDrive);
                //rightMotor1.setPower(rightDrive);
                //rightMotor2.setPower(rightDrive);


                //Left

            /*if (-gamepad1.left_stick_x == 1) {
                leftMotor1.setPower(-1);
                leftMotor2.setPower(1);
                rightMotor1.setPower(1);
                rightMotor2.setPower(-1);
            }
            //right
            else if (gamepad1.left_stick_x == 1) {
                leftMotor1.setPower(1);
                leftMotor2.setPower(-1);
                rightMotor1.setPower(-1);
                rightMotor2.setPower(1);
            }
            //forward
            else if (-gamepad1.left_stick_y == 1) {
                leftMotor1.setPower(1);
                leftMotor2.setPower(1);
                rightMotor1.setPower(1);
                rightMotor2.setPower(1);
            }
            //backward
            else if (gamepad1.left_stick_y == 1) {
                leftMotor1.setPower(-1);
                leftMotor2.setPower(-1);
                rightMotor1.setPower(-1);
                rightMotor2.setPower(-1);
            }*/


                max = Math.max(Math.abs(left), Math.abs(right));
                /*
                if (max > 1.0) {

                    // left = left / max;
                    // left += max;
                    // left = left + max;

                    right /= max;


                }
                */

                // Clamp the input values to be sure they stay within -1 and 1
                //left = clamp(-1.0, 1.0, left);
                //right = clamp(-1.0, 1.0, right);


                telemetry.addData("Mode", "running");
                telemetry.addData("raw", " L1=" + leftMotor1 + " L2=" + leftMotor2 + " R1=" + rightMotor1 + " R2=" + rightMotor2);
                telemetry.addData("power", " L1=" + leftMotor1 + " L2=" + leftMotor2 + " R1=" + rightMotor1 + " R2=" + rightMotor2);
                telemetry.update();

                leftMotor1.setPower(left);
                leftMotor2.setPower(left);
                rightMotor1.setPower(right);
                rightMotor2.setPower(right);

                //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`
                //Left
                if (-gamepad1.left_stick_x == 1) {
                    leftMotor1.setPower(1.0);
                    leftMotor2.setPower(-1.0);
                    rightMotor1.setPower(-1.0);
                    rightMotor2.setPower(1.0);
                }
                //right
                else if (gamepad1.left_stick_x == 1) {
                    leftMotor1.setPower(-1.0);
                    leftMotor2.setPower(1.0);
                    rightMotor1.setPower(1.0);
                    rightMotor2.setPower(-1.0);
                }
                //forward
                else if (-gamepad1.left_stick_y == 1) {
                    leftMotor1.setPower(1.0);
                    leftMotor2.setPower(1.0);
                    rightMotor1.setPower(1.0);
                    rightMotor2.setPower(1.0);
                }
                //backward
                else if (gamepad1.left_stick_y == 1) {
                    leftMotor1.setPower(-1.0);
                    leftMotor2.setPower(-1.0);
                    rightMotor1.setPower(-1.0);
                    rightMotor2.setPower(-1.0);
                }
                if (-gamepad2.left_stick_y == 1) {
                    LanderMotor1.setPower(1.0);
                } else if (gamepad2.left_stick_y == 1) {
                    LanderMotor1.setPower(-1.0);
                }


                //TODO: marker, delete when done.
                /*
                 //auto  stop
                 if (gamepad1.left_stick_y == 0 && gamepad1.left_stick_x == 0 && gamepad1.right_stick_x == 0) {
                 leftMotor1.setPower(0.0);
                 leftMotor2.setPower(0.0);
                 rightMotor1.setPower(0.0);
                 rightMotor2.setPower(0.0);
                 }
                 */

                if (gamepad1.dpad_down) {
                    // TODO: uncomment these when arm is added
                    //LanderArm1.setPower(.5);
                } else if (gamepad1.dpad_up) {
                    // TODO: uncomment these when arm is added
                    //LanderArm1.setPower(-.5);
                }
                //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
/*
 if (gamepad2.y) {

 MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.lion);
 mediaPlayer.start();
 }
 */
                // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`
           /* if ((gamepad2.left_stick_y < 0) && (upMotor.getCurrentPosition()<FULL)){
                upMotor.setPower(-gamepad2.left_stick_y);
            }

                else if ((gamepad2.left_stick_y >0)&&(!liftLower.getState())){
                upMotor.setPower(-gamepad2.left_stick_y);
            }
            else {
                upMotor.setPower(0);
            }

           */ //upMotor.setPower(-gamepad2.left_stick_y);
                //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


                telemetry.update();
            }
        }



/*
    private double clamp(double min, double max, double val) {

        double retVal = val;

        if (max < val || val < min) {
            if (max < val) {
                retVal = max;
            }
            else {
                retVal = min;
            }
        }

        return retVal;
    }*/

    }}
