package ru.SlimeEngine;

import java.util.TimerTask;

public class FrameCounter extends TimerTask {

        @Override
        public void run() {
        	Settings.realFrameRate = Settings.frameCounter;
        	Settings.frameCounter = 0;
        }
}
