package ru.SlimeEngine;

import java.util.TimerTask;

public class mFrameCounter extends TimerTask {

        @Override
        public void run() {
        	mSettings.realFrameRate = mSettings.frameCounter;
        	mSettings.frameCounter = 0;
        }
}
